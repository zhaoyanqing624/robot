
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>智能小朵-知识库</title>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <script src="js/jquery-3.1.1.min.js"></script>
    <script>
    /**
 * 
 */
var websocket = null;
var username = null;
function connect() {
	$("#chat_container").show();
	// 判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket(
				"ws://localhost:8080/org.xjtusicd3.partner/websocketserver");
	} else {
		alert('Not support websocket')
	}

	// 连接发生错误的回调方法
	websocket.onerror = function(e) {
		setMessageInnerHTML("error" + e);
	};

	// 连接成功建立的回调方法
	websocket.onopen = function(event) {
		$.ajax({
			type:"GET",
			url:"/org.xjtusicd3.partner/getUserName.html",
			dataType:"json",
			success:function(data){
				username = data[0].uSERNAME;
				send("{username:'" + username + "',type:1}");
			}
		})
	}

	// 接收到消息的回调方法
	websocket.onmessage = function(event) {
		var onlinelisthtml = $("#lastChat10000").html();
		var datajson = eval('(' + event.data + ')');
		switch (datajson.type) {
		case 1:
			for (var u = 0; u < datajson.onlinelist.length; u++)
				if (datajson.onlinelist[u] != datajson.username)
					if (onlinelisthtml.indexOf("chat-" + datajson.onlinelist[u]) < 0)
						$.ajax({
							type:"POST",
							url:"/org.xjtusicd3.partner/getUserInfo.html",
							data:{
								"username":datajson.onlinelist[u],
							},
							dataType:"json",
							success:function(data){
								$("#lastChat10000").append("<div class='list-box'><img src='"+data[0].aVATAR+"'  width='40' height='40'><div class='info'><h5>"+ data[0].uSERNAME+ "</h5></div></div>");
								$("#chat_content").append("<ul class='userchatUl'><li><div class='timeLine'><strong style='width:130px;'>2016-07-16</strong></div> </li></ul>")							
							}
						})
			break;
		case 2:
			if (datajson.isSelf == true) {
				$(".chat-" + datajson.sendto)
						.append(
								"<div class='say-box'><span class='say-username-left'>我</span><span class='say-container-left'>"
										+ datajson.content + "</span></div>");
				$(".chat-" + datajson.sendto).scrollTop(
						$(".chat-" + datajson.sendto)[0].scrollHeight);
			} else if (datajson.isSelf == false) {
				if (!$(".list-item-" + datajson.username).hasClass("bg-color"))
					if($(".list-item-" + datajson.username).children("span").html().indexOf("point")<0)
					$(".list-item-" + datajson.username).children("span")
							.append("<i class='point'></i>");
				$(".chat-" + datajson.username).append(
						"<div class='say-box'><span class='say-username-right'>"
								+ datajson.username
								+ "</span><span class='say-container-right'>"
								+ datajson.content + "</span></div>");
				$(".chat-" + datajson.username).scrollTop(
						$(".chat-" + datajson.username)[0].scrollHeight);
			}
			break;
		case 4:
			$(".chat-box").each(
					function() {
						$(this).append(
								"<div class='msg'>" + datajson.username
										+ "下线了！</div>");
					});
			$(".list-item-" + datajson.username).remove();
			if ($("#online-list").html().indexOf("bg-color") < 0) {
				$(".title").text("");
				$("#content").blur();
				$("#content").click(function() {
					$(this).blur();
				});
			}
			break;
		}
	}

	// 连接关闭的回调方法
	websocket.onclose = function() {
		send("{username:'" + $("#username").val() + "',type:4}");
	}

	// 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	window.onbeforeunload = function() {
		websocket.close();
	}
}

// 关闭连接
function closeWebSocket() {
	websocket.close();
}

// 发送消息
function send(message) {
	websocket.send(message);
}

