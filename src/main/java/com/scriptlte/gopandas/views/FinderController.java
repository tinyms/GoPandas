package com.scriptlte.gopandas.views;

import com.scriptlte.gopandas.plugins.INavBar;
import com.scriptlte.gopandas.plugins.Plugin;
import com.scriptlte.gopandas.ui.NavBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FinderController {
    @Autowired
    private Environment env;

    @RequestMapping("/finder")
    public String finder(HttpServletRequest request) {
        INavBar o = Plugin.load("gopandas.ui.navbar", this.env);
        NavBar navBar = new NavBar();
        navBar.initNavMenus(o);
        request.setAttribute("_NavBar", navBar.html(request));
        return "finder";
    }
}
