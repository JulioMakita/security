package com.juliomakita.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TokenResponse {

    @JsonProperty("access_token")
    private String accessToken = null;

    @JsonProperty("refresh_token")
    private String refreshToken = null;

    @JsonProperty("expires_in")
    private Integer expiresIn = null;

    @JsonProperty("token_type")
    private String tokenType = null;

    @JsonProperty("id_token")
    private String idToken;

    public TokenResponse accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public TokenResponse refreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public TokenResponse expiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public TokenResponse tokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TokenResponse tokenResponse = (TokenResponse) o;
        return Objects.equals(this.accessToken, tokenResponse.accessToken)
                && Objects.equals(this.refreshToken, tokenResponse.refreshToken)
                && Objects.equals(this.expiresIn, tokenResponse.expiresIn)
                && Objects.equals(this.tokenType, tokenResponse.tokenType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, refreshToken, expiresIn, tokenType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TokenResponse {\n");

        sb.append("    accessToken: ").append(toIndentedString(accessToken)).append("\n");
        sb.append("    refreshToken: ").append(toIndentedString(refreshToken)).append("\n");
        sb.append("    expiresIn: ").append(toIndentedString(expiresIn)).append("\n");
        sb.append("    tokenType: ").append(toIndentedString(tokenType)).append("\n");
        sb.append("    idToken: ").append(toIndentedString(idToken)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
