
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
    <link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/header.css">
	<link rel="stylesheet" type="text/css" href="css/body.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="new/front/style/utilDetails.css" />
    <link href="zhao/lunbo/css/jquery.onebyone-min.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/header.js"></script>
    <script type="text/javascript" src="js/lnv_frontMonitor.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="js/modernizr.custom.79639.js"></script>
   
</head>
<body>
	<div class="header" id="head">      
	    <!-- 头部开始 -->
		<#include "inc/incTop.ftl">
	    <!-- 头部结束 -->
       	
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
        <div class="contentWra clearfix">
            <div class="leftMainWrapper ">
				<section id="mainL">
				<div id="quickAsk">
					<p>有问题，来问吧~</p>
					<form onsubmit="return false;">
						<input type="text" placeholder="输入问题，如“如何升级至Windows 10？”">
						<button>提交</button>
					</form>
					<div class="wrapper-demo">
						<div id="dd" class="wrapper-dropdown-3" tabindex="1">
							<span id="dd_">${typename}</span>
							<ul class="dropdown">
								<li><a onclick="getType()"><i class="icon-envelope icon-large"></i>全部</a></li>
								<li><a onclick="getType()"><i class="icon-truck icon-large"></i>已解决</a></li>
								<li><a onclick="getType()"><i class="icon-plane icon-large"></i>待回答</a></li>
							</ul>
						</div>
					​</div>
				</div>

				<!-- 问题查询列表 -->
				<ul id="searchResult">
					<#list communityViews as communityViews>
					<li id="${communityViews.communityId}_">
						<article >
							<div class="tag">
								<ul>
									<li>${communityViews.classifyName}</li>
									<#if communityViews.userId ??>
									<li class="type">回答</li>
									<#else>
									<li class="type">问题</li>
									</#if>
								</ul>
								<div class="time"><p>${communityViews.time}</p></div>
							</div>
							
							<div class="title">
								<h2><a href="question2.html?q=${communityViews.communityId}">${communityViews.communityTitle}</a></h2>
							</div>
							
							<#if communityViews.userId ??>
							<div class="description">
								<div class="answerer" data-id="270369">
									<img class="answerImg" src="${communityViews.userImage}">
									<div>
										<a href="">
											<span class="user_name">${communityViews.userName}</span>
												&nbsp;&nbsp;<span>${communityViews.signature}</span>
										</a>
									</div>
									<div>
										<img src="images/bluepoint.png" class="bluepoint">贡献${communityViews.totalCommunityNumber}个回答，获得${communityViews.totalLikesNumber}个赞
									</div>
								</div>
								
								<div class="detail">
									<#if communityViews.answer?length gt 100>
									<div class="detailP">
										${communityViews.answer[0..100]}......
										<span class="readMore">查看更多</span>
									</div>
									<#else>
									<div class="detailP">${communityViews.answer}
									</div>
									</#if>
								</div>
								
								<div class="fullDetail hidden">
									<p>${communityViews.answer}</p>
								</div>
							</div>
							
							<div class="options">
								<ul>
									<li class="special">
										<#if userEmail ??>
											<#if communityViews.isLike="1">
											<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">已点赞</span>  |  <span class="number">${communityViews.likesNumber}</span></a>
											<#else>
											<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">${communityViews.likesNumber}</span></a>
											</#if>
										<#else>
											<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">${communityViews.likesNumber}</span></a>
										</#if>
									</li>
									<li><a data-fun="toComment" class="fm_ele" fm-type="button" fm-name="answer_comment" fm-operation="click" fm-zoon="option_area"><span>评论 </span><span class="number">${communityViews.communityNumber}</span></a></li>
									<span class="fold"><a data-fun="fold"><span class="foldicon"></span>收起</a></span>
								</ul>
							</div>
							<#else>
							<div class="description">
								<div class="detail">
									<#if communityViews.communityQuestion?length gt 100>
									<div class="detailP">
										${communityViews.communityQuestion[0..100]}......
										<span class="readMore">查看更多</span>
									</div>
									<#else>
									<div class="detailP">${communityViews.communityQuestion}
									</div>
									</#if>
								</div>
								<div class="fullDetail hidden">
									<p>${communityViews.communityQuestion}</p>
								</div>
							</div>
							<div class="options">
								<ul>
									<li class="special"><a onclick="create_edit()" class="unFocused fm_ele" ><span class="status" id="${communityViews.communityId}">回答</span></a></li>
									<li><a data-fun="toComment" class="fm_ele" fm-type="button" fm-name="answer_comment" fm-operation="click" fm-zoon="option_area"><span>评论 </span><span class="number">${communityViews.communityNumber}</span></a></li>
									<span class="fold"><a data-fun="fold"><span class="foldicon"></span>收起</a></span>
								</ul>
							</div>
							</#if>
							
						</article>
					</li>
					</#list>
				</ul>
				
				<div id="loadStatus">
					<div id="loading" class="">
						<span>点击查看更多</span>
						<div class="spinner">
  							<div class="bounce1"></div>
  							<div class="bounce2"></div>
  							<div class="bounce3"></div>
						</div>
					</div>
					<div id="loadMore" class="hidden">
						<button>加载更多</button>
					</div>
					<div id="noMore" class="hidden">
						<span>没有更多</span>
					</div>
				</div>
			</section>
                
            </div>
            
            <div class="rightBarWrapper">
            	<div id="mainR">
            	<div style="height:85px;"><button class="fm_ele" id="ask" fm-type="button" fm-name="button_ask" fm-operation="click" fm-zoon="header_area" onclick="questionForm();">提新问题</button></div>
				<div><a href="http://iknow.lenovo.com/"><img id="wenba" src="images/iknow.png"></a></div>
				<div id="topic">
					<div id="topicTitle"><img src="images/topic.png" ><a onclick="getClassify()">话题</a></div>
					<!-- 问题标签列表 -->
					<ul id="tagFilter">
					<#list classifyList as classifyList>
						<li><a class="fm_ele" onclick="getClassify()" >${classifyList.FAQCLASSIFYNAME}</a></li>
					</#list>
					</ul>
				</div>
			</div>
        	</div>
        </div>
    </div>
    
    <div id="foot" class="footer">
    	<p style="color: #ffffff;text-align: center;">© 西安交通大学社会智能与复杂数据处理实验室  2017.</p>
    </div>
   
    <div id="questionForm" class="popup" style="display: none;">		
    	<div class="fade"></div>			
    	<div id="question-f">				
    		<div id="question-s" class="">					
    			<a href="javascript:" id="close"></a>
				<h1 id="titH">添加问题</h1>					
				<textarea rows="1" name="question_content" id="title" placeholder="请输入您的问题，如：IPhone 6指纹识别如何破解？" maxlength="100"></textarea>
				<p class="askTitleTip" style="display: none;"></p>	
				<ul id="similarAsk" style="display: block;"></ul>					
				<h1 id="desH">添加问题的详细描述</h1>					
				<script id="editor" type="text/plain" style="width:650px;height:300px;margin-left:34px;margin-top:20px;"></script>
				<button id="toStep2">下一步</button>				
			</div>				
			
			<div id="tag-s" class="hidden">					
				<a href="javascript:" id="close"></a>					
				<h1 id="tagH">添加标签</h1>					
				<h2>可选</h2>					
				<p id="titP">“asfdsdafa”</p>					
				<ul id="systemTag">
					<#list classifyList as classifyList>
					<li>
						<input type="checkbox" name="category_id" value="${classifyList.FAQCLASSIFYID}">
						<label>${classifyList.FAQCLASSIFYNAME}</label>
					</li>
					</#list>
				</ul>					
		<ul id="customTag"></ul>					
		<div></div>					
		<button id="toStep1">返回</button>				
		<button id="submit"  onclick="saveCommunityQuestion()">提交</button>				
			</div>			
		</div>		
	</div>
	
	<div id="lasturl" style="display:none"></div>
    	<script type="text/javascript" src="new/front/js/util.js"></script>
		<script type="text/javascript" src="js/view/ueditor.js"></script>
		<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="js/view/question.js"></script>
		<script type="text/javascript" src="js/browserEvent.js"></script>
		
		<script type="text/javascript">
			function DropDown(el) {
				this.dd = el;
				this.placeholder = this.dd.children('span');
				this.opts = this.dd.find('ul.dropdown > li');
				this.val = '';
				this.index = -1;
				this.initEvents();
			}
			DropDown.prototype = {
				initEvents : function() {
					var obj = this;
					obj.dd.on('click', function(event){
						$(this).toggleClass('active');
						return false;
					});
					obj.opts.on('click',function(){
						var opt = $(this);
						obj.val = opt.text();
						obj.index = opt.index();
						obj.placeholder.text(obj.val);
					});
				},
				getValue : function() {
					return this.val;
				},
				getIndex : function() {
					return this.index;
				}
			}
		</script>
		<div class="success" id="success" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/true.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">提交成功</h2></div></div>
		<div class="success" id="chongfu" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/cuo.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">切勿重复提交</h2></div></div>
		<div class="success" id="noclassify" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/cuo.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">请选择分类</h2></div></div>
</body>
</html>
