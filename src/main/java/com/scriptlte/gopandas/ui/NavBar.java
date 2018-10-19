package com.scriptlte.gopandas.ui;

import com.scriptlte.gopandas.plugins.INavBar;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class NavBar {
    private List<NavBarMenu> navBarMenus = new ArrayList<>();

    public List<NavBarMenu> getNavBarMenus() {
        return navBarMenus;
    }

    public void setNavBarMenus(List<NavBarMenu> navBarMenus) {
        this.navBarMenus = navBarMenus;
    }

    public void initNavMenus(INavBar iCustomNavBar) {
        // 数据源
        NavBarMenu dataSourceBarMenu = new NavBarMenu(new NavBarMenuItem("m_datasource_menuitem", "数据源"));
        this.navBarMenus.add(dataSourceBarMenu);
        // 用户自定义扩展菜单
        if (iCustomNavBar != null) {
            iCustomNavBar.doInNavBar(this.navBarMenus);
        }
        // 任务调度
        NavBarMenu qzBarMenu = new NavBarMenu(new NavBarMenuItem("m_qz_menuitem", "任务调度"));
        this.navBarMenus.add(qzBarMenu);
        // 组织
        NavBarMenu orgBarMenu = new NavBarMenu(new NavBarMenuItem("m_org_menuitem", "组织"));
        orgBarMenu.append(new NavBarMenuItem("m_org_submenuitem", "组织"));
        orgBarMenu.append(new NavBarMenuItem("m_org_department", "部门"));
        orgBarMenu.append(new NavBarMenuItem("m_org_employee", "人员"));
        this.navBarMenus.add(orgBarMenu);
        // 权限
        NavBarMenu authBarMenu = new NavBarMenu(new NavBarMenuItem("m_perm_menuitem", "权限"));
        authBarMenu.append(new NavBarMenuItem("m_perm_users", "用户"));
        authBarMenu.append(new NavBarMenuItem("m_perm_group", "组"));
        authBarMenu.append(new NavBarMenuItem("m_perm_permission", "权限"));
        this.navBarMenus.add(authBarMenu);
        // 日志
        NavBarMenu logBarMenu = new NavBarMenu(new NavBarMenuItem("m_log_menuitem", "日志"));
        this.navBarMenus.add(logBarMenu);
    }

    public String html(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        for (NavBarMenu menu : this.navBarMenus) {
            NavBarMenuItem top = menu.getTopLevelItem();
            if (menu.getChildren().size() == 0) {
                String target = "";
                if (top.getTarget().equalsIgnoreCase("_blank")) {
                    target = "target='_blank'";
                }
                sb.append("<li class='nav-item'>");
                sb.append(String.format("<a class='nav-link gopandas-link' data-target='%s' data-sidebar_width='%d' id='%s' href='%s' %s>%s</a>", top.getTarget(), top.getSideBarWidth(),
                        top.getId(), top.getHref(), target, top.getLabel()));
                sb.append("</li>");
            } else {
                sb.append("<li class='nav-item dropdown'>");
                sb.append("<a class='nav-link dropdown-toggle' href='#' id='navbarDropdown' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>");
                sb.append(top.getLabel());
                sb.append("</a>");
                sb.append("<div class='dropdown-menu' aria-labelledby='navbarDropdown'>");
                for (NavBarMenuItem sub : menu.getChildren()) {
                    String target = "";
                    if (sub.getTarget().equalsIgnoreCase("_blank")) {
                        target = "target='_blank'";
                    }
                    sb.append(String.format("<a class='dropdown-item gopandas-link' data-target='%s' data-sidebar_width='%d' id='%s' href='%s' %s>%s</a>", top.getTarget(), top.getSideBarWidth(),
                            sub.getId(), sub.getHref(), target, sub.getLabel()));
                }
                sb.append("</div>");
                sb.append("</li>");
            }
        }
        return sb.toString();
    }
}
