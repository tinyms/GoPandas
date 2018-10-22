<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>GoPandas 资源管理器</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Cosmo - Responsive Dashboard Admin Template">
    <meta name="author" content="ScriptLTE">
    <link rel="icon" href="/assets/images/favicon.ico">

    <link rel="stylesheet" href="/assets/css/basestyle/style.css">
    <link rel="stylesheet" href="/font-awesome/css/font-awesome.min.css">

    <!-- elFinder CSS (REQUIRED) -->
    <link rel="stylesheet" type="text/css" media="screen" href="/elfinder/css/elfinder.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/elfinder/css/theme.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/elFinder-Material-Theme/css/theme-light.css">

    <!-- elFinder JS (REQUIRED) -->
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="/elfinder/js/elfinder.min.js"></script>
    <script type="text/javascript" src="/assets/json2.js"></script>
    <script type="text/javascript" src="/assets/js.cookie-2.2.0.min.js"></script>
    <script type="text/javascript" src="/assets/underscore-min.js"></script>
    <script type="text/javascript" src="/assets/js/lib/moment.min.js"></script>
    <script type="text/javascript" src="/assets/js/lib/popper.min.js"></script>
    <script type="text/javascript" src="/assets/js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="/assets/js/chosen-js/chosen.jquery.js"></script>
    <script type="text/javascript" src="/assets/layer.js"></script>

    <!-- elFinder translation (OPTIONAL) -->
    <script type="text/javascript" src="/elfinder/js/i18n/elfinder.zh_CN.js"></script>
    <style type="text/css">
        .elfinder-button-icon-sl_create_menu:before {
            content: '\e81b';
        }
        .elfinder-button-icon-sl_create_scheduled:before {
            content: '\e82f';
        }
    </style>
    <!-- elFinder initialization (REQUIRED) -->
    <script type="text/javascript" charset="utf-8">
        // Documentation for client options:
        // https://github.com/Studio-42/elFinder/wiki/Client-configuration-options
        $(document).ready(function () {
            elFinder.prototype.i18.zh_CN.messages['cmd' + 'sl_create_menu'] = '创建菜单';
            elFinder.prototype._options.commands.push('sl_create_menu');
            elFinder.prototype.commands.sl_create_menu = function () {
                this.exec = function (hashes) {
                    //do whatever
                    console.log(1, hashes);
                    var files = this.files(hashes);
                    if (files !== null && files.length > 0) {
                        var file = files[0];
                        //判断后缀
                        var filename = file.name;
                        var strs = filename.split(".");
                        console.log(files, strs);
                    }
                };
                this.getstate = function (sel) {
                    //return 0 to enable, -1 to disable icon access
                    console.log(2, sel);
                    return 0;
                }
            };
            elFinder.prototype.i18.zh_CN.messages['cmd' + 'sl_create_scheduled'] = '创建调度';
            elFinder.prototype._options.commands.push('sl_create_scheduled');
            elFinder.prototype.commands.sl_create_scheduled = function () {
                this.exec = function (hashes) {
                    //do whatever
                    console.log(hashes);
                };
                this.getstate = function (sel) {
                    //return 0 to enable, -1 to disable icon access
                    console.log(sel);
                    return 0;
                }
            };
            var elf_commands = [
                'sl_create_menu', 'sl_create_scheduled', 'open', 'reload', 'home', 'up', 'back', 'forward', 'getfile', 'quicklook',
                'download', 'rm', 'duplicate', 'rename', 'mkdir', 'mkfile', 'upload', 'copy',
                'cut', 'paste', 'edit', 'extract', 'archive', 'search', 'info', 'view', 'help', 'resize', 'sort', 'netmount'
            ];
            var elf_contextmenu = {
                // navbarfolder menu
                navbar: ['open', '|', 'copy', 'cut', 'paste', 'duplicate', '|', 'rm', '|', 'info'],
                // current directory menu
                cwd: ['reload', 'back', '|', 'upload', 'mkdir', 'mkfile', 'paste', '|', 'sort', '|', 'info'],
                // current directory file menu
                files: ['sl_create_menu', 'sl_create_scheduled', '|', 'getfile', '|', 'quicklook', '|', 'download', '|', 'copy', 'cut', 'paste', 'duplicate', '|', 'rm', '|', 'edit', 'rename', 'resize', '|', 'archive', 'extract', '|', 'info']
            };
            $('#elfinder').elfinder({
                cssAutoLoad: false,
                commands: elf_commands,
                contextmenu: elf_contextmenu,
                url: '/connector',  // connector URL (REQUIRED)
                lang: 'zh_CN'// language (OPTIONAL)
            });
        });
    </script>
</head>
<body>
<section class="wrapper">
    <header class="header sticky-top">
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand" href="#">GoPandas</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse pull-right" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                ${_NavBar}
                </ul>

            </div>
        </nav>
    </header>
    <div class="content" style="padding-left:10px; padding-right: 10px;">
        <div id="elfinder" class="row" style="height: 100%;"></div>
    </div>
</section>
</body>
<script type="text/javascript">

    function autodivheight() { //函数：获取尺寸
        //获取浏览器窗口高度
        var winHeight = 0;
        if (window.innerHeight)
            winHeight = window.innerHeight;
        else if ((document.body) && (document.body.clientHeight))
            winHeight = document.body.clientHeight;
        //通过深入Document内部对body进行检测，获取浏览器窗口高度
        if (document.documentElement && document.documentElement.clientHeight)
            winHeight = document.documentElement.clientHeight;
        //DIV高度为浏览器窗口的高度
        document.getElementById("elfinder").style.height = (winHeight - 56) + "px";
    }

    window.onresize = autodivheight; //浏览器窗口发生变化时同时变化DIV高度

    $(function () {
        autodivheight();
    });
</script>
</html>
