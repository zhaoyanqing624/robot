
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>智能小朵-问题中心</title>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/reset.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util.css" />
    <link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/header.css">
	<link rel="stylesheet" type="text/css" href="css/body.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="new/front/style/utilDetails.css" />
    <link href="zhao/lunbo/css/jquery.onebyone-min.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="js/header.js"></script>
    <script type="text/javascript" src="js/lnv_frontMonitor.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="js/modernizr.custom.79639.js"></script>
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
		                </div>
		             </#if>
		            </ul> 
                </div>
            </div>
        </div>
       	<div class="headContent">
    		<div class="headTop clearfix">
	        	<a href="" class="logoCon">
	            	<img src="images/logo.jpg" class="logo">
	            	<span>IT运维智能化服务一体化平台——问题中心</span>
	        	</a>
    		</div>
		</div>
    </div>
    <div class="mainContent">
        <div class="contentWra clearfix">
            <div class="leftMainWrapper ">
				<section id="mainL">
				<div id="quickAsk">
					<p>有问题，来问吧~</p>
					<form onsubmit="return false;">
						<input type="text" placeholder="输入问题，如“如何升级至Windows 10？”">
						<button>提交</button>
					</form>
					<div class="wrapper-demo">
						<div id="dd" class="wrapper-dropdown-3" tabindex="1">
							<span id="dd_">${typename}</span>
							<ul class="dropdown">
								<li><a onclick="getType()"><i class="icon-envelope icon-large"></i>全部</a></li>
								<li><a onclick="getType()"><i class="icon-truck icon-large"></i>已解决</a></li>
								<li><a onclick="getType()"><i class="icon-plane icon-large"></i>待回答</a></li>
							</ul>
						</div>
					​</div>
				</div>

				<!-- 问题查询列表 -->
				<ul id="searchResult">
					<#list communityViews as communityViews>
					<li id="${communityViews.communityId}_">
						<article >
							<div class="tag">
								<ul>
									<li>${communityViews.classifyName}</li>
									<#if communityViews.userId ??>
									<li class="type">回答</li>
									<#else>
									<li class="type">问题</li>
									</#if>
								</ul>
								<div class="time"><p>${communityViews.time}</p></div>
							</div>
							<div class="title">
								<h2><a href="question2.html?q=${communityViews.communityId}">${communityViews.communityTitle}</a></h2>
							</div>
							<#if communityViews.userId ??>
							<div class="description">
								<div class="answerer" data-id="270369">
									<img class="answerImg" src="${communityViews.userImage}">
									<div>
										<a href="">
											<span class="user_name">${communityViews.userName}</span>
												&nbsp;&nbsp;<span>${communityViews.signature}</span>
										</a>
									</div>
									<div>
										<img src="images/bluepoint.png" class="bluepoint">贡献${communityViews.totalCommunityNumber}个回答，获得${communityViews.totalLikesNumber}个赞
									</div>
								</div>
								<div class="detail">
									<#if communityViews.answer?length gt 100>
									<div class="detailP">
										${communityViews.answer[0..100]}......
										<span class="readMore">查看更多</span>
									</div>
									<#else>
									<div class="detailP">${communityViews.answer}
									</div>
									</#if>
								</div>
								<div class="fullDetail hidden">
									<p>${communityViews.answer}</p>
								</div>
							</div>
							<div class="options">
								<ul>
									<li class="special">
										<#if communityViews.isLike="0">
										<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">${communityViews.likesNumber}</span></a>
										<#else>
										<a data-fun="toVote" class="unVoted" onclick="getAgreeAnswer()"><span class="status">已点赞</span>  |  <span class="number">${communityViews.likesNumber}</span></a>
										</#if>
									</li>
									<li><a data-fun="toComment" class="fm_ele" fm-type="button" fm-name="answer_comment" fm-operation="click" fm-zoon="option_area"><span>评论 </span><span class="number">${communityViews.communityNumber}</span></a></li>
									<span class="fold"><a data-fun="fold"><span class="foldicon"></span>收起</a></span>
								</ul>
							</div>
							<#else>
							<div class="description">
								<div class="detail">
									<#if communityViews.communityQuestion?length gt 100>
									<div class="detailP">
										${communityViews.communityQuestion[0..100]}......
										<span class="readMore">查看更多</span>
									</div>
									<#else>
									<div class="detailP">${communityViews.communityQuestion}
									</div>
									</#if>
								</div>
								<div class="fullDetail hidden">
									<p>${communityViews.communityQuestion}</p>
								</div>
							</div>
							<div class="options">
								<ul>
									<li class="special"><a onclick="create_edit()" class="unFocused fm_ele" ><span class="status" id="${communityViews.communityId}">回答</span></a></li>
									<li><a data-fun="toComment" class="fm_ele" fm-type="button" fm-name="answer_comment" fm-operation="click" fm-zoon="option_area"><span>评论 </span><span class="number">${communityViews.communityNumber}</span></a></li>
									<span class="fold"><a data-fun="fold"><span class="foldicon"></span>收起</a></span>
								</ul>
							</div>
							</#if>
							
						</article>
					</li>
					</#list>
				</ul>
				
				<div id="loadStatus">
					<div id="loading" class="">
						<span>点击查看更多</span>
						<div class="spinner">
  							<div class="bounce1"></div>
  							<div class="bounce2"></div>
  							<div class="bounce3"></div>
						</div>
					</div>
					<div id="loadMore" class="hidden">
						<button>加载更多</button>
					</div>
					<div id="noMore" class="hidden">
						<span>没有更多</span>
					</div>
				</div>
			</section>
                
            </div>
            
            <div class="rightBarWrapper">
            	<div id="mainR">
            	<div style="height:85px;"><button class="fm_ele" id="ask" fm-type="button" fm-name="button_ask" fm-operation="click" fm-zoon="header_area" onclick="questionForm();">提新问题</button></div>
				<div><a href="http://iknow.lenovo.com/"><img id="wenba" src="images/iknow.png"></a></div>
				<div id="topic">
					<div id="topicTitle"><img src="images/topic.png" ><a onclick="getClassify()">话题</a></div>
					<!-- 问题标签列表 -->
					<ul id="tagFilter">
					<#list classifyList as classifyList>
						<li><a class="fm_ele" onclick="getClassify()">${classifyList.FAQCLASSIFYNAME}</a></li>
					</#list>
					</ul>
				</div>
			</div>
        	</div>
        </div>
    </div>
    
    <div id="foot" class="footer">
    	<p style="color: #ffffff;text-align: center;">© 西安交通大学社会智能与复杂数据处理实验室  2017.</p>
    </div>
    <div id="questionForm" class="popup" style="display: none;">		
    	<div class="fade"></div>			
    	<div id="question-f">				
    		<div id="question-s" class="">					
    			<a href="javascript:" id="close"></a>
				<h1 id="titH">添加问题</h1>					
				<textarea rows="1" name="question_content" id="title" placeholder="请输入您的问题，如：IPhone 6指纹识别如何破解？" maxlength="100"></textarea>
				<p class="askTitleTip" style="display: none;"></p>	
				<ul id="similarAsk" style="display: block;"></ul>					
				<h1 id="desH">添加问题的详细描述</h1>					
				<script id="editor" type="text/plain" style="width:650px;height:300px;margin-left:34px;margin-top:20px;"></script>
			<button id="toStep2">下一步</button>				</div>				<div id="tag-s" class="hidden">					<a href="javascript:" id="close"></a>					<h1 id="tagH">添加标签</h1>					<h2>可选</h2>					<p id="titP">“asfdsdafa”</p>					<ul id="systemTag">
			<#list classifyList as classifyList>
			<li>
				<input type="checkbox" name="category_id" value="${classifyList.FAQCLASSIFYID}">
				<label>${classifyList.FAQCLASSIFYNAME}</label>
			</li>
			</#list>
		</ul>					
		<ul id="customTag"></ul>					
		<div></div>					
		<button id="toStep1">返回</button>				
		<button id="submit"  onclick="saveCommunityQuestion()">提交</button>				</div>			</div>		</div>
	<div id="lasturl" style="display:none"></div>
    	<script type="text/javascript" src="new/front/js/util.js"></script>
		<script>
			function questionForm(){
				document.getElementById("questionForm").style.display="block";
			}
		</script>
