
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>智能小朵</title>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <script src="js/login_bg/jquery-1.9.1.js"></script>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="new/front/style/reset2.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util2.css" />
    <link href="css/main.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/css.css" rel="stylesheet">
    <link rel="stylesheet" href="css/all.css">
    <script src="js/login_bg/jquery-1.9.1.js"></script>
    <script language="javascript" src="zhao/tab/common.js"></script>
    <link  type="text/css" rel="stylesheet" href="zhao/tab/style.css" />
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
    </div>
	
	<section id="shortcodes">
		<div id="main">
			<div class="bg-other user-head-info">
				<#list personal2_list as list>
    			<div class="user-info">
        			<h3 class="user-name clearfix">
            		<span>${list.USERNAME}</span>
        			</h3>
        			<p class="about-info">
        			<#if list.GENDER=="男">
        			<span class="gender" title="男"></span>
        			<#elseif list.GENDER=="女">
        			<span class="gender girl" title="女"></span>
        			<#else>
        			<span class="sexSecret " title="保密"></span>
        			</#if>
        				${list.USERADDRESS?replace('0',' ')?replace('1',' ')?replace('2',' ')?replace('3',' ')}     </p>
                 	<p class="about-info">${GOODWORK}   ${WORKAGE}</p>
                 	<p class="user-desc" >${list.USERSIGNATURE}</p>
        			<div class="study-info clearfix">
                    	<div class="item follows">
                        	<a onclick="guanzhubutton()"><em>${paynumber}</em></a>
                        	<span>关注</span>
                    	</div>
                    	<div class="item followers">
                            <a onclick="beiguanzhubutton()"><em>${bepaynumber}</em></a>
                            <span>粉丝</span>
                    	</div>
        			</div><!--.study-info end-->
    			</div><!-- .user-info end -->
    			</#list>
			</div><!-- .big-pic end -->
			
			<div class="wrap">
			<#list personal2_list as picture>
				<div class="slider">
        			<div class="user-pic" data-is-fans="" data-is-follows="">
            			<div class="user-pic-bg"></div><!--user-pic-big end-->
            			<img class="img" src="${picture.AVATAR}" alt="">
            		<#if IsMy=="0">
                    <div class="friend mail js-already-follow  " id="yiguanzhu0" onmouseover="showGuanzhu();" onmouseout="hideGuanzhu();" style="display:none;" >
                         <a href="message.html" target="_blank"><i class="fa fa-envelope"></i></a>
							<div class="u-info-tips u-info-alreadyfollow-tip" data-type="2" id="yiguanzhu" style="display:none" >    
    							<span class="title">已成功关注Ta</span>
    							<p class="content">关注后可及时了解Ta的动态，并可向Ta发送即时消息。</p>
        						<a onclick="quguanzhu()" class="cancel-follow" >取消关注</a>    
							</div>
                    </div>
                    <div class="friend group_add js-add-follow" id="weiguanzhu0" onmouseover="showAddGuanzhu();" onmouseout="hideAddGuanzhu();" style="display:block;" onclick="guanzhu()">
                        <i class="fa fa-plus"></i>
							<div class="u-info-tips u-info-follow-tip" data-type="1" id="weiguanzhu" style="display:none" >    
    							<span class="title">关注Ta</span>
    							<p class="content">关注后可及时了解Ta的动态信息，并可向Ta发送即时消息</p>
							</div>
                    </div>
                    </#if>
		        <ul>
		        <li>
		            <a onclick="c_index()" class="active" id="c_index">
		            <i class="fa fa-home"></i><span>主&nbsp;&nbsp;&nbsp;&nbsp;页</span><b class="fa fa-angle-right"></b>
		            </a>
		        </li>
		        <li>
		            <a onclick="c_know()" id="c_know">
		            <i class="fa fa-lightbulb-o"></i><span>知识库</span><b class="fa fa-angle-right"></b>
		            </a>
		        </li>
		        <li>
		            <a onclick="c_question()" id="c_question">
		            <i class="fa fa-question"></i><span>问&nbsp;&nbsp;&nbsp;&nbsp;吧</span><b class="fa fa-angle-right"></b>
		            </a>
		        </li>     
		        </ul>
		</div><!-- .slider end -->
		</#list>
	</div>	
