package com.scriptlte.gopandas.extend;

import org.apache.commons.lang3.ClassUtils;

import java.util.*;

public class Plugin {
    private final static Map<String, List<PluginMeta>> ACTIONS = new HashMap<>();
    private final static Map<String, List<PluginMeta>> FILTERS = new HashMap<>();

    private static void sort(List<PluginMeta> pluginMetas) {
        pluginMetas.sort(new Comparator<PluginMeta>() {
            @Override
            public int compare(PluginMeta o1, PluginMeta o2) {
                return o1.getPriority() - o2.getPriority();
            }
        });
    }

    /**
     * 添加动作
     *
     * @param name     扩展点名称
     * @param clzName  实现了IExtend接口的类全名
     * @param priority 执行优先级, 值越大优先级越小
     */
    public static void add_action(String name, String clzName, int priority) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        Object plugin = ClassUtils.getClass(clzName, true).newInstance();
        if (ACTIONS.containsKey(name)) {
            ACTIONS.get(name).add(new PluginMeta(plugin, priority));
        } else {
            List<PluginMeta> metas = new ArrayList<>();
            metas.add(new PluginMeta(plugin, priority));
            ACTIONS.put(name, metas);
        }
    }

    /**
     * 处理动作
     *
     * @param name 扩展点名称
     * @param args 动作需要的参数
     */
    public static void do_action(String name, Object... args) {
        if (ACTIONS.containsKey(name)) {
            List<PluginMeta> metas = ACTIONS.get(name);
            sort(metas);
            for (PluginMeta pluginMeta : metas) {
                IAction action = (IAction) pluginMeta.getPlugin();
                action.execute(args);
            }
        }
    }

    /**
     * 添加过滤器
     *
     * @param name     扩展点名称
     * @param clzName  实现了IExtend接口的类
     * @param priority 执行优先级, 值越大优先级越小
     */
    public static void add_filter(String name, String clzName, int priority) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        Object plugin = ClassUtils.getClass(clzName, true).newInstance();
        if (FILTERS.containsKey(name)) {
            FILTERS.get(name).add(new PluginMeta(plugin, priority));
        } else {
            List<PluginMeta> metas = new ArrayList<>();
            metas.add(new PluginMeta(plugin, priority));
            FILTERS.put(name, metas);
        }
    }

    /**
     * 执行过滤器
     *
     * @param name 扩展点名称
     * @param v    过滤的值
     * @param args 过滤器需要的参数
     */
    public static Object apply_filters(String name, Object v, Object... args) {
        if (FILTERS.containsKey(name)) {
            List<PluginMeta> metas = FILTERS.get(name);
            sort(metas);
            for (PluginMeta pluginMeta : metas) {
                IFilter filter = (IFilter) pluginMeta.getPlugin();
                v = filter.execute(v, args);
            }
        }
        return v;
    }
}
