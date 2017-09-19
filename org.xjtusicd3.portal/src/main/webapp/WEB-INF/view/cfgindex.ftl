<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>配置</title>
<meta name="keywords" content="">
<meta name="description" content="">

<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css?v=4.1.0" rel="stylesheet">


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
							aria-expanded="true">配置首页</a></li>
						<li class=""><a data-toggle="tab" href="#tab-2"
							aria-expanded="false">标准配置资源</a></li>	
						<li class=""><a data-toggle="tab" href="#tab-3"
							aria-expanded="false">设备资源</a></li>
						<li class=""><a data-toggle="tab" href="#tab-4"
							aria-expanded="false">系统配置</a></li>

					</ul>
					<div class="tab-content">

						<div id="tab-1" class="tab-pane active">
							<!DOCTYPE html>
							<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!--360浏览器优先以webkit内核解析-->


<title>配置首页</title>

<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">

<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-10">
				<div class="row">
					<div class="col-sm-4">
						<div class="row row-sm text-center">
							<div class="col-xs-6">
								<div class="panel padder-v item">
									<div class="h1 text-info font-thin h1">2</div>
									<span class="text-muted text-xs" style="color:black">计算机</span>
									<div class="top text-right w-full">
										<i class="fa fa-caret-down text-warning m-r-sm"></i>
									</div>
								</div>
							</div>
							<div class="col-xs-6">
								<div class="panel padder-v item bg-info">
									<div class="h1 text-fff font-thin h1">0</div>
									<span class="text-muted text-xs">服务器</span>
									<div class="top text-right w-full">
										<i class="fa fa-caret-down text-warning m-r-sm"></i>
									</div>
								</div>
							</div>
							<div class="col-xs-6">
								<div class="panel padder-v item bg-primary">
									<div class="h1 text-fff font-thin h1">184751</div>
									<span class="text-muted text-xs">系统配置</span>
									<div class="top text-right w-full">
										<i class="fa fa-caret-down text-warning m-r-sm"></i>
									</div>
								</div>
							</div>
							<div class="col-xs-6">
								<div class="panel padder-v item">
									<div class="font-thin h1">117</div>
									<span class="text-muted text-xs" style="color:black">已配置系统资源</span>
									<div class="bottom text-left">
										<i class="fa fa-caret-up text-warning m-l-sm"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-8">
						<div class="ibox float-e-margins" >
							<div class="ibox-title"
								style="border-bottom: none; background: #fff;">
								<h5>设备资源使用比</h5>
							</div>
							<div class="ibox-content" style="border-top: none;">
								<div id="resource1" style="width:300px;height:300px;"></div>
							</div>
							
						</div>
						 
					</div>
				</div>
				<div class="row">
					<div class="col-sm-9" style="padding-right: 0;">
						<div class="ibox float-e-margins">
							<div class="ibox-title"
								style="border-bottom: none; background: #fff;">
								<h5>已配置系统资源</h5>
							</div>
							<div class="ibox-content" style="border-top: none;">
								<div id="pieChart" style="width:500px;height:500px;"></div>
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
	<script src="js/plugins/layer/layer.min.js"></script>

	<!-- 自定义js -->
	<script src="js/content.js"></script>
	<!--flotdemo-->