<div class="u-container" id="zhao1" style="display:block;">
		<div class=" page-home js-usercard-box" id="notices">
			<div class="dyanmic-title-wrap">
	    		<p class="dynamic-title">Ta的动态 </p>
			</div>
           <div class="item newanswer">
                <a class="fl" href="/u/3940996" target="_blank">
                   	<img src="http://img.mukewang.com/545864000001644402200220-40-40.jpg" class="userHead  js-usercard-dialog " width="40" height="40" data-userid="3940996">
               	</a>
                <div class="content fr">    
                    <div class="head">
                        <div class="name"> 
                            <a class=" js-usercard-dialog " href="/u/3940996" data-userid="3940996" target="_blank">黄智强</a>
                        </div>
                        <div class="time">3小时前</div> 
                        <div class="title">在问吧发布了问题</div>
                    </div>
                    <div class="body pr">
                        <a class="fl" href="/wenda/5" target="_blank">
                        	<img src="http://img.mukewang.com/563affe40001680c00900090.jpg" width="40" height="40" title="Html/CSS">
                        </a>
                        <div class="content">
                            <div class="tag">   
                            	<span>来自</span> 
                                	<a href="/wenda/5" target="_blank">
                                    	<span class="ml10">个人电脑</span> 
                                    </a>
                            </div>
                            <div class="subtitle">
                            	<a href="/wenda/detail/343802" target="_blank"> 数组 + 号是什么意思 我一删掉就无法显示了。 </a>
                            </div> 
                        </div>
                    </div>
               	</div>
                <div class="cb"></div>
            </div>

			<div class="item newanswer">
                <a class="fl" href="/u/3940996" target="_blank">
                   	<img src="http://img.mukewang.com/545864000001644402200220-40-40.jpg" class="userHead  js-usercard-dialog " width="40" height="40" data-userid="3940996">
               	</a>
                <div class="content fr">    
                    <div class="head">
                        <div class="name"> 
                            <a class=" js-usercard-dialog " href="/u/3940996" data-userid="3940996" target="_blank">黄智强</a>
                        </div>
                        <div class="time">3小时前</div> 
                        <div class="title">在问吧回答了问题</div>
                    </div>
                    <div class="body pr">
                        <a class="fl" href="/wenda/5" target="_blank">
                        	<img src="http://img.mukewang.com/563affe40001680c00900090.jpg" width="40" height="40" title="Html/CSS">
                        </a>
                        <div class="content">
                            <div class="tag">   
                            	<span>来自</span> 
                                	<a href="/wenda/5" target="_blank">
                                    	<span class="ml10">个人电脑</span> 
                                    </a>
                            </div>
                            <div class="subtitle">
                            	<a href="/wenda/detail/343802" target="_blank"> 数组 + 号是什么意思 我一删掉就无法显示了。 </a>
                            </div> 
                        </div>
                    </div>
               	</div>
                <div class="cb"></div>
            </div>
            
            <div class="item newarticlescrap">
                    <span class=" pa share js-share" data-shareid="share_12030172"></span>
                    <a href="/u/10000" target="_blank">
                    <img src="http://img.mukewang.com/57a322f00001e4ae02560256-40-40.jpg" class="userHead  js-usercard-dialog  " width="40" height="40" data-userid="10000">
                    </a>
                    <div class="content fr">   
                        <div class="head">
                            <div class="name"> 
                                <a class=" js-usercard-dialog " href="/u/10000" data-userid="10000" target="_blank">朵女神</a>
                            </div>
                            <div class="time">1天前</div> 
                            <div class="title">创建了知识</div>
                        </div>
                        <a href="/article/16835" target="_blank">
                            <div class="body pr">
                                 <div class="content">
                                        <div class="subtitle">自信可改变未来，问谁又能做到</div> 
                                        <div class="detail ">自信可改变未来，问谁又能做到 前言：应广大人民群众的要求，我决定写这一篇如何从一个屌丝逆袭的手记。至于为何换个马甲，是因为里面有些东西不想让别人知道是我发的。内容非常多，但是我都做了标题，不想看的可以选择性的看。我还是建议看一下的，为什么写这些呢，其实是想让你们了解一下，我也有不堪的过去，我也是一步一步走来的，我可以，你们也可以。 如果大家觉得对自己有帮助，可以点个赞，顺便分享...</div>
                                        <div class="cb"></div>
                                 </div>
                            </div>
                        </a>
                    </div>
                    <div class="cb"></div>
        	</div>
