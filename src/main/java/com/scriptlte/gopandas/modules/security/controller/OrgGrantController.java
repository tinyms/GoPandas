package com.scriptlte.gopandas.modules.security.controller;

import com.scriptlte.gopandas.modules.security.pojo.grant.OrgGrant;
import com.scriptlte.gopandas.modules.security.service.OrgGrantService;
import com.scriptlte.gopandas.vo.ResponseMessage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@ResponseBody
@RequestMapping("/gp")
public class OrgGrantController {
    @Autowired
    private OrgGrantService orgGrantService;

    @ApiOperation(value = "获取所有权限")
    @RequestMapping(value ="/grant/all",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getAllGrants(){
        List<OrgGrant> grantList = orgGrantService.getAllGrants();
        if(grantList.size()>0){
            return ResponseMessage.GetSuccessMessage(grantList);
        }else {
            return ResponseMessage.GetErrorMessage("暂无权限数据！");
        }
    }

    @ApiOperation(value = "根据权限code获取权限信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grantCode", value = "权限code", paramType = "query", required = true)
    })
    @RequestMapping(value ="/grant/by_grantcode",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getGrantByGrantCode(@RequestParam String grantCode){
        if(StringUtils.isBlank(grantCode)){
            return ResponseMessage.GetErrorMessage("权限code不能为空！");
        }
        OrgGrant grant = orgGrantService.getGrantByGrantCode(grantCode);
        if(grant!=null){
            return ResponseMessage.GetSuccessMessage(grant);
        }else {
            return ResponseMessage.GetErrorMessage("不存在该权限！");
        }
    }

    @ApiOperation(value = "根据权限codes获取权限信息",notes = "参数格式:{grantCodes:'xx,xx,xx,xx'}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grantCodes", value = "权限codes", paramType = "query", required = true)
    })
    @RequestMapping(value ="/grant/by_grantcodes",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getGrantsByGrantCodes(@RequestParam @RequestBody String grantCodes){
        if(StringUtils.isBlank(grantCodes)){
            return ResponseMessage.GetErrorMessage("权限code不能为空！");
        }
        String[] arr = grantCodes.split(",");
        Set<String> grantSet = new HashSet<>();
        for (int i=0;arr.length > i;i++){
            grantSet.add(arr[i]);
        }
        List<OrgGrant> grantList = orgGrantService.getGrantsByCodes(grantSet);
        if(grantList.size()>0){
            return ResponseMessage.GetSuccessMessage(grantList);
        }else {
            return ResponseMessage.GetErrorMessage("暂无权限数据！");
        }
    }

    @ApiOperation(value = "根据权限名称获取权限信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grantName", value = "权限名称", paramType = "query", required = true)
    })
    @RequestMapping(value ="/grant/by_grantname",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getGrantByGrantName(@RequestParam String grantName){
        if(StringUtils.isBlank(grantName)){
            return ResponseMessage.GetErrorMessage("权限名称不能为空！");
        }
        List<OrgGrant> grantList = orgGrantService.getGrantByGrantName(grantName);
        if(grantList.size()>0){
            return ResponseMessage.GetSuccessMessage(grantList);
        }else {
            return ResponseMessage.GetErrorMessage("暂无权限数据！");
        }
    }


}
