define(function(require, exports, module){
	var tpl ='\
<div class="dialogBox tpl-settingPhone" style="width: 430px;">\
            \
            <div class="moco-form-group">\
                <label for="inputEmail3" class="moco-control-label">手机号码：</label>\
                <div class="moco-control-input">\
                    <input data-callback="checkPhone"  placeholder="请输入您的手机号" class="moco-form-control js-phoneNumber" data-validate="require-mobile-phone" id="">\
                    <div class="moco-control-tip errorHint color_red"></div>\
                </div>\
            </div>\
             <div class="moco-form-group">\
                <label for="inputEmail3" class="moco-control-label">短信验证码：</label>\
                <div class="moco-control-input w200">\
                    <p class="js-getCode getCode pa js-timer">获取验证码</p>\
                    <input maxLength=4 placeholder="" class="moco-form-control js-phoneCode" data-validate="require-string" data-minLength="4"  id="">\
                    <div class="moco-control-tip errorHint color_red" data-error-hint="输入四位验证码"></div>\
                </div>\
            </div>\
            <div class="moco-form-group js-hassPassHide">\
                <label for="inputEmail3" class="moco-control-label">输入密码：</label>\
                <div class="moco-control-input">\
                    <input type="password" placeholder="输入密码" class="moco-form-control js-pwd" data-validate="require-password" id="">\
                    <div class="moco-control-tip errorHint color_red"></div>\
                </div>\
            </div>\
            <div class="moco-form-group js-hassPassHide">\
                <label for="inputEmail3" class="moco-control-label">确认密码：</label>\
                <div class="moco-control-input">\
                    <input type="password" placeholder="再次输入密码" class="moco-form-control js-surPwd" data-validate="require-password"  id="">\
                    <div class="moco-control-tip errorHint color_red"></div>\
                </div>\
            </div>\
            <div class="moco-form-group">\
                <label for="inputEmail3" class="moco-control-label"></label>\
                <div class="moco-control-input">\
                    <button class="moco-btn moco-btn-blue js-submit">确定</button>\
                    <button class="moco-btn moco-btn-normal js-modal-close">取消</button>\
                    <p class="js-gerror g_error"></p>\
                </div>\
            </div>\
        </div>\
	'

	module.exports = tpl
})