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
    <script src="js/ajax-pushlet-client.js"></script>
    <script type="text/javascript">
    	PL.parameters.push('uid');
    	PL.parameters.push('${uid}');  
		PL._init();     
		PL.joinListen('/mipc/he');  
		function onData(event) {
			var result = decodeURIComponent(event.get("notice"));
			var jsonresult = strToJson(result);
			function strToJson(str){ 
				return JSON.parse(str); 
			}
			for(var i in jsonresult){
				//判断是否存在
				if(!!document.getElementById(jsonresult[i].noticeId)){
				
				}else{
					var oDiv = document.createElement('div');
					oDiv.id = jsonresult[i].noticeId;
					document.getElementById("second").appendChild(oDiv);
					var from = jsonresult[i].value.split("_")[0];
					var how = jsonresult[i].value.split("_")[1];
					var name =jsonresult[i].name.replace(/<.*?>/ig,"");
					var notice = jsonresult[i].notice.replace(/<.*?>/ig,""); 
					if(name.length<20){
						name = name;
					}else{
						name = name.substr(0,20)+"...";
					}	
					if(notice.length<20){
						notice = notice;
					}else{
						notice = notice.substr(0,20)+"...";					
					}
					var time = jsonresult[i].time;
					time = time.split('');
					time.splice(10,1,' ');
					time = time.join('');
					if(jsonresult[i].value=="问吧_有了新的评论"){
						document.getElementById(jsonresult[i].noticeId).outerHTML = '<div id='+jsonresult[i].noticeId+' class="notice" onmouseover="showdelete(event,this)" onmouseout="hiddendelete(event,this)"></div>';
						document.getElementById(jsonresult[i].noticeId).innerHTML = '<div class="notice-box clearfix"><p class="notice-type " style="color: #0343fb;border: 1px solid #0343fb;background: #9be4ff;">'+from+'</p><div class="notice-show-box"><p class="notice-con ">你的提问：“<a class="notice-question" target="_blank" href="/wenda/detail/338293">'+name+'</a>”有新的评论“<a class="notice-answer" target="_blank" href="/wenda/detail/338293">'+notice+'</a>”</p><h5 class="notice-date">'+time+'</h5></div><div class="del-box clearfix"><a onclick="deletenotice()" class="del-notice" title="删除此通知"><i class="fa fa-trash-o"></i></a></div></div>';
					}else if(jsonresult[i].value=="问吧_有了新的回复"){
						document.getElementById(jsonresult[i].noticeId).outerHTML = '<div id='+jsonresult[i].noticeId+' class="notice" onmouseover="showdelete(event,this)" onmouseout="hiddendelete(event,this)"></div>';
						document.getElementById(jsonresult[i].noticeId).innerHTML = '<div class="notice-box clearfix"><p class="notice-type " style="color: #0343fb;border: 1px solid #0343fb;background: #9be4ff;">'+from+'</p><div class="notice-show-box"><p class="notice-con ">你的评论：“<a class="notice-question" target="_blank" href="/wenda/detail/338293">'+name+'</a>”有新的回复“<a class="notice-answer" target="_blank" href="/wenda/detail/338293">'+notice+'</a>”</p><h5 class="notice-date">'+time+'</h5></div><div class="del-box clearfix"><a onclick="deletenotice()" class="del-notice" title="删除此通知"><i class="fa fa-trash-o"></i></a></div></div>';
					}else if(jsonresult[i].value=="问吧_有了新的回复@"){
						document.getElementById(jsonresult[i].noticeId).outerHTML = '<div id='+jsonresult[i].noticeId+' class="notice" onmouseover="showdelete(event,this)" onmouseout="hiddendelete(event,this)"></div>';
						document.getElementById(jsonresult[i].noticeId).innerHTML = '<div class="notice-box clearfix"><p class="notice-type " style="color: #0343fb;border: 1px solid #0343fb;background: #9be4ff;">'+from+'</p><div class="notice-show-box"><p class="notice-con ">你的回复：“<a class="notice-question" target="_blank" href="/wenda/detail/338293">'+name+'</a>”有新的回复“<a class="notice-answer" target="_blank" href="/wenda/detail/338293">'+notice+'</a>”</p><h5 class="notice-date">'+time+'</h5></div><div class="del-box clearfix"><a onclick="deletenotice()" class="del-notice" title="删除此通知"><i class="fa fa-trash-o"></i></a></div></div>';
					}else if(jsonresult[i].value=="知识库_有了新的评论"){
						document.getElementById(jsonresult[i].noticeId).outerHTML = '<div id='+jsonresult[i].noticeId+' class="notice" onmouseover="showdelete(event,this)" onmouseout="hiddendelete(event,this)"></div>';
						document.getElementById(jsonresult[i].noticeId).innerHTML = '<div class="notice-box clearfix"><p class="notice-type " style="color: #05c953;border: 1px solid #05c953;background: #a9fba8;">'+from+'</p><div class="notice-show-box"><p class="notice-con ">你的知识：“<a class="notice-question" target="_blank" href="/wenda/detail/338293">'+name+'</a>”有新的评论“<a class="notice-answer" target="_blank" href="/wenda/detail/338293">'+notice+'</a>”</p><h5 class="notice-date">'+time+'</h5></div><div class="del-box clearfix"><a onclick="deletenotice()" class="del-notice" title="删除此通知"><i class="fa fa-trash-o"></i></a></div></div>';					
					}else if(jsonresult[i].value=="知识库_有了新的回复"){
						document.getElementById(jsonresult[i].noticeId).outerHTML = '<div id='+jsonresult[i].noticeId+' class="notice" onmouseover="showdelete(event,this)" onmouseout="hiddendelete(event,this)"></div>';
						document.getElementById(jsonresult[i].noticeId).innerHTML = '<div class="notice-box clearfix"><p class="notice-type " style="color: #05c953;border: 1px solid #05c953;background: #a9fba8;">'+from+'</p><div class="notice-show-box"><p class="notice-con ">你的评论：“<a class="notice-question" target="_blank" href="/wenda/detail/338293">'+name+'</a>”有新的回复“<a class="notice-answer" target="_blank" href="/wenda/detail/338293">'+notice+'</a>”</p><h5 class="notice-date">'+time+'</h5></div><div class="del-box clearfix"><a onclick="deletenotice()" class="del-notice" title="删除此通知"><i class="fa fa-trash-o"></i></a></div></div>';					
					}else if(jsonresult[i].value=="知识库_有了新的回复@"){
						document.getElementById(jsonresult[i].noticeId).outerHTML = '<div id='+jsonresult[i].noticeId+' class="notice" onmouseover="showdelete(event,this)" onmouseout="hiddendelete(event,this)"></div>';
						document.getElementById(jsonresult[i].noticeId).innerHTML = '<div class="notice-box clearfix"><p class="notice-type " style="color: #05c953;border: 1px solid #05c953;background: #a9fba8;">'+from+'</p><div class="notice-show-box"><p class="notice-con ">你的回复：“<a class="notice-question" target="_blank" href="faq3.html?p='+jsonresult[i].parentId+'&q='+jsonresult[i].questionId+'" onclick="updatenotice()">'+name+'</a>”有新的回复“<a class="notice-answer" target="_blank" href="faq3.html?n='+jsonresult[i].noticeId+'&p='+jsonresult[i].parentId+'&q='+jsonresult[i].questionId+'">'+notice+'</a>”</p><h5 class="notice-date">'+time+'</h5></div><div class="del-box clearfix"><a onclick="deletenotice()" class="del-notice" title="删除此通知"><i class="fa fa-trash-o"></i></a></div></div>';					
					}
					
				}
			} 
		  
		    // 离开    
		    // PL.leave();    
		}   
	</script> 
