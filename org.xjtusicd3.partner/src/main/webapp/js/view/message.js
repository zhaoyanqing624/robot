$(document).ready(function() {
	autosize(document.querySelectorAll('textarea'));
})
// 关闭私信
$(function() {
	$(".fa&.fa-caret-down&.msg-more&.js-msg-more").click(
			function(event) {
				var id = event.target.parentNode.parentNode.id;
				var myDiv = document.getElementById(id).getElementsByClassName(
						"more-box")[0];
				// showDiv();//调用显示DIV方法
				$(myDiv).toggle();
				$(document).one("click", function() { // 对document绑定一个影藏Div方法
					$(myDiv).hide();
				});
				event.stopPropagation(); // 阻止事件向上冒泡
				$(myDiv).click(function(event) {
					event.stopPropagation(); // 阻止事件向上冒泡
				});
				function showDiv() {
					$(myDiv).fadeIn();
				}
			});

	$(".shield&.js-shield").click(function(event) {
		var id = event.target.parentNode.parentNode.id.split("lastChat")[1];
		document.getElementById("lastChat" + id).remove();
		document.getElementById("userchatUl" + id).remove();
		$.ajax({
			type : "POST",
			url : "/org.xjtusicd3.partner/deleteMessageList.html",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function(data) {
				if (data.value == "0") {
					self.location = 'login.html';
				} else {

				}
			}
		})
	})
});

