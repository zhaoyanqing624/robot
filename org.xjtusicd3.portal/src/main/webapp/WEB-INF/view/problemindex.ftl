<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>问题</title>
<meta name="keywords" content="">
<meta name="description" content="">

<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css?v=4.1.0" rel="stylesheet">
<!-- jqgrid-->
<link href="css/plugins/jqgrid/ui.jqgrid.css?0820" rel="stylesheet">

<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/bootstrap-responsive.min.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/font-awesome.min.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/style-metro.css" rel="stylesheet" type="text/css" />

<link href="media/css/style.css" rel="stylesheet" type="text/css" />

<link href="media/css/style-responsive.css" rel="stylesheet"
	type="text/css" />



<link href="media/css/uniform.default.css" rel="stylesheet"
	type="text/css" />

<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->

<link rel="stylesheet" type="text/css"
	href="media/css/select2_metro.css" />

<link rel="stylesheet" href="media/css/DT_bootstrap.css" />

<!-- END PAGE LEVEL STYLES -->

<link rel="shortcut icon" href="media/image/favicon.ico" />

<!-- echarts JS -->
	<script src="media/js/echarts.js"></script>

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeIn">

		<div class="row" style="height: 100%">
			<div class="col-sm-6" style="width: 100%; height: 100%">
				<div class="tabs-container">
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#tab-1"
							aria-expanded="true">问题首页</a></li>
						<li class=""><a data-toggle="tab" href="#tab-2"
							aria-expanded="false">问题管理</a></li>

					</ul>
					<div class="tab-content">

						<div id="tab-1" class="tab-pane active">
							<!DOCTYPE html>
							<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!--360浏览器优先以webkit内核解析-->


<title>问题首页</title>

<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div style="visibility: hidden;">
		您好：${UserName}
		</div>
		<div class="row">
			<div class="col-sm-10">
				<div class="row">
					<div class="col-sm-4">
						<div class="row row-sm text-center">
							<div class="col-xs-6">
								<div class="panel padder-v item">
									<div class="h1 text-info font-thin h1">0</div>
									<span class="text-muted text-xs">未处理问题</span>
									<div class="top text-right w-full">
										<i class="fa fa-caret-down text-warning m-r-sm"></i>
									</div>
								</div>
							</div>
							<div class="col-xs-6">
								<div class="panel padder-v item bg-info">
									<div class="h1 text-fff font-thin h1">8</div>
									<span class="text-muted text-xs">已处理问题</span>
									<div class="top text-right w-full">
										<i class="fa fa-caret-down text-warning m-r-sm"></i>
									</div>
								</div>
							</div>
							<div class="col-xs-6">
								<div class="panel padder-v item bg-primary">
									<div class="h1 text-fff font-thin h1">0</div>
									<span class="text-muted text-xs">今日未处理问题数</span>
									<div class="top text-right w-full">
										<i class="fa fa-caret-down text-warning m-r-sm"></i>
									</div>
								</div>
							</div>
							<div class="col-xs-6">
								<div class="panel padder-v item">
									<div class="font-thin h1">0</div>
									<span class="text-muted text-xs">今日已处理问题数</span>
									<div class="bottom text-left">
										<i class="fa fa-caret-up text-warning m-l-sm"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="ibox float-e-margins">
							<div class="ibox-title"
								style="border-bottom: none; background: #fff;">
								 
							</div>
							<div class="ibox-content" style="border-top: none;">
								<div id="lineChart" style="height: 217px;"></div>
							</div>
						</div>
					</div>
				</div>
				 

			</div>
			<div class="col-sm-2">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>问题列表</h5>
					</div>
					<div class="ibox-content">
						<ul class="todo-list m-t small-list ui-sortable">
							<li><a href="widgets.html#" class="check-link"><i
									class="fa fa-check-square"></i> </a> <span
								class="m-l-xs todo-completed">12312312312</span></li>
							<li><a href="widgets.html#" class="check-link"><i
									class="fa fa-check-square"></i> </a> <span
								class="m-l-xs  todo-completed">测试222222222222</span></li>
							<li><a href="widgets.html#" class="check-link"><i
									class="fa fa-check-square"></i> </a> <span
								class="m-l-xs todo-completed">测试11111111111</span></li>
							 
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 全局js -->
	<script src="js/jquery.min.js?v=2.1.4"></script>
	<script src="js/bootstrap.min.js?v=3.3.6"></script>
	<script src="js/plugins/layer/layer.min.js"></script>

	<!-- 自定义js -->
	<script src="js/content.js"></script>
	<!--flotdemo-->

