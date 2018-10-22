package com.scriptlte.gopandas.views;

import com.scriptlte.gopandas.plugins.IContextMenu;
import com.scriptlte.gopandas.plugins.INavBar;
import com.scriptlte.gopandas.plugins.Plugin;
import com.scriptlte.gopandas.ui.ContextMenuItem;
import com.scriptlte.gopandas.ui.NavBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FinderController {
    @Autowired
    private Environment env;

    @RequestMapping("/finder")
    public String finder(HttpServletRequest request) {
        // NavBar 构造及插件化
        INavBar iNavBar = Plugin.load("gopandas.ui.navbar", this.env);
        NavBar navBar = new NavBar();
        navBar.initNavMenus(iNavBar);
        request.setAttribute("_NavBar", navBar.html(request));
        // 右键菜单 构造及插件化
        List<ContextMenuItem> contextMenuItemList = new ArrayList<>();
        IContextMenu iContextMenu = Plugin.load("gopandas.ui.contextmenu", this.env);
        if (iContextMenu != null) {
            iContextMenu.doInContextMenu(contextMenuItemList);
        }
        request.setAttribute("_ContextMenu", contextMenuItemList);
        return "finder";
    }
}
