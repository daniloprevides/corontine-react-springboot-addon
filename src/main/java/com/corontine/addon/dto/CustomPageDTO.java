package com.corontine.addon.dto;

public class CustomPageDTO {
    String name;
    String description;
    PluginDTO pageApi;
    PluginDTO api;
    FieldsDTO component;
    String elementName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PluginDTO getPageApi() {
        return pageApi;
    }

    public void setPageApi(PluginDTO pageApi) {
        this.pageApi = pageApi;
    }

    public PluginDTO getApi() {
        return api;
    }

    public void setApi(PluginDTO api) {
        this.api = api;
    }

    public FieldsDTO getComponent() {
        return component;
    }

    public void setComponent(FieldsDTO component) {
        this.component = component;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }
}
