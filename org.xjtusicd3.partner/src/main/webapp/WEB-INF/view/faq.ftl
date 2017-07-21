<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>智能小朵-知识库</title>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <link rel="stylesheet" type="text/css" href="new/front/style/reset.css" />
    
    <link rel="stylesheet" type="text/css" href="new/front/style/util-rb.css">
    <link rel="stylesheet" type="text/css" href="new/front/style/util.css" />
    <link href="zhao/lunbo/css/jquery.onebyone-min.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="zhao/lunbo_1/styles/slider.css" />
    <link rel="stylesheet" type="text/css" href="zhao/lunbo_1/styles/skitter.styles.css" media="all" />
    <script type="text/javascript" src="zhao/lunbo/js/jquery.js"></script>
    <script type="text/javascript" src="js/my.js"></script>
</head>

<body>
	<!-- 头部开始 -->
	<div class="header" id="head"> 
	<#include "inc/incTop.ftl">
   		<div class="headContent">
			<div class="headTop clearfix">
				<div class="queryBox">
					<input type="text" class="text" value="输入关键字" onfocus="if(this.value=='输入关键字')this.value='';" onblur="if(this.value=='')this.value='输入关键字';" id="keyWordText" onkeydown="if (event.keyCode == 13) {queryKnowledgeByKey();}" autocomplete="off">
					<a href="javascript:void(0);" class="queryBtn" onclick="queryKnowledgeByKey()"></a>
				</div>
				<a href="" class="logoCon">
					<img src="images/logo.jpg" class="logo">
					<span>IT运维智能化服务一体化平台——知识库</span>
				</a>
			</div>
		</div>
	</div>	
    <!-- 头部结束 -->
    
    <!-- 主体开始 -->
	<div class="mainContent">
		<div class="contentWra clearfix">
			<div class="leftMainWrapper">
				<div class="banner">
			        <!-- 知识库界面图片切换开始 -->
			        <div id="outerslider" >
			        	<div id="slidercontainer">
			            	<section id="slider">
				                <div class="box_skitter box_skitter_large">
				                    <ul>
										<li>
				                            <img src="zhao/lunbo_1/images/content/z1.png"  alt="" />
				                            <div class="label_text">
				                                <span>Windows 10系统升级、安装、更新热点问题汇总</span>
				                            </div>
				                        </li>
				                        <li>
				                            <img src="zhao/lunbo_1/images/content/z2.png"  alt="" />
				                            <div class="label_text">
				                                <span>联想一键恢复的使用方法</span>
				                            </div>
				                        </li>
				                        <li>
				                            <img src="zhao/lunbo_1/images/content/z3.jpg"  alt="" />
				                            <div class="label_text">
				                                <span>采用RAID SSD的机型安装Win10系统无法识别随机标配的硬盘</span>
				                            </div>
				                        </li>
				                    </ul>
				                </div>
			                </section>
			            </div>
			        </div>
			        <!-- 知识库界面图片切换结束 -->
                </div>
                
                <div class="topWrapper" id="tplWrapper">
					<!-- 第一个模块的显示 -->
					<div class="topList clearfix">
                        <div class="imagesPotion">
                            <a href="/detail/dc_143417.html" target="_blank">
                                <img src="images/test/21.png" alt="">
                            </a>
                        </div>
                        <ul class="topcontent">
                            <li>
                                <p class="title">
                                    <a href="/detail/dc_143417.html" target="_blank">如何在Windows 7安装U盘中加入USB3.0的支持</a>
                                </p>
                            </li>
                            <li class="clearfix">
                                <span class="userPic"><img src="new/front/images/avatar.jpg"></span>
                                <span class="username">Iknow</span>
                                <span class="dot">-</span>
                                <span class="time">2015/11/04</span>
                                <span class="line">|</span>
                                <span class="showCount">236955</span>
                                <span class="message">34</span>
                                <span class="collection">78</span>
                            </li>
                            <li class="content">
                            	原生Win7系统不包含USB3.0的驱动，所以无法使用USB3.0的U盘在USB3.0的设备上引导，且安装完系统后还需要重新安装USB3.0驱动。
                            </li>
                        </ul>
                    </div>
                    	 
					<!-- 第二个模块的显示 -->
					<div class="topList clearfix">
                        <div class="imagesPotion">
                            <a href="/detail/dc_039949.html" target="_blank">
                                <img src="images/test/22.jpg" alt="新扬天电脑所配USB键盘">
                            </a>
                        </div>
                        <ul class="topcontent">
                            <li>
                                <p class="title">
                                    <a href="/detail/dc_039949.html" target="_blank">新扬天电脑所配USB键盘功能键驱动问题注意事项</a>
                                </p>
                            </li>
                            <li class="clearfix">
                                <span class="userPic"><img src="new/front/images/avatar.jpg"></span>
                                <span class="username">Iknow</span>
                                <span class="dot">-</span>
                                <span class="time">2015/11/07</span>
                                <span class="line">|</span>
                                <span class="showCount">148932</span>
                                <span class="message">11</span>
                                <span class="collection">7</span>
                            </li>
                            <li class="content">
                            	2010年7月上市的新扬天电脑所配的USB键盘，F1-F12系统下会默认为快捷键（功能键），而非F1-F12实际功能，本文介绍了按键的切换调整方法，供参考。
                            </li>
                        </ul>
					</div>
                    
					<!-- 第三个模块的显示 -->
					<div class="topList clearfix">
                        <div class="imagesPotion">
                            <a href="/detail/dc_153184.html" target="_blank">
                                <img src="images/test/23.jpg" alt="永久关闭Windows 10的自动更新">
                            </a>
                        </div>
                        <ul class="topcontent">
                            <li>
                                <p class="title">
                                    <a href="/detail/dc_153184.html" target="_blank">永久关闭Windows 10的自动更新</a>
                                </p>
                            </li>
                            <li class="clearfix">
                                <span class="userPic"><img src="new/front/images/avatar.jpg"></span>
                                <span class="username">Iknow</span>
                                <span class="dot">-</span>
                                <span class="time">2016/10/26</span>
                                <span class="line">|</span>
                                <span class="showCount">44195</span>
                                <span class="message">26</span>
                                <span class="collection">86</span>
                            </li>
                            <li class="content">
                            	本文介绍了永久关闭Win10自动更新的操作方法。
                            </li>
                        </ul>
					</div>
                    
					<!-- 第四个模块的显示 -->
					<div class="topList clearfix">
                        <div class="imagesPotion">
                            <a href="/detail/dc_141257.html" target="_blank">
                                <img src="images/test/24.jpg" alt="Lenovo settings下载及使用介绍">
                            </a>
                        </div>
                        <ul class="topcontent">
                            <li>
                                <p class="title">
                                    <a href="/detail/dc_141257.html" target="_blank">Lenovo settings下载及使用介绍</a>
                                </p>
                            </li>
                            <li class="clearfix">
                                <span class="userPic"><img src="new/front/images/avatar.jpg"></span>
                                <span class="username">Iknow</span>
                                <span class="dot">-</span>
                                <span class="time">2015/12/11</span>
                                <span class="line">|</span>
                                <span class="showCount">183724</span>
                                <span class="message">19</span>
                                <span class="collection">36</span>
                            </li>
                            <li class="content">
                            	联想随机软件Lenovo settings界面展示，下载和使用方法介绍。
                            </li>
                        </ul>
					</div>
                    
					<!-- 第五个模块的显示 -->
					<div class="topList clearfix">
                        <div class="imagesPotion">
                            <a href="/detail/dc_152752.html" target="_blank">
                                <img src="images/test/25.jpg" alt="小新Air12 LTE版如何4G上网">
                            </a>
                        </div>
                        <ul class="topcontent">
                            <li>
                                <p class="title">
                                    <a href="/detail/dc_152752.html" target="_blank">小新Air12 LTE版如何4G上网？</a>
                                </p>
                            </li>
                            <li class="clearfix">
                                <span class="userPic"><img src="new/front/images/avatar.jpg"></span>
                                <span class="username">Iknow</span>
                                <span class="dot">-</span>
                                <span class="time">2016/10/08</span>
                                <span class="line">|</span>
                                <span class="showCount">9615</span>
                                <span class="message">0</span>
                                <span class="collection">5</span>
                            </li>
                            <li class="content">
                            	本文介绍了小新Air12 LTE版4G上网的设置方法和常见问题。
                            </li>
                        </ul>
                    </div>
				</div>
                
                <div class="topMoreTop" id="querymorelink">
                
                <div style="width: 118px;height: 32px;margin: 0 auto;padding-top: 14px;">
                    <a href="javascript:void(0);">加载中</a>
                    </div>
                </div>          
			</div> 
           
           <!-- 右侧开始 -->
           <div class="rightBarWrapper">
	           <#include "inc/incRight.ftl">
				<div class="barBox " id="dynamicBox" data="1" style="left:0px;top:621px;z-index:1">
					<h3 class="box-title">用户动态</h3>
					<ul class="dynamicList">
						<#list userDynamics as userDynamics>
						<li class="dynamicItem">
							<p class="dynamicTime">${userDynamics.time}</p><p class="dynamicDesc"><a href="personal2.html?p=${userDynamics.userId}">${userDynamics.userName}</a>: ${userDynamics.how}“<a href="faq3.html?q=${userDynamics.faqId}">${userDynamics.faqTitle}</a>”</p>
						</li>
						</#list>
					</ul>
				</div>
			</div>	
           <!-- 右侧结束 -->
		</div>
	</div>            
    <!-- 主体结束 -->
    
    <!-- 底部开始 -->
	<#include "inc/incFoot.ftl">
	<!-- 底部结束 -->
     
    <!-- Slider -->
    <script type="text/javascript" src="zhao/lunbo_1/js/jquery-1.6.4.min.js"></script>
	<script type="text/javascript" src="zhao/lunbo_1/js/jquery.animate-colors-min.js"></script>
	<script type="text/javascript" src="zhao/lunbo_1/js/jquery.skitter.js"></script>
	<script type="text/javascript" src="zhao/lunbo_1/js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="new/front/js/util.js"></script>
	<script type="text/javascript">
	jQuery(document).ready(function(){
		jQuery(".box_skitter_large").skitter({
			animation: "random",
			interval: 3000,
			numbers: false, 
			numbers_align: "right", 
			hideTools: false,
			controls: false,
			focus: false,
			focus_position: true,
			width_label:'1000px', 
			enable_navigation_keys: true,   
			progressbar: false
		});
	});
	</script>
</body>
</html>
