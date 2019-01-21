package com.scriptlte.gopandas.modules.security.custom_url_access;

import com.scriptlte.gopandas.modules.security.custom_url_access.pojo.UrlAccessConfigEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CustomUrlAccessConfig {

    /**
     * 包含所有自定义的Url访问配置对象
     */
    private final static HashMap<String,UrlAccessConfigEntity> allAccessConfigs = new HashMap<>();

    public static HashMap<String,UrlAccessConfigEntity> getAllAccessConfigs() {
        return CustomUrlAccessConfig.allAccessConfigs;
    }
}
