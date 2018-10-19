package com.scriptlte.gopandas.ui;

public class NavBarMenuItem {
    private String id;
    private String label;
    private String perm="";
    private String href = "javascript:void(0)";
    /**
     * _blank、_custom、_sidebar，默认 _sidebar
     */
    private String target = "_sidebar";
    private int sideBarWidth = 300;

    public NavBarMenuItem(String label) {
        this.label = label;
    }

    public NavBarMenuItem(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public NavBarMenuItem(String id, String label, String perm) {
        this.id = id;
        this.label = label;
        this.perm = perm;
    }

    public NavBarMenuItem(String id, String label, String href, String target) {
        this.id = id;
        this.label = label;
        this.href = href;
        this.target = target;
    }


    public NavBarMenuItem(String id, String label, String href, String target, int sideBarWidth) {
        this.id = id;
        this.label = label;
        this.href = href;
        this.target = target;
        this.sideBarWidth = sideBarWidth;
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

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getSideBarWidth() {
        return sideBarWidth;
    }

    public void setSideBarWidth(int sideBarWidth) {
        this.sideBarWidth = sideBarWidth;
    }


}
