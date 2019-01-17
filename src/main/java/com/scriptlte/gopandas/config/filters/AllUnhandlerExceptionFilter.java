package com.scriptlte.gopandas.config.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 该过滤器拦截某个请求中出现的未被捕捉异常,并返回统一格式
 */
@Slf4j
//@WebFilter
//@Component("allUnhandlerExceptionFilter")
@Order(Integer.MIN_VALUE+1)
public class AllUnhandlerExceptionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
//            response.getWriter().print("试试");
//            System.out.println("看看是否被关闭了:"+response.isCommitted());
        }
        catch (Throwable throwable){
            log.error("抓到异常一个！",throwable);
        }
    }

    @Override
    public void destroy() {

    }
}
