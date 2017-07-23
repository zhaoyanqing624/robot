
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>智能小朵-问题中心</title>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/reset.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/utilDetails.css" />
    <link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/header.css">
	<link rel="stylesheet" type="text/css" href="css/body.css">
	<link rel="stylesheet" type="text/css" href="css/detail.css">
	<link href="zhao/lunbo/css/jquery.onebyone-min.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/header.js"></script>
    <script type="text/javascript" src="js/lnv_frontMonitor.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="js/modernizr.custom.79639.js"></script>
    <script type="text/javascript">
    $(function(){
    	if(document.URL.indexOf("n=")>0){
    		var i=0;
    		var j=0;
    		var z=0;
    		var evt1 = document.createEvent("MouseEvents");
    		var evt2 = document.createEvent("MouseEvents");
    		var evt3 = document.createEvent("MouseEvents");
    		var noticeId = document.URL.split("n=")[1].split("&p=")[0];
    		var parentId = document.URL.split("p=")[1].split("&q=")[0];
    		//模拟点击查看更多评论
    		var commentNumber = ${commentNumber};
    		commentNumber = Math.ceil(commentNumber/5);
    		if(document.getElementById(parentId)==null){//页面没有此评论
	    		for(i=0;i<commentNumber;i++){
				    evt1.initEvent("click", true, true);
	    			document.getElementById("querymorelink").firstChild.dispatchEvent(evt1);
	    			if(document.getElementById(parentId)!=null){
	    				break;
	    			}
	    		}
	    		document.getElementById(parentId).getElementsByClassName("subCommentList")[0].style.display=="block";
	    		if(document.getElementById(noticeId)==null){
	    			for(j=0;j<100;j++){
	   					evt2.initEvent("click", true, true);   
	    				document.getElementById(parentId).getElementsByClassName("commentReplay")[0].dispatchEvent(evt2);
	    				if(document.getElementById(noticeId)!=null){
	    					break;
	    				} 
	    			}
			    	mScroll(noticeId);
			    	function mScroll(id){
				    	$("html,body").stop(true);
				    	$("html,body").animate({
				    		scrollTop: $("#"+id).offset().top
				    	}, 800);
				    }
				}else{
			    	mScroll(noticeId);
			    	function mScroll(id){
				    	$("html,body").stop(true);
				    	$("html,body").animate({
				    		scrollTop: $("#"+id).offset().top
				    	}, 800);
				    }
				} 
    		}else if(document.getElementById(parentId)!=null){//页面有此评论
    			var evt = document.createEvent("MouseEvents");
    			evt.initEvent("click", true, true);
	    		document.getElementById(parentId).getElementsByClassName("commentReplay")[0].dispatchEvent(evt);
				if(document.getElementById(noticeId)==null){
	    			for(z=0;z<100;z++){
	   					evt3.initEvent("click", true, true);   
	    				document.getElementById(parentId).getElementsByClassName("ac2")[0].firstChild.dispatchEvent(evt3);
	    				if(document.getElementById(noticeId)!=null){
	    					break;
	    				} 
	    			}
			    	mScroll(noticeId);
			    	function mScroll(id){
				    	$("html,body").stop(true);
				    	$("html,body").animate({
				    		scrollTop: $("#"+id).offset().top
				    	}, 800);
				    }
				}else{
			    	mScroll(noticeId);
			    	function mScroll(id){
				    	$("html,body").stop(true);
				    	$("html,body").animate({
				    		scrollTop: $("#"+id).offset().top
				    	}, 800);
				    }
				} 		
    		}
    	}else{
    		var parentId = document.URL.split("p=")[1].split("&q=")[0];
    		//模拟点击查看更多评论
    		var commentNumber = ${commentNumber};
    		commentNumber = Math.ceil(commentNumber/5);
    		if(document.getElementById(parentId)==null){//页面没有此评论
    			for(var i1=0;i1<commentNumber;i1++){
    				var evt4 = document.createEvent("MouseEvents");
    				evt4.initEvent("click", true, true); 
	    			document.getElementById("querymorelink").getElementsByTagName("a")[0].dispatchEvent(evt4);
	    			if(document.getElementById(parentId)!=""){
	    				break;
	    			}
	    		}
	    		mScroll(parentId);
		    	function mScroll(id){
			    	$("html,body").stop(true);
			    	$("html,body").animate({
			    		scrollTop: $("#"+id).offset().top
			    	}, 800);
			    }
    		}else{
		    	mScroll(parentId);
		    	function mScroll(id){
			    	$("html,body").stop(true);
			    	$("html,body").animate({
			    		scrollTop: $("#"+id).offset().top
			    	}, 800);
			    }
    		}
    	}
    }); 
    </script>
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
	            	<span>IT运维智能化服务一体化平台——问题中心</span>
	        	</a>
    		</div>
		</div>
    </div>
    
    <div class="mainContent">
    <div id="main">
        <div class="contentWra clearfix">
            <div class="leftMainWrapper ">
				<div id="mainL">
			<div id="detail-wrapper">
			
		<article id="question" data-question-id="42314" data-asker-id="294663">
			<div class="tag">
				<ul>
					<li>${classifyName}</li>
				</ul>
			</div>
			<#list questionList as questionList>
			<div class="title">
				<h2><a>${questionList.TITLE}</a></h2>
			</div>
			<div class="description">
				<div class="detail">
					<#if questionList.CONTENT?length gt 150>
					<div class="detailP">
						${questionList.CONTENT[0..150]}......
						<span class="readMore">查看更多</span>
					</div>
					<#else>
					<div class="detailP">${questionList.CONTENT}
					</div>
					</#if>
				</div>
				<div class="fullDetail hidden">
					<p>${questionList.CONTENT}</p>
				</div>
			</div>
			<div class="options">
				<ul>
					<span class="fold"><a data-fun="fold"><span class="foldicon"></span>收起</a></span>
				</ul>
			</div>
			<#if IsIT=="1">
			<div class="options" id="shareCommunity">
                <#if IsShare=="0">
	                <ul>
						<li class="special"><a data-fun="toFocus" class="unFocused" onclick="saveShare()"><span class="status">分享</span>  |  <span class="number"> </span></a></li>
					</ul>
            	<#elseif IsShare=="1">
            		<ul>
						<li class="special"><a data-fun="toFocus" class="unFocused" onclick="saveShare()"><span class="status">已分享</span>  |  <span class="number">√</span></a></li>
					</ul>
            	</#if>
			</div>
			</#if>
			</#list>
		<div id="sort">
			<h2>${communityNumber}个回答</h2>
			<select>
				<option value="time">时间排序</option>
				<option value="recommended">赞数排序</option>
			</select>
		</div>
		<div class="clearfix"></div>
		<div id="answer-edit">
			<#list userList as userList>
			<div id="answerer">
				<img src="${userList.AVATAR}">
				<p>${userList.USERNAME}</p>
			</div>
			</#list>
			<input type="text" placeholder="添加您的答案" id="input_answer" onclick="showeditor()" style="display:block">
			<div id="answer-ueditor" class="edui-default" style="width:645px;font-size:14px;display:none">
				<script id="editor" name="content" type="text/plain"></script>
			</div>
			<div class="submitDiv" style="display:none"><button id="answerSubmit" onclick="replyQuestion()">提交</button></div>
		</div>
		<ul id="searchResult">
				<#if answerList_best?size gt 0>
					<#list answerList_best as answerList_best>
					<li id="${answerList_best.answerId}">
						<article class="answerArticle">
							<#if answerList_best.isBestAnswer=="1">
							<div class="hd line ">
								<div id="act-link-banner-wp" class="grid-r">
									<span class="iknow-qb_home_icons answer-type answer-best grid "><img src="images/best.png"/></span>
									<span class="answer-title h2 grid">最佳答案</span>
								</div>
							</div>
							</#if>
							<div class="description">
								<div class="answerer">
									<img class="answerImg" src="${answerList_best.userImage}">
									<div class="answer_name">
										<a href="personal2.html?userid=270369">
											<span class="user_name">${answerList_best.userName}</span>
												&nbsp;&nbsp;<span>${answerList_best.signature}</span>
										</a>
									</div>
									<span class="answer_time">${answerList_best.time}</span>
									<div><img src="images/bluepoint.png" class="bluepoint">贡献${answerList_best.totalAnswer}个回答，获得${answerList_best.totalLikes}个赞</div>
								</div>
								<div class="fullDetail"><p>${answerList_best.answer}</p></div>
							</div>
							<div class="options">
								<ul>
									<li class="special">
										<#if answerList_best.isLike=="0">
										<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">${answerList_best.likesNumber}</span></a>
										<#else>
										<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">已点赞</span>  |  <span class="number">${answerList_best.likesNumber}</span></a>
										</#if>
									</li>
									<li><a onclick="getCommentList()"><span>评论 </span><span class="number">${answerList_best.communityNumber}</span></a></li>
									<li>
										<#if answerList_best.isCollection=="0">
										<a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">收藏</span></a>
										<#else>
										<a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">已收藏</span></a>
										</#if>
									</li>
								</ul>
							</div>
						   <div class="comment" style="display:none">
								<img class="deco" src="images/dia-deco.png" style="left:106px">
								<div class="comment-outer">
									<#list userList as userList>
									<div class="comment-Editor">
										<img class="userImg" src="${userList.AVATAR}">
										<div class="replyOther_div" id="">
											<span class="username_span" style="color:#F00"></span>:
											<span class="content_span" style="color:#F00"></span>
										</div>
										<input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true">
										<button class="submitComment" onclick="saveComment()">评论</button>
									</div>
									</#list>
									<ul class="commentList">
									<#list answerList_best.replay as replay>
										<li id="${replay.commentId}">
											<img class="userImg" src="${replay.userImage}">
											<div class="commentDetail">
												<p class="userName">${replay.userName}<span class="touserName">    ${replay.touserName}</span></p>
												<p class="content" onclick="replyOther()">${replay.community}</p>
												<p class="commentTime">${replay.time}</p>
											</div>
										</li>
									</#list>
									</ul>
									<#if answerList_best.communityNumber?eval gt 5>
									<a class="allComments" id="allComments" onclick="getMoreComment()">点击获取更多</a>
									</#if>
								</div>
							</div>
						</article>
					</li>
					</#list>
					
					<#list answerList_other as answerList_other>
					<li id="${answerList_other.answerId}">
						<article class="answerArticle">
							<#if answerList_other.isBestAnswer=="1">
							<div class="hd line ">
								<div id="act-link-banner-wp" class="grid-r">
									<span class="iknow-qb_home_icons answer-type answer-best grid "><img src="images/best.png"/></span>
									<span class="answer-title h2 grid">最佳答案</span>
								</div>
							</div>
							</#if>
							<div class="description">
								<div class="answerer">
									<img class="answerImg" src="${answerList_other.userImage}">
									<div class="answer_name">
										<a href="personal2.html?userid=270369">
											<span class="user_name">${answerList_other.userName}</span>
												&nbsp;&nbsp;<span>${answerList_other.signature}</span>
										</a>
									</div>
									<span class="answer_time">${answerList_other.time}</span>
									<div><img src="images/bluepoint.png" class="bluepoint">贡献${answerList_other.totalAnswer}个回答，获得${answerList_other.totalLikes}个赞</div>
								</div>
								<div class="fullDetail"><p>${answerList_other.answer}</p></div>
							</div>
							<div class="options">
								<ul>
									<li class="special">
										<#if answerList_other.isLike=="0">
										<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">${answerList_other.likesNumber}</span></a>
										<#else>
										<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">已点赞</span>  |  <span class="number">${answerList_other.likesNumber}</span></a>
										</#if>
									</li>
									<li><a onclick="getCommentList()"><span>评论 </span><span class="number">${answerList_other.communityNumber}</span></a></li>
									<li>
										<#if answerList_best.isCollection=="0">
										<a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">收藏</span></a>
										<#else>
										<a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">已收藏</span></a>
										</#if>
									</li>
								</ul>
							</div>
						   <div class="comment" style="display:none">
								<img class="deco" src="images/dia-deco.png" style="left:106px">
								<div class="comment-outer">
									<#list userList as userList>
									<div class="comment-Editor">
										<img class="userImg" src="${userList.AVATAR}">
										<div class="replyOther_div" id="">
											<span class="username_span" style="color:#F00"></span>:
											<span class="content_span" style="color:#F00"></span>
										</div>
										<input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true">
										<button class="submitComment" onclick="saveComment()">评论</button>
									</div>
									</#list>
									<ul class="commentList">
									<#list answerList_other.replay as replay>
										<li id="${replay.commentId}">
											<img class="userImg" src="${replay.userImage}">
											<div class="commentDetail">
												<p class="userName">${replay.userName}<span class="touserName">    ${replay.touserName}</span></p>
												<p class="content" onclick="replyOther()">${replay.community}</p>
												<p class="commentTime">${replay.time}</p>
											</div>
										</li>
									</#list>
									</ul>
									<#if answerList_other.communityNumber?eval gt 5>
									<a class="allComments" id="allComments" onclick="getMoreComment()">点击获取更多</a>
									</#if>
								</div>
							</div>
						</article>
					</li>
					</#list>
				<#else>
					<#if userid==_userid>
						<#list answerList_other as answerList_other>
						<li id="${answerList_other.answerId}">
							<article class="answerArticle">
								<#if answerList_other.isBestAnswer=="1">
								<div class="hd line ">
									<div id="act-link-banner-wp" class="grid-r">
										<span class="iknow-qb_home_icons answer-type answer-best grid "><img src="images/best.png"></span>
										<span class="answer-title h2 grid">最佳答案</span>
									</div>
								</div>
								</#if>
								<div class="description">
									<div class="answerer">
										<img class="answerImg" src="${answerList_other.userImage}">
										<div class="answer_name">
											<a href="personal2.html?userid=270369">
												<span class="user_name">${answerList_other.userName}</span>
													&nbsp;&nbsp;<span>${answerList_other.signature}</span>
											</a>
										</div>
										<span class="answer_time">${answerList_other.time}</span>
										<div><img src="images/bluepoint.png" class="bluepoint">贡献${answerList_other.totalAnswer}个回答，获得${answerList_other.totalLikes}个赞</div>
									</div>
									<div class="fullDetail"><p>${answerList_other.answer}</p></div>
								</div>
								<div class="options">
									<ul>
										<#if userid!=answerList_other.userId>
											<li class="special"><a data-fun="toVote" class="unVoted" onclick="getBestAnswer(event)"><span class="status">设为最佳答案</span></a></li>
										</#if>
										<li class="special">
											<#if answerList_other.isLike=="0">
											<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">${answerList_other.likesNumber}</span></a>
											<#else>
											<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">已点赞</span>  |  <span class="number">${answerList_other.likesNumber}</span></a>
											</#if>
										</li>
										<li><a onclick="getCommentList()"><span>评论 </span><span class="number">${answerList_other.communityNumber}</span></a></li>
										<li>
										<#if answerList_best.isCollection=="0">
										<a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">收藏</span></a>
										<#else>
										<a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">已收藏</span></a>
										</#if>
									</li>
									</ul>
								</div>
							   <div class="comment" style="display:none">
									<img class="deco" src="images/dia-deco.png" style="left:106px">
									<div class="comment-outer">
										<#list userList as userList>
										<div class="comment-Editor">
											<img class="userImg" src="${userList.AVATAR}">
											<div class="replyOther_div" id="">
												<span class="username_span" style="color:#F00"></span>:
												<span class="content_span" style="color:#F00"></span>
											</div>
											<input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true">
											<button class="submitComment" onclick="saveComment()">评论</button>
										</div>
										</#list>
										<ul class="commentList">
										<#list answerList_other.replay as replay>
											<li id="${replay.commentId}">
												<img class="userImg" src="${replay.userImage}">
												<div class="commentDetail">
													<p class="userName">${replay.userName}<span class="touserName">    ${replay.touserName}</span></p>
													<p class="content" onclick="replyOther()">${replay.community}</p>
													<p class="commentTime">${replay.time}</p>
												</div>
											</li>
										</#list>
										</ul>
										<#if answerList_other.communityNumber?eval gt 5>
										<a class="allComments" id="allComments" onclick="getMoreComment()">点击获取更多</a>
										</#if>
									</div>
								</div>
							</article>
						</li>
						</#list>
					<#else>
						<#list answerList_other as answerList_other>
						<li id="${answerList_other.answerId}">
							<article class="answerArticle">
								<#if answerList_other.isBestAnswer=="1">
								<div class="hd line ">
									<div id="act-link-banner-wp" class="grid-r">
										<span class="iknow-qb_home_icons answer-type answer-best grid "><img src="images/best.png"></span>
										<span class="answer-title h2 grid">最佳答案</span>
									</div>
								</div>
								</#if>
								<div class="description">
									<div class="answerer">
										<img class="answerImg" src="${answerList_other.userImage}">
										<div class="answer_name">
											<a href="personal2.html?userid=270369">
												<span class="user_name">${answerList_other.userName}</span>
													&nbsp;&nbsp;<span>${answerList_other.signature}</span>
											</a>
										</div>
										<span class="answer_time">${answerList_other.time}</span>
										<div><img src="images/bluepoint.png" class="bluepoint">贡献${answerList_other.totalAnswer}个回答，获得${answerList_other.totalLikes}个赞</div>
									</div>
									<div class="fullDetail"><p>${answerList_other.answer}</p></div>
								</div>
								<div class="options">
									<ul>
										<li class="special">
											<#if answerList_other.isLike="0">
											<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">${answerList_other.likesNumber}</span></a>
											<#else>
											<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">已点赞</span>  |  <span class="number">${answerList_other.likesNumber}</span></a>
											</#if>
										</li>
										<li><a onclick="getCommentList()"><span>评论 </span><span class="number">${answerList_other.communityNumber}</span></a></li>
										<li>
										<#if answerList_best.isCollection=="0">
										<a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">收藏</span></a>
										<#else>
										<a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">已收藏</span></a>
										</#if>
									</li>
									</ul>
								</div>
							   <div class="comment" style="display:none">
									<img class="deco" src="images/dia-deco.png" style="left:106px">
									<div class="comment-outer">
										<#list userList as userList>
										<div class="comment-Editor">
											<img class="userImg" src="${userList.AVATAR}">
											<div class="replyOther_div" id="">
												<span class="username_span" style="color:#F00"></span>:
												<span class="content_span" style="color:#F00"></span>
											</div>
											<input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true">
											<button class="submitComment" onclick="saveComment()">评论</button>
										</div>
										</#list>
										<ul class="commentList">
										<#list answerList_other.replay as replay>
											<li id="${replay.commentId}">
												<img class="userImg" src="${replay.userImage}">
												<div class="commentDetail">
													<p class="userName">${replay.userName}<span class="touserName">    ${replay.touserName}</span></p>
													<p class="content" onclick="replyOther()">${replay.community}</p>
													<p class="commentTime">${replay.time}</p>
												</div>
											</li>
										</#list>
										</ul>
										<#if answerList_other.communityNumber?eval gt 5>
										<a class="allComments" id="allComments" onclick="getMoreComment()">点击获取更多</a>
										</#if>
									</div>
								</div>
							</article>
						</li>
						</#list>
					</#if>
				</#if>
				
		</ul>
		
	</div>
	<div id="loadStatus">
		<div id="loading" class="">
			<span>点击查看更多</span>
			<div class="spinner">
				<div class="bounce1"></div>
				<div class="bounce2"></div>
				<div class="bounce3"></div>
			</div>
		</div>
	</div>
		</div> 
            </div>
            
            <div class="rightBarWrapper">
            	<div id="mainR">
			<div class="QRCode hidden">
				<div class="QRCode-wrapper">
					<img class="QRCode-img">
				</div>
				<div class="QRCode-tip">
					<img class="QRCode-bulb" src="img/bulb.png">
					<div class="QRCode-word">
						<p>扫微信二维码获取</p>
						<p>[问题被回答]提醒</p>
					</div>
				</div>
			</div>
			<div class="rightWrap">
				<div class="head">
					<img src="images/topic.png">
					<h1>相关问题</h1>
				</div>
			<ul id="relatedQuestion">
			<li></li>
			<li><a data-qid="10650" href="detail.html?qid=10650">数字小键盘&nbsp;开机输入密码的时候能用，开机以后不能用&nbsp;可以控制上下左右，numlock亮也不能用，修改regedit那个和键盘有关的数值等于2也不管用，该怎么能啊？</a></li>
			<li><a data-qid="11167" href="detail.html?qid=11167">昭阳k2450&nbsp;i5&nbsp;如何开启“虚拟化”？在bios下没有看到相应的选项</a></li>
			<li><a data-qid="36333" href="detail.html?qid=36333">联想U410,bios始终进不去，不管用什么方式，看到bios选项点击还是进入windows，只能找售后么？</a></li>
			<li><a data-qid="27163" href="detail.html?qid=27163">开机自动开启小键盘</a></li>
			<li><a data-qid="36332" href="detail.html?qid=36332">联想U410,bios始终进不去，不管用什么方式，看到bios选项点击还是进入windows，只能找售后么？</a></li>
			</ul>
			</div>
			<div class="rightWrap">
				<div class="head">
					<img src="images/questionStatus.png">
					<h1>问题状态</h1>
				</div>
				<div id="status">
		<p><span class="status">查看</span><span class="number">31</span></p>
		<p><span class="status">修改于</span><span class="number">4 天前</span></p>
	</div>
			</div>
		</div>
        	</div>
        </div>
    </div>
   </div>
    <div id="foot" class="footer">
    	<p style="color: #ffffff;text-align: center;">© 西安交通大学社会智能与复杂数据处理实验室  2017.</p>
    </div>
	<script type="text/javascript" src="js/view/ueditor.js"></script>
	<script type="text/javascript" src="js/view/question2.js"></script>
		<div class="success" id="success" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/true.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">评论成功</h2></div></div>
		<div class="success" id="chongfu" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/cuo.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">切勿重复提交</h2></div></div>
		<div class="success" id="null" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/cuo.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">内容不能为空</h2></div></div>
		<div id="zhao_hidden" style="display:none">${userName}</div>
</body>
</html>
