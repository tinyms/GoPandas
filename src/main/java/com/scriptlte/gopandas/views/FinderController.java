package com.scriptlte.gopandas.views;

import com.scriptlte.gopandas.plugins.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FinderController {
    @Autowired
    private Environment env;

    @RequestMapping("/finder")
    public String finder() {
        Object o = Plugin.load("gopandas.ui.navbar", this.env);
        return "finder";
    }
}
