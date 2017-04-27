
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
	<div class="header" id="head">      
        <div class="loginRegistHead" role="banner">
            <div class="content clearfix">
                <div class="header_top_wrap_left">
		            <ul>
		                <li><a class="new_a" href="robot.html" data-pos="categorys_1_1">智能小朵</a></li>
		                <li><a class="new_a" href="faq.html" data-pos="categorys_1_1">知识库</a></li>
		                <li><a class="new_a" href="question.html" data-pos="categorys_1_1">问题中心</a></li>
		                <li>
		                    <a class="new_a" href="service.html">关于我们</a>
		                </li>
		            </ul> 
                </div>
                <div class="header_top_wrap_right">
		            <ul>
		              <#if UserEmail??>
		                <div class="unlogin">
		                    <li class="loginLinkLi"><span class="person_icon"></span></li>
		                    <li class="loginLinkLi" id="userNameText">您好：${UserEmail}</li>
		                    <li class="left_margin my_center loginLinkLi" id="my_center" onmouseover="Util.showPersonCenter()" onmouseout="Util.hidePersonCenter()">个人中心<span class="v_center_arrow"></span>
		                        <div class="my_service_list" style="display: none; height: 116px; padding-top: 0px; margin-top: 0px; padding-bottom: 0px; margin-bottom: 0px;">
		                            <div class="top_icon"></div>
		                            <ul class="ul_list">
		                                <li><a href="personal.html">个人信息</a></li>
		                                <li><a href="personal3.html">我的设备</a></li>
		                                <li><a href="personal2.html">我的主页</a></li>
		                                <li><a href="notice.html">消息通知</a></li>
		                            </ul>
		                        </div>
		                    </li>
		                    <li class="left_margin loginLinkLi"><a href="loginout.html" id="headExit">退出</a>
		                    </li>
		                </div>
		             <#else>
				       	<div class="unlogin">
		                    <li class="unloginLinkLi">
		                        <a href="login.html" id="headLogin" class="listen_btn" data-pos="categorys_1_2">登录/注册</a>
		                        </li>
		                    </li>
		                </div>
		             </#if>
		            </ul> 
                </div>
            </div>
        </div>
       	<div class="headContent">
    		<div class="headTop clearfix">
	        	<a href="" class="logoCon">
	            	<img src="images/logo.jpg" class="logo">
	            	<span>个人信息</span>
	        	</a>
    		</div>
		</div>
    </div>
    <div class="mainContent">
        <div class="contentWra clearfix">
        	<section id="shortcodes">
        <div class="container">
            <div id="tab-container">
                <div class="row">
                    <div class="col-md-12">
                    </div>
                    <div class="col-md-6" style="width:100%;">
                        <ul id="tab2" class="nav nav-pills">
                            <li class="active"><a href="#tab2-item1" data-toggle="tab">服务器</a></li>
                            <li><a href="#tab2-item2" data-toggle="tab">电脑设备</a></li>
                        </ul>
                        <div class="tab-content" style="margin-left: 165px;margin-top: 30px;width: 1000px;float: left;">
                            <div class="tab-pane fade active in" id="tab2-item1" style="width: 889px;float: left;">
								<section id="portfolio-information" class="padding-top">
							        <div class="container">
							            <div class="row">
							                <div class="col-sm-6" style="width:35%">
							                    <img src="images/portfolio-details/RH2288.jpg" class="img-responsive" alt="">
							                </div>
							                <div class="col-sm-6">
							                    <div class="project-name overflow">
							                        <h2 class="bold">华为FusionServer RH2288 V2 </h2>
							                        <ul class="nav navbar-nav navbar-default">
							                            <li style="width:165px;margin-top: 16px;"><i class="fa fa-clock-o"></i><span>时间：2016.12.26</span></li>
							                            <li><a href="#"><i class="fa fa-tag"></i>编号：A1454641232654</a></li>
							                        </ul>
							                    </div>
							                    <div class="project-info overflow">
							                        <h3>硬件信息：</h3>
							                        <ul class="elements">
							                            <li><i class="fa fa-angle-right"></i> 处理器：Xeon E5-2620 v2</li>
							                            <li><i class="fa fa-angle-right"></i> 内存（RAM）：8,223MB</li>
							                            <li><i class="fa fa-angle-right"></i> 存储：300GB</li>
							                            <li><i class="fa fa-angle-right"></i> 主板：6×PCI-E 3.0</li>
							                            <li><i class="fa fa-angle-right"></i> 网络控制器：集成4个千兆GE网口</li>
							                            <li><i class="fa fa-angle-right"></i> 安全认证：CCC/CE/UL/FCC认证</li>
							                        </ul>
							                    </div>
							                </div>
							            </div>
							        </div>
							    </section>
                        	</div>
                        	
                            <div class="tab-pane fade" id="tab2-item2" style="width: 800px;float: left;">
 									<section id="portfolio-information" class="padding-top">
							        <div class="container">
							            <div class="row">
							                <div class="col-sm-6" style="width:35%">
							                    <img src="images/portfolio-details/hp8180.jpg" class="img-responsive" alt="">
							                </div>
							                <div class="col-sm-6">
							                    <div class="project-name overflow">
							                        <h2 class="bold">HP Compaq 8180 Elite CMT PC </h2>
							                        <ul class="nav navbar-nav navbar-default">
							                            <li style="width:165px;margin-top: 16px;"><i class="fa fa-clock-o"></i><span>时间：2016.12.25</span></li>
							                            <li><a href="#"><i class="fa fa-tag"></i>编号：4CV0515KJ0</a></li>
							                        </ul>
							                    </div>
							                    <div class="project-info overflow">
							                        <h3>硬件信息：</h3>
							                        <ul class="elements">
							                            <li><i class="fa fa-angle-right"></i> 处理器：Inter64 Family 6 Model 30 Stepping 5 GenuineTntel ~2933Mhz</li>
							                            <li><i class="fa fa-angle-right"></i> 内存（RAM）：12,223MB</li>
							                            <li><i class="fa fa-angle-right"></i> 网卡：Inter<R> 82578DM Gigabit Network Connection</li>
							                            <li><i class="fa fa-angle-right"></i> BIOS：Hewlett-Packard 786H1 v01.05,2010/6/9</li>
							                            <li><i class="fa fa-angle-right"></i> 系统名称：Microsoft Windows 7 旗舰版</li>
							                            <li><i class="fa fa-angle-right"></i> 系统ID：00426-OEM-8992662-00400</li>
							                            
							                        </ul>
							                    </div>
							                    <div class="skills overflow" style="height:78px;" id="buding">
							                        <h3>补丁信息：</h3>
							                        <ul class="nav navbar-nav navbar-default">
							                        	<a onclick="zhankai()" style="color:blue;margin-left:10px;" id="zhankaiall">展开</a>
							                            <li style="float:left"><image src="ico/system.ico"></image><span>KB2849697</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: #78ba32;">3.5.150.0</span></li><br/>
							                            <li style="float:left"><image src="ico/system.ico"></image><span>KB2849697</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">8.5.18600.0</span></li><br/>
							                            <li style="float:left"><image src="ico/system.ico"></image><span>KB2849697</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: #78ba32;">7.2.1</span></li><br/>
							                        </ul>
							                    </div>
							                    <div class="client overflow" style="height:78px;" id="ruanjian">
							                        <h3>软件信息：</h3>
							                        <ul class="nav navbar-nav navbar-default">
							                        	<a onclick="zhankai2()" style="color:blue;margin-left:10px;" id="zhankaiall2">展开</a>
							                            <li style="float:left"><image src="ico/Python.ico"></image><span>Python 3.5.0 Utility Scripts<64-bit></span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: #78ba32;">3.5.150.0</span></li><br/>
							                            <li style="float:left"><image src="ico/qq.ico"></image><span>腾讯QQ</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">8.5.18600.0</span></li><br/>
							                            <li style="float:left"><image src="ico/CAJViewer.ico"></image><span>CAJViewer</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: #78ba32;">7.2.1</span></li><br/>
							                        </ul>
							                    </div>
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
    <div id="foot" class="footer">
    	<p style="color: #ffffff;text-align: center;">© 西安交通大学社会智能与复杂数据处理实验室  2017.</p>
    </div>
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
