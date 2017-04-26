
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
    <script type="text/javascript" src="zhao/lunbo/js/jquery.js"></script>
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
	            	<span>小朵知识库</span>
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
                    <a href="/org.xjtusicd3.partner/faq1.html?p=${classify.classifyId}" id="firstNavStep"><span id="fristProductionName">${classify.classifyName}</span></a>
                    </#list>
                    <span class="arrow">&gt;</span>
                    <#list classify2 as classify2>
                    <a href="/org.xjtusicd3.partner/faq2.html?c=${classify2.classifyId}" id="secondNavStep"><span class="secondStep" id="subProductionName">${classify2.classifyName}</span></a>
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
                            <span>|</span>
                            <span id="doc_id" doc_id="11717">知识编号：</span>
                            <span>${faq3Views.faqId}</span>
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
                            <span class="share">分享至：</span>
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
                    <textarea class="textarea" id="content"></textarea>
                    <div class="clearfix commentScoreBtn">
                        <input type="button" value="发表评论" class="publishCommentBtn" onclick="comment()">
                        <div class="scoreContent clearfix">
                            <!--<span class="fl">评分:</span>
                            <span class="score-icon fl" id="scoreList">
                                <i class="score-star"></i>
                                <i class="score-star"></i>
                                <i class="score-star"></i>
                                <i class="score-star"></i>
                                <i class="score-star"></i>
                            </span>-->
                        </div>
                        <!-- 隐藏域，用于存放回复用户id -->
                        <input type="hidden" id="replayuserid">
                        <!-- 隐藏域，用于存放评论id -->
                        <input type="hidden" id="knowledgecommentid">
                        <input type="hidden" id="commentid">
                        <input type="hidden" id="replyid">
                    </div>
                    <h3>文章评论</h3>
                    <ul class="commentList" id="commentContent"><li class="commentLiContent"><div class="userContent clearfix"><span class="username">新手9254</span><span class="line">|</span><span class="time">2016-12-06 14:05:20</span></div><div class="clearfix content"><a href="javascript:void(0);" class="commentReplay" onclick="openreply(this,'10085849254','10097837826','11717','793')">回复</a><p class="text">很好的一篇文章，新手也可以很容易理解。</p></div></li></ul>

                    <p class="ac hidden" id="querymorelink">
                        <a href="javascript:void(0);" onclick="querymorecomment()">查看更多...</a>
                    </p>
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
    <!--script--!>
    	<script type="text/javascript" src="new/front/js/util.js"></script>
    	<script type="text/javascript" src="zhao/lunbo/js/jquery.plugins-min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			$('#onebyone_slider').oneByOne({
				className:'oneByOne1',
				easeType:'random',
				slideShow:true,
				delay:200,
				slideShowDelay:4000
			})
		});
		</script> 
    <!--/script--!>
</body>
</html>
