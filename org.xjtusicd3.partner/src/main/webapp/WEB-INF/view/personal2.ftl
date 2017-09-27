
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
       <#include "inc/incTop.ftl">
    </div>
	
	<section id="shortcodes">
		<div id="main">
			<!-- 顶部栏 -->
			<div class="bg-other user-head-info">
				<#list personal2_list as list>
    			<div class="user-info" id="${list.USERID}">
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
			
			<!-- 主体信息 -->
			<div class="wrap">
			<#list personal2_list as picture>
				<!-- 左侧栏 -->
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
		        	<#if IsMy=="1">
		            <a onclick="c_know()" id="c_know">
		            <i class="fa fa-lightbulb-o"></i><span>知识库</span><b class="fa fa-angle-right"></b>
		            </a>
		            </#if>
		        </li>
		        <li>
		        	<#if IsMy=="1">
		            <a onclick="c_question()" id="c_question">
		            <i class="fa fa-question"></i><span>问&nbsp;&nbsp;&nbsp;&nbsp;吧</span><b class="fa fa-angle-right"></b>
		            </a>
		            </#if>
		        </li>
		        </ul>
		</div><!-- .slider end -->
		</#list>
	</div>	

		<!-- 显示区域 -->
		<!-- 主页显示区域 -->
		<div class="u-container" id="zhao1" style="display:block;">
		<div class=" page-home js-usercard-box" id="notices">
			<div class="dyanmic-title-wrap">
	    		<p class="dynamic-title">Ta的动态 </p>
			</div>
			<#assign n = indexList?size />
			<#if n gt 5>
			<#assign n =5 />
			</#if>
			<#if n!=0>
			<#list 0..(n-1) as i>
			<#assign ls = indexList[i] />
			<#assign isNew = indexListIstrue[i] />
			<#if ls.how=="在问吧提问"||ls.how=="推荐了问题">
           	<div class="item newanswer">
                <a class="fl" href="personal2.html?u=${ls.userId}" target="_blank">
                   	<img src="${ls.userImage}" class="userHead  js-usercard-dialog " width="40" height="40">
               	</a>
                <div class="content fr">    
                    <div class="head">
                        <div class="name"> 
                            <a class=" js-usercard-dialog " href="personal2.html?u=${ls.userId}"  target="_blank">${ls.userName}</a>
                        </div>
                        <div class="time">${ls.time?substring(0,16)}</div> 
                        <div class="title">${ls.how}</div>
                    </div>
                    <div class="body pr">
                    	<a class="fl" href="question.html?c=${ls.from}&type=all" target="_blank">
                            <img src="${ls.fromImage}" width="150" height="110">
                        </a>
                        <div class="content">
                            <div class="tag">   
                            	<span>来自</span> 
                                	<a href="question.html?c=${ls.from}&type=all" target="_blank">
                                    	<span class="ml10">${ls.from}</span> 
                                    </a>
                            </div>
                            <div class="subtitle">
                            	<a href="question2.html?q=${ls.questionId}" target="_blank">${ls.content}</a>
                            </div> 
                        </div>
                    </div>
               	</div>
                <div class="cb"></div>
            </div>
			<#elseif ls.how=="创建了知识"||ls.how=="修改了知识"||ls.how=="推荐了知识">
            <div class="item newarticlescrap">
                    <span class=" pa share js-share"></span>
                    <a class="fl" href="personal2.html?u=${ls.userId}" target="_blank">
                   		<img src="${ls.userImage}" class="userHead  js-usercard-dialog " width="40" height="40">
               		</a>
                    <div class="content fr">   
                        <div class="head">
                            <div class="name"> 
                                <a class=" js-usercard-dialog " href="personal2.html?u=${ls.userId}"  target="_blank">${ls.userName}</a>
                            </div>
                            <div class="time">${ls.time?substring(0,16)}</div> 
                            <div class="title">${ls.how}</div>
                        </div>
                        <a href="faq3.html?q=${ls.questionId}" target="_blank">
                            <div class="body pr">
                                 <div class="content">
                                        <div class="subtitle">${ls.title}</div> 
                                        <div class="detail ">${ls.content}</div>
                                        <div class="cb"></div>
                                 </div>
                            </div>
                        </a>
                    </div>
                    <div class="cb"></div>
        	</div>
        	<#elseif ls.how=="关注了用户">
        	<div class="item newuser">
                    <a class="fl" href="personal2.html?u=${ls.userId}" target="_blank">
                   		<img src="${ls.userImage}" class="userHead  js-usercard-dialog " width="40" height="40">
               		</a>
                    <div class="content fr">   
                        <div class="head">
                            <div class="name"> 
                                <a class=" js-usercard-dialog " href="personal2.html?u=${ls.userId}" target="_blank">${ls.userName}</a>
                            </div>
                            <div class="time">${ls.time?substring(0,16)}</div> 
                            <div class="title">${ls.how}</div>
                        </div> 
                        <div class="body pr">
                            <a class="fl" href="personal2.html?u=${ls.touserId}" target="_blank">
                                <img class=" js-usercard-dialog   mr10" src="${ls.touserImage}" width="40" height="40">
                            </a>
                            <div class="content">
                                <div class="subtitle">
                                    <a class=" js-usercard-dialog " href="personal2.html?u=${ls.touserId}"  target="_blank">${ls.touserName}</a>
                                </div> 
                                <div class="tag ">
                                	 <#if ls.touserSex=="男">
                                	 	<span class="gender mr10"></span>
                                	 <#elseif ls.touserSex=="女">
                                	 	<span class="gender girl mr10"></span>
                                	 <#else>
                                	 	<span class="sexSecret mr10"></span>
                                	 </#if>	
                                     <span class="mr10">${ls.touserAddress?split("0")[1]?split("1")[0]}</span> 
                                     <span class="mr10">${ls.touserAddress?split("1")[1]?split("2")[0]}</span>
                                     <span class="mr10">${ls.touserAddress?split("2")[1]?split("3")[0]}</span>
                                     <span class="mr10">${ls.touserJob}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="cb"></div>
                </div>
        	</#if>
        	</#list>
        </#if>
	</div>
	<#if indexListSize gt 5>
     <p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreIndex">
          <span class="js-dynamicLoad " onclick="getMoreIndex()">下拉显示更多</span>
     </p>
    </#if>
