package com.scriptlte.gopandas.utils;

import org.slf4j.Logger;

public class LogUtil {

    public static void printDebug(String message, Logger log) {
        if (log.isDebugEnabled()) {
            log.debug(message);
        }
    }

    public static void printDebug(String message, Logger log , Throwable throwable) {
        if (log.isDebugEnabled()) {
            log.debug(message,throwable);
        }
    }

}
