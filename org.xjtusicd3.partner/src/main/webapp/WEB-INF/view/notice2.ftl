
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>智能小朵-知识库</title>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <script src="js/jquery-3.1.1.min.js"></script>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="new/front/style/reset2.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util2.css" />
    <link href="css/main.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/css.css" rel="stylesheet">
    <link rel="stylesheet" href="css/all.css">
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
			$.ajax({
			type:"GET",
			url:"/org.xjtusicd3.partner/getUserName.html",
			dataType:"json",
			success:function(data){
				username = data[0].uSERNAME;
				send("{username:'" + username + "',type:4}");
			}
		})
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
						$.ajax({
							type:"GET",
							url:"/org.xjtusicd3.partner/getUserName.html",
							dataType:"json",
							success:function(data){
								username = data[0].uSERNAME;
								send("{username:'" + username
								+ "',type:2,content:'" + $("#content").val()
								+ "',sendto:'"
								+ $(".bg-color").eq(0).find("span").html()
								+ "'}");
						$("#content").val("");
							}
						})
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
});
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

<div id="notices" class="noticesPage">
	<ul class="n-tab clearfix">
	    <li class="active">
	        <a id="not_new" href="notice.html">通知<span class="not-num">(3)</span></a>
	    </li>
	    <li>
	        <a id="msg_new" href="message.html" class="">私信<span class="msg-num" style="display: none;"></span></a>
	    </li>
	</ul>
    <div class="content">
        <div class="title-bar">
			<a class="tip-setting" href="#" onclick="tongzhishezhi()">通知设置</a>
		    <a class="tag-read-btn" href="#">全部标记为已读</a>
			<p class="tip-test">系统自动清理三个月前的已读通知</p>
        </div>

	<div id="Prompt-layer" class="Prompt-layer">
		<div class="clearfix Prompt-succ-layer Prompt-error-layer">
			<i class="Prompt-layer-icon succicon"></i>
			<span class="Prompt-layer-text"></span>
		</div>
	</div>
	
	<div class="notice-list">
    	<div id="840249" class="notice" onmouseover="showdelete(event,this)" onmouseout="hiddendelete(event,this)">
       		<div class="notice-box clearfix">
            	<p class="notice-type ">系统</p>
        		<div class="notice-show-box">
            		<p class="notice-con "> 您的电脑有<a target="_blank" href="/wenda/detail/344192">48个软件</a>可以更新</p>
           			<h5 class="notice-date">11:47:09</h5>
        		</div>
        		<div class="del-box clearfix">
            		<a onclick="deletenotice()" class="del-notice" title="删除此通知">
                		<i class="fa fa-trash-o"></i>
            		</a>
        		</div>
        	</div>
		</div>
		
		<div id="second">
		<#list secondList as secondList>
			<div id=${secondList.noticeId} class="notice" onmouseover="showdelete(event,this)" onmouseout="hiddendelete(event,this)">
				<div class="notice-box clearfix">
					<#if secondList.value=="知识库_有了新的评论">
						<p class="notice-type " style="color: #05c953;border: 1px solid #05c953;background: #a9fba8;">知识库</p>
						<div class="notice-show-box">
							<p class="notice-con ">你的知识：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${secondList.name?replace('<(.|\n)+?>','','r')}</a>”有新的评论“<a class="notice-answer" target="_blank" href="">${secondList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
							<h5 class="notice-date">2017-06-08 14:15:36</h5>
						</div>
					<#elseif secondList.value=="知识库_有了新的回复">
						<p class="notice-type " style="color: #05c953;border: 1px solid #05c953;background: #a9fba8;">知识库</p>
						<div class="notice-show-box">
							<p class="notice-con ">你的评论：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${secondList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${secondList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
							<h5 class="notice-date">2017-06-08 14:15:36</h5>
						</div>
					<#elseif secondList.value=="知识库_有了新的回复@">
						<p class="notice-type " style="color: #05c953;border: 1px solid #05c953;background: #a9fba8;">知识库</p>
						<div class="notice-show-box">
							<p class="notice-con ">你的回复：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${secondList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${secondList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
							<h5 class="notice-date">2017-06-08 14:15:36</h5>
						</div>
					<#elseif secondList.value=="问吧_有了新的评论">
						<p class="notice-type " style="color: #0343fb;border: 1px solid #0343fb;background: #9be4ff;">问吧</p>
						<div class="notice-show-box">
							<p class="notice-con ">你的提问：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${secondList.name?replace('<(.|\n)+?>','','r')}</a>”有新的评论“<a class="notice-answer" target="_blank" href="">${secondList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
							<h5 class="notice-date">2017-06-08 14:15:36</h5>
						</div>
					<#elseif secondList.value=="问吧_有了新的回复">
						<p class="notice-type " style="color: #0343fb;border: 1px solid #0343fb;background: #9be4ff;">问吧</p>
						<div class="notice-show-box">
							<p class="notice-con ">你的评论：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${secondList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${secondList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
							<h5 class="notice-date">2017-06-08 14:15:36</h5>
						</div>
					<#elseif secondList.value=="问吧_有了新的回复@">
						<p class="notice-type " style="color: #0343fb;border: 1px solid #0343fb;background: #9be4ff;">问吧</p>
						<div class="notice-show-box">
							<p class="notice-con ">你的回复：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${secondList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${secondList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
							<h5 class="notice-date">2017-06-08 14:15:36</h5>
						</div>
					</#if>
					<div class="del-box clearfix" style="display: none;">
						<a onclick="deletenotice()" class="del-notice" title="删除此通知">
							<i class="fa fa-trash-o"></i>
						</a>
					</div>
				</div>
			</div>
		</#list>
		</div>
		
		<div id="third">
			<#list thirdList as thirdList>
			<div id=${thirdList.noticeId} class="notice" onmouseover="showdelete(event,this)" onmouseout="hiddendelete(event,this)">
				<div class="notice-box clearfix">
					<#if thirdList.value=="知识库_有了新的评论">
						<p class="notice-type  already-read" >知识库</p>
						<div class="notice-show-box">
							<p class="notice-con pass ">你的知识：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${thirdList.name?replace('<(.|\n)+?>','','r')}</a>”有新的评论“<a class="notice-answer" target="_blank" href="">${thirdList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
							<h5 class="notice-date">2017-06-08 14:15:36</h5>
						</div>
					<#elseif thirdList.value=="知识库_有了新的回复">
						<p class="notice-type  already-read" >知识库</p>
						<div class="notice-show-box">
							<p class="notice-con pass ">你的评论：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${thirdList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${thirdList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
							<h5 class="notice-date">2017-06-08 14:15:36</h5>
						</div>
					<#elseif thirdList.value=="知识库_有了新的回复@">
						<p class="notice-type  already-read" >知识库</p>
						<div class="notice-show-box">
							<p class="notice-con pass ">你的回复：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${thirdList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${thirdList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
							<h5 class="notice-date">2017-06-08 14:15:36</h5>
						</div>
					<#elseif thirdList.value=="问吧_有了新的评论">
						<p class="notice-type  already-read" >问吧</p>
						<div class="notice-show-box">
							<p class="notice-con pass ">你的提问：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${thirdList.name?replace('<(.|\n)+?>','','r')}</a>”有新的评论“<a class="notice-answer" target="_blank" href="">${thirdList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
							<h5 class="notice-date">2017-06-08 14:15:36</h5>
						</div>
					<#elseif thirdList.value=="问吧_有了新的回复">
						<p class="notice-type  already-read" >问吧</p>
						<div class="notice-show-box">
							<p class="notice-con pass ">你的评论：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${thirdList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${thirdList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
							<h5 class="notice-date">2017-06-08 14:15:36</h5>
						</div>
					<#elseif thirdList.value=="问吧_有了新的回复@">
						<p class="notice-type  already-read" >问吧</p>
						<div class="notice-show-box">
							<p class="notice-con pass ">你的回复：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${thirdList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${thirdList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
							<h5 class="notice-date">2017-06-08 14:15:36</h5>
						</div>
					</#if>
					<div class="del-box clearfix" style="display: none;">
						<a onclick="deletenotice()" class="del-notice" title="删除此通知">
							<i class="fa fa-trash-o"></i>
						</a>
					</div>
				</div>
			</div>
			</#list>
		</div>
	</div>
</div>
    
    
    <div id="js-setup-popl" class="setup-popl" style="display:none">
    <div class="setup-popl-top clearfix">
        <span class="title">通知设置</span>
        <i class="fa fa-times close" onclick="notongzhishezhi()"></i>
    </div>
    
    <div class="setup-content">
        <!-- 问吧 -->
        <dl>
            <dt class="clearfix">
                <span class="dt-tit">问吧</span>
                <div class="dt-line"></div>
            </dt>
            <dd class="clearfix">
                <div class="dd-item clearfix">
                    <div class="switch on" data-setting="mybbs_is_answered">
                        <div class="pinkline"></div>
                        <span class="pinkround"></span>
                    </div>
                    
                    <p class="switchname">提问有人回答</p>
                </div>
                
                <div class="dd-item clearfix">
                    <div class="switch on" data-setting="follow_bbs_is_answered">
                        <div class="pinkline"></div>
                        <span class="pinkround"></span>
                    </div>
                    
                    <p class="switchname">关注的问答有人回答</p>
                </div>
                
                <div class="dd-item clearfix">
                    <div class="switch on" data-setting="mybbs_answer_is_best">
                        <div class="pinkline"></div>
                        <span class="pinkround"></span>
                    </div>
                    
                    <p class="switchname">回答被采纳</p>
                </div>
                
                <div class="dd-item clearfix">
                    <div class="switch on" data-setting="mybbs_answer_is_praised">
                        <div class="pinkline"></div>
                        <span class="pinkround"></span>
                    </div>
                    
                    <p class="switchname">回答被人赞</p>
                </div>
                
                <div class="dd-item clearfix">
                    <div class="switch on" data-setting="mybbs_answer_is_replied">
                        <div class="pinkline"></div>
                        <span class="pinkround"></span>
                    </div>
                    
                    <p class="switchname">回答被回复</p>
                </div>

                <div class="dd-item clearfix">
                    <div class="switch on" data-setting="mybbs_reply_is_replied">
                        <div class="pinkline"></div>
                        <span class="pinkround"></span>
                    </div>
                    
                    <p class="switchname">回复被回复</p>
                </div>
            </dd>
        </dl>
        <!-- 问吧end -->
	    <!-- 知识库 -->
        <dl>
            <dt class="clearfix">
                <span class="dt-tit">知识库</span>
                <div class="dt-line"></div>
            </dt>
            <dd class="clearfix">
                <div class="dd-item clearfix">
                    <div class="switch on" data-setting="myblog_is_replied">
                        <div class="pinkline"></div>
                        <span class="pinkround"></span>
                    </div>
                    
                    <p class="switchname">我的知识被评论</p>
                </div>
                                <div class="dd-item clearfix">
                    <div class="switch on" data-setting="myblog_is_praised">
                        <div class="pinkline"></div>
                        <span class="pinkround"></span>
                    </div>
                    
                    <p class="switchname">我的知识被收藏</p>
                </div>
                <div class="dd-item clearfix">
                    <div class="switch on" data-setting="myblog_comment_is_replied">
                        <div class="pinkline"></div>
                        <span class="pinkround"></span>
                    </div>
                    
                    <p class="switchname">评论被回复</p>
                </div>
                <div class="dd-item clearfix">
                    <div class="switch on" data-setting="myblog_reply_is_replied">
                        <div class="pinkline"></div>
                        <span class="pinkround"></span>
                    </div>
                    
                    <p class="switchname">回复被回复</p>
                </div>
            </dd>
        </dl>
        <!-- 手记end -->
    </div>
    
    <div class="clearfix">
        <span class="save">保存</span>
    </div>
</div>
<div id="bg_tongzhi" class="setup-coverLayer" style="display:none"></div></div>

</div>
    </section>    
		
    <div id="foot" class="footer">
    	<p style="color: #ffffff;text-align: center;">© 西安交通大学社会智能与复杂数据处理实验室  2017.</p>
    </div>
    <script type="text/javascript" src="new/front/js/util.js"></script>
    <script>
    	function tongzhishezhi(){
    		document.getElementById("js-setup-popl").style.display="block";
    		document.getElementById("bg_tongzhi").style.display="block";
    	}
    	function notongzhishezhi(){
    		document.getElementById("js-setup-popl").style.display="none";
    		document.getElementById("bg_tongzhi").style.display="none";
    	}
    	//防止mouseover多次触发
    	function contains(parentNode, childNode) 
		{
		    if (parentNode.contains) {
		        return parentNode != childNode && parentNode.contains(childNode);
		    } else {
		        return !!(parentNode.compareDocumentPosition(childNode) & 16);
		    }
		}
		function checkHover(e,target)
		{
		    if (getEvent(e).type=="mouseover")  {
		        return !contains(target,getEvent(e).relatedTarget||getEvent(e).fromElement) && !((getEvent(e).relatedTarget||getEvent(e).fromElement)===target);
		    } else {
		        return !contains(target,getEvent(e).relatedTarget||getEvent(e).toElement) && !((getEvent(e).relatedTarget||getEvent(e).toElement)===target);
		    }
		}
		function getEvent(e){
		    return e||window.event;
		}

		function showdelete(e, obj){
		    if(checkHover(e,obj)){
			event.target.parentNode.getElementsByClassName("del-box clearfix")[0].style.display="block";
		  }
		}
    	function hiddendelete(e, obj){
    		if(checkHover(e,obj)){
			event.target.parentNode.getElementsByClassName("del-box clearfix")[0].style.display="none";
		  }
    	}
		function deletenotice(){
			event.target.parentNode.parentNode.parentNode.parentNode.style.display="none";
			var type = event.target.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("notice-type")[0].innerHTML;
			var id = event.target.parentNode.parentNode.parentNode.parentNode.id;
			var zhao = event.target.parentNode.parentNode.parentNode.getElementsByTagName("p")[0].className;
			var type2 ="";
			if(zhao=="notice-type "){
				type2 = event.target.parentNode.parentNode.parentNode.getElementsByClassName("notice-con")[0].innerHTML.substring(0,4);
			}else{
				type2 = event.target.parentNode.parentNode.parentNode.getElementsByClassName("notice-con pass")[0].innerHTML.substring(0,4);
			}
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/deleteNotice.html",
				data:{
					"id":id,
					"type":type,
					"type2":type2
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}
				}
			})
		}
		function updatenotice(){
			var zhao = event.target.parentNode.parentNode.parentNode.getElementsByTagName("p")[0].className;
			if(zhao=="notice-type "){
				var type = event.target.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("notice-type")[0].innerHTML;
				var id = event.target.parentNode.parentNode.parentNode.parentNode.id;
				var type2 = event.target.parentNode.innerHTML.substring(0,4);
				document.getElementById(id).getElementsByClassName("notice-box clearfix")[0].getElementsByClassName("notice-type")[0].style="";
				document.getElementById(id).getElementsByClassName("notice-box clearfix")[0].getElementsByClassName("notice-type")[0].className="notice-type  already-read";
				document.getElementById(id).getElementsByClassName("notice-box clearfix")[0].getElementsByClassName("notice-con ")[0].className="notice-con pass ";
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/updateNotice.html",
					data:{
						"id":id,
						"type":type,
						"type2":type2
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}
					}
				})
			}

		}
		
    </script>
</body>
</html>
