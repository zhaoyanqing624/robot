	function showeditor(){
		document.getElementById('input_answer').style.display="none";
		document.getElementById('answer-ueditor').style.display="block";
		document.getElementsByClassName('submitDiv')[0].style.display="block";
	}
	function replyQuestion(){
		var content = UE.getEditor('editor').getContent();
		var questionId = document.URL.split("=")[1];
		var url = document.URL;
		if(content==""){
			document.getElementById('null').style.display='block';
			setTimeout("codefans3()",3000);
		}else{
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/saveReplyQuestion.html",
				data:{
					"content":content,
					"questionId":questionId,
					"url":url
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}else if(data.value=="1"){
					setTimeout("location.reload()",1000)
						document.getElementById('success').style.display='block';
						setTimeout("codefans()",3000);
						
					}else{
					setTimeout("location.reload()",1000)
						document.getElementById('chongfu').style.display='block';
						setTimeout("codefans2()",3000);
					}
				}
			})
		}
	}
	
	function codefans(){
		var box=document.getElementById("success");
		box.style.display="none"; 
	}
	function codefans2(){
		var box=document.getElementById("chongfu");
		box.style.display="none"; 
	}
	function codefans3(){
		var box=document.getElementById("null");
		box.style.display="none"; 
	}
	//展开评论
	function getCommentList(){
		var _event= browserEvent();
		if(_event.parentNode.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("comment")[0].style.display=="block"){
			_event.parentNode.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("comment")[0].style.display="none";
		}else{
			_event.parentNode.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("comment")[0].style.display="block";
		}
	}
	//添加评论回复
	function saveComment(){
		var _event= browserEvent();
		id = _event.parentNode.getElementsByClassName("replyOther_div")[0].id;
		var questionId = document.URL.split("=")[1];
		var content = _event.parentNode.parentNode.getElementsByClassName("comment-Editor-input")[0].value;
		var answerId = _event.parentNode.parentNode.parentNode.parentNode.parentNode.id;
		if(id==""){
			if(content==""){
				document.getElementById('null').style.display='block';
				setTimeout("codefans3()",3000);
			}else{
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/saveCommunityComment.html",
					data:{
						"questionId":questionId,
						"content":content,
						"answerId":answerId
					},
					dataType:"json",
					success:function(data){
						jsondata=$.parseJSON(data);
						if(jsondata.value=="0"){
							self.location='login.html';
						}else if(jsondata.value=="1"){
						setTimeout("location.reload()",1000)
							document.getElementById('success').style.display='block';
							setTimeout("codefans()",3000);
							
						}else{
						setTimeout("location.reload()",1000)
							document.getElementById('chongfu').style.display='block';
							setTimeout("codefans2()",3000);
						}
					}
				})
			}
		}else{
			if(content==""){
				document.getElementById('null').style.display='block';
				setTimeout("codefans3()",3000);
			}else{
				var name = _event.parentNode.getElementsByClassName("username_span")[0].innerHTML;
				var length = name.length;
				var tousername = name.substring(1,length); 
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/saveCommunityReply.html",
					data:{
						"questionId":questionId,
						"content":content,
						"answerId":answerId,
						"tousername":tousername
					},
					dataType:"json",
					success:function(data){
						jsondata=$.parseJSON(data);
						if(jsondata.value=="0"){
							self.location='login.html';
						}else if(jsondata.value=="1"){
						setTimeout("location.reload()",1000)
							document.getElementById('success').style.display='block';
							setTimeout("codefans()",3000);
							
						}else{
						setTimeout("location.reload()",1000)
							document.getElementById('chongfu').style.display='block';
							setTimeout("codefans2()",3000);
						}
					}
				})
			}
		}

	}
	//获取更多回复
	function getMoreComment(){
		var _event= browserEvent();
		var html = _event.parentNode.getElementsByClassName("commentList")[0].innerHTML;
		var startnumber = _event.parentNode.getElementsByClassName("commentList")[0].getElementsByTagName("li").length;
		var questionId = document.URL.split("=")[1];
		var answerId = _event.parentNode.parentNode.parentNode.parentNode.id;
		
		$.ajax({
			type:"POST",
			url:"/org.xjtusicd3.partner/getMoreComment.html",
			data:{
				"questionId":questionId,
				"startnumber":startnumber,
				"answerId":answerId
			},
			dataType:"json",
			success:function(data){
				if(data.value=="0"){
					self.location='login.html';
				}else if(data.value=="1"){
					for(var i in data.commentList){
						var htmls = document.getElementById(answerId).getElementsByClassName("commentList")[0].innerHTML;
						document.getElementById(answerId).getElementsByClassName("commentList")[0].innerHTML = htmls+ '<li><img class="userImg" src="'+data.commentList[i].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].userName+'</p><p class="content">'+data.commentList[i].community+'</p><p class="commentTime">'+data.commentList[i].time+'</p></div></li>';
					}
					if(data.endnumber<data.totalnumber){
						startnumber = data.endnumber;
					}else{
						document.getElementById(answerId).getElementsByClassName("allComments")[0].remove();
					}
				}
			}
		})
	}
	
	//点赞
	function getAgreeAnswer(){
		var _event= browserEvent();
		var answerId = _event.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.id;
		if(answerId!="searchResult"){
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/saveAgreeAnswer.html",
				data:{
					"answerId":answerId
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}else if(data.value=="1"){
						document.getElementById(answerId).getElementsByClassName("status")[0].innerHTML="已点赞";
						var number = parseInt(document.getElementById(answerId).getElementsByClassName("number")[0].innerHTML);
						document.getElementById(answerId).getElementsByClassName("number")[0].innerHTML = number+1;
					}else if(data.value=="2"){
						document.getElementById(answerId).getElementsByClassName("status")[0].innerHTML="点赞";
						var number = parseInt(document.getElementById(answerId).getElementsByClassName("number")[0].innerHTML);
						document.getElementById(answerId).getElementsByClassName("number")[0].innerHTML = number-1;
					}
				}
			})
		}
	}
	//收藏
	function getCollectionAnswer(){
		var _event= browserEvent();
		var answerId = _event.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.id;
		if(answerId!="searchResult"){
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/saveCollectionAnswer.html",
				data:{
					"answerId":answerId
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}else if(data.value=="1"){
						document.getElementById(answerId).getElementsByClassName("shoucang")[0].innerHTML="已收藏";
					}else if(data.value=="2"){
						document.getElementById(answerId).getElementsByClassName("shoucang")[0].innerHTML="收藏";
					}
				}
			})
		}
	}
	//设置为最佳答案
	function getBestAnswer(event){
		var _event= browserEvent();
		var questionId = document.URL.split("q=")[1];
		var answerId = _event.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.id;
		if(answerId!="searchResult"){
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/saveBestAnswer.html",
				data:{
					"answerId":answerId,
					"questionId":questionId
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}else if(data.value=="1"){
						location.reload();
					}
				}
			})
		}
	}
	//回复别人的回复
	function replyOther(){
		var _event= browserEvent();
		var commentId = _event.parentNode.parentNode.id;
		document.getElementById(commentId).parentNode.parentNode.getElementsByClassName("replyOther_div")[0].style.display="block";
		document.getElementById(commentId).parentNode.parentNode.getElementsByClassName("comment-Editor-input")[0].style.margin="0 48px";
		document.getElementById(commentId).parentNode.parentNode.getElementsByClassName("replyOther_div")[0].id="_"+commentId;
		var username = document.getElementById("zhao_hidden").innerHTML;
		var tousername = _event.parentNode.getElementsByClassName("userName")[0].innerHTML.split("<span")[0];
		var tocomment = _event.parentNode.getElementsByClassName("content")[0].innerHTML;
		if(username==tousername){
			document.getElementById(commentId).parentNode.parentNode.getElementsByClassName("replyOther_div")[0].style.display="none";
			document.getElementById(commentId).parentNode.parentNode.getElementsByClassName("comment-Editor-input")[0].style.margin="0 15px";
			document.getElementById(commentId).parentNode.parentNode.getElementsByClassName("replyOther_div")[0].id="";
			document.getElementById(commentId).parentNode.parentNode.getElementsByClassName("username_span")[0].innerHTML="";
			document.getElementById(commentId).parentNode.parentNode.getElementsByClassName("content_span")[0].innerHTML="";
		}else{
			if(tocomment.length<20){
				tocomment = tocomment;
			}else{
				tocomment = tocomment.substr(0,20)+"...";
			}
			document.getElementById(commentId).parentNode.parentNode.getElementsByClassName("username_span")[0].innerHTML="@"+tousername;
			document.getElementById(commentId).parentNode.parentNode.getElementsByClassName("content_span")[0].innerHTML=tocomment;
		}
	}
	//分享communityQuestion
		function saveShare(){
			var questionId = document.URL.split("q=")[1];
			var from = "communityQuestion";
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/saveShare.html",
				data:{
					"questionId":questionId,
					"from":from
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}else if(data.value=="1"){
						document.getElementById("shareCommunity").getElementsByClassName("status")[0].innerHTML = "已分享";
						document.getElementById("shareCommunity").getElementsByClassName("number")[0].innerHTML = "√";
					}else{
						document.getElementById("shareCommunity").getElementsByClassName("status")[0].innerHTML = "分享";
						document.getElementById("shareCommunity").getElementsByClassName("number")[0].innerHTML = "";
					}
				}
			})
		}
	//查看更多评论
		$('#loading').click(function() {
			var startnumber = document.getElementById("searchResult").childElementCount;
			var questionId = document.URL.split("q=")[1];
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/queryMoreComment2.html",
				data:{
					"startnumber":startnumber,
					"questionId":questionId
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}else if(data.value=="1"){
						for(var i in data.commentList){
							var html = document.getElementById("searchResult").innerHTML;
							if(data.commentList[i].isLike=="0"){
								if(data.commentList[i].isCollection=="0"){
									if(parseInt(data.commentList[i].communityNumber)<5){
										document.getElementById("searchResult").innerHTML = html + '<li id="'+data.commentList[i].answerId+'"><article class="answerArticle"><div class="description"><div class="answerer"><img class="answerImg" src="'+data.commentList[i].userImage+'"><div class="answer_name"><a href="personal2.html?userid='+data.commentList[i].userId+'"><span class="user_name">'+data.commentList[i].userName+'</span>&nbsp;&nbsp;<span>'+data.commentList[i].signature+'</span></a></div><span class="answer_time">'+data.commentList[i].time+'</span><div><img src="images/bluepoint.png" class="bluepoint">贡献'+data.commentList[i].totalAnswer+'个回答，获得'+data.commentList[i].totalLikes+'个赞</div></div><div class="fullDetail"><p></p>'+data.commentList[i].answer+'<p></p></div></div><div class="options"><ul><li class="special"><a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">'+data.commentList[i].likesNumber+'</span></a></li><li><a onclick="getCommentList()"><span>评论 </span><span class="number">'+data.commentList[i].communityNumber+'</span></a></li><li><a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">收藏</span></a></li></ul></div><div class="comment" style="display:none"><img class="deco" src="images/dia-deco.png" style="left:106px"><div class="comment-outer"><div class="comment-Editor"><img class="userImg" src="'+data.commentList[0].userImage+'"><div class="replyOther_div" id=""><span class="username_span" style="color:#F00"></span>:<span class="content_span" style="color:#F00"></span></div><input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true"><button class="submitComment" onclick="saveComment()">评论</button></div><ul class="commentList"></ul></div></div></article></li>';
										for(var j in data.commentList[i].replay){
											var htmls = document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML;
											if(data.commentList[i].replay[j].touserName==null){
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    </span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}else{
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    '+data.commentList[i].replay[j].touserName+'</span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}
										}
									}else{
										
										document.getElementById("searchResult").innerHTML = html + '<li id="'+data.commentList[i].answerId+'"><article class="answerArticle"><div class="description"><div class="answerer"><img class="answerImg" src="'+data.commentList[i].userImage+'"><div class="answer_name"><a href="personal2.html?userid='+data.commentList[i].userId+'"><span class="user_name">'+data.commentList[i].userName+'</span>&nbsp;&nbsp;<span>'+data.commentList[i].signature+'</span></a></div><span class="answer_time">'+data.commentList[i].time+'</span><div><img src="images/bluepoint.png" class="bluepoint">贡献'+data.commentList[i].totalAnswer+'个回答，获得'+data.commentList[i].totalLikes+'个赞</div></div><div class="fullDetail"><p></p>'+data.commentList[i].answer+'<p></p></div></div><div class="options"><ul><li class="special"><a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">'+data.commentList[i].likesNumber+'</span></a></li><li><a onclick="getCommentList()"><span>评论 </span><span class="number">'+data.commentList[i].communityNumber+'</span></a></li><li><a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">收藏</span></a></li></ul></div><div class="comment" style="display:none"><img class="deco" src="images/dia-deco.png" style="left:106px"><div class="comment-outer"><div class="comment-Editor"><img class="userImg" src="'+data.commentList[0].userImage+'"><div class="replyOther_div" id=""><span class="username_span" style="color:#F00"></span>:<span class="content_span" style="color:#F00"></span></div><input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true"><button class="submitComment" onclick="saveComment()">评论</button></div><ul class="commentList"></ul><a class="allComments" id="allComments" onclick="getMoreComment()">点击获取更多</a></div></div></article></li>';
										for(var j in data.commentList[i].replay){
											var htmls = document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML;
											if(data.commentList[i].replay[j].touserName==null){
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    </span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}else{
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    '+data.commentList[i].replay[j].touserName+'</span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}
										}
									}
								}else{
									if(parseInt(data.commentList[i].communityNumber)<5){
										document.getElementById("searchResult").innerHTML = html + '<li id="'+data.commentList[i].answerId+'"><article class="answerArticle"><div class="description"><div class="answerer"><img class="answerImg" src="'+data.commentList[i].userImage+'"><div class="answer_name"><a href="personal2.html?userid='+data.commentList[i].userId+'"><span class="user_name">'+data.commentList[i].userName+'</span>&nbsp;&nbsp;<span>'+data.commentList[i].signature+'</span></a></div><span class="answer_time">'+data.commentList[i].time+'</span><div><img src="images/bluepoint.png" class="bluepoint">贡献'+data.commentList[i].totalAnswer+'个回答，获得'+data.commentList[i].totalLikes+'个赞</div></div><div class="fullDetail"><p></p>'+data.commentList[i].answer+'<p></p></div></div><div class="options"><ul><li class="special"><a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">'+data.commentList[i].likesNumber+'</span></a></li><li><a onclick="getCommentList()"><span>评论 </span><span class="number">'+data.commentList[i].communityNumber+'</span></a></li><li><a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">已收藏</span></a></li></ul></div><div class="comment" style="display:none"><img class="deco" src="images/dia-deco.png" style="left:106px"><div class="comment-outer"><div class="comment-Editor"><img class="userImg" src="‘+data.commentList[0].userImage+’"><div class="replyOther_div" id=""><span class="username_span" style="color:#F00"></span>:<span class="content_span" style="color:#F00"></span></div><input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true"><button class="submitComment" onclick="saveComment()">评论</button></div><ul class="commentList"></ul></div></div></article></li>';
										for(var j in data.commentList[i].replay){
											var htmls = document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML;
											if(data.commentList[i].replay[j].touserName==null){
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    </span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}else{
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    '+data.commentList[i].replay[j].touserName+'</span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}
										}
									}else{
										document.getElementById("searchResult").innerHTML = html + '<li id="'+data.commentList[i].answerId+'"><article class="answerArticle"><div class="description"><div class="answerer"><img class="answerImg" src="'+data.commentList[i].userImage+'"><div class="answer_name"><a href="personal2.html?userid='+data.commentList[i].userId+'"><span class="user_name">'+data.commentList[i].userName+'</span>&nbsp;&nbsp;<span>'+data.commentList[i].signature+'</span></a></div><span class="answer_time">'+data.commentList[i].time+'</span><div><img src="images/bluepoint.png" class="bluepoint">贡献'+data.commentList[i].totalAnswer+'个回答，获得'+data.commentList[i].totalLikes+'个赞</div></div><div class="fullDetail"><p></p>'+data.commentList[i].answer+'<p></p></div></div><div class="options"><ul><li class="special"><a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">'+data.commentList[i].likesNumber+'</span></a></li><li><a onclick="getCommentList()"><span>评论 </span><span class="number">'+data.commentList[i].communityNumber+'</span></a></li><li><a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">已收藏</span></a></li></ul></div><div class="comment" style="display:none"><img class="deco" src="images/dia-deco.png" style="left:106px"><div class="comment-outer"><div class="comment-Editor"><img class="userImg" src="‘+data.commentList[0].userImage+’"><div class="replyOther_div" id=""><span class="username_span" style="color:#F00"></span>:<span class="content_span" style="color:#F00"></span></div><input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true"><button class="submitComment" onclick="saveComment()">评论</button></div><ul class="commentList"></ul><a class="allComments" id="allComments" onclick="getMoreComment()">点击获取更多</a></div></div></article></li>';
										for(var j in data.commentList[i].replay){
											var htmls = document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML;
											if(data.commentList[i].replay[j].touserName==null){
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    </span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}else{
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    '+data.commentList[i].replay[j].touserName+'</span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}
										}
									}
								}
							}else{
								if(data.commentList[i].isCollection=="0"){
									if(parseInt(data.commentList[i].communityNumber)<5){
										document.getElementById("searchResult").innerHTML = html + '<li id="'+data.commentList[i].answerId+'"><article class="answerArticle"><div class="description"><div class="answerer"><img class="answerImg" src="'+data.commentList[i].userImage+'"><div class="answer_name"><a href="personal2.html?userid='+data.commentList[i].userId+'"><span class="user_name">'+data.commentList[i].userName+'</span>&nbsp;&nbsp;<span>'+data.commentList[i].signature+'</span></a></div><span class="answer_time">'+data.commentList[i].time+'</span><div><img src="images/bluepoint.png" class="bluepoint">贡献'+data.commentList[i].totalAnswer+'个回答，获得'+data.commentList[i].totalLikes+'个赞</div></div><div class="fullDetail"><p></p>'+data.commentList[i].answer+'<p></p></div></div><div class="options"><ul><li class="special"><a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">已点赞</span>  |  <span class="number">'+data.commentList[i].likesNumber+'</span></a></li><li><a onclick="getCommentList()"><span>评论 </span><span class="number">'+data.commentList[i].communityNumber+'</span></a></li><li><a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">收藏</span></a></li></ul></div><div class="comment" style="display:none"><img class="deco" src="images/dia-deco.png" style="left:106px"><div class="comment-outer"><div class="comment-Editor"><img class="userImg" src="‘+data.commentList[0].userImage+’"><div class="replyOther_div" id=""><span class="username_span" style="color:#F00"></span>:<span class="content_span" style="color:#F00"></span></div><input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true"><button class="submitComment" onclick="saveComment()">评论</button></div><ul class="commentList"></ul></div></div></article></li>';
										for(var j in data.commentList[i].replay){
											var htmls = document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML;
											if(data.commentList[i].replay[j].touserName==null){
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    </span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}else{
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    '+data.commentList[i].replay[j].touserName+'</span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}
										}
									}else{
										document.getElementById("searchResult").innerHTML = html + '<li id="'+data.commentList[i].answerId+'"><article class="answerArticle"><div class="description"><div class="answerer"><img class="answerImg" src="'+data.commentList[i].userImage+'"><div class="answer_name"><a href="personal2.html?userid='+data.commentList[i].userId+'"><span class="user_name">'+data.commentList[i].userName+'</span>&nbsp;&nbsp;<span>'+data.commentList[i].signature+'</span></a></div><span class="answer_time">'+data.commentList[i].time+'</span><div><img src="images/bluepoint.png" class="bluepoint">贡献'+data.commentList[i].totalAnswer+'个回答，获得'+data.commentList[i].totalLikes+'个赞</div></div><div class="fullDetail"><p></p>'+data.commentList[i].answer+'<p></p></div></div><div class="options"><ul><li class="special"><a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">已点赞</span>  |  <span class="number">'+data.commentList[i].likesNumber+'</span></a></li><li><a onclick="getCommentList()"><span>评论 </span><span class="number">'+data.commentList[i].communityNumber+'</span></a></li><li><a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">收藏</span></a></li></ul></div><div class="comment" style="display:none"><img class="deco" src="images/dia-deco.png" style="left:106px"><div class="comment-outer"><div class="comment-Editor"><img class="userImg" src="‘+data.commentList[0].userImage+’"><div class="replyOther_div" id=""><span class="username_span" style="color:#F00"></span>:<span class="content_span" style="color:#F00"></span></div><input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true"><button class="submitComment" onclick="saveComment()">评论</button></div><ul class="commentList"></ul><a class="allComments" id="allComments" onclick="getMoreComment()">点击获取更多</a></div></div></article></li>';
										for(var j in data.commentList[i].replay){
											var htmls = document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML;
											if(data.commentList[i].replay[j].touserName==null){
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    </span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}else{
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    '+data.commentList[i].replay[j].touserName+'</span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}
										}
									}
								}else{
									if(parseInt(data.commentList[i].communityNumber)<5){
										document.getElementById("searchResult").innerHTML = html + '<li id="'+data.commentList[i].answerId+'"><article class="answerArticle"><div class="description"><div class="answerer"><img class="answerImg" src="'+data.commentList[i].userImage+'"><div class="answer_name"><a href="personal2.html?userid='+data.commentList[i].userId+'"><span class="user_name">'+data.commentList[i].userName+'</span>&nbsp;&nbsp;<span>'+data.commentList[i].signature+'</span></a></div><span class="answer_time">'+data.commentList[i].time+'</span><div><img src="images/bluepoint.png" class="bluepoint">贡献'+data.commentList[i].totalAnswer+'个回答，获得'+data.commentList[i].totalLikes+'个赞</div></div><div class="fullDetail"><p></p>'+data.commentList[i].answer+'<p></p></div></div><div class="options"><ul><li class="special"><a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">已点赞</span>  |  <span class="number">'+data.commentList[i].likesNumber+'</span></a></li><li><a onclick="getCommentList()"><span>评论 </span><span class="number">'+data.commentList[i].communityNumber+'</span></a></li><li><a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">已收藏</span></a></li></ul></div><div class="comment" style="display:none"><img class="deco" src="images/dia-deco.png" style="left:106px"><div class="comment-outer"><div class="comment-Editor"><img class="userImg" src="‘+data.commentList[0].userImage+’"><div class="replyOther_div" id=""><span class="username_span" style="color:#F00"></span>:<span class="content_span" style="color:#F00"></span></div><input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true"><button class="submitComment" onclick="saveComment()">评论</button></div><ul class="commentList"></ul></div></div></article></li>';
										for(var j in data.commentList[i].replay){
											var htmls = document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML;
											if(data.commentList[i].replay[j].touserName==null){
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    </span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}else{
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    '+data.commentList[i].replay[j].touserName+'</span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}
										}
									}else{
										document.getElementById("searchResult").innerHTML = html + '<li id="'+data.commentList[i].answerId+'"><article class="answerArticle"><div class="description"><div class="answerer"><img class="answerImg" src="'+data.commentList[i].userImage+'"><div class="answer_name"><a href="personal2.html?userid='+data.commentList[i].userId+'"><span class="user_name">'+data.commentList[i].userName+'</span>&nbsp;&nbsp;<span>'+data.commentList[i].signature+'</span></a></div><span class="answer_time">'+data.commentList[i].time+'</span><div><img src="images/bluepoint.png" class="bluepoint">贡献'+data.commentList[i].totalAnswer+'个回答，获得'+data.commentList[i].totalLikes+'个赞</div></div><div class="fullDetail"><p></p>'+data.commentList[i].answer+'<p></p></div></div><div class="options"><ul><li class="special"><a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">已点赞</span>  |  <span class="number">'+data.commentList[i].likesNumber+'</span></a></li><li><a onclick="getCommentList()"><span>评论 </span><span class="number">'+data.commentList[i].communityNumber+'</span></a></li><li><a data-fun="toSave"><span class="shoucang" onclick="getCollectionAnswer()">已收藏</span></a></li></ul></div><div class="comment" style="display:none"><img class="deco" src="images/dia-deco.png" style="left:106px"><div class="comment-outer"><div class="comment-Editor"><img class="userImg" src="‘+data.commentList[0].userImage+’"><div class="replyOther_div" id=""><span class="username_span" style="color:#F00"></span>:<span class="content_span" style="color:#F00"></span></div><input class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true"><button class="submitComment" onclick="saveComment()">评论</button></div><ul class="commentList"></ul><a class="allComments" id="allComments" onclick="getMoreComment()">点击获取更多</a></div></div></article></li>';
										for(var j in data.commentList[i].replay){
											var htmls = document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML;
											if(data.commentList[i].replay[j].touserName==null){
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    </span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}else{
												document.getElementById(data.commentList[i].answerId).getElementsByClassName("commentList")[0].innerHTML = htmls + '<li id="'+data.commentList[i].replay[j].commentId+'"><img class="userImg" src="'+data.commentList[i].replay[j].userImage+'"><div class="commentDetail"><p class="userName">'+data.commentList[i].replay[j].userName+'<span class="touserName">    '+data.commentList[i].replay[j].touserName+'</span></p><p class="content" onclick="replyOther()">'+data.commentList[i].replay[j].community+'</p><p class="commentTime">'+data.commentList[i].replay[j].time+'</p></div></li>';
											}
										}
									}
								}
							}

						}
						if(data.endnumber<data.totalnumber){
							startnumber = data.endnumber;
						}else{
							document.getElementById("loading").remove();
						}
					}
				}
			})
		});