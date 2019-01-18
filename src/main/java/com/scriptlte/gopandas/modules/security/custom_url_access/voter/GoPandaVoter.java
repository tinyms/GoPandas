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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
        //获取用户对象具备的所有权限
//        Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);
/*        for (ConfigAttribute attribute : attributes) {
            if(attribute.getAttribute()==null){
                continue;
            }
            if (this.supports(attribute)) {
                result = ACCESS_DENIED;

                // Attempt to find a matching granted authority
                for (GrantedAuthority authority : authorities) {
                    if (attribute.getAttribute().equals(authority.getAuthority())) {
                        return ACCESS_GRANTED;
                    }
                }
            }
        }*/
        //根据url获取所有自定义的url访问配置
        List<UrlAccessConfigEntity> allAccessConfig = getUrlAccessConfig(url);
        //根据获取到的访问配置和用户对象分析是否有权限访问
//        result = analyzerAccessConfig(allAccessConfig,authentication);
        return result;
    }

    private int analyzerAccessConfig(List<UrlAccessConfigEntity> allAccessConfig, Authentication authentication) {
        OrgUser user = (OrgUser) authentication.getPrincipal();
        Collection<OrgRole> roles = user.getRoles();
        Collection<OrgGrant> grants = user.getGrants();
        //FIXME 2019/1/18 19:17 By:VATE
        for (UrlAccessConfigEntity urlAccessConfigEntity : allAccessConfig) {
            String authoritys = urlAccessConfigEntity.getAuthorityExpress();
            for (OrgGrant grant : grants) {

            }
            for (OrgRole role : roles) {

            }
        }
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

    public List<UrlAccessConfigEntity> getUrlAccessConfig(String url){
        //从数据库中获取所有的自定义URL访问配置对象
        List<UrlAccessConfigEntity> allAccessConfigs = CustomUrlAccessConfig.getAllAccessConfigs();
        //跟当前路径匹配的url权限设定,如果同一个url对应了多个配置，则将多个配置都取出来，必须满足所有配置才能通过
        List< UrlAccessConfigEntity > matchAccessConfigs = new ArrayList<>();

        for (UrlAccessConfigEntity oneAccessConfig : allAccessConfigs) {
            if(antPathMatcher.match(oneAccessConfig.getAntUrlPattern(), url)){
                matchAccessConfigs.add(oneAccessConfig);
            }
        }
        return matchAccessConfigs;
    }
}