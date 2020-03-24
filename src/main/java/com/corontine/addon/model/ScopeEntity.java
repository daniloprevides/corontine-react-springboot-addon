package com.corontine.addon.model;

import org.springframework.data.annotation.Id;

public class ScopeEntity {

    @Id
    public String id;

    public String appName;
    public String appDescription;
    public String apiUrl;
    public String authenticationUrl;
    public String accessToken;
    public String refreshToken;
    public String clientName;
    public String clientSecret;
    public String code;
    public String scope;

    public ScopeEntity() {}

}