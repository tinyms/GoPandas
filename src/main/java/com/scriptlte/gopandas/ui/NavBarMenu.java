package com.scriptlte.gopandas.ui;

import java.util.ArrayList;
import java.util.List;

public class NavBarMenu {
    private NavBarMenuItem topLevelItem;
    private List<NavBarMenuItem> children = new ArrayList<>();

    public NavBarMenu(NavBarMenuItem topLevelItem) {
        this.topLevelItem = topLevelItem;
    }

    public NavBarMenuItem getTopLevelItem() {
        return topLevelItem;
    }

    public void setTopLevelItem(NavBarMenuItem topLevelItem) {
        this.topLevelItem = topLevelItem;
    }

    public List<NavBarMenuItem> getChildren() {
        return children;
    }

    public void setChildren(List<NavBarMenuItem> children) {
        this.children = children;
    }

    /**
     * 添加子菜单
     * @param navBarMenuItem 子菜单
     */
    public void append(NavBarMenuItem navBarMenuItem){
        this.children.add(navBarMenuItem);
    }
}
