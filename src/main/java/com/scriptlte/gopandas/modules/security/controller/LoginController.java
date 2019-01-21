package com.scriptlte.gopandas.modules.security.controller;

import com.scriptlte.gopandas.modules.log.annotation.LogRecord;
import com.scriptlte.gopandas.modules.security.custom_url_access.CustomUrlAccessConfig;
import com.scriptlte.gopandas.modules.security.service.OrgRoleService;
import com.scriptlte.gopandas.modules.security.service.OrgUserService;
import com.scriptlte.gopandas.vo.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private OrgUserService orgUserService;
    @Autowired
    private OrgRoleService orgRoleService;


    @ResponseBody
    @RequestMapping("/logi")
    public ResponseMessage test(){
        return ResponseMessage.GetSuccessMessage("访问成功！");
    }
}
