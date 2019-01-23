package com.scriptlte.gopandas.modules.security.controller;

import com.scriptlte.gopandas.modules.security.config.SecurityConstant;
import com.scriptlte.gopandas.modules.security.custom_url_access.pojo.UrlAccessConfigEntity;
import com.scriptlte.gopandas.modules.security.dao.grant.OrgGrantRepository;
import com.scriptlte.gopandas.modules.security.dao.relation.OrgRel_X_GrantRepository;
import com.scriptlte.gopandas.modules.security.dao.relation.OrgRel_X_RoleRepository;
import com.scriptlte.gopandas.modules.security.dao.role.OrgRoleRepository;
import com.scriptlte.gopandas.modules.security.dao.user.OrgUserRepository;
import com.scriptlte.gopandas.modules.security.pojo.relation.OrgRel_X_Grant;
import com.scriptlte.gopandas.modules.security.pojo.user.OrgUser;
import com.scriptlte.gopandas.modules.security.service.OrgRoleService;
import com.scriptlte.gopandas.modules.security.service.OrgUserService;
import com.scriptlte.gopandas.utils.UrlAccessConfigUtil;
import com.scriptlte.gopandas.vo.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @Autowired
    OrgUserService orgUserService;
    @Autowired
    OrgRoleService orgRoleService;
    @Autowired
    OrgUserRepository orgUserRepository;
    @Autowired
    OrgRoleRepository orgRoleRepository;
    @Autowired
    OrgGrantRepository orgGrantRepository;
    @Autowired
    OrgRel_X_GrantRepository orgRel_x_grantRepository;
    @Autowired
    OrgRel_X_RoleRepository orgRel_x_roleRepository;

    @ResponseBody
    @RequestMapping("/user/username/{username}")
    public ResponseMessage test_getUserbyName(@PathVariable(name = "username") String username){
        try {
            OrgUser orgUser = (OrgUser) orgUserService.loadUserByUsername(username);
//            orgRel_x_grantRepository.save(new OrgRel_X_Grant(orgUser.getId(), "testGrant1", SecurityConstant.OBJECT_TYPE_USER));
            return ResponseMessage.GetSuccessMessage(orgUser);
        }
        catch (Throwable t){
           return ResponseMessage.GetErrorMessage("用户名查找失败。");
        }
    }

    @ResponseBody
    @RequestMapping("/user/username/simple/{username}")
    public ResponseMessage test_getUserByName_simple(@PathVariable(name = "username") String username){
        OrgUser orgUser = (OrgUser) orgUserService.getUserByUserName(username);
        return ResponseMessage.GetSuccessMessage(orgUserService);
    }

    @ResponseBody
    @RequestMapping("/addurlconf")
    public ResponseMessage test_addUrlAccessConfig(String urlConfig,String grantCode){
        UrlAccessConfigUtil.regesiterUrlAccessConfig(new UrlAccessConfigEntity(urlConfig,grantCode ));
        return ResponseMessage.GetSuccessMessage("操作成功！");
    }
}
