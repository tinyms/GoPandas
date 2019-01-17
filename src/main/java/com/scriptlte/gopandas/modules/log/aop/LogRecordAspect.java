package com.scriptlte.gopandas.modules.log.aop;


import com.scriptlte.gopandas.modules.log.annotation.LogRecord;
import com.scriptlte.gopandas.modules.log.config.LogRecordConfig;
import com.scriptlte.gopandas.modules.log.dao.LogEntityRepository;
import com.scriptlte.gopandas.modules.log.pojo.LogEntity;
import com.scriptlte.gopandas.modules.log.service.LogEntityService;
import com.scriptlte.gopandas.modules.security.pojo.user.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;


/**
 * 日志记录切面实现类
 */
@Aspect
@Component
@Slf4j
public class LogRecordAspect {

    @Autowired
    private LogEntityRepository logEntityRepository;
    @Autowired
    private LogEntityService logEntityService;
    @Autowired
    private LogRecordConfig logRecordConfig;

    @Before("@annotation(com.scriptlte.gopandas.modules.log.annotation.LogRecord)")
    public void Before_Record(JoinPoint joinPoint) {
        if (!LogRecordConfig.isOpenRecord()) {
            //未开启则不记录操作日志
            return;
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LogRecord logRecordAnnotation = method.getAnnotation(LogRecord.class);
        doRecode(logRecordAnnotation.operaModule(), logRecordAnnotation.operaFunctionName(), logRecordAnnotation.operaDescription(), method, logEntityService);
    }

    public static void doRecode(@NotNull String module, @NotNull String functionName, @NotNull String description, Method calledMethod, @NotNull LogEntityService logEntityService) {
        if (!LogRecordConfig.isOpenRecord()) {
            return;
        }
        OrgUser user = getCurrentUser();
        if (user != null) {
            Long timeStamp = System.currentTimeMillis();
            LogEntity logEntity = new LogEntity();
            logEntity
                    .setOperaModule(module)
                    .setOperaFunctionName(functionName)
                    .setOperaDescription(description)
                    .setOperaTime(timeStamp)
                    .setOperaUserName(user.getUsername());
            if (calledMethod != null) {
                logEntity.setCalledMethod(calledMethod.getDeclaringClass().getName() + "." + calledMethod.getName());
            }
            logEntityService.addLogEntity(logEntity);
        }
    }

    public static OrgUser getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof OrgUser) {
            return (OrgUser) userDetails;
        } else {
            log.error("操作日志记录: 操作记录失败！Cause:当前用户对象非OrgUser对象！");
            return null;
        }
    }
}

