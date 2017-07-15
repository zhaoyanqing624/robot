<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>知识库</title>
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
							aria-expanded="true">知识库首页</a></li>
						<li class=""><a data-toggle="tab" href="#tab-2"
							aria-expanded="false">知识库管理</a></li>

					</ul>
					<div class="tab-content">

						<div id="tab-1" class="tab-pane active">

							<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!--360浏览器优先以webkit内核解析-->


<title>知识库首页</title>

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
									<div class="h1 text-info font-thin h1">0</div>
									<span class="text-muted text-xs">待审核FAQ</span>
									<div class="top text-right w-full">
										<i class="fa fa-caret-down text-warning m-r-sm"></i>
									</div>
								</div>
							</div>
							<div class="col-xs-6">
								<div class="panel padder-v item bg-info">
									<div class="h1 text-fff font-thin h1">${total}</div>
									<span class="text-muted text-xs">FAQ总数</span>
									<div class="top text-right w-full">
										<i class="fa fa-caret-down text-warning m-r-sm"></i>
									</div>
								</div>
							</div>
							
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-sm-9" style="padding-right: 0;">
						<div class="ibox float-e-margins">
							<div class="ibox-title"
								style="border-bottom: none; background: #fff;">
								<h5>配置变化</h5>
							</div>
							<div class="ibox-content" style="border-top: none;">
								<div id="pieChart" style="width:700px;height:700px;"></div>
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
													<li class=""><a data-toggle="tab" href="#tab-31"
														aria-expanded="true">待审核FAQ</a></li>
													<li class="active"><a data-toggle="tab" href="#tab-32"
														aria-expanded="false">FAQ</a></li>

												</ul>
												<div class="tab-content">

													<div id="tab-32" class="tab-pane active">
														<div class="ibox-content">






															<!-- BEGIN EXAMPLE TABLE PORTLET-->

															<div class="portlet box blue">

																<div class="portlet-title">

																	<div class="caption">
																		<i class="icon-edit"></i>FAQ信息列表
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


																				<th>FAQ分类</th>

																				<th>FAQ名称</th>

																				<th>FAQ关键词</th>

																				<th>操作</th>

																				<th>操作</th>

																				<th>查看FAQ详情</th>

																			</tr>

																		</thead>

																		<tbody>
																			<#list faqList as faqList>
																			<tr class="" id="${faqList.faqId}">

																				<td>${faqList.classifyName}</td>
																				<td>${faqList.faqTitle}</td>

																				<td class="center">${faqList.faqKeyWord}</td>



																				<td><a class="faq"
																					href="/org.xjtusicd3.portal/editFAQ.html?u=${faqList.faqId}">编辑</a></td>

																				<td><a onclick="deleteFAQ()">删除</a></td>

																				<td><a class="faqinfo"
																					href="/org.xjtusicd3.portal/showFAQ.html?u=${faqList.faqId}">查看FAQ信息</a></td>

																			</tr>
																			</#list>


																		</tbody>

																	</table>

																</div>

															</div>

															<!-- END EXAMPLE TABLE PORTLET-->


														</div>
													</div>

													<div id="tab-31" class="tab-pane">
														<div class="ibox-content">


															<!-- BEGIN PAGE CONTENT-->



															<!-- BEGIN EXAMPLE TABLE PORTLET-->

															<div class="portlet box blue">

																<div class="portlet-title">

																	<div class="caption">
																		<i class="icon-edit"></i>待审核FAQ信息列表
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

																				<th>FAQ分类</th>

																				<th>FAQ名称</th>

																				<th>FAQ关键词</th>

																				<th>操作</th>

																				<th>操作</th>

																				<th>操作</th>

																				<th>查看FAQ详情</th>

																			</tr>

																		</thead>

																		<tbody>
																			<#list allUserList as userlist2>
																			<tr class="">

																				<td>${userlist2.USERNAME}</td>
																				<td>${userlist2.USEREMAIL}</td>

																				<td class="center">${userlist2.USERADDRESS}</td>

																				<td><a class="userinfo"
																					href="/org.xjtusicd3.portal/editUserInformation.html?u=${userlist2.USERID}">添加至FAQ</a></td>

																				<td><a class="userinfo"
																					href="/org.xjtusicd3.portal/editUserInformation.html?u=${userlist2.USERID}">编辑</a></td>

																				<td><a onclick="deleteUser()">删除</a></td>

																				<td><a class="userinfo"
																					href="/org.xjtusicd3.portal/showUserInfo.html?u=${userlist2.USERID}">查看用户信息</a></td>

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
		function deleteFAQ() {
			var FAQID = event.target.parentNode.parentNode.id;
			var present_row = event.target.parentNode.parentNode;
			if (confirm("确认删除？")) {
				$.ajax({
					type : "post",
					url : "/org.xjtusicd3.portal/deleteFAQ.html",
					data : {
						"FAQID" : FAQID
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
	        	    title : {
	        	        text: '知识库FAQ占比',
	        	        subtext: '来自后台数据库',
	        	        x:'center'
	        	    },
	        	    tooltip : {
	        	        trigger: 'item',
	        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	        	    },
	        	    legend: {
	        	        orient: 'vertical',
	        	        left: 'left',
	        	        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
	        	    },
	        	    series : [
	        	        {
	        	            name: '所占百分比',
	        	            type: 'pie',
	        	            radius : '55%',
	        	            center: ['50%', '60%'],
	        	            data:${result},
	        	            itemStyle: {
	        	                emphasis: {
	        	                    shadowBlur: 10,
	        	                    shadowOffsetX: 0,
	        	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	        	                }
	        	            }
	        	        }
	        	    ]
	        	};
			myChart.setOption(option);
	</script>


</body>

</html>
