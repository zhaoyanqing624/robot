define(function(require, exports, module){
	require('common');
	var mbox=require("/org.xjtusicd3.partner/static/page/user/messagebox.js");
    var util = require("/org.xjtusicd3.partner/static/page/user/common/settingUitl.js");
    require("/org.xjtusicd3.partner/static/component/base/util/modal.button.js");
    var changePWDView = require("/org.xjtusicd3.partner/static/page/user/settingBindDialogs/setting_password/setting_password.js");

    var g_changePWDView;
    var viewBind;


	$(document).on("click",".js-changePWD",function(e){
		if(!g_changePWDView){
		    g_changePWDView = new changePWDView ({
		        el: 'body'});
		}
	    g_changePWDView.render()
	});

    $.ajaxSetup({cache:false})
	var winsns=(function(){
	    var o={};
	    function clearPrev(){//dereference
	        for(var key in o){
	            if(key.indexOf("/user")>-1){
	                o[key].close&&o[key].close();
	                o[key]=null;
	                delete o[key];
	            }
	        }
	    }

	    return {
	        open:function(url){
	            var l,t;
	            if(o[url]&&o[url].closed===false){
	                o[url].focus&&o[url].focus();
	                return ;
	            }
	            clearPrev();
	            l=(screen.width-600)/2,
	            t=(screen.height-400)/2;
	            (o[url]=window.open(url, '_blank', 'toolbar=no, directories=no, status=no, menubar=no, width=600, height=500, top='+t+', left='+l)).focus();
	            //o[url].onclose=winClose;

	        },
	        clear:clearPrev
	    }
	})();

	$(document).on("click",".js-bindphone",function(e){
		if(!phoneviewBind){
			phoneviewBind = new bangdingPhoneView ({
		        el: 'body',
		        mode: 'signup',
		    	hasPass: hasPass,
		    });
		}
		    phoneviewBind.render()
	});

	// 清除错误信息
	$(document).on("focus","input",function(e){
		$(".errorHint , .js-gerror").html('');
	});



	$(document).on("click",".js-verify-refresh",function(e){
		showVerityCode();
	});





	$(document).on("click",".js-bind",function(e){
		e.preventDefault();
		winsns.open($(this).attr("href")+"&referer="+window.location.protocol+"//"+window.location.hostname);
	});

	$(document).on("click","[data-unbind]",function(){
		var type=$(this).attr("data-unbind");
		var str={'qq':'QQ', 'weibo':'新浪微博', 'weixin':'微信'}[type];

        var m=$(".msg-layer");
        if(!m.length){
            $("body").append('<div class="msg-layer" style="height:300px!important">\
                <h3>解除绑定</h3>\
                <p>解绑后将不能再使用'+str+'帐号登录慕课网。</p>\
                <div class="error js-error"></div>\
                <input type="password" placeholder="请输入慕课网登录密码" />\
                <div class="error"></div>\
                <a href="javascript:void(0)" class="js-sf-submit btn-submit">确定</a>\
                <button aria-hidden="true" hidefocus="true" data-dismiss="modal" class="btn-close" type="button"></button>\
            </div>');
            m=$(".msg-layer");




            m.on("shown",function(){
            	function unbind(){
                    $('.js-error').html();
                    util.errortiphide($(".msg-layer input"));
                    var c=$(".msg-layer input").val();
                    if(c.length==0){
                    	$(".msg-layer input").addClass('inpt-error').focus();
                    	util.errortipshow($(".msg-layer input"), "请输入慕课网登录密码");
                        return ;
                    }

					$.ajax({
						url:"/passport/user/tpbindcancel",
						data:{
	                        password:c,
	                        tp:type
                    	},
						dataType:"json",
						success:function(data){
							if(data.status==10001){
								window.location.reload();
							}
							else{
                                $('.js-error').html(data.msg)
							}
						},
						error:function(){
                            $('.js-error').html("服务器错误，请稍后重试！");
						}
                        
					})
            	}
                $(".msg-layer .js-sf-submit").on("click",function(event) {
                	unbind();
                })

                $(".msg-layer input").on("keydown",function(event){
		            switch (event.keyCode) {
		                case 13:
	                        unbind()
		                break;
		            }
                })


            }).on("hidden",function(){
                $(this).remove();
            });
        }
        m.modal("show");


	});

	window.__fireLogined=function(data,signup){
		window.location.reload();
	}



	// 邮箱绑定

	var account = $('#jsMail').val();
	var account_phone = $('#jsPhone').text();
	
    
    function usercheck(account){
        var str = '';
            str += '<div class="msg-layer">\
	                    <h3 class="bold">验证身份</h3>\
	                    <div class="keybox"><i class="icon-key"></i></div>\
				             <p class="font1 mb20">请输入登录密码验证身份<br/>'+ account +'</p>\
	                    <div class="dialogBox yanZhengBox" style="width: 430px;">\
				            <div class="moco-form-group">\
				                <label for="inputEmail3" class="moco-control-label">密码：</label>\
				                <div class="moco-control-input">\
				                    <input type="password"  placeholder="请输入密码" class="js-pwd moco-form-control js-phoneNumber" data-validate="require-password" id="">\
				                    <div class="moco-control-tip errorHint color_red"></div>\
				                </div>\
				            </div>\
				             <div class="moco-form-group">\
				                <label for="inputEmail3" class="moco-control-label">验证码：</label>\
				                <div class="moco-control-input w135 mr12">\
				                    <input data-callback="checkverity"   maxLength=4 placeholder="输入验证码" class="js-verify moco-form-control w140 fl js-phoneCode" data-validate="require-string" data-minLength="4"  id="">\
				                    <div class="moco-control-tip errorHint color_red" data-error-hint="请输入4位验证码"></div>\
				                </div>\
				                <div class="yzmBox verify-img-wrap "><img class="verify-img"/><a href="javascript:void(0)" hidefocus="true" class="icon-refresh js-verify-refresh"></a></div>\
				            </div>\
				            <div class="moco-form-group">\
				                <label for="inputEmail3" class="moco-control-label"></label>\
				                <div class="moco-control-input">\
				                    <button class="moco-btn moco-btn-blue js-sf-submit">确定</button>\
				                     <button class="moco-btn moco-btn-normal js-colse">取消</button>\
				                	<p class="js-gerror tl g_error js-error"></p>\
				                </div>\
				            </div>\
	                    <button aria-hidden="true" hidefocus="true" data-dismiss="modal" class="btn-close" type="button"></button>\
				        </div>\
            		</div>';

        return str;
    }

  	
  	function showVerityCode(){
	        $('.js-verify-row').show();
	        $('.verify-img').attr('src', '/user/verifycode'+"?t=" + new Date().getTime()); 
  	}

    
	$(".js-change").click(function(){
		var changeType = $(this).attr("changeType");
        var m=$(".msg-layer");
        if(!m.length){
        	if(changeType == "email"){
            	$("body").append(usercheck(account));
        	}else{
        		$("body").append(usercheck(account_phone));
        	}
            showVerityCode()
            m=$(".msg-layer");
            $('.verify-img').attr('src', '/user/verifycode'+"?t=" + new Date().getTime()); 
            function sendEmail(){
            	if (!W.validate($(".msg-layer"))){
			            return;
			        }
                $(".msg-layer .js-error").html('');
                util.errortipshow($(".msg-layer input"), "");
                $(".msg-layer input").removeClass('inpt-error');
                
                if( $(".msg-layer .js-sf-submit").html() == '验证中...'){
                    return;
                }
                
                $(".msg-layer .js-sf-submit").html('验证中...')
                
                var c=$(".msg-layer .js-pwd").val();
                var verify =$(".msg-layer .js-verify").val();
         
				$.ajax({
					url:"/user/ajaxcheckpass",
					data:{
                        passwd:c,
                        verify:verify,

                	},
					dataType:"json",
					success:function(data){
                        
                        $(".msg-layer .js-sf-submit").html('确定')
						if(data.result==0){
							$(".modal-backdrop").click();

							// 判断是修改手机还是邮箱
							if(changeType == "email"){

								if(!g_changeEmailView){
								    g_changeEmailView = new changeEmailView ({
								        el: 'body',
								        mall: account});
								}
							    g_changeEmailView.render()
							}
							if(changeType == "phone"){

								if(!g_changePhoneView){
								    g_changePhoneView = new changePhoneView ({
								        el: 'body',
								    });
								}
							    g_changePhoneView.render()
							}

						}
						else{
							showVerityCode()
                            $(".msg-layer input").addClass('inpt-error').focus();
							$(".msg-layer .js-error").html(data.msg);
						}
					},
					error:function(){
                        $(".msg-layer .js-sf-submit").html('确定')
						$(".msg-layer .js-error").html("服务器错误，请稍后重试！");
					}
				})
            }
            m.on("shown",function(){
                $(".msg-layer .js-sf-submit").on("click",function(event) {
                	
                	sendEmail()
                });

                $(".msg-layer .js-colse").on("click",function(event) {
                	$(".modal-backdrop").click();
                });
                

                $(".msg-layer input").on("keydown",function(event){
		            switch (event.keyCode) {
		                case 13:
	                        sendEmail()
		                break;
		            }
                })
            }).on("hidden",function(){
                $(this).remove();
            });
        }
        m.modal("show");
    });
    
    function checkverity(value){
        validateCallback.rel = false;
        $.ajax({
            url:'/user/verifycheck',
            method:"get",
            async: false,
            data:{verify: value},
            dataType:"json",
            success:function(data){
                if(data.result==0){
                    validateCallback['errorHint'] = '';
                    validateCallback.rel = true;
                }
                else{
                    validateCallback['errorHint'] = data.msg;
                }
            },
            error:function(){
                validateCallback['errorHint'] = "网络错误";
               
            },
        })
    }

    validateCallback['checkverity'] = function(value){
        checkverity(value);
    }


	$(".js-verify").click(function(){
		
		var $this=$(this);
		if($this.text()=="正在发送...") return ;
		$this.text("正在发送...");
		$.ajax({
			url:"/user/verificationmail",
			dataType:"json",
			success:function(data){
				if(data.status==0){
					if(!g_yz_EmailView){
					    g_yz_EmailView = new yanzhengEmailView ({
					        el: 'body',
					        mall: account});
					}
				    g_yz_EmailView.render()
				    $this.text("立即验证");
				}
				else{
					$.alert('error',{info:data.msg})
					$this.text("立即验证");
				}
				
			},
			error:function(){
				$.alert('error',{info:'系统错误！'})
				$this.text("立即验证");
			}
		})

	});


	// 绑定手机



	
    

});
