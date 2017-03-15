
	 var phoneVerityTpl = '<div id="signin" class="rl-modal ">\
	  <div class="rl-modal-header">\
	    <button type="button" class="rl-close" data-dismiss="modal" aria-hidden="true"></button>\
	    <h1>\
			<span data-fromto="signup:signin" class="xa-showSignin">登录</span>\
			<span  class="active-title">注册</span>\
	    </h1>\
	  </div>\
	  <div class="rl-modal-body phoneVerityBox js-phoneVerityWrap">\
	    <form id="signup-form">\
			<p class="title">填写短信验证码密码完成注册</p>\
			<p class="subtitle">短信验证码已发送至<span class="color-red js-phoneNumber"></span></p>\
			<p class="rlf-tip-globle color-red" id="signin-globle-error"></p>\
			<div class="rlf-group pr">\
				<input type="text" id="js-phoneVerity" data-validate="require-string" data-minLength="4"   class="ipt ipt-pwd" placeholder="请输入短信验证码" maxlength="4" autocomplete="off"/>\
				<p class="reSend pa ">重新发送<span class="js-second">60</span></p>\
				<p class="rlf-tip-wrap errorHint color-red" data-error-hint="请输入正确验证码" ></p>\
			</div>\
			<div class="rlf-group proclaim-loc  pr">\
				<a href="javascript:void(0)" hidefocus="true" class="proclaim-btn js-proclaim icon-show-pw is-pwd" tabindex="-1"></a>\
				<input type="password" id="js-password" name="password" data-validate="require-password"  class="ipt ipt-pwd js-pass-pwd" placeholder="6-16位密码，区分大小写，不能用空格" maxlength="16" autocomplete="off"/>\
		       <p class="rlf-tip-wrap errorHint color-red " data-error-hint="请输入6-16位密码，区分大小写，不能使用空格！"></p>\
			</div>\
			<div class="rlf-group clearfix">\
				<a href="javascript:void(0)" value="提交" hidefocus="true" class="btn-red btn-full btn r xa-submitePhoneVerity">提交</a>\
			</div>\
			<p class="backNotify js-back"  data-fromto="signin:signup">返回修改手机号 </p>\
	    </form>\
	  </div>\
	</div>'