$(document).ready(
		function() {
				connect();
			$("#send").click(
					function() {
						send("{username:'" + $("#username").val()
								+ "',type:2,content:'" + $("#content").val()
								+ "',sendto:'"
								+ $(".bg-color").eq(0).find("span").html()
								+ "'}");
						$("#content").val("");
					});
		});
$(document).ready(function() {
	$("#lastChat10000").delegate(".list-box", "click", function() {
		$(".list-box").each(function() {
			$(this).removeClass("bg-color");
			$(this).find(".chat-box").hide();
		});
		$(this).addClass("bg-color");
		$(".title").text($(this).children("span").text());
		$(this).find(".chat-box").show();
		$("#content").unbind("click");
		$(this).children("span").find("i").remove();
	});
	$("#content").keyup(function(event) {
		if (event.keyCode == 13)
			$("#send").click();
	});
	if ($("#online-list").html().indexOf("bg-color") < 0)
		$("#content").click(function() {
			$(this).blur();
		});
});
    </script>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="new/front/style/reset2.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util2.css" />
    <link href="css/main.css" rel="stylesheet">
    <link href="css/bootstrap.min2.css" rel="stylesheet">
    <link href="css/css.css" rel="stylesheet">
    <link rel="stylesheet" href="css/all.css">
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
	                    <li id="lastChat10000" uid="10000"> 							
	                    	<div class="list-box">							
	                    		<img src="http://img.mukewang.com/user/57a322f00001e4ae02560256-40-40.jpg" alt="女神" width="40" height="40"> 					     	
	                    		<div class="info"><h5>女神</h5><p class="theLastMsg">各位小伙伴们~大家学习的怎么样了呢？如果有什么意见和建议欢迎大家随时提出来哦~~另外，马上就要过年了。，到底是什么呢？我们一起来看一下吧~在移动互联网如此火爆的今天，你是不是也有过想入行却没有人领路的痛苦呢？之《零基础入门Android语法与界面》来学习吧!传送门：http://class.imooc.com/sc/6 在这里你将获得1V1的专业教学团队的答疑支持，</p>
								</div>					     	
							</div>
							<div class="list-box">							
	                    		<img src="http://img.mukewang.com/user/57a322f00001e4ae02560256-40-40.jpg" alt="女神" width="40" height="40"> 					     	
	                    		<div class="info"><h5>女神</h5><p class="theLastMsg">各位小伙伴们~大家学习的怎么样了呢？如果有什么意见和建议欢迎大家随时提出来哦~~另外，马上就要过年了。，到底是什么呢？我们一起来看一下吧~在移动互联网如此火爆的今天，你是不是也有过想入行却没有人领路的痛苦呢？之《零基础入门Android语法与界面》来学习吧!传送门：http://class.imooc.com/sc/6 在这里你将获得1V1的专业教学团队的答疑支持，</p>
								</div>					     	
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
                    <div class="no-result">无检索结果</div>
                </div>
                <!-- 搜索结果 end -->
                <div class="mask"></div>
            </div>
        </div>
        <!-- 聊天面板 -->
        <div id="chat_container">
            <div id="chat_content" class="ps-container"> 
			</div>  <!-- 聊天内容显示区 -->
            <div class="no_friend_right"></div>   <!-- 默认底图 -->
            <!-- 聊天input -->
            <div id="chat_editor" style="display: block;">
                <form method="post" action="/u/3674640/uploadimg?1489304166966" enctype="multipart/form-data" id="upLoadForm" target="imageFrame">
                    <table cellpadding="0" cellspacing="0">
                        <tbody><tr>
                            <th> <div class="attach"><a id="sendEmojiIcon" href="javascript:void(0)" onclick="return false" title="表情" style="margin-top:0;" class=""></a><div id="face_panel" style="display: none; z-index: 1;"><div id="choose_face"><a title="[微笑]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/1.png"><p>微笑</p></a><a title="[不]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/2.png"><p>不</p></a><a title="[亲亲]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/3.png"><p>亲亲</p></a><a title="[无聊]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/4.png"><p>无聊</p></a><a title="[鼓掌]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/5.png"><p>鼓掌</p></a><a title="[伤心]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/6.png"><p>伤心</p></a><a title="[害羞]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/7.png"><p>害羞</p></a><a title="[闭嘴]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/8.png"><p>闭嘴</p></a><a title="[耍酷]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/9.png"><p>耍酷</p></a><a title="[无语]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/10.png"><p>无语</p></a><a title="[发怒]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/11.png"><p>发怒</p></a><a title="[惊讶]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/12.png"><p>惊讶</p></a><a title="[委屈]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/13.png"><p>委屈</p></a><a title="[酷]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/14.png"><p>酷</p></a><a title="[汗]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/15.png"><p>汗</p></a><a title="[闪]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/16.png"><p>闪</p></a><a title="[放屁]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/17.png"><p>放屁</p></a><a title="[洗澡]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/18.png"><p>洗澡</p></a><a title="[偶耶]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/19.png"><p>偶耶</p></a><a title="[困]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/20.png"><p>困</p></a><a title="[咒骂]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/21.png"><p>咒骂</p></a><a title="[疑问]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/22.png"><p>疑问</p></a><a title="[晕]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/23.png"><p>晕</p></a><a title="[衰]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/24.png"><p>衰</p></a><a title="[装鬼]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/25.png"><p>装鬼</p></a><a title="[受伤]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/26.png"><p>受伤</p></a><a title="[再见]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/27.png"><p>再见</p></a><a title="[抠鼻]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/28.png"><p>抠鼻</p></a><a title="[心寒]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/29.png"><p>心寒</p></a><a title="[怒]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/30.png"><p>怒</p></a><a title="[凄凉]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/31.png"><p>凄凉</p></a><a title="[悄悄]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/32.png"><p>悄悄</p></a><a title="[奋斗]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/33.png"><p>奋斗</p></a><a title="[哭]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/34.png"><p>哭</p></a><a title="[赞]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/35.png"><p>赞</p></a><a title="[开心]" href="javascript:;"><img class="ph_face_s" src="/static/img/smiley/36.png"><p>开心</p></a></div></div></div>
                            </th>
                            <th> <div class="chat_upImg" style="text-align:center">
                                    <input type="file" name="imgFile" id="msgUploadImg" accept="image/jpeg,image/gif,image/x-png" title="图片" style="display:none">
                                </div>
                            </th>
                            <th> <div style="position:relative;width:490px;margin-top:8px;">
                                    <textarea class="chatInput" id="textInput" type="text" maxlength="300" placeholder="输入您要发送的私信..." style="height: 40px; overflow-y: hidden;"></textarea>
                                    <div id="msg_upImg_box" style="height:62px;display:none"></div>
                                    <span id="imgDel" style="display:none;width:10px;height:10px;background:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAoAAAAKBAMAAAB/HNKOAAAAA3NCSVQICAjb4U/gAAAAHlBMVEXKytn////z8/bX1+PU1OD19fjb2+Xz8/f19fnZ2ePstdLlAAAACXBIWXMAAAsSAAALEgHS3X78AAAAHHRFWHRTb2Z0d2FyZQBBZG9iZSBGaXJld29ya3MgQ1M26LyyjAAAABZ0RVh0Q3JlYXRpb24gVGltZQAxMS8yNS8xM7kML+MAAAAvSURBVAiZY2AVFBRUYlBTFBQyYhByF1RRZBAsUUoSZBAUMiiEk2ARiGwYWCVYFwDX5gdZj1qR8wAAAABJRU5ErkJggg==) no-repeat 0 0;"></span> </div>
                            </th>
                            <th> <a class="chatSend btn btn-large btn-green" href="javascript:;">发送</a> </th>
                        </tr>
                    </tbody></table>
                </form>
                <iframe width="0" height="0" id="imageFrame" name="imageFrame" frameborder="0" scrolling="no"></iframe>
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
</body>
</html>
