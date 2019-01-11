package com.scriptlte.gopandas.security.controller;

import com.scriptlte.gopandas.security.service.OrgRoleService;
import com.scriptlte.gopandas.security.service.OrgUserService;
import com.scriptlte.gopandas.vo.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.UniqueConstraint;

@Controller
public class LoginController {

    @Autowired
    private OrgUserService orgUserService;
    @Autowired
    private OrgRoleService orgRoleService;

}
