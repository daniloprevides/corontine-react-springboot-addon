package com.corontine.addon.dto;

import java.util.List;

public class RedirectorDTO {
    public List<PluginDTO> plugins;

    public List<PluginDTO> getPlugins() {
        return plugins;
    }

    public void setPlugins(List<PluginDTO> plugins) {
        this.plugins = plugins;
    }
}
