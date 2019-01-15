package com.scriptlte.gopandas.modules.log.config;

import com.scriptlte.gopandas.modules.log.dao.LogEntityRepository;
import com.scriptlte.gopandas.modules.log.service.LogEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("logConstant")
public class LogRecordConstant {

    /**
     * 对日志Service对象的引用
     */
    public static LogEntityService logEntityService;
    /**
     * 对日志Repository对象的引用
     */
    public static LogEntityRepository logEntityRepository;


    public final static String LOGRECORD_MODULE_LOG = "module_log";






    @Autowired(required = true)
    public static void setLogEntityService(LogEntityService logEntityService) {
        LogRecordConstant.logEntityService = logEntityService;
    }

    @Autowired(required = true)
    public static void setLogEntityRepository(LogEntityRepository logEntityRepository) {
        LogRecordConstant.logEntityRepository = logEntityRepository;
    }
}
