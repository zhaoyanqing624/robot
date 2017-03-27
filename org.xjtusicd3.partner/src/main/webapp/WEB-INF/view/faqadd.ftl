
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
    <link href="zhao/lunbo/css/jquery.onebyone-min.css" rel="stylesheet" />
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
		                <div class="unlogin">
		                    <li class="loginLinkLi"><span class="person_icon"></span></li>
		                    <li class="loginLinkLi" id="userNameText">您好：zhao</li>
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
		                    <li class="left_margin loginLinkLi"><a id="headExit">退出</a>
		                    </li>
		                </div>
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
                        <div class="details">
                            <input type="text" class="text" id="title" placeholder="标题不超过44个中文字符">
                        </div>
                    </li>
                     <li class="clearfix">
                        <label>来源：</label>
                        <div class="details">
                         	<input type="radio" class="radio" value="0" name="resource">原创</input>&nbsp;&nbsp;&nbsp;
                         	<input type="radio" class="radio" value="1" name="resource">转载</input>
                        </div>
                    </li>
                    <li class="clearfix">
                        <label>关键字：</label>
                        <div class="details">
                            <input type="text" class="text" id="keywords" placeholder="在输入关键词时请使用半角逗号间隔">
                        </div>
                    </li>
                    <li class="clearfix">
                        <label>分类：</label>
                        <div class="details">
                        	 <select class="select" id="specialCategoryId"><option value="">请选择第一分类</option><option value="1">操作系统</option><option value="2">随机软件</option><option value="3">设备应用</option><option value="4">显卡切换与调试</option><option value="5">病毒与安全</option><option value="6">驱动程序帮助</option><option value="7">上网问题</option><option value="8">一键恢复</option><option value="9">显示故障</option><option value="10">娱乐影音</option><option value="11">服务网点</option><option value="12">第三方软件</option><option value="13">BIOS</option><option value="140">术语</option><option value="147">评测赏析</option></select>
                   			 <select class="select" id="subspecialCategoryId"><option value="">请选择第二分类</option></select>
                        </div>
                    </li>
                    <li class="clearfix">
                        <label><em>*</em>摘要说明：</label>
                        <div class="details">
                            <textarea class="text" id="description"></textarea> 
                        </div>
                    </li>
                  </ul>
                  <ul class="formul" id="formList">

                  </ul>
                  <ul class="formul">
                    <li class="clearfix faultLi hidden">
                        <label><em>*</em>故障描述：</label>
                        <div class="details">
                            <input type="text" class="text" id="fault_description">
                        </div>
                    </li>                    
                    <li class="clearfix faultLi hidden">
                        <label>原因分析：</label>
                        <div class="details">
                            <textarea class="text" id="reason_analysis"></textarea>
                        </div>
                    </li>
                    <li class="clearfix">
                        <label>风险提示：</label>
                        <div class="details">
                            <textarea class="text" id="risk_prompt"></textarea>
                        </div>
                    </li>
                    <li class="clearfix">
                        <label><em>*</em>知识正文：</label>
                        <div class="details">
                        	<script id="editor" type="text/plain" style="width:672px;height:300px;"></script>
                        </div>
                    </li>
                
            <div class="btnWrapper">
                <a href="javascript:void(0);" class="blue" onclick="submitKnowledge()">提交</a>
                <a href="javascript:void(0);" onclick="preview()">预览</a>
            </div>
        </div>
        </div>
        </div>
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
    <!--/script--!>
</body>
</html>
