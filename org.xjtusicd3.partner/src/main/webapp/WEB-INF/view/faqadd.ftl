
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
    <script type="text/javascript" charset ="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
    <script src="zhao/tankuang/js/modernizr.custom.js"></script>

</head>
<body>
	<!-- 头部开始 -->
	<div class="header" id="head"> 
	<#include "inc/incTop.ftl">
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
    <!-- 头部结束 -->
    
    <!-- 主体 -->
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
                        	<!-- 嵌入编辑器 -->
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
    
	<!-- 底部开始 -->
	<#include "/inc/incFoot.ftl">
	<!-- 底部结束 -->
	
	<!-- 用于提交 -->
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
	
	<!-- 检测重复提交-->
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
	
		<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>    
		<script type="text/javascript" src="js/my_faqadd.js"></script>
		<script type="text/javascript" src="new/front/js/util.js"></script>  
		<script src="zhao/tankuang/js/classie.js"></script>
		<script src="zhao/tankuang/js/dialogFx.js"></script>
		<script type="text/javascript" charset="utf-8" src="js/view/ueditor.js"></script>
		<script>
			function windowclose(){
				var url = document.getElementById('lasturl').innerHTML;
				self.location=url;
			}
		</script>
</body>
</html>
