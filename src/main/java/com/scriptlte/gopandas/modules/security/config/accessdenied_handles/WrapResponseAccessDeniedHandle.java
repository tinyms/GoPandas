package com.scriptlte.gopandas.modules.security.config.accessdenied_handles;

import com.alibaba.fastjson.JSONObject;
import com.scriptlte.gopandas.vo.ResponseMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义权限不匹配访问失败处理器
 * 为了统一访问格式
 */
@Component("wrapResponseAccessDeniedHandle")
public class WrapResponseAccessDeniedHandle implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        writer.print(JSONObject.toJSONString(ResponseMessage.GetErrorMessage("访问被拒绝！用户权限不足！")));
        writer.flush();
    }
}
