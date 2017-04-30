define(function(require, exports, module){
   require('/org.xjtusicd3.partner/static/lib/backbone/backbone-1.0.0.min.js');
   require("/org.xjtusicd3.partner/static/component/base/util/validate_v2.js");
   var tpl = require("./tpl.js");
   var bangdingPhoneView = Backbone.View.extend({
        initialize:function(){
            this.tpl = tpl;
            var _this = this;
            this.timer = '';
            validateCallback['checkPhone'] = function(value){

               _this.blurToCheckPhone(value);
            };

        },
        events: {
            "click .tpl-changePhone .js-getCode":"clickTogetCode",
            "click .tpl-changePhone .js-submit": "clickToSubmit",
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

           
            var link = genericEmailLinksMap[this.mall.match(/[^@]*$/)[0]];
                this.$el.find("#js-gotoVerity").attr("href",link);
        },

        resendCode: function(){
            clearInterval(this.timer);
            var num = 60;
            var $node = this.$el.find('.js-timer');
            var _this = this;
            this.timer = setInterval(function(){
                if(num<1){
                    clearInterval(_this.timer);
                    $node.html('重新获取');
                    $node.addClass('js-getCode');
                    $node.css('cursor','pointer');

                }else{
                     $node.html('<span style="color:#B4B8BB">获取验证码['+num+']</span>');
                }
                    num--;

            },1000)
        },

        clickTogetCode: function(){
            var number = this.$el.find(".js-phoneNumber").val();
            var _this = this;
            $.ajax({
                url:"/passport/user/checkphonecode",
                dataType:"json",
                data:{
                    phone:number,
                    noverify:1, 
                },
                success:function(data){
                    if(data.status==10001){
                        $(".js-timer").removeClass("js-getCode").css('cursor','default');;
                        _this.resendCode();
                    }
                    else{
                        _this.$el.find('.js-gerror').html(data.msg)
                    }
                    
                },
                error:function(){
                    $(".tpl-changePhone .js-submit").text("保存");
                    _this.$el.find('.js-gerror').html('系统出错，请稍后重试！')
                },
                complete:function(){

                }


            })


        },

        blurToCheckPhone: function(value){
            var phoneNumber = value;
            validateCallback.rel = false;
            $.ajax({
                url: '/passport/user/checkphone',
                type: 'post',
                data: phoneNumber,
                dataType: 'json',
                success: function (res){
                    if( res.status == 10001 ){
                        validateCallback.rel = true;
                    }else{

                    }
                },
                error:function(){
                    validateCallback['errorHint'] = "网络错误"
                   
                },
            })

        },
        clickToSubmit: function(){
            if (!W.validate(this.$el.find(".tpl-changePhone"))) {
                return;
            }
            var _this = this; 
            $(".tpl-changePhone .js-submit").text("正在保存...")
            var number = this.$el.find(".js-phoneNumber").val();
            var code = this.$el.find(".js-phoneCode").val();
            $.ajax({
                url:"/passport/user/bindphone",
                dataType:"json",
                data:{
                    phone:number,
                    code:code,
                   
                    noverify:1, 
                },
                success:function(data){
                    if(data.status==10001){
                        window.location.reload();
                    }
                    else{
                       _this.$el.find('.js-gerror').html(data.msg);
                    }
                },
                error:function(){
                    _this.$el.find('.js-gerror').html('系统出错，请稍后重试！')
                },
                complete:function(){
                    $(".tpl-changePhone .js-submit").text("确定");

                }
            })
        },
        render:function(){
            $.dialog(this.tpl,{modal:true,title:"绑定手机",width:488})
        },
        renderFinish:function(){
            $(".js-modal-close").click();
            $.dialog(' <div class="icon-tick-revert s-right"></div><p class="success-hint">恭喜你绑定成功</p><button class="moco-btn moco-btn-blue ">完成[5]</button>',{modal:true,title:"绑定手机",width:488})
        },


   })
   module.exports = bangdingPhoneView;

});


