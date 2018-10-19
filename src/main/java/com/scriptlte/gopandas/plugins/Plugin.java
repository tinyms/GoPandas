package com.scriptlte.gopandas.plugins;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

public class Plugin {
    public static <T> T load(String id, Environment env) {
        String clsName = env.getProperty(id);
        if (StringUtils.isBlank(clsName)){
            return null;
        }
        try {
            return (T)ClassUtils.getClass(clsName, true).newInstance();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
