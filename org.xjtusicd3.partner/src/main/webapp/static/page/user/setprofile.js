define(function(require, exports, module){
	require('common');
	require("/org.xjtusicd3.partner/static/component/base/placeholder/placeholder.js");
	require("/org.xjtusicd3.partner/static/component/base/util/validate_v2.js");
	var mbox=require("/org.xjtusicd3.partner/static/page/user/messagebox.js");
    
	var util = require("/org.xjtusicd3.partner/static/page/user/common/settingUitl.js");
	//prov city area plugin init;


	var mutex=(function(){ //互斥上传类型
		var m={};
		return {
			set:function(t,xhr){
				m.xhr&&m.xhr.abort();
				m.type=t;
				xhr&&(m.xhr=xhr);
			},
			is:function(t){
				return m.type===t;
			},
			get:function(){
				return m.type;
			}
		}
	})();

	var setAvators=(function(){
		var $ha,$a;
		//$ha=$("#header-avator img");
		$a=$(".avator-img");
		return function(src){
			$a.attr('data-portrait',src.imgname)
			$a.attr("src",src.imgpath);
			//$a.attr("src",src.replace(/(?:-\d*-\d*)?(\.\w+)$/,"-40-40$1"));
		}
	})();	

//	$(document).on("logined.sns",function(e){
//		$("#avator-btns").find("[data-sns=\""+mutex.get()+"\"]").trigger("click").siblings(".avator-sns-relogintip").remove();
//		e.preventDefault();
//	});
//
//	$(document).on("click",".avator-btn-fake",function(e){
//		$("#upload").click();
//	});
//
//	var t=0;
//	$(document).on("click","[data-sns]",function(e){
//		var $this=$(this),
//			type=$this.attr("data-sns");
//		mutex.set(type);
//
//		$.ajax({
//			url:"/user/getimage?type="+type+"_sid",
//			dataType:"json",
//			success:function(data){
//				if(!mutex.is(type)) return ;
//				if(data.result==1){
//					console.log('------')
//					console.log(data);
//					setAvators(data.data);
//				}
//				else if(data.result==3&&!$this.siblings(".avator-sns-relogintip").length){
//					$.alert('error',{info:'帐号过期，请重新登录'});
//				}
//				else{
//					$.alert('error',{info:data.msg});
//				}
//			},
//			error:function(){
//				$.alert('error',{info:'后端请求失败,请稍后重试！'});
//			}
//		})
//	});

	function winOpen(url){
		var left = (screen.width - 600) / 2,
			top = (screen.height - 400) / 2,
			url="/user/login"+url;
		window.open(url, '_blank', 'toolbar=no, directories=no, status=no, menubar=no, width=600, height=500, top='+top+', left='+left).focus();
	}
    
	$(function(){
		var num = 128-$(".js-sign").val().length;
    	if(num>=0){
    		$(".js-numCanInput").html('还可以输入'+num+'个字符');
    	}else{
    		num = 0-num;
    		$(".js-numCanInput").html('已超出<span class="color-red">'+num+'</span>个字符');
    	}
	})

    $(document).delegate('.js-sign','change',function(e){
    	var num = 128-$(".js-sign").val().length;
    	if(num>=0){
    		$(".js-numCanInput").html('还可以输入'+num+'个字符');
    	}else{
    		num = 0-num;
    		$(".js-numCanInput").html('已超出<span class="color-red">'+num+'</span>个字符');
    	}
	});

	$(document).delegate('.js-sign','keyup',function(e){
		//alert(22)
		var num = 128-$(".js-sign").val().length;
    	if(num>=0){
    		$(".js-numCanInput").html('还可以输入'+num+'个字符');
    	}else{
    		num = 0-num;
    		$(".js-numCanInput").html('已超出<span class="color-red">'+num+'</span>个字符');
    	}
	});
	$(document).delegate('.js-sign','keydown',function(e){
		//alert(22)
		var num = 128-$(".js-sign").val().length;
    	if(num>=0){
    		$(".js-numCanInput").html('还可以输入'+num+'个字符');
    	}else{
    		num = 0-num;
    		$(".js-numCanInput").html('已超出<span class="color-red">'+num+'</span>个字符');
    	}
	});



	!function(){
		var key,
			$img;
		$("#upload").on("change",function(e){
			if(!this.value) return ;
			mutex.set("local");
			this.form.submit();
		});
		$("#uploadtarget").on("load",function(){
			if(!mutex.is("local")) return ;
			var txt=$('#uploadtarget').contents().find('body').text();
			txt=$.parseJSON(txt);
			if(!txt.key){
				mbox.error("上传的图片无效，请重新上传");
				return ;
			}
			var data = {};
			console.log(txt);
			data.imgname = txt.key;
			data.imgpath = txt.imgpath;
			setAvators(data);
		});
	}();


	
	//avator change
	$(".js-avator-try").click(function(){
		mutex.set("try");
		$.getJSON("/user/randimage/","type=1",function(data){
			if(!mutex.is("try")) return ;
			if(data){
				//$img=$('.avator-inner img').attr("src",data.imgpath);
				setAvators(data);
			}
		});
	});




	// 以上是头像设置 以下是质料设置
	$('#province-select').change(function(){
		$('#city-select').text('').append("<option value='0'>选择城市 </option>");
		$('#area-select').text('').append("<option value='0'>选择区县 </option>");
		$.get('/user/ajaxchangeprov', 'id='+$(this).val(),function(data){
			if(data&&data.result==1){
				var $c=$("#city-select"),
					d=data.data,
					len=d.length,
					i=0;
					//$c.append("<option value='0'>选择城市 </option>");
					for(;i<len;i++){
						$c.append("<option value="+d[i].id+" >"+d[i].name+ " </option>");
					}
			}
		},'json')
	});
	
	$('#city-select').change(function(){
		$('#area-select').text('').append("<option value='0'>选择区县 </option>");
		$.get('/user/ajaxchangecity', 'id='+$(this).val(),function(data){
			if(data&&data.result==1){
				var $c=$("#area-select"),
					d=data.data,
					len=d.length,
					i=0;
					//$c.append("<option value='0'>选择区县 </option>");
					for(;i<len;i++){
						$c.append("<option value="+d[i].id+" >"+d[i].name+ " </option>");
					}
			}

		},'json')
	});


	function formOnerror(e){
		var $t,$d;
		if(e._relateField&&e.tip){
			$t=$(e._relateField);
			$t.addClass("rlf-field-error").next(".rlf-tip-wrap").html(e.tip).addClass("rlf-tip-error");
			($d=$t.data("placeholder-textinput"))&&$d.addClass("rlf-field-error");
		}
	}

	function formOnvalid(e){
		var $t,$d;
		if(e._relateField){
			$t=$(e._relateField);
			$t.removeClass("rlf-field-error").next(".rlf-tip-wrap").removeClass("rlf-tip-error").empty();
			($d=$t.data("placeholder-textinput"))&&$d.removeClass("rlf-field-error");
		}
	}

	// $("#profile").validateSetup({
	// 	fields:{
	// 		"aboutme":{
	// 			rules:[{
	// 				rule:function(cb,v){
	// 					if(v.length>128){
	// 						return "个性签名不能超过128个字符！";
	// 					}
	// 				}
	// 			}]
	// 		}
	// 	},
	// 	onerror:formOnerror,
	// 	onvalid:formOnvalid
	// });
    
 //    $("#job").change(function(){
	// 	if(!!$(this).val()){
	// 		formOnvalid.call(this,{_relateField:this});
	// 	}
	// 	else{
	// 		formOnerror.call(this,{_relateField:this,tip:"请选择职位！"});
	// 	}
	// });

	$("#profile-submit").click(function(){
		var num = 128-$(".js-sign").val().length;
    	if(num>=0){
    	}else{
    		return;
    	}
		var $this=$(this),$form;
		if($this.text()=='正在保存...'){ return;}
		$this.text("正在保存...");

        // if( $('.rlf-select').val() == '' ) {
        //     $this.text("保存");
        //     $('.rlf-select').next('.rlf-tip-wrap').addClass('rlf-tip-error').show().html('请选择职位！')
        //     return;
        // }
        
        $form=$this.closest("form");	

         if(W.validate()){
         		var postData={
					type:1  
				};
				postData.job = $("#job").val();
				postData.sex = $("#profile input[name='sex']:checked").val();

				
				postData.portrait=$("#js-portrait").attr('data-portrait');
				postData.nickname=$("#nick").val();
				postData.about=$("#aboutme").val();
				postData.province=$("#province-select").val();
				postData.city=$("#city-select").val();
				postData.area=$("#area-select").val();
				$.ajax({
					url:"/user/ajaxsetinfo",
					data:postData,
					method:"post",
					dataType:"json",
					success:function(data){
						if(data.result==1){
							window.location.reload();
						}
						else{
							$.alert('error',{info:data.msg})
						}
						$this.text("保存");
					},
					error:function(){
						$.alert('error',{info:'修改失败！'})
						$this.text("保存");
					}
				});
         }else{
         	$this.text("保存");
         }
        
		// $form.validate({
		// 	success:function(vals){
                                
		// 		var postData={
		// 			type:1  
		// 		};
		// 		postData.job = $("#job").val();
		// 		postData.sex = $("#profile input[name='sex']:checked").val();

		// 		postData.nickname=vals.nickname;
		// 		postData.about=vals.aboutme;
		// 		postData.province=$("#province-select").val();
		// 		postData.city=$("#city-select").val();
		// 		postData.area=$("#area-select").val();
		// 		$.ajax({
		// 			url:"/user/ajaxsetinfo",
		// 			data:postData,
		// 			method:"post",
		// 			dataType:"json",
		// 			success:function(data){
		// 				if(data.result==1){
		// 					util.layerInfo("修改成功！");
		// 				}
		// 				else{
		// 					util.layerError(data.msg||data.data);
		// 				}
		// 				$this.text("保存");
		// 			},
		// 			error:function(){
		// 				util.layerError("修改失败！");
		// 				$this.text("保存");
		// 			}
		// 		});

		// 	},
		// 	error:function(){
		// 		$this.text("保存");
		// 	}
		// });


	});


});



