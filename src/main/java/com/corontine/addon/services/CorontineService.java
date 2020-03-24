package com.corontine.addon.services;

import com.corontine.addon.dto.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;


@Service
public class CorontineService {

    private Object f;

    private RestTemplate getRestClient(){
        var rest = new RestTemplate();

        return rest;
    }
    public CorontineClientCredentialsDTO login(String clientId, String clientSecret, String authenticationUrl){
        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");

        var http = getRestClient();
        var authDto = new AuthDTO();
        authDto.setClient_id(clientId);
        authDto.setClient_secret(clientSecret);
        authDto.setGrant_type("client_credentials");
        var requestUrl = authenticationUrl + "/oauth/token";
        HttpEntity<AuthDTO> requestEntity = new HttpEntity<AuthDTO>(authDto, headers);

        ResponseEntity<CorontineClientCredentialsDTO> responseEntity = http.exchange(requestUrl, HttpMethod.POST, requestEntity, CorontineClientCredentialsDTO.class);
        return responseEntity.getBody();
    }

    public RedirectorDTO getRedirector(String apiUrl, String token){
        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        headers.add("Authorization", "Bearer " + token);

        var http = getRestClient();
        var requestUrl = apiUrl;
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<RedirectorDTO> responseEntity = http.exchange(requestUrl, HttpMethod.GET, requestEntity, RedirectorDTO.class);

        return responseEntity.getBody();
    }

    public PluginDTO createPlugin(String apiUrl, PluginDTO plugin, String token){
        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        headers.add("Authorization", "Bearer " + token);
        var redirector = this.getRedirector(apiUrl,token);
        var pluginByRedirector = getPluginByName(redirector,"Plugins");

        var http = getRestClient();
        var requestUrl = pluginByRedirector.getApiUrl();
        HttpEntity<PluginDTO> requestEntity = new HttpEntity<PluginDTO>(plugin, headers);
        try {
            ResponseEntity<PluginDTO> responseEntity = http.exchange(requestUrl, HttpMethod.POST, requestEntity, PluginDTO.class);
            return responseEntity.getBody();
        }catch(Error ex){
            System.out.println(ex.getMessage());
        }

        return null;

    }

    public ScopesDTO addScope(String apiUrl, String token, String scope, String description){
        var newScope = new ScopesDTO();
        newScope.setName(scope);
        newScope.setDescription(description);

        var headers = new HttpHeaders();
        var http = getRestClient();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        headers.add("Authorization", "Bearer " + token);

        var redirector = this.getRedirector(apiUrl,token);
        var scopesPlugin = this.getPluginByName(redirector,"scopes_api");
        var requestUrl = scopesPlugin.getApiUrl();

        ResponseEntity<ScopesDTO> responseEntity = http.exchange(
                requestUrl, HttpMethod.POST, new HttpEntity(newScope,headers),
                ScopesDTO.class);

        return responseEntity.getBody();
    }

    public PagionatedResultDTO addEntryToCustomMenu(String apiUrl, String token, String name, String permission, PageDTO page){
        var headers = new HttpHeaders();
        var http = getRestClient();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        headers.add("Authorization", "Bearer " + token);

        var redirector = this.getRedirector(apiUrl,token);
        var plugin = this.getPluginByName(redirector,"menu_api");
        var requestUrl = plugin.getApiUrl() + "/add/entry";

        var menuEntry = new MenuEntryDTO();
        menuEntry.setName(name);
        menuEntry.setPageId(page.getId());
        menuEntry.setPageName(page.getName());
        menuEntry.setRequiredPermission(permission);

        ResponseEntity<PagionatedResultDTO> responseEntity = http.exchange(
                requestUrl, HttpMethod.PUT, new HttpEntity(menuEntry,headers),
                PagionatedResultDTO.class);

        return responseEntity.getBody();
    }




    public PageDTO addCustomPage(String apiUrl, String token, CustomPageDTO page){
        var headers = new HttpHeaders();
        var http = getRestClient();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        headers.add("Authorization", "Bearer " + token);

        var redirector = this.getRedirector(apiUrl,token);
        var pages = this.getPluginByName(redirector,"pages_api");
        var requestUrl = pages.getApiUrl() + "/custom/page";

        ResponseEntity<PageDTO> responseEntity = http.exchange(
                requestUrl, HttpMethod.POST, new HttpEntity(page,headers),
                PageDTO.class);

        return responseEntity.getBody();
    }

    public FieldsDTO getCustomComponent(String apiUrl, String token, String name) {
        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        headers.add("Authorization", "Bearer " + token);
        var redirector = this.getRedirector(apiUrl, token);
        var pluginByRedirector = getPluginByName(redirector, "Fields");

        var http = getRestClient();
        var requestUrl = pluginByRedirector.getApiUrl();
        try {
            ResponseEntity responseEntity = http.exchange(
                    requestUrl, HttpMethod.GET, new HttpEntity(headers),
                    PagionatedResultDTO.class);

            PagionatedResultDTO fieldsList = (PagionatedResultDTO) responseEntity.getBody();
            final FieldsDTO result = new FieldsDTO();;
            fieldsList.getItems().forEach(f -> {
                var map = (LinkedHashMap) f;
                if (map.get("name").toString().equalsIgnoreCase(name)){
                    result.setName(name);
                    result.setId(map.get("id").toString());
                }
            });

            return result;

        } catch (Error ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }


    private PluginDTO getPluginByName(RedirectorDTO redirectorDTO, String name){
        return redirectorDTO.plugins.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst().get();
    }


}
