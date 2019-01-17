package com.scriptlte.gopandas.modules.security.config;

import com.scriptlte.gopandas.modules.security.IpSecurity.IpAuthenticationProcessingFilter;
import com.scriptlte.gopandas.modules.security.config.pwencoder.Md5PasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "orgUserService")
    UserDetailsService userDetailsService;
    @Resource(name = "wrapResponseAccessDeniedHandle")
    AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(new IpAuthenticationProvider());
        auth.userDetailsService(userDetailsService).passwordEncoder(Md5PasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        EnableSecurity(http);
    }

    private void EnableSecurity(HttpSecurity http) throws Exception{
        http
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
                .authorizeRequests()
                //这几个路径 不需要权限
                .antMatchers("/finder*").hasAnyAuthority("testGrant3")
//                .antMatchers("/finder*").hasAnyRole("TESTROLE")
                .antMatchers("/iplogin","/login").permitAll()
                //其他url需要登陆权限
                .anyRequest().authenticated()
                .and()
                //配置form表单方式登陆点
                .formLogin()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .csrf().disable();
        //如果出现权限不够，或者未登录访问的情况，会跳转到该url
//                .loginPage("/login");
        //把自定义的过滤器加到SpringSecurity的过滤器之前
//        http.addFilterBefore(ipAuthenticationProcessingFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    private void unEnableSecurity(HttpSecurity http) throws Exception{
        http.authorizeRequests().anyRequest().permitAll();
    }


    IpAuthenticationProcessingFilter ipAuthenticationProcessingFilter(AuthenticationManager authenticationManager){

        IpAuthenticationProcessingFilter ipAuthenticationProcessingFilter = new IpAuthenticationProcessingFilter("/iplogin",null);
        //将ProviderManager填充进去
        ipAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager);
        ipAuthenticationProcessingFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/iplogin/error"));
        return ipAuthenticationProcessingFilter;
    }
}