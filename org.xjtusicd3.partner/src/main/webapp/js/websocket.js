/**
 * 
 */
var websocket = null;
function connect() {
	$("#content-box").show();
	// 判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://localhost:8080/org.xjtusicd3.partner/websocketserver");
	} else {
		alert('Not support websocket')
	}

	// 连接发生错误的回调方法
	websocket.onerror = function(e) {
		setMessageInnerHTML("error" + e);
	};

	// 连接成功建立的回调方法
	websocket.onopen = function(event) {
		var useremail = document.getElementById("userNameText").innerHTML.slice(3);
		send("{useremail:'" +  useremail + "',type:1}");
	}

	// 接收到消息的回调方法
	websocket.onmessage = function(event) {
		var onlinelisthtml = $("#online-list").html();
		var datajson = eval('(' + event.data + ')');
		switch (datajson.type) {
		case 1:
			
			break;
		case 2:
			
			break;
		case 4:
			
			break;
		}
	}

	// 连接关闭的回调方法
	websocket.onclose = function() {
		send("{useremail:'" + $("#useremail").val() + "',type:4}");
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