</div>

     <p class="js-noreload     dynamicLoad js-dynamicLoadwrap">
          <span class="js-dynamicLoad  ">下拉显示更多</span>
     </p>
</div><!-- .container end -->


<div class="u-container" id="zhao2" style="display:none">
					<div class="investment_f">
			  			<div class="investment_title">
			    			<div class="on">我的知识</div>
			    			<div>我的收藏</div>
						    <div>我的评论</div>
					    </div>
			  			<div class="investment_con">
						    <div class="article-main">
						    <p class="notattend">你还没有任何原创知识，快去<a href="/article/publish" class="red" target="_blank">发表知识</a>吧</p>
			    			</div>
						    <div class="investment_con_list">
						    	<div id="articleMain" class="article-main">
									<div id="articlesList" class="articles-list">
                 						<div class="list-item article-item ">
            								<h3 class="item-title">
	                							<a target="_blank" href="/article/16835" class="title-detail">自信可改变未来，问谁又能做到</a>
	                                            <span class="original">原创</span>
                            				</h3>
								            <div class="">
	               								 <p class="item-bd">自信可改变未来，问谁又能做到 前言：应广大人民群众的要求，我决定写这一篇如何从一个屌丝逆袭的手记。至于为何换个马甲，是因为里面有些东西不想让别人知道是我发的。内容非常多，但是我都做了标题，不想看的可以选择性的看。我还是建议看一下的，为什么写这些呢，其实是想让你们了解一下，我也有不堪的过去，我也是一步一步走来的，我可以，...</p>
	            							</div>
	            							<div class="item-btm clearfix">
	                							<ul class="l left-info">
	                                            	<li class="hd-pic">
	                            						<a class="publisher-name" href="/u/1008447/articles" target="_blank">神秘路人甲</a>
	                       							</li>
	                                            </ul>
               									<div class="r right-info">
                    								<div class="favorite l">
                       									 <i class="icon sns-thumb-up-outline"></i><em> 1606浏览</em>
                    								</div>
								                    <div class="favorite l">
								                        <i class="icon sns-thumb-up-outline"></i><em> 116点赞</em>
								                    </div>
								                    <div class=" l">
								                        <i class="icon sns-comment"></i><em> 40评论</em>
								                    </div>
               									 </div>
                           					 </div>
       									 </div>
									</div>
								</div>
						    </div>
						    
				    		<div class="investment_con_list">
						    	<div id="articleMain" class="article-main">
									<div id="articlesList" class="articles-list">
                 						<div class="list-item article-item ">
            								<h3 class="item-title">
	                							<a target="_blank" href="/article/16835" class="title-detail">自信可改变未来，问谁又能做到</a>
	                                            <span class="original">原创</span>
                            				</h3>
								            <div class="">
	               								 <p class="item-bd">自信可改变未来，问谁又能做到 前言：应广大人民群众的要求，我决定写这一篇如何从一个屌丝逆袭的手记。至于为何换个马甲，是因为里面有些东西不想让别人知道是我发的。内容非常多，但是我都做了标题，不想看的可以选择性的看。我还是建议看一下的，为什么写这些呢，其实是想让你们了解一下，我也有不堪的过去，我也是一步一步走来的，我可以，...</p>
	            							</div>
	            							<div class="item-btm clearfix">
	                							<ul class="l left-info">
	                                            	<li class="hd-pic">
	                            						<a class="publisher-name" href="/u/1008447/articles" target="_blank">神秘路人甲</a>
	                       							</li>
	                                            </ul>
               									<div class="r right-info">
                    								<div class="favorite l">
                       									 <i class="icon sns-thumb-up-outline"></i><em> 1606浏览</em>
                    								</div>
								                    <div class="favorite l">
								                        <i class="icon sns-thumb-up-outline"></i><em> 116点赞</em>
								                    </div>
								                    <div class=" l">
								                        <i class="icon sns-comment"></i><em> 40评论</em>
								                    </div>
               									 </div>
                           					 </div>
       									 </div>
									</div>
								</div>
						    </div>
			  			</div>
					</div>
