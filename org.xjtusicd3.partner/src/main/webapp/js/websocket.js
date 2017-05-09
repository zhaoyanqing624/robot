/**
 * 
 */
var websocket = null;
function connect() {
	$("#chat_content").show();
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
		send("{username:'" + $("#username").val() + "',type:1}");
	}

	// 接收到消息的回调方法
	websocket.onmessage = function(event) {
		var onlinelisthtml = $("#lastChat10000").html();
		var datajson = eval('(' + event.data + ')');
		switch (datajson.type) {
		case 1:
			for (var u = 0; u < datajson.onlinelist.length; u++)
				if (datajson.onlinelist[u] != $("#username").val())
					if (onlinelisthtml
							.indexOf("chat-" + datajson.onlinelist[u]) < 0)
						$("#lastChat10000").append(
								"<li class='list-box list-item-"
										+ datajson.onlinelist[u] + "'><span>"
										+ datajson.onlinelist[u]
										+ "</span><div class='chat_content chat-"
										+ datajson.onlinelist[u]
										+ "'></div></li>");
			/*
			 * onlinelisthtml = onlinelisthtml + "<li class='list-box'><span>" +
			 * datajson.onlinelist[u] + "</span><div class='chat_content
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
			$(".chat_content").each(
					function() {
						$(this).append(
								"<div class='msg'>" + datajson.username
										+ "下线了！</div>");
					});
			$(".list-item-" + datajson.username).remove();
			if ($("#lastChat10000").html().indexOf("bg-color") < 0) {
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
	$(".lastChat10000").delegate(".list-box", "click", function() {
		$(".list-box").each(function() {
			$(this).removeClass("bg-color");
			$(this).find(".chat_content").hide();
		});
		$(this).addClass("bg-color");
		$(".title").text($(this).children("span").text());
		$(this).find(".chat_content").show();
		$("#content").unbind("click");
		$(this).children("span").find("i").remove();
	});
	$("#content").keyup(function(event) {
		if (event.keyCode == 13)
			$("#send").click();
	});
	if ($("#lastChat10000").html().indexOf("bg-color") < 0)
		$("#content").click(function() {
			$(this).blur();
		});
	$("#openwindow").click(function(){
		window.open("client.jsp","百度一下","width=820px,height=620px,scrollbars=no,resizable=no");
	});
});