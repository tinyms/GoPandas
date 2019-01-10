package com.scriptlte.gopandas.security.IpSecurity;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class IpAuthenticationProvider implements AuthenticationProvider {
    final static Map<String, SimpleGrantedAuthority> ipAuthorityMap = new ConcurrentHashMap<>();
    //维护一个ip白名单列表，每个ip对应一定的权限
    static {
        ipAuthorityMap.put("127.0.0.1", new SimpleGrantedAuthority("ADMIN"));
        ipAuthorityMap.put("10.0.0.8", new SimpleGrantedAuthority("FRIEND"));
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof IpAuthenticationToken){
            String ip = ((IpAuthenticationToken)authentication).getIp();
            if (ipAuthorityMap.containsKey(ip)){
                HashSet<SimpleGrantedAuthority> grants = new HashSet();
                grants.add(ipAuthorityMap.get(ip));
                return new IpAuthenticationToken(ip,grants);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return IpAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
