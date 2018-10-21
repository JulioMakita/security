package com.juliomakita.security;

import com.okta.spring.config.OktaOAuth2Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.web.client.RestTemplate;

@EnableOAuth2Sso
@Configuration
public class SpringSecurityWebAppConfig extends WebSecurityConfigurerAdapter {

    private final OktaOAuth2Properties oktaOAuth2Properties;

    @Value("${okta.oauth2.accessTokenUri}")
    private String accessTokenUri;

    public SpringSecurityWebAppConfig(OktaOAuth2Properties oktaOAuth2Properties) {
        this.oktaOAuth2Properties = oktaOAuth2Properties;
    }

    @Bean
    public ResourceOwnerPasswordResourceDetails resourceOwnerPasswordResourceDetails() {
        ResourceOwnerPasswordResourceDetails passwordResourceDetails = new ResourceOwnerPasswordResourceDetails();
        passwordResourceDetails.setAccessTokenUri(accessTokenUri);
        passwordResourceDetails.setClientId(oktaOAuth2Properties.getClientId());
        passwordResourceDetails.setClientSecret(oktaOAuth2Properties.getClientSecret());
        passwordResourceDetails.setScope(oktaOAuth2Properties.getScopes());
        return passwordResourceDetails;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/generateToken");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                //.antMatchers(HttpMethod.GET, "/", "/token").permitAll()
                .antMatchers(HttpMethod.POST, "/generateToken").permitAll()
                .antMatchers("/img/**").permitAll().anyRequest()
                .authenticated();

    }
}
