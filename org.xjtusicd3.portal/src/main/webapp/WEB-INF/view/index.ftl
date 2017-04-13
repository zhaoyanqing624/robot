<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title> hAdmin- 主页</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:16px;">
                                        <i class="fa fa-area-chart"></i>
                                        <strong class="font-bold">烟草公司IT管理系统</strong>
                                    </span>
                                </span>
                        </a>
                    </div>
                    <div class="logo-element">hAdmin
                    </div>
                </li>
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope">管理员界面</span>
                </li>
                <li>
                    <a class="J_menuItem" href="ManageIndexElement.html">
                        <i class="fa fa-home"></i>
                        <span class="nav-label">首页</span>
                    </a>
                </li>

                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope"></span>

                <li>
                    <a href="">
                        <i class="fa fa-paste"></i>
                        <span class="nav-label">ITIL管理流程</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="ManageIncidentIndex.html">
                                <i class="fa fa-paste"></i>
                                <span class="nav-label">事件</span>
                            </a>
                        </li>

                        <li>
                            <a class="J_menuItem" href="ManageProblemIndex.html">
                                <i class="fa fa-warning"></i>
                                <span class="nav-label">问题</span>
                            </a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="ManageChangeIndex.html">
                                <i class="fa fa-edit"></i>
                                <span class="nav-label">变更</span>
                            </a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="ManageCfgIndex.html">
                                <i class="fa fa-cutlery"></i>
                                <span class="nav-label">配置</span>
                            </a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="knowledgeindex.html">
                                <i class="fa fa-table"></i>
                                <span class="nav-label">知识</span>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope"></span>
                </li>

                <li>
                    <a class="J_menuItem" href="ManageWormIndex.html">
                        <i class="fa fa-bug"></i>
                        <span class="nav-label">爬虫管理</span>
                        <span class="nav-label"></span>
                    </a>
                </li>


                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope"></span>
                </li>
                <li>
                    <a class="J_menuItem" href="ManageLog.html">
                        <i class="fa fa-user-md"></i>
                        <span class="nav-label">日志管理</span>
                    </a>
                </li>

                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope"></span>
                </li>
                <li>
                    <a class="J_menuItem" href="ManageFile.html">
                        <i class="fa fa-file"></i>
                        <span class="nav-label">文件管理</span>
                        <span class="nav-label"></span>
                    </a>
                </li>
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope"></span>
                </li>

                <li>
                    <a class="J_menuItem" href="SuperUserManage.html">
                        <i class="fa fa-user-plus"></i>
                        <span class="nav-label">用户管理</span>
                    </a>
                </li>
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope"></span>
                </li>
                <li>
                    <a class="J_menuItem" href="ManageMessage.html">
                        <i class="fa fa-link"></i>
                        <span class="nav-label">留言管理</span>
                    </a>
                </li>

                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope"></span>
                </li>

                <li>
                    <a class="J_menuItem" href="SuperRBAC.html">
                        <i class="fa fa-rebel"></i>
                        <span class="nav-label">权限管理</span>
                    </a>
                </li>


            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <!--<div class="row border-bottom">-->
            <!--<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">-->
                <!--&lt;!&ndash;<div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i class="fa fa-bars"></i> </a>&ndash;&gt;-->
                    <!--&lt;!&ndash;<form role="search" class="navbar-form-custom" method="post" action="search_results.html">&ndash;&gt;-->
                        <!--&lt;!&ndash;<div class="form-group">&ndash;&gt;-->
                            <!--&lt;!&ndash;<input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">&ndash;&gt;-->
                        <!--&lt;!&ndash;</div>&ndash;&gt;-->
                    <!--&lt;!&ndash;</form>&ndash;&gt;-->
                <!--&lt;!&ndash;</div>&ndash;&gt;-->
                <!--<ul class="nav navbar-top-links navbar-right">-->
                    <!--<li class="dropdown">-->
                        <!--<a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">-->
                            <!--<i class="fa fa-envelope"></i> <span class="label label-warning">16</span>-->
                        <!--</a>-->
                        <!--<ul class="dropdown-menu dropdown-messages">-->
                            <!--<li class="m-t-xs">-->
                                <!--<div class="dropdown-messages-box">-->
                                    <!--<a href="profile.html" class="pull-left">-->
                                        <!--<img alt="image" class="img-circle" src="img/a7.jpg">-->
                                    <!--</a>-->
                                    <!--<div class="media-body">-->
                                        <!--<small class="pull-right">46小时前</small>-->
                                        <!--<strong>小四</strong> 是不是只有我死了,你们才不骂爵迹-->
                                        <!--<br>-->
                                        <!--<small class="text-muted">3天前 2014.11.8</small>-->
                                    <!--</div>-->
                                <!--</div>-->
                            <!--</li>-->
                            <!--<li class="divider"></li>-->
                            <!--<li>-->
                                <!--<div class="dropdown-messages-box">-->
                                    <!--<a href="profile.html" class="pull-left">-->
                                        <!--<img alt="image" class="img-circle" src="img/a4.jpg">-->
                                    <!--</a>-->
                                    <!--<div class="media-body ">-->
                                        <!--<small class="pull-right text-navy">25小时前</small>-->
                                        <!--<strong>二愣子</strong> 呵呵-->
                                        <!--<br>-->
                                        <!--<small class="text-muted">昨天</small>-->
                                    <!--</div>-->
                                <!--</div>-->
                            <!--</li>-->
                            <!--<li class="divider"></li>-->
                            <!--<li>-->
                                <!--<div class="text-center link-block">-->
                                    <!--<a class="J_menuItem" href="mailbox.html">-->
                                        <!--<i class="fa fa-envelope"></i> <strong> 查看所有消息</strong>-->
                                    <!--</a>-->
                                <!--</div>-->
                            <!--</li>-->
                        <!--</ul>-->
                    <!--</li>-->
                    <!--<li class="dropdown">-->
                        <!--<a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">-->
                            <!--<i class="fa fa-bell"></i> <span class="label label-primary">8</span>-->
                        <!--</a>-->
                        <!--<ul class="dropdown-menu dropdown-alerts">-->
                            <!--<li>-->
                                <!--<a href="mailbox.html">-->
                                    <!--<div>-->
                                        <!--<i class="fa fa-envelope fa-fw"></i> 您有16条未读消息-->
                                        <!--<span class="pull-right text-muted small">4分钟前</span>-->
                                    <!--</div>-->
                                <!--</a>-->
                            <!--</li>-->
                            <!--<li class="divider"></li>-->
                            <!--<li>-->
                                <!--<a href="profile.html">-->
                                    <!--<div>-->
                                        <!--<i class="fa fa-qq fa-fw"></i> 3条新回复-->
                                        <!--<span class="pull-right text-muted small">12分钟钱</span>-->
                                    <!--</div>-->
                                <!--</a>-->
                            <!--</li>-->
                            <!--<li class="divider"></li>-->
                            <!--<li>-->
                                <!--<div class="text-center link-block">-->
                                    <!--<a class="J_menuItem" href="notifications.html">-->
                                        <!--<strong>查看所有 </strong>-->
                                        <!--<i class="fa fa-angle-right"></i>-->
                                    <!--</a>-->
                                <!--</div>-->
                            <!--</li>-->
                        <!--</ul>-->
                    <!--</li>-->
                <!--</ul>-->
            <!--</nav>-->
        <!--</div>-->
        <div class="row J_mainContent" id="content-main">
            <iframe id="J_iframe" width="100%" height="100%" src="elementindex.html" frameborder="0" data-id="index_v1.html" seamless></iframe>
        </div>
    </div>
    <!--右侧部分结束-->

</div>

<!-- 全局js -->
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>
<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="js/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->
<script src="js/hAdmin.js?v=4.1.0"></script>
<script type="text/javascript" src="js/index.js"></script>

<!-- 第三方插件 -->
<script src="js/plugins/pace/pace.min.js"></script>
<div style="text-align:center;">
    <p>来源:<a href="http://www.mycodes.net/" target="_blank">源码之家</a></p>
</div>
</body>

</html>
