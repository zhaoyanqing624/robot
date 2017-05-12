<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - jqGird</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <!-- jqgrid-->
    <link href="css/plugins/jqgrid/ui.jqgrid.css?0820" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

    <style>
        /* Additional style to fix warning dialog position */

        #alertmod_table_list_2 {
            top: 900px !important;
        }
    </style>

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>用户列表</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="SuperUserManage.html">普通用户</a>
                            </li>
                            <li><a href="SuperUserManage.html">管理员</a>
                            </li>                         
                            <li><a href="SuperUserManage.html">IT运维师</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="input-group">
                        <input type="text" placeholder="请输入搜索内容" class="input-sm form-control"> <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-primary"> 搜索</button> </span>

                    </div>
                    <div class="jqGrid_wrapper">
                        <table id="table_list_2"></table>
                        <div id="pager_list_2"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>



<!-- Peity -->
<script src="js/plugins/peity/jquery.peity.min.js"></script>

<!-- jqGrid -->
<script src="js/plugins/jqgrid/i18n/grid.locale-cn.js?0820"></script>
<script src="js/plugins/jqgrid/jquery.jqGrid.min.js?0820"></script>

<!-- 自定义js -->
<script src="js/content.js?v=1.0.0"></script>

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {

        $.jgrid.defaults.styleUI = 'Bootstrap';
        // Examle data for jqGrid
        var mydata = [${user}];



        // Configuration for jqGrid Example 2
        $("#table_list_2").jqGrid({
            data: mydata,
            datatype: "local",
            height: 450,
            autowidth: true,
            shrinkToFit: true,
            rowNum: 20,
            rowList: [10, 20, 30],
            colNames: ['ID',  '用户名', '用户密码', '用户邮箱'],
            colModel: [
                {
                    name: 'id',
                    index: 'id',
                    editable: true,
                    width: 60,
                    sorttype: "int",
                    search: true,
                    formatter:'showlink',
                    formatoptions:{baseLinkUrl:"userdetail.html"}
                },
                {
                    name: 'userName',
                    index: 'userName',
                    editable: true,
                    width: 90,
                    sorttype: "int",
                },
                {
                    name: 'userPassword',
                    index: 'userPassword',
                    editable: true,
                    width: 100
                },
                {
                    name: 'userEmail',
                    index: 'userEmail',
                    editable: true,
                    width: 80,
                    align: "right",
                    sorttype: "float",
                }, 
            ],
            pager: "#pager_list_2",
            viewrecords: true,
            caption: "所有用户",
            add: true,
            edit: true,
            addtext: 'Add',
            edittext: 'Edit',
            hidegrid: false
        });

        // Add selection
        $("#table_list_2").setSelection(4, true);


        // Setup buttons
        $("#table_list_2").jqGrid('navGrid', '#pager_list_2', {
            edit: true,
            add: true,
            del: true,
            search: true
        }, {
            height: 200,
            reloadAfterSubmit: true
        });

        // Add responsive to jqGrid
        $(window).bind('resize', function () {
            var width = $('.jqGrid_wrapper').width();
            $('#table_list_1').setGridWidth(width);
            $('#table_list_2').setGridWidth(width);
        });
    });
</script>




</body>

</html>
