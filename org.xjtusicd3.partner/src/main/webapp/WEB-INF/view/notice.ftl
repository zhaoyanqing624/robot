
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
    
        	<div note-id="840249" class="notice">
        <div class="notice-box clearfix">
                            <p class="notice-type ">系统</p>
            
        
            <div class="notice-show-box">
                <p class="notice-con ">
                    您的电脑有<a target="_blank" href="/wenda/detail/344192">48个软件</a>可以更新
                </p>
                <h5 class="notice-date">11:47:09</h5>
            </div>
            <div class="del-box clearfix">
                <a href="javascript:void(0)" class="del-notice" title="删除此通知">
                    <i class="icon icon-del"></i>
                </a>
            </div>
        </div>
	</div>
    	<div note-id="815382" class="notice">
        <div class="notice-box clearfix">
                            <p class="notice-type " style="    color: #14191e;
    border: 1px solid #14191e;
    background: #00aeef;">问吧</p>
            
        
            <div class="notice-show-box">
                <p class="notice-con ">
                    你的提问<a target="_blank" href="/wenda/detail/338293">想学习框架，SSH好还是SSM好，新手</a>有新的回复
                </p>
                <h5 class="notice-date">2017-03-01 22:00:07</h5>
            </div>
            <div class="del-box clearfix">
                <a href="javascript:void(0)" class="del-notice" title="删除此通知">
                    <i class="icon icon-del"></i>
                </a>
            </div>
        </div>
	</div>
    	<div note-id="604172" class="notice">
        <div class="notice-box clearfix">
                            <p class="notice-type " style="    color: #4233b7;
    border: 1px solid #4233b7;
    background: #eee;">知识库</p>
            
        
            <div class="notice-show-box">
                <p class="notice-con ">
                    你的知识库：“<a target="_blank" href="/wenda/detail/338486">指针初始化的问题</a>” 有新的回复
                </p>
                <h5 class="notice-date">2016-12-12 23:00:02</h5>
            </div>
            <div class="del-box clearfix">
                <a href="javascript:void(0)" class="del-notice" title="删除此通知">
                    <i class="icon icon-del"></i>
                </a>
            </div>
        </div>
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
    </script>
</body>
</html>
