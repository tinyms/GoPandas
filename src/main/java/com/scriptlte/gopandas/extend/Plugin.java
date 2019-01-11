package com.scriptlte.gopandas.extend;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ClassUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Plugin {
    private final static Map<String, List<Item>> ACTIONS = new HashMap<>();
    private final static Map<String, List<Item>> FILTERS = new HashMap<>();

    private static void sort(List<Item> pluginMetas) {
        pluginMetas.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
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
            ACTIONS.get(name).add(new Item(plugin, priority));
        } else {
            List<Item> metas = new ArrayList<>();
            metas.add(new Item(plugin, priority));
            ACTIONS.put(name, metas);
        }
    }

    /**
     * 处理动作
     *
     * @param name 扩展点名称
     * @param args 动作需要的参数
     */
    public static void do_actions(String name, Object... args) {
        if (ACTIONS.containsKey(name)) {
            List<Item> metas = ACTIONS.get(name);
            sort(metas);
            for (Item pluginMeta : metas) {
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
            FILTERS.get(name).add(new Item(plugin, priority));
        } else {
            List<Item> metas = new ArrayList<>();
            metas.add(new Item(plugin, priority));
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
            List<Item> metas = FILTERS.get(name);
            sort(metas);
            for (Item pluginMeta : metas) {
                IFilter filter = (IFilter) pluginMeta.getPlugin();
                v = filter.execute(v, args);
            }
        }
        return v;
    }

    private static void iteratorDirectory(File f, List<File> paths) {
        File[] fs = f.listFiles();
        if (fs == null) {
            return;
        }
        for (File file : fs) {
            if (file.isDirectory()) {
                iteratorDirectory(file, paths);
            }
            if (file.isFile()) {
                if (file.getName().equals("meta.js")) {
                    paths.add(file.getAbsoluteFile());
                }
            }
        }
    }

    /**
     * 搜索插件目录，找到所有 meta.js
     *
     * @param pluginPaths 插件目录
     * @return 返回 meta.js 集合
     */
    public static List<File> search(String[] pluginPaths) {
        List<File> paths = new ArrayList<>();
        if (pluginPaths != null) {
            for (String path : pluginPaths) {
                File file = new File(path);
                iteratorDirectory(file, paths);
            }
        }
        return paths;
    }

    /**
     * 执行 meta.js，载入全部扩展点实现
     *
     * @param metaFiles meta.js 集合
     */
    public static void load(List<File> metaFiles) {
        if (metaFiles == null) {
            return;
        }
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        SimpleBindings simpleBindings = new SimpleBindings();
        simpleBindings.put("gp_plugin_class", Plugin.class);
        for (File f : metaFiles) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("function add_action(name, clsName, priority){gp_plugin_class.add_action(name, clsName, priority);}");
                sb.append("function add_filter(name, clsName, priority){gp_plugin_class.add_filter(name, clsName, priority);}");
                String s = FileUtils.readFileToString(f, "utf-8");
                sb.append(s);
                try {
                    engine.eval(sb.toString(), simpleBindings);
                } catch (ScriptException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

    }
}
