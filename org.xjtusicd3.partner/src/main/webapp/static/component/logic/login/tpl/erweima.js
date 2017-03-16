

var erweimaTpl='<div id="signin" class="rl-modal ">\
	  <div class="rl-modal-header">\
	    <button type="button" class="rl-close" data-dismiss="modal" aria-hidden="true"></button>\
	    <h1>\
			<span class="active-title">登录</span>\
			<span data-fromto="signin:signup" class="xa-showSignup">注册</span>\
	    </h1>\
	  </div>\
	  <div class="rl-modal-body">\
	    <form id="signup-form">\
		\
			<div class="rlf-group proclaim-loc js-proclaim-on">\
				<div class="qrcode" id="qrcode">\
					<div class="hide qrcode-bk-scand">\
						<div class="qrcode-inner"></div>\
						<div class="qrcode_state_scaned"></div>\
					</div>\
					<div class="hide qrcode-bk-validate">\
						<div class="qrcode-inner"></div>\
						<div class="qrcode_state_validate xa-refresh"></div>\
					</div>\
				</div>\
				<p class="qrcode_title">扫描二维码登录慕课网</p>\
				<p class="qrcode_mark">请使用新版 <a target="_blank" href="http://www.imooc.com/mobile/app" class="color-red">慕课网手机APP</a> 扫码完成登录</p>\
			</div>\
			\
	    </form>\
	  </div>\
	  <div class="rl-model-footer">\
				<div class="pcLogin xa-hideQrcode" data-fromto="signup:signin"></div>\
	  </div>\
	</div>'