package com.corontine.addon.dto;

public class CorontineClientCredentialsDTO {
    public String accessToken;
    public String refreshToken;
    public String expiresInToken;
    public String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpiresInToken() {
        return expiresInToken;
    }

    public void setExpiresInToken(String expiresInToken) {
        this.expiresInToken = expiresInToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
