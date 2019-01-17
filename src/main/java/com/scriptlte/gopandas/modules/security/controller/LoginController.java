package com.scriptlte.gopandas.modules.security.controller;

import com.scriptlte.gopandas.modules.security.service.OrgRoleService;
import com.scriptlte.gopandas.modules.security.service.OrgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private OrgUserService orgUserService;
    @Autowired
    private OrgRoleService orgRoleService;

    @RequestMapping("/test")
    public String test(){
        return "redirect:/test";
    }
}
