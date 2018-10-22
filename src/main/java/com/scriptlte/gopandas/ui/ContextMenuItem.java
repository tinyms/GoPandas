package com.scriptlte.gopandas.ui;

public class ContextMenuItem {
    /**
     * ID
     */
    private String id;
    /**
     * 标签
     */
    private String label;
    /**
     * 图标
     */
    private String icon = "";
    /**
     * 单击事件回调函数名，可从全局插件中引入
     */
    private String click = "";
    /**
     * 右键选中事件回调函数名，可从全局插件中引入
     */
    private String state = "";
    /**
     * 菜单权限
     */
    private String perm = "";

    public ContextMenuItem(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public ContextMenuItem(String id, String label, String click) {
        this.id = id;
        this.label = label;
        this.click = click;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }
}