// 好友私信列表显示
$('#lastChat')
		.find('li')
		.click(
				function() {
					$(this).addClass("active").siblings("li").removeClass(
							"active");
					var userid = $(this)[0].id.split("lastChat")[1];
					var usercontentid = "userchatUl" + userid;
					$('#' + usercontentid).css('display', 'block').siblings(
							"ul").css('display', 'none');
					if (document.getElementById("messagenumber" + userid) != null) {
						document.getElementById("messagenumber" + userid).innerHTML = "";
					}
					document.getElementById("lastChat" + userid)
							.getElementsByClassName("theLastMsg")[0].innerHTML = "";
					var touserId = document.getElementById("lastChat")
							.getElementsByClassName("active")[0].id
							.split("lastChat")[1];
					// 点击列表后开始查询
					$
							.ajax({
								type : "POST",
								url : "/org.xjtusicd3.partner/getMessage.html",
								data : {
									"touserId" : touserId
								},
								dataType : "json",
								success : function(data) {
									if (data.value == "0") {
										self.location = 'login.html';
									} else {
										if (data.messageContentList == "") {
											if (data.messageHistory != "") {
												if (data.isMore == "1") {
													var html = document
															.getElementById("userchatUl"
																	+ touserId).innerHTML;
													if (document
															.getElementById(
																	"userchatUl"
																			+ touserId)
															.getElementsByClassName(
																	"getmore")[0] == null) {
														document
																.getElementById("userchatUl"
																		+ touserId).innerHTML = '<li><div class="getmore"><strong style="width:130px;" onclick="getMoreMessageHistory()">查看更多记录</strong></div></li>'
																+ html;
													}
													for ( var j in data.messageHistory) {
														if (data.messageHistory[j].userId == document
																.getElementById("zhao_hidden").innerHTML) {
															if (document
																	.getElementById(data.messageHistory[j].messageId) == null) {
																if (document
																		.getElementById(data.messageHistory[j].time
																				.substring(
																						0,
																						10)) == null) {
																	var htmls = document
																			.getElementById("userchatUl"
																					+ touserId).innerHTML;
																	document
																			.getElementById("userchatUl"
																					+ touserId).innerHTML = htmls
																			+ '<li id="'
																			+ data.messageHistory[j].time
																					.substring(
																							0,
																							10)
																			+ '"><div class="timeLine"><strong style="width:130px;">'
																			+ data.messageHistory[j].time
																					.substring(
																							0,
																							10)
																			+ '</strong></div></li>';
																}
																var html = document
																		.getElementById("userchatUl"
																				+ touserId).innerHTML;
																document
																		.getElementById("userchatUl"
																				+ touserId).innerHTML = html
																		+ '<li class="me" id="'
																		+ data.messageHistory[j].messageId
																		+ '"><div class="chat_avata"><a href="personal2.html?u='
																		+ data.messageHistory[j].userId
																		+ '" target="_blank"><img width="40" height="40" class="img_border_one" src="'
																		+ data.messageHistory[j].userImage
																		+ '"></a></div><div class="a_msg_info"><pre>'
																		+ data.messageHistory[j].content
																		+ '</pre><i class="arrow_left_b"></i></div><small class="time">'
																		+ data.messageHistory[j].time
																		+ '</small></li>';
															}

														} else {
															if (document
																	.getElementById(data.messageHistory[j].messageId) == null) {
																if (document
																		.getElementById(data.messageHistory[j].time
																				.substring(
																						0,
																						10)) == null) {
																	var htmls = document
																			.getElementById("userchatUl"
																					+ touserId).innerHTML;
																	document
																			.getElementById("userchatUl"
																					+ touserId).innerHTML = htmls
																			+ '<li id="'
																			+ data.messageHistory[j].time
																					.substring(
																							0,
																							10)
																			+ '"><div class="timeLine"><strong style="width:130px;">'
																			+ data.messageHistory[j].time
																					.substring(
																							0,
																							10)
																			+ '</strong></div></li>';
																}
																var html = document
																		.getElementById("userchatUl"
																				+ touserId).innerHTML;
																document
																		.getElementById("userchatUl"
																				+ touserId).innerHTML = html
																		+ '<li class="you" id="'
																		+ data.messageHistory[j].messageId
																		+ '"><div class="chat_avata"><a href="personal2.html?u='
																		+ data.messageHistory[j].userId
																		+ '" target="_blank"><img width="40" height="40" class="img_border_one" src="'
																		+ data.messageHistory[j].userImage
																		+ '"></a></div><div class="a_msg_info"><pre>'
																		+ data.messageHistory[j].content
																		+ '</pre><i class="arrow_left_b"></i></div><small class="time">'
																		+ data.messageHistory[j].time
																		+ '</small></li>';
															}
														}
													}
												} else {
													for ( var j in data.messageHistory) {
														if (data.messageHistory[j].userId == document
																.getElementById("zhao_hidden").innerHTML) {
															if (document
																	.getElementById(data.messageHistory[j].messageId) == null) {
																if (document
																		.getElementById(data.messageHistory[j].time
																				.substring(
																						0,
																						10)) == null) {
																	var htmls = document
																			.getElementById("userchatUl"
																					+ touserId).innerHTML;
																	document
																			.getElementById("userchatUl"
																					+ touserId).innerHTML = htmls
																			+ '<li id="'
																			+ data.messageHistory[j].time
																					.substring(
																							0,
																							10)
																			+ '"><div class="timeLine"><strong style="width:130px;">'
																			+ data.messageHistory[j].time
																					.substring(
																							0,
																							10)
																			+ '</strong></div></li>';
																}
																var html = document
																		.getElementById("userchatUl"
																				+ touserId).innerHTML;
																document
																		.getElementById("userchatUl"
																				+ touserId).innerHTML = html
																		+ '<li class="me" id="'
																		+ data.messageHistory[j].messageId
																		+ '"><div class="chat_avata"><a href="personal2.html?u='
																		+ data.messageHistory[j].userId
																		+ '" target="_blank"><img width="40" height="40" class="img_border_one" src="'
																		+ data.messageHistory[j].userImage
																		+ '"></a></div><div class="a_msg_info"><pre>'
																		+ data.messageHistory[j].content
																		+ '</pre><i class="arrow_left_b"></i></div><small class="time">'
																		+ data.messageHistory[j].time
																		+ '</small></li>';
															}
														} else {
															if (document
																	.getElementById(data.messageHistory[j].messageId) == null) {
																if (document
																		.getElementById(data.messageHistory[j].time
																				.substring(
																						0,
																						10)) == null) {
																	var htmls = document
																			.getElementById("userchatUl"
																					+ touserId).innerHTML;
																	document
																			.getElementById("userchatUl"
																					+ touserId).innerHTML = htmls
																			+ '<li id="'
																			+ data.messageHistory[j].time
																					.substring(
																							0,
																							10)
																			+ '"><div class="timeLine"><strong style="width:130px;">'
																			+ data.messageHistory[j].time
																					.substring(
																							0,
																							10)
																			+ '</strong></div></li>';
																}
																var html = document
																		.getElementById("userchatUl"
																				+ touserId).innerHTML;
																document
																		.getElementById("userchatUl"
																				+ touserId).innerHTML = html
																		+ '<li class="you" id="'
																		+ data.messageHistory[j].messageId
																		+ '"><div class="chat_avata"><a href="personal2.html?u='
																		+ data.messageHistory[j].userId
																		+ '" target="_blank"><img width="40" height="40" class="img_border_one" src="'
																		+ data.messageHistory[j].userImage
																		+ '"></a></div><div class="a_msg_info"><pre>'
																		+ data.messageHistory[j].content
																		+ '</pre><i class="arrow_left_b"></i></div><small class="time">'
																		+ data.messageHistory[j].time
																		+ '</small></li>';
															}
														}
													}
												}
											}
										} else {
											if (data.isMore == "1") {
												var html = document
														.getElementById("userchatUl"
																+ touserId).innerHTML;
												document
														.getElementById("userchatUl"
																+ touserId).innerHTML = '<li><div class="getmore"><strong style="width:130px;" onclick="getMoreMessageHistory()">查看更多记录</strong></div></li>'
														+ html;
												for ( var i in data.messageContentList) {
													var html = document
															.getElementById("userchatUl"
																	+ touserId).innerHTML;
													if (document
															.getElementById(data.messageContentList[i].messageId) == null) {
														if (document
																.getElementById(data.messageContentList[i].time
																		.substring(
																				0,
																				10)) == null) {
															var htmls = document
																	.getElementById("userchatUl"
																			+ touserId).innerHTML;
															document
																	.getElementById("userchatUl"
																			+ touserId).innerHTML = htmls
																	+ '<li id="'
																	+ data.messageContentList[i].time
																			.substring(
																					0,
																					10)
																	+ '"><div class="timeLine"><strong style="width:130px;">'
																	+ data.messageContentList[i].time
																			.substring(
																					0,
																					10)
																	+ '</strong></div></li>';
														}
														document
																.getElementById("userchatUl"
																		+ touserId).innerHTML = html
																+ '<li class="you" id="'
																+ data.messageContentList[i].messageId
																+ '"><div class="chat_avata"><a href="personal2.html?u='
																+ data.messageContentList[i].userId
																+ '" target="_blank"><img width="40" height="40" class="img_border_one" src="'
																+ data.messageContentList[i].userImage
																+ '"></a></div><div class="a_msg_info" id="4426066"><pre>'
																+ data.messageContentList[i].content
																+ '</pre><i class="arrow_left_b"></i></div><small class="time">'
																+ data.messageContentList[i].time
																+ '</small></li>';
													}
												}
											} else {
												for ( var i in data.messageContentList) {
													var html = document
															.getElementById("userchatUl"
																	+ touserId).innerHTML;
													if (document
															.getElementById(data.messageContentList[i].messageId) == null) {
														if (document
																.getElementById(data.messageContentList[i].time
																		.substring(
																				0,
																				10)) == null) {
															var htmls = document
																	.getElementById("userchatUl"
																			+ touserId).innerHTML;
															document
																	.getElementById("userchatUl"
																			+ touserId).innerHTML = htmls
																	+ '<li id="'
																	+ data.messageContentList[i].time
																			.substring(
																					0,
																					10)
																	+ '"><div class="timeLine"><strong style="width:130px;">'
																	+ data.messageContentList[i].time
																			.substring(
																					0,
																					10)
																	+ '</strong></div></li>';
														}
														document
																.getElementById("userchatUl"
																		+ touserId).innerHTML = html
																+ '<li class="you" id="'
																+ data.messageContentList[i].messageId
																+ '"><div class="chat_avata"><a href="personal2.html?u='
																+ data.messageContentList[i].userId
																+ '" target="_blank"><img width="40" height="40" class="img_border_one" src="'
																+ data.messageContentList[i].userImage
																+ '"></a></div><div class="a_msg_info" id="4426066"><pre>'
																+ data.messageContentList[i].content
																+ '</pre><i class="arrow_left_b"></i></div><small class="time">'
																+ data.messageContentList[i].time
																+ '</small></li>';
													}
												}
											}
										}
									}
								}
							});
				})

