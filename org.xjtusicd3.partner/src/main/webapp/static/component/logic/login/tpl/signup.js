

	var signupTpl = '<div id="signup" class="rl-modal ">\
	 <div class="rl-modal-header">\
		    <button type="button" class="rl-close" data-dismiss="modal" aria-hidden="true"></button>\
		    <h1>\
				<span data-fromto="signup:signin" class="xa-showSignin">��¼</span>\
				<span class="active-title">ע��</span>\
		    </h1>\
		  </div>\
		  <div class="rl-modal-body js-modal-body js-registerWrap">\
		    <form id="signup-form pr">\
				<p class="rlf-tip-globle color-red rlf-g-tip" id="signup-globle-error"></p>\
				<div class="rlf-group  pr">\
							<input type="text"  maxlength="37" value="" name="email" data-callback="checkusername" data-validate="require-mobile-phone" autocomplete="off" class="xa-emailOrPhone ipt ipt-email " placeholder="������ע������/�ֻ���"/>\
							<p class="rlf-tip-wrap errorHint color-red" data-error-hint="��������ȷ��������ֻ���"></p>\
						</div>\
				<div class="rlf-group proclaim-loc pr xa-passwordWrap hide">\
					<a href="javascript:void(0)" hidefocus="true" class="proclaim-btn js-proclaim icon-show-pw is-pwd" tabindex="-1"></a>\
					<input type="password" name="password"  data-validate="require-password" class="ipt ipt-pwd js-pass-pwd" placeholder="6-16λ���룬���ִ�Сд�������ÿո�" maxlength="16" autocomplete="off"/>\
			      \
			        <p class="rlf-tip-wrap errorHint color-red " data-error-hint="������6-16λ���룬���ִ�Сд������ʹ�ÿո�"></p>\
				</div>\
				<div class="rlf-group clearfix form-control ">\
				    <input type="text"  name="verify" class="ipt ipt-verify js-emailverify l" data-validate="require-string" data-callback="checkverity"  maxlength="4" data-minLength="4" placeholder="��������֤��">\
				    <a href="javascript:void(0)" hidefocus="true" class="verify-img-wrap js-verify-refresh"></a>\
				    <a href="javascript:void(0)" hidefocus="true" class="icon-refresh js-verify-refresh"></a>\
					<p class="rlf-tip-wrap errorHint color-red" data-error-hint="��֤�����"></p>\
				</div>\
				<div class="rlf-group clearfix">\
					<a href="javascript:void(0)"  id="signup-btn"  hidefocus="true" class="btn-red btn-full btn r"> ע�� </a>\
				</div>\
		    </form>\
		  </div>\
		  <div class="rl-model-footer">\
		  	<div class="pop-login-sns clearfix">\
		  		<span class="l " style="color:#666">������ʽ��¼</span>         \
		  		<a href="javascript:void(0)" hidefocus="true" data-login-sns="/passport/user/tplogin?tp=weibo" class="pop-sns-weibo r"><i class="icon-weibo"></i></a>\
		  		<a href="javascript:void(0)" hidefocus="true" data-login-sns="/passport/user/tplogin?tp=weixin" class="pop-sns-weixin r"><i class="icon-weixin"></i></a>\
		  		<a href="javascript:void(0)" hidefocus="true" data-login-sns="/passport/user/tplogin?tp=qq" class="pop-sns-qq r"><i class="icon-qq"></i></a>\
		  	</div>\
		  </div>\
		</div>\
	'



