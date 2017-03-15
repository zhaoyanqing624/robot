define(function(require, exports, module){
   require('/static/lib/backbone/backbone-1.0.0.min.js');
   require("/static/component/base/util/validate_v2.js");
   var tpl = require("./tpl.js");

   var bangdingPhoneView = Backbone.View.extend({
        initialize:function(options){
            this.tpl = tpl;
            var _this = this;
            this.timer = null;
            this.hasPass = options.hasPass
            validateCallback['checkPhone'] = function(value){

               _this.blurToCheckPhone(value);
            };

        },
        events: {
            "click .tpl-settingPhone .js-getCode":"clickTogetCode",
            "click .tpl-settingPhone .js-submit": "clickToSubmit",
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
                    $node.text(num+'s');
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
                    $(".tpl-settingPhone .js-submit").text("保存");
                    _this.$el.find('.js-gerror').html('系统出错，请稍后重试！')
                },
                complete:function(){
                }
            })
        },
        timerForColse:function(){
            // var num = 5;
            // var _this = this;
            // this.timer = setInterval(function(){
            //     num-=1;
            //     if(num<=1){
            //         $(".js-modal-close").click();
            //     }
            //     else{
            //         _this.$el.find(".js-num").html(num);
            //     }
            // },1000)
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

            if (!W.validate(this.$el.find(".tpl-settingPhone"))) {
                return;
            }
            var _this = this;
            $(".tpl-settingPhone .js-submit").text("正在保存...")
            var number = this.$el.find(".js-phoneNumber").val();
            var code = this.$el.find(".js-phoneCode").val();
            var data = {};
            data.phone = number;
            data.code = code;
            data.noverify = 1;
            if(!this.hasPass){
                var pwd = this.$el.find(".js-pwd").val();
                var surpwd = this.$el.find(".js-surPwd").val();
                data.password = pwd;
                data.password_confirm = surpwd;
            }

           $.ajax({
                url:"/passport/user/bindphone",
                dataType:"json",
                data:data,
                success:function(data){
                    if(data.status==10001){
                        window.location.reload();
                    }else{
                        _this.$el.find('.js-gerror').html(data.msg);
                    }
                    
                },
                error:function(){
                    util.layerError("系统错误！");
                    $this.text("立即验证");
                },
                complete:function(){
                    $(".tpl-settingPhone .js-submit").text("确定");

                }
            })
          
        },
        render:function(){
            $.dialog(this.tpl,{modal:true,title:"绑定手机",width:488})
            if(this.hasPass){

                this.$el.find(".js-hassPassHide").remove();
            }else{
            }
        },
        renderFinish:function(){
            $(".js-modal-close").click();
            $.dialog(' <div class="icon-tick-revert s-right"></div><p class="success-hint">恭喜你绑定成功</p><button class="moco-btn moco-btn-blue "></button>',{modal:true,title:"绑定手机",width:488})
            this.timerForColse();
        },


   })
   module.exports = bangdingPhoneView;

});



 // repeatmsgcode: function (){
 //            var $recounttime = $jsPhonemsg.find('.js-recounttime');
 //            var $recount = $jsPhonemsg.find('.js-recount');
 //            clearInterval(timer);
 //            var count = 60;
 //            timer = setInterval(function (){
 //                count--;
 //                if( count == 0 ){
 //                    clearInterval(timer);
 //                    $recounttime.hide();
 //                    $recount.show();
 //                }else{
 //                    $recounttime.html( '重新发送' + count )
 //                }
 //            }, 1000)
 //        },
        
 //        checkphone: function(callback){
 //            var $phone = $jsBinding.find('.js-phone');
 //            var oldphoneval = $('#jsHidephone').val()
 //            util.errortiphide($phone)
 //            $jsBinding.find('.js-gerror').html('')
 //            var value = $.trim( $phone.val() );
 //            if( value == ''){
 //                util.errortipshow($phone, '手机号不能为空')
 //                return;
 //            }
            
 //            if( !this.isPhone.test(value) ){
 //                util.errortipshow($phone, '手机号格式不正确')
 //                return;
 //            }
 //            var data = {
 //                phone: value
 //            };
            
 //            if( oldphoneval.length > 2){
 //                data['type'] = 1;
 //            }
            
 //            $.ajax({
 //                url: '/passport/user/checkphone',
 //                type: 'post',
 //                data: data,
 //                dataType: 'json',
 //                success: function (res){
 //                    if( res.status == 10001 ){
 //                        config.phone = value;
 //                    }else{
 //                        $jsBinding.find('.js-gerror').html(res.msg)
 //                    }
                    
 //                    if(typeof callback == 'function'){
 //                        callback(res.status)
 //                    }
 //                }
 //            })
 //        }