</head>

<body>
	<!-- 头部开始 -->
	<div class="header" id="head">      
        <#include "inc/incTop.ftl">
    </div>
	<!-- 头部结束 -->
	
	<!-- 主体开始 -->
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
											<h5 class="notice-date">${secondList.time}</h5>
										</div>
									<#elseif secondList.value=="知识库_有了新的回复">
										<p class="notice-type " style="color: #05c953;border: 1px solid #05c953;background: #a9fba8;">知识库</p>
										<div class="notice-show-box">
											<p class="notice-con ">你的评论：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${secondList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${secondList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
											<h5 class="notice-date">${secondList.time}</h5>
										</div>
									<#elseif secondList.value=="知识库_有了新的回复@">
										<p class="notice-type " style="color: #05c953;border: 1px solid #05c953;background: #a9fba8;">知识库</p>
										<div class="notice-show-box">
											<p class="notice-con ">你的回复：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${secondList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${secondList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
											<h5 class="notice-date">${secondList.time}</h5>
										</div>
									<#elseif secondList.value=="问吧_有了新的评论">
										<p class="notice-type " style="color: #0343fb;border: 1px solid #0343fb;background: #9be4ff;">问吧</p>
										<div class="notice-show-box">
											<p class="notice-con ">你的提问：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${secondList.name?replace('<(.|\n)+?>','','r')}</a>”有新的评论“<a class="notice-answer" target="_blank" href="">${secondList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
											<h5 class="notice-date">${secondList.time}</h5>
										</div>
									<#elseif secondList.value=="问吧_有了新的回复">
										<p class="notice-type " style="color: #0343fb;border: 1px solid #0343fb;background: #9be4ff;">问吧</p>
										<div class="notice-show-box">
											<p class="notice-con ">你的评论：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${secondList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${secondList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
											<h5 class="notice-date">${secondList.time}</h5>
										</div>
									<#elseif secondList.value=="问吧_有了新的回复@">
										<p class="notice-type " style="color: #0343fb;border: 1px solid #0343fb;background: #9be4ff;">问吧</p>
										<div class="notice-show-box">
											<p class="notice-con ">你的回复：“<a class="notice-question" target="_blank" href="" onclick="updatenotice()">${secondList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${secondList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
											<h5 class="notice-date">${secondList.time}</h5>
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
											<p class="notice-con pass ">你的知识：“<a class="notice-question" target="_blank" href="faq3.html?q=${thirdList.questionId}" onclick="updatenotice()">${thirdList.name?replace('<(.|\n)+?>','','r')}</a>”有新的评论“<a class="notice-answer" target="_blank" href="">${thirdList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
											<h5 class="notice-date">${thirdList.time}</h5>
										</div>
									<#elseif thirdList.value=="知识库_有了新的回复">
										<p class="notice-type  already-read" >知识库</p>
										<div class="notice-show-box">
											<p class="notice-con pass ">你的评论：“<a class="notice-question" target="_blank" href="faq3.html?p=${thirdList.parentId}&q=${thirdList.questionId}" onclick="updatenotice()">${thirdList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${thirdList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
											<h5 class="notice-date">${thirdList.time}</h5>
										</div>
									<#elseif thirdList.value=="知识库_有了新的回复@">
										<p class="notice-type  already-read" >知识库</p>
										<div class="notice-show-box">
											<p class="notice-con pass ">你的回复：“<a class="notice-question" target="_blank" href="faq3.html?n=${thirdList.noticeId}&p=${thirdList.parentId}&q=${thirdList.questionId}" onclick="updatenotice()">${thirdList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${thirdList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
											<h5 class="notice-date">${thirdList.time}</h5>
										</div>
									<#elseif thirdList.value=="问吧_有了新的评论">
										<p class="notice-type  already-read" >问吧</p>
										<div class="notice-show-box">
											<p class="notice-con pass ">你的提问：“<a class="notice-question" target="_blank" href="question2.html?q=${thirdList.questionId}" onclick="updatenotice()">${thirdList.name?replace('<(.|\n)+?>','','r')}</a>”有新的评论“<a class="notice-answer" target="_blank" href="">${thirdList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
											<h5 class="notice-date">${thirdList.time}</h5>
										</div>
									<#elseif thirdList.value=="问吧_有了新的回复">
										<p class="notice-type  already-read" >问吧</p>
										<div class="notice-show-box">
											<p class="notice-con pass ">你的评论：“<a class="notice-question" target="_blank" href="question2.html?p=${thirdList.parentId}&q=${thirdList.questionId}" onclick="updatenotice()">${thirdList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${thirdList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
											<h5 class="notice-date">${thirdList.time}</h5>
										</div>
									<#elseif thirdList.value=="问吧_有了新的回复@">
										<p class="notice-type  already-read" >问吧</p>
										<div class="notice-show-box">
											<p class="notice-con pass ">你的回复：“<a class="notice-question" target="_blank" href="question2.html?n=${thirdList.noticeId}&p=${thirdList.parentId}&q=${thirdList.questionId}" onclick="updatenotice()">${thirdList.name?replace('<(.|\n)+?>','','r')}</a>”有新的回复“<a class="notice-answer" target="_blank" href="">${thirdList.notice?replace('<(.|\n)+?>','','r')}</a>”</p>
											<h5 class="notice-date">${thirdList.time}</h5>
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
				
				<div id="bg_tongzhi" class="setup-coverLayer" style="display:none"></div>
			</div>
		</div>
	</section>    
	<!-- 主体结束 -->
		
    <!-- 底部开始 -->
	<#include "/inc/incFoot.ftl">
	<!-- 底部结束 -->
	
    <script type="text/javascript" src="new/front/js/util.js"></script>
    <script type="text/javascript" src="js/view/notice.js"></script>
</body>
</html>
