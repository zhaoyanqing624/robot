define(function(require, exports, module){
    var tpl ='<div class="icon-tick-revert s-right"></div>\
        <div class="finshBox"> <p class="font1">已向<span class="account color-red boreakall"></span>发送邮件</p>\
        <p class="success-hint">请登录邮箱点击确认连接完成验证</p>\
        <a href="/about/faq?currid=2" target="_blank" class="cantGot  js-resendMail">收不到邮件怎么办</a><div class="cb mb30"></div>\
        <a  class="moco-btn moco-btn-blue js-gotoVerity3"  target="_blank">马上去验证</a>\
        <button class="moco-btn moco-btn-normal js-modal-close js-notVerity3 js-djs">暂不验证</button>\
        </div>\
        '


    module.exports = tpl
})