</div><!-- .container end -->

<div class="u-container" id="zhao3" style="display:none;">
					<div class="investment_f">
			  			<div class="investment_title">
			    			<div class="on">我的提问</div>
			    			<div>我的回答</div>
						    <div>我的关注</div>
					    </div>
			  			<div class="investment_con">
						    <div class="article-main">
						    	<div class="ques-list">
                        			<div class="ques-answer">
            							<div class="tag-img">
                                             <a href="/wenda/3" target="_blank">
                        						<img src="http://img.mukewang.com/563afff200010a9f00900090.jpg" alt="JAVA" title="JAVA">
                    						</a>
                                        </div><!--.tag-img end-->
            							<div class="from-tag">来自<a href="/wenda/3" target="_blank">JAVA</a></div><!--.from-tag end-->
            							<div class="ques-con">
                							<a href="/wenda/detail/338293" class="ques-con-content" title="想学习框架，SSH好还是SSM好，新手" target="_blank">想学习框架，SSH好还是SSM好，新手</a>
            							</div><!-- .ques-con end-->
            							<div class="about-info">
							                <span class="time">2016-12-10</span>
							                <a href="/wenda/detail/338293" target="_blank" class="reply-num">4个回答</a>
							            </div><!-- .about-info end -->
        							</div><!-- .ques-answer end -->
    							</div>
			    			</div>
						    <div class="investment_con_list">
						    	<div class="ques-list">
            						<div class="ques-answer">
        								<div class="tag-img">
                                        	<a href="/wenda/3" target="_blank">
                    							<img src="http://img.mukewang.com/563afff200010a9f00900090.jpg" title="JAVA">
                							</a>
                                        </div><!--.tag-img end-->
        								<div class="from-tag">来自<a href="/wenda/3" target="_blank">JAVA</a></div><!--.from-tag end-->
        								<div class="ques-con">
            								<a href="/wenda/detail/338293" target="_blank" class="ques-con-content" title="想学习框架，SSH好还是SSM好，新手">想学习框架，SSH好还是SSM好，新手</a>
        								</div><!-- .ques-con end-->
								        <div class="answer-con" data-answer-id="2">
								            <div class="user">我的回答</div>
								            <div class="answer-content">
								                <a href="/wenda/wdanswer/217416" target="_blank">不知道哪个适合初学者</a>
								            </div>
								        </div><!--.answer-con end-->
								        <div class="about-info">
								            <span class="time">2016-12-10</span>
								            <a href="/wenda/wdanswer/217416" target="_blank" class="reply-num">1个回复</a>
								        </div><!-- .about-info end -->
								    </div><!-- .ques-answer end -->
								</div>
						    </div>
						    
						    <div class="investment_con_list">
						    	<div class="ques-list">
                        			<div class="ques-answer">
            							<div class="tag-img">
                                             <a href="/wenda/3" target="_blank">
                        						<img src="http://img.mukewang.com/563afff200010a9f00900090.jpg" alt="JAVA" title="JAVA">
                    						</a>
                                        </div><!--.tag-img end-->
            							<div class="from-tag">来自<a href="/wenda/3" target="_blank">JAVA</a></div><!--.from-tag end-->
            							<div class="ques-con">
                							<a href="/wenda/detail/338293" class="ques-con-content" title="想学习框架，SSH好还是SSM好，新手" target="_blank">想学习框架，SSH好还是SSM好，新手</a>
            							</div><!-- .ques-con end-->
            							<div class="about-info">
							                <span class="time">2016-12-10</span>
							                <a href="/wenda/detail/338293" target="_blank" class="reply-num">4个回答</a>
							            </div><!-- .about-info end -->
        							</div><!-- .ques-answer end -->
    							</div>
						    </div>
				    		
			  			</div>
					</div>
