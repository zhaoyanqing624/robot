
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
		                <li><a class="new_a" href="faq.html" data-pos="categorys_1_1" >知识库</a></li>
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
			function c_index(){
				document.getElementById("c_index").className="active";
				document.getElementById("c_know").className="";
				document.getElementById("c_question").className="";
				zhao1.style.display="block";
				zhao2.style.display="none";
				zhao3.style.display="none";
				zhao4.style.display="none";
			}
			//展示知识库
			function c_know(){
				document.getElementById("c_index").className="";
				document.getElementById("c_know").className="active";
				document.getElementById("c_question").className="";
				zhao2.style.display="block";
				zhao1.style.display="none";
				zhao3.style.display="none";
				zhao4.style.display="none";
				//获取我的知识
				var userId = document.getElementsByClassName("user-info")[0].id;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getpersonalFaq.html",
					data:{
						"userId":userId
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.faqView==""){
								document.getElementById("zhao2_article-main").innerHTML='<p class="notattend">你还没有任何原创知识，快去<a href="faq.html" class="red" target="_blank">发表知识</a>吧</p>';
							}else{
								if(document.getElementById("getMoreFaq1")!=null){
									document.getElementById("getMoreFaq1").remove();
								}
								for(var i in data.faqView){
									if(document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0]==null){
										document.getElementById("zhao2").getElementsByClassName("article-main")[0].innerHTML='<div  class="article-main"><div  class="articles-list"></div></div>';
									}
									if(document.getElementById("myfaq"+data.faqView[i].faqId)==null){
										var html = document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML;
										document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML=html + ' <div class="list-item article-item" id="myfaq'+data.faqView[i].faqId+'"> <h3 class="item-title"> <a target="_blank" href="faq3.html?q='+data.faqView[i].faqId+'" class="title-detail">'+data.faqView[i].title+'</a></h3> <div class="zhao"> <p class="item-bd">'+data.faqView[i].content+'......</p> </div> <div class="item-btm clearfix"> <ul class="l left-info"> <li class="hd-pic"> <a class="publisher-name" href="personal2.html?u='+data.faqView[i].userId+'" target="_blank">'+data.faqView[i].username+'</a> </li> </ul> <div class="r right-info"> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].scanNumber+'浏览</em> </div> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].collectionNumber+'收藏</em> </div> <div class=" l"> <i class="icon sns-comment"></i><em> '+data.faqView[0].commentNumber+'评论</em> </div> </div> </div> </div> ';
										var duo="";
										for(var j=0;j<4;j++){
											duo = duo + document.getElementById("zhao2_article-main").getElementsByClassName("zhao")[i].getElementsByTagName("p")[j].outerHTML;
										}
										document.getElementById("zhao2_article-main").getElementsByClassName("zhao")[i].innerHTML=duo;
									}
								}
								if(data.faqView[0].isMore=="1"){
									var html = document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML;
									document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreFaq1"> <span class="js-dynamicLoad " onclick="getMoreFaq1()">下拉显示更多</span> </p>';
								}
							}
						}
					}
				})
			}
			function getMoreFaq1(){
				var startnumber = document.getElementById("zhao2_article-main").getElementsByClassName("list-item article-item").length;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getMoreFaq1.html",
					data:{
						"startnumber":startnumber
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(document.getElementById("getMoreFaq1")!=null){
								document.getElementById("getMoreFaq1").remove();
							}
							for(var i in data.faqView){
								if(document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0]==null){
									document.getElementById("zhao2").getElementsByClassName("article-main")[0].innerHTML='<div  class="article-main"><div  class="articles-list"></div></div>';
								}
								if(document.getElementById("myfaq"+data.faqView[i].faqId)==null){
									var html = document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML;
									document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML=html + ' <div class="list-item article-item" id="myfaq'+data.faqView[i].faqId+'"> <h3 class="item-title"> <a target="_blank" href="faq3.html?q='+data.faqView[i].faqId+'" class="title-detail">'+data.faqView[i].title+'</a></h3> <div class="zhao"> <p class="item-bd">'+data.faqView[i].content+'......</p> </div> <div class="item-btm clearfix"> <ul class="l left-info"> <li class="hd-pic"> <a class="publisher-name" href="personal2.html?u='+data.faqView[i].userId+'" target="_blank">'+data.faqView[i].username+'</a> </li> </ul> <div class="r right-info"> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].scanNumber+'浏览</em> </div> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].collectionNumber+'收藏</em> </div> <div class=" l"> <i class="icon sns-comment"></i><em> '+data.faqView[0].commentNumber+'评论</em> </div> </div> </div> </div> ';
									var duo="";
									var addnumber = eval(startnumber)+eval(i);
									for(var j=0;j<4;j++){
										duo = duo + document.getElementById("zhao2_article-main").getElementsByClassName("zhao")[addnumber].getElementsByTagName("p")[j].outerHTML;
									}
									document.getElementById("zhao2_article-main").getElementsByClassName("zhao")[addnumber].innerHTML=duo;
								}
							}
							if(data.faqView[0].isMore=="1"){
								var html = document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML;
								document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreFaq1"> <span class="js-dynamicLoad " onclick="getMoreFaq1()">下拉显示更多</span> </p>';
							}
						}
					}
				})
			}
			function c_question(){
				document.getElementById("c_index").className="";
				document.getElementById("c_know").className="";
				document.getElementById("c_question").className="active";
				zhao3.style.display="block";
				zhao1.style.display="none";
				zhao2.style.display="none";
				zhao4.style.display="none";
				var userId = document.getElementsByClassName("user-info")[0].id;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getpersonalCommunity.html",
					data:{
						"userId":userId
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.communityView==""){
								document.getElementById("zhao3_article-main").innerHTML='<p class="notattend">你还没有任何提问记录，快去<a href="question.html?c=all&type=all" class="red" target="_blank">问题中心</a>吧</p>';
							}else{
								if(document.getElementById("getMoreCommunity")!=null){
									document.getElementById("getMoreCommunity").remove();
								}
								for(var i in data.communityView){
									if(document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0]==null){
										document.getElementById("zhao3_article-main").innerHTML='<div class="ques-list"></div>';
									}
									if(document.getElementById("myCommunity"+data.communityView[i].questionId)==null){
										var html = document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML;
										document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML=html + '<div class="ques-answer" id="myCommunity'+data.communityView[i].questionId+'"> <div class="tag-img"> <a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank"> <img src="'+data.communityView[i].classifyImage+'"> </a> </div> <div class="from-tag">来自<a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank">'+data.communityView[i].classifyName+'</a></div> <div class="ques-con"> <a href="question2.html?q='+data.communityView[i].questionId+'" class="ques-con-content"  target="_blank">'+data.communityView[i].title+'</a> </div> <div class="about-info"> <span class="time">'+data.communityView[i].time.substring(0,16)+'</span> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank" class="reply-num">'+data.communityView[i].answerNumber+'个回答</a> </div> </div> ';
									}
								}
								if(data.communityView[0].isMore=="1"){
									var html = document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML;
									document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreCommunity"> <span class="js-dynamicLoad " onclick="getMoreCommunity()">点击显示更多</span> </p>';
								}
							}
						}
					}
				})
			}
			function getMoreCommunity(){
				var startnumber = document.getElementById("zhao3_article-main").getElementsByClassName("ques-answer").length;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getMoreCommunity1.html",
					data:{
						"startnumber":startnumber
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(document.getElementById("getMoreCommunity")!=null){
								document.getElementById("getMoreCommunity").remove();
							}
							for(var i in data.communityView){
								if(document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0]==null){
									document.getElementById("zhao3_article-main").innerHTML='<div class="ques-list"></div>';
								}
								if(document.getElementById("myCommunity"+data.communityView[i].questionId)==null){
									var html = document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML;
									document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML=html + '<div class="ques-answer" id="myCommunity'+data.communityView[i].questionId+'"> <div class="tag-img"> <a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank"> <img src="'+data.communityView[i].classifyImage+'"> </a> </div> <div class="from-tag">来自<a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank">'+data.communityView[i].classifyName+'</a></div> <div class="ques-con"> <a href="question2.html?q='+data.communityView[i].questionId+'" class="ques-con-content"  target="_blank">'+data.communityView[i].title+'</a> </div> <div class="about-info"> <span class="time">'+data.communityView[i].time.substring(0,16)+'</span> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank" class="reply-num">'+data.communityView[i].answerNumber+'个回答</a> </div> </div> ';
								}
							}
							if(data.communityView[0].isMore=="1"){
								var html = document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML;
								document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreCommunity"> <span class="js-dynamicLoad " onclick="getMoreCommunity()">点击显示更多</span> </p>';
							}
						}
					}
				})
			}
			function getPayCommunity(){
				var userId = document.getElementsByClassName("user-info")[0].id;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getPayCommunity.html",
					data:{
						"userId":userId
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.communityView==""){
								document.getElementById("zhao3_article-main2").innerHTML='<p class="notattend">你还没有关注提问记录，快去<a href="question.html?c=all&type=all" class="red" target="_blank">问题中心</a>吧</p>';
							}else{
								if(document.getElementById("getMorePayCommunity")!=null){
									document.getElementById("getMorePayCommunity").remove();
								}
								for(var i in data.communityView){
									if(document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0]==null){
										document.getElementById("zhao3_article-main2").innerHTML='<div class="ques-list"></div>';
									}
									if(document.getElementById("payCommunity"+data.communityView[i].questionId)==null){
										var html = document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML;
										document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML=html + '<div class="ques-answer" id="payCommunity'+data.communityView[i].questionId+'"> <div class="tag-img"> <a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank"> <img src="'+data.communityView[i].classifyImage+'"> </a> </div> <div class="from-tag">来自<a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank">'+data.communityView[i].classifyName+'</a></div> <div class="ques-con"> <a href="question2.html?q='+data.communityView[i].questionId+'" class="ques-con-content"  target="_blank">'+data.communityView[i].title+'</a> </div><div class="answer-con" > <div class="user">最佳答案</div> <div class="answer-content"> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank">'+data.communityView[i].content+'</a> </div> </div> <div class="about-info"> <span class="time">'+data.communityView[i].replyTime.substring(0,16)+'</span> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank" class="reply-num">'+data.communityView[i].replyNumber+'个回复</a> </div> </div> ';
									}
								}
								if(data.communityView[0].isMore=="1"){
									var html = document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML;
									document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMorePayCommunity"> <span class="js-dynamicLoad " onclick="getMorePayCommunity()">点击显示更多</span> </p>';
								}
							}
						}
					}
				})
			}
			function getMorePayCommunity(){
				var startnumber = document.getElementById("zhao3_article-main2").getElementsByClassName("ques-answer").length;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getMorePayCommunity.html",
					data:{
						"startnumber":startnumber
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(document.getElementById("getMorePayCommunity")!=null){
								document.getElementById("getMorePayCommunity").remove();
							}
							for(var i in data.communityView){
								if(document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0]==null){
									document.getElementById("zhao3_article-main2").innerHTML='<div class="ques-list"></div>';
								}
								if(document.getElementById("payCommunity"+data.communityView[i].questionId)==null){
									var html = document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML;
									document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML=html + '<div class="ques-answer" id="payCommunity'+data.communityView[i].questionId+'"> <div class="tag-img"> <a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank"> <img src="'+data.communityView[i].classifyImage+'"> </a> </div> <div class="from-tag">来自<a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank">'+data.communityView[i].classifyName+'</a></div> <div class="ques-con"> <a href="question2.html?q='+data.communityView[i].questionId+'" class="ques-con-content"  target="_blank">'+data.communityView[i].title+'</a> </div><div class="answer-con" > <div class="user">最佳答案</div> <div class="answer-content"> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank">'+data.communityView[i].content+'</a> </div> </div> <div class="about-info"> <span class="time">'+data.communityView[i].replyTime.substring(0,16)+'</span> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank" class="reply-num">'+data.communityView[i].replyNumber+'个回复</a> </div> </div> ';
								}
							}
							if(data.communityView[0].isMore=="1"){
								var html = document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML;
								document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMorePayCommunity"> <span class="js-dynamicLoad " onclick="getMorePayCommunity()">点击显示更多</span> </p>';
							}
						}
					}
				})
			}
			function getReplyCommunity(){
				var userId = document.getElementsByClassName("user-info")[0].id;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getReplyCommunity.html",
					data:{
						"userId":userId
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.communityView==""){
								document.getElementById("zhao3_article-main3").innerHTML='<p class="notattend">你还没有回复提问记录，快去<a href="question.html?c=all&type=all" class="red" target="_blank">问题中心</a>吧</p>';
							}else{
								if(document.getElementById("getMorePayCommunity")!=null){
									document.getElementById("getMorePayCommunity").remove();
								}
								for(var i in data.communityView){
									if(document.getElementById("zhao3_article-main3").getElementsByClassName("ques-list")[0]==null){
										document.getElementById("zhao3_article-main3").innerHTML='<div class="ques-list"></div>';
									}
									if(document.getElementById("replyCommunity"+data.communityView[i].questionId)==null){
										var html = document.getElementById("zhao3_article-main3").getElementsByClassName("ques-list")[0].innerHTML;
										if(data.communityView[i].isreply=="0"){
											document.getElementById("zhao3_article-main3").getElementsByClassName("ques-list")[0].innerHTML=html + '<div class="ques-answer" id="replyCommunity'+data.communityView[i].questionId+'"> <div class="tag-img"> <a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank"> <img src="'+data.communityView[i].classifyImage+'"> </a> </div> <div class="from-tag">来自<a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank">'+data.communityView[i].classifyName+'</a></div> <div class="ques-con"> <a href="question2.html?q='+data.communityView[i].questionId+'" class="ques-con-content"  target="_blank">'+data.communityView[i].title+'</a> </div><div class="answer-con" > <div class="user">我的评论</div> <div class="answer-content"> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank">'+data.communityView[i].content+'</a> </div> </div> <div class="about-info"> <span class="time">'+data.communityView[i].replyTime.substring(0,16)+'</span> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank" class="reply-num">'+data.communityView[i].replyNumber+'个回复</a> </div> </div> ';
										}else{
											document.getElementById("zhao3_article-main3").getElementsByClassName("ques-list")[0].innerHTML=html + '<div class="ques-answer" id="replyCommunity'+data.communityView[i].questionId+'"> <div class="tag-img"> <a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank"> <img src="'+data.communityView[i].classifyImage+'"> </a> </div> <div class="from-tag">来自<a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank">'+data.communityView[i].classifyName+'</a></div> <div class="ques-con"> <a href="question2.html?q='+data.communityView[i].questionId+'" class="ques-con-content"  target="_blank">'+data.communityView[i].title+'</a> </div><div class="answer-con" > <div class="user">我的回复</div> <div class="answer-content"> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank">'+data.communityView[i].content+'</a> </div> </div> <div class="about-info"> <span class="time">'+data.communityView[i].replyTime.substring(0,16)+'</span> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank" class="reply-num">'+data.communityView[i].replyNumber+'个回复</a> </div> </div> ';
										}
									}
								}
								if(data.communityView[0].isMore=="1"){
									var html = document.getElementById("zhao3_article-main3").getElementsByClassName("ques-list")[0].innerHTML;
									document.getElementById("zhao3_article-main3").getElementsByClassName("ques-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getReplyCommunity"> <span class="js-dynamicLoad " onclick="getReplyCommunity()">点击显示更多</span> </p>';
								}
							}
						}
					}
				})
			}
			function guanzhubutton(){
				zhao1.style.display="none";
				zhao2.style.display="none";
				zhao3.style.display="none";
				zhao4.style.display="block";
				document.getElementById("investment_title").getElementsByTagName("div")[0].className="on";
				document.getElementById("investment_title").getElementsByTagName("div")[1].className="";
				document.getElementById("zhao4").getElementsByClassName("article-main")[0].style.display="block";
				document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].style.display="none";
				//获取关注
				var userId = document.getElementsByClassName("user-info")[0].id;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getPay.html",
					data:{
						"userId":userId
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.payView==""){
								document.getElementById("zhao4").getElementsByClassName("article-main")[0].innerHTML='<div class="ques-list"><div class="nodata">您还没有关注任何人</div></div>';
							}else{
								for(var i in data.payView){
									if(document.getElementById("pay"+data.payView[i].userId)==null){
										var html = document.getElementById("zhao4").getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML;
										if(data.payView[i].work==null){
											if(data.payView[i].isTogetherPay=="1"){
												document.getElementById("zhao4").getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="pay'+data.payView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><img src="'+data.payView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><span class="nickname">'+data.payView[i].userName+'</span></a></div><p class="desc"></p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.payView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.payView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">互相关注</a><a href="message.html?u='+data.payView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}else{
												document.getElementById("zhao4").getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="pay'+data.payView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><img src="'+data.payView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><span class="nickname">'+data.payView[i].userName+'</span></a></div><p class="desc"></p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.payView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.payView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">已关注</a><a href="message.html?u='+data.payView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}
										}else{
											if(data.payView[i].isTogetherPay=="1"){
												document.getElementById("zhao4").getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="pay'+data.payView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><img src="'+data.payView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><span class="nickname">'+data.payView[i].userName+'</span></a><ul class="icon-list"><li class="u-icon imooc-teacher"></li></ul></div><p class="desc">'+data.payView[i].work+'</p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.payView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.payView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">互相关注</a><a href="message.html?u='+data.payView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}else{
												document.getElementById("zhao4").getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="pay'+data.payView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><img src="'+data.payView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><span class="nickname">'+data.payView[i].userName+'</span></a><ul class="icon-list"><li class="u-icon imooc-teacher"></li></ul></div><p class="desc">'+data.payView[i].work+'</p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.payView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.payView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">已关注</a><a href="message.html?u='+data.payView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}
										}
									}
								}
							}
						}
					}
				})
			}
			function beiguanzhubutton(){
				zhao1.style.display="none";
				zhao2.style.display="none";
				zhao3.style.display="none";
				zhao4.style.display="block";
				document.getElementById("investment_title").getElementsByTagName("div")[0].className="";
				document.getElementById("investment_title").getElementsByTagName("div")[1].className="on";
				document.getElementById("zhao4").getElementsByClassName("article-main")[0].style.display="none";
				document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].style.display="block";
				//获取粉丝
				var userId = document.getElementsByClassName("user-info")[0].id;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getbePay.html",
					data:{
						"userId":userId
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.bepayView==""){
								document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].innerHTML='<div class="ques-list"><div class="nodata">您还没有任何粉丝关注</div></div>';
							}else{
								for(var i in data.bepayView){
									if(document.getElementById("bepay"+data.bepayView[i].userId)==null){
										var html = document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML;
										if(data.bepayView[i].work==null){
											if(data.bepayView[i].isTogetherPay=="1"){
												document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="bepay'+data.bepayView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><img src="'+data.bepayView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><span class="nickname">'+data.bepayView[i].userName+'</span></a></div><p class="desc"></p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.bepayView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.bepayView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">互相关注</a><a href="message.html?u='+data.bepayView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}else{
												document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="bepay'+data.bepayView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><img src="'+data.bepayView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><span class="nickname">'+data.bepayView[i].userName+'</span></a></div><p class="desc"></p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.bepayView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.bepayView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">已关注</a><a href="message.html?u='+data.bepayView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}
										}else{
											if(data.bepayView[i].isTogetherPay=="1"){
												document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="bepay'+data.bepayView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><img src="'+data.bepayView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><span class="nickname">'+data.bepayView[i].userName+'</span></a><ul class="icon-list"><li class="u-icon imooc-teacher"></li></ul></div><p class="desc">'+data.bepayView[i].work+'</p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.bepayView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.bepayView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">互相关注</a><a href="message.html?u='+data.bepayView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}else{
												document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="bepay'+data.bepayView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><img src="'+data.bepayView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><span class="nickname">'+data.bepayView[i].userName+'</span></a><ul class="icon-list"><li class="u-icon imooc-teacher"></li></ul></div><p class="desc">'+data.bepayView[i].work+'</p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.bepayView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.bepayView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">已关注</a><a href="message.html?u='+data.bepayView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}
										}
									}
								}
							}
						}
					}
				})
			}
			function getMoreIndex(){
				var userId = document.getElementsByClassName("user-info")[0].id;
	    		var time1=document.getElementById("notices").getElementsByClassName("time")[0].innerHTML;
	    		var time2=document.getElementById("notices").getElementsByClassName("time")[0].innerHTML;
	    		var time3=document.getElementById("notices").getElementsByClassName("time")[0].innerHTML;
	    		var time11=document.getElementById("notices").getElementsByClassName("time")[0].innerHTML;
	    		var time22=document.getElementById("notices").getElementsByClassName("time")[0].innerHTML;
	    		var time33=document.getElementById("notices").getElementsByClassName("time")[0].innerHTML;
	    		//获取作者的关注
	    		var size1 = document.getElementById("notices").getElementsByClassName("item newuser").length;
	    		if(size1!=0){
		    		for(var i=0;i<size1;i++){
		    			var url = document.getElementById("notices").getElementsByClassName("item newuser")[i].getElementsByClassName("fl")[0].href;
		    			var touserId = url.split("u=")[1];
		    			if(userId==touserId){
			    			var time1_1 = document.getElementById("notices").getElementsByClassName("item newuser")[i].getElementsByClassName("time")[0].innerHTML;
							if((new Date(time1_1).getTime()) <(new Date(time1).getTime())){
								time1 = time1_1;
							}
		    			}else{
		    				var time11_11 = document.getElementById("notices").getElementsByClassName("item newuser")[i].getElementsByClassName("time")[0].innerHTML;
		    				if((new Date(time11_11).getTime()) <(new Date(time11).getTime())){
								time11 = time11_11;
							}
		    			}
		    		}
	    		}
	    		//获取作者的faq
	    		var size2 = document.getElementById("notices").getElementsByClassName("item newarticlescrap").length;
	    		if(size2!=0){
	    			for(var i=0;i<size2;i++){
	    				var url = document.getElementById("notices").getElementsByClassName("item newarticlescrap")[i].getElementsByClassName("fl")[0].href;
	    				var touserId = url.split("u=")[1];
	    				if(userId==touserId){
	    					var time2_2 = document.getElementById("notices").getElementsByClassName("item newarticlescrap")[i].getElementsByClassName("time")[0].innerHTML;
	    					if((new Date(time2_2).getTime()) <(new Date(time2).getTime())){
								time2 = time2_2;
							}
	    				}else{
	    					var time22_22 = document.getElementById("notices").getElementsByClassName("item newuser")[i].getElementsByClassName("time")[0].innerHTML;
		    				if((new Date(time22_22).getTime()) <(new Date(time22).getTime())){
								time22 = time22_22;
							}
	    				}
	    			}
	    		}
	    		//获取作者的community
	    		var size3 = document.getElementById("notices").getElementsByClassName("item newanswer").length;
	    		if(size3!=0){
	    			var time3_3;
	    			for(var i=0;i<size3;i++){
	    				var url = document.getElementById("notices").getElementsByClassName("item newanswer")[i].getElementsByClassName("fl")[0].href;
	    				var touserId = url.split("u=")[1];
	    				if(userId==touserId){
	    					var time3_3 = document.getElementById("notices").getElementsByClassName("item newanswer")[i].getElementsByClassName("time")[0].innerHTML;
	    					if((new Date(time3_3).getTime()) <(new Date(time3).getTime())){
								time3 = time3_3;
							}
	    				}else{
	    					var time33_33 = document.getElementById("notices").getElementsByClassName("item newanswer")[i].getElementsByClassName("time")[0].innerHTML;
	    					if((new Date(time33_33).getTime()) <(new Date(time33).getTime())){
								time33 = time33_33;
							}
	    				}
	    			}
	    		}
	    		
		    	$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getMoreIndex.html",
					data:{
						"time1":time1,
						"time2":time2,
						"time3":time3,
						"time11":time11,
						"time22":time22,
						"time33":time33
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else{
							if(data.personalIndexSize>=5){
								for(var i=0;i<5;i++){
									if(data.personalIndex[i].how=="创建了知识"||data.personalIndex[i].how=="修改了知识"||data.personalIndex[i].how=="推荐了知识"){
										document.getElementById("getMoreIndex").remove();
										var html = document.getElementById("notices").innerHTML;
										document.getElementById("notices").innerHTML = html + '<div class="item newarticlescrap"> <span class=" pa share js-share"></span> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'"  target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <a href="faq3.html?q='+data.personalIndex[i].questionId+'" target="_blank"> <div class="body pr"> <div class="content"> <div class="subtitle">'+data.personalIndex[i].title+'</div> <div class="detail ">'+data.personalIndex[i].content+'</div> <div class="cb"></div> </div> </div> </a> </div> <div class="cb"></div> </div>';
										var htmls = document.getElementById("zhao1").innerHTML;
										document.getElementById("zhao1").innerHTML = htmls + '<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreIndex"> <span class="js-dynamicLoad " onclick="getMoreIndex()">下拉显示更多</span> </p>';
									}else if(data.personalIndex[i].how=="在问吧提问"||data.personalIndex[i].how=="推荐了问题"){
										document.getElementById("getMoreIndex").remove();
										var html = document.getElementById("notices").innerHTML;
										document.getElementById("notices").innerHTML = html + '<div class="item newanswer"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'"  target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="question.html?c='+data.personalIndex[i].from+'&type=all" target="_blank"> <img src="'+data.personalIndex[i].fromImage+'" width="150" height="110"> </a><div class="content"> <div class="tag"> <span>来自</span> <a href="question.html?c='+data.personalIndex[i].from+'&type=all" target="_blank"> <span class="ml10">'+data.personalIndex[i].from+'</span> </a> </div> <div class="subtitle"> <a href="question2.html?q='+data.personalIndex[i].questionId+'" target="_blank">'+data.personalIndex[i].content+'</a> </div> </div> </div> </div> <div class="cb"></div> </div>';
										var htmls = document.getElementById("zhao1").innerHTML;
										document.getElementById("zhao1").innerHTML = htmls + '<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreIndex"> <span class="js-dynamicLoad " onclick="getMoreIndex()">下拉显示更多</span> </p>';
									}else if(data.personalIndex[i].how=="关注了用户"){
										document.getElementById("getMoreIndex").remove();
										var html = document.getElementById("notices").innerHTML;
										if(data.personalIndex[i].touserSex=="男"){
											document.getElementById("notices").innerHTML = html + '<div class="item newuser"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].touserId+'" target="_blank"> <img class=" js-usercard-dialog   mr10" src="'+data.personalIndex[i].touserImage+'" width="40" height="40"> </a> <div class="content"> <div class="subtitle"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].touserId+'"  target="_blank">'+data.personalIndex[i].touserName+'</a> </div> <div class="tag "> <span class="gender mr10"></span>  <span class="mr10">'+data.personalIndex[i].touserAddress.split("0")[1].split("1")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("1")[1].split("2")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("2")[1].split("3")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserJob+'</span> </div> </div> </div> </div> <div class="cb"></div> </div>';
										}else if(data.personalIndex[i].touserSex=="女"){
											document.getElementById("notices").innerHTML = html + '<div class="item newuser"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].touserId+'" target="_blank"> <img class=" js-usercard-dialog   mr10" src="'+data.personalIndex[i].touserImage+'" width="40" height="40"> </a> <div class="content"> <div class="subtitle"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].touserId+'"  target="_blank">'+data.personalIndex[i].touserName+'</a> </div> <div class="tag "> <span class="gender girl mr10"></span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("0")[1].split("1")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("1")[1].split("2")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("2")[1].split("3")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserJob+'</span> </div> </div> </div> </div> <div class="cb"></div> </div>';
										}else{
											document.getElementById("notices").innerHTML = html + '<div class="item newuser"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].touserId+'" target="_blank"> <img class=" js-usercard-dialog   mr10" src="'+data.personalIndex[i].touserImage+'" width="40" height="40"> </a> <div class="content"> <div class="subtitle"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].touserId+'"  target="_blank">'+data.personalIndex[i].touserName+'</a> </div> <div class="tag "> <span class="sexSecret mr10"></span><span class="mr10">'+data.personalIndex[i].touserAddress.split("0")[1].split("1")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("1")[1].split("2")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("2")[1].split("3")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserJob+'</span> </div> </div> </div> </div> <div class="cb"></div> </div>';
										}
										var htmls = document.getElementById("zhao1").innerHTML;
										document.getElementById("zhao1").innerHTML = htmls + '<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreIndex"> <span class="js-dynamicLoad " onclick="getMoreIndex()">下拉显示更多</span> </p>';
									}
								}
							}else{
								document.getElementById("getMoreIndex").remove();
								for(var i in data.personalIndex){
									if(data.personalIndex[i].how=="创建了知识"||data.personalIndex[i].how=="修改了知识"||data.personalIndex[i].how=="推荐了知识"){
										var html = document.getElementById("notices").innerHTML;
										document.getElementById("notices").innerHTML = html + '<div class="item newarticlescrap"> <span class=" pa share js-share"></span> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'"  target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <a href="faq3.html?q='+data.personalIndex[i].questionId+'" target="_blank"> <div class="body pr"> <div class="content"> <div class="subtitle">'+data.personalIndex[i].title+'</div> <div class="detail ">'+data.personalIndex[i].content+'</div> <div class="cb"></div> </div> </div> </a> </div> <div class="cb"></div> </div>';
									}else if(data.personalIndex[i].how=="在问吧提问"||data.personalIndex[i].how=="推荐了问题"){
										var html = document.getElementById("notices").innerHTML;
										document.getElementById("notices").innerHTML = html + '<div class="item newanswer"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'"  target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="question.html?c='+data.personalIndex[i].from+'&type=all" target="_blank"> <img src="'+data.personalIndex[i].fromImage+'" width="150" height="110"> </a><div class="content"> <div class="tag"> <span>来自</span> <a href="question.html?c='+data.personalIndex[i].from+'&type=all" target="_blank"> <span class="ml10">'+data.personalIndex[i].from+'</span> </a> </div> <div class="subtitle"> <a href="question2.html?q='+data.personalIndex[i].questionId+'" target="_blank">'+data.personalIndex[i].content+'</a> </div> </div> </div> </div> <div class="cb"></div> </div>';
									}else if(data.personalIndex[i].how=="关注了用户"){
										var html = document.getElementById("notices").innerHTML;
										if(data.personalIndex[i].touserSex=="男"){
											document.getElementById("notices").innerHTML = html + '<div class="item newuser"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].touserId+'" target="_blank"> <img class=" js-usercard-dialog   mr10" src="'+data.personalIndex[i].touserImage+'" width="40" height="40"> </a> <div class="content"> <div class="subtitle"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].touserId+'"  target="_blank">'+data.personalIndex[i].touserName+'</a> </div> <div class="tag "> <span class="gender mr10"></span>  <span class="mr10">'+data.personalIndex[i].touserAddress.split("0")[1].split("1")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("1")[1].split("2")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("2")[1].split("3")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserJob+'</span> </div> </div> </div> </div> <div class="cb"></div> </div>';
										}else if(data.personalIndex[i].touserSex=="女"){
											document.getElementById("notices").innerHTML = html + '<div class="item newuser"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].touserId+'" target="_blank"> <img class=" js-usercard-dialog   mr10" src="'+data.personalIndex[i].touserImage+'" width="40" height="40"> </a> <div class="content"> <div class="subtitle"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].touserId+'"  target="_blank">'+data.personalIndex[i].touserName+'</a> </div> <div class="tag "> <span class="gender girl mr10"></span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("0")[1].split("1")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("1")[1].split("2")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("2")[1].split("3")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserJob+'</span> </div> </div> </div> </div> <div class="cb"></div> </div>';
										}else{
											document.getElementById("notices").innerHTML = html + '<div class="item newuser"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].touserId+'" target="_blank"> <img class=" js-usercard-dialog   mr10" src="'+data.personalIndex[i].touserImage+'" width="40" height="40"> </a> <div class="content"> <div class="subtitle"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].touserId+'"  target="_blank">'+data.personalIndex[i].touserName+'</a> </div> <div class="tag "> <span class="sexSecret mr10"></span><span class="mr10">'+data.personalIndex[i].touserAddress.split("0")[1].split("1")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("1")[1].split("2")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("2")[1].split("3")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserJob+'</span> </div> </div> </div> </div> <div class="cb"></div> </div>';
										}
									}
								}
							}
						}
					}
				})
			}
			function getCollectFaq(){
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getCollectFaq.html",
					data:{
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.faqView==""){
								document.getElementById("zhao2_article-main2").innerHTML='<p class="notattend">你还没有收藏任何原创知识，快去<a href="faq.html" class="red" target="_blank">发表知识</a>吧</p>';
							}else{
								for(var i in data.faqView){
									if(document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0]==null){
										document.getElementById("zhao2_article-main2").innerHTML='<div  class="article-main"><div  class="articles-list"></div></div>';
									}
									if(document.getElementById("mycollec"+data.faqView[i].faqId)==null){
										var html = document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML;
										document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML=html + '<div class="list-item article-item" id="mycollec'+data.faqView[i].faqId+'"> <h3 class="item-title"> <a target="_blank" href="faq3.html?q='+data.faqView[i].faqId+'" class="title-detail">'+data.faqView[i].title+'</a></h3> <div class="zhao">'+data.faqView[i].content+'</div> <div class="item-btm clearfix"> <ul class="l left-info"> <li class="hd-pic"> <a class="publisher-name" href="personal2.html?u='+data.faqView[i].userId+'" target="_blank">'+data.faqView[i].username+'</a> </li> </ul> <div class="r right-info"> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].scanNumber+'浏览</em> </div> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].collectionNumber+'收藏</em> </div> <div class=" l"> <i class="icon sns-comment"></i><em> '+data.faqView[i].commentNumber+'评论</em> </div> </div> </div> </div>';
										var duo="";
										for(var j=0;j<4;j++){
											duo = duo + document.getElementById("zhao2_article-main2").getElementsByClassName("zhao")[i].getElementsByTagName("p")[j].outerHTML;
										}
										document.getElementById("zhao2_article-main2").getElementsByClassName("zhao")[i].innerHTML=duo;
									}
								}
								if(document.getElementById("getMoreCollection")==null){
									if(data.faqView[0].isMore=="1"){
										var html = document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML;
										document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreCollection"> <span class="js-dynamicLoad " onclick="getMoreCollectFaq()">点击显示更多</span> </p>';
									}
								}
							}
						}
					}
				})
			}
			function getMoreCollectFaq(){
				var startnumber = document.getElementById("zhao2_article-main2").getElementsByClassName("list-item article-item").length;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getMoreCollectFaq.html",
					data:{
						"startnumber":startnumber
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(document.getElementById("getMoreCollection")!=null){
								document.getElementById("getMoreCollection").remove();
							}
							var number = document.getElementById("zhao2_article-main2").getElementsByClassName("list-item article-item").length;
							for(var i in data.faqView){
								if(document.getElementById("mycollec"+data.faqView[i].faqId)==null){
									var html = document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML;
									document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML=html + '<div class="list-item article-item" id="mycollec'+data.faqView[i].faqId+'"> <h3 class="item-title"> <a target="_blank" href="faq3.html?q='+data.faqView[i].faqId+'" class="title-detail">'+data.faqView[i].title+'</a></h3> <div class="zhao">'+data.faqView[i].content+'</div> <div class="item-btm clearfix"> <ul class="l left-info"> <li class="hd-pic"> <a class="publisher-name" href="personal2.html?u='+data.faqView[i].userId+'" target="_blank">'+data.faqView[i].username+'</a> </li> </ul> <div class="r right-info"> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].scanNumber+'浏览</em> </div> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].collectionNumber+'收藏</em> </div> <div class=" l"> <i class="icon sns-comment"></i><em> '+data.faqView[i].commentNumber+'评论</em> </div> </div> </div> </div>';
									var duo="";
									var addnumber = eval(number)+eval(i);
									for(var j=0;j<4;j++){
										duo = duo + document.getElementById("zhao2_article-main2").getElementsByClassName("zhao")[addnumber].getElementsByTagName("p")[j].outerHTML;
									}
									document.getElementById("zhao2_article-main2").getElementsByClassName("zhao")[addnumber].innerHTML=duo;
								}
							}
							if(document.getElementById("getMoreCollection")==null){
								if(data.faqView[0].isMore=="1"){
									var html = document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML;
									document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreCollection"> <span class="js-dynamicLoad " onclick="getMoreCollectFaq()">点击显示更多</span> </p>';
								}
							}
						}
					}
				})
			}
			function getCommentFaq(){
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getCommentFaq.html",
					data:{
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.faqView==""){
								document.getElementById("zhao2_article-main3").innerHTML='<p class="notattend">你还没有评论任何知识，快去<a href="faq.html" class="red" target="_blank">知识首页查看</a>吧</p>';
							}else{
								for(var i in data.faqView){
									if(document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0]==null){
										document.getElementById("zhao2_article-main3").innerHTML='<div  class="article-main"><div  class="articles-list"></div></div>';
									}
									if(document.getElementById("mycomment"+data.faqView[i].replyId)==null){
										var html = document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML;
										document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML=html + '<div class="list-item article-item" id="mycomment'+data.faqView[i].replyId+'"> <h3 class="item-title"> <a target="_blank" href="faq3.html?q='+data.faqView[i].faqId+'" class="title-detail">'+data.faqView[i].title+'</a></h3> <div class="zhao">'+data.faqView[i].content+'</div> <div class="duo"> <div class="answer-con"> <div class="user">我的回答</div><div class="answer-content"> <a href="faq3.html?p='+data.faqView[i].parentId+'&q='+data.faqView[i].faqId+'" target="_blank" style="color:#0683ff">'+data.faqView[i].reply+'</a> </div> </div> <div class="about-info"> <span class="time">'+data.faqView[i].replytime.substring(0,16)+'</span> <a href="faq3.html?p='+data.faqView[i].parentId+'&q='+data.faqView[i].faqId+'" target="_blank" class="reply-num">'+data.faqView[i].replyNumber+'个回复</a> </div></div> <div class="item-btm clearfix"> <ul class="l left-info"> <li class="hd-pic"> <a class="publisher-name" href="personal2.html?u='+data.faqView[i].userId+'" target="_blank">'+data.faqView[i].username+'</a> </li> </ul> <div class="r right-info"> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].scanNumber+'浏览</em> </div> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].collectionNumber+'收藏</em> </div> <div class=" l"> <i class="icon sns-comment"></i><em> '+data.faqView[i].commentNumber+'评论</em> </div> </div> </div> </div>';
										var duo="";
										for(var j=0;j<2;j++){
											duo = duo + document.getElementById("zhao2_article-main3").getElementsByClassName("zhao")[i].getElementsByTagName("p")[j].outerHTML;
										}
										document.getElementById("zhao2_article-main3").getElementsByClassName("zhao")[i].innerHTML=duo+"........";
									}
								}
								if(document.getElementById("getMoreCollection")==null){
									if(data.faqView[0].isMore=="1"){
										var html = document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML;
										document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreComment"> <span class="js-dynamicLoad " onclick="getMoreCommentFaq()">点击显示更多</span> </p>';
									}
								}
							}
						}
					}
				})
			}
			function getMoreCommentFaq(){
				var startnumber = document.getElementById("zhao2_article-main3").getElementsByClassName("list-item article-item").length;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getMoreCommentFaq.html",
					data:{
						"startnumber":startnumber
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(document.getElementById("getMoreComment")!=null){
								document.getElementById("getMoreComment").remove();
							}
							var number = document.getElementById("zhao2_article-main3").getElementsByClassName("list-item article-item").length;
							for(var i in data.faqView){
								if(document.getElementById("mycomment"+data.faqView[i].faqId)==null){
									var html = document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML;
									document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML=html + '<div class="list-item article-item" id="mycomment'+data.faqView[i].replyId+'"> <h3 class="item-title"> <a target="_blank" href="faq3.html?q='+data.faqView[i].faqId+'" class="title-detail">'+data.faqView[i].title+'</a></h3> <div class="zhao">'+data.faqView[i].content+'</div> <div class="duo"> <div class="answer-con"> <div class="user">我的回答</div><div class="answer-content"> <a href="faq3.html?p='+data.faqView[i].parentId+'&q='+data.faqView[i].faqId+'" target="_blank" style="color:#0683ff">'+data.faqView[i].reply+'</a> </div> </div> <div class="about-info"> <span class="time">'+data.faqView[i].replytime.substring(0,16)+'</span> <a href="faq3.html?p='+data.faqView[i].parentId+'&q='+data.faqView[i].faqId+'" target="_blank" class="reply-num">'+data.faqView[i].replyNumber+'个回复</a> </div></div> <div class="item-btm clearfix"> <ul class="l left-info"> <li class="hd-pic"> <a class="publisher-name" href="personal2.html?u='+data.faqView[i].userId+'" target="_blank">'+data.faqView[i].username+'</a> </li> </ul> <div class="r right-info"> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].scanNumber+'浏览</em> </div> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].collectionNumber+'收藏</em> </div> <div class=" l"> <i class="icon sns-comment"></i><em> '+data.faqView[i].commentNumber+'评论</em> </div> </div> </div> </div>';
									var duo="";
									var addnumber = eval(number)+eval(i);
									for(var j=0;j<2;j++){
										duo = duo + document.getElementById("zhao2_article-main3").getElementsByClassName("zhao")[addnumber].getElementsByTagName("p")[j].outerHTML;
									}
									document.getElementById("zhao2_article-main3").getElementsByClassName("zhao")[addnumber].innerHTML=duo;
								}
							}
							if(document.getElementById("getMoreComment")==null){
								if(data.faqView[0].isMore=="1"){
									var html = document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML;
									document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreComment"> <span class="js-dynamicLoad " onclick="getMoreCommentFaq()">点击显示更多</span> </p>';
								}
							}
						}
					}
				})
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
	    		if(document.URL.indexOf("u=")>0){
	    			if(document.getElementById("duoduo").innerHTML==document.URL.split("u=")[1]){
						window.location.href = document.URL.split("?u=")[0];		    			
	    			}else{
	    				document.URL = document.URL;
	    			}
	    		}
	    	})
	    </script>
	    <div id="duoduo" style="display:none">${uid}</div>
</body>
</html>
