package com.scriptlte.gopandas.modules.log.annotation;

import java.lang.annotation.*;

/**
 * 加上该注解的方法被调用时 调用记录会被存储在日志中(该注解只针对Spring管理范围内的方法)
 * 因为该注解的功能实现是基于Spring AOP 拦截相关方法的执行，以记录当前方法的调用信息
 */
@Target(value = ElementType.METHOD)
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface LogRecord {
    /**
     * 操作对应的功能名称
     */
    public String operaFunctionName();

    /**
     * 操作的描述
     */
    public String operaDescription();

    /**
     * 操作对应功能所属的模块
     */
    public String operaModule();
}
