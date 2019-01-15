package com.scriptlte.gopandas.modules.log.config;

import com.scriptlte.gopandas.modules.log.service.LogEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LogRecordConfig {

    /**
     * 是否开启操作日志记录
     */
    private static boolean openRecord;

    /**
     * Spring不支持对静态的属性进行注入,但是支持Set方法注入,通过这种方法注入静态变量
     */
    @Value("${logrecord.open:false}")
    public void setOpenRecord(boolean openRecord) {
        LogRecordConfig.openRecord = openRecord;
    }

    public static boolean isOpenRecord() {
        return LogRecordConfig.openRecord;
    }

    public static void closeLogRecord() {
        LogRecordConfig.openRecord = false;
    }

    public static void openLogRecord() {
        LogRecordConfig.openRecord = true;
    }
}
