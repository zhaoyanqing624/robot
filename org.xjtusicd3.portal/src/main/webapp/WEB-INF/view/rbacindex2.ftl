<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>权限管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>权限分配&nbsp;<small></small></h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="SuperRBAC.html">普通用户</a>
                            </li>
                            <li><a href="SuperRBAC.html">管理员</a>
                            </li>
                            <li><a href="SuperRBAC.html">IT运维员</a>
                            </li>
                            <li><a href="SuperRBAC.html">超级管理员</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">

                    <!--<div class="">-->
                        <!--<a onclick="fnClickAddRow();" href="javascript:void(0);" class="btn btn-primary ">添加权限</a>-->
                    <!--</div>-->

                    <div class="">
                        <a href="#" class="btn btn-primary ">普通用户</a>
                    </div>

                    <table class="table table-striped table-bordered table-hover dataTables-example" id="editable">
                        <thead>
                        <tr>
                            <th><input type="checkbox" id="checkAll"/></th>
                            <th>角色名称</th>
                            <th>拥有的权限</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="gradeX">
                            <td><input value="1" check='box' type="checkbox" /></td>
                            <td><div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked class="onoffswitch-checkbox" id="example1.1">
                                   	 	<label class="onoffswitch-label" for="example1.1">
                                        	<span class="onoffswitch-inner"></span>
                                        	<span class="onoffswitch-switch"></span>
                                    	</label>
                                	</div>
                            	</div>
                            </td>
                            <td><div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked class="onoffswitch-checkbox" id="example1.2">
                                    <label class="onoffswitch-label" for="example1.2">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div></td>
                            <td class="center"><div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked class="onoffswitch-checkbox" id="example1.3">
                                    <label class="onoffswitch-label" for="example1.3">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div></td>
                            <td class="center"><div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked class="onoffswitch-checkbox" id="example1.4">
                                    <label class="onoffswitch-label" for="example1.4">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div></td>
                        </tr>
                        <tr class="gradeC">
                            <td>发帖</td>
                            <td>
                                <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked class="onoffswitch-checkbox" id="example2.1">
                                    <label class="onoffswitch-label" for="example2.1">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                            </td>
                            <td> <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked class="onoffswitch-checkbox" id="example2.2">
                                    <label class="onoffswitch-label" for="example2.2">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div></td>
                            <td class="center"> <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked class="onoffswitch-checkbox" id="example2.3">
                                    <label class="onoffswitch-label" for="example2.3">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div></td>
                            <td class="center"> <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked class="onoffswitch-checkbox" id="example2.4">
                                    <label class="onoffswitch-label" for="example2.4">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div></td>
                        </tr>
                        <tr class="gradeA">
                            <td>知识库</td>
                            <td> <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked class="onoffswitch-checkbox" id="example3.1">
                                    <label class="onoffswitch-label" for="example3.1">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                            </td>
                            <td> <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked class="onoffswitch-checkbox" id="example3.2">
                                    <label class="onoffswitch-label" for="example3.2">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div></td>
                            <td class="center"> <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked class="onoffswitch-checkbox" id="example3.3">
                                    <label class="onoffswitch-label" for="example3.3">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div></td>
                            <td class="center"> <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked class="onoffswitch-checkbox" id="example3.4">
                                    <label class="onoffswitch-label" for="example3.4">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div></td>
                        </tr>

                        </tbody>
                        <!--<tfoot>-->
                        <!--<tr>-->
                            <!--<th>渲染引擎</th>-->
                            <!--<th>浏览器</th>-->
                            <!--<th>平台</th>-->
                            <!--<th>引擎版本</th>-->
                            <!--<th>CSS等级</th>-->
                        <!--</tr>-->
                        <!--</tfoot>-->
                    </table>

                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>



<script src="js/plugins/jeditable/jquery.jeditable.js"></script>

<!-- Data Tables -->
<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

<!-- 自定义js -->
<script src="js/content.js?v=1.0.0"></script>


<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {
        $('.dataTables-example').dataTable();

        /* Init DataTables */
//        var oTable = $('#editable').dataTable();

        /* Apply the jEditable handlers to the table */
        oTable.$('td').editable('../example_ajax.php', {
            "callback": function (sValue, y) {
                var aPos = oTable.fnGetPosition(this);
                oTable.fnUpdate(sValue, aPos[0], aPos[1]);
            },
            "submitdata": function (value, settings) {
                return {
                    "row_id": this.parentNode.getAttribute('id'),
                    "column": oTable.fnGetPosition(this)[2]
                };
            },

            "width": "90%",
            "height": "100%"
        });


    });

    function fnClickAddRow() {
        $('#editable').dataTable().fnAddData([
            "Custom row",
            "New row",
            "New row",
            "New row",
            "New row"]);

    }
</script>




</body>

</html>
