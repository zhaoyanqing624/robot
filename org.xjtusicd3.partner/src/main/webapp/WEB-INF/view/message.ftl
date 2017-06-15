
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>智能小朵-知识库</title>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/websocket.js"></script>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="new/front/style/reset2.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util2.css" />
    <link href="css/main.css" rel="stylesheet">
    <link href="css/bootstrap.min2.css" rel="stylesheet">
    <link href="css/css.css" rel="stylesheet">
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="includes/style.css">
    <link rel="stylesheet" href="includes/prettify/prettify.css">
    <link rel="stylesheet" href="css/scrollbar.css">
    <script src="includes/prettify/prettify.js"></script>
    <script src="includes/jquery.js"></script>
    <script src="js/jquery.scrollbar.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function(){
            jQuery('.scrollbar-macosx').scrollbar();
        });
    </script>
    <script src="js/ajax-pushlet-client.js"></script>
    <script type="text/javascript">
    	PL.parameters.push('uid');
    	PL.parameters.push('${uid}');  
		PL._init();     
		PL.joinListen('/mipc/she');  
		function onData(event) {
			var result = decodeURIComponent(event.get("message"));
		    // 离开    
		    // PL.leave();    
		}   
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
    </div>
	
	<section id="shortcodes">
		<div id="main" style="min-height:825px">

<div class="container">
    <!-- 导航条  -->
    <div class="header-banner clearfix">
        <div class="nav-box l">
            <ul id="navTab" class="n-tab clearfix">
    <li>
        <a id="not_new" href="notice.html">通知<span class="not-num">(2)</span></a>
    </li>
    <li data-index="0" class="active">
        <a id="msg_new" href="message.html" class="">私信<span class="msg-num" style="display: none;"></span></a>
    </li>
