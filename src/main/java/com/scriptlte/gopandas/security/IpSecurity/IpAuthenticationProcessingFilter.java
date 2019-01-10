package com.scriptlte.gopandas.security.IpSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class IpAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {


    public IpAuthenticationProcessingFilter(String uri, @Autowired AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(uri));
        if (authenticationManager != null) {
            setAuthenticationManager(authenticationManager);
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        //获取host信息
        String host = httpServletRequest.getRemoteHost();
        //交给内部的AuthenticationManager去认证，实现解耦
        return getAuthenticationManager().authenticate(new IpAuthenticationToken(host));
    }
}
