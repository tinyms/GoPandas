package com.scriptlte.gopandas.modules.security.controller;

import com.scriptlte.gopandas.modules.security.pojo.role.OrgRole;
import com.scriptlte.gopandas.modules.security.service.OrgRoleService;
import com.scriptlte.gopandas.vo.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@ResponseBody
@RequestMapping("/gp")
@Api(tags = "角色信息API")
public class OrgRoleController {
    @Autowired
    private OrgRoleService orgRoleService;

    @ApiOperation(value = "获取所有角色")
    @RequestMapping(value ="/role/all",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getAllRoles(){
        List<OrgRole> roleList = orgRoleService.getAllRoles();
        if(roleList.size()>0){
            return ResponseMessage.GetSuccessMessage(roleList);
        }else {
            return ResponseMessage.GetErrorMessage("暂无角色数据！");
        }
    }

    @ApiOperation(value = "根据id获取角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", paramType = "query", required = true)
    })
    @RequestMapping(value ="/role/by_id",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getRoleById(@RequestParam String id){
        if(StringUtils.isBlank(id)){
            return ResponseMessage.GetErrorMessage("角色id不能为空！");
        }
        OrgRole role = orgRoleService.getRoleById(id);
        if(role!=null){
            return ResponseMessage.GetSuccessMessage(role);
        }else {
            return ResponseMessage.GetErrorMessage("不存在该角色！");
        }
    }

    @ApiOperation(value = "根据id获取角色信息",notes = "参数格式:{roleIds:'xx,xx,xx,xx'}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleIds", value = "角色id", paramType = "query", required = true)
    })
    @RequestMapping(value ="/role/by_ids",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getRolesByIds(@RequestParam String roleIds){
        if(StringUtils.isBlank(roleIds)){
            return ResponseMessage.GetErrorMessage("角色id不能为空！");
        }
        String[] arr = roleIds.split(",");
        Set<String> roleSet = new HashSet<>();
        for (int i=0;arr.length > i;i++){
            roleSet.add(arr[i]);
        }
        List<OrgRole> roleList = orgRoleService.getRolesByIds(roleSet);
        if(roleList.size()>0){
            return ResponseMessage.GetSuccessMessage(roleList);
        }else {
            return ResponseMessage.GetErrorMessage("暂无角色数据！");
        }
    }

    @ApiOperation(value = "根据角色名称获取角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名称", paramType = "query", required = true)
    })
    @RequestMapping(value ="/role/by_rolename",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getRoleByRoleName(@RequestParam String roleName){
        if(StringUtils.isBlank(roleName)){
            return ResponseMessage.GetErrorMessage("角色名称不能为空！");
        }
        OrgRole role = orgRoleService.getRoleByName(roleName);
        if(role!=null){
            return ResponseMessage.GetSuccessMessage(role);
        }else {
            return ResponseMessage.GetErrorMessage("不存在该角色！");
        }
    }

    @ApiOperation(value = "新增或更新一条角色数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", paramType = "query", required = false),
            @ApiImplicitParam(name = "roleName", value = "角色code", paramType = "query", required = true),
            @ApiImplicitParam(name = "createrUsername", value = "角色名称", paramType = "query", required = true)
    })
    @RequestMapping(value ="/role/saveorupdate",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage saveOrUpdate(@RequestParam String id,@RequestParam String roleName,@RequestParam(required = false,defaultValue = "")String createrUsername){
        //新增一条角色数据时，判断角色code是否已存在
        if(StringUtils.isBlank(id)){
            if(StringUtils.isBlank(roleName)){
                return ResponseMessage.GetErrorMessage("角色名称不能为空!");
            }
            OrgRole or = orgRoleService.getRoleByName(roleName);
            if(or!=null){
                return ResponseMessage.GetErrorMessage("已存在该角色code！");
            }
        }
        OrgRole r = new OrgRole(id,roleName,createrUsername);
        OrgRole role = orgRoleService.save(r);
        if(role!=null){
            return ResponseMessage.GetSuccessMessage("新增或更新一条角色数据成功!");
        }else {
            return ResponseMessage.GetErrorMessage("新增或更新一条角色数据失败！");
        }
    }
}
