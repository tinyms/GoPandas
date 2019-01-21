package com.scriptlte.gopandas.modules.security.custom_url_access.voter;

import com.scriptlte.gopandas.modules.security.custom_url_access.CustomUrlAccessConfig;
import com.scriptlte.gopandas.modules.security.custom_url_access.pojo.UrlAccessConfigEntity;
import com.scriptlte.gopandas.modules.security.pojo.grant.OrgGrant;
import com.scriptlte.gopandas.modules.security.pojo.role.OrgRole;
import com.scriptlte.gopandas.modules.security.pojo.user.OrgUser;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.ELState;
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
        //如果配置项为空 则返回弃权
        if (needGrantCodes.isEmpty()){
            return ACCESS_ABSTAIN;
        }
        OrgUser user = null;
        if (authentication.getPrincipal() instanceof OrgUser) {
             user = (OrgUser) authentication.getPrincipal();
        }
        else {
            //如果不是OrgUser对象，且对该url配置了权限访问限制，则直接拒绝访问
            return ACCESS_DENIED;
        }
        Collection<OrgGrant> userGrants = user.getGrants();
        HashSet<String> userGrantCodes = new HashSet<>();
        //将用户具备的所有权限取出来转换为set字符串
        for (OrgGrant grant : userGrants) {
            userGrantCodes.add(grant.getGrantCode());
        }
        if (userGrantCodes.containsAll(needGrantCodes)){
            //必须包括所有所需权限点才通过
            return ACCESS_GRANTED;
        }
        //访问拒绝
        return ACCESS_DENIED;
    }
    /**
     * 根据当前访问的url匹配数据库中定义的url访问配置，如果查出多条访问配置，则需要同时满足所有权限
     * @param url 当前访问的url
     * @return
     */
    public List<String> getUrlAccessConfig(String url){
        //从数据库中获取所有的自定义URL访问配置对象
        HashMap<String,UrlAccessConfigEntity> allAccessConfigs = CustomUrlAccessConfig.getAllAccessConfigs();
        //跟当前路径匹配的url权限设定,如果同一个url对应了多个配置，则将多个配置都取出来，必须满足所有配置才能通过
        List<String> needGrantCodes = new ArrayList<>();
        for (String urlPattern : allAccessConfigs.keySet()) {
            //查看配置对象的urlPattern与传入url是否匹配
            if(antPathMatcher.match(urlPattern, url)){
                needGrantCodes.add(allAccessConfigs.get(urlPattern).getGrantCode());
            }
        }
        return needGrantCodes;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }
    @Override
    public boolean supports(Class clazz) {
        return true;
    }
}