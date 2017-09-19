	    	$(document).ready(function(){
	    		if('${IsMy}'=="0"){
	    			if('${payList}'=="0"){
	    				document.getElementById("weiguanzhu0").style.display="block";
	    				document.getElementById("yiguanzhu0").style.display="none";
	    			}else if('${payList}'=="1"){
	    				document.getElementById("weiguanzhu0").style.display="none";
	    				document.getElementById("yiguanzhu0").style.display="block";
	    			}
	    		}
	    		if(document.URL.indexOf("u=")>0){
	    			if(document.getElementById("duoduo").innerHTML==document.URL.split("u=")[1]){
						window.location.href = document.URL.split("?u=")[0];		    			
	    			}else{
	    				document.URL = document.URL;
	    			}
	    		}
	    	})
	    	function guanzhu(){
				var touserId = document.URL.split("u=")[1];
				var userId = '${uid}';
				if(userId!=touserId){
					$.ajax({
						type:"POST",
						url:"/org.xjtusicd3.partner/savePay.html",
						data:{
							"touserId":touserId
						},
						dataType:"json",
						success:function(data){
							if(data.value=="0"){
								self.location='login.html';
							}else if(data.value=="1"){
								document.getElementById("weiguanzhu0").style.display="none";
	    						document.getElementById("yiguanzhu0").style.display="block";
							}
						}
					})
				}
			}
			function quguanzhu(){
				var touserId = document.URL.split("u=")[1];
				var userId = '${uid}';
				if(userId!=touserId){
					$.ajax({
						type:"POST",
						url:"/org.xjtusicd3.partner/deletePay.html",
						data:{
							"touserId":touserId
						},
						dataType:"json",
						success:function(data){
							if(data.value=="0"){
								self.location='login.html';
							}else if(data.value=="1"){
								document.getElementById("weiguanzhu0").style.display="block";
	    						document.getElementById("yiguanzhu0").style.display="none";
							}
						}
					})
				}
			}
			function c_index(){
				document.getElementById("c_index").className="active";
				document.getElementById("c_know").className="";
				document.getElementById("c_question").className="";
				zhao1.style.display="block";
				zhao2.style.display="none";
				zhao3.style.display="none";
				zhao4.style.display="none";
			}
			//展示知识库
			function c_know(){
				document.getElementById("c_index").className="";
				document.getElementById("c_know").className="active";
				document.getElementById("c_question").className="";
				zhao2.style.display="block";
				zhao1.style.display="none";
				zhao3.style.display="none";
				zhao4.style.display="none";
				//获取我的知识
				var userId = document.getElementsByClassName("user-info")[0].id;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getpersonalFaq.html",
					data:{
						"userId":userId
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.faqView==""){
								document.getElementById("zhao2_article-main").innerHTML='<p class="notattend">你还没有任何原创知识，快去<a href="faq.html" class="red" target="_blank">发表知识</a>吧</p>';
							}else{
								if(document.getElementById("getMoreFaq1")!=null){
									document.getElementById("getMoreFaq1").remove();
								}
								for(var i in data.faqView){
									if(document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0]==null){
										document.getElementById("zhao2").getElementsByClassName("article-main")[0].innerHTML='<div  class="article-main"><div  class="articles-list"></div></div>';
									}
									if(document.getElementById("myfaq"+data.faqView[i].faqId)==null){
										var html = document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML;
										document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML=html + ' <div class="list-item article-item" id="myfaq'+data.faqView[i].faqId+'"> <h3 class="item-title"> <a target="_blank" href="faq3.html?q='+data.faqView[i].faqId+'" class="title-detail">'+data.faqView[i].title+'</a></h3> <div class="zhao"> <p class="item-bd">'+data.faqView[i].content+'......</p> </div> <div class="item-btm clearfix"> <ul class="l left-info"> <li class="hd-pic"> <a class="publisher-name" href="personal2.html?u='+data.faqView[i].userId+'" target="_blank">'+data.faqView[i].username+'</a> </li> </ul> <div class="r right-info"> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].scanNumber+'浏览</em> </div> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].collectionNumber+'收藏</em> </div> <div class=" l"> <i class="icon sns-comment"></i><em> '+data.faqView[0].commentNumber+'评论</em> </div> </div> </div> </div> ';
										var duo="";
										for(var j=0;j<4;j++){
											duo = duo + document.getElementById("zhao2_article-main").getElementsByClassName("zhao")[i].getElementsByTagName("p")[j].outerHTML;
										}
										document.getElementById("zhao2_article-main").getElementsByClassName("zhao")[i].innerHTML=duo;
									}
								}
								if(data.faqView[0].isMore=="1"){
									var html = document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML;
									document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreFaq1"> <span class="js-dynamicLoad " onclick="getMoreFaq1()">下拉显示更多</span> </p>';
								}
							}
						}
					}
				})
			}
			function getMoreFaq1(){
				var startnumber = document.getElementById("zhao2_article-main").getElementsByClassName("list-item article-item").length;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getMoreFaq1.html",
					data:{
						"startnumber":startnumber
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(document.getElementById("getMoreFaq1")!=null){
								document.getElementById("getMoreFaq1").remove();
							}
							for(var i in data.faqView){
								if(document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0]==null){
									document.getElementById("zhao2").getElementsByClassName("article-main")[0].innerHTML='<div  class="article-main"><div  class="articles-list"></div></div>';
								}
								if(document.getElementById("myfaq"+data.faqView[i].faqId)==null){
									var html = document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML;
									document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML=html + ' <div class="list-item article-item" id="myfaq'+data.faqView[i].faqId+'"> <h3 class="item-title"> <a target="_blank" href="faq3.html?q='+data.faqView[i].faqId+'" class="title-detail">'+data.faqView[i].title+'</a></h3> <div class="zhao"> <p class="item-bd">'+data.faqView[i].content+'......</p> </div> <div class="item-btm clearfix"> <ul class="l left-info"> <li class="hd-pic"> <a class="publisher-name" href="personal2.html?u='+data.faqView[i].userId+'" target="_blank">'+data.faqView[i].username+'</a> </li> </ul> <div class="r right-info"> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].scanNumber+'浏览</em> </div> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].collectionNumber+'收藏</em> </div> <div class=" l"> <i class="icon sns-comment"></i><em> '+data.faqView[0].commentNumber+'评论</em> </div> </div> </div> </div> ';
									var duo="";
									var addnumber = eval(startnumber)+eval(i);
									for(var j=0;j<4;j++){
										duo = duo + document.getElementById("zhao2_article-main").getElementsByClassName("zhao")[addnumber].getElementsByTagName("p")[j].outerHTML;
									}
									document.getElementById("zhao2_article-main").getElementsByClassName("zhao")[addnumber].innerHTML=duo;
								}
							}
							if(data.faqView[0].isMore=="1"){
								var html = document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML;
								document.getElementById("zhao2_article-main").getElementsByClassName("articles-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreFaq1"> <span class="js-dynamicLoad " onclick="getMoreFaq1()">下拉显示更多</span> </p>';
							}
						}
					}
				})
			}
			function c_question(){
				document.getElementById("c_index").className="";
				document.getElementById("c_know").className="";
				document.getElementById("c_question").className="active";
				zhao3.style.display="block";
				zhao1.style.display="none";
				zhao2.style.display="none";
				zhao4.style.display="none";
				var userId = document.getElementsByClassName("user-info")[0].id;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getpersonalCommunity.html",
					data:{
						"userId":userId
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.communityView==""){
								document.getElementById("zhao3_article-main").innerHTML='<p class="notattend">你还没有任何提问记录，快去<a href="question.html?c=all&type=all" class="red" target="_blank">问题中心</a>吧</p>';
							}else{
								if(document.getElementById("getMoreCommunity")!=null){
									document.getElementById("getMoreCommunity").remove();
								}
								for(var i in data.communityView){
									if(document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0]==null){
										document.getElementById("zhao3_article-main").innerHTML='<div class="ques-list"></div>';
									}
									if(document.getElementById("myCommunity"+data.communityView[i].questionId)==null){
										var html = document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML;
										document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML=html + '<div class="ques-answer" id="myCommunity'+data.communityView[i].questionId+'"> <div class="tag-img"> <a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank"> <img src="'+data.communityView[i].classifyImage+'"> </a> </div> <div class="from-tag">来自<a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank">'+data.communityView[i].classifyName+'</a></div> <div class="ques-con"> <a href="question2.html?q='+data.communityView[i].questionId+'" class="ques-con-content"  target="_blank">'+data.communityView[i].title+'</a> </div> <div class="about-info"> <span class="time">'+data.communityView[i].time.substring(0,16)+'</span> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank" class="reply-num">'+data.communityView[i].answerNumber+'个回答</a> </div> </div> ';
									}
								}
								if(data.communityView[0].isMore=="1"){
									var html = document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML;
									document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreCommunity"> <span class="js-dynamicLoad " onclick="getMoreCommunity()">点击显示更多</span> </p>';
								}
							}
						}
					}
				})
			}
			function getMoreCommunity(){
				var startnumber = document.getElementById("zhao3_article-main").getElementsByClassName("ques-answer").length;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getMoreCommunity1.html",
					data:{
						"startnumber":startnumber
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(document.getElementById("getMoreCommunity")!=null){
								document.getElementById("getMoreCommunity").remove();
							}
							for(var i in data.communityView){
								if(document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0]==null){
									document.getElementById("zhao3_article-main").innerHTML='<div class="ques-list"></div>';
								}
								if(document.getElementById("myCommunity"+data.communityView[i].questionId)==null){
									var html = document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML;
									document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML=html + '<div class="ques-answer" id="myCommunity'+data.communityView[i].questionId+'"> <div class="tag-img"> <a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank"> <img src="'+data.communityView[i].classifyImage+'"> </a> </div> <div class="from-tag">来自<a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank">'+data.communityView[i].classifyName+'</a></div> <div class="ques-con"> <a href="question2.html?q='+data.communityView[i].questionId+'" class="ques-con-content"  target="_blank">'+data.communityView[i].title+'</a> </div> <div class="about-info"> <span class="time">'+data.communityView[i].time.substring(0,16)+'</span> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank" class="reply-num">'+data.communityView[i].answerNumber+'个回答</a> </div> </div> ';
								}
							}
							if(data.communityView[0].isMore=="1"){
								var html = document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML;
								document.getElementById("zhao3_article-main").getElementsByClassName("ques-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreCommunity"> <span class="js-dynamicLoad " onclick="getMoreCommunity()">点击显示更多</span> </p>';
							}
						}
					}
				})
			}
			function getPayCommunity(){
				var userId = document.getElementsByClassName("user-info")[0].id;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getPayCommunity.html",
					data:{
						"userId":userId
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.communityView==""){
								document.getElementById("zhao3_article-main2").innerHTML='<p class="notattend">你还没有关注提问记录，快去<a href="question.html?c=all&type=all" class="red" target="_blank">问题中心</a>吧</p>';
							}else{
								if(document.getElementById("getMorePayCommunity")!=null){
									document.getElementById("getMorePayCommunity").remove();
								}
								for(var i in data.communityView){
									if(document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0]==null){
										document.getElementById("zhao3_article-main2").innerHTML='<div class="ques-list"></div>';
									}
									if(document.getElementById("payCommunity"+data.communityView[i].questionId)==null){
										var html = document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML;
										document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML=html + '<div class="ques-answer" id="payCommunity'+data.communityView[i].questionId+'"> <div class="tag-img"> <a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank"> <img src="'+data.communityView[i].classifyImage+'"> </a> </div> <div class="from-tag">来自<a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank">'+data.communityView[i].classifyName+'</a></div> <div class="ques-con"> <a href="question2.html?q='+data.communityView[i].questionId+'" class="ques-con-content"  target="_blank">'+data.communityView[i].title+'</a> </div><div class="answer-con" > <div class="user">最佳答案</div> <div class="answer-content"> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank">'+data.communityView[i].content+'</a> </div> </div> <div class="about-info"> <span class="time">'+data.communityView[i].replyTime.substring(0,16)+'</span> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank" class="reply-num">'+data.communityView[i].replyNumber+'个回复</a> </div> </div> ';
									}
								}
								if(data.communityView[0].isMore=="1"){
									var html = document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML;
									document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMorePayCommunity"> <span class="js-dynamicLoad " onclick="getMorePayCommunity()">点击显示更多</span> </p>';
								}
							}
						}
					}
				})
			}
			function getMorePayCommunity(){
				var startnumber = document.getElementById("zhao3_article-main2").getElementsByClassName("ques-answer").length;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getMorePayCommunity.html",
					data:{
						"startnumber":startnumber
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(document.getElementById("getMorePayCommunity")!=null){
								document.getElementById("getMorePayCommunity").remove();
							}
							for(var i in data.communityView){
								if(document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0]==null){
									document.getElementById("zhao3_article-main2").innerHTML='<div class="ques-list"></div>';
								}
								if(document.getElementById("payCommunity"+data.communityView[i].questionId)==null){
									var html = document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML;
									document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML=html + '<div class="ques-answer" id="payCommunity'+data.communityView[i].questionId+'"> <div class="tag-img"> <a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank"> <img src="'+data.communityView[i].classifyImage+'"> </a> </div> <div class="from-tag">来自<a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank">'+data.communityView[i].classifyName+'</a></div> <div class="ques-con"> <a href="question2.html?q='+data.communityView[i].questionId+'" class="ques-con-content"  target="_blank">'+data.communityView[i].title+'</a> </div><div class="answer-con" > <div class="user">最佳答案</div> <div class="answer-content"> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank">'+data.communityView[i].content+'</a> </div> </div> <div class="about-info"> <span class="time">'+data.communityView[i].replyTime.substring(0,16)+'</span> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank" class="reply-num">'+data.communityView[i].replyNumber+'个回复</a> </div> </div> ';
								}
							}
							if(data.communityView[0].isMore=="1"){
								var html = document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML;
								document.getElementById("zhao3_article-main2").getElementsByClassName("ques-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMorePayCommunity"> <span class="js-dynamicLoad " onclick="getMorePayCommunity()">点击显示更多</span> </p>';
							}
						}
					}
				})
			}
			function getReplyCommunity(){
				var userId = document.getElementsByClassName("user-info")[0].id;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getReplyCommunity.html",
					data:{
						"userId":userId
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.communityView==""){
								document.getElementById("zhao3_article-main3").innerHTML='<p class="notattend">你还没有回复提问记录，快去<a href="question.html?c=all&type=all" class="red" target="_blank">问题中心</a>吧</p>';
							}else{
								if(document.getElementById("getMorePayCommunity")!=null){
									document.getElementById("getMorePayCommunity").remove();
								}
								for(var i in data.communityView){
									if(document.getElementById("zhao3_article-main3").getElementsByClassName("ques-list")[0]==null){
										document.getElementById("zhao3_article-main3").innerHTML='<div class="ques-list"></div>';
									}
									if(document.getElementById("replyCommunity"+data.communityView[i].questionId)==null){
										var html = document.getElementById("zhao3_article-main3").getElementsByClassName("ques-list")[0].innerHTML;
										if(data.communityView[i].isreply=="0"){
											document.getElementById("zhao3_article-main3").getElementsByClassName("ques-list")[0].innerHTML=html + '<div class="ques-answer" id="replyCommunity'+data.communityView[i].questionId+'"> <div class="tag-img"> <a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank"> <img src="'+data.communityView[i].classifyImage+'"> </a> </div> <div class="from-tag">来自<a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank">'+data.communityView[i].classifyName+'</a></div> <div class="ques-con"> <a href="question2.html?q='+data.communityView[i].questionId+'" class="ques-con-content"  target="_blank">'+data.communityView[i].title+'</a> </div><div class="answer-con" > <div class="user">我的评论</div> <div class="answer-content"> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank">'+data.communityView[i].content+'</a> </div> </div> <div class="about-info"> <span class="time">'+data.communityView[i].replyTime.substring(0,16)+'</span> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank" class="reply-num">'+data.communityView[i].replyNumber+'个回复</a> </div> </div> ';
										}else{
											document.getElementById("zhao3_article-main3").getElementsByClassName("ques-list")[0].innerHTML=html + '<div class="ques-answer" id="replyCommunity'+data.communityView[i].questionId+'"> <div class="tag-img"> <a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank"> <img src="'+data.communityView[i].classifyImage+'"> </a> </div> <div class="from-tag">来自<a href="question.html?c='+data.communityView[i].classifyName+'&type=all" target="_blank">'+data.communityView[i].classifyName+'</a></div> <div class="ques-con"> <a href="question2.html?q='+data.communityView[i].questionId+'" class="ques-con-content"  target="_blank">'+data.communityView[i].title+'</a> </div><div class="answer-con" > <div class="user">我的回复</div> <div class="answer-content"> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank">'+data.communityView[i].content+'</a> </div> </div> <div class="about-info"> <span class="time">'+data.communityView[i].replyTime.substring(0,16)+'</span> <a href="question2.html?q='+data.communityView[i].questionId+'" target="_blank" class="reply-num">'+data.communityView[i].replyNumber+'个回复</a> </div> </div> ';
										}
									}
								}
								if(data.communityView[0].isMore=="1"){
									var html = document.getElementById("zhao3_article-main3").getElementsByClassName("ques-list")[0].innerHTML;
									document.getElementById("zhao3_article-main3").getElementsByClassName("ques-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getReplyCommunity"> <span class="js-dynamicLoad " onclick="getReplyCommunity()">点击显示更多</span> </p>';
								}
							}
						}
					}
				})
			}
			function guanzhubutton(){
				zhao1.style.display="none";
				zhao2.style.display="none";
				zhao3.style.display="none";
				zhao4.style.display="block";
				document.getElementById("investment_title").getElementsByTagName("div")[0].className="on";
				document.getElementById("investment_title").getElementsByTagName("div")[1].className="";
				document.getElementById("zhao4").getElementsByClassName("article-main")[0].style.display="block";
				document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].style.display="none";
				//获取关注
				var userId = document.getElementsByClassName("user-info")[0].id;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getPay.html",
					data:{
						"userId":userId
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.payView==""){
								document.getElementById("zhao4").getElementsByClassName("article-main")[0].innerHTML='<div class="ques-list"><div class="nodata">您还没有关注任何人</div></div>';
							}else{
								for(var i in data.payView){
									if(document.getElementById("pay"+data.payView[i].userId)==null){
										var html = document.getElementById("zhao4").getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML;
										if(data.payView[i].work==null){
											if(data.payView[i].isTogetherPay=="1"){
												document.getElementById("zhao4").getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="pay'+data.payView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><img src="'+data.payView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><span class="nickname">'+data.payView[i].userName+'</span></a></div><p class="desc"></p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.payView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.payView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">互相关注</a><a href="message.html?u='+data.payView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}else{
												document.getElementById("zhao4").getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="pay'+data.payView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><img src="'+data.payView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><span class="nickname">'+data.payView[i].userName+'</span></a></div><p class="desc"></p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.payView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.payView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">已关注</a><a href="message.html?u='+data.payView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}
										}else{
											if(data.payView[i].isTogetherPay=="1"){
												document.getElementById("zhao4").getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="pay'+data.payView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><img src="'+data.payView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><span class="nickname">'+data.payView[i].userName+'</span></a><ul class="icon-list"><li class="u-icon imooc-teacher"></li></ul></div><p class="desc">'+data.payView[i].work+'</p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.payView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.payView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">互相关注</a><a href="message.html?u='+data.payView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}else{
												document.getElementById("zhao4").getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="pay'+data.payView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><img src="'+data.payView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.payView[i].userId+'" target="_blank"><span class="nickname">'+data.payView[i].userName+'</span></a><ul class="icon-list"><li class="u-icon imooc-teacher"></li></ul></div><p class="desc">'+data.payView[i].work+'</p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.payView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.payView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">已关注</a><a href="message.html?u='+data.payView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}
										}
									}
								}
							}
						}
					}
				})
			}
			function beiguanzhubutton(){
				zhao1.style.display="none";
				zhao2.style.display="none";
				zhao3.style.display="none";
				zhao4.style.display="block";
				document.getElementById("investment_title").getElementsByTagName("div")[0].className="";
				document.getElementById("investment_title").getElementsByTagName("div")[1].className="on";
				document.getElementById("zhao4").getElementsByClassName("article-main")[0].style.display="none";
				document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].style.display="block";
				//获取粉丝
				var userId = document.getElementsByClassName("user-info")[0].id;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getbePay.html",
					data:{
						"userId":userId
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.bepayView==""){
								document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].innerHTML='<div class="ques-list"><div class="nodata">您还没有任何粉丝关注</div></div>';
							}else{
								for(var i in data.bepayView){
									if(document.getElementById("bepay"+data.bepayView[i].userId)==null){
										var html = document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML;
										if(data.bepayView[i].work==null){
											if(data.bepayView[i].isTogetherPay=="1"){
												document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="bepay'+data.bepayView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><img src="'+data.bepayView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><span class="nickname">'+data.bepayView[i].userName+'</span></a></div><p class="desc"></p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.bepayView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.bepayView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">互相关注</a><a href="message.html?u='+data.bepayView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}else{
												document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="bepay'+data.bepayView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><img src="'+data.bepayView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><span class="nickname">'+data.bepayView[i].userName+'</span></a></div><p class="desc"></p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.bepayView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.bepayView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">已关注</a><a href="message.html?u='+data.bepayView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}
										}else{
											if(data.bepayView[i].isTogetherPay=="1"){
												document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="bepay'+data.bepayView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><img src="'+data.bepayView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><span class="nickname">'+data.bepayView[i].userName+'</span></a><ul class="icon-list"><li class="u-icon imooc-teacher"></li></ul></div><p class="desc">'+data.bepayView[i].work+'</p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.bepayView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.bepayView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">互相关注</a><a href="message.html?u='+data.bepayView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}else{
												document.getElementById("zhao4").getElementsByClassName("investment_con_list")[0].getElementsByClassName("concern-list")[0].getElementsByTagName("ul")[0].innerHTML = html + '<li class="box" id="bepay'+data.bepayView[i].userId+'"><div class="left-img"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><img src="'+data.bepayView[i].userImage+'" class="top_head"></a></div><div class="right-c"><div class="title"><a href="personal2.html?u='+data.bepayView[i].userId+'" target="_blank"><span class="nickname">'+data.bepayView[i].userName+'</span></a><ul class="icon-list"><li class="u-icon imooc-teacher"></li></ul></div><p class="desc">'+data.bepayView[i].work+'</p><div class="fs-line"><a class="u-target"><span class="group"><em>关注</em><em class="u-margin-l-5">'+data.bepayView[0].payNumber+'</em></span></a><a class="u-target u-margin-l-15"><span class="group"><em>粉丝</em><em class="u-margin-l-5">'+data.bepayView[i].bepayNumber+'</em></span></a></div><div class="btn-line" ><a href="Javascript:" class="btn-o btn-gray-o  js-concern-already">已关注</a><a href="message.html?u='+data.bepayView[i].userId+'" target="_blank" class="btn-o btn-gray-o  js-concern-msg">私信</a></div></div></li>';
											}
										}
									}
								}
							}
						}
					}
				})
			}
			function getMoreIndex(){
				var userId = document.getElementsByClassName("user-info")[0].id;
	    		var time1=document.getElementById("notices").getElementsByClassName("time")[0].innerHTML;
	    		var time2=document.getElementById("notices").getElementsByClassName("time")[0].innerHTML;
	    		var time3=document.getElementById("notices").getElementsByClassName("time")[0].innerHTML;
	    		var time11=document.getElementById("notices").getElementsByClassName("time")[0].innerHTML;
	    		var time22=document.getElementById("notices").getElementsByClassName("time")[0].innerHTML;
	    		var time33=document.getElementById("notices").getElementsByClassName("time")[0].innerHTML;
	    		//获取作者的关注
	    		var size1 = document.getElementById("notices").getElementsByClassName("item newuser").length;
	    		if(size1!=0){
		    		for(var i=0;i<size1;i++){
		    			var url = document.getElementById("notices").getElementsByClassName("item newuser")[i].getElementsByClassName("fl")[0].href;
		    			var touserId = url.split("u=")[1];
		    			if(userId==touserId){
			    			var time1_1 = document.getElementById("notices").getElementsByClassName("item newuser")[i].getElementsByClassName("time")[0].innerHTML;
							if((new Date(time1_1).getTime()) <(new Date(time1).getTime())){
								time1 = time1_1;
							}
		    			}else{
		    				var time11_11 = document.getElementById("notices").getElementsByClassName("item newuser")[i].getElementsByClassName("time")[0].innerHTML;
		    				if((new Date(time11_11).getTime()) <(new Date(time11).getTime())){
								time11 = time11_11;
							}
		    			}
		    		}
	    		}
	    		//获取作者的faq
	    		var size2 = document.getElementById("notices").getElementsByClassName("item newarticlescrap").length;
	    		if(size2!=0){
	    			for(var i=0;i<size2;i++){
	    				var url = document.getElementById("notices").getElementsByClassName("item newarticlescrap")[i].getElementsByClassName("fl")[0].href;
	    				var touserId = url.split("u=")[1];
	    				if(userId==touserId){
	    					var time2_2 = document.getElementById("notices").getElementsByClassName("item newarticlescrap")[i].getElementsByClassName("time")[0].innerHTML;
	    					if((new Date(time2_2).getTime()) <(new Date(time2).getTime())){
								time2 = time2_2;
							}
	    				}else{
	    					var time22_22 = document.getElementById("notices").getElementsByClassName("item newuser")[i].getElementsByClassName("time")[0].innerHTML;
		    				if((new Date(time22_22).getTime()) <(new Date(time22).getTime())){
								time22 = time22_22;
							}
	    				}
	    			}
	    		}
	    		//获取作者的community
	    		var size3 = document.getElementById("notices").getElementsByClassName("item newanswer").length;
	    		if(size3!=0){
	    			var time3_3;
	    			for(var i=0;i<size3;i++){
	    				var url = document.getElementById("notices").getElementsByClassName("item newanswer")[i].getElementsByClassName("fl")[0].href;
	    				var touserId = url.split("u=")[1];
	    				if(userId==touserId){
	    					var time3_3 = document.getElementById("notices").getElementsByClassName("item newanswer")[i].getElementsByClassName("time")[0].innerHTML;
	    					if((new Date(time3_3).getTime()) <(new Date(time3).getTime())){
								time3 = time3_3;
							}
	    				}else{
	    					var time33_33 = document.getElementById("notices").getElementsByClassName("item newanswer")[i].getElementsByClassName("time")[0].innerHTML;
	    					if((new Date(time33_33).getTime()) <(new Date(time33).getTime())){
								time33 = time33_33;
							}
	    				}
	    			}
	    		}
	    		
		    	$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getMoreIndex.html",
					data:{
						"time1":time1,
						"time2":time2,
						"time3":time3,
						"time11":time11,
						"time22":time22,
						"time33":time33
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else{
							if(data.personalIndexSize>=5){
								for(var i=0;i<5;i++){
									if(data.personalIndex[i].how=="创建了知识"||data.personalIndex[i].how=="修改了知识"||data.personalIndex[i].how=="推荐了知识"){
										document.getElementById("getMoreIndex").remove();
										var html = document.getElementById("notices").innerHTML;
										document.getElementById("notices").innerHTML = html + '<div class="item newarticlescrap"> <span class=" pa share js-share"></span> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'"  target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <a href="faq3.html?q='+data.personalIndex[i].questionId+'" target="_blank"> <div class="body pr"> <div class="content"> <div class="subtitle">'+data.personalIndex[i].title+'</div> <div class="detail ">'+data.personalIndex[i].content+'</div> <div class="cb"></div> </div> </div> </a> </div> <div class="cb"></div> </div>';
										var htmls = document.getElementById("zhao1").innerHTML;
										document.getElementById("zhao1").innerHTML = htmls + '<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreIndex"> <span class="js-dynamicLoad " onclick="getMoreIndex()">下拉显示更多</span> </p>';
									}else if(data.personalIndex[i].how=="在问吧提问"||data.personalIndex[i].how=="推荐了问题"){
										document.getElementById("getMoreIndex").remove();
										var html = document.getElementById("notices").innerHTML;
										document.getElementById("notices").innerHTML = html + '<div class="item newanswer"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'"  target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="question.html?c='+data.personalIndex[i].from+'&type=all" target="_blank"> <img src="'+data.personalIndex[i].fromImage+'" width="150" height="110"> </a><div class="content"> <div class="tag"> <span>来自</span> <a href="question.html?c='+data.personalIndex[i].from+'&type=all" target="_blank"> <span class="ml10">'+data.personalIndex[i].from+'</span> </a> </div> <div class="subtitle"> <a href="question2.html?q='+data.personalIndex[i].questionId+'" target="_blank">'+data.personalIndex[i].content+'</a> </div> </div> </div> </div> <div class="cb"></div> </div>';
										var htmls = document.getElementById("zhao1").innerHTML;
										document.getElementById("zhao1").innerHTML = htmls + '<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreIndex"> <span class="js-dynamicLoad " onclick="getMoreIndex()">下拉显示更多</span> </p>';
									}else if(data.personalIndex[i].how=="关注了用户"){
										document.getElementById("getMoreIndex").remove();
										var html = document.getElementById("notices").innerHTML;
										if(data.personalIndex[i].touserSex=="男"){
											document.getElementById("notices").innerHTML = html + '<div class="item newuser"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].touserId+'" target="_blank"> <img class=" js-usercard-dialog   mr10" src="'+data.personalIndex[i].touserImage+'" width="40" height="40"> </a> <div class="content"> <div class="subtitle"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].touserId+'"  target="_blank">'+data.personalIndex[i].touserName+'</a> </div> <div class="tag "> <span class="gender mr10"></span>  <span class="mr10">'+data.personalIndex[i].touserAddress.split("0")[1].split("1")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("1")[1].split("2")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("2")[1].split("3")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserJob+'</span> </div> </div> </div> </div> <div class="cb"></div> </div>';
										}else if(data.personalIndex[i].touserSex=="女"){
											document.getElementById("notices").innerHTML = html + '<div class="item newuser"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].touserId+'" target="_blank"> <img class=" js-usercard-dialog   mr10" src="'+data.personalIndex[i].touserImage+'" width="40" height="40"> </a> <div class="content"> <div class="subtitle"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].touserId+'"  target="_blank">'+data.personalIndex[i].touserName+'</a> </div> <div class="tag "> <span class="gender girl mr10"></span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("0")[1].split("1")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("1")[1].split("2")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("2")[1].split("3")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserJob+'</span> </div> </div> </div> </div> <div class="cb"></div> </div>';
										}else{
											document.getElementById("notices").innerHTML = html + '<div class="item newuser"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].touserId+'" target="_blank"> <img class=" js-usercard-dialog   mr10" src="'+data.personalIndex[i].touserImage+'" width="40" height="40"> </a> <div class="content"> <div class="subtitle"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].touserId+'"  target="_blank">'+data.personalIndex[i].touserName+'</a> </div> <div class="tag "> <span class="sexSecret mr10"></span><span class="mr10">'+data.personalIndex[i].touserAddress.split("0")[1].split("1")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("1")[1].split("2")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("2")[1].split("3")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserJob+'</span> </div> </div> </div> </div> <div class="cb"></div> </div>';
										}
										var htmls = document.getElementById("zhao1").innerHTML;
										document.getElementById("zhao1").innerHTML = htmls + '<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreIndex"> <span class="js-dynamicLoad " onclick="getMoreIndex()">下拉显示更多</span> </p>';
									}
								}
							}else{
								document.getElementById("getMoreIndex").remove();
								for(var i in data.personalIndex){
									if(data.personalIndex[i].how=="创建了知识"||data.personalIndex[i].how=="修改了知识"||data.personalIndex[i].how=="推荐了知识"){
										var html = document.getElementById("notices").innerHTML;
										document.getElementById("notices").innerHTML = html + '<div class="item newarticlescrap"> <span class=" pa share js-share"></span> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'"  target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <a href="faq3.html?q='+data.personalIndex[i].questionId+'" target="_blank"> <div class="body pr"> <div class="content"> <div class="subtitle">'+data.personalIndex[i].title+'</div> <div class="detail ">'+data.personalIndex[i].content+'</div> <div class="cb"></div> </div> </div> </a> </div> <div class="cb"></div> </div>';
									}else if(data.personalIndex[i].how=="在问吧提问"||data.personalIndex[i].how=="推荐了问题"){
										var html = document.getElementById("notices").innerHTML;
										document.getElementById("notices").innerHTML = html + '<div class="item newanswer"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'"  target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="question.html?c='+data.personalIndex[i].from+'&type=all" target="_blank"> <img src="'+data.personalIndex[i].fromImage+'" width="150" height="110"> </a><div class="content"> <div class="tag"> <span>来自</span> <a href="question.html?c='+data.personalIndex[i].from+'&type=all" target="_blank"> <span class="ml10">'+data.personalIndex[i].from+'</span> </a> </div> <div class="subtitle"> <a href="question2.html?q='+data.personalIndex[i].questionId+'" target="_blank">'+data.personalIndex[i].content+'</a> </div> </div> </div> </div> <div class="cb"></div> </div>';
									}else if(data.personalIndex[i].how=="关注了用户"){
										var html = document.getElementById("notices").innerHTML;
										if(data.personalIndex[i].touserSex=="男"){
											document.getElementById("notices").innerHTML = html + '<div class="item newuser"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].touserId+'" target="_blank"> <img class=" js-usercard-dialog   mr10" src="'+data.personalIndex[i].touserImage+'" width="40" height="40"> </a> <div class="content"> <div class="subtitle"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].touserId+'"  target="_blank">'+data.personalIndex[i].touserName+'</a> </div> <div class="tag "> <span class="gender mr10"></span>  <span class="mr10">'+data.personalIndex[i].touserAddress.split("0")[1].split("1")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("1")[1].split("2")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("2")[1].split("3")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserJob+'</span> </div> </div> </div> </div> <div class="cb"></div> </div>';
										}else if(data.personalIndex[i].touserSex=="女"){
											document.getElementById("notices").innerHTML = html + '<div class="item newuser"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].touserId+'" target="_blank"> <img class=" js-usercard-dialog   mr10" src="'+data.personalIndex[i].touserImage+'" width="40" height="40"> </a> <div class="content"> <div class="subtitle"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].touserId+'"  target="_blank">'+data.personalIndex[i].touserName+'</a> </div> <div class="tag "> <span class="gender girl mr10"></span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("0")[1].split("1")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("1")[1].split("2")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("2")[1].split("3")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserJob+'</span> </div> </div> </div> </div> <div class="cb"></div> </div>';
										}else{
											document.getElementById("notices").innerHTML = html + '<div class="item newuser"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank"> <img src="'+data.personalIndex[i].userImage+'" class="userHead  js-usercard-dialog " width="40" height="40"> </a> <div class="content fr"> <div class="head"> <div class="name"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].userId+'" target="_blank">'+data.personalIndex[i].userName+'</a> </div> <div class="time">'+data.personalIndex[i].time.substring(0,16)+'</div> <div class="title">'+data.personalIndex[i].how+'</div> </div> <div class="body pr"> <a class="fl" href="personal2.html?u='+data.personalIndex[i].touserId+'" target="_blank"> <img class=" js-usercard-dialog   mr10" src="'+data.personalIndex[i].touserImage+'" width="40" height="40"> </a> <div class="content"> <div class="subtitle"> <a class=" js-usercard-dialog " href="personal2.html?u='+data.personalIndex[i].touserId+'"  target="_blank">'+data.personalIndex[i].touserName+'</a> </div> <div class="tag "> <span class="sexSecret mr10"></span><span class="mr10">'+data.personalIndex[i].touserAddress.split("0")[1].split("1")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("1")[1].split("2")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserAddress.split("2")[1].split("3")[0]+'</span> <span class="mr10">'+data.personalIndex[i].touserJob+'</span> </div> </div> </div> </div> <div class="cb"></div> </div>';
										}
									}
								}
							}
						}
					}
				})
			}
			function getCollectFaq(){
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getCollectFaq.html",
					data:{
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.faqView==""){
								document.getElementById("zhao2_article-main2").innerHTML='<p class="notattend">你还没有收藏任何原创知识，快去<a href="faq.html" class="red" target="_blank">发表知识</a>吧</p>';
							}else{
								for(var i in data.faqView){
									if(document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0]==null){
										document.getElementById("zhao2_article-main2").innerHTML='<div  class="article-main"><div  class="articles-list"></div></div>';
									}
									if(document.getElementById("mycollec"+data.faqView[i].faqId)==null){
										var html = document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML;
										document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML=html + '<div class="list-item article-item" id="mycollec'+data.faqView[i].faqId+'"> <h3 class="item-title"> <a target="_blank" href="faq3.html?q='+data.faqView[i].faqId+'" class="title-detail">'+data.faqView[i].title+'</a></h3> <div class="zhao">'+data.faqView[i].content+'</div> <div class="item-btm clearfix"> <ul class="l left-info"> <li class="hd-pic"> <a class="publisher-name" href="personal2.html?u='+data.faqView[i].userId+'" target="_blank">'+data.faqView[i].username+'</a> </li> </ul> <div class="r right-info"> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].scanNumber+'浏览</em> </div> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].collectionNumber+'收藏</em> </div> <div class=" l"> <i class="icon sns-comment"></i><em> '+data.faqView[i].commentNumber+'评论</em> </div> </div> </div> </div>';
										var duo="";
										for(var j=0;j<4;j++){
											duo = duo + document.getElementById("zhao2_article-main2").getElementsByClassName("zhao")[i].getElementsByTagName("p")[j].outerHTML;
										}
										document.getElementById("zhao2_article-main2").getElementsByClassName("zhao")[i].innerHTML=duo;
									}
								}
								if(document.getElementById("getMoreCollection")==null){
									if(data.faqView[0].isMore=="1"){
										var html = document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML;
										document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreCollection"> <span class="js-dynamicLoad " onclick="getMoreCollectFaq()">点击显示更多</span> </p>';
									}
								}
							}
						}
					}
				})
			}
			function getMoreCollectFaq(){
				var startnumber = document.getElementById("zhao2_article-main2").getElementsByClassName("list-item article-item").length;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getMoreCollectFaq.html",
					data:{
						"startnumber":startnumber
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(document.getElementById("getMoreCollection")!=null){
								document.getElementById("getMoreCollection").remove();
							}
							var number = document.getElementById("zhao2_article-main2").getElementsByClassName("list-item article-item").length;
							for(var i in data.faqView){
								if(document.getElementById("mycollec"+data.faqView[i].faqId)==null){
									var html = document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML;
									document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML=html + '<div class="list-item article-item" id="mycollec'+data.faqView[i].faqId+'"> <h3 class="item-title"> <a target="_blank" href="faq3.html?q='+data.faqView[i].faqId+'" class="title-detail">'+data.faqView[i].title+'</a></h3> <div class="zhao">'+data.faqView[i].content+'</div> <div class="item-btm clearfix"> <ul class="l left-info"> <li class="hd-pic"> <a class="publisher-name" href="personal2.html?u='+data.faqView[i].userId+'" target="_blank">'+data.faqView[i].username+'</a> </li> </ul> <div class="r right-info"> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].scanNumber+'浏览</em> </div> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].collectionNumber+'收藏</em> </div> <div class=" l"> <i class="icon sns-comment"></i><em> '+data.faqView[i].commentNumber+'评论</em> </div> </div> </div> </div>';
									var duo="";
									var addnumber = eval(number)+eval(i);
									for(var j=0;j<4;j++){
										duo = duo + document.getElementById("zhao2_article-main2").getElementsByClassName("zhao")[addnumber].getElementsByTagName("p")[j].outerHTML;
									}
									document.getElementById("zhao2_article-main2").getElementsByClassName("zhao")[addnumber].innerHTML=duo;
								}
							}
							if(document.getElementById("getMoreCollection")==null){
								if(data.faqView[0].isMore=="1"){
									var html = document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML;
									document.getElementById("zhao2_article-main2").getElementsByClassName("articles-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreCollection"> <span class="js-dynamicLoad " onclick="getMoreCollectFaq()">点击显示更多</span> </p>';
								}
							}
						}
					}
				})
			}
			function getCommentFaq(){
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getCommentFaq.html",
					data:{
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(data.faqView==""){
								document.getElementById("zhao2_article-main3").innerHTML='<p class="notattend">你还没有评论任何知识，快去<a href="faq.html" class="red" target="_blank">知识首页查看</a>吧</p>';
							}else{
								for(var i in data.faqView){
									if(document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0]==null){
										document.getElementById("zhao2_article-main3").innerHTML='<div  class="article-main"><div  class="articles-list"></div></div>';
									}
									if(document.getElementById("mycomment"+data.faqView[i].replyId)==null){
										var html = document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML;
										document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML=html + '<div class="list-item article-item" id="mycomment'+data.faqView[i].replyId+'"> <h3 class="item-title"> <a target="_blank" href="faq3.html?q='+data.faqView[i].faqId+'" class="title-detail">'+data.faqView[i].title+'</a></h3> <div class="zhao">'+data.faqView[i].content+'</div> <div class="duo"> <div class="answer-con"> <div class="user">我的回答</div><div class="answer-content"> <a href="faq3.html?p='+data.faqView[i].parentId+'&q='+data.faqView[i].faqId+'" target="_blank" style="color:#0683ff">'+data.faqView[i].reply+'</a> </div> </div> <div class="about-info"> <span class="time">'+data.faqView[i].replytime.substring(0,16)+'</span> <a href="faq3.html?p='+data.faqView[i].parentId+'&q='+data.faqView[i].faqId+'" target="_blank" class="reply-num">'+data.faqView[i].replyNumber+'个回复</a> </div></div> <div class="item-btm clearfix"> <ul class="l left-info"> <li class="hd-pic"> <a class="publisher-name" href="personal2.html?u='+data.faqView[i].userId+'" target="_blank">'+data.faqView[i].username+'</a> </li> </ul> <div class="r right-info"> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].scanNumber+'浏览</em> </div> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].collectionNumber+'收藏</em> </div> <div class=" l"> <i class="icon sns-comment"></i><em> '+data.faqView[i].commentNumber+'评论</em> </div> </div> </div> </div>';
										var duo="";
										for(var j=0;j<2;j++){
											duo = duo + document.getElementById("zhao2_article-main3").getElementsByClassName("zhao")[i].getElementsByTagName("p")[j].outerHTML;
										}
										document.getElementById("zhao2_article-main3").getElementsByClassName("zhao")[i].innerHTML=duo+"........";
									}
								}
								if(document.getElementById("getMoreCollection")==null){
									if(data.faqView[0].isMore=="1"){
										var html = document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML;
										document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreComment"> <span class="js-dynamicLoad " onclick="getMoreCommentFaq()">点击显示更多</span> </p>';
									}
								}
							}
						}
					}
				})
			}
			function getMoreCommentFaq(){
				var startnumber = document.getElementById("zhao2_article-main3").getElementsByClassName("list-item article-item").length;
				$.ajax({
					type:"POST",
					url:"/org.xjtusicd3.partner/getMoreCommentFaq.html",
					data:{
						"startnumber":startnumber
					},
					dataType:"json",
					success:function(data){
						if(data.value=="0"){
							self.location='login.html';
						}else if(data.value=="1"){
							if(document.getElementById("getMoreComment")!=null){
								document.getElementById("getMoreComment").remove();
							}
							var number = document.getElementById("zhao2_article-main3").getElementsByClassName("list-item article-item").length;
							for(var i in data.faqView){
								if(document.getElementById("mycomment"+data.faqView[i].faqId)==null){
									var html = document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML;
									document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML=html + '<div class="list-item article-item" id="mycomment'+data.faqView[i].replyId+'"> <h3 class="item-title"> <a target="_blank" href="faq3.html?q='+data.faqView[i].faqId+'" class="title-detail">'+data.faqView[i].title+'</a></h3> <div class="zhao">'+data.faqView[i].content+'</div> <div class="duo"> <div class="answer-con"> <div class="user">我的回答</div><div class="answer-content"> <a href="faq3.html?p='+data.faqView[i].parentId+'&q='+data.faqView[i].faqId+'" target="_blank" style="color:#0683ff">'+data.faqView[i].reply+'</a> </div> </div> <div class="about-info"> <span class="time">'+data.faqView[i].replytime.substring(0,16)+'</span> <a href="faq3.html?p='+data.faqView[i].parentId+'&q='+data.faqView[i].faqId+'" target="_blank" class="reply-num">'+data.faqView[i].replyNumber+'个回复</a> </div></div> <div class="item-btm clearfix"> <ul class="l left-info"> <li class="hd-pic"> <a class="publisher-name" href="personal2.html?u='+data.faqView[i].userId+'" target="_blank">'+data.faqView[i].username+'</a> </li> </ul> <div class="r right-info"> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].scanNumber+'浏览</em> </div> <div class="favorite l"> <i class="icon sns-thumb-up-outline"></i><em> '+data.faqView[i].collectionNumber+'收藏</em> </div> <div class=" l"> <i class="icon sns-comment"></i><em> '+data.faqView[i].commentNumber+'评论</em> </div> </div> </div> </div>';
									var duo="";
									var addnumber = eval(number)+eval(i);
									for(var j=0;j<2;j++){
										duo = duo + document.getElementById("zhao2_article-main3").getElementsByClassName("zhao")[addnumber].getElementsByTagName("p")[j].outerHTML;
									}
									document.getElementById("zhao2_article-main3").getElementsByClassName("zhao")[addnumber].innerHTML=duo;
								}
							}
							if(document.getElementById("getMoreComment")==null){
								if(data.faqView[0].isMore=="1"){
									var html = document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML;
									document.getElementById("zhao2_article-main3").getElementsByClassName("articles-list")[0].innerHTML=html+'<p class="js-noreload dynamicLoad js-dynamicLoadwrap" id="getMoreComment"> <span class="js-dynamicLoad " onclick="getMoreCommentFaq()">点击显示更多</span> </p>';
								}
							}
						}
					}
				})
			}