
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
               <!--所有分类   -->
                <div class="allFristTypeWrapper">
                    <p class="allFristType">
                    	<a href="/org.xjtusicd3.partner/faq.html">
                    		<span class="type">所有分类</span>
                    	</a>
                    	<span> &gt; </span>
                    	
                    	<#list classifyInfo as classify1>
                    	<a href="/topic/c_1.html" id="oneNavStep">
                    		<span id="productionName">${classify1.FAQCLASSIFYNAME }</span>
                    	</a>
                    	</#list>
                    </p>
                    <input type="hidden" id="secondeType" value="c">
                    	<ul class="typeContent clearfix" id="typeTplWrapper">
                    		<#list faq1_list as a>
                        	<li><a href="faq2.html?c=${a.FAQCLASSIFYID}">${a.FAQCLASSIFYNAME}</a></li>
                        	</#list>
						</ul>
                </div>
                
                <!--推荐知识   -->
                <div class="recommendWrapper">
                    <p class="knowledgeType"><span class="type">推荐知识</span></p>
                   	 <div id="onebyone_slider">
						<#list faq_list as b>
						<div class="oneByOne_item">							
							<span class="ob1_title">
							<p class="title">
                                    <a href="faq3.html?q=${b.FAQQUESTIONID} " target="_blank">${b.FAQTITLE }</a>
                                </p>
							</span>
							<span class="ob1_description">${b.FAQDESCRIPTION}</span>
							<img src="zhao/lunbo/images/header/${b_index+1}.jpg" class="ob1_img_device1"  />
						</div>
						</#list>
					</div>
                </div>
                
                <!--具体信息  -->
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
                                        <a href="/org.xjtusicd3.partner/faq3.html?q=${b1.questionId}" target="_blank" >${b1.faqTitle}</a>
                                    </div>
                                    <p class="topContent" >${b1.faqDescription}</p>
                                </div>
                            </li>
                            </#list>
                            <#list a2.content2 as b2>
                            <li><a href="/org.xjtusicd3.partner/faq3.html?q=${b2.questionId}" target="_blank" >${b2.faqTitle}</a></li>
                            </#list>
                        </ul>
                    </div>
                 </#list>   
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
			                <div id="user-pic"><a target="_blank" href="personal2.html?q=${userActive.userId}"><img style="display: block;height: 100%;" src="${userActive.userImage}"></a></div><!--.user-pic end-->
			                <div id="user-name"><a target="_blank" href="personal2.html?q=${userActive.userId}">${userActive.userName}</a></div><!--.user-name end-->
			                <div id="user-info" class="clearfix"><span id="role">${userActive.work}</span><span id="answer-num">${userActive.faqNumber}评论</span></div><!--.user-info end-->
            			</li>
            		</#list>
                    </ul>
                   	<ul class="leifeng-tab-box-min week" style="display:none">
				    <#list userActiveWeek as userActiveWeek>
			            <li>
			                <div id="ranking" class="second">${userActiveWeek_index+1}</div>
			                <div id="user-pic"><a target="_blank" href="personal2.html?q=${userActiveWeek.userId}"><img style="display: block;height: 100%;" src="${userActiveWeek.userImage}"></a></div><!--.user-pic end-->
			                <div id="user-name"><a target="_blank" href="personal2.html?q=${userActiveWeek.userId}">${userActiveWeek.userName}</a></div><!--.user-name end-->
			                <div id="user-info" class="clearfix"><span id="role">${userActiveWeek.work}</span><span id="answer-num">${userActiveWeek.faqNumber}评论</span></div><!--.user-info end-->
            			</li>
            		</#list>
                    </ul>
    			</div>
			</div>
           	<!-- 右侧结束 -->
		</div>
	</div>	 
			<!-- 底部开始 -->
			<#include "/inc/incFoot.ftl">
			<!-- 底部结束 -->
  </body>
</html>
    
   
   	<script type="text/javascript" src="new/front/js/util.js"></script>
   	<script type="text/javascript" src="zhao/lunbo/js/jquery.plugins-min.js"></script>
   	<script type="text/javascript" src="js/view/faq1.js"></script>
    
