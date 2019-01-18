package com.scriptlte.gopandas.config;

public class DynamicConfig {

    /**
     * 创建用户时用户是否默认具备授权资格 true/false
     */
    private static boolean defaultOpenUserAuthority = true;
    public static boolean getDefaultOpenUserAuthority(){
        return DynamicConfig.defaultOpenUserAuthority;
    }


}
