	var signinTpl ='<div id="signin" class="rl-modal">\
		  <div class="rl-modal-header">\
		    <h1>\
				<span class="active-title">��¼</span>\
				<span data-fromto="signin:signup" class="xa-showSignup">ע��</span>\
		    </h1>\
		    <button type="button" class="rl-close" data-dismiss="modal" hidefocus="true" aria-hidden="true"></button>\
		  </div>\
		  <div class="rl-modal-body js-loginWrap">\
		  	<div class="clearfix">\
				<div class="l-left-wrap l">\
					<form id="signup-form" autocomplete="off">\
						<p class="rlf-tip-globle color-red" id="signin-globle-error"></p>\
						<div class="rlf-group pr">\
							<input type="text" value=""  maxlength="37" name="email" data-validate="require-mobile-phone" autocomplete="off" class="xa-emailOrPhone ipt ipt-email js-own-name" placeholder="�������¼����/�ֻ���"/>\
							<p class="rlf-tip-wrap errorHint color-red" data-error-hint="��������ȷ��������ֻ���"></p>\
						</div>\
						<div class="rlf-group  pr">\
							<input type="password" name="password" data-validate="require-password" class="ipt ipt-pwd js-loginPassword js-pass-pwd" placeholder="6-16λ���룬���ִ�Сд�������ÿո�" maxlength="16" autocomplete="off"/>\
					        <p class="rlf-tip-wrap errorHint color-red " data-error-hint="������6-16λ���룬���ִ�Сд������ʹ�ÿո�"></p>\
						</div>\
						\
						<div class="rlf-group clearfix form-control js-verify-row">\
						    <input type="text"  name="verify" class="ipt ipt-verify l" data-validate="require-string" data-callback="checkverity"  maxlength="4" data-minLength="4" placeholder="��������֤��">\
						    <a href="javascript:void(0)" hidefocus="true" class="verify-img-wrap js-verify-refresh"></a>\
						    <a href="javascript:void(0)" hidefocus="true" class="icon-refresh js-verify-refresh"></a>\
							<p class="rlf-tip-wrap errorHint color-red" data-error-hint="��������ȷ��֤��"></p>\
						</div>\
						<div class="rlf-group rlf-appendix form-control  clearfix">\
							<label for="auto-signin" class="rlf-autoin l" hidefocus="true"><input type="checkbox" checked="checked" class="auto-cbx" id="auto-signin">�´��Զ���¼</label>\
							<a href="/user/newforgot" class="rlf-forget r" target="_blank" hidefocus="true">�������� </a>\
						</div>\
						<div class="rlf-group clearfix">\
							<input  type="button" value="��¼" hidefocus="true" class="btn-red btn-full xa-login"/>\
						</div>\
				    </form>\
				</div>\
		  	</div>\
		  </div>\
		  <div class="rl-model-footer">\
			<div class="pop-login-sns clearfix">\
				<span class="l " style="color:#666">������ʽ��¼</span>\
				<a href="javascript:void(0)" hidefocus="true" data-login-sns="/passport/user/tplogin?tp=weibo" class="pop-sns-weibo r mr60"><i class="icon-weibo"></i></a>\
				<a href="javascript:void(0)" hidefocus="true" data-login-sns="/passport/user/tplogin?tp=weixin" class="pop-sns-weixin r"><i class="icon-weixin"></i></a>\
				<a href="javascript:void(0)" hidefocus="true" data-login-sns="/passport/user/tplogin?tp=qq" class="pop-sns-qq r"><i class="icon-qq"></i></a>\
			</div>\
			 <div class="erweima xa-showQrcode"></div>\
		  </div>\
		</div>\
	'

