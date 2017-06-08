<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 客户管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-8" style="width:100%">
            <div class="ibox">
                <div class="ibox-content">
                    <span class="text-muted small pull-right">最后更新：<i class="fa fa-clock-o"></i> 2015-09-01 12:00</span>
                    <h2>日志管理AAA</h2>
                    <#list log_list as log>
								${log.logid} 
							</#list>
                    <p>
                        所有用户必须通过验证
                    </p>
                    <div class="input-group">
                        <input type="text" placeholder="查找用户" class="input form-control">
                        <span class="input-group-btn">
                                        <button type="button" class="btn btn btn-primary"> <i class="fa fa-search"></i> 搜索</button>
                                </span>
                    </div>
                    <div class="clients-list">
                        <ul class="nav nav-tabs">
                            <span class="pull-right small text-muted">1406条记录
                            </span>
                            <li class="active"><a data-toggle="tab" href="#tab-1"><i class="fa fa-user"></i>日志记录</a>
                            </li>
                            <!--<li class=""><a data-toggle="tab" href="#tab-2"><i class="fa fa-briefcase"></i> 部门</a>-->
                            <!--</li>-->
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="full-height-scroll">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-hover">
                                            <tbody>
                                            <tr>
                                                <td><a data-toggle="tab" href="#contact-1" class="client-link">1</a>
                                                </td>
                                                <td>撤销事件请求</td>
                                                <#list log_list as log>
													${log.LOGMETHOD} 
												</#list>
                                                <td class="client-status"><span class="label label-primary">2015-09-01 12:00</span>
                                            </td>
                                                <td class="contact-type"><i class="fa fa-check-circle-o"> </i>
                                                </td>
                                                <td>袁悦</td>

                                                <td class="contact-type"><i class="fa fa-check-circle-o"> </i>
                                                </td>
                                                <td>逻辑地址</td>
                                            </tr>
                                            <tr>
                                                <td><a data-toggle="tab" href="#contact-2" class="client-link">2</a>
                                                </td>
                                                <td>用户修改个人信息</td>
                                                <td class="client-status"><span class="label label-primary">2015-09-01 12:00</span>
                                            </td>
                                                <td class="contact-type"><i class="fa fa-check-circle-o"> </i>
                                                </td>
                                                <td class="">王利锋</td>

                                                <td class="contact-type"><i class="fa fa-check-circle-o"> </i>
                                                </td>
                                                <td>逻辑地址</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- 全局js -->
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>

<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- 自定义js -->
<script src="js/content.js?v=1.0.0"></script>

<script>
    $(function () {
        $('.full-height-scroll').slimScroll({
            height: '100%'
        });
    });
</script>




</body>

</html>