</body>

							</html>


						</div>

						<div id="tab-2" class="tab-pane">


							<body class="gray-bg">
								<div class="wrapper wrapper-content animated fadeIn">

									<div class="row">
										<div class="col-sm-6" style="width: 100%">
											<div class="tabs-container">
												<ul class="nav nav-tabs">
													<li class="active"><a data-toggle="tab" href="#tab-31"
														aria-expanded="true">待处理问题</a></li>
													<li class=""><a data-toggle="tab" href="#tab-32"
														aria-expanded="false">已处理问题</a></li>

												</ul>
												<div class="tab-content">

													<div id="tab-31" class="tab-pane active">
														<div class="ibox-content">

															<!-- BEGIN EXAMPLE TABLE PORTLET-->

															<div class="portlet box blue">

																<div class="portlet-title">

																	<div class="caption">
																		<i class="icon-edit"></i>待处理问题列表
																	</div>

																	<div class="tools">

																		<a href="javascript:;" class="collapse"></a> <a
																			href="#portlet-config" data-toggle="modal"
																			class="config"></a> <a href="javascript:;"
																			class="reload"></a> <a href="javascript:;"
																			class="remove"></a>

																	</div>

																</div>

																<div class="portlet-body">

																	<!-- <div class="clearfix">

														<div class="btn-group">

															<button class="btn green"
																onclick="window.location='addUserInformation.html'">

																<i class="icon-plus">增加FAQ</i>

															</button>

														</div>

												 	<div class="btn-group pull-right">

										<button class="btn dropdown-toggle" data-toggle="dropdown">Tools <i class="icon-angle-down"></i>

										</button>

										<ul class="dropdown-menu pull-right">

											<li><a href="#">Print</a></li>

											<li><a href="#">Save as PDF</a></li>

											<li><a href="#">Export to Excel</a></li>

										</ul>

									</div> 

													</div> -->

																	<table
																		class="table table-striped table-hover table-bordered"
																		id="sample_editable_1">

																		<thead>

																			<tr>


																				<th>问题名称</th>

																				<th>问题类别</th>

																				<th>提问用户</th>

																				<th>提问时间</th>

																				<th>操作</th>

																				<th>查看详情</th>

																			</tr>

																		</thead>

																		<tbody>
																			<#list cqlist1 as cqlist>
																			<tr class="" id = "${cqlist.problemId}">

																				<td>${cqlist.problemTitle}</td>
																				<td>${cqlist.problemClassify}</td>

																				<td class="center">${cqlist.problemUser}</td>

																				<td class="center">${cqlist.problemTime}</td>

															 

																				<td><a onclick="deleteUser()">删除</a></td>

																				<td><a class="faq"
																					href="/org.xjtusicd3.portal/showProblem.html?u=${cqlist.problemId}">查看问题详情</a></td>

																			</tr>
																			</#list>


																		</tbody>

																	</table>

																</div>

															</div>

															<!-- END EXAMPLE TABLE PORTLET-->


														</div>
													</div>

													<div id="tab-32" class="tab-pane">
														<div class="ibox-content">


															<!-- BEGIN PAGE CONTENT-->



															<!-- BEGIN EXAMPLE TABLE PORTLET-->

															<div class="portlet box blue">

																<div class="portlet-title">

																	<div class="caption">
																		<i class="icon-edit"></i>已处理问题列表
																	</div>

																	<div class="tools">

																		<a href="javascript:;" class="collapse"></a> <a
																			href="#portlet-config" data-toggle="modal"
																			class="config"></a> <a href="javascript:;"
																			class="reload"></a> <a href="javascript:;"
																			class="remove"></a>

																	</div>

																</div>

																<div class="portlet-body">



																	<table
																		class="table table-striped table-hover table-bordered"
																		id="sample_editable_2">

																		<thead>

																			<tr>

																				<th>问题名称</th>

																				<th>问题类别</th>

																				<th>提问用户</th>

																				<th>提问时间</th>

																				<th>操作</th>

																				<th>操作</th>
																				
																				<th>操作</th>

																				<th>查看详情</th>

																			</tr>

																		</thead>

																		<tbody>
																			<#list cqlist as cqlist>
																			<tr class="" id = "${cqlist.problemId}">

																				<td>${cqlist.problemTitle}</td>
																				<td>${cqlist.problemClassify}</td>

																				<td class="center">${cqlist.problemUser}</td>

																				<td class="center">${cqlist.problemTime}</td>

																				<td><a class="faq"
																					href="/org.xjtusicd3.portal/editUserInformation.html?u=${communityquestionlist.USERID}">编辑</a></td>

																				<td><a onclick="deleteUser()">删除</a></td>

																				<td><a onclick="addProblemtoFAQ()">加入至FAQ</a></td>

																				<td><a class="faq" 
																					href="/org.xjtusicd3.portal/showProblemInfo.html?u=${cqlist.problemId}">查看问题详情</a></td>

																			</tr>
																			</#list>

																		</tbody>

																	</table>

																</div>

															</div>

															<!-- END EXAMPLE TABLE PORTLET-->

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
							</body>
