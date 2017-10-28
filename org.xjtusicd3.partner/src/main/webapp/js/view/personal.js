$("#date03").jeDate({
    isinitVal:true,
    festival:true,
    ishmsVal:false,
    minDate: '1900-01-01 00:00:00',
    maxDate: $.nowDate(0),
    format:"YYYY-MM-DD",
    zIndex:3000,
});
		
$("#update_").click(function(){
	$("#subinfo").css('display','none');
	$("#updateinfo").css('display','block');	
});

$("#submit_").click(function(){
	$("#subinfo").css('display','block');
	$("#updateinfo").css('display','none');
});
		
$("#submitupdatePassword").click(function(){
	var pw = /^\w{6,16}$/;
	var rpw = $("#repassword2").val();
	if(pw.test($("#password").val())&&pw.test($("#password2").val())&&(rpw==($("#password2").val()))){
	$.ajax({
		type:"POST",
		url:"/org.xjtusicd3.partner/updateUserPassword.html",
		data:{
			"password":$("#password").val(),
			"password2":$("#password2").val(),
			"repassword2":$("#repassword2").val()
		},
		dataType:"json",
		success:function(data){
			if(data=="0"){
				$(".spa2").text('新密码不能与原密码相同');
			}if(data=="1"){
				$(".spa1").text('原密码错误');
				document.getElementById("modal_bg").style.display="none";
				document.getElementById("myModal").style.display="none";
			}if(data=="2"){
				document.getElementById("modal_bg").style.display="block";
				document.getElementById("myModal").style.display="";
				document.getElementById("myModal").style.visibility="visible";
			}
		}
		})
		return true;
	}else{
	if($("#password").val()==""){
		$(".spa1").text('请填写密码');
	}
	if($("#password2").val()==""){
		$(".spa2").text('请填写新密码');
	}
	if($("#repassword2").val()==""){
		$(".spa3").text('请再次填写密码');
		}
		return false;
	}
});

function _password(){
	if($("#password").val()!=""){
		var pw = /^\w{6,16}$/;
		if(!(pw.test($("#password").val()))){
			$(".spa1").text("密码由字母、数字、下划线组成且6-16位");
			return false;
		}else if (pw){
			$(".spa1").text("");
			return true;
		}
	}else{
		$(".spa1").text("");
	}
}

function _password2(){
	if($("#password2").val()!=""){
		var pw = /^\w{6,16}$/;
		if(!(pw.test($("#password2").val()))){
			$(".spa2").text("密码由字母、数字、下划线组成且6-16位");
			return false;
		}else if (pw){
			$(".spa2").text("");
			return true;
		}
	}else{
		$(".spa2").text("");
	}
}

function _repassword2(){
	if($("#repassword2").val()!=""){
		var pw = $("#password2").val();
		if(!(pw==$("#repassword2").val())){
			$(".spa3").text("两次输入的密码不一致");
			return false;
		}else if (pw){
			$(".spa3").text("");
			return true;
		}
	}else{
		$(".spa3").text("");
	}
}

//取消弹出框
$("#cancelModal").click(function(){
	document.getElementById("modal_bg").style.display="none";
	document.getElementById("myModal").style.visibility = "hidden";
	window.location.reload(); 
})