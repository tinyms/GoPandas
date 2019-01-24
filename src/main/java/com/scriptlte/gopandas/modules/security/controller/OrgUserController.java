package com.scriptlte.gopandas.modules.security.controller;

import com.scriptlte.gopandas.modules.security.config.pwencoder.Md5PasswordEncoder;
import com.scriptlte.gopandas.modules.security.pojo.user.OrgUser;
import com.scriptlte.gopandas.modules.security.service.OrgEmployeeService;
import com.scriptlte.gopandas.modules.security.service.OrgUserService;
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
@Api(tags = "用户信息API")
public class OrgUserController {
    @Autowired
    private OrgUserService orgUserService;
    @Autowired
    private OrgEmployeeService orgEmployeeService;

    @ApiOperation(value = "获取所有用户信息")
    @RequestMapping(value ="/user/all",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getAllUsers(){
        List<OrgUser> userList = orgUserService.getAllUsers();
        if(userList.size()>0){
            return ResponseMessage.GetSuccessMessage(userList);
        }else {
            return ResponseMessage.GetErrorMessage("暂无用户数据！");
        }
    }

    @ApiOperation(value = "根据id获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", paramType = "query", required = true)
    })
    @RequestMapping(value ="/user/by_id",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getUserById(@RequestParam String id){
        if(StringUtils.isBlank(id)){
            return ResponseMessage.GetErrorMessage("用户id不能为空！");
        }
        OrgUser user = orgUserService.getUserById(id);
        if(user!=null){
            return ResponseMessage.GetSuccessMessage(user);
        }else {
            return ResponseMessage.GetErrorMessage("不存在该用户！");
        }
    }

    @ApiOperation(value = "根据用户名获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", paramType = "query", required = true)
    })
    @RequestMapping(value ="/user/by_username",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getUserByUserName(@RequestParam String userName){
        if(StringUtils.isBlank(userName)){
            return ResponseMessage.GetErrorMessage("用户名不能为空！");
        }
        OrgUser user = orgUserService.getUserByUserName(userName);
        if(user!=null){
            return ResponseMessage.GetSuccessMessage(user);
        }else {
            return ResponseMessage.GetErrorMessage("不存在该用户！");
        }
    }

    @ApiOperation(value = "根据员工code获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeCode", value = "员工code", paramType = "query", required = true)
    })
    @RequestMapping(value ="/user/by_employeecode",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage getUserByEmployeeCode(@RequestParam String employeeCode){
        if(StringUtils.isBlank(employeeCode)){
            return ResponseMessage.GetErrorMessage("员工code不能为空！");
        }
        OrgUser user = orgUserService.getUserByEmployeeCode(employeeCode);
        if(user!=null){
            return ResponseMessage.GetSuccessMessage(user);
        }else {
            return ResponseMessage.GetErrorMessage("该用户并未绑定员工！");
        }
    }

    @ApiOperation(value = "新增一个用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeCode", value = "员工code", paramType = "query", required = true),
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query", required = true),
            @ApiImplicitParam(name = "nickname", value = "昵称", paramType = "query", required = true)

    })
    @RequestMapping(value ="/user/save",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage save(@RequestParam String employeeCode,@RequestParam String username,@RequestParam String password,@RequestParam String status,@RequestParam(required = false,defaultValue = "") String nickname){
        if(StringUtils.isBlank(username)){
            return ResponseMessage.GetErrorMessage("用户名不能为空!");
        }
        if(orgUserService.getUserByUserName(username)!=null){
            return ResponseMessage.GetErrorMessage("该用户名已被占用！");
        }
        OrgUser u = new OrgUser(employeeCode,username,Md5PasswordEncoder.getInstance().encode(password),status,nickname);
        OrgUser user = orgUserService.saveOrUpdate(u);
        if(user!=null){
            return ResponseMessage.GetSuccessMessage("新增一个用户信息成功!");
        }else {
            return ResponseMessage.GetErrorMessage("新增一个用户信息失败!");
        }
    }

    @ApiOperation(value = "更新一个用户信息",notes = "前端传过来的密码为加密后的密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", paramType = "query", required = true),
            @ApiImplicitParam(name = "employeeCode", value = "员工code", paramType = "query", required = true),
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query", required = true),
            @ApiImplicitParam(name = "nickname", value = "昵称", paramType = "query", required = true)

    })
    @RequestMapping(value ="/user/update_unchgpwd",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage update1(@RequestParam String id,@RequestParam String employeeCode,@RequestParam String username,@RequestParam String password,@RequestParam String status,@RequestParam String nickname){
        if(StringUtils.isBlank(id)){
            return ResponseMessage.GetErrorMessage("id不能为空！");
        }
        //此处员工code不为空时，若查询该员工code不存在
        if(StringUtils.isNotBlank(employeeCode)&&orgEmployeeService.getEmployeByCode(employeeCode)==null){
            return ResponseMessage.GetErrorMessage("员工code不存在！");
        }
        OrgUser u = new OrgUser(id,employeeCode,username,password,status,nickname);
        OrgUser user = orgUserService.saveOrUpdate(u);
        if(user!=null){
            return ResponseMessage.GetSuccessMessage("更新一个用户信息成功!");
        }else {
            return ResponseMessage.GetErrorMessage("更新一个用户信息失败!");
        }
    }

    @ApiOperation(value = "更新一个用户信息",notes = "前端传过来的密码为未加密的密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", paramType = "query", required = true),
            @ApiImplicitParam(name = "employeeCode", value = "员工code", paramType = "query", required = true),
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query", required = true),
            @ApiImplicitParam(name = "nickname", value = "昵称", paramType = "query", required = true)

    })
    @RequestMapping(value ="/user/update_chgpwd",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage update2(@RequestParam String id,@RequestParam String employeeCode,@RequestParam String username,@RequestParam String password,@RequestParam String status,@RequestParam String nickname){
        if(StringUtils.isBlank(id)){
            return ResponseMessage.GetErrorMessage("id不能为空！");
        }
        //此处员工code不为空时，若查询该员工code不存在
        if(StringUtils.isNotBlank(employeeCode)&&orgEmployeeService.getEmployeByCode(employeeCode)==null){
            return ResponseMessage.GetErrorMessage("员工code不存在！");
        }
        OrgUser u = new OrgUser(id,employeeCode,username,Md5PasswordEncoder.getInstance().encode(password),status,nickname);
        OrgUser user = orgUserService.saveOrUpdate(u);
        if(user!=null){
            return ResponseMessage.GetSuccessMessage("更新一个用户信息成功!");
        }else {
            return ResponseMessage.GetErrorMessage("更新一个用户信息失败!");
        }
    }



    @ApiOperation(value = "根据id删除某个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", paramType = "query", required = true)
    })
    @RequestMapping(value ="/user/delete/by_id",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage deleteUserById(@RequestParam String id){
        if(StringUtils.isBlank(id)){
            return ResponseMessage.GetErrorMessage("用户id不能为空！");
        }
        OrgUser u = orgUserService.deleteOrgUserById(id);
        if(u!=null){
            return ResponseMessage.GetSuccessMessage("删除一个用户信息成功!");
        }else {
            return ResponseMessage.GetErrorMessage("删除一个用户信息失败!");
        }
    }

    @ApiOperation(value = "根据用户名删除某个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", paramType = "query", required = true)
    })
    @RequestMapping(value ="/user/delete/by_username",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseMessage deleteUserByUserName(@RequestParam String userName){
        if(StringUtils.isBlank(userName)){
            return ResponseMessage.GetErrorMessage("用户名不能为空！");
        }
        OrgUser u = orgUserService.deleteOrgUserByUsername(userName);
        if(u!=null){
            return ResponseMessage.GetSuccessMessage("删除一个用户信息成功!");
        }else {
            return ResponseMessage.GetErrorMessage("删除一个用户信息失败!");
        }
    }

}
