define(function(require, exports, module){
   require('/org.xjtusicd3.partner/org.xjtusicd3.partner/static/lib/backbone/backbone-1.0.0.min.js');
   var tpl = require("/org.xjtusicd3.partner/org.xjtusicd3.partner/static/page/user/settingBindDialogs/setting_password/tpl.js");
   var bangdingPhoneView = Backbone.View.extend({
       initialize:function(){
            this.tpl = tpl;
        },
        events: {

            "click .tpl-changePwd .js-submit": "clickToSubmit",
        },
        clickToSubmit: function(){
            var _this = this;
            if (!W.validate(this.$el.find(".tpl-changePwd"))) {
                return;
            }
            $(".tpl-changePwd .js-submit").text("正在保存...")
            var prePwd = this.$el.find(".js-prePwd").val();
            var pwd = this.$el.find(".js-pwd").val();
            var surPwd = this.$el.find(".js-surPwd").val();

            if(pwd != surPwd){
                this.$el.find('.js-gerror').html('两次输入密码不一致')
                $(".tpl-changePwd .js-submit").text("确定")
                return;
            }
            $.ajax({ 
                 type: 'post',
                 url: '/user/ajaxsetinfo',
                 data: {
                    type:4,
                    oldpw:prePwd,
                    newpw:pwd,
                 },
                 dataType: 'json',
                 success: function(data){
                    console.log(data);
                    if(data.result == 1 ){
                        _this.renderFinish();
                    }
                    else{
                        _this.$el.find('.js-gerror').html(data.msg);
                    }
                 },
                 error: function(e){
                    _this.$el.find('.js-gerror').html('后端请求失败,请检查后端接口！');    
                 },
                 complete:function(){
                    $(".tpl-changePwd .js-submit").html("确定");
                 }
            })  


            // this.renderFinish();
            // alert(11)

          
        },
        render:function(){
            $.dialog(this.tpl,{modal:true,title:"修改密码",width:488})
        },
        renderFinish:function(){
            $(".js-modal-close").click();
        },


   })
   module.exports = bangdingPhoneView

});