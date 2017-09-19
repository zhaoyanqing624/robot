
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
            <div class="leftMainWrapper fristKnowledgeWra">
				<div class="nowPositionBox clearfix">
                    <a href="/org.xjtusicd3.partner/faq.html">
                    	<span class="allType">所有分类</span>
                    </a>
                    <span class="arrow">&gt;</span>
                    <#list faq2_list as classify>
                    <a href="/org.xjtusicd3.partner/faq1.html?p=${classify.FAQCLASSIFYID}" id="firstNavStep"><span id="fristProductionName">${classify.FAQCLASSIFYNAME}</span></a>
                    </#list>
                    <span class="arrow">&gt;</span>
                    <#list faq2_list2 as classify2>
                    <a href="/org.xjtusicd3.partner/faq2.html?c=${classify2.FAQCLASSIFYID}" id="secondNavStep" value="${classify2.FAQCLASSIFYID}"><span class="secondStep" id="subProductionName">${classify2.FAQCLASSIFYNAME}</span></a>
                    </#list>                    
                </div>
                
                <div class="secondListtWrapper" id="secondListtWrapper">
                <#list faq2_list3 as faqlist>
                	<ul class="knowledgeList">
                		<li>
                			<p class="title">
                				<a href="faq3.html?q=${faqlist.questionId}" target="_blank">${faqlist.faqTitle}</a>
                				<span class="tags undefined"></span>
                			</p>
                		</li>
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
                			<span class="showCount">${faqlist.faqScan}</span><span class="message">${faqlist.commentNumber}</span><span class="collection">${faqlist.faqCollection}</span></li><li class="content">${faqlist.faqDescription}</li>
                	</ul>
                </#list>            
                </div>
                
                <div class="topMoreTop" id="querymorelink" value="1" display="block">
                    <a href="javascript:void(0);" onclick="queryMoreTop()" >加载更多</a>
                </div>
            </div>
           		
            <!-- 右侧开始 -->
			<div class="rightBarWrapper">
				<#include "inc/incRight.ftl">
				<div class="barBox2 " id="" data="1" style="left:0px;top:645px;z-index:1;width:340px;">
            		<h3 class="box-title">活跃用户
            			<span class="leifeng-tab js-leifeng-tab week" data-type="week">一周</span>
            			<span class="leifeng-tab js-leifeng-tab day active" data-type="day">今日</span>
            		</h3>
				    <ul class="leifeng-tab-box-min day" style="display:block">
				    <#list userActive as userActive>
			            <li>
			                <div id="ranking" class="first">${userActive_index+1}</div>
			                <div id="user-pic"><a target="_blank" href="personal2.html?u=${userActive.userId}"><img style="display: block;height: 100%;" src="${userActive.userImage}"></a></div><!--.user-pic end-->
			                <div id="user-name"><a target="_blank" href="personal2.html?u=${userActive.userId}">${userActive.userName}</a></div><!--.user-name end-->
			                <div id="user-info" class="clearfix"><span id="role">${userActive.work}</span><span id="answer-num">${userActive.faqNumber}评论</span></div><!--.user-info end-->
            			</li>
            		</#list>
                    </ul>
                   	<ul class="leifeng-tab-box-min week" style="display:none">
				    <#list userActiveWeek as userActiveWeek>
			            <li>
			                <div id="ranking" class="second">${userActiveWeek_index+1}</div>
			                <div id="user-pic"><a target="_blank" href="personal2.html?u=${userActiveWeek.userId}"><img style="display: block;height: 100%;" src="${userActiveWeek.userImage}"></a></div><!--.user-pic end-->
			                <div id="user-name"><a target="_blank" href="personal2.html?u=${userActiveWeek.userId}">${userActiveWeek.userName}</a></div><!--.user-name end-->
			                <div id="user-info" class="clearfix"><span id="role">${userActiveWeek.work}</span><span id="answer-num">${userActiveWeek.faqNumber}评论</span></div><!--.user-info end-->
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
	<#include "/inc/incFoot.ftl">
	<!-- 底部结束 -->
	
	
	<!--加载更多  -->
	<script type="text/javascript" src="new/front/js/util.js"></script>
	<script type="text/javascript" src="js/view/faq2.js"></script>
</body>
</html>
