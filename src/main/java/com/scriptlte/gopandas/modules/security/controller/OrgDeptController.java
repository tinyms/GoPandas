package com.scriptlte.gopandas.modules.security.controller;

import com.scriptlte.gopandas.modules.security.pojo.dept.OrgDept;
import com.scriptlte.gopandas.modules.security.service.OrgDeptService;
import com.scriptlte.gopandas.vo.ResponseMessage;
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

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/gp")
public class OrgDeptController {
    @Autowired
    private OrgDeptService orgDeptService;

    @ApiOperation(value = "获取所有部门")
    @RequestMapping(value ="/alldepts",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getAllDepts(){
        List<OrgDept> deptList = orgDeptService.getAllDepts();
        if(deptList.size()>0){
            return ResponseMessage.GetSuccessMessage(deptList);
        }else {
            return ResponseMessage.GetErrorMessage("暂无部门数据！");
        }
    }

    @ApiOperation(value = "根据id获取部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "部门id", paramType = "query", required = true)
    })
    @RequestMapping(value ="/deptbyid",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getDeptById(@RequestParam String id){
        if(StringUtils.isBlank(id)){
            return ResponseMessage.GetErrorMessage("部门id不能为空！");
        }
        OrgDept dept = orgDeptService.getDeptById(id);
        if(dept!=null){
            return ResponseMessage.GetSuccessMessage(dept);
        }else {
            return ResponseMessage.GetErrorMessage("不存在该部门！");
        }
    }

    @ApiOperation(value = "根据部门code获取部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptCode", value = "部门code", paramType = "query", required = true)
    })
    @RequestMapping(value ="/deptbydeptcode",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getDeptByDeptCode(@RequestParam String deptCode){
        if(StringUtils.isBlank(deptCode)){
            return ResponseMessage.GetErrorMessage("部门code不能为空！");
        }
        OrgDept dept = orgDeptService.getDeptByDeptCode(deptCode);
        if(dept!=null){
            return ResponseMessage.GetSuccessMessage(dept);
        }else {
            return ResponseMessage.GetErrorMessage("不存在该部门！");
        }
    }

    @ApiOperation(value = "根据部门名称获取部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptName", value = "部门名称", paramType = "query", required = true)
    })
    @RequestMapping(value ="/deptbydeptname",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getDeptByDeptName(@RequestParam String deptName){
        if(StringUtils.isBlank(deptName)){
            return ResponseMessage.GetErrorMessage("部门名称不能为空！");
        }
        List<OrgDept> deptList = orgDeptService.getDeptByDeptName(deptName);
        if(deptList.size()>0){
            return ResponseMessage.GetSuccessMessage(deptList);
        }else {
            return ResponseMessage.GetErrorMessage("暂无部门数据！");
        }
    }

    @ApiOperation(value = "新增或更新一条部门数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "部门id", paramType = "query", required = false),
            @ApiImplicitParam(name = "deptCode", value = "部门code", paramType = "query", required = true),
            @ApiImplicitParam(name = "deptName", value = "部门名称", paramType = "query", required = true),
            @ApiImplicitParam(name = "deptType", value = "部门类型", paramType = "query", required = true),
            @ApiImplicitParam(name = "parentCode", value = "上级部门code", paramType = "query", required = true)
    })
    @RequestMapping(value ="/saveorupdatedept",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage saveOrUpdate(@RequestParam String id,@RequestParam String deptCode,@RequestParam(required = false,defaultValue = "") String deptName,@RequestParam String deptType,@RequestParam(required = false,defaultValue = "") String parentCode){
        //新增一条部门数据时，判断部门code是否已存在
        if(StringUtils.isBlank(id)){
            if(StringUtils.isBlank(deptCode)){
                return ResponseMessage.GetErrorMessage("部门code不能为空!");
            }
            OrgDept od = orgDeptService.getDeptByDeptCode(deptCode);
            if(od!=null){
                return ResponseMessage.GetErrorMessage("已存在该部门code！");
            }
        }
        OrgDept d = new OrgDept(id,deptCode,deptName,deptType,parentCode);
        OrgDept dept = orgDeptService.save(d);
        if(dept!=null){
            return ResponseMessage.GetSuccessMessage("新增或更新一条部门数据成功!");
        }else {
            return ResponseMessage.GetErrorMessage("新增或更新一条部门数据失败！");
        }
    }
}
