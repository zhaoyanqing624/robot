define(function(require, exports, module){
    var tpl ='\
<div class="dialogBox " style="width: 430px;">\
            <form action="updatemyinfo2.html" method="post">\
            <div class="moco-form-group">\
                <label  class="moco-control-label">输入原始密码：</label>\
                <div class="moco-control-input">\
                    <input type="password" placeholder="输入密码" class="moco-form-control" data-validate="require-password" id="" name="userPassword1">\
                    <div class="moco-control-tip errorHint color_red"></div>\
                </div>\
            </div>\
            <div class="moco-form-group">\
                <label  class="moco-control-label">输入新密码：</label>\
                <div class="moco-control-input">\
                    <input type="password" placeholder="输入密码" class="moco-form-control" data-validate="require-password" id="" name="userPassword2">\
                    <div class="moco-control-tip errorHint color_red"></div>\
                </div>\
            </div>\
            <div class="moco-form-group">\
                <label class="moco-control-label">确认新密码：</label>\
                <div class="moco-control-input">\
                    <input type="password" placeholder="再次输入密码" class="moco-form-control" data-validate="require-password"  id="" name="userPassword3">\
                    <div class="moco-control-tip errorHint color_red"></div>\
                </div>\
            </div>\
            <div class="moco-form-group">\
                <label class="moco-control-label"></label>\
                <div class="moco-control-input">\
                    <button class="moco-btn moco-btn-blue">确定</button>\
                     <button class="moco-btn moco-btn-normal">取消</button>\
                    <p class="g_error"></p>\
                </div>\
            </div>\
            </form>\
        </div>\
    '

    module.exports = tpl
})