<script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
    function isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData () {
        alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
    }

    function clearLocalData () {
        UE.getEditor('editor').execCommand( "clearlocaldata" );
        alert("已清空草稿箱")
    }
</script> 
		<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
		<script>
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
			
			

</script>
		<script type="text/javascript">
			function DropDown(el) {
				this.dd = el;
				this.placeholder = this.dd.children('span');
				this.opts = this.dd.find('ul.dropdown > li');
				this.val = '';
				this.index = -1;
				this.initEvents();
			}
			DropDown.prototype = {
				initEvents : function() {
					var obj = this;
					obj.dd.on('click', function(event){
						$(this).toggleClass('active');
						return false;
					});
					obj.opts.on('click',function(){
						var opt = $(this);
						obj.val = opt.text();
						obj.index = opt.index();
						obj.placeholder.text(obj.val);
					});
				},
				getValue : function() {
					return this.val;
				},
				getIndex : function() {
					return this.index;
				}
			}
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
										document.getElementById("searchResult").innerHTML = htmls+ '<li id="'+data.communityViews[i].communityId+'_'+'"><article ><div class="tag"><ul><li>'+data.communityViews[i].classifyName+'</li><li class="type">回答</li></ul><div class="time"><p>'+data.communityViews[i].time+'</p></div></div><div class="title"><h2><a href="question2.html?q='+data.communityViews[i].communityId+'">'+data.communityViews[i].communityTitle+'</a></h2></div><div class="description"><div class="detail"><div class="detailP">'+data.communityViews[i].answer+'</div></div><div class="fullDetail hidden"><p>'+data.communityViews[i].answer+'</p></div></div><div class="options"><ul><li class="special"><a  class="unVoted" onclick="getAgreeAnswer()"><span class="status">点赞</span>  |  <span class="number">'+data.communityViews[i].likesNumber+'</span></a></li><li><a data-fun="toComment" class="fm_ele" fm-type="button" fm-name="answer_comment" fm-operation="click" fm-zoon="option_area"><span>评论 </span><span class="number">'+data.communityViews[i].communityNumber+'</span></a></li><span class="fold"><a data-fun="fold"><span class="foldicon"></span>收起</a></span></ul></div></article></li>';
									}
								}else{
									if(data.communityViews[i].communityQuestion>100){
										var htmls = document.getElementById("searchResult").innerHTML;
										document.getElementById("searchResult").innerHTML = htmls+ '<li id="'+data.communityViews[i].communityId+'_'+'"><article ><div class="tag"><ul><li>'+data.communityViews[i].classifyName+'</li><li class="type">回答</li></ul><div class="time"><p>'+data.communityViews[i].time+'</p></div></div><div class="title"><h2><a href="question2.html?q='+data.communityViews[i].communityId+'">'+data.communityViews[i].communityTitle+'</a></h2></div><div class="description"><div class="detail"><div class="detailP">'+data.communityViews[i].communityQuestion.substr(0,100)+'......'+'<span class="readMore">查看更多</span></div></div><div class="fullDetail hidden"><p>'+data.communityViews[i].communityQuestion+'</p></div></div><div class="options"><ul><li class="special"><a onclick="create_edit(this)" class="unFocused fm_ele" ><span class="status" id="'+data.communityViews[i].communityId+'">回答</span></a></li><li><a data-fun="toComment" class="fm_ele" fm-type="button" fm-name="answer_comment" fm-operation="click" fm-zoon="option_area"><span>评论 </span><span class="number">'+communityViews.communityNumber+'</span></a></li><span class="fold"><a data-fun="fold"><span class="foldicon"></span>收起</a></span></ul></div></article></li>';										
									}else{
										var htmls = document.getElementById("searchResult").innerHTML;
										document.getElementById("searchResult").innerHTML = htmls+ '<li id="'+data.communityViews[i].communityId+'_'+'"><article ><div class="tag"><ul><li>'+data.communityViews[i].classifyName+'</li><li class="type">回答</li></ul><div class="time"><p>'+data.communityViews[i].time+'</p></div></div><div class="title"><h2><a href="question2.html?q='+data.communityViews[i].communityId+'">'+data.communityViews[i].communityTitle+'</a></h2></div><div class="description"><div class="detail"><div class="detailP">'+data.communityViews[i].communityQuestion+'</div></div><div class="fullDetail hidden"><p>'+data.communityViews[i].communityQuestion+'</p></div></div><div class="options"><ul><li class="special"><a onclick="create_edit(this)" class="unFocused fm_ele" ><span class="status" id="'+data.communityViews[i].communityId+'">回答</span></a></li><li><a data-fun="toComment" class="fm_ele" fm-type="button" fm-name="answer_comment" fm-operation="click" fm-zoon="option_area"><span>评论 </span><span class="number">'+data.communityViews[i].communityNumber+'</span></a></li><span class="fold"><a data-fun="fold"><span class="foldicon"></span>收起</a></span></ul></div></article></li>';	
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
		</script>
		<div class="success" id="success" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/true.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">提交成功</h2></div></div>
		<div class="success" id="chongfu" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/cuo.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">切勿重复提交</h2></div></div>

</body>
</html>