</html>

</div>


</div>
</div>
</div>
</div>
</div>
<!-- 全局js -->
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>

<!-- Flot -->
<script src="js/plugins/flot/jquery.flot.js"></script>
<script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="js/plugins/flot/jquery.flot.resize.js"></script>
<script src="js/plugins/flot/jquery.flot.pie.js"></script>

<!-- 自定义js -->
<script src="js/content.js?v=1.0.0"></script>


<!-- BEGIN CORE PLUGINS -->

<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

<script src="media/js/jquery-migrate-1.2.1.min.js"
	type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

<script src="media/js/jquery-ui-1.10.1.custom.min.js"
	type="text/javascript"></script>

<script src="media/js/bootstrap.min.js" type="text/javascript"></script>

<script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>

<script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>

<script src="media/js/jquery.uniform.min.js" type="text/javascript"></script>

<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL PLUGINS -->

<script type="text/javascript" src="media/js/select2.min.js"></script>

<script type="text/javascript" src="media/js/jquery.dataTables.js"></script>

<script type="text/javascript" src="media/js/DT_bootstrap.js"></script>

<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->

<script src="media/js/app.js"></script>

<script src="media/js/table-editable.js"></script>
<script src="media/js/table-editable2.js"></script>
<script>
	jQuery(document).ready(function() {

		App.init();

		TableEditable.init();
		TableEditable2.init();

	});
</script>

<script type="text/javascript">
	function addProblemtoFAQ() {
		var problemID = event.target.parentNode.parentNode.id;
		alert(problemID)
		var present_row = event.target.parentNode.parentNode;
		if (confirm("确认添加至FAQ？")) {
			$.ajax({
				type : "post",
				url : "/org.xjtusicd3.portal/addProblemToFAQ.html",
				data : {
					"problemID" : problemID
				},
				dataType : "json",
				success : function(data) {
					alert("添加成功");
					present_row.remove();
				}
			});
		} else {
			return;
		}

	}
</script>

<script type="text/javascript">
	function deleteUser() {
		var userEmail = event.target.parentNode.parentNode.children[1].innerHTML;
		var present_row = event.target.parentNode.parentNode;
		if (confirm("确认删除？")) {
			$.ajax({
				type : "post",
				url : "/org.xjtusicd3.portal/deleteUser.html",
				data : {
					"userEmail" : userEmail
				},
				dataType : "json",
				success : function(data) {
					alert("删除成功");
					present_row.remove();
				}
			});
		} else {
			return;
		}

	}
</script>


	<script type="text/javascript">
		 
	        var myChart = echarts.init(document.getElementById('pieChart'));
			
			var option = {
				backgroundColor : '#2c343c',

				title : {
					text : '各FAQ占比',
					left : 'center',
					top : 20,
					textStyle : {
						color : '#ccc'
					}
				},

				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},

				visualMap : {
					show : false,
					min : 80,
					max : 600,
					inRange : {
						colorLightness : [ 0, 1 ]
					}
				},
				series : [ {
					name : '访问来源',
					type : 'pie',
					radius : '55%',
					center : [ '50%', '50%' ],
					data : ${result}.sort(function(a, b) {
						return a.value - b.value;
					}),
					roseType : 'radius',
					label : {
						normal : {
							textStyle : {
								color : 'rgba(255, 255, 255, 0.3)'
							}
						}
					},
					labelLine : {
						normal : {
							lineStyle : {
								color : 'rgba(255, 255, 255, 0.3)'
							},
							smooth : 0.2,
							length : 10,
							length2 : 20
						}
					},
					itemStyle : {
						normal : {
							color : '#c23531',
							shadowBlur : 200,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						}
					},

					animationType : 'scale',
					animationEasing : 'elasticOut',
					animationDelay : function(idx) {
						return Math.random() * 200;
					}
				} ]
			};
		 
			myChart.setOption(option);
	</script>


	<script type="text/javascript">
		 
	        var myChart = echarts.init(document.getElementById('lineChart'));
			
			var option = {
				    title: {
				        text: '近七日问题数'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['未处理问题','已处理问题']
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    toolbox: {
				        feature: {
				            saveAsImage: {}
				        }
				    },
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        data: ['6.26','6.27','6.28','6.29','6.30','7.1','昨日']
				    },
				    yAxis: {
				        type: 'value'
				    },
				    series: [
				        {
				            name:'未处理问题',
				            type:'line',
				            stack: '总量',
				            data:[0, 0, 0, 0,0,  0, 0]
				        },
				        {
				            name:'已处理问题',
				            type:'line',
				            stack: '总量',
				            data:[0,0, 1, 0, 1, 4,2]
				        }, 
				    ]
				};

		 
			myChart.setOption(option);
	</script>


</body>

</html>
