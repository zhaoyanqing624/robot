			$(function() {
				var dd = new DropDown( $('#dd') );
				$(document).click(function() {
					// all dropdowns
					$('.wrapper-dropdown-3').removeClass('active');
				});
			});
			
			$('#loading').click(function() {
				var startnumber = document.getElementById("searchResult").childElementCount;
				var type = document.URL.split("type=")[1];
				var c = document.URL.split("c=")[1].split("&")[0];
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getMoreCommunity.html",
					data:{
						"startnumber":startnumber,
						"type":type,
						"c":c
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							for(var i in data.communityViews){
								if(data.communityViews[i].userId!=null){
									if(data.communityViews[i].answer.length>100){
										var htmls = document.getElementById("searchResult").innerHTML;
										document.getElementById("searchResult").innerHTML = htmls+ '<li id="'+data.communityViews[i].communityId+'_'+'"><article ><div class="tag"><ul><li>'+data.communityViews[i].classifyName+'</li><li class="type">回答</li></ul><div class="time"><p>'+data.communityViews[i].time+'</p></div></div><div class="title"><h2><a href="question2.html?q='+data.communityViews[i].communityId+'">'+data.communityViews[i].communityTitle+'</a></h2></div><div class="description"><div class="answerer" data-id="270369"><img class="answerImg" src="'+data.communityViews[i].userImage+'"><div><a href=""><span class="user_name">'+data.communityViews[i].userName+'</span>&nbsp;&nbsp;<span>'+data.communityViews[i].signature+'</span></a></div><div><img src="images/bluepoint.png" class="bluepoint">贡献'+data.communityViews[i].totalCommunityNumber+'个回答，获得'+data.communityViews[i].totalLikesNumber+'个赞</div></div><div class="detail"><div class="detailP">'+data.communityViews[i].answer.substr(0,100)+'......'+'<span class="readMore">查看更多</span></div></div><div class="fullDetail hidden"><p>'+data.communityViews[i].answer+'</p></div></div><div class="options"><ul><li class="special"><a  class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">'+data.communityViews[i].likesNumber+'</span></a></li><li><a data-fun="toComment" class="fm_ele" fm-type="button" fm-name="answer_comment" fm-operation="click" fm-zoon="option_area"><span>评论 </span><span class="number">'+data.communityViews[i].communityNumber+'</span></a></li><span class="fold"><a data-fun="fold"><span class="foldicon"></span>收起</a></span></ul></div></article></li>';
									}else{
										var htmls = document.getElementById("searchResult").innerHTML;
										document.getElementById("searchResult").innerHTML = htmls+ '<li id="'+data.communityViews[i].communityId+'_'+'"><article ><div class="tag"><ul><li>'+data.communityViews[i].classifyName+'</li><li class="type">回答</li></ul><div class="time"><p>'+data.communityViews[i].time+'</p></div></div><div class="title"><h2><a href="question2.html?q='+data.communityViews[i].communityId+'">'+data.communityViews[i].communityTitle+'</a></h2></div><div class="description"><div class="answerer" data-id="270369"><img class="answerImg" src="'+data.communityViews[i].userImage+'"><div><a href=""><span class="user_name">'+data.communityViews[i].userName+'</span>&nbsp;&nbsp;<span>'+data.communityViews[i].signature+'</span></a></div><div><img src="images/bluepoint.png" class="bluepoint">贡献'+data.communityViews[i].totalCommunityNumber+'个回答，获得'+data.communityViews[i].totalLikesNumber+'个赞</div></div><div class="detail"><div class="detailP">'+data.communityViews[i].answer+'</div></div><div class="fullDetail hidden"><p>'+data.communityViews[i].answer+'</p></div></div><div class="options"><ul><li class="special"><a  class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">'+data.communityViews[i].likesNumber+'</span></a></li><li><a data-fun="toComment" class="fm_ele" fm-type="button" fm-name="answer_comment" fm-operation="click" fm-zoon="option_area"><span>评论 </span><span class="number">'+data.communityViews[i].communityNumber+'</span></a></li><span class="fold"><a data-fun="fold"><span class="foldicon"></span>收起</a></span></ul></div></article></li>';
									}
								}else{
									if(data.communityViews[i].communityQuestion.length>100){
										var htmls = document.getElementById("searchResult").innerHTML;
										document.getElementById("searchResult").innerHTML = htmls+ '<li id="'+data.communityViews[i].communityId+'_'+'"><article ><div class="tag"><ul><li>'+data.communityViews[i].classifyName+'</li><li class="type">问题</li></ul><div class="time"><p>'+data.communityViews[i].time+'</p></div></div><div class="title"><h2><a href="question2.html?q='+data.communityViews[i].communityId+'">'+data.communityViews[i].communityTitle+'</a></h2></div><div class="description"><div class="detail"><div class="detailP">'+data.communityViews[i].communityQuestion.substr(0,100)+'......'+'<span class="readMore">查看更多</span></div></div><div class="fullDetail hidden"><p>'+data.communityViews[i].communityQuestion+'</p></div></div><div class="options"><ul><li class="special"><a onclick="create_edit(this)" class="unFocused fm_ele" ><span class="status" id="'+data.communityViews[i].communityId+'">回答</span></a></li><li><a data-fun="toComment" class="fm_ele" fm-type="button" fm-name="answer_comment" fm-operation="click" fm-zoon="option_area"><span>评论 </span><span class="number">'+data.communityViews[i].communityNumber+'</span></a></li><span class="fold"><a data-fun="fold"><span class="foldicon"></span>收起</a></span></ul></div></article></li>';										
									}else{
										var htmls = document.getElementById("searchResult").innerHTML;
										document.getElementById("searchResult").innerHTML = htmls+ '<li id="'+data.communityViews[i].communityId+'_'+'"><article ><div class="tag"><ul><li>'+data.communityViews[i].classifyName+'</li><li class="type">问题</li></ul><div class="time"><p>'+data.communityViews[i].time+'</p></div></div><div class="title"><h2><a href="question2.html?q='+data.communityViews[i].communityId+'">'+data.communityViews[i].communityTitle+'</a></h2></div><div class="description"><div class="detail"><div class="detailP">'+data.communityViews[i].communityQuestion+'</div></div><div class="fullDetail hidden"><p>'+data.communityViews[i].communityQuestion+'</p></div></div><div class="options"><ul><li class="special"><a onclick="create_edit(this)" class="unFocused fm_ele" ><span class="status" id="'+data.communityViews[i].communityId+'">回答</span></a></li><li><a data-fun="toComment" class="fm_ele" fm-type="button" fm-name="answer_comment" fm-operation="click" fm-zoon="option_area"><span>评论 </span><span class="number">'+data.communityViews[i].communityNumber+'</span></a></li><span class="fold"><a data-fun="fold"><span class="foldicon"></span>收起</a></span></ul></div></article></li>';	
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
	//点赞
	function getAgreeAnswer(){
		var questionid = event.target.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.id;
		var questionId = questionid.replace(/_/,"")
		if(questionid!="searchResult"){
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/saveAgreeAnswer2.html",
				data:{
					"questionId":questionId
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}else if(data.value=="1"){
						document.getElementById(questionid).getElementsByClassName("status")[0].innerHTML="已点赞";
						var number = parseInt(document.getElementById(questionid).getElementsByClassName("number")[0].innerHTML);
						document.getElementById(questionid).getElementsByClassName("number")[0].innerHTML = number+1;
					}else if(data.value=="2"){
						document.getElementById(questionid).getElementsByClassName("status")[0].innerHTML="点赞";
						var number = parseInt(document.getElementById(questionid).getElementsByClassName("number")[0].innerHTML);
						document.getElementById(questionid).getElementsByClassName("number")[0].innerHTML = number-1;
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
	function saveCommunityQuestion(){
		var title = document.getElementById("title").value;
		var description = UE.getEditor('editor').getContent();
		var obj = document.getElementsByName("category_id");
		check_val = [];
	    for(k in obj){
	        if(obj[k].checked)
	            check_val.push(obj[k].value);
	    }
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/saveCommunityQuestion.html",
				data:{
					"title":title,
					"description":description,
					"check_val":check_val[0]
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}else if(data.value=="1"){
					setTimeout("location.reload()",1000)
						document.getElementById('lasturl').innerHTML=data.url;
						document.getElementById('questionForm').style.display='none';
						document.getElementById('success').style.display='block';
						setTimeout("codefans()",3000);
						
					}else{
					setTimeout("location.reload()",1000)
						document.getElementById('lasturl').innerHTML=data.url;
						document.getElementById('questionForm').style.display='none';
						document.getElementById('chongfu').style.display='block';
						setTimeout("codefans2()",3000);
						
					}
				}
			})
		}
		function create_edit(){
			var a = document.getElementById(event.target.id+"_");
			if(document.getElementById('userNameText')==null){
				self.location='login.html';
			}else{
				if(a.children[0].lastChild.id=="addcomment"){
					a.children[0].lastChild.remove();
				}else{
					var b = document.getElementById('userNameText').textContent;
					$.ajax({
						type:"POST",
						url:"/org.xjtusicd3.partner/getUserInfo.html",
						data:{
							"useremail":b.replace(/您好：/,"")
						},
						dataType:"json",
						success:function(data){
							jsondata=$.parseJSON(data);
							var oDiv = document.createElement('div');
							oDiv.setAttribute("id","addcomment");
	    					oDiv.innerHTML  = '<div class="comment"><img class="deco" src="images/dia-deco.png"><div class="comment-outer"><div class="comment-Editor"><img class="userImg" src="'+jsondata[0].aVATAR+'"><input id="input_'+a.id.replace(/_/,"")+'" class="comment-Editor-input" type="text" placeholder="添加一个评论" growing-track="true"><button class="submitComment" onclick="addComment()" id="button_'+a.id.replace(/_/,"")+'">评论</button></div><ul class="commentList"></ul></div></div>';
	    					a.children[0].appendChild(oDiv);
						}
					})
				}
			}
		}
		
		function addComment(){
			var a = document.getElementById(event.target.id);
			var questionId = a.id.replace(/button_/,"");
			var commentContent = document.getElementById('input_'+questionId).value;
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/addComment.html",
				data:{
					"questionId":questionId,
					"commentContent":commentContent
				},
				dataType:"json",
				success:function(data){
					jsondata=$.parseJSON(data);
					if(jsondata.value=="0"){
						self.location='login.html';
					}else if(jsondata.value=="1"){
					setTimeout("location.reload()",1000)
						document.getElementById('lasturl').innerHTML=data.url;
						document.getElementById('success').style.display='block';
						setTimeout("codefans()",3000);
						
					}else{
					setTimeout("location.reload()",1000)
						document.getElementById('lasturl').innerHTML=data.url;
						document.getElementById('chongfu').style.display='block';
						setTimeout("codefans2()",3000);
					}
				}
			})
		}
		
		function getClassify(){
			var url = window.location.href;
			var classifyName = event.target.text;
			if(classifyName=="话题"){
				classifyName="all";
			}
			window.location.href = changeURLArg(url,'c',classifyName);
			
			function changeURLArg(url,arg,arg_val){ 
			    var pattern=arg+'=([^&]*)'; 
			    var replaceText=arg+'='+arg_val; 
			    if(url.match(pattern)){ 
			        var tmp='/('+ arg+'=)([^&]*)/gi'; 
			        tmp=url.replace(eval(tmp),replaceText); 
			        return tmp; 
			    }else{ 
			        if(url.match('[\?]')){ 
			            return url+'&'+replaceText; 
			        }else{ 
			            return url+'?'+replaceText; 
			        } 
			    } 
			    return url+'\n'+arg+'\n'+arg_val; 
			} 
		}
		
		function getType(){
			var url = window.location.href;
			var typename = event.target.text;
			var type;
			if(typename=="全部"){
				type = "all";
			}else if(typename=="已解决"){
				type = "1";
			}else if(typename=="待回答"){
				type = "2";
			}
			window.location.href = changeURLArg(url,'type',type);
			function changeURLArg(url,arg,arg_val){ 
			    var pattern=arg+'=([^&]*)'; 
			    var replaceText=arg+'='+arg_val; 
			    if(url.match(pattern)){ 
			        var tmp='/('+ arg+'=)([^&]*)/gi'; 
			        tmp=url.replace(eval(tmp),replaceText); 
			        return tmp; 
			    }else{ 
			        if(url.match('[\?]')){ 
			            return url+'&'+replaceText; 
			        }else{ 
			            return url+'?'+replaceText; 
			        } 
			    } 
			    return url+'\n'+arg+'\n'+arg_val; 
			} 
		}
		function questionForm(){
			document.getElementById("questionForm").style.display="block";
		}