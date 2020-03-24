package com.corontine.addon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PluginDTO {
    String id;
    String name;
    String apiUrl;
    String componentsUrl;
    String environment;
    List<ComponentDTO> components;
    String pluginType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getComponentsUrl() {
        return componentsUrl;
    }

    public void setComponentsUrl(String componentsUrl) {
        this.componentsUrl = componentsUrl;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public List<ComponentDTO> getComponents() {
        return components;
    }

    public void setComponents(List<ComponentDTO> components) {
        this.components = components;
    }

    public String getPluginType() {
        return pluginType;
    }

    public void setPluginType(String pluginType) {
        this.pluginType = pluginType;
    }
}
