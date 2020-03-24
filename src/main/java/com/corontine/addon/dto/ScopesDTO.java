package com.corontine.addon.dto;

public class ScopesDTO {
    public String name;
    public String description;

    public ScopesDTO(String name){
        this.name = name;
    }

    public ScopesDTO(){
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
