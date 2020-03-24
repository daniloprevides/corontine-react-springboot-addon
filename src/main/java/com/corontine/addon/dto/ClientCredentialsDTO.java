package com.corontine.addon.dto;

import java.util.List;

public class ClientCredentialsDTO {
    String client_id;
    String secret;
    String code;
    String authentication_uri;
    String api_uri;
    List<ScopesDTO> scopes;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthentication_uri() {
        return authentication_uri;
    }

    public void setAuthentication_uri(String authentication_uri) {
        this.authentication_uri = authentication_uri;
    }

    public String getApi_uri() {
        return api_uri;
    }

    public void setApi_uri(String api_uri) {
        this.api_uri = api_uri;
    }

    public List<ScopesDTO> getScopes() {
        return scopes;
    }

    public void setScopes(List<ScopesDTO> scopes) {
        this.scopes = scopes;
    }
}
