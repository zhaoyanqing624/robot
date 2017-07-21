
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
			var replyNumber = decodeURIComponent(event.get("messageNumber"));
			var jsonresult = strToJson(result);
			var jsonreplyNumber = strToJson(replyNumber);
			function strToJson(str){ 
				return JSON.parse(str); 
			}
			for(var i in jsonresult){
				var content = jsonresult[i].content;
				var lastContent = jsonresult[i].lastContent;
				var messageId = jsonresult[i].messageId;
				var time = jsonresult[i].time;
				time = time.split('');
				time.splice(10,1,' ');
				time = time.join('');
				var userId = jsonresult[i].userId;
				var userImage = jsonresult[i].userImage;
				if(lastContent.length<30){
					lastContent = lastContent;
				}else{
					lastContent = lastContent.substr(0,30)+"...";
				}	
				if(document.getElementById("lastChat"+userId).className!="active"){
					document.getElementById("messagenumber"+userId).innerHTML = "("+jsonreplyNumber+")";
					document.getElementById("lastChat"+userId).getElementsByClassName("theLastMsg")[0].innerHTML = lastContent;
				}
				
				if(document.getElementById(messageId)==null){
					var html = document.getElementById("userchatUl"+userId).innerHTML;
					if(userId==document.getElementById("zhao_hidden").innerHTML){
						document.getElementById("userchatUl"+userId).innerHTML = html + '<li class="me"  id="'+messageId+'"><div class="chat_avata"><a href="personal2.html?u='+userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+userImage+'"></a></div><div class="a_msg_info"><pre>'+content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+time+'</small></li>';
					}else{
						document.getElementById("userchatUl"+userId).innerHTML = html + '<li class="you" id="'+messageId+'"><div class="chat_avata"><a href="personal2.html?u='+userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+userImage+'"></a></div><div class="a_msg_info"><pre>'+content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+time+'</small></li>';
					}
				}
			}
		    // 离开    
		    // PL.leave();    
		}   
	</script> 
</head>
<body>
	<!-- 头部开始 -->
	<div class="header" id="head">      
       <#include "inc/incTop.ftl">
    </div>
	<!-- 头部结束 -->
	
	<!-- 主体开始 -->
	<section id="shortcodes">
		<div id="main" style="min-height:825px">

			<div class="container">
    			<!-- 导航条  -->
    			<div class="header-banner clearfix">
        			<div class="nav-box l">
            			<ul id="navTab" class="n-tab clearfix">
    					<li><a id="not_new" href="notice.html">通知<span class="not-num">(2)</span></a></li>
    					<li data-index="0" class="active"><a id="msg_new" href="message.html" class="">私信<span class="msg-num" style="display: none;"></span></a></li>
						</ul>        		
					</div>
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
					                    <li id="lastChatfa2f2884-985d-44e0-89b8-0454d0feaeac" class=""> 							
					                    	<div class="list-box">
					                    		<div class="fa fa-caret-down msg-more js-msg-more" style="color: rgb(147, 153, 159);"></div>							
					                    		<img src="/org.xjtusicd3.partner/robot/user/1134955889@qq.com/userImage/20170505/75fSVk.png" alt="luckyforever" width="40" height="40"> 					     	
					                    		<div class="info"><h5>西安烟草知识库
					                    			<span id="messagenumberfa2f2884-985d-44e0-89b8-0454d0feaeac"></span></h5>
					                    			<p class="theLastMsg"></p></div>	
					                    	</div>
					                    	<div class="more-box" style="left: 246px; display: none;">            					
					                    		<span class="shield js-shield">关闭聊天</span>        					
					                    	</div>
					                    </li>
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
			            <div class="scroll-wrapper scrollbar-macosx" style="position: relative;"><div id="chat_content" class="scrollbar-macosx scroll-content" style="height: auto; margin-bottom: 0px; margin-right: 0px; max-height: 0px;">
				            		<ul id="userchatUlfa2f2884-985d-44e0-89b8-0454d0feaeac" class="userchatUl" style="display:none"></ul>
			            	
						</div><div class="scroll-element scroll-x"><div class="scroll-element_outer"><div class="scroll-element_size"></div><div class="scroll-element_track"></div><div class="scroll-bar" style="width: 96px;"></div></div></div><div class="scroll-element scroll-y"><div class="scroll-element_outer"><div class="scroll-element_size"></div><div class="scroll-element_track"></div><div class="scroll-bar"></div></div></div></div>  <!-- 聊天内容显示区 -->
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
			                                    <textarea class="chatInput" id="textInput" type="text" maxlength="300" placeholder="输入您要发送的私信..." style="overflow-x: hidden; word-wrap: break-word; height: 66px;"></textarea>
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
    <!-- 聊天大容器 end -->
	<!-- 主体结束 -->
    
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
        	<div class="shield-item-list clearfix"></div>
        	<div class="no-shield-item">
            	<p>暂无屏蔽</p>
        	</div>
        </div>
	</div>
	
	<div class="setup-coverLayer"></div>
    </section>    
		
    <!-- 底部开始 -->
	<#include "/inc/incFoot.ftl">
	<!-- 底部结束 -->
    
    <script type="text/javascript" src="new/front/js/util.js"></script>
    <script src="js/autosize.js"></script>
	<script src="js/view/message.js"></script>
    <div id="zhao_hidden" style="display:none" class="0">${uid}</div>
</body>
</html>
