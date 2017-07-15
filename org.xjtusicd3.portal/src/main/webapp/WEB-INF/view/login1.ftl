<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

<meta charset="utf-8" />

<title>Metronic | Login Options - Login Form 2</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<meta content="" name="description" />

<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/bootstrap-responsive.min.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/font-awesome.min.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/style-metro.css" rel="stylesheet" type="text/css" />

<link href="media/css/style.css" rel="stylesheet" type="text/css" />

<link href="media/css/style-responsive.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/default.css" rel="stylesheet" type="text/css"
	id="style_color" />

<link href="media/css/uniform.default.css" rel="stylesheet"
	type="text/css" />

<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->

<link href="media/css/login-soft.css" rel="stylesheet" type="text/css" />

<!-- END PAGE LEVEL STYLES -->

<link rel="shortcut icon" href="media/image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="login">

	<!-- BEGIN LOGO -->

	<div class="logo">

		<!-- <img src="media/image/logo-big.png" alt="" />  -->

	</div>

	<!-- END LOGO -->

	<!-- BEGIN LOGIN -->

	<div class="content">

		<!-- BEGIN LOGIN FORM -->
<form class="form-vertical login-form" action="#"
			method="post">
			<input type="submit" value="登陆" class="btn blue pull-right"/>
			</form>
		<form class="form-vertical login-form" action="adminLogin.html"
			method="post">

			<h3 class="form-title">你好，请登入系统</h3>

			<div class="alert alert-error hide">

				<button class="close" data-dismiss="alert"></button>

				<span>输入用户名和密码</span>

			</div>

			<div class="control-group">

				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->

				<label class="control-label visible-ie8 visible-ie9">用户名</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-user"></i> <input class="m-wrap placeholder-no-fix"
							type="text" placeholder="用户名/邮箱" name="UserEmail" />

					</div>

				</div>

			</div>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">密码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-lock"></i> <input class="m-wrap placeholder-no-fix"
							type="password" placeholder="密码" name="UserPassword" />

					</div>

				</div>

			</div>

			<div class="form-actions">

				<label class="checkbox"> <input type="checkbox"
					name="remember" value="1" /> 记住密码

				</label>

				<input type="submit" value="登陆" class="btn blue pull-right"/>

			</div>

			<div class="forget-password">

				<h4>忘记密码?</h4>

				<p>

					<a href="javascript:;" class="" id="forget-password">点击找回</a>

				</p>

			</div>

			<div class="create-account">

				<p>

					暂无账号&nbsp; <a href="javascript:;" id="register-btn" class="">创建账号</a>

				</p>

			</div>

		</form>

		<!-- END LOGIN FORM -->

		<!-- BEGIN FORGOT PASSWORD FORM -->

		<form class="form-vertical forget-form" action="">

			<h3 class="">忘记密码？</h3>

			<p>输入你的邮箱地址来找回密码</p>

			<div class="control-group">

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-envelope"></i> <input
							class="m-wrap placeholder-no-fix" type="text" placeholder="邮箱"
							name="email" />

					</div>

				</div>

			</div>

			<div class="form-actions">

				<button type="button" id="back-btn" class="btn">

					<i class="m-icon-swapleft"></i> 返回

				</button>

				<button type="submit" class="btn blue pull-right">

					已发送 <i class="m-icon-swapright m-icon-white"></i>

				</button>

			</div>

		</form>

		<!-- END FORGOT PASSWORD FORM -->

		<!-- BEGIN REGISTRATION FORM -->

		<form class="form-vertical register-form" action="">

			<h3 class="">注册管理员账号</h3>

			<p>请输入以下信息</p>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">用户名</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-user"></i> <input class="m-wrap placeholder-no-fix"
							type="text" placeholder="用户名" name="username" />

					</div>

				</div>

			</div>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">密码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-lock"></i> <input class="m-wrap placeholder-no-fix"
							type="password" id="register_password" placeholder="密码"
							name="password" />

					</div>

				</div>

			</div>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">请再输入一次密码</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-ok"></i> <input class="m-wrap placeholder-no-fix"
							type="password" placeholder="请再输入一次密码" name="rpassword" />

					</div>

				</div>

			</div>

			<div class="control-group">

				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->

				<label class="control-label visible-ie8 visible-ie9">邮箱</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-envelope"></i> <input
							class="m-wrap placeholder-no-fix" type="text" placeholder="邮箱"
							name="email" />

					</div>

				</div>

			</div>

			<div class="control-group">

				<div class="controls">

					<label class="checkbox"> <input type="checkbox" name="tnc" />我同意<a
						href="#">管理员条约</a> and <a href="#">保密协议</a>

					</label>

					<div id="register_tnc_error"></div>

				</div>

			</div>

			<div class="form-actions">

				<button id="register-back-btn" type="button" class="btn">

					<i class="m-icon-swapleft"></i> 返回

				</button>

				<button type="submit" id="register-submit-btn"
					class="btn blue pull-right">

					注册 <i class="m-icon-swapright m-icon-white"></i>

				</button>

			</div>

		</form>

		<!-- END REGISTRATION FORM -->

	</div>

	<!-- END LOGIN -->

	<!-- BEGIN COPYRIGHT -->

	<div class="copyright">2017 &copy; 西安交通大学SICDP实验室.</div>

	<!-- END COPYRIGHT -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

	<script src="media/js/jquery-migrate-1.2.1.min.js"
		type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="media/js/jquery-ui-1.10.1.custom.min.js"
		type="text/javascript"></script>

	<script src="media/js/bootstrap.min.js" type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="media/js/excanvas.min.js"></script>

	<script src="media/js/respond.min.js"></script>  

	<![endif]-->

	<script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

	<script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>

	<script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>

	<script src="media/js/jquery.uniform.min.js" type="text/javascript"></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script src="media/js/jquery.validate.min.js" type="text/javascript"></script>

	<script src="media/js/jquery.backstretch.min.js" type="text/javascript"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="media/js/app.js" type="text/javascript"></script>

	<script src="media/js/login-soft.js" type="text/javascript"></script>

	<!-- END PAGE LEVEL SCRIPTS -->

	<script>

		jQuery(document).ready(function() {     

		  App.init();

		  Login.init();

		});

	</script>

	<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>