package com.corontine.addon.dto;

import java.util.List;

public class AuthorizationCodeDTO {
    public String name;
    public String description;
    public List<ScopesDTO> scopes;
    public String code;
    public String redirect_uri;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public List<ScopesDTO> getScopes() {
        return scopes;
    }

    public void setScopes(List<ScopesDTO> scopes) {
        this.scopes = scopes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }





}
