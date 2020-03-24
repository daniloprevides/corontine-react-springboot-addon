package com.corontine.addon.dto;

public class AddonResponseDTO {
    Boolean requireLogoff;
    Boolean requireReload;

    public AddonResponseDTO(boolean requireLogoff, boolean requireReload){
        this.requireLogoff = requireLogoff;
        this.requireReload = requireReload;
    }

    public Boolean getRequireLogoff() {
        return requireLogoff;
    }

    public void setRequireLogoff(Boolean requireLogoff) {
        this.requireLogoff = requireLogoff;
    }

    public Boolean getRequireReload() {
        return requireReload;
    }

    public void setRequireReload(Boolean requireReload) {
        this.requireReload = requireReload;
    }
}
