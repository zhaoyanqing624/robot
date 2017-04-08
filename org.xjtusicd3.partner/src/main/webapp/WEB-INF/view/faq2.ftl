
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
		                <li><a class="new_a" href="question.html" data-pos="categorys_1_1">问题中心</a></li>
		                <li>
		                    <a class="new_a" href="service.html">关于我们</a>
		                </li>
		            </ul> 
                </div>
                <div class="header_top_wrap_right">
		              <ul>
		                <div class="unlogin">
		                    <li class="loginLinkLi"><span class="person_icon"></span></li>
		                    <li class="loginLinkLi" id="userNameText">您好：zhao</li>
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
		                    <li class="left_margin loginLinkLi"><a id="headExit">退出</a>
		                    </li>
		                </div>
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
	            	<span>小朵知识库</span>
	        	</a>
    		</div>
		</div>
    </div>
    <div class="mainContent">
        <div class="contentWra clearfix">
            <div class="leftMainWrapper fristKnowledgeWra">
				<div class="nowPositionBox clearfix">
                    <a href="/org.xjtusicd3.partner/faq.html"><span class="allType">所有分类</span></a>
                    <span class="arrow">&gt;</span>
                    <#list classify as classify>
                    <a href="/org.xjtusicd3.partner/faq1.html?p=${classify.classifyId}" id="firstNavStep"><span id="fristProductionName">${classify.classifyName}</span></a>
                    </#list>
                    <span class="arrow">&gt;</span>
                    <#list classify2 as classify2>
                    <a href="/org.xjtusicd3.partner/faq2.html?c=${classify2.classifyId}" id="secondNavStep" value="${classify2.classifyId}"><span class="secondStep" id="subProductionName">${classify2.classifyName}</span></a>
                    </#list>                    
                </div>
                
                <div class="secondListtWrapper" id="secondListtWrapper">
                <#list faq2Views as faqlist>
                	<ul class="knowledgeList">
                		<li><p class="title"><a href="faq3.html?f=${faqlist.faqId}" target="_blank">${faqlist.faqTitle}</a><span class="tags undefined"></span></p></li>
                		<li class="clearfix">
                		<#list faqlist.uList as ulist>
                			<span class="userPic">
                				<img src="${ulist.userImage}">
                			</span>
                			<span class="username">${ulist.userName}</span>
                		</#list>
                			<span class="dot">-</span>
                			<span class="time">${faqlist.faqModifytime?substring(0,10)?replace('-','/')}</span>
                			<span class="line">|</span>
                			<span class="showCount">${faqlist.faqScan}</span><span class="message">2</span><span class="collection">${faqlist.faqCollection}</span></li><li class="content">${faqlist.faqDescription}</li>
                	</ul>
                </#list>
                
                </div>
                
                <div class="topMoreTop" id="querymorelink" value="1">
                    <a href="javascript:void(0);" onclick="queryMoreTop()" >加载更多</a>
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
					    <a href="javascript:void(0)" class="writer" onclick="goCreateKnowledge()">创建知识</a>
					    <a href="http://ask.lenovo.com.cn/html/index.html" class="question" target="_blank">我要提问</a>
					    </p>
				</div>
				<div class="barBox2 " id="" data="1" style="left:0px;top:645px;z-index:1;width:340px;">
            		<h3 class="box-title">活跃用户</h3>
				    <ul class="leifeng-tab-box-min" >
			            <li style="position: relative;padding-left: 90px;margin-top: 30px;">
			                <div id="ranking" class="first">1</div>
			                <div id="user-pic"><a target="_blank" href="/u/4985559/bbs"><img style="display: block;height: 100%;" src="http://img.mukewang.com/58bec74a0001ff7a16001280-100-100.jpg" title="八神花露水"></a></div><!--.user-pic end-->
			                <div id="user-name"><a target="_blank" href="/u/4985559/bbs">八神花露水</a></div><!--.user-name end-->
			                <div id="user-info" class="clearfix"><span id="role"></span><span id="answer-num">19条知识</span></div><!--.user-info end-->
			            </li>
			            <li>
			                <div id="ranking" class="second">2</div>
			                <div id="user-pic"><a target="_blank" href="/u/4499997/bbs"><img style="display: block;height: 100%;" src="http://img.mukewang.com/583d2ab20001050406400640-100-100.jpg" title="习惯受伤"></a></div><!--.user-pic end-->
			                <div id="user-name"><a target="_blank" href="/u/4499997/bbs">习惯受伤</a></div><!--.user-name end-->
			                <div id="user-info" class="clearfix"><span id="role">西安明乐运维</span><span id="answer-num">10条知识</span></div><!--.user-info end-->
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
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
	<script>
		function queryMoreTop(){ 
			var pagenow = $("#querymorelink").attr("value");
			var classifyId = $("#secondNavStep").attr("value");
				$.ajax({
					type:"post",
					url:"/org.xjtusicd3.partner/getMoreFaqList.html",
					data:{
						"pagenow":pagenow,
						"classifyId":classifyId
					},
					dataType:"json",
					success:function(data){
						alert(1);
						alert(data);
					}
				})
		}
	</script>
</body>
</html>
