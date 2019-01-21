package com.scriptlte.gopandas.utils;

import com.scriptlte.gopandas.modules.security.custom_url_access.CustomUrlAccessConfig;
import com.scriptlte.gopandas.modules.security.custom_url_access.UrlAccessService;
import com.scriptlte.gopandas.modules.security.custom_url_access.pojo.UrlAccessConfigEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.*;

public class UrlAccessConfigUtil {
    /**
     * 注册或者更新一个url访问配置项
     * @param urlAccessConfigEntity url访问配置实体对象
     */
    public static void regesiterUrlAccessConfig(UrlAccessConfigEntity urlAccessConfigEntity){
        Assert.notNull(urlAccessConfigEntity, "注册url访问配置,url访问配置对象不能为null!");
        Assert.isTrue(StringUtils.isNotBlank(urlAccessConfigEntity.getAntUrlPattern()),"注册url访问配置，url不能为空！" );
        Assert.isTrue(StringUtils.isNotBlank(urlAccessConfigEntity.getGrantCode()),"注册url访问配置，权限编号不能为空！" );
        //更新到数据库
        urlAccessConfigEntity = UrlAccessService.save(urlAccessConfigEntity);
        //更新到缓存
        updateCache(Arrays.asList(urlAccessConfigEntity));
    }

    /**
     * 从数据库中查询所有的url访问配置,只在项目启动初始化的时候使用，其他时候请使用 CustomUrlAccessConfig.getAllUrlAccessConfig类获取
     * @return 所有的url访问配置
     */
    public static List<UrlAccessConfigEntity> getAllAccessConfig(){
        return UrlAccessService.getAll();
    }

    /**
     * 将url访问配置对象更新到缓存
     * @param urlAccessConfigEntities
     */
    public static void updateCache(List<UrlAccessConfigEntity> urlAccessConfigEntities){
        HashMap<String,UrlAccessConfigEntity> allAccssConfigs = CustomUrlAccessConfig.getAllAccessConfigs();
        for (UrlAccessConfigEntity urlAccessConfigEntity : urlAccessConfigEntities) {
            allAccssConfigs.put(urlAccessConfigEntity.getAntUrlPattern(), urlAccessConfigEntity);
        }
    }
}
