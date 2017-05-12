<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - jqGird</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
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
                    <h5>爬虫列表</h5>
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
        var mydata = [
            {
                id: "1",
                date: "2015-05-25",
                name: "FAQ直属库",
                time: "2017.3.13",
                location: "D:/FAQ/69816219.json",
                information:"SpiderController > knowledge",
                total: "123461"
            },
            {
                id: "2",
                date: "2015-05-25",
                name: "爬取雾霾信息",
                time: "2017.3.13",
                location: "D:/weather/6191951621.json",
                information:"SpiderController > weather",
                total: "1561651"
            },
            {
                id: "3",
                date: "2015-05-25",
                name: "FAQ直属库",
                time: "2017.3.13",
                location: "D:/FAQ/69816219.json",
                information:"SpiderController > knowledge",
                total: "123461"
            },
            {
                id: "4",
                date: "2015-05-25",
                name: "爬取雾霾信息",
                time: "2017.3.13",
                location: "D:/weather/6191951621.json",
                information:"SpiderController > weather",
                total: "1561651"
            },
            {
                id: "5",
                date: "2015-05-25",
                name: "FAQ直属库",
                time: "2017.3.13",
                location: "D:/FAQ/69816219.json",
                information:"SpiderController > knowledge",
                total: "123461"
            },
            {
                id: "6",
                date: "2015-05-25",
                name: "爬取雾霾信息",
                time: "2017.3.13",
                location: "D:/weather/6191951621.json",
                information:"SpiderController > weather",
                total: "1561651"
            },
            {
                id: "7",
                date: "2015-05-25",
                name: "FAQ直属库",
                time: "2017.3.13",
                location: "D:/FAQ/69816219.json",
                information:"SpiderController > knowledge",
                total: "123461"
            },
            {
                id: "8",
                date: "2015-05-25",
                name: "爬取雾霾信息",
                time: "2017.3.13",
                location: "D:/weather/6191951621.json",
                information:"SpiderController > weather",
                total: "1561651"
            },
            {
                id: "9",
                date: "2015-05-25",
                name: "FAQ直属库",
                time: "2017.3.13",
                location: "D:/FAQ/69816219.json",
                information:"SpiderController > knowledge",
                total: "123461"
            },
            {
                id: "10",
                date: "2015-05-25",
                name: "爬取雾霾信息",
                time: "2017.3.13",
                location: "D:/weather/6191951621.json",
                information:"SpiderController > weather",
                total: "1561651"
            },

        ];



        // Configuration for jqGrid Example 2
        $("#table_list_2").jqGrid({
            data: mydata,
            datatype: "local",
            height: 450,
            autowidth: true,
            shrinkToFit: true,
            rowNum: 20,
            rowList: [10, 20, 30],
            colNames: ['爬虫编号', '添加日期', '爬虫功能简介', '上次爬虫时间', '数据存储地址', '爬虫逻辑信息', '爬取数目'],
            colModel: [
                {
                    name: 'id',
                    index: 'id',
                    editable: true,
                    width: 60,
                    sorttype: "int",
                    search: true
                },
                {
                    name: 'date',
                    index: 'date',
                    editable: true,
                    width: 90,
                    sorttype: "date",
                    formatter: "date"
                },
                {
                    name: 'name',
                    index: 'name',
                    editable: true,
                    width: 100
                },
                {
                    name: 'time',
                    index: 'time',
                    editable: true,
                    width: 50,
                    align: "right",
                    sorttype: "float",
                },
                {
                    name: 'location',
                    index: 'location',
                    editable: true,
                    width: 110,
                    align: "right",
                    sorttype: "float"
                },
                {
                    name: 'information',
                    index: 'information',
                    editable: true,
                    width: 110,
                    align: "right",
                    sorttype: "float"
                },
                {
                    name: 'total',
                    index: 'total',
                    editable: true,
                    width: 70,
                    sortable: false
                }
            ],
            pager: "#pager_list_2",
            viewrecords: true,
            caption: "爬虫信息",
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