function sendMessage() {
	var content = document.getElementById("textInput").value;
	var touserId = document.getElementById("lastChat").getElementsByClassName(
			"active")[0].id.split("lastChat")[1];
	$
			.ajax({
				type : "POST",
				url : "/org.xjtusicd3.partner/saveMessage.html",
				data : {
					"content" : content,
					"touserId" : touserId
				},
				dataType : "json",
				success : function(data) {
					if (data.value == "0") {
						self.location = 'login.html';
					} else {
						var html = document.getElementById("userchatUl"
								+ touserId).innerHTML;
						document.getElementById("userchatUl" + touserId).innerHTML = html
								+ '<li class="me"><div class="chat_avata"><a href="personal2.html?u='
								+ data.messageList.userId
								+ '" target="_blank"><img width="40" height="40" class="img_border_one" src="'
								+ data.messageList.userImage
								+ '"></a></div><div class="a_msg_info"><pre>'
								+ data.messageList.content
								+ '</pre><i class="arrow_left_b"></i></div><small class="time">'
								+ data.messageList.time + '</small></li>';
						document.getElementById("textInput").value = "";
						alert(document.getElementById("userchatUl" + touserId)
								.getElementsByClassName("scroll-bar")[0].innerHTML)
					}
				}
			})
}
function getMoreMessageHistory() {
	var touserId = document.getElementById("lastChat").getElementsByClassName(
			"active")[0].id.split("lastChat")[1];
	var date = document.getElementById("userchatUl" + touserId)
			.getElementsByClassName("time")[0].innerHTML;
	$
			.ajax({
				type : "POST",
				url : "/org.xjtusicd3.partner/getMoreMessageHistory.html",
				data : {
					"date" : date,
					"touserId" : touserId
				},
				dataType : "json",
				success : function(data) {
					if (data.value == "0") {
						self.location = 'login.html';
					} else {
						document.getElementById("userchatUl" + touserId)
								.getElementsByClassName("getmore")[0].parentNode
								.remove();
						if (data.messageHistory != "") {
							for ( var j in data.messageHistory) {
								if (data.messageHistory[j].userId == document
										.getElementById("zhao_hidden").innerHTML) {
									if (document
											.getElementById(data.messageHistory[j].messageId) == null) {
										var html = document
												.getElementById("userchatUl"
														+ touserId).innerHTML;
										document.getElementById("userchatUl"
												+ touserId).innerHTML = '<li class="me" id="'
												+ data.messageHistory[j].messageId
												+ '"><div class="chat_avata"><a href="personal2.html?u='
												+ data.messageHistory[j].userId
												+ '" target="_blank"><img width="40" height="40" class="img_border_one" src="'
												+ data.messageHistory[j].userImage
												+ '"></a></div><div class="a_msg_info"><pre>'
												+ data.messageHistory[j].content
												+ '</pre><i class="arrow_left_b"></i></div><small class="time">'
												+ data.messageHistory[j].time
												+ '</small></li>' + html;
										if (document
												.getElementById(data.messageHistory[j].time
														.substring(0, 10)) == null) {
											var htmls = document
													.getElementById("userchatUl"
															+ touserId).innerHTML;
											document
													.getElementById("userchatUl"
															+ touserId).innerHTML = '<li id="'
													+ data.messageHistory[j].time
															.substring(0, 10)
													+ '"><div class="timeLine"><strong style="width:130px;">'
													+ data.messageHistory[j].time
															.substring(0, 10)
													+ '</strong></div></li>'
													+ htmls;
										} else {
											document.getElementById(
													data.messageHistory[j].time
															.substring(0, 10))
													.remove();
											var htmls = document
													.getElementById("userchatUl"
															+ touserId).innerHTML;
											document
													.getElementById("userchatUl"
															+ touserId).innerHTML = '<li id="'
													+ data.messageHistory[j].time
															.substring(0, 10)
													+ '"><div class="timeLine"><strong style="width:130px;">'
													+ data.messageHistory[j].time
															.substring(0, 10)
													+ '</strong></div></li>'
													+ htmls;
										}
									}
								} else {
									if (document
											.getElementById(data.messageHistory[j].messageId) == null) {
										var html = document
												.getElementById("userchatUl"
														+ touserId).innerHTML;
										document.getElementById("userchatUl"
												+ touserId).innerHTML = '<li class="you" id="'
												+ data.messageHistory[j].messageId
												+ '"><div class="chat_avata"><a href="personal2.html?u='
												+ data.messageHistory[j].userId
												+ '" target="_blank"><img width="40" height="40" class="img_border_one" src="'
												+ data.messageHistory[j].userImage
												+ '"></a></div><div class="a_msg_info"><pre>'
												+ data.messageHistory[j].content
												+ '</pre><i class="arrow_left_b"></i></div><small class="time">'
												+ data.messageHistory[j].time
												+ '</small></li>' + html;
										if (document
												.getElementById(data.messageHistory[j].time
														.substring(0, 10)) == null) {
											var htmls = document
													.getElementById("userchatUl"
															+ touserId).innerHTML;
											document
													.getElementById("userchatUl"
															+ touserId).innerHTML = '<li id="'
													+ data.messageHistory[j].time
															.substring(0, 10)
													+ '"><div class="timeLine"><strong style="width:130px;">'
													+ data.messageHistory[j].time
															.substring(0, 10)
													+ '</strong></div></li>'
													+ htmls;
										} else {
											document.getElementById(
													data.messageHistory[j].time
															.substring(0, 10))
													.remove();
											var htmls = document
													.getElementById("userchatUl"
															+ touserId).innerHTML;
											document
													.getElementById("userchatUl"
															+ touserId).innerHTML = '<li id="'
													+ data.messageHistory[j].time
															.substring(0, 10)
													+ '"><div class="timeLine"><strong style="width:130px;">'
													+ data.messageHistory[j].time
															.substring(0, 10)
													+ '</strong></div></li>'
													+ htmls;
										}
									}
								}
							}
						}
						if (data.isMore == "1") {
							document.getElementById("zhao_hidden").className = "1";
							var htmlss = document.getElementById("userchatUl"
									+ touserId).innerHTML;
							document.getElementById("userchatUl" + touserId).innerHTML = '<li><div class="getmore"><strong style="width:130px;" onclick="getMoreMessageHistory()">查看更多记录</strong></div></li>'
									+ htmlss;
						}
					}
				}
			})
}