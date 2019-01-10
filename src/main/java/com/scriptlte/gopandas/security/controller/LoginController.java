package com.scriptlte.gopandas.security.controller;

import com.scriptlte.gopandas.security.service.OrgRoleService;
import com.scriptlte.gopandas.security.service.OrgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    @Autowired
    private OrgUserService orgUserService;
    @Autowired
    private OrgRoleService orgRoleService;

    

}
