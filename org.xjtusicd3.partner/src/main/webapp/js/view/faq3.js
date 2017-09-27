
function comment(){
	var faqusername = document.getElementsByClassName("username")[0].innerHTML;
	var faqtitle = document.getElementById("detailTplWrapper").getElementsByClassName("title")[0].innerHTML;
	var comment = UE.getEditor('editor').getContent();
	if(comment==""){
		document.getElementById('null').style.display='block';
		setTimeout("codefans3()",3000);
	}else{
		$.ajax({
			type:"POST",
			url:"/org.xjtusicd3.partner/saveComment.html",
			data:{
				"faqtitle":faqtitle,
				"comment":comment,
				"faqusername":faqusername
			},
			dataType:"json",
			success:function(data){
				if(data=="0"){
					self.location='login.html'; 
				}else{
					window.location.reload(); 
				}
			}
		})
	}
}
function showeditor(){
	document.getElementById('content').style.display="none";
	document.getElementById('answer-ueditor').style.display="block";
	document.getElementsByClassName('clearfix commentScoreBtn')[0].style.display="block";
}
function openreply(){
	var _event= browserEvent();
	if(_event.parentNode.parentNode.getElementsByClassName('subCommentList')[0].style.display=="none"){
		commentid = _event.parentNode.parentNode.parentNode.id;
		document.getElementById(commentid).getElementsByClassName('subCommentList')[0].style.display="block";
		document.getElementById(commentid).getElementsByClassName('commentReplayText')[0].style.display="block";
		document.getElementById(commentid).getElementsByClassName('ac')[0].style.display="block";
		document.getElementById(commentid).getElementsByClassName('commentReplayUser')[0].style.display="block";
		username = _event.parentNode.parentNode.getElementsByClassName('username')[0].innerHTML;
		document.getElementById(commentid).getElementsByClassName('username_span')[0].innerHTML=username;
		document.getElementById(commentid).getElementsByClassName('commentReplayUser')[0].id=commentid+"_"
	}else{
		commentid = _event.parentNode.parentNode.parentNode.id;
		document.getElementById(commentid).getElementsByClassName('subCommentList')[0].style.display="none";
		document.getElementById(commentid).getElementsByClassName('commentReplayText')[0].style.display="none";
		document.getElementById(commentid).getElementsByClassName('ac')[0].style.display="none";
		document.getElementById(commentid).getElementsByClassName('commentReplayUser')[0].style.display="none";
	}
}
function replycomment(){
	var _event= browserEvent();
	var questionId = document.URL.split("q=")[1].split("#")[0];
	var comment = _event.parentNode.parentNode.getElementsByClassName("commentReplayText")[0].value;
	var commentId = _event.parentNode.parentNode.parentNode.id;
	var duo = "";
	if(_event.parentNode.parentNode.getElementsByClassName("content_span")[0].innerHTML==""){
		duo="0";
	}else{
		duo="1";
	}
	if(comment==""){
		document.getElementById('null').style.display='block';
		setTimeout("codefans3()",3000);
	}else{
		$.ajax({
			type:"POST",
			url:"/org.xjtusicd3.partner/saveFaqComment.html",
			data:{
				"questionId":questionId,
				"comment":comment,
				"commentId":commentId,
				"duo":duo
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
	var _event= browserEvent();
    if(checkHover(e,obj)){
	_event.parentNode.getElementsByClassName("commentReplay")[0].style.display="block";
  }
}
function hiddendelete(e, obj){
	var _event= browserEvent();
	if(checkHover(e,obj)){
	_event.parentNode.getElementsByClassName("commentReplay")[0].style.display="none";
  }
}
//删除自己的回复
function deleteComment(){
	var _event= browserEvent();
	var commentId = _event.parentNode.parentNode.parentNode.id;
	$.ajax({
		type:"POST",
		url:"/org.xjtusicd3.partner/deleteReply.html",
		data:{
			"commentId":commentId
		},
		dataType:"json",
		success:function(data){
			if(data.value=="0"){
				self.location='login.html';
			}else{
				location.reload();
			}
		}
	})
}

//特定回复某人的评论
function replyOther(){
	var _event= browserEvent();
	var username = document.getElementById("zhao_hidden").innerHTML;
	var content = _event.parentNode.getElementsByClassName("text")[0].innerHTML;
	if(content.length<10){
		content = content;
	}else{
		content = content.substr(0,10)+"...";
	}
	var tousername = _event.parentNode.parentNode.getElementsByClassName("zhao")[0].innerHTML;
	var commentId = _event.parentNode.parentNode.id;
	if(username!=tousername){
		_event.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("username_span")[0].innerHTML=tousername;
		_event.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("content_span")[0].innerHTML=content;
		_event.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("commentReplayUser")[0].id=commentId+"_";
	}
}
//获取更多评论
function querymorecomment(){
	startnumber = document.getElementsByClassName("comment")[0].getElementsByClassName("commentList").length;
	var questionId = document.URL.split("q=")[1].split("#")[0];
	$.ajax({
		type:"POST",
		url:"/org.xjtusicd3.partner/queryMoreComment.html",
		data:{
			"questionId":questionId,
			"startnumber":startnumber
		},
		dataType:"json",
		success:function(data){
			if(data.value=="0"){
				self.location='login.html';
			}else if(data.value=="1"){
				if(document.getElementById("querymorelink")!=null){
					document.getElementById("querymorelink").remove();
				}
				if(data.endnumber<data.totalnumber){
					startnumber = data.endnumber;
					for(var i in data.commentList){
						var htmls = document.getElementsByClassName("comment")[0].innerHTML;
						document.getElementsByClassName("comment")[0].innerHTML = htmls+ '<ul class="commentList" id="'+data.commentList[i].commentId+'"><li class="commentLiContent"><div class="userContent clearfix"><span class="userPic"><img src="'+data.commentList[i].userViews[0].userImage+'"></span><span class="username">'+data.commentList[i].userViews[0].userName+'</span><span class="line">|</span><span class="time">'+data.commentList[i].commentTime+'</span></div><div class="clearfix content"><a href="javascript:void(0);" class="commentReplay" onclick="openreply()">回复('+data.commentList[i].commentNumber+')</a><p class="text"></p><p>'+data.commentList[i].commentContent+'</p><p></p></div><ul class="subCommentList" style="display:none"></ul><div class="commentReplayUser" id="">回复：<span class="username_span" style="color:#F00"></span>:<span class="content_span" style="color:#F00"></span></div><textarea class="commentReplayText" id="replycontenttext" style="display:none"></textarea><p class="ac" style="display:none"><input type="button" value="发表" class="replayBtn" onclick="replycomment()"></p></li></ul><p class="ac" id="querymorelink" class="display:block"><a href="javascript:void(0);" onclick="querymorecomment()">查看更多...</a></p>';
					}
				}else{
					for(var i in data.commentList){
						var htmls = document.getElementsByClassName("comment")[0].innerHTML;
						document.getElementsByClassName("comment")[0].innerHTML = htmls+ '<ul class="commentList" id="'+data.commentList[i].commentId+'"><li class="commentLiContent"><div class="userContent clearfix"><span class="userPic"><img src="'+data.commentList[i].userViews[0].userImage+'"></span><span class="username">'+data.commentList[i].userViews[0].userName+'</span><span class="line">|</span><span class="time">'+data.commentList[i].commentTime+'</span></div><div class="clearfix content"><a href="javascript:void(0);" class="commentReplay" onclick="openreply()">回复('+data.commentList[i].commentNumber+')</a><p class="text"></p><p>'+data.commentList[i].commentContent+'</p><p></p></div><ul class="subCommentList" style="display:none"></ul><div class="commentReplayUser" id="">回复：<span class="username_span" style="color:#F00"></span>:<span class="content_span" style="color:#F00"></span></div><textarea class="commentReplayText" id="replycontenttext" style="display:none"></textarea><p class="ac" style="display:none"><input type="button" value="发表" class="replayBtn" onclick="replycomment()"></p></li></ul>';
					}
				}
			}
		}
	})
}
//获取更多回复
function querymorereply(){
	var _event= browserEvent();
	var commentid = _event.parentNode.parentNode.parentNode.parentNode.id;
	startnumber = _event.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("_commentlist").length;
	$.ajax({
		type:"POST",
		url:"/org.xjtusicd3.partner/queryMoreReply.html",
		data:{
			"commentid":commentid,
			"startnumber":startnumber
		},
		dataType:"json",
		success:function(data){
			if(data.value=="0"){
				self.location='login.html';
			}else if(data.value=="1"){
				document.getElementById("querymorelink2").remove();
				if(data.endnumber<data.totalnumber){
					startnumber = data.endnumber;
					for(var i in data.commentList){
						var htmls = document.getElementsByClassName("subCommentList")[0].innerHTML;
						document.getElementsByClassName("comment")[0].innerHTML = htmls+ '<ul class="commentList" id="'+data.commentList[i].commentId+'"><li class="commentLiContent"><div class="userContent clearfix"><span class="userPic"><img src="'+data.commentList[i].userViews[0].userImage+'"></span><span class="username">'+data.commentList[i].userViews[0].userName+'</span><span class="line">|</span><span class="time">'+data.commentList[i].commentTime+'</span></div><div class="clearfix content"><a href="javascript:void(0);" class="commentReplay" onclick="openreply()">回复('+data.commentList[i].commentNumber+')</a><p class="text"></p><p>'+data.commentList[i].commentContent+'</p><p></p></div><ul class="subCommentList" style="display:none"></ul><div class="commentReplayUser" id="">回复：<span class="username_span" style="color:#F00"></span>:<span class="content_span" style="color:#F00"></span></div><textarea class="commentReplayText" id="replycontenttext" style="display:none"></textarea><p class="ac" style="display:none"><input type="button" value="发表" class="replayBtn" onclick="replycomment()"></p></li></ul><p class="ac" id="querymorelink" class="display:block"><a href="javascript:void(0);" onclick="querymorecomment()">查看更多...</a></p>';
					}
				}else{
					for(var i in data.commentList){
						var htmls = document.getElementsByClassName("subCommentList")[0].innerHTML;
						if(data.commentList[i].toUserName==null){
							document.getElementsByClassName("subCommentList")[0].innerHTML = htmls+ '<li id="'+data.commentList[i].commentId+'" class="_commentlist" onmouseover="showdelete(event,this)" onmouseout="hiddendelete(event,this)"><div class="userContent clearfix" id=""><span class="username"><span class="zhao">'+data.commentList[i].userName+'</span> 评论</span><span class="line">|</span><span class="time">'+data.commentList[i].time+'</span></div><div class="clearfix content"><a class="commentReplay" style="display: none;"></a><p class="text">'+data.commentList[i].comment+'</p></div></li>';
						}else{
							document.getElementsByClassName("subCommentList")[0].innerHTML = htmls+ '<li id="'+data.commentList[i].commentId+'" class="_commentlist" onmouseover="showdelete(event,this)" onmouseout="hiddendelete(event,this)"><div class="userContent clearfix" id="'+data.commentList[i].userName+'"><span class="username"><span class="zhao">'+data.commentList[i].userName+'</span> 回复：'+data.commentList[i].toUserName+'</span><span class="line">|</span><span class="time">'+data.commentList[i].time+'</span></div><div class="clearfix content"><a class="commentReplay" style="display: none;"></a><p class="text">'+data.commentList[i].comment+'</p></div></li>';
						}
					}
				}
			}
		}
	})
}
//收藏
function favorite(){
	var questionId = document.URL.split("q=")[1].split("#")[0];
	$.ajax({
		type:"POST",
		url:"/org.xjtusicd3.partner/saveCollectionFAQ.html",
		data:{
			"questionId":questionId
		},
		dataType:"json",
		success:function(data){
			if(data.value=="0"){
				self.location='login.html';
			}else if(data.value=="1"){
				document.getElementById("favoriteHeart").setAttribute("class","share redheart");
			}else if(data.value=="2"){
				document.getElementById("favoriteHeart").setAttribute("class","share heart");
			}
		}
	})
}
//评分
function score(){
	var _event= browserEvent();
	var questionId = document.URL.split("q=")[1].split("#")[0];
	var score = _event.parentNode.parentNode.getElementsByClassName("fenshu")[0].innerHTML;
	$.ajax({
		type:"POST",
		url:"/org.xjtusicd3.partner/saveFAQscore.html",
		data:{
			"questionId":questionId,
			"score":score
		},
		dataType:"json",
		success:function(data){
			if(data.value=="0"){
				self.location='login.html';
			}else if(data.value=="1"){
				document.getElementsByClassName("shareBox_hidden")[0].style.display="block";
			}
		}
	})
}
//分享
function saveShare(){
	var state;
	if(document.getElementsByClassName("fa fa-share-alt")[0].style.color!="red"){
		state=1;
	}else{
		state=2;
	}
	var questionId = document.URL.split("q=")[1];
	$.ajax({
		type:"POST",
		url:"/org.xjtusicd3.partner/saveShare.html",
		data:{
			"questionId":questionId,
			"state":state
		},
		dataType:"json",
		success:function(data){
			if(data.value=="0"){
				self.location='login.html';
			}else if(data.value=="1"){
				document.getElementsByClassName("fa fa-share-alt")[0].style.color="red";
			}else{
				document.getElementsByClassName("fa fa-share-alt")[0].style.color="#9c9c9c";
			}
		}
	})
}