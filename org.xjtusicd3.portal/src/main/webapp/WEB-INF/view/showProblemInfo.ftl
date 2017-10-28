<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>智能小朵-问题中心</title>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <link rel="stylesheet" type="text/css" href="css/problem/style.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/reset.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util.css" />
    <link rel="stylesheet" type="text/css" href="css/problem/reset.css">
	<link rel="stylesheet" type="text/css" href="css/problem/header.css">
	<link rel="stylesheet" type="text/css" href="css/problem/body.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="new/front/style/utilDetails.css" />
	<link rel="stylesheet" type="text/css" href="css/problem/detail.css">
	<link href="zhao/lunbo/css/jquery.onebyone-min.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/header.js"></script>
    <script type="text/javascript" src="js/lnv_frontMonitor.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="js/modernizr.custom.79639.js"></script>
    <script type="text/javascript" src="js/view/question2.js"></script>
    
</head>
<body>
	<div class="header" id="head" style="visibility: hidden;">      
        <li class="loginLinkLi" id="userNameText">您好：${UserName}</li>
    </div>
    
    <!-- main content begin -->
	<div class="mainContent">
	<!-- main begin -->
	<div id="main">
	<!-- 主体内容 begin -->
	<div class="contentWra clearfix">
		<!-- 左侧栏 begin -->
		<div class="leftMainWrapper ">
		<!-- 左侧主要区域 begin -->
		<div id="mainL">
			<!-- 左侧主要内容 begin -->
			<div id="detail-wrapper">			
				<!-- 左侧article begin -->
				<article id="question" data-question-id="42314" data-asker-id="294663">
					<!-- 显示分类名 -->
					<div class="tag">
						<ul>
							<li>${classifyName}分类名</li>
						</ul>
					</div>
				
				<!-- 获取问题信息 begin -->
				<#list questionList as questionList>
					<!-- 问题标题 -->
					<div class="title">
						<h2><a>${questionList.TITLE}问题标题</a></h2>
					</div>
					
					<!-- 问题描述 -->
					<div class="description">
						<div class="detail">
							<#if questionList.CONTENT?length gt 150>
							<div class="detailP">
								${questionList.CONTENT[0..150]}......这是问题的描述
								<span class="readMore">查看更多</span>
							</div>
							<#else>
							<div class="detailP">${questionList.CONTENT}问题内容
							</div>
							</#if>
						</div>
						<div class="fullDetail hidden">
							<p>${questionList.CONTENT}</p>
						</div>
					</div>
					
					<!-- 问题描述长度大于150时显示 -->
					<div class="options">
						<ul>
							<span class="fold"><a data-fun="fold"><span class="foldicon"></span>收起</a></span>
						</ul>
					</div>

				</#list>
				<!-- 获取问题信息 end -->
					
					<!-- 获取回答数；时间排序、赞数排序 -->
					<div id="sort">
						<h2>${communityNumber}个回答</h2>
						<select>
							<option value="time">时间排序</option>
							<option value="recommended">赞数排序</option>
						</select>
					</div>
		
					<div class="clearfix"></div>
					
					
					<!-- 不同用户对问题答案的不同操作 begin -->
					<ul id="searchResult">
					<!-- 判断有无最佳答案 begin -->
					<#if answerList_best?size gt 0>
						<!-- 有最佳答案_获取最佳答案信息 begin -->
						<#list answerList_best as answerList_best>
						<li id="${answerList_best.answerId}">
							<article class="answerArticle">
								<!-- 最佳答案显示 begin -->
								<#if answerList_best.isBestAnswer=="1">
								<div class="hd line ">
									<div id="act-link-banner-wp" class="grid-r">
										<span class="iknow-qb_home_icons answer-type answer-best grid ">
											<img src="images/best.png"/>
										</span>
										<span class="answer-title h2 grid">最佳答案</span>
									</div>
								</div>
								</#if>
								<!-- 最佳答案显示 end -->
								
								<!-- 最佳答案具体信息显示 begin -->
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
								<!-- 最佳答案具体信息显示 begin -->
								
								
							   
							   	<!-- 最佳答案用户评论 begin -->
								<div class="comment" style="display:none">
									<img class="deco" src="images/dia-deco.png" style="left:106px">
									<div class="comment-outer">									
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
								<!-- 最佳答案用户评论 end -->
							</article>
						</li>
						</#list>
						<!-- 有最佳答案_获取最佳答案信息 end -->
						
						<!-- 有最佳答案_获取非最佳答案信息 begin -->		
						<#list answerList_other as answerList_other>
						<li id="${answerList_other.answerId}">
							<article class="answerArticle">
								<!-- 非最佳答案具体信息显示 begin -->
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
								<!-- 非最佳答案具体信息显示 end -->
								
								
								
								<!-- 非最佳答案评论信息 begin -->
								<div class="comment" style="display:none">
									<img class="deco" src="images/dia-deco.png" style="left:106px">
									<div class="comment-outer">										
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
								<!-- 非最佳答案评论信息 end -->
							</article>
						</li>
						</#list>
						<!-- 有最佳答案_获取非最佳答案信息 end -->		
						
					<!-- 无最佳答案 begin -->	
					<#else>
						<!-- userid:登录用户ID；_userid:问题提问者ID -->						
						<#if userid==_userid>
							<!-- 登录用户对自己提问的操作 begin-->
							<#list answerList_other as answerList_other>
							<li id="${answerList_other.answerId}">
								<article class="answerArticle">									
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
																		
									<div class="comment" style="display:none">
										<img class="deco" src="images/dia-deco.png" style="left:106px">
										<div class="comment-outer">										
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
							<!-- 登录用户对自己提问的操作 end-->							
						<#else>
							<!-- 登录用户不是问题提问者 begin-->
							<#list answerList_other as answerList_other>
							<li id="${answerList_other.answerId}">
								<article class="answerArticle">							
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
									
									
								   
								   <div class="comment" style="display:none">
										<img class="deco" src="images/dia-deco.png" style="left:106px">
										<div class="comment-outer">
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
							<!-- 登录用户不是问题提问者 end-->
						</#if>
					</#if>				
					</ul>
					<!-- 不同用户对问题答案的不同操作 end -->
				</article>
				<!-- 左侧article end -->
			</div>
			<!-- 左侧主要左侧主要内容 end -->
			
			<!-- 点击查看更多 begin -->
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
			<!-- 点击查看更多 end -->
		</div>
		<!-- 左侧主要区域 end --> 
		</div>
		<!-- 左侧栏 end -->

	</div>
	<!-- 主体内容 end -->
	</div>
	<!-- main end -->    
	</div>
	<!-- main content end -->
    
    
    
    <div id="foot" class="footer">
    	<p style="color: #ffffff;text-align: center;">© 西安交通大学社会智能与复杂数据处理实验室  2017.</p>
    </div>
	<script type="text/javascript" src="js/browserEvent.js"></script>
</body>
</html>
