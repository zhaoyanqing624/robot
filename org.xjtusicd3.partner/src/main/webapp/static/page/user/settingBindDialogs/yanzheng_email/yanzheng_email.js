define(function(require, exports, module){
   require('/static/lib/backbone/backbone-1.0.0.min.js');
   require("/static/component/base/util/validate_v2.js");
   var tpl = require("./tpl.js");
   var view = Backbone.View.extend({
        initialize:function(options){
            this.tpl = tpl;
            this.mall = options.mall;
            this.timer;

        },
        events: {
            "click .dialogBox .js-resendMail": "clickResend",
            "click .js-notVerity3": "clickClearTimer",
            "click .js-gotoVerity3": "clickToVerityEmail",
        },
        clickClearTimer:function(){
            clearInterval(this.timer);
        },
        clickResend:function(e){
            
            $.ajax({
                url:"/user/verificationmail",
                dataType:"json",
                success:function(data){
                    if(data.status==0){
                       
                    }
                    else{
                        util.layerError(data.msg);
                        $this.text("立即验证");
                    }
                    
                },
                error:function(){
                    util.layerError("系统错误！");
                    $this.text("立即验证");
                }
            })

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
            //     console.log('yanzheng',num);
            //     num-=1;
            //     if(num<0){
            //         clearInterval(_this.timer);
            //         _this.$el.find(".js-notVerity3").click();
            //     }
            //     else{
            //         _this.$el.find(".js-djs .js-num").html(num);
            //     }
            // },1000)
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
            return link;
        },
        
        render:function(){
            clearInterval(this.timer);
            $.dialog(this.tpl,{modal:true,title:"验证邮箱",width:488})
            this.math()
            this.$el.find(".account").html(this.mall);
            this.timerForColse();
        },

   })
   module.exports = view;

});




