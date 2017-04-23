<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>事件详情</title>
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
                                <dd><span class="label label-primary">已解决</span>
                                </dd>
                            </dl>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-5">
                            <dl class="dl-horizontal">

                                <dt>事件描述：</dt>
                                <dd>显卡性能优化</dd>
                                <dt>事件类型：</dt>
                                <dd>显卡切换与调试</dd>
                                <dt>应答记录：</dt>
                                <dd><a href="KnowledgeDetail.html" class="text-navy">请您点击这里查看"显卡性能优化模式的调整方法"...</a></dd>
                                <dt>发问者：</dt>
                                <dd><a href="Userdetail.html#" class="text-navy">张三</a>
                                </dd>
                                <dt>解决方式：</dt>
                                <dd><a href="Userdetail.html#" class="text-navy">小乐</a>
                                </dd>
                            </dl>
                        </div>
                        <div class="col-sm-7" id="cluster_info">
                            <dl class="dl-horizontal">

                                <dt>提出时间：</dt>
                                <dd>2017年3月16日 22:03</dd>
                                <dt>解决时间：</dt>
                                <dd>2017年3月16日 22:06</dd>
                                <dt>反馈记录：</dt>
                                <dd>满意</dd>
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
