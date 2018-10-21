package com.juliomakita.security.service;

import com.juliomakita.security.model.TokenResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class SecurityService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ResourceOwnerPasswordResourceDetails resource;

    public TokenResponse getUserToken(final String username, final String password) {

        ResponseEntity<TokenResponse> response = null;

        try {

            String credentials = resource.getClientId() + ":" + resource.getClientSecret();
            String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("Authorization", "Basic " + encodedCredentials);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            final String accessTokenUrl = generateTokenUrl(resource, username, password);

            response = restTemplate.exchange(accessTokenUrl, HttpMethod.POST, entity,
                    new ParameterizedTypeReference<TokenResponse>() {
                    });

        } catch (HttpClientErrorException e) {
            throw new SecurityException("The credentials provided were invalid.", e);
        }

        return response.getBody();
    }

    private String generateTokenUrl(final ResourceOwnerPasswordResourceDetails resource, final String username, final String password) {

        String accessTokenUrl = resource.getAccessTokenUri();
        accessTokenUrl += "?grant_type=" + resource.getGrantType();
        accessTokenUrl += "&username=" + username;
        accessTokenUrl += "&password=" + password;
        accessTokenUrl += "&scope=openid";

        return accessTokenUrl;
    }
}

