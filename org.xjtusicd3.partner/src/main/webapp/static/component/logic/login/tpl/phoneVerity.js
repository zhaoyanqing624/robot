
	 var phoneVerityTpl = '<div id="signin" class="rl-modal ">\
	  <div class="rl-modal-header">\
	    <button type="button" class="rl-close" data-dismiss="modal" aria-hidden="true"></button>\
	    <h1>\
			<span data-fromto="signup:signin" class="xa-showSignin">��¼</span>\
			<span  class="active-title">ע��</span>\
	    </h1>\
	  </div>\
	  <div class="rl-modal-body phoneVerityBox js-phoneVerityWrap">\
	    <form id="signup-form">\
			<p class="title">��д������֤���������ע��</p>\
			<p class="subtitle">������֤���ѷ�����<span class="color-red js-phoneNumber"></span></p>\
			<p class="rlf-tip-globle color-red" id="signin-globle-error"></p>\
			<div class="rlf-group pr">\
				<input type="text" id="js-phoneVerity" data-validate="require-string" data-minLength="4"   class="ipt ipt-pwd" placeholder="�����������֤��" maxlength="4" autocomplete="off"/>\
				<p class="reSend pa ">���·���<span class="js-second">60</span></p>\
				<p class="rlf-tip-wrap errorHint color-red" data-error-hint="��������ȷ��֤��" ></p>\
			</div>\
			<div class="rlf-group proclaim-loc  pr">\
				<a href="javascript:void(0)" hidefocus="true" class="proclaim-btn js-proclaim icon-show-pw is-pwd" tabindex="-1"></a>\
				<input type="password" id="js-password" name="password" data-validate="require-password"  class="ipt ipt-pwd js-pass-pwd" placeholder="6-16λ���룬���ִ�Сд�������ÿո�" maxlength="16" autocomplete="off"/>\
		       <p class="rlf-tip-wrap errorHint color-red " data-error-hint="������6-16λ���룬���ִ�Сд������ʹ�ÿո�"></p>\
			</div>\
			<div class="rlf-group clearfix">\
				<a href="javascript:void(0)" value="�ύ" hidefocus="true" class="btn-red btn-full btn r xa-submitePhoneVerity">�ύ</a>\
			</div>\
			<p class="backNotify js-back"  data-fromto="signin:signup">�����޸��ֻ��� </p>\
	    </form>\
	  </div>\
	</div>'