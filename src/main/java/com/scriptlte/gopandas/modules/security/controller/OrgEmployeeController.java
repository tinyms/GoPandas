package com.scriptlte.gopandas.modules.security.controller;

import com.scriptlte.gopandas.modules.security.pojo.employee.OrgEmployee;
import com.scriptlte.gopandas.modules.security.service.OrgEmployeeService;
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

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/gp")
@Api(tags = "组织结构-雇员API")
public class OrgEmployeeController {
    @Autowired
    private OrgEmployeeService orgEmployeeService;

    @ApiOperation(value = "获取所有员工")
    @RequestMapping(value ="/employee/all",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getAllEmployees(){
        List<OrgEmployee> employeeList = orgEmployeeService.getAllEmployees();
        if(employeeList.size()>0){
            return ResponseMessage.GetSuccessMessage(employeeList);
        }else {
            return ResponseMessage.GetErrorMessage("暂无员工数据！");
        }
    }

    @ApiOperation(value = "根据id获取员工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "员工id", paramType = "query", required = true)
    })
    @RequestMapping(value ="/employee/by_id",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getEmployeeById(@RequestParam String id){
        if(StringUtils.isBlank(id)){
            return ResponseMessage.GetErrorMessage("员工id不能为空！");
        }
        OrgEmployee employee = orgEmployeeService.getEmployeeById(id);
        if(employee!=null){
            return ResponseMessage.GetSuccessMessage(employee);
        }else {
            return ResponseMessage.GetErrorMessage("不存在该员工！");
        }
    }

    @ApiOperation(value = "根据员工code获取部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeCode", value = "员工code", paramType = "query", required = true)
    })
    @RequestMapping(value ="/employee/by_employeecode",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getEmployeebyEmployeeCode(@RequestParam String employeeCode){
        if(StringUtils.isBlank(employeeCode)){
            return ResponseMessage.GetErrorMessage("员工code不能为空！");
        }
        OrgEmployee employee = orgEmployeeService.getEmployeByCode(employeeCode);
        if(employee!=null){
            return ResponseMessage.GetSuccessMessage(employee);
        }else {
            return ResponseMessage.GetErrorMessage("不存在该员工！");
        }
    }

    @ApiOperation(value = "根据员工名称获取员工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeName", value = "员工名称", paramType = "query", required = true)
    })
    @RequestMapping(value ="/employee/by_employeename",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getEmployeebyEmployeeName(@RequestParam String employeeName){
        if(StringUtils.isBlank(employeeName)){
            return ResponseMessage.GetErrorMessage("员工名称不能为空！");
        }
        List<OrgEmployee> employeeList = orgEmployeeService.getEmployeebyEmployeeName(employeeName);
        if(employeeList.size()>0){
            return ResponseMessage.GetSuccessMessage(employeeList);
        }else {
            return ResponseMessage.GetErrorMessage("暂无员工数据！");
        }
    }

    @ApiOperation(value = "新增或更新一条员工数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "员工id", paramType = "query", required = false),
            @ApiImplicitParam(name = "employeeCode", value = "员工code", paramType = "query", required = true),
            @ApiImplicitParam(name = "deptCode", value = "部门code", paramType = "query", required = true),
            @ApiImplicitParam(name = "employeeName", value = "员工名称", paramType = "query", required = true),
            @ApiImplicitParam(name = "age", value = "年龄", paramType = "query", required = true),
            @ApiImplicitParam(name = "phoneNumber", value = "电话号码", paramType = "query", required = true),
            @ApiImplicitParam(name = "email", value = "电子邮件", paramType = "query", required = true)
    })
    @RequestMapping(value ="/employee/saveorupdate",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage saveOrUpdate(@RequestParam String id,@RequestParam String employeeCode,@RequestParam String deptCode,@RequestParam(required = false,defaultValue = "") String employeeName,@RequestParam(required = false,defaultValue = "0") String age,@RequestParam(required = false,defaultValue = "") String phoneNumber,@RequestParam(required = false,defaultValue = "") String email){
        //新增一条员工数据时，判断员工code是否已存在
        if(StringUtils.isBlank(id)){
            if(StringUtils.isBlank(employeeCode)){
                return ResponseMessage.GetErrorMessage("员工code不能为空!");
            }
            OrgEmployee oe = orgEmployeeService.getEmployeByCode(employeeCode);
            if(oe!=null){
                return ResponseMessage.GetErrorMessage("已存在该员工code！");
            }
        }
        short employeeAge = Short.valueOf(age);
        OrgEmployee e = new OrgEmployee(id,employeeCode,deptCode,employeeName,employeeAge,phoneNumber,email);
        OrgEmployee employee = orgEmployeeService.save(e);
        if(employee!=null){
            return ResponseMessage.GetSuccessMessage("新增或更新一条员工数据成功!");
        }else {
            return ResponseMessage.GetErrorMessage("新增或更新一条员工数据失败！");
        }
    }
}
