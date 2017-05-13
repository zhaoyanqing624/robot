
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>智能小朵-知识库</title>
    <link href="/org.xjtusicd3.partner/ico/zyq.ico" type="image/x-icon" rel="shortcut icon">
    <link rel="stylesheet" type="text/css" href="new/front/style/reset.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/utilDetails.css" />
    <link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/header.css">
	<link rel="stylesheet" type="text/css" href="css/body.css">
	<link rel="stylesheet" type="text/css" href="css/detail.css">
	<link rel="stylesheet" type="text/css" href="css/validate.css">
    <link href="zhao/lunbo/css/jquery.onebyone-min.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="zhao/tankuang/css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="zhao/tankuang/css/dialog.css" />
	<link rel="stylesheet" type="text/css" href="zhao/tankuang/css/dialog-wilma.css" />
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
    <script src="zhao/tankuang/js/modernizr.custom.js"></script>
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
	        	<a href="" class="logoCon">
	            	<img src="images/logo.jpg" class="logo">
	            	<span>小朵知识库</span>
	        	</a>
    		</div>
		</div>
    </div>
    
    <div class="mainContent">
    <div id="main">
        <div class="contentWra ">
			<div class="writerWrapper">
            <h3>创建知识</h3>
            <div class="writerContent">
                <ul class="formul">
                	<li class="clearfix">
                        <label><em>*</em>标题：</label>
                        <div class="details" >
                            <input type="text" class="text" id="title" placeholder="请输入知识标题">
                            <div class="validate_faqadd spa1"></div>
                        </div>
                    </li>
                    <li class="clearfix">
                        <label>关键字：</label>
                        <div class="details">
                            <input type="text" class="text" id="keywords" placeholder="在输入关键词时请使用半角逗号间隔">
                            <div class="validate_faqadd spa3"></div>
                        </div>
                    </li>
                    <li class="clearfix">
                        <label>分类：</label>
                        <div class="details">
                        	 <select class="select" id="specialCategoryId" onchange="selectSecondChild()"></select>
                   			 <select class="select" id="subspecialCategoryId" name="classifyName"></select>
                   			 <div class="validate_faqadd spa4"></div>
                        </div>
                    </li>
                    <li class="clearfix">
                        <label><em>*</em>摘要说明：</label>
                        <div class="details">
                            <textarea class="text" id="description"></textarea>
                            <div class="validate_faqadd spa5"></div> 
                        </div>
                        
                    </li>
                    <li class="clearfix">
                        <label>风险提示：</label>
                        <div class="details">
                            <textarea class="text" id="risk_prompt"></textarea>
                            <div class="validate_faqadd spa6"></div> 
                        </div>
                    </li>
                    <li class="clearfix">
                        <label><em>*</em>知识正文：</label>
                        <div class="details">
                        	<script id="editor" type="text/plain" style="width:672px;height:300px;"></script>
                        </div>
                    </li>
                  </ul>
                  
            <div class="btnWrapper">
                <a  class="blue"  id="sub" data-dialog="somedialog" >提交</a>
            </div>
        </div>
        </div>
        </div>
   </div>
    <div id="foot" class="footer">
    	<p style="color: #ffffff;text-align: center;">© 西安交通大学社会智能与复杂数据处理实验室  2017.</p>
    </div>
	<div id="somedialog" class="dialog">
		<div class="dialog__overlay"></div>
		<div class="dialog__content">
			<div class="morph-shape">
				<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 560 280" preserveAspectRatio="none">
					<rect x="3" y="3" fill="none" width="556" height="276"/>
				</svg>
			</div>
			<div class="dialog-inner">
				<h2><strong>上传成功，请您耐心等待管理员审核</strong></h2>
				<div><button class="action" data-dialog-close onclick="windowclose()">关闭</button></div>
			</div>
		</div>
	</div>
	<div id="somedialog2" class="dialog">
		<div class="dialog__overlay"></div>
		<div class="dialog__content">
			<div class="morph-shape">
				<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 560 280" preserveAspectRatio="none">
					<rect x="3" y="3" fill="none" width="556" height="276"/>
				</svg>
			</div>
			<div class="dialog-inner">
				<h2><strong>请耐心等待审核，切勿重复提交</strong></h2>
				<div><button class="action" data-dialog-close onclick="windowclose()">关闭</button></div>
			</div>
		</div>
	</div>
	<div id="lasturl" style="display:none"></div>
		<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>    
		<script type="text/javascript" src="js/my_faqadd.js"></script>
		<script type="text/javascript" src="new/front/js/util.js"></script>
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
		<script src="zhao/tankuang/js/classie.js"></script>
		<script src="zhao/tankuang/js/dialogFx.js"></script>
		<script>
			function windowclose(){
				var url = document.getElementById('lasturl').innerHTML;
				self.location=url;
			}
		</script>
</body>
</html>
