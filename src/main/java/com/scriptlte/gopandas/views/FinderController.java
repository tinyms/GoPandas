package com.scriptlte.gopandas.views;

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
        String a = this.env.getProperty("spring.freemarker.template-loader-path");
        return "finder";
    }
}
