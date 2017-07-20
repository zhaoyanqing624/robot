
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>智能小朵-知识库</title>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <link rel="stylesheet" type="text/css" href="new/front/style/reset.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util.css" />
    <link href="zhao/lunbo/css/jquery.onebyone-min.css" rel="stylesheet" />
    <script type="text/javascript" src="zhao/lunbo/js/jquery.js"></script>
    <script type="text/javascript" src="js/my.js"></script>
</head>
<body>
	<div class="header" id="head">      
        <div class="loginRegistHead" role="banner">
            <div class="content clearfix">
                <div class="header_top_wrap_left">
		            <ul>
		                <li><a class="new_a" href="robot.html" data-pos="categorys_1_1">智能小朵</a></li>
		                <li><a class="new_a" href="faq.html" data-pos="categorys_1_1">知识库</a></li>
		                <li><a class="new_a" href="question.html?c=all&type=all" data-pos="categorys_1_1">问题中心</a></li>
		                <li><a class="new_a" href="advise.html" data-pos="categorys_1_1">意见建议</a></li>
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
    <div class="mainContent">
        <div class="contentWra clearfix">
            <div class="leftMainWrapper fristKnowledgeWra">
                <div class="allFristTypeWrapper">
                    <p class="allFristType"><a href="/org.xjtusicd3.partner/faq.html"><span class="type">所有分类</span></a><span> &gt; </span><a href="/topic/c_1.html" id="oneNavStep"><span id="productionName">操作系统</span></a></p>
                    <input type="hidden" id="secondeType" value="c">
                    <ul class="typeContent clearfix" id="typeTplWrapper">
                    	<#list faq1_list as a>
                        	<li><a href="faq2.html?c=${a.FAQCLASSIFYID}">${a.FAQCLASSIFYNAME}</a></li>
                        </#list>
                    </ul>
                </div>
                <div class="recommendWrapper">
                    <p class="knowledgeType"><span class="type">推荐知识</span></p>
                   	 <div id="onebyone_slider">
						<div class="oneByOne_item">
							<span class="ob1_title">近期Win8.1、Win10系统出现不定时蓝屏</span>
							<span class="ob1_description">近期Win8.1、Win10出现的蓝屏现象，可以通过本文提供的工具清除插件来解决。</span>
							<img src="zhao/lunbo/images/header/1.jpg" class="ob1_img_device1"  />
						</div>
						<div class="oneByOne_item">
							<span class="ob1_title">永久关闭Windows10自动更新</span>
							<span class="ob1_description">本文介绍了永久关闭Win10自动更新的操作方法。</span>
							<img src="zhao/lunbo/images/header/2.jpg" class="ob1_img_device1" alt="" />
						</div>
						<div class="oneByOne_item">
							<span class="ob1_title">正式版Windows 10回退至Windows 7或Windows 8.1的功能介绍</span>
							<span class="ob1_description">正式版Windows 10回退至Windows 7和Windows 8.1的功能介绍、操作步骤、风险提示。</span>
							<img src="zhao/lunbo/images/header/3.jpg" class="ob1_img_device1" alt="" />
						</div>
						<div class="oneByOne_item">
							<span class="ob1_title">如何安装或重新安装Windows 7</span>
							<span class="ob1_description">本文介绍了使用光盘重新安装Windows 7操作系统的方法，因安装系统需要对硬盘进行格式化操作，为了避免重要数据的丢失，请事先把硬盘上的重要数据进行备份。</span>
							<img src="zhao/lunbo/images/header/4.jpg" class="ob1_img_device1" alt="BubbleTips气泡提示Jquery插件" />
						</div>
					</div>
                </div>
                <div class="fristTypeKnowledgeWra clearfix" id="knowledgeTopTplWrapper">
                <#list faq1_list2 as a2>
                    <div class="knowledgeBox">
                        <p class="knowledgeType"><span class="type">${a2.classifyName}</span></p>
                        <ul class="typeKnowledgeList">
                        	<#list a2.content as b1>
                            <li class="clearfix fristList">
                                <img src="zhao/lunbo/images/${a2_index}.jpg"  class="topImage">
                                <div class="topList clearfix">
                                    <div class="topTitle">
                                        <a href="/org.xjtusicd3.partner/faq3.html?f=${b1.faqId}" target="_blank" >${b1.faqTitle}</a>
                                    </div>
                                    <p class="topContent" >${b1.faqDescription}</p>
                                </div>
                            </li>
                            </#list>
                            <#list a2.content2 as b2>
                            <li><a href="/org.xjtusicd3.partner/faq3.html?f=${b2.faqId}" target="_blank" >${b2.faqTitle}</a></li>
                            </#list>
                        </ul>
                    </div>
                 </#list>   
                 </div>
            </div>
            <div class="rightBarWrapper">
            	<div class="barBox " id="knowledgeBox" data="1" style="left:0px;top:0px;z-index:1">
            		<h3 class="box-title">知识专区</h3>
				    <ul class="knowledge clearfix" id="konwledge-first">
				    </ul>
    			</div>
    			<div class="barBox contribution " id="contribution" data="1" style="left:0px;top:460.078125px;z-index:1;height:130px;">
                    	<h3 class="box-title">我想贡献</h3>
					    <p class="textp">小朵知识库是众人参与可协作的知识分享平台。</p>
					    <p class="linkWrapper">
					    <a href="javascript:void(0);" onclick="window.open('faqadd.html');">创建知识</a>
					    <a href=""javascript:void(0);" onclick="window.open('question.html?c=all&type=all');" >我要提问</a>
					    </p>
				</div>
				<div class="barBox2 " id="" data="1" style="left:0px;top:645px;z-index:1;width:340px;">
            		<h3 class="box-title">活跃用户</h3>
				    <ul class="leifeng-tab-box-min" >
			            <li style="position: relative;padding-left: 90px;margin-top: 30px;">
			                <div id="ranking" class="first">1</div>
			                <div id="user-pic"><a target="_blank" href="/u/4985559/bbs"><img style="display: block;height: 100%;" src="http://img.mukewang.com/58bec74a0001ff7a16001280-100-100.jpg" title="八神花露水"></a></div><!--.user-pic end-->
			                <div id="user-name"><a target="_blank" href="/u/4985559/bbs">八神花露水</a></div><!--.user-name end-->
			                <div id="user-info" class="clearfix"><span id="role"></span><span id="answer-num">19知识库</span></div><!--.user-info end-->
			            </li>
			            <li>
			                <div id="ranking" class="second">2</div>
			                <div id="user-pic"><a target="_blank" href="/u/4499997/bbs"><img style="display: block;height: 100%;" src="http://img.mukewang.com/583d2ab20001050406400640-100-100.jpg" title="习惯受伤"></a></div><!--.user-pic end-->
			                <div id="user-name"><a target="_blank" href="/u/4499997/bbs">习惯受伤</a></div><!--.user-name end-->
			                <div id="user-info" class="clearfix"><span id="role">西安明乐运维</span><span id="answer-num">10知识库</span></div><!--.user-info end-->
            			</li>
                    </ul>
    			</div>
				<div class=" " id="" data="1" style="left:0px;top:215px;z-index:1">
                    <input type="hidden" class="data" value="18"><a href="" onclick="clickadd()"><img src=""></a></div><div class="barAdBox " id="" data="1" style="left:0px;top:215px;z-index:1">
                    <input type="hidden" class="data" value="24"><a href="http://iknow.lenovo.com/detail/dc_KB022987.html" onclick="clickadd()"><img src="images/erweima.PNG"></a></div></div>
        		</div>
        		
    		</div>
    <div id="foot" class="footer">
    	<p style="color: #ffffff;text-align: center;">© 西安交通大学社会智能与复杂数据处理实验室  2017.</p>
    </div>
    <!--script--!>
    	<script type="text/javascript" src="new/front/js/util.js"></script>
    	<script type="text/javascript" src="zhao/lunbo/js/jquery.plugins-min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			$('#onebyone_slider').oneByOne({
				className:'oneByOne1',
				easeType:'random',
				slideShow:true,
				delay:200,
				slideShowDelay:4000
			})
		});
		</script> 
    <!--/script--!>
</body>
</html>
