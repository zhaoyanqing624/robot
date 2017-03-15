seajs.config({
    paths:{
        "cptbase":"/static/component" ,//componet base path
    }
});

seajs.use(["cptbase/base/placeholder/placeholder.js",
            "cptbase/base/autocomplete/autocomplete.js",
            "cptbase/base/util/modal.button.js",
            "cptbase/base/util/core.js",
            "cptbase/base/util/validate.js",
            "cptbase/base/qrcode/qrcode.min.js",
            "cptbase/logic/login/login-view.js"],
function(){


    var PageDocumentRow = Loginview.extend({
         initialize: function(options) {
            this.options = options;
            this.dom = signupTpl;
            this.interval = null;
            this.val = null;
            this.loginWithCode = false;
            this.verifyLoad = false;
            this.IfPWDTypeChange = true;
            var _this = this;
            validateCallback['checkusername'] = function(value){

               _this.blurToCheckUserName(value);
            };
            validateCallback['checkverity'] = function(value){
               _this.checkverity(value);
            }
           
        },

        clickTosubmitePhoneVerity: function(vals) {
          
            if (!W.validate(this.$el.find("#js-phoneVerity").parent())) {
                return;
            }
            
            if (!W.validate(this.$el.find(".js-pass-pwd ").parent())) {
                return;
            }
             $(".xa-submitePhoneVerity").text("正在提交...")
            $(".xa-submitePhoneVerity").attr("disabled","disabled"); 
            var _this = this;
            var val ={
                plantform : plantform,
                number:$(".js-phoneNumber").html(),
                mobileverify:$("#js-phoneVerity").val(),
                password:$("#js-password").val(),
                type:1,
                referer:window.location.protocol+"//"+window.location.hostname,
            }
            var plantform = $.getUrlParam('plantform');
            if(plantform == 'ipad'){
                val.plantform = plantform;
            }
            var _data = { username:$(".js-phoneNumber").html(),}
            console.log(val);
            console.log('iiiiiiiiiii')
            $.ajax({
                url:"/passport/user/phoneregister",
                data:val,
                method:"post",
                dataType:"json",
                success:function(res){
                    if(res.status == 10001){

                            var ipaddata = {};
                            ipaddata.plantform = $.getUrlParam('plantform');
                            if(ipaddata.plantform == 'ipad'){
                                var uid = res.data['userInfo']['uid'];
                                ipaddata.account = val.number;
                                ipaddata.uid =uid.toString();
                                window.webkit.messageHandlers.registerSuccess.postMessage(JSON.stringify(ipaddata));
                                return;
                            }

                            imoocSSO.crossDomainAction(function(){
                                _this.showRegisterFinished(_data);
                               
                            })
                            imoocSSO.setCrossDomainCookie(res['data']['url']);

                    }else{
                        $("#signin-globle-error").addClass("rlf-tip-error").html(res['msg']);
                    }
                    
                },
                error:function(res){
                    $("#signin-globle-error").addClass("rlf-tip-error").html("服务错误，稍后重试");
                },
                complete:function(){
                    $(".xa-submitePhoneVerity").text("提交").removeAttr("disabled").removeClass("disabled");
                }

            })

        },


        showPhoneVerity: function(val){
            this.dom = phoneVerityTpl;
            this.render();
            this.$el.find(".wel-hd").hide();
            $(".js-phoneNumber").html(val.username);
            var index = 60;
                clearInterval(this.interval);

                this.interval = setInterval(function(){
                $(".js-second").parent().removeClass("js-reSend");
                $(".js-second").parent().removeClass("active");
                $(".js-second").html(index);
                if(index<1){
                    $(".js-second").parent().addClass("active");
                    $(".js-second").parent().addClass("js-reSend");
                    $(".js-second").html("");
                    clearInterval(this.interval);

                }
                index--;
                
            },1000)
        },
        clickToShowSignup: function(){
            this.dom = signupTpl;
            this.render();
            this.$el.find(".wel-hd").show();
        },


        render: function() {
            $(".rl-modal").remove();   
            this.$el.append(this.dom);     
            var plantform = $.getUrlParam('plantform');
            if(plantform == 'ipad'){
                this.$el.find('.ipadHide').hide();
            }
            this.$el.find('.pop-login-sns').removeClass("pop-login-sns").addClass('login-sns-wrap');
            this.$el.find('.js-modal-body').addClass('mt30');
            this.$el.find('.verify-img-wrap').append(
                $('<img class="verify-img"/>')
            );
            this.refreshVerifyCode();


            if(typeof(ownName) !== "undefined"){$(".js-own-name").val(ownName)};
            this.$el.find('.rl-modal-header').remove();

             var plantform = $.getUrlParam('plantform');
                if(plantform == 'ipad'){
                    this.$el.find('.login-sns-wrap').remove();
                        
            }

        }

    })
    var view = new PageDocumentRow ({
        el: $(".login-wrap"),
    });
    view.render();

});