</ul>        </div>
        
    </div>
    <!-- 导航条 end -->
    
    <!-- 聊天大容器 -->
    <div class="clearfix msgbox">
        <!-- 左侧面板 -->
        <div id="left_panel">
            <div class="left_panel_content">
                <!-- 搜索框 -->
                <div class="find-input-box">
                    <i class="fa fa-search"></i>
                    <input class="input js-input" placeholder="通过昵称快速搜索" type="text" autocomplete="off" value="">
                    <span class="icon-close2 btn-text-clear js-text-clear" title="清空"></span>
                </div>
                <!-- 搜索框 end -->
                <!-- 加载loading -->
                <div id="list_waper" class="clearfix ps-container">
                    <ul id="lastChat" class="user_list">
                        <div class="msg-loading" style="display: none;">
                            <div class="msg-loading-icon"></div>
                            <p>正在加载...</p>
                        </div>
                        <#if touserList ??>
	                        <#list touserList as touserList>
		                    <li id="lastChat${touserList.USERID}" class="active"> 							
		                    	<div class="list-box">							
		                    		<img src="${touserList.AVATAR}" alt="luckyforever" width="40" height="40"> 					     	
		                    		<div class="info"><h5>${touserList.USERNAME}</h5><p class="theLastMsg"></p></div>					     	
		                    	</div>
		                    </li>
		                    </#list>
		                    <#list messageList as messageList>
		                    <li id="lastChat${messageList.userId}" class=""> 							
		                    	<div class="list-box">
		                    		<div class="fa fa-caret-down msg-more js-msg-more" style="color: rgb(147, 153, 159);"></div>							
		                    		<img src="${messageList.userImage}" alt="luckyforever" width="40" height="40"> 					     	
		                    		<div class="info"><h5>${messageList.userName}
		                    			<#if messageList.number!=0>
		                    			<span id="messagenumber">(${messageList.number})</span></h5>
		                    			</#if>
		                    		<#if messageList.lastContent?length gt 30>
		                    			<p class="theLastMsg">${messageList.lastContent[0..30]}...</p></div>	
		                    		<#else>
		                    			<p class="theLastMsg">${messageList.lastContent}</p></div>	
		                    		</#if>
		                    	</div>
		                    </li>
		                    </#list>
	                    <#else>
		                    <#list messageList as messageList>
		                    <li id="lastChat${messageList.userId}" class=""> 							
		                    	<div class="list-box">
		                    		<div class="fa fa-caret-down msg-more js-msg-more" style="color: rgb(147, 153, 159);"></div>							
		                    		<img src="${messageList.userImage}" alt="luckyforever" width="40" height="40"> 					     	
		                    		<div class="info"><h5>${messageList.userName}
		                    			<#if messageList.number!=0>
		                    			<span id="messagenumber">(${messageList.number})</span></h5>
		                    			</#if>
		                    		<#if messageList.lastContent?length gt 30>
		                    			<p class="theLastMsg">${messageList.lastContent[0..30]}...</p></div>	
		                    		<#else>
		                    			<p class="theLastMsg">${messageList.lastContent}</p></div>	
		                    		</#if>
		                    	</div>
		                    </li>
		                    </#list>
	                    </#if>
                    </ul>
                <div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 3px; width: 320px;"><div class="ps-scrollbar-x" style="left: 0px; width: 0px;"></div></div><div class="ps-scrollbar-y-rail" style="top: 0px; right: 2px; height: 560px;"><div class="ps-scrollbar-y" style="top: 0px; height: 0px;"></div></div></div>
                <!-- 加载loading end -->
                <!-- 搜索历史 -->
                <div class="history-box">
                    <ul id="history-list"></ul>
                </div>
                <!-- 搜索历史 end -->
                <!-- 搜索结果 -->
                <div class="result-box js-result-box">
                    <ul id="result-list"></ul>
                </div>
                <!-- 搜索结果 end -->
                <div class="mask"></div>
            </div>
        </div>
        <!-- 聊天面板 -->
        <div id="chat_container" class="content">
        	<div class="demo">
            <div id="chat_content" class="scrollbar-macosx">
            	<#if touserList ??>
	                <#list touserList as touserList>
	            		<ul id="userchatUl${touserList.USERID}" class="userchatUl" style="display:block">
	            			
	            		</ul>
	            	</#list>
	            	<#list messageList as messageList>
	            		<ul id="userchatUl${messageList.userId}" class="userchatUl" style="display:block"></ul>
	            	</#list>
	            <#else>
	            	<#list messageList as messageList>
	            		<ul id="userchatUl${messageList.userId}" class="userchatUl" style="display:block"></ul>
	            	</#list>
            	</#if>
            	
            	<ul id="userchatUl10000" uid="10000" class="userchatUl" style="display:none">
            		<li><div class="timeLine"> <strong style="width:130px;">2016-07-26</strong></div> </li>
            	</ul>
			</div>  <!-- 聊天内容显示区 -->
			</div>
            <!-- 聊天input -->
			<div id="chat_editor" style="display: block;">
                <form method="post" action="/u/3674640/uploadimg?1497343130681" enctype="multipart/form-data" id="upLoadForm" target="imageFrame">
                    <table cellpadding="0" cellspacing="0">
                        <tbody><tr>
                            <th> <div class="attach"><a id="sendEmojiIcon" href="javascript:void(0)" onclick="return false" title="表情" style="margin-top:0"></a></div>
                            </th>
                            <th> <div class="chat_upImg" style="text-align:center">
                                </div>
                            </th>
                            <th> <div style="position:relative;width:490px;margin-top:8px;">
                                    <textarea class="chatInput" id="textInput" type="text" maxlength="300" placeholder="输入您要发送的私信..."></textarea>
                                    <div id="msg_upImg_box" style="height:62px;display:none"></div>
                                    <span id="imgDel" style="display:none;width:10px;height:10px;background:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKBAMAAAB/HNKOAAAAA3NCSVQICAjb4U/gAAAAHlBMVEXKytn////z8/bX1+PU1OD19fjb2+Xz8/f19fnZ2ePstdLlAAAACXBIWXMAAAsSAAALEgHS3X78AAAAHHRFWHRTb2Z0d2FyZQBBZG9iZSBGaXJld29ya3MgQ1M26LyyjAAAABZ0RVh0Q3JlYXRpb24gVGltZQAxMS8yNS8xM7kML+MAAAAvSURBVAiZY2AVFBRUYlBTFBQyYhByF1RRZBAsUUoSZBAUMiiEk2ARiGwYWCVYFwDX5gdZj1qR8wAAAABJRU5ErkJggg==) no-repeat 0 0;"></span> </div>
                            </th>
                            <th> <a class="chatSend btn btn-large btn-green" href="javascript:;" onclick="sendMessage()">发送</a> </th>
                        </tr>
                    </tbody></table>
                </form>
            </div>
            <!-- 聊天input end -->
        </div>
        <div id="editor_msg"></div>     <!-- 聊天提示信息 -->
    </div>
    <!-- 聊天大容器 end -->

    <div id="js-setup-popl" class="setup-popl">
    <div class="setup-popl-top clearfix">
        <span class="title">私信设置</span>
        <i class="icon-close close"></i>
    </div>
    
    <div class="setup-content">
        <dl>
            <dt class="clearfix">
                <span class="dt-tit">接收设置</span>
                <div class="dt-line"></div>
            </dt>
            <dd class="clearfix">
                <div class="dd-item clearfix">
                    <div class="switch on" data-setting="1">
                        <div class="pinkline"></div>
                        <span class="pinkround"></span>
                    </div>
                    
                    <p class="switchname">只接受相互关注人的私信</p>
                </div>
                <div class="tip">关闭后可接受所有粉丝的私信</div>
               
            </dd>
        </dl>

        <dl>
            <dt class="clearfix">
                <span class="dt-tit">屏蔽列表</span>
                <div class="dt-line"></div>
            </dt>
            <dd class="clearfix">
                <div class="tip shield-tip">屏蔽<span>0</span></div> 
            </dd>
        </dl>

        <div class="shield-item-list clearfix">

        </div>
        <div class="no-shield-item">
            <p>暂无屏蔽</p>
        </div>
    </div>