</div><!-- .container end -->

				<!-- 知识库显示区域 -->
				<div class="u-container" id="zhao2" style="display:none">
					<div class="investment_f">
			  			<div class="investment_title">
			    			<div class="on">我的知识</div>
			    			<div onclick="getCollectFaq()">我的收藏</div>
						    <div onclick="getCommentFaq()">我的评论</div>
					    </div>
			  			<div class="investment_con">
						    <div class="article-main" id="zhao2_article-main">
			    			</div>
						    <div class="investment_con_list">
						    	<div  class="article-main" id="zhao2_article-main2">
								</div>
						    </div>
				    		<div class="investment_con_list">
						    	<div class="article-main" id="zhao2_article-main3">
								</div>
						    </div>
			  			</div>
					</div>
				</div><!-- .container end -->
				
				<!-- 问吧显示区域 -->
				<div class="u-container" id="zhao3" style="display:none;">
					<div class="investment_f">
			  			<div class="investment_title">
			    			<div class="on">我的提问</div>
			    			<div onclick="getPayCommunity()">我的关注</div>
						    <div onclick="getReplyCommunity()">我的回答</div>
					    </div>
			  			<div class="investment_con">
						    <div class="article-main" id="zhao3_article-main">
			    			</div>
						    <div class="investment_con_list" id="zhao3_article-main2">
						    </div>
						    <div class="investment_con_list" id="zhao3_article-main3">
						    </div>
			  			</div>
					</div>
				</div><!-- .container end -->
				
				<div class="u-container" id="zhao4" style="display:none;">
					<div class="investment_f">
			  			<div class="investment_title" id="investment_title">
			    			<div class="on" onclick="guanzhubutton()">我的关注</div>
			    			<div onclick="beiguanzhubutton()">我的粉丝</div>
					    </div>
			  			<div class="investment_con">
						    <div class="article-main">
						    	<div class="concern-list">
									<ul>
									</ul>
								</div>
			    			</div>
						    <div class="investment_con_list">
						    	<div class="concern-list">
						    		<ul>
									</ul>
								</div>
						    </div>
			  			</div>
					</div>
</div><!-- .container end -->

</div><!-- .wrap end-->

</div>
    </section>    
		
    <div id="foot" class="footer" >
    	<p style="color: #ffffff;text-align: center;">© 西安交通大学社会智能与复杂数据处理实验室  2017.</p>
    </div>
    	<script type="text/javascript" src="new/front/js/util.js"></script>
		<script type="text/javascript" src="js/my.js"></script>
		<script type="text/javascript" src="js/view/personal2.js"></script>
	    <div id="duoduo" style="display:none">${uid}</div>
</body>
</html>
