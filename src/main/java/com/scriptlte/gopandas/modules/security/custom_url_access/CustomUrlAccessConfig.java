package com.scriptlte.gopandas.modules.security.custom_url_access;

import com.scriptlte.gopandas.modules.security.custom_url_access.pojo.UrlAccessConfigEntity;

import java.util.ArrayList;
import java.util.List;

public class CustomUrlAccessConfig {

    /**
     * 包含所有自定义的Url访问配置对象
     */
    private static List<UrlAccessConfigEntity> allAccessConfigs = new ArrayList<>();

    public static List<UrlAccessConfigEntity> getAllAccessConfigs() {
        return CustomUrlAccessConfig.allAccessConfigs;
    }

    public static void setAllAccessConfigs(List<UrlAccessConfigEntity> allAccessConfigs) {
        CustomUrlAccessConfig.allAccessConfigs = allAccessConfigs;
    }
}
