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
                    <span class="ng-scope">管理员</span>
                </li>
                <li>
                    <a class="J_menuItem" href="elementindex.html">
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
                            <a class="J_menuItem" href="incidentindex.html">
                                <i class="fa fa-paste"></i>
                                <span class="nav-label">事件</span>
                            </a>
                        </li>

                        <li>
                            <a class="J_menuItem" href="problemindex.html">
                                <i class="fa fa-warning"></i>
                                <span class="nav-label">问题</span>
                            </a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="changeindex.html">
                                <i class="fa fa-edit"></i>
                                <span class="nav-label">变更</span>
                            </a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="cfgindex.html">
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
                    <a class="J_menuItem" href="spiderindex.html">
                        <i class="fa fa-bug"></i>
                        <span class="nav-label">爬虫管理</span>
                        <span class="nav-label"></span>
                    </a>
                </li>


                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope"></span>
                </li>
                <li>
                    <a class="J_menuItem" href="logindex.html">
                        <i class="fa fa-user-md"></i>
                        <span class="nav-label">日志管理</span>
                    </a>
                </li>

                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope"></span>
                </li>
                 
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope"></span>
                </li>

                <li>
                    <a class="J_menuItem" href="userindex.html">
                        <i class="fa fa-user-plus"></i>
                        <span class="nav-label">用户管理</span>
                    </a>
                </li>
                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope"></span>
                </li>
                <li>
                    <a class="J_menuItem" href="messageindex.html">
                        <i class="fa fa-link"></i>
                        <span class="nav-label">留言管理</span>
                    </a>
                </li>

                <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope"></span>
                </li>

                <li>
                    <a class="J_menuItem" href="rbacindex.html">
                        <i class="fa fa-rebel"></i>
                        <span class="nav-label">权限管理</span>
                    </a>
                </li>

 				<li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                    <span class="ng-scope"></span>
                </li>

				<li>
                    <a class="J_menuItem" href="" onclick="logout()">
                        <i class="fa fa-warning"></i>
                        <span class="nav-label">退出</span>
                    </a>
                </li>

            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        
        <div class="row J_mainContent" id="content-main">
            <iframe id="J_iframe" width="100%" height="100%" src="elementindex.html" frameborder="0" data-id="index_v1.html" seamless></iframe>
        </div>
    </div>
    <!--右侧部分结束-->

</div>

<script language="JavaScript" type="text/javascript">
    function logout(){
         if (confirm("您确定要退出系统吗？"))
        	 top.location = "login.html";
         	
         return false;
    }
</script>

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
