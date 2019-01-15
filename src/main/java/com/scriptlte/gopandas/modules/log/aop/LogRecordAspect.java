package com.scriptlte.gopandas.modules.log.aop;


import com.scriptlte.gopandas.modules.log.annotation.LogRecord;
import com.scriptlte.gopandas.modules.log.dao.LogEntityRepository;
import com.scriptlte.gopandas.modules.log.pojo.LogEntity;
import com.scriptlte.gopandas.modules.log.service.LogEntityService;
import com.scriptlte.gopandas.modules.security.pojo.user.OrgUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

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
    @Value("${logRecord.open:false}")
    private boolean openRecord;

    @Before("@annotation(com.scriptlte.gopandas.modules.log.annotation.LogRecord)")
    public void Before_Record(JoinPoint joinPoint) {
        Long timeStamp = System.currentTimeMillis();
        if (!isOpenRecord()) {
            //未开启则不记录操作日志
            return;
        }
        Class clazz = joinPoint.getClass();
        try {
            Method method = clazz.getDeclaredMethod(joinPoint.getSignature().getName(), null);
            LogRecord logRecordAnnotation = method.getAnnotation(LogRecord.class);
            logRecordAnnotation.operaDescription();
            OrgUser user = getCurrentUser();
            if (user != null) {
                LogEntity logEntity = new LogEntity();
                logEntity
                        .setOperaModule(logRecordAnnotation.operaModule())
                        .setOperaFunctionName(logRecordAnnotation.operaFunctionName())
                        .setOperaDescription(logRecordAnnotation.operaDescription())
                        .setOperaTime(timeStamp)
                        .setOperaUserName(user.getUsername());
            }
        } catch (NoSuchMethodException e) {
            log.error("操作日志记录: 操作记录失败！Cause:未获取到注解方法！");
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

    public boolean isOpenRecord() {
        return this.openRecord;
    }

    public void closeLogRecord() {
        this.openRecord = false;
    }

    public void openLogRecord() {
        this.openRecord = true;
    }
}