</div><!-- .container end -->

<div class="u-container" id="zhao4" style="display:none;">
					<div class="investment_f">
			  			<div class="investment_title" id="investment_title">
			    			<div class="on">我的关注</div>
			    			<div>我的粉丝</div>
					    </div>
			  			<div class="investment_con">
						    <div class="article-main">
						    	<div class="concern-list">
									<ul>
										<li class="box">
				 							<div class="left-img">
				 								<a href="/u/3806692" target="_blank"><img src="http://img.mukewang.com/57a991b80001a15406500634-100-100.jpg" class="top_head"></a>
				 							</div>
				 							<div class="right-c">
				 								<div class="title">
				 									<a href="/u/3806692" target="_blank"><span class="nickname">TerryG</span></a>
				 										<ul class="icon-list">
				 										 	<li class="u-icon imooc-teacher">
				 											</li>
				 										</ul>
				 								</div>
				 							<p class="desc" title="全栈工程师">全栈工程师</p>
				 								<div class="fs-line">
				 									<a  class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">0</em></span></a>
						 							<a href="/u/3806692/fans" class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">103</em></span></a>					 				
				 								</div>
				 								<div class="btn-line" data-is_self="1" data-is-fans="0">
													<a href="Javascript:" data-uid="3806692" class="btn-o btn-gray-o hide js-concern-mutual">互相关注</a>
													<a href="Javascript:" data-uid="3806692" class="btn-o btn-gray-o  js-concern-already">已关注</a>
													<a href="/u/3674640/messages?uid=3806692" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a>
												</div>
				 							</div>
				 						</li>
									</ul>
								</div>
			    			</div>
						    <div class="investment_con_list">
						    	<div class="ques-list">
            						<div class="nodata">您还没有任何粉丝</div>
								</div>
						    </div>
			  			</div>
					</div>
</div><!-- .container end -->

</div><!-- .wrap end-->

</div>
    </section>    
		
    <div id="foot" class="footer">
    	<p style="color: #ffffff;text-align: center;">© 西安交通大学社会智能与复杂数据处理实验室  2017.</p>
    </div>
    	<script type="text/javascript" src="new/front/js/util.js"></script>
		<script type="text/javascript" src="js/my.js"></script>
		<script>
			function guanzhu(){
				var touserId = document.URL.split("u=")[1];
				var userId = '${uid}';
				if(userId!=touserId){
					$.ajax({
						type:"POST",
						url:"/org.xjtusicd3.partner/savePay.html",
						data:{
							"touserId":touserId
						},
						dataType:"json",
						success:function(data){
							if(data.value=="0"){
								self.location='login.html';
							}else if(data.value=="1"){
								document.getElementById("weiguanzhu0").style.display="none";
	    						document.getElementById("yiguanzhu0").style.display="block";
							}
						}
					})
				}
			}
			function quguanzhu(){
				var touserId = document.URL.split("u=")[1];
				var userId = '${uid}';
				if(userId!=touserId){
					$.ajax({
						type:"POST",
						url:"/org.xjtusicd3.partner/deletePay.html",
						data:{
							"touserId":touserId
						},
						dataType:"json",
						success:function(data){
							if(data.value=="0"){
								self.location='login.html';
							}else if(data.value=="1"){
								document.getElementById("weiguanzhu0").style.display="block";
	    						document.getElementById("yiguanzhu0").style.display="none";
							}
						}
					})
				}
			}
		</script>
		<script>
	    	$(document).ready(function(){
	    		if('${IsMy}'=="0"){
	    			if('${payList}'=="0"){
	    				document.getElementById("weiguanzhu0").style.display="block";
	    				document.getElementById("yiguanzhu0").style.display="none";
	    			}else if('${payList}'=="1"){
	    				document.getElementById("weiguanzhu0").style.display="none";
	    				document.getElementById("yiguanzhu0").style.display="block";
	    			}
	    		}
	    	})
	    </script>
</body>
</html>
