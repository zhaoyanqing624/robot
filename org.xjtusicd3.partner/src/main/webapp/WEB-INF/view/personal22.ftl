
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
function connect() {
	$("#content-box").show();
	// 判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket(
				"ws://localhost:8080/websocket_demo2/websocketserver");
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
		var onlinelisthtml = $("#online-list").html();
		var datajson = eval('(' + event.data + ')');
		switch (datajson.type) {
		case 1:
			for (var u = 0; u < datajson.onlinelist.length; u++)
				if (datajson.onlinelist[u] != datajson.username)
					if (onlinelisthtml
							.indexOf("chat-" + datajson.onlinelist[u]) < 0)
						$("#online-list").append(
								"<li class='online-list-item list-item-"
										+ datajson.onlinelist[u] + "'><span>"
										+ datajson.onlinelist[u]
										+ "</span><div class='chat-box chat-"
										+ datajson.onlinelist[u]
										+ "'></div></li>");
			/*
			 * onlinelisthtml = onlinelisthtml + "<li class='online-list-item'><span>" +
			 * datajson.onlinelist[u] + "</span><div class='chat-box
			 * chat-"+datajson.onlinelist[u]+"'></div></li>";
			 */
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
	$(".online-list").delegate(".online-list-item", "click", function() {
		$(".online-list-item").each(function() {
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
	$("#openwindow").click(function(){
		window.open("client.jsp","百度一下","width=820px,height=620px,scrollbars=no,resizable=no");
	});
});</script>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="new/front/style/reset2.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util2.css" />
    <link href="css/main.css" rel="stylesheet">
    <link href="css/bootstrap.min2.css" rel="stylesheet">
    <link href="css/css.css" rel="stylesheet">
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="css/chat.css">
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
    
	<div id="container" class="container_chat">
		<div class="online-box">
			<div class="find-input-box">
                <i class="fa fa-search"></i>
                <input class="input js-input" placeholder="通过昵称快速搜索" type="text" autocomplete="off" value="">
            </div>
			<ul id="online-list" class="online-list">
			</ul>
		</div>
		<div id="content-box">
			<input id="content" type="text" placeholder="请输入聊天内容">
			<button id="send">send</button>
		</div>
	</div>




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
