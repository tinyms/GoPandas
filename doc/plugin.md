插件系统
==============================

插件系统包含两类扩展类型: `Action`、`Filter`(动作与过滤器)，过滤器针对某一个值进行连贯处理，比如文章内容，过滤掉敏感词汇，解析特别的标签等，并返回过滤后的值; 
虽然动作也可以做过滤处理，但是并不返回值，只是单纯执行某一动作，比如在调度完成后发送邮件。



放置扩展点
-----------------------

扩展点通常布置在`.java`文件中，比如当增加用户完成，或者调度完成，或者文件上传成功后，触发执行实现了某一个扩展点的动作/过滤。

1. 执行动作 

    `` Plugin.do_action("gp_upload_completed", file, user, ...); ``

2. 执行过滤

    `` content = Plugin.apply_filters("gp_posts_published", content, user, ...); ``


添加扩展点实现
-----------------------

扩展点实现统一在 `meta.js` 文件中添加。

1. 添加动作

    ``add_action("gp_upload_completed", "com.company.ClassA", 5);``
    
    > com.company.ClassA 需实现接口 `IAction`
    
2. 添加过滤器

    ``add_filter("gp_posts_published", "com.company.ClassB", 2);``
        
    > com.company.ClassB 需实现接口 `IFilter`
    
    
内置扩展点
-------------------------

待续。。