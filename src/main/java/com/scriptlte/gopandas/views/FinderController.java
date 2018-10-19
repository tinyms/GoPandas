package com.scriptlte.gopandas.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FinderController {
    @RequestMapping("/finder")
    public String finder(){
        return "finder";
    }
}
