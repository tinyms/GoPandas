package com.scriptlte.gopandas.modules.security.config;

/**
 * 安全模块常量类
 */
public class SecurityConstant {

    /**
     * 所有角色的名字在内存中最终都要加上这个前缀
     */
    public static final String ROLE_PREFIX = "ROLE_";



    /*用户对象相关常量 start -------*/

    /**
     * 用户可用
     */
    public static final String USER_STATUS_ENABLE = "0";
    /**
     * 用户不可用
     */
    public static final String USER_STATUS_DISABLE = "-1";

    /*用户对象相关常量 end -------*/



    /*部门对象相关常量 start -------*/

    /**
     * 部门类型 ： 公司
     */
    public static final String DEPT_TYPE_COMPANY = "company";
    /**
     * 部门类型 ： 部门
     */
    public static final String DEPT_TYPE_DEPARTMENT = "department";

    /**
     * 部门层级: 该值作为部门对象的 'pId' 字段，代表该部门/组织 为 根组织/根部门
     */
    public static final String DEPT_HIERARCHY_ROOT = "root";

    /*部门对象相关常量 end -------*/



    /*关联关系定义常量 start-------*/

    /**
     * 关联对象类型:角色
     */
    public static final String OBJECT_TYPE_ROLE = "role_";
    /**
     * 关联对象类型:用户
     */
    public static final String OBJECT_TYPE_USER = "user_";
    /**
     * 关联对象类型:部门
     */
    public static final String OBJECT_TYPE_DEPT = "dept_";
    /**
     * 关联对象类型:员工
     */
    public static final String OBJECT_TYPE_EMPOLYEE = "empolyee_";

    /*关联关系定义常量 end-------*/

    /*权限对象常量定义 start-------*/

    /**
     * 权限所属类型: 默认
     */
    public static final String GRANT_TYPE_DEFAULT = "grant_default";

    /*权限对象常量定义 end-------*/

}
