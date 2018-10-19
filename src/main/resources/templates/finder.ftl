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

    <!-- elFinder initialization (REQUIRED) -->
    <script type="text/javascript" charset="utf-8">
        // Documentation for client options:
        // https://github.com/Studio-42/elFinder/wiki/Client-configuration-options
        $(document).ready(function () {
            $('#elfinder').elfinder({
                cssAutoLoad: false,
                url: '/connector',  // connector URL (REQUIRED)
                lang: 'zh_CN'                    // language (OPTIONAL)
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
