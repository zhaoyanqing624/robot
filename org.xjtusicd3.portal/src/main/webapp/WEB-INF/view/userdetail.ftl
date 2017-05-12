<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>用户详情</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="row">
    <div class="col-sm-9" style="width: 100%">
        <div class="wrapper wrapper-content animated fadeInUp">
            <div class="ibox">
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="m-b-md">
                                <a href="#" class="btn btn-white btn-xs pull-left" onClick="javascript :history.back(-1);">返回</a>


                            </div>
                            <dl class="dl-horizontal">
                                <dt>当前状态：</dt>
                                <dd><span class="label label-primary">在职</span>
                                </dd>
                            </dl>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-5">
                            <dl class="dl-horizontal">

                                <dt>姓名：</dt>
                                <dd>张三</dd>
                                <dt>年龄：</dt>
                                <dd>32</dd>
                                <dt>性别：</dt>
                                <dd>男</dd>
                                <dt>职位：</dt>
                                <dd>科员 </dd>
                                <dt>用户密码：</dt>
                                <dd>65165198162198 /dd>
                            </dl>
                        </div>
                        <div class="col-sm-7" id="cluster_info">
                            <dl class="dl-horizontal">

                                <dt>邮箱：</dt>
                                <dd>5985161891@qq.com</dd>
                                <dt>联系方式：</dt>
                                <dd>15626892165</dd>
                                <dt>注册时间：</dt>
                                <dd>2017年3月16日 22:03</dd>

                            </dl>
                        </div>
                    </div>
                    <!--<div class="row">-->
                    <!--<div class="col-sm-12">-->
                    <!--<dl class="dl-horizontal">-->
                    <!--<dt>当前进度</dt>-->
                    <!--<dd>-->
                    <!--<div class="progress progress-striped active m-b-sm">-->
                    <!--<div style="width: 60%;" class="progress-bar"></div>-->
                    <!--</div>-->
                    <!--<small>当前已完成项目总进度的 <strong>60%</strong></small>-->
                    <!--</dd>-->
                    <!--</dl>-->
                    <!--</div>-->
                    <!--</div>-->
                    <div class="row m-t-sm">
                        <div class="col-sm-12">
                            <div class="panel blank-panel">
                                <div class="panel-heading">
                                    <div class="panel-options">
                                        <ul class="nav nav-tabs">
                                            <li><a href="project_detail.html#tab-1" data-toggle="tab">对应发帖</a>
                                            </li>
                                            <!--<li class=""><a href="project_detail.html#tab-2" data-toggle="tab">最后更新</a>-->
                                            <!--</li>-->
                                        </ul>
                                    </div>
                                </div>

                                <div class="panel-body">

                                    <div class="tab-content">
                                        <div class="tab-pane active" id="tab-1">
                                            <div class="feed-activity-list">


                                                <div class="feed-element">

                                                    <div class="media-body ">
                                                        <small class="pull-right">2小时前</small>
                                                        <strong></strong>
                                                        <br>
                                                        <small class="text-muted"> </small>
                                                        <div class="well">
                                                            <a href="incidentdetail.html">为什么我的电脑总是蓝屏</a>
                                                        </div>

                                                    </div>
                                                </div>

                                            </div>

                                        </div>
                                        <!--<div class="tab-pane" id="tab-2">-->

                                        <!--<table class="table table-striped">-->
                                        <!--<thead>-->
                                        <!--<tr>-->
                                        <!--<th>状态</th>-->
                                        <!--<th>标题</th>-->
                                        <!--<th>开始时间</th>-->
                                        <!--<th>结束时间</th>-->
                                        <!--<th>说明</th>-->
                                        <!--</tr>-->
                                        <!--</thead>-->
                                        <!--<tbody>-->
                                        <!--<tr>-->
                                        <!--<td>-->
                                        <!--<span class="label label-primary"><i class="fa fa-check"></i> 已完成</span>-->
                                        <!--</td>-->
                                        <!--<td>-->
                                        <!--文档在线预览功能-->
                                        <!--</td>-->
                                        <!--<td>-->
                                        <!--11月7日 22:03-->
                                        <!--</td>-->
                                        <!--<td>-->
                                        <!--11月7日 20:11-->
                                        <!--</td>-->
                                        <!--<td>-->
                                        <!--<p class="small">-->
                                        <!--已经测试通过-->
                                        <!--</p>-->
                                        <!--</td>-->

                                        <!--</tr>-->
                                        <!--<tr>-->
                                        <!--<td>-->
                                        <!--<span class="label label-primary"><i class="fa fa-check"></i> 解决中</span>-->
                                        <!--</td>-->
                                        <!--<td>-->
                                        <!--会员登录-->
                                        <!--</td>-->
                                        <!--<td>-->
                                        <!--11月7日 22:03-->
                                        <!--</td>-->
                                        <!--<td>-->
                                        <!--11月7日 20:11-->
                                        <!--</td>-->
                                        <!--<td>-->
                                        <!--<p class="small">-->
                                        <!--测试中-->
                                        <!--</p>-->
                                        <!--</td>-->

                                        <!--</tr>-->
                                        <!--<tr>-->
                                        <!--<td>-->
                                        <!--<span class="label label-primary"><i class="fa fa-check"></i> 解决中</span>-->
                                        <!--</td>-->
                                        <!--<td>-->
                                        <!--会员积分-->
                                        <!--</td>-->
                                        <!--<td>-->
                                        <!--11月7日 22:03-->
                                        <!--</td>-->
                                        <!--<td>-->
                                        <!--11月7日 20:11-->
                                        <!--</td>-->
                                        <!--<td>-->
                                        <!--<p class="small">-->
                                        <!--未测试-->
                                        <!--</p>-->
                                        <!--</td>-->

                                        <!--</tr>-->


                                        <!--</tbody>-->
                                        <!--</table>-->

                                        <!--</div>-->
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



<!-- 自定义js -->
<script src="js/content.js?v=1.0.0"></script>


<script>
    $(document).ready(function () {

        $('#loading-example-btn').click(function () {
            btn = $(this);
            simpleLoad(btn, true)

            // Ajax example
            //                $.ajax().always(function () {
            //                    simpleLoad($(this), false)
            //                });

            simpleLoad(btn, false)
        });
    });

    function simpleLoad(btn, state) {
        if (state) {
            btn.children().addClass('fa-spin');
            btn.contents().last().replaceWith(" Loading");
        } else {
            setTimeout(function () {
                btn.children().removeClass('fa-spin');
                btn.contents().last().replaceWith(" Refresh");
            }, 2000);
        }
    }
</script>




</body>

</html>
