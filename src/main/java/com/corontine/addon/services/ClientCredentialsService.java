package com.corontine.addon.services;

import com.corontine.addon.dto.*;
import com.corontine.addon.model.ScopeEntity;
import com.corontine.addon.repository.ScopeRepository;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientCredentialsService {

    @Autowired
    public ScopeRepository scopeRepository;

    @Autowired
    public CorontineService corontineService;

    public AuthorizationCodeDTO getCode(){
        var authorizationCode = new AuthorizationCodeDTO();
        var scopes = Arrays.asList(("token_info page_create page_update page_delete page_read plugin_create plugin_" +
                "update plugin_delete plugin_read components_create components_update components_delete components_read fields_" +
                "read scope_create menu_add_entry").split(" "));

        List<ScopesDTO> scopesList = scopes.stream().map(s -> new ScopesDTO(s)).collect(Collectors.toList());
        authorizationCode.scopes = scopesList;
        authorizationCode.code = UUID.randomUUID().toString();
        authorizationCode.name = "spring-addon";
        authorizationCode.description = "A Plugin using spring boot";
        authorizationCode.redirect_uri = "http://localhost:8000/callback";


        var entity = new ScopeEntity();
        entity.code = authorizationCode.code;
        entity.appName = authorizationCode.name;
        entity.appDescription = authorizationCode.description;
        scopeRepository.save(entity);

        return authorizationCode;
    }


    public AddonResponseDTO addPlugin(ClientCredentialsDTO clientCredentialsDTO){
        //finding code
        var scopeEntity = scopeRepository.findOneByCode(clientCredentialsDTO.getCode());
        scopeEntity.apiUrl = clientCredentialsDTO.getApi_uri();
        scopeEntity.authenticationUrl = clientCredentialsDTO.getAuthentication_uri();
        scopeEntity.clientName = clientCredentialsDTO.getClient_id();
        scopeEntity.clientSecret = clientCredentialsDTO.getSecret();
        scopeEntity = scopeRepository.save(scopeEntity);

        var loginResponse = corontineService.login(scopeEntity.clientName,scopeEntity.clientSecret,scopeEntity.authenticationUrl);
        scopeEntity.accessToken = loginResponse.accessToken;
        scopeEntity.refreshToken = loginResponse.refreshToken;
        scopeEntity.scope = loginResponse.scope;
        scopeRepository.save(scopeEntity);

        var plugin = new PluginDTO();
        plugin.setName(scopeEntity.appName);
        plugin.setEnvironment("DEVELOPMENT");
        plugin.setApiUrl("http://localhost:8000/recipes");
        plugin.setComponentsUrl("http://localhost:8000/components");
        plugin.setPluginType("API");
        var component = new ComponentDTO();
        component.setName("recipes-component");
        component.setUrl("http://localhost:8000/components/recipes/build/bundle.js");
        plugin.setComponents(Arrays.asList(component));
        var baseUrl = scopeEntity.apiUrl;

        var pluginCreated = corontineService.createPlugin(baseUrl,plugin,scopeEntity.accessToken);
        var customElement = corontineService.getCustomComponent(baseUrl,scopeEntity.accessToken,"custom-data");
        var customPage = new CustomPageDTO();
        customPage.setName("Recipes");
        customPage.setDescription("Manage Recipes");
        customPage.setApi(pluginCreated);
        customPage.setPageApi(pluginCreated);
        customPage.setComponent(customElement);
        customPage.setElementName("recipes-component");
        var page = corontineService.addCustomPage(baseUrl,scopeEntity.accessToken,customPage);

        corontineService.addScope(baseUrl,scopeEntity.accessToken,"recipes_create","Create Recipes");
        corontineService.addScope(baseUrl,scopeEntity.accessToken,"recipes_read","Read Recipes");
        corontineService.addScope(baseUrl,scopeEntity.accessToken,"recipes_update","Update Recipes");
        corontineService.addScope(baseUrl,scopeEntity.accessToken,"recipes_delete","Delete Recipes");

        corontineService.addEntryToCustomMenu(baseUrl,scopeEntity.accessToken,"Recipes","recipes_read",page);

        return new AddonResponseDTO(true,true);
    }

}
