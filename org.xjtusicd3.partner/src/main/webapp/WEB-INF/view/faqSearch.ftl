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
				<form class="avatar-form" action="faqSearch.html" enctype="multipart/form-data" method="post">
					<div class="queryBox">
						<input type="text" class="text" value="输入关键字" onfocus="if(this.value=='输入关键字')this.value='';" onblur="if(this.value=='')this.value='输入关键字';" id="keyWordText" onkeydown="if (event.keyCode == 13) {queryKnowledgeByKey();}" autocomplete="off" name="queryString">
						<a href="javascript:void(0);" class="queryBtn" type="submit"></a>
					</div>
				</form>
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
		   <!-- 左侧开始 -->
		   <div class="leftMainWrapper">
            <div class="searchResult wenba">
      			求助网友，找到更多你想要的答案
                <button class="width:50px" onclick="javascript:window.open('question.html?c=all&type=all')">立即提问</button>
            </div>
                <div class="searchResult clearfix">
                    <span class="fr">共搜索到 <label id="searchCount">${titleNumber}</label> 篇文章</span>
                    <span class="fl">关键字: <label id="searchKeyWord">${queryStr}</label></span>
                </div>
                <div class="searchListWrapper" id="searchTplWrapper" style="min-height: 0px;">
                <#list faq2List as faq2List>
                    <ul class="knowledgeList">
                        <li>
                            <p class="title">
                                <a href="faq3.html?q=${faq2List.questionId}" target="_blank">${faq2List.faqTitle}</a>
                            </p>
                        </li>
                        <li class="clearfix">
                        	<#list faq2List.uList as uList>
                            <span class="userPic"><img src="${uList.userImage}" onclick="javascript:window.open('personal2.html?u=${uList.userId}')" style="cursor:pointer"></span>
                            <span class="username" onclick="javascript:window.open('personal2.html?u=${uList.userId}')"  style="cursor:pointer">${uList.userName}</span>
                            <span class="dot">-</span>
                            </#list>
                            <span class="time">${faq2List.faqModifytime}</span>
                            <span class="line">|</span>
                            <span class="showCount">${faq2List.faqScan}</span>
                            <span class="message">${faq2List.commentNumber}</span>
                            <span class="collection">${faq2List.faqCollection}</span>
                        
                        </li>
                        <li class="content">${faq2List.faqDescription}</li>
                    </ul>
                </#list>    
                </div>
                <input type="hidden" id="searchId" name="search_id" value="1687592">
                <div class="topMoreTop" id="querymorelink" style="height: 54px;margin: 0 auto;padding-top: 14px;">
                    <a href="javascript:void(0);" onclick="queryMoreTop()">加载更多</a>
                </div>
            </div>
           <!-- 左侧结束 -->
           
           <!-- 右侧开始 -->
           <div class="rightBarWrapper">
	           <#include "inc/incRight.ftl">
			</div>	
           <!-- 右侧结束 -->
		</div>
	</div>            
    <!-- 主体结束 -->
    
    <!-- 底部开始 -->
	<#include "inc/incFoot.ftl">
	<!-- 底部结束 -->
     
    <!-- Slider -->
	<script type="text/javascript" src="new/front/js/util.js"></script>
	<script>
		function queryMoreTop(){
			var queryStr = document.getElementById("searchKeyWord").innerHTML;
			var starNumb = document.getElementById("searchTplWrapper").childElementCount;
			$.ajax({
				type : "POST",
				url : "/org.xjtusicd3.partner/queryMoreResult.html",
				data : {
					"queryStr" : queryStr,
					"starNumb" : starNumb
				},
				dataType : "json",
				success : function(data) {
					if (data.value == "0") {
						self.location = 'login.html';
					} else {
						if(data.queryList==null){
							document.getElementById("querymorelink").remove();
						}else{
							for(var i in data.queryList){
								var htmls = document.getElementById("searchTplWrapper").innerHTML;
								document.getElementById("searchTplWrapper").innerHTML = htmls + '<ul class="knowledgeList"> <li> <p class="title"> <a href="faq3.html?q='+data.queryList[i].questionId+'" target="_blank">'+data.queryList[i].faqTitle+'</a> </p> </li> <li class="clearfix"> <span class="userPic"><img src="'+data.queryList[i].uList[0].userImage+'" onclick="javascript:window.open("personal2.html?u='+data.queryList[i].uList[0].userId+'")" style="cursor:pointer"></span> <span class="username" onclick="javascript:window.open("personal2.html?u='+data.queryList[i].uList[0].userId+'")" style="cursor:pointer">'+data.queryList[i].uList[0].userName+'</span> <span class="dot">-</span> <span class="time">'+data.queryList[i].faqModifytime+'</span> <span class="line">|</span> <span class="showCount">'+data.queryList[i].faqScan+'</span> <span class="message">'+data.queryList[i].commentNumber+'</span> <span class="collection">'+data.queryList[i].faqCollection+'</span>  </li> <li class="content">'+data.queryList[i].faqDescription+'</li> </ul>';
							}
						}
					}
				}
			})
		}
	</script>

</body>
</html>
