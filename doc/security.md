# 安全模块

## 安全框架概述
本系统安全模块采用`SpringSecurity`框架实现包括`Authentication`(认证)和`Authenrization`(授权).
### 认证实现
在`SpringSecurity`的基础上自定义了其`UserDetails`接口实现，将用户登录时获取用户信息的逻辑换成了我们的逻辑，
在登录时根据用户名从数据库中获取到该用户对应的对象，以及该用户相关信息，并且一并存入`SpringContext`中(实际则是Session);

###  授权实现
在用户登录时，SpringSecurity中的认证拦截器，会调用`UserDetailsService`接口的`loadUserByUsername`方法来查询登录用户的信息；   
当查询到用户后，我们会根据用户对象去获取他所关联的：**员工**、**员工直接关联的所有角色**、**员工所属部门**、**部门直接关联的所有角色**、**用户直接关联的所有角色**；  
然后根据所关联的这些对象分别查询**对象-权限关联表**，获取所有的继承权限点；  
然后再根据用户对象，查询**授权表**，获取所有通过授权得到的权限点。  

## <a name="用户和权限">用户和权限</a>
### 用户
- 用户关联权限
- 用户关联角色
- 用户可以将自己的权限授予其他对象(只有当该用户的auth)

### 角色
- 角色关联权限 	

### 权限
#### a.权限点创建
- 权限编码
- 权限类型，权限类型主要用于展示。权限类型的值的规范为:```用户资料管理/基本资料/``` 路径式风格。

#### b.权限点注册
- [未完成] 系统提供权限注册接口 GrantUtil 将自定义的权限点注册进系统.
- 注：此处是权限点注册而非具体对某个资源的权限限制配置，资源访问限制请参考：<a href="#访问控制">访问控制</a>

## <a name="组织机构管理">组织机构管理</a>
### 部门管理
- 部门可以绑定角色或权限
- 在本系统中，公司(组织)作为一种特殊的部门存在，公司(组织)和部门的区分通过deptType字段来确定

### 员工管理
- 员工可以绑定角色或权限

## <a name="访问控制">访问控制</a>
### 访问失败
- 无权访问的统一返回格式为Json格式

### 方法访问限制
- 对于方法级别的限制，直接为该方法添加注解(详情参考SpringSecurity官方的定义方式)`@PreAuthorize("hasRole('xxx') and hasAuthrority('xxx')")`即可

### url访问限制
- url的限制，可以动态配置url访问所需的权限
- 实现方案：[SpringSecurity动态配置URL权限](https://www.cnblogs.com/xiaoqi/p/spring-security-rabc.html)