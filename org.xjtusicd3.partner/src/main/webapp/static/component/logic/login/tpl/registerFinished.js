var registerFinishedTpl = '<div id="signin" class="rl-modal">\
		  <div class="rl-modal-header">\
		    <button type="button" class="rl-close js-refresh" data-dismiss="modal" aria-hidden="true"></button>\
		    <h1>\
				<span data-fromto="signup:signin">登录</span>\
				<span  class="active-title">注册</span>\
		    </h1>\
		  </div>\
		  <div class="rl-modal-body phoneVerityBox">\
		    <form id="signup-form">\
				<div class="user_header"></div> \
				<p class="title fs16 mb20"><span class="fs18 bold">恭喜您成功注册！</span><br/>资料尚未设置，是否去完善个人资料？</p>\
				<div class="rlf-group clearfix finished_wrap">\
					<a  class="moco-btn moco-btn-red js-gotoSetting"  style="padding:12px 35px; margin-right:10px;" target="_blank">现在就去</a>\
			        <button class="moco-btn moco-btn-normal js-modal-close js-notVerity js-refresh" style="padding:12px 35px;">以后再说</button>\
			    </div>\
		    </form>\
		  </div>\
		</div>'