
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>智能小朵 </title>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <script src="js/login_bg/jquery-1.9.1.js"></script>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="new/front/style/reset2.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util2.css" />
    <link href="css/main.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="zhao/upload/cropper/cropper.min.css" rel="stylesheet">
	<link href="zhao/upload/sitelogo/sitelogo.css" rel="stylesheet">
    <link href="zhao/radio&check/css/labelauty.css" rel="stylesheet" />
    <link href="zhao/password/css/gloab.css" rel="stylesheet">
	<link href="zhao/password/css/index.css" rel="stylesheet">
	<script src="zhao/upload/cropper/cropper.min.js"></script>
	<script src="zhao/upload/sitelogo/sitelogo.js"></script>
	<script src="zhao/upload/bootstrap/js/bootstrap.min.js"></script>
	<script src="zhao/password/js/register.js"></script>
	<script type="text/javascript" src="js/view/personal3.js"></script>
	<link type="text/css" rel="stylesheet" href="zhao/data/jedate/skin/jedate.css">
	<style>
		.datainp { width: 128px; height: 35px; border: 1px #A5D2EC solid; }
		.datep { margin-bottom: 40px; line-height: 24px; margin-right: 15px; }
		.wicon { background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABkAAAAQCAYAAADj5tSrAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABZ0RVh0Q3JlYXRpb24gVGltZQAwNi8xNS8xNGnF/oAAAAAcdEVYdFNvZnR3YXJlAEFkb2JlIEZpcmV3b3JrcyBDUzVxteM2AAAAoElEQVQ4jWPceOnNfwYqAz9dYRQ+E7UtwAaGjyUsDAyYYUgJ2HT5LXZLcEmSCnA6duOlN///////H0bDALl8dPH/////Z8FuNW6Qtvw2nL3lyjsGBgYGhlmRqnj1kGwJuqHIlhJlCXq8EOITEsdqCXLEbbr8FisfFkTo+vBZRFZwERNEFFkCiw90nxJtCalxQmzegltCzVyP1RJq5HZ8AABuNZr0628DMwAAAABJRU5ErkJggg=="); background-repeat: no-repeat; background-position: right center;    margin-left: -13px; }
		.showcase-form textarea .ng-dirty.ng-invalid,
		.showcase-form select .ng-dirty.ng-invalid,
		.showcase-form input.ng-dirty.ng-invalid {
   		 border-color: #e9322d;
    	-webkit-box-shadow: 0 0 6px #f8b9b7;
    	-moz-box-shadow: 0 0 6px #f8b9b7;
    	box-shadow: 0 0 6px #f8b9b7;
	</style>
</head>
<body>
	<!-- 头部开始 -->
	<div class="header" id="head">      
        <#include "inc/incTop.ftl">
       	<div class="headContent">
    		<div class="headTop clearfix">
	        	<a href="" class="logoCon">
	            	<img src="images/logo.jpg" class="logo">
	            	<span>设备信息${macAddress}</span>
	        	</a>
    		</div>
		</div>
    </div>
     <!-- 头部结束 -->
     
	<!-- 主体开始 -->
    <div class="mainContent">
        <div class="contentWra clearfix">
        	<section id="shortcodes">
        		<div class="container">
            		<div id="tab-container">
                		<div class="row">
                    		<div class="col-md-12"></div>
                    		<div class="col-md-6" style="width:100%;">
                        		<ul id="tab2" class="nav nav-pills">
                            		<li class="active"><a href="#tab2-item1" data-toggle="tab">电脑PC</a></li>
                        		</ul>
                        		<div class="tab-content" style="margin-left: 165px;margin-top: 30px;width: 1000px;float: left;">
                            		<div class="tab-pane fade active in" id="tab2-item1" style="width: 889px;float: left;">
										<section id="portfolio-information" class="padding-top">
							        		<div class="container">
							            		<div class="row">
							                		<div class="col-sm-6" style="width:35%">
														<img src="images/portfolio-details/hp8180.jpg" class="img-responsive" alt="">
							                		</div>
							                		<div class="col-sm-6">
													<#list personal3_list as list>
							                    		<div class="project-name overflow">
							                        		<h2 class="bold">${list.EQUIPMENTMODEL}</h2>
									                        <ul class="nav navbar-nav navbar-default">
									                            <li style="width:165px;margin-top: 16px;"><i class="fa fa-clock-o"></i><span>时间：${list.EQUIPMENTTIME}</span></li>
									                        </ul>
							                    		</div>
									                    
									                    <div class="project-info overflow">
									                        <h3>硬件信息：</h3>
									                        <ul class="elements">
									                            <li><i class="fa fa-angle-right"></i> 处理器：${list.CPU}</li>
									                            <li><i class="fa fa-angle-right"></i> 内存（RAM）：${list.RAM}</li>
									                            <li><i class="fa fa-angle-right"></i> 硬盘：${list.HARDDRIVER}</li>
									                            <li><i class="fa fa-angle-right"></i> 网卡：${list.NETWORKCARD}</li>
									                            <#if list.NETWORKCARD2 ??>
									                            <li><i class="fa fa-angle-right"></i> 无线网卡：${list.NETWORKCARD2}</li>
									                            </#if>
									                            <li><i class="fa fa-angle-right"></i> 主板：${list.MOTHERBOARD}</li>
									                            <li><i class="fa fa-angle-right"></i> 系统名称：${list.OS}</li>
									                            
									                        </ul>
									                    </div>
							                   	
									                    <div class="skills overflow" style="height:78px;" id="buding">
									                        <h3>补丁信息：</h3>
									                        <ul class="nav navbar-nav navbar-default">
									                        	<a onclick="zhankai()" style="color:blue;margin-left:10px;" id="zhankaiall">展开</a>
									                        	<#list list.patchViews as patchView>
									                            <li style="float:left"><image src="ico/system.ico"></image><span>${patchView.CONFIGURENAME}</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: #78ba32;"></span></li><br/>
									                        	</#list>
									                        </ul>
									                    </div>
							                    
									                    <div class="client overflow" style="height:78px;" id="ruanjian">
									                        <h3>软件信息：</h3>
									                        <ul class="nav navbar-nav navbar-default">
									                        	<a onclick="zhankai2()" style="color:blue;margin-left:10px;" id="zhankaiall2">展开</a>
									                        	<#list list.softViews as softView>
									                            <li style="float:left"><image src="ico/Python.ico"></image><span>${softView.CONFIGURENAME}</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: #78ba32;">${softView.CONFIGUREVERSION}</span></li><br/>
									                       		</#list>
									                        </ul>
									                    </div>
							                		</#list>  
							                		</div>
							            		</div>
							        		</div>
							    		</section>
                        			</div>    	
                        		</div>
                    		</div>
               			 </div>
            		</div><!--/#table-container-->
            
            		<div class="padding"></div>
        		</div>
    		</section>
		</div>     		
    </div>
    <!-- 主体结束 -->
    
    <!-- 底部开始 -->
	<#include "/inc/incFoot.ftl">
	<!-- 底部结束 -->
    	
    	<script type="text/javascript" src="js/jquery.js"></script>
    	<script type="text/javascript" src="new/front/js/util.js"></script>
		<script src="zhao/radio&check/js/labelauty.js"></script>
    <script>
        $(document).ready(function () {
            $(".rdolist").labelauty("rdolist", "rdo");
            $(".chklist").labelauty("chklist", "check");
        });
    </script>  
	<script src="http://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>
	<script src="zhao/address/js/distpicker.data.js"></script>
	<script src="zhao/address/js/distpicker.js"></script>
	<script src="zhao/address/js/main.js"></script> 
	<script type="text/javascript" src="zhao/data/jedate/jquery.jedate.js"></script>
	<script type="text/javascript" src="zhao/data/jedate/data.js"></script>
	<script type="text/javascript" src="js/my.js"></script>
</body>
</html>