</body>

							</html>
						</div>

						<div id="tab-2" class="tab-pane">

							<div class="wrapper wrapper-content  animated fadeInRight">
								<div class="row">
									<div class="col-sm-12">
										<div class="ibox ">
											<div class="ibox-title">
												<h5>系统配置</h5>
											</div>
											<div class="ibox-content">

												<div class="jqGrid_wrapper">


													<!-- BEGIN EXAMPLE TABLE PORTLET-->

													<div class="portlet box blue">

														<div class="portlet-title">

															<div class="caption">
																<i class="icon-edit"></i>系统配置列表
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
																id="sample_editable_3">

																<thead>

																	<tr>


																		<th>配置名称</th>

																		<th>配置类型</th>

																		<th>URL</th>

																		<th>配置时间</th>

																		<th>操作</th>

																		<th>操作</th>

																		<th>查看配置详情</th>

																	</tr>

																</thead>

																<tbody>
																	<#list cfgList as cfgList>
																	<tr class="">

																		<td>${cfgList.CONFIGURENAME}</td>
																		<td>${cfgList.CONFIGURETYPE}</td>

																		<td class="center">${cfgList.URL}</td>

																		<td class="center">${cfgList.CONFIGURETIME}</td>

																		<td><a class="faq"
																			href="/org.xjtusicd3.portal/editUserInformation.html?u=${cfgList.USERID}">编辑</a></td>

																		<td><a onclick="deleteUser()">删除</a></td>

																		<td><a class="faq"
																			href="/org.xjtusicd3.portal/showUserInfo.html?u=${cfgList.USERID}">查看配置信息</a></td>

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
							</div>





						</div>

						<div id="tab-3" class="tab-pane">

							<div class="wrapper wrapper-content animated fadeIn">

								<div class="row">
									<div class="col-sm-6" style="width: 100%">
										<div class="tabs-container">
											<ul class="nav nav-tabs">
												<li class="active"><a data-toggle="tab" href="#tab-21"
													aria-expanded="true">计算机</a></li>
												<li class=""><a data-toggle="tab" href="#tab-22"
													aria-expanded="false">服务器</a></li>

											</ul>
											<div class="tab-content">
												<div id="tab-21" class="tab-pane active">
													<div class="ibox-content">

														<div class="jqGrid_wrapper">



															<!-- BEGIN EXAMPLE TABLE PORTLET-->

															<div class="portlet box blue">

																<div class="portlet-title">

																	<div class="caption">
																		<i class="icon-edit"></i>计算机配置列表
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

																	<div class="clearfix">

																		<div class="btn-group">

																			<button class="btn green"
																				onclick="window.location='addUserInformation.html'">

																				<i class="icon-plus">增加计算机设备</i>

																			</button>

																		</div>

																	</div>

																	<table
																		class="table table-striped table-hover table-bordered"
																		id="sample_editable_1">

																		<thead>

																			<tr>


																				<th>计算机设备ID</th>

																				<th>显卡</th>

																				<th>声卡</th>

																				<th>操作</th>

																				<th>操作</th>

																				<th>查看计算机设备详情</th>

																			</tr>

																		</thead>

																		<tbody>
																			<#list computerList as computerList>
																			<tr class="">

																				<td>${computerList.EQUIPMENTID}</td>
																				<td>${computerList.GRAPHICCARD}</td>

																				<td class="center">${computerList.AUDIOCARD}</td>



																				<td><a class="faq"
																					href="/org.xjtusicd3.portal/editUserInformation.html?u=${computerList.EQUIPMENTID}">编辑</a></td>

																				<td><a onclick="deleteCfg()">删除</a></td>

																				<td><a class="faq"
																					href="/org.xjtusicd3.portal/showUserInfo.html?u=${computerList.EQUIPMENTID}">查看设备信息</a></td>

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
												<div id="tab-22" class="tab-pane">
													<div class="ibox-content">

														<div class="jqGrid_wrapper">



															<!-- BEGIN EXAMPLE TABLE PORTLET-->

															<div class="portlet box blue">

																<div class="portlet-title">

																	<div class="caption">
																		<i class="icon-edit"></i>服务器配置列表
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

																	<div class="clearfix">

																		<div class="btn-group">

																			<button class="btn green"
																				onclick="window.location='addUserInformation.html'">

																				<i class="icon-plus">增加服务器设备</i>

																			</button>

																		</div>

																		<!-- 	<div class="btn-group pull-right">

										<button class="btn dropdown-toggle" data-toggle="dropdown">Tools <i class="icon-angle-down"></i>

										</button>

										<ul class="dropdown-menu pull-right">

											<li><a href="#">Print</a></li>

											<li><a href="#">Save as PDF</a></li>

											<li><a href="#">Export to Excel</a></li>

										</ul>

									</div> -->

																	</div>

																	<table
																		class="table table-striped table-hover table-bordered"
																		id="sample_editable_2">

																		<thead>

																			<tr>


																				<th>服务器设备ID</th>

																				<th>BIOS</th>

																				<th>AVTIVEUSER</th>

																				<th>操作</th>

																				<th>操作</th>

																				<th>查看服务器设备详情</th>

																			</tr>

																		</thead>

																		<tbody>
																			<#list serverList as serverList>
																			<tr class="">

																				<td>${serverList.classifyName}</td>
																				<td>${serverList.faqTitle}</td>

																				<td class="center">${serverList.faqKeyWord}</td>



																				<td><a class="faq"
																					href="/org.xjtusicd3.portal/editUserInformation.html?u=${serverList.USERID}">编辑</a></td>

																				<td><a onclick="deleteUser()">删除</a></td>

																				<td><a class="faq"
																					href="/org.xjtusicd3.portal/showUserInfo.html?u=${serverList.USERID}">查看服务器设备信息</a></td>

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
									</div>


								</div>





							</div>




						</div>

						<div id="tab-4" class="tab-pane">

							<div class="wrapper wrapper-content  animated fadeInRight">
								<div class="row">
									<div class="col-sm-12">
										<div class="ibox ">
											<div class="ibox-title">
												<h5>系统配置</h5>
											</div>
											<div class="ibox-content">

												<div class="jqGrid_wrapper">


													<!-- BEGIN EXAMPLE TABLE PORTLET-->

													<div class="portlet box blue">

														<div class="portlet-title">

															<div class="caption">
																<i class="icon-edit"></i>系统配置列表
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
																id="sample_editable_4">

																<thead>

																	<tr>


																		<th>配置名称</th>

																		<th>配置类型</th>

																		<th>URL</th>

																		<th>配置时间</th>

																		<th>操作</th>

																		<th>操作</th>

																		<th>查看配置详情</th>

																	</tr>

																</thead>

																<tbody>
																	<#list cfgList as cfgList>
																	<tr class="">

																		<td>${cfgList.CONFIGURENAME}</td>
																		<td>${cfgList.CONFIGURETYPE}</td>

																		<td class="center">${cfgList.URL}</td>

																		<td class="center">${cfgList.CONFIGURETIME}</td>

																		<td><a class="faq"
																			href="/org.xjtusicd3.portal/editUserInformation.html?u=${cfgList.USERID}">编辑</a></td>

																		<td><a onclick="deleteUser()">删除</a></td>

																		<td><a class="faq"
																			href="/org.xjtusicd3.portal/showUserInfo.html?u=${cfgList.USERID}">查看配置信息</a></td>

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
	<script src="media/js/table-editable3.js"></script>
	<script src="media/js/table-editable4.js"></script>
	<script>
		jQuery(document).ready(function() {

			App.init();

			TableEditable.init();
			TableEditable2.init();
			TableEditable3.init();
			TableEditable4.init();
		});
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
			    tooltip: {
			        trigger: 'item',
			        formatter: "{a} <br/>{b}: {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        x: 'left',
			        data:['补丁','驱动','软件']
			    },
			    series: [
			        {
			            name:'详情',
			            type:'pie',
			            radius: ['40%', '50%'],
			            avoidLabelOverlap: false,
			            label: {
			                normal: {
			                    show: false,
			                    position: 'center'
			                },
			                emphasis: {
			                    show: true,
			                    textStyle: {
			                        fontSize: '30',
			                        fontWeight: 'bold'
			                    }
			                }
			            },
			            labelLine: {
			                normal: {
			                    show: false
			                }
			            },
			            data:[
			                {value:25, name:'补丁'},
			                {value:5, name:'驱动'},
			                {value:187, name:'软件'},
			                 
			            ]
			        }
			    ]
			};
		 
			myChart.setOption(option);
	</script>



	<script type="text/javascript">
		 
	        var myChart = echarts.init(document.getElementById('resource1'));
			
			var option = {
				    tooltip : {
				        formatter: "{a} <br/>{b} : {c}%"
				    },
				    toolbox: {
				        feature: {
				            restore: {},
				            saveAsImage: {}
				        }
				    },
				    series: [
				        {
				            name: '业务指标',
				            type: 'gauge',
				            detail: {formatter:'{value}%'},
				            data: [{value: 100, name: '使用率'}]
				        }
				    ]
				};

				setInterval(function () {
				     
				    myChart.setOption(option, true);
				},2000);
		 
			myChart.setOption(option);
	</script>
 

</body>

</html>
