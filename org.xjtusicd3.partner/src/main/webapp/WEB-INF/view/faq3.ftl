
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Windows 10系统下如何进行压缩卷</title>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <link rel="stylesheet" type="text/css" href="new/front/style/reset.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/utilDetails.css" />
    <link href="zhao/lunbo/css/jquery.onebyone-min.css" rel="stylesheet" />
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <script type="text/javascript" src="zhao/lunbo/js/jquery.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
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
       	<div class="headContent">
    		<div class="headTop clearfix">
		    	<div class="queryBox">
		            <input type="text" class="text" value="输入关键字" onfocus="if(this.value=='输入关键字')this.value='';" onblur="if(this.value=='')this.value='输入关键字';" id="keyWordText" onkeydown="if (event.keyCode == 13) {queryKnowledgeByKey();}" autocomplete="off">
		            <a href="javascript:void(0);" class="queryBtn" onclick="queryKnowledgeByKey()"></a>
		        </div>
	        	<a href="" class="logoCon">
	            	<img src="images/logo.jpg" class="logo">
	            	<span>IT运维智能化服务一体化平台——知识库</span>
	        	</a>
    		</div>
		</div>
    </div>
    <#list faq3Views as faq3Views>
    <div class="mainContent">
        <div class="contentWra clearfix">
            <div class="leftMainWrapper ">
                <div class="nowPositionBox clearfix">
                    <a href="/org.xjtusicd3.partner/faq.html"><span class="allType">所有分类</span></a>
                    <span class="arrow">&gt;</span>
                    <#list classify as classify>
                    <a href="/org.xjtusicd3.partner/faq1.html?p=${classify.FAQCLASSIFYID}" id="firstNavStep"><span id="fristProductionName">${classify.FAQCLASSIFYNAME}</span></a>
                    </#list>
                    <span class="arrow">&gt;</span>
                    <#list classify2 as classify2>
                    <a href="/org.xjtusicd3.partner/faq2.html?c=${classify2.FAQCLASSIFYID}" id="secondNavStep"><span class="secondStep" id="subProductionName">${classify2.FAQCLASSIFYNAME}</span></a>
                    </#list>
                    <span class="arrow twoFlag">&gt;</span>
                    <span class="titleStep">${faq3Views.faqTitle}</span>
                </div>
                
                <div class="knowledgeDetail">
                    <div id="detailTplWrapper">
                        <span class="title">${faq3Views.faqTitle}</span>
                        <span class=""></span>
                        <p class="createInfo clearfix">
                        <#list faq3Views.uList as ulist>
                            <img src="${ulist.userImage}">
                            <span class="username">${ulist.userName}</span>
                        </#list>
                        </p>
                        <#list faq3Views.faqAnswers as flist>
                        <div class="content">${flist.faqContent}</div>
                        </#list>
                        <div id="affixBtn" class="hidden">
                            <a href="" style="color:blue;">点击下载附件</a>
                        </div>

                        <input type="hidden" id="version_id" value="2"></div>
                    <div class="qcodeWra ar hidden">
                        <img src="/front/images/qcode.jpg" width="130" height="130">
                    </div>
                    <div class="clearfix">
                        <div class="shareBox">
                            <span class="share">收藏：</span>
                            <a href="javascript:void(0);" id="favoriteHeart" class="share heart" onclick="favorite()"></a>
                            <span class="share">|</span>
                            <!-- JiaThis Button BEGIN -->
                            <!--<div class="jiathis_style_32x32 fl">
                                <a class="jiathis_button_qzone"></a>
                                <a class="jiathis_button_tsina"></a>
                                <a class="jiathis_button_weixin"></a>
                            </div>
                            <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>-->
                            <!-- JiaThis Button END -->
                            <div class="social-share fl share-component" data-sites="weibo,qzone"><a class="social-share-icon icon-weibo" href="http://service.weibo.com/share/share.php?url=http%3A%2F%2Fiknow.lenovo.com%2Fdetail%2Fdc_133316.html&amp;title=%E8%81%94%E6%83%B3%E4%B8%AD%E5%9B%BD(Lenovo%20China)%E8%81%94%E6%83%B3%E7%9F%A5%E8%AF%86%E5%BA%93&amp;pic=http%3A%2F%2Fiknow.lenovo.com%2Ffront%2Fimages%2Fqcode.jpg&amp;appkey=" target="_blank"></a><a class="social-share-icon icon-qzone" href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=http%3A%2F%2Fiknow.lenovo.com%2Fdetail%2Fdc_133316.html&amp;title=%E8%81%94%E6%83%B3%E4%B8%AD%E5%9B%BD(Lenovo%20China)%E8%81%94%E6%83%B3%E7%9F%A5%E8%AF%86%E5%BA%93&amp;desc=%E8%81%94%E6%83%B3%E4%B8%AD%E5%9B%BD(Lenovo%20China)%E8%81%94%E6%83%B3%E7%9F%A5%E8%AF%86%E5%BA%93&amp;summary=%E8%81%94%E6%83%B3%E4%B8%AD%E5%9B%BD(Lenovo%20China)%E8%81%94%E6%83%B3%E7%9F%A5%E8%AF%86%E5%BA%93&amp;site=%E8%81%94%E6%83%B3%E4%B8%AD%E5%9B%BD(Lenovo%20China)%E8%81%94%E6%83%B3%E7%9F%A5%E8%AF%86%E5%BA%93" target="_blank"></a></div>
                        </div>
                        <div class="btnWrapper">
                            <!--<span class="addpraise hidden">+1</span>
                            <a href="javascript:void(0);" class="praise" onclick="praise()">赞一个</a>-->

                            <span class="score-icon fl" id="scoreList">
                                <i class="score-star-big"></i>
                                <i class="score-star-big"></i>
                                <i class="score-star-big"></i>
                                <i class="score-star-big"></i>
                                <i class="score-star-big"></i>
                            </span>
                            <span class="fl score-text"></span>
                            <span class="fl" id="praiseEditTplWrapper"><a href="javascript:void(0);" id="editBtn" class="edit hidden" onclick="goEditKnowledge(11717)">我要编辑</a></span></div>
                    </div>
                    <div>知识库提供的技术方案或与您产品的实际情况有所差异，您需在完整阅读方案并知晓其提示风险的情况下谨慎操作，避免造成任何损失。</div>
                </div>
                <div class="comment">
                    <h3>发表评论</h3>
                    <textarea class="textarea" id="content" placeholder="点击此处编辑评论" style="display:block" onclick="showeditor()"></textarea>
                    <div id="answer-ueditor" class="edui-default" style="width:645px;font-size:14px;display:none;margin-left: 32px;">
						<script id="editor" name="content" type="text/plain"></script>
					</div>
                    <div class="clearfix commentScoreBtn"  style="display:none">
                        <input type="button" value="发表评论" class="publishCommentBtn" onclick="comment()">
                    </div>
                    <h3>文章评论</h3>
                    <#list comment as comment>
                    <ul class="commentList" id="${comment.commentId}" >
                    	<li class="commentLiContent">
                    		<#list comment.userViews as user>
                    		<div class="userContent clearfix">
                    			<span class="userPic"><img src="${user.userImage}"></span>
                    			<span class="username">${user.userName}</span><span class="line">|</span><span class="time">${comment.commentTime}</span>
                    		</div>
                    		<div class="clearfix content" >
	                    		<a href="javascript:void(0);" class="commentReplay" onclick="openreply()">回复(${comment.commentNumber})</a>
	                    		<p class="text" >${comment.commentContent}</p>
                    		</div>
                    		<ul class="subCommentList" style="display:none">
                    			<#list comment.replyViews as reply>
                    			<li id="${reply.commentId}" class="_commentlist" onmouseover="showdelete(event,this)" onmouseout="hiddendelete(event,this)">
                    				<div class="userContent clearfix" id="${reply.toUserName}">
                    					<#if reply.toUserName ??>
                    						<span class="username">
                    							<span class="zhao">${reply.userName}</span> 回复：${reply.toUserName}
	                    					</span>
                    					<#else>
	                    					<span class="username">
	                    						<span class="zhao">${reply.userName}</span> 评论
	                    					</span>
                    					</#if>
                    					<span class="line">|</span><span class="time">${reply.time}</span>
                    				</div>
                    				<#if userName ?? && reply.userName==userName>
                    				<div class="clearfix content" onclick="replyOther()" style="cursor:pointer">
                    					<a href="javascript:void(0);" class="commentReplay" onclick="deleteComment()" style="display:none"><i class="fa fa-trash-o"></i></a>
                    					<p class="text">${reply.comment}</p>
                    				</div>
                    				<#else>
                    				<div class="clearfix content">
                    					<a  class="commentReplay"  style="display:none"></a>
                    					<p class="text">${reply.comment}</p>
                    				</div>
                    				</#if>
                    			</li>
                    			</#list>
                    			<#if comment.commentNumber gt 5>
                    			<p class="ac" id="querymorelink2"><a href="javascript:void(0);" onclick="querymorereply()">更多回复</a></p>
                    			</#if>
                    		</ul>
                    		</#list>
                    		<div class="commentReplayUser" id="">回复：<span class="username_span" style="color:#F00" ></span>:<span class="content_span" style="color:#F00"></span></div>
                    		<textarea class="commentReplayText" id="replycontenttext" style="display:none"></textarea>
                    		<p class="ac" style="display:none"><input type="button" value="发表" class="replayBtn" onclick="replycomment()"></p>
                    	</li>
                    </ul>
                    </#list>
                    <#if commentNumber gt 5>
                    <p class="ac" id="querymorelink" class="display:block"><a href="javascript:void(0);" onclick="querymorecomment()">查看更多...</a></p>
                    </#if>
                </div>
            </div>
            
            <div class="rightBarWrapper knowledgeCount">
            	<div class="barBox" id="countTplWrapper"><h3 class="box-title">知识统计</h3>
                    <ul class="countList">
                        <li><label>浏览次数：</label><span>${faq3Views.faqScan}次</span></li>
                        <li><label>最近更新：</label><span>${faq3Views.faqModifytime}</span></li>
                        <#list faq3Views.uList as ulist>
                        <li><label>创建者：</label><a href="/foreignCenter/10068673852.html" class="creatUser">${ulist.userName}</a><a href="javascript:void(0);" class="attention" id="attentionLink" onclick="attention('10068673852')">+关注</a><a href="javascript:void(0);" class="attention overAttention hidden" id="overAttentionLink">+已关注</a></li>
                        </#list>
                        <li><label>编辑次数：</label><span>${faq3Views.faqWritetime}次</span></li>
                        <li>
                            <label>评分：</label>
                            <span class="scoreResult" id="showScore">
                                <i class="score-star current"></i>
                                <i class="score-star current"></i>
                                <i class="score-star current"></i>
                                <i class="score-star current"></i>
                                <i class="score-star"></i>
                            </span>
                        </li>
                    </ul>
                </div>
				
				<div style="margin-bottom: 20px;">
					<a href="" onclick="clickadd()"><img src="images/erweima.PNG" alt=""></a>
				</div>        		
        		<div class="barBox relateKnowledge">
                    <h3 class="box-title">相关知识</h3>
                    <ul class="relateKnowledgeList" id="relateTplWrapper">
                        <li><a href="/detail/dc_077225.html" target="_blank" title="Windows XP和Windows 8双系统，如何删除Windows 8保留Windows XP">Windows XP和Windows 8双系统，如何删除Windows 8保留Windows XP</a></li>
                        
                        <li><a href="/detail/dc_102795.html" target="_blank" title="预装Windows 8的机型更换Windows 7及Windows XP如何设置">预装Windows 8的机型更换Windows 7及Windows XP如何设置</a></li>
                        
                        <li><a href="/detail/dc_130218.html" target="_blank" title="Windows 8.1升级Windows 10后，回调至Windows 8.1的操作指导">Windows 8.1升级Windows 10后，回调至Windows 8.1的操作指导</a></li>
                        </ul>
                </div>
        	</div>
    </div>
    </#list>
    <div class="dialoge">
        <a href="http://365.lenovo.com.cn/?from=lenser03" target="_blank">如果本知识没解决您的问题，可以选择<label style="color:#4e9eeb;border-bottom:1px solid #4e9eeb;cursor:pointer">远程专家服务</label></a>
        <span class="closeDia">关闭</span>
    </div>
    <div id="foot" class="footer">
    	<p style="color: #ffffff;text-align: center;">© 西安交通大学社会智能与复杂数据处理实验室  2017.</p>
    </div>
    <script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor',{
    	initialFrameHeight:200,
    	initialFrameWidth:700
    });
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
	<script type="text/javascript">
		function comment(){
			var faqusername = document.getElementsByClassName("username")[0].innerHTML;
			var faqtitle = document.getElementById("detailTplWrapper").getElementsByClassName("title")[0].innerHTML;
			var comment = UE.getEditor('editor').getContent();
			if(comment==""){
				setTimeout("location.reload()",1000)
				document.getElementById('null').style.display='block';
				setTimeout("codefans()",3000);
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
			if(event.target.parentNode.parentNode.getElementsByClassName('subCommentList')[0].style.display=="none"){
				commentid = event.target.parentNode.parentNode.parentNode.id;
				document.getElementById(commentid).getElementsByClassName('subCommentList')[0].style.display="block";
				document.getElementById(commentid).getElementsByClassName('commentReplayText')[0].style.display="block";
				document.getElementById(commentid).getElementsByClassName('ac')[0].style.display="block";
				document.getElementById(commentid).getElementsByClassName('commentReplayUser')[0].style.display="block";
				username = event.target.parentNode.parentNode.getElementsByClassName('username')[0].innerHTML;
				document.getElementById(commentid).getElementsByClassName('username_span')[0].innerHTML=username;
				document.getElementById(commentid).getElementsByClassName('commentReplayUser')[0].id=commentid+"_"
			}else{
				commentid = event.target.parentNode.parentNode.parentNode.id;
				document.getElementById(commentid).getElementsByClassName('subCommentList')[0].style.display="none";
				document.getElementById(commentid).getElementsByClassName('commentReplayText')[0].style.display="none";
				document.getElementById(commentid).getElementsByClassName('ac')[0].style.display="none";
				document.getElementById(commentid).getElementsByClassName('commentReplayUser')[0].style.display="none";
			}
		}
		function replycomment(){
			var questionId = document.URL.split("=")[1];
			var comment = event.target.parentNode.parentNode.getElementsByClassName("commentReplayText")[0].value;
			var commentId = event.target.parentNode.parentNode.parentNode.id;
			var duo = "";
			if(event.target.parentNode.parentNode.getElementsByClassName("content_span")[0].innerHTML==""){
				duo="0";
			}else{
				duo="1";
			}
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
		function codefans(){
			var box=document.getElementById("success");
			box.style.display="none"; 
		}
		function codefans2(){
			var box=document.getElementById("chongfu");
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
		    if(checkHover(e,obj)){
			event.target.parentNode.getElementsByClassName("commentReplay")[0].style.display="block";
		  }
		}
    	function hiddendelete(e, obj){
    		if(checkHover(e,obj)){
			event.target.parentNode.getElementsByClassName("commentReplay")[0].style.display="none";
		  }
    	}
    	//删除自己的评论
    	function deleteComment(){
			event.target.parentNode.parentNode.parentNode.style.display="none";
		}
		
		//特定回复某人的评论
		function replyOther(){
			var username = document.getElementById("zhao_hidden").innerHTML;
			var content = event.target.parentNode.getElementsByClassName("text")[0].innerHTML;
			if(content.length<10){
				content = content;
			}else{
				content = content.substr(0,10)+"...";
			}
			var tousername = event.target.parentNode.parentNode.getElementsByClassName("zhao")[0].innerHTML;
			var commentId = event.target.parentNode.parentNode.id;
			if(username!=tousername){
				event.target.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("username_span")[0].innerHTML=tousername;
				event.target.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("content_span")[0].innerHTML=content;
				event.target.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("commentReplayUser")[0].id=commentId+"_";
			}
		}
		//获取更多评论
		function querymorecomment(){
			html = event.target.parentNode.parentNode.parentNode.getElementsByClassName("comment")[0].innerHTML;
			startnumber = event.target.parentNode.parentNode.parentNode.getElementsByClassName("comment")[0].getElementsByClassName("commentList").length;
			var questionId = document.URL.split("=")[1];
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
						document.getElementById("querymorelink").remove();
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
			var commentid = event.target.parentNode.parentNode.parentNode.parentNode.id;
			startnumber = event.target.parentNode.parentNode.parentNode.parentNode.getElementsByClassName("_commentlist").length;
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
							for(var i in data){
								var htmls = document.getElementsByClassName("subCommentList")[0].innerHTML;
								document.getElementsByClassName("subCommentList")[0].innerHTML = htmls+ '<ul class="commentList" id="'+data.commentList[i].commentId+'"><li class="commentLiContent"><div class="userContent clearfix"><span class="userPic"><img src="'+data.commentList[i].userViews[0].userImage+'"></span><span class="username">'+data.commentList[i].userViews[0].userName+'</span><span class="line">|</span><span class="time">'+data.commentList[i].commentTime+'</span></div><div class="clearfix content"><a href="javascript:void(0);" class="commentReplay" onclick="openreply()">回复('+data.commentList[i].commentNumber+')</a><p class="text"></p><p>'+data.commentList[i].commentContent+'</p><p></p></div><ul class="subCommentList" style="display:none"></ul><div class="commentReplayUser" id="">回复：<span class="username_span" style="color:#F00"></span>:<span class="content_span" style="color:#F00"></span></div><textarea class="commentReplayText" id="replycontenttext" style="display:none"></textarea><p class="ac" style="display:none"><input type="button" value="发表" class="replayBtn" onclick="replycomment()"></p></li></ul>';
							}
						}
					}
				}
			})
		}
	</script> 
		<div class="success" id="success" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/true.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">评论成功</h2></div></div>
		<div class="success" id="chongfu" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/cuo.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">切勿重复提交</h2></div></div>
		<div class="success" id="null" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;display:none"><div style="margin-top:30px; margin-bottom:30px;"><img src="images/cuo.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top">内容不能为空</h2></div></div>
		<div id="zhao_hidden" style="display:none">${userName}</div>
</body>
</html>
