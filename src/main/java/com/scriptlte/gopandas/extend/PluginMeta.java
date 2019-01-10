package com.scriptlte.gopandas.extend;

public class PluginMeta {
    private int priority = 10;
    private Object plugin;

    public PluginMeta(Object plugin) {
        this.plugin = plugin;
    }

    public PluginMeta(Object plugin, int priority) {
        this.priority = priority;
        this.plugin = plugin;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Object getPlugin() {
        return plugin;
    }

    public void setPlugin(Object plugin) {
        this.plugin = plugin;
    }
}
