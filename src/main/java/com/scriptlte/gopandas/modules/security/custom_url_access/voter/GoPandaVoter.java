package com.scriptlte.gopandas.modules.security.custom_url_access.voter;

import com.scriptlte.gopandas.modules.security.custom_url_access.CustomUrlAccessConfig;
import com.scriptlte.gopandas.modules.security.custom_url_access.pojo.UrlAccessConfigEntity;
import com.scriptlte.gopandas.modules.security.pojo.grant.OrgGrant;
import com.scriptlte.gopandas.modules.security.pojo.role.OrgRole;
import com.scriptlte.gopandas.modules.security.pojo.user.OrgUser;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;

import java.util.*;

/**
 * 自定义的Voter
 */
public class GoPandaVoter implements AccessDecisionVoter<Object> {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        if(authentication == null) {
            return ACCESS_DENIED;
        }
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();

        int result = ACCESS_ABSTAIN;
        //根据url获取所有自定义的url访问配置
        List<String> needGrantCodes = getUrlAccessConfig(url);
        //根据获取到的访问配置和用户对象分析是否有权限访问
        result = analyzerAccessConfig(needGrantCodes,authentication);
        return result;
    }

    private int analyzerAccessConfig(List<String> needGrantCodes, Authentication authentication) {
        OrgUser user = (OrgUser) authentication.getPrincipal();
        Collection<OrgGrant> userGrants = user.getGrants();
        HashSet<String> userGrantCodes = new HashSet<>();
        //将用户具备的所有权限取出来转换为set字符串
        for (OrgGrant grant : userGrants) {
            userGrantCodes.add(grant.getGrantCode());
        }
        //如果配置项为空 则返回弃权
        if (needGrantCodes.isEmpty()){
            return ACCESS_ABSTAIN;
       }
        if (userGrantCodes.containsAll(needGrantCodes)){
            //必须包括所有所需权限点才通过
            return ACCESS_GRANTED;
        }
        //访问拒绝
        return ACCESS_DENIED;
    }

    Collection<? extends GrantedAuthority> extractAuthorities(
            Authentication authentication) {
        return authentication.getAuthorities();
    }

    @Override
    public boolean supports(Class clazz) {
        return true;
    }

    /**
     * 根据当前访问的url匹配数据库中定义的url访问配置，如果查出多条访问配置，则需要同时满足所有权限
     * @param url 当前访问的url
     * @return
     */
    public List<String> getUrlAccessConfig(String url){
        //从数据库中获取所有的自定义URL访问配置对象
        List<UrlAccessConfigEntity> allAccessConfigs = CustomUrlAccessConfig.getAllAccessConfigs();
        //跟当前路径匹配的url权限设定,如果同一个url对应了多个配置，则将多个配置都取出来，必须满足所有配置才能通过
        List<String> needGrantCodes = new ArrayList<>();

        for (UrlAccessConfigEntity oneAccessConfig : allAccessConfigs) {
            if(antPathMatcher.match(oneAccessConfig.getAntUrlPattern(), url)){
                needGrantCodes.add(oneAccessConfig.getGrantCode());
            }
        }
        return needGrantCodes;
    }
}