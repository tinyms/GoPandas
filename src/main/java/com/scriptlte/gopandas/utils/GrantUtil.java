package com.scriptlte.gopandas.utils;

import com.scriptlte.gopandas.modules.security.pojo.grant.OrgGrant;
import com.scriptlte.gopandas.modules.security.service.OrgGrantService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.Collection;

public class GrantUtil {
    /**
     * 注册或更新权限对象
     * @param grantType 权限类型
     * @param grantCode 权限编号(唯一ID)
     * @param grantName 权限名称
     * @throws IllegalArgumentException grantType 为 null 或 ""
     *                                  grantCode 为 null 或 ""
     *                                  grantName 为 null 或 ""
     */
    public static void regesiterOrgGrant(String grantType,String grantCode,String grantName){
        Assert.isTrue(StringUtils.isNotBlank(grantType),"注册权限点，权限类型不能为空！" );
        Assert.isTrue(StringUtils.isNotBlank(grantCode),"注册权限点，权限编号不能为空！" );
        Assert.isTrue(StringUtils.isNotBlank(grantName),"注册权限点，权限名称不能为空！" );
        OrgGrant orgGrant = new OrgGrant();
        orgGrant.setGrantCode(grantCode)
                .setGrantName(grantName)
                .setGrantType(grantType);
        OrgGrantService.save(orgGrant);
    }

    /**
     * 注册或更新一个权限对象
     * @param orgGrant 权限对象
     * @throws IllegalArgumentException   orgGrant 为 null
     *                                    grantType 为 null 或 ""
     *                                    grantCode 为 null 或 ""
     *                                    grantName 为 null 或 ""
     */
    public static void  regesiterOrgGrant(OrgGrant orgGrant){
        Assert.notNull(orgGrant,"注册权限点，权限对象不能为null！" );
        Assert.isTrue(StringUtils.isNotBlank(orgGrant.getGrantType()),"注册权限点，权限类型不能为空！" );
        Assert.isTrue(StringUtils.isNotBlank(orgGrant.getGrantCode()),"注册权限点，权限编号不能为空！" );
        Assert.isTrue(StringUtils.isNotBlank(orgGrant.getGrantName()),"注册权限点，权限名称不能为空！" );
        OrgGrantService.save(orgGrant);
    }

    public static void  regesiterOrgGrant(Collection<OrgGrant> orgGrantS){
        for (OrgGrant orgGrant : orgGrantS) {
            regesiterOrgGrant(orgGrant);
        }
    }
}
