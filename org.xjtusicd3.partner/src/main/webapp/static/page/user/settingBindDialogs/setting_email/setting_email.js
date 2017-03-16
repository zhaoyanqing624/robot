define(function(require, exports, module){
   require('/org.xjtusicd3.partner/static/lib/backbone/backbone-1.0.0.min.js');
   require("/org.xjtusicd3.partner/static/component/base/util/validate_v2.js");
   var tpl = require("./tpl.js");
   var view = Backbone.View.extend({
        initialize:function(options){
            this.tpl = tpl;
            this.hasPass = hasPass;
        },
        events: {
            "click .tpl-settingEmail .js-submit": "clickToSubmit",
             "click .js-gotoVerity2": "clickToVerityEmail",
        },
        math:function(){
            var genericEmailLinks="sohu.com::http://mail.sohu.com \
                        |sina.com,sina.cn :: http://mail.sina.com \
                        |vip.sina.com :: http://vip.sina.com.cn \
                        |126.com :: http://www.126.com \
                        |163.com :: http://mail.163.com \
                        |vip.163.com :: http://vip.163.com \
                        |vip.126.com :: http://vip.126.com \
                        |qq.com,vip.qq.com :: http://mail.qq.com \
                        |msn.com,outlook.com,hotmail.com,live.cn,live.com :: http://outlook.com \
                        |gmail.com :: http://www.gmail.com \
                        |yahoo.com.cn,yahoo.cn,aliyun.com :: http://mail.aliyun.com \
                        |yahoo.com.tw :: http://mail.yahoo.com.tw \
                        |21cn.com :: http://mail.21cn.com \
                        |tom.com :: http://mail.tom.com/ ",
            genericEmailLinksMap={};

            $.each(genericEmailLinks.split("|"),function(index,v){
                var val=v.split("::"),
                    v=$.trim(val[1]),
                    i,len;
                val=val[0].split(",");
                for(i=0,len=val.length;i<len;i++){
                    genericEmailLinksMap[$.trim(val[i])]=v;
                }
            });   

           
            var link = genericEmailLinksMap[this.email.match(/[^@]*$/)[0]];
            return link;
        }, 

        clickToVerityEmail: function(){
            var link = this.math()
            window.open(link);
            window.location.reload();

        },

        timerForColse:function(){
            // clearInterval(this.timer);
            // var num = 5;
            // var _this = this;
            // this.timer = setInterval(function(){
            //     num-=1;
            //     if(num<=1){
            //         clearInterval(_this.timer);
            //         _this.$el.find(".js-notVerity").click();
            //     }
            //     else{
            //         _this.$el.find(".js-num").html(num);
            //     }
            // },1000)
        },
        
        clickToSubmit: function(){

            if (!W.validate(this.$el.find(".tpl-settingEmail"))) {
                return;
            }
            $(".tpl-settingEmail .js-submit").text("正在保存...")
            var data = {};
            this.email = data.email = this.$el.find(".js-email").val();
            if(!this.hasPass){
                data.password = this.$el.find(".js-pwd").val();
                data.password_confirm = this.$el.find(".js-surPwd").val();
            }
            var _this = this;
             $.ajax({
                url:"/user/ajaxbindemail",
                data:data,
                dataType:"JSON",
                type:"post",
                success:function(res){
                    
                    if(res.result == 0){
                        _this.renderFinish();
                    }
                    else{
                        _this.$el.find('.js-gerror').html(res.msg);
                    }
                    
                },
                error:function(){
                    _this.$el.find('.js-gerror').html('系统出错，请稍后重试！')
                },
                complete:function(){
                    $(".tpl-settingEmail .js-submit").text("确定");

                }
            })



          
        },
        render:function(){
            $.dialog(this.tpl,{modal:true,title:"绑定邮箱",width:488});
            if(this.hasPass){

                this.$el.find(".js-hassPassHide").remove();
            }else{
            }
        },
        renderFinish:function(){
            $(".js-modal-close").click();
            $.dialog('<div class="icon-tick-revert s-right"></div>\
                <div class="finshBox"> \
                <p class="success-hint">邮箱绑定成功，但尚未验证</p>\
                <p>你可以通过<span class="color-red">'+this.email+'</span>登录慕课网</p><div class="cb mb30"></div>\
                <a  class="moco-btn moco-btn-blue js-gotoVerity2"  target="_blank">马上去验证</a>\
                <button class="moco-btn moco-btn-normal js-modal-close js-notVerity">暂不验证</button>\
                </div>\
                ',{modal:true,title:"修改邮箱",width:488})
            this.math();
            this.timerForColse();
        },


   })
   module.exports = view;

});