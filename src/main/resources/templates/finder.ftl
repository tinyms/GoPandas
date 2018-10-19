<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>GoPandas 资源管理器</title>

    <!-- elFinder CSS (REQUIRED) -->
    <link rel="stylesheet" type="text/css" media="screen" href="/elfinder/css/elfinder.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/elfinder/css/theme.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/elFinder-Material-Theme/css/theme-light.css">

    <!-- elFinder JS (REQUIRED) -->
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="/elfinder/js/elfinder.min.js"></script>

    <!-- elFinder translation (OPTIONAL) -->
    <script type="text/javascript" src="/elfinder/js/i18n/elfinder.zh_CN.js"></script>

    <!-- elFinder initialization (REQUIRED) -->
    <script type="text/javascript" charset="utf-8">
        // Documentation for client options:
        // https://github.com/Studio-42/elFinder/wiki/Client-configuration-options
        $(document).ready(function() {
            $('#elfinder').elfinder({
                cssAutoLoad : false,
                url : '/connector',  // connector URL (REQUIRED)
                lang: 'zh_CN'                    // language (OPTIONAL)
            });
        });
    </script>
</head>
<body>
<!-- Element where elFinder will be created (REQUIRED) -->
<div id="elfinder"></div>
</body>
</html>