</div>
<div class="setup-coverLayer"></div></div>

</div>
    </section>    
		
    <div id="foot" class="footer">
    	<p style="color: #ffffff;text-align: center;">© 西安交通大学社会智能与复杂数据处理实验室  2017.</p>
    </div>
    <script type="text/javascript" src="new/front/js/util.js"></script>
    <script src="js/autosize.js"></script>
    <script>
    	$(document).ready(function(){
    		autosize(document.querySelectorAll('textarea'));
    	})
    	//好友私信列表显示
		$('#lastChat').find('li').click(function(){
			$(this).addClass("active").siblings("li").removeClass("active");
			var userid = $(this)[0].id.split("lastChat")[1];
			var usercontentid = "userchatUl"+userid;
			$('#'+usercontentid).css('display','block').siblings("ul").css('display','none');
			if(document.getElementById("messagenumber")!=null){
				document.getElementById("messagenumber").innerHTML="";
			}
			var touserId = document.getElementById("lastChat").getElementsByClassName("active")[0].id.split("lastChat")[1];
			//点击列表后开始查询
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/getMessage.html",
				data:{
					"touserId":touserId
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}else{
						if(data.messageContentList==""){
							if(data.messageHistory!=""){
								if(data.isMore=="1"){
									var html = document.getElementById("userchatUl"+touserId).innerHTML;
									if(document.getElementById("userchatUl"+touserId).getElementsByClassName("getmore")[0]==null){
										document.getElementById("userchatUl"+touserId).innerHTML ='<li><div class="getmore"><strong style="width:130px;" onclick="getMoreMessageHistory()">查看更多记录</strong></div></li>'+ html;
									}
									for(var j in data.messageHistory){
										if(data.messageHistory[j].userId==document.getElementById("zhao_hidden").innerHTML){
											if(document.getElementById(data.messageHistory[j].messageId)==null){
											 	if(document.getElementById(data.messageHistory[j].time.substring(0,10))==null){
											 		var htmls = document.getElementById("userchatUl"+touserId).innerHTML;
											 		document.getElementById("userchatUl"+touserId).innerHTML =htmls + '<li id="'+data.messageHistory[j].time.substring(0,10)+'"><div class="timeLine"><strong style="width:130px;">'+data.messageHistory[j].time.substring(0,10)+'</strong></div></li>';
											 	}
												var html = document.getElementById("userchatUl"+touserId).innerHTML;
												document.getElementById("userchatUl"+touserId).innerHTML = html+'<li class="me" id="'+data.messageHistory[j].messageId+'"><div class="chat_avata"><a href="personal2.html?u='+data.messageHistory[j].userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageHistory[j].userImage+'"></a></div><div class="a_msg_info"><pre>'+data.messageHistory[j].content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageHistory[j].time+'</small></li>';
											}
											
										}else{
											if(document.getElementById(data.messageHistory[j].messageId)==null){
												if(document.getElementById(data.messageHistory[j].time.substring(0,10))==null){
											 		var htmls = document.getElementById("userchatUl"+touserId).innerHTML;
											 		document.getElementById("userchatUl"+touserId).innerHTML =htmls + '<li id="'+data.messageHistory[j].time.substring(0,10)+'"><div class="timeLine"><strong style="width:130px;">'+data.messageHistory[j].time.substring(0,10)+'</strong></div></li>';
											 	}
												var html = document.getElementById("userchatUl"+touserId).innerHTML;
												document.getElementById("userchatUl"+touserId).innerHTML = html+'<li class="you" id="'+data.messageHistory[j].messageId+'"><div class="chat_avata"><a href="personal2.html?u='+data.messageHistory[j].userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageHistory[j].userImage+'"></a></div><div class="a_msg_info"><pre>'+data.messageHistory[j].content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageHistory[j].time+'</small></li>';
											}
										}
									}
								}else{
									for(var j in data.messageHistory){
										if(data.messageHistory[j].userId==document.getElementById("zhao_hidden").innerHTML){
											if(document.getElementById(data.messageHistory[j].messageId)==null){
												if(document.getElementById(data.messageHistory[j].time.substring(0,10))==null){
											 		var htmls = document.getElementById("userchatUl"+touserId).innerHTML;
											 		document.getElementById("userchatUl"+touserId).innerHTML =htmls + '<li id="'+data.messageHistory[j].time.substring(0,10)+'"><div class="timeLine"><strong style="width:130px;">'+data.messageHistory[j].time.substring(0,10)+'</strong></div></li>';
											 	}
												var html = document.getElementById("userchatUl"+touserId).innerHTML;
												document.getElementById("userchatUl"+touserId).innerHTML = html+'<li class="me" id="'+data.messageHistory[j].messageId+'"><div class="chat_avata"><a href="personal2.html?u='+data.messageHistory[j].userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageHistory[j].userImage+'"></a></div><div class="a_msg_info"><pre>'+data.messageHistory[j].content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageHistory[j].time+'</small></li>';
											}
										}else{
											if(document.getElementById(data.messageHistory[j].messageId)==null){
												if(document.getElementById(data.messageHistory[j].time.substring(0,10))==null){
											 		var htmls = document.getElementById("userchatUl"+touserId).innerHTML;
											 		document.getElementById("userchatUl"+touserId).innerHTML =htmls + '<li id="'+data.messageHistory[j].time.substring(0,10)+'"><div class="timeLine"><strong style="width:130px;">'+data.messageHistory[j].time.substring(0,10)+'</strong></div></li>';
											 	}
												var html = document.getElementById("userchatUl"+touserId).innerHTML;
												document.getElementById("userchatUl"+touserId).innerHTML = html+'<li class="you" id="'+data.messageHistory[j].messageId+'"><div class="chat_avata"><a href="personal2.html?u='+data.messageHistory[j].userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageHistory[j].userImage+'"></a></div><div class="a_msg_info"><pre>'+data.messageHistory[j].content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageHistory[j].time+'</small></li>';
											}
										}
									}
								}
							}
						}else{
							if(data.isMore=="1"){
								var html = document.getElementById("userchatUl"+touserId).innerHTML;
								document.getElementById("userchatUl"+touserId).innerHTML = html+'<li><div class="getmore"><strong style="width:130px;">查看更多记录</strong></div></li>';
								for(var i in data.messageContentList){
									var html = document.getElementById("userchatUl"+touserId).innerHTML;
									if(document.getElementById(data.messageContentList[i].messageId)==null){
										if(document.getElementById(data.messageContentList[i].time.substring(0,10))==null){
									 		var htmls = document.getElementById("userchatUl"+touserId).innerHTML;
									 		document.getElementById("userchatUl"+touserId).innerHTML =htmls + '<li id="'+data.messageContentList[i].time.substring(0,10)+'"><div class="timeLine"><strong style="width:130px;">'+data.messageContentList[i].time.substring(0,10)+'</strong></div></li>';
									 	}
										document.getElementById("userchatUl"+touserId).innerHTML = html + '<li class="you" id="'+data.messageContentList[i].messageId+'"><div class="chat_avata"><a href="personal2.html?u='+data.messageContentList[i].userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageContentList[i].userImage+'"></a></div><div class="a_msg_info" id="4426066"><pre>'+data.messageContentList[i].content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageContentList[i].time+'</small></li>';
									}
								}
							}else{
								for(var i in data.messageContentList){
									var html = document.getElementById("userchatUl"+touserId).innerHTML;
									if(document.getElementById(data.messageContentList[i].messageId)==null){
										if(document.getElementById(data.messageContentList[i].time.substring(0,10))==null){
									 		var htmls = document.getElementById("userchatUl"+touserId).innerHTML;
									 		document.getElementById("userchatUl"+touserId).innerHTML =htmls + '<li id="'+data.messageContentList[i].time.substring(0,10)+'"><div class="timeLine"><strong style="width:130px;">'+data.messageContentList[i].time.substring(0,10)+'</strong></div></li>';
									 	}
										document.getElementById("userchatUl"+touserId).innerHTML = html + '<li class="you" id="'+data.messageContentList[i].messageId+'"><div class="chat_avata"><a href="personal2.html?u='+data.messageContentList[i].userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageContentList[i].userImage+'"></a></div><div class="a_msg_info" id="4426066"><pre>'+data.messageContentList[i].content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageContentList[i].time+'</small></li>';
									}
								}
							}
						}
					}
				}
			});			
		})
    </script>
    <script>
    	function sendMessage(){
    		var content = document.getElementById("textInput").value;
    		var touserId = document.getElementById("lastChat").getElementsByClassName("active")[0].id.split("lastChat")[1];
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/saveMessage.html",
				data:{
					"content":content,
					"touserId":touserId
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}else{
						var html = document.getElementById("userchatUl"+touserId).innerHTML;
						document.getElementById("userchatUl"+touserId).innerHTML= html + '<li class="me"><div class="chat_avata"><a href="personal2.html?u='+data.messageList.userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageList.userImage+'"></a></div><div class="a_msg_info"><pre>'+data.messageList.content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageList.time+'</small></li>';
						document.getElementById("textInput").value="";
					}
				}
			})
    	}
    	function getMoreMessageHistory(){
    		var touserId = document.getElementById("lastChat").getElementsByClassName("active")[0].id.split("lastChat")[1];
    		var date = document.getElementById("userchatUl"+touserId).getElementsByClassName("time")[0].innerHTML;
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/getMoreMessageHistory.html",
				data:{
					"date":date,
					"touserId":touserId
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}else{
						var html = document.getElementById("userchatUl"+touserId).innerHTML;
						if(data.messageHistory!=""){
							for(var j in data.messageHistory){
								if(data.messageHistory[j].userId==document.getElementById("zhao_hidden").innerHTML){
									if(document.getElementById(data.messageHistory[j].messageId)==null){
									 	if(document.getElementById(data.messageHistory[j].time.substring(0,10))==null){
									 		var htmls = document.getElementById("userchatUl"+touserId).innerHTML;
									 		document.getElementById("userchatUl"+touserId).innerHTML ='<li id="'+data.messageHistory[j].time.substring(0,10)+'"><div class="timeLine"><strong style="width:130px;">'+data.messageHistory[j].time.substring(0,10)+'</strong></div></li>'+htmls;
									 	}
										var html = document.getElementById("userchatUl"+touserId).innerHTML;
										document.getElementById("userchatUl"+touserId).innerHTML = '<li class="me" id="'+data.messageHistory[j].messageId+'"><div class="chat_avata"><a href="personal2.html?u='+data.messageHistory[j].userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageHistory[j].userImage+'"></a></div><div class="a_msg_info"><pre>'+data.messageHistory[j].content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageHistory[j].time+'</small></li>'+htmls;
									}
								}else{
									if(document.getElementById(data.messageHistory[j].messageId)==null){
										if(document.getElementById(data.messageHistory[j].time.substring(0,10))==null){
									 		var htmls = document.getElementById("userchatUl"+touserId).innerHTML;
									 		document.getElementById("userchatUl"+touserId).innerHTML ='<li id="'+data.messageHistory[j].time.substring(0,10)+'"><div class="timeLine"><strong style="width:130px;">'+data.messageHistory[j].time.substring(0,10)+'</strong></div></li>'+htmls;
									 	}
										var html = document.getElementById("userchatUl"+touserId).innerHTML;
										document.getElementById("userchatUl"+touserId).innerHTML = '<li class="you" id="'+data.messageHistory[j].messageId+'"><div class="chat_avata"><a href="personal2.html?u='+data.messageHistory[j].userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageHistory[j].userImage+'"></a></div><div class="a_msg_info"><pre>'+data.messageHistory[j].content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageHistory[j].time+'</small></li>'+htmls;
									}
								}
							}
						}
					}
				}
			})
    	}
    </script>
    <div id="zhao_hidden" style="display:none">${uid}</div>
</body>
</html>
