define(function(require, exports, module){
    var tpl ='<div class="tpl-settingEmail" style="width: 430px;">\
            \
            <div class="moco-form-group">\
                <label for="inputEmail3" class="moco-control-label">邮箱地址：</label>\
                <div class="moco-control-input">\
                    <input type="email" placeholder="请输入您的邮箱" class="moco-form-control js-email" data-validate="require-email" id="">\
                    <div class="moco-control-tip errorHint color_red"></div>\
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
                     <button class="moco-btn moco-btn-normal  js-modal-close">取消</button>\
                </div>\
            </div>\
                <p class="js-gerror g_error"></p>\
            \
        </div>\
    '

    module.exports = tpl
})