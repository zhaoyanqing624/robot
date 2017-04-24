/*
 * 邮箱自动补全
 */
var nowid;
var totalid;
var can1press = false;
var emailafter;
var emailbefor;
$(document).ready(function(){ 
 $("#me").focus(function(){ //文本框获得焦点，插入Email提示层
 $("#myemail").remove();
 $(this).after("<div id='myemail' style='width:84%; height:auto; background:#fff; color:#6B6B6B; position:absolute; left:9.5%; top:38%; border:1px solid #ccc;z-index:5px; '></div>");
 if($("#myemail").html()){
  $("#myemail").css("display","block");
 $(".newemail").css("width",$("#myemail").width());
  can1press = true;
 } else {
  $("#myemail").css("display","none");
  can1press = false;
 }  
 }).keyup(function(){ //文本框输入文字时，显示Email提示层和常用Email
  var press = $("#me").val();
  if (press!="" || press!=null){
  var emailtxt = "";   
  var emailvar = new Array("@qq.com","@163.com","@126.com","@yahoo.com","@sina.com","@gmail.com","@hotmail.com","@foxmail.com","@sohu.com");
  totalid = emailvar.length;
   var emailmy = "<div class='newemail' style='width:84%; color:#6B6B6B; overflow:hidden;'><font color='#D33022'>" + press + "</font></div>";
   if(!(isEmail(press))){
    for(var i=0; i<emailvar.length; i++) {
     emailtxt = emailtxt + "<div class='newemail' style='width:84%; color:#6B6B6B; overflow:hidden;'><font color='#D33022'>" + press + "</font>" + emailvar[i] + "</div>"
    }
   } else {
    emailbefor = press.split("@")[0];
    emailafter = "@" + press.split("@")[1];
    for(var i=0; i<emailvar.length; i++) {
     var theemail = emailvar[i];
     if(theemail.indexOf(emailafter) == 0)
     {
      emailtxt = emailtxt + "<div class='newemail' style='width:84%; color:#6B6B6B; overflow:hidden;'><font color='#D33022'>" + emailbefor + "</font>" + emailvar[i] + "</div>"
     }
    }
   }
   $("#myemail").html(emailmy+emailtxt);
   if($("#myemail").html()){
     $("#myemail").css("display","block");
     $(".newemail").css("width",$("#myemail").width());
     can1press = true;
   } else {
     $("#myemail").css("display","none");
     can1press = false;
   }
   beforepress = press;
  }
  if (press=="" || press==null){
   $("#myemail").html("");  
   $("#myemail").css("display","none"); 
  }
 })
 $(document).click(function(){ //文本框失焦时删除层
 if(can1press){
   $("#myemail").remove();
   can1press = false;
   if($("#me").focus()){
    can1press = false;
   }
  }
 })
 $(".newemail").live("mouseover",function(){ //鼠标经过提示Email时，高亮该条Email
 $(".newemail").css("background","#FFF");
 $(this).css("background","#CACACA");
  $(this).focus();
  nowid = $(this).index();
 }).live("click",function(){ //鼠标点击Email时，文本框内容替换成该条Email，并删除提示层
 var newhtml = $(this).html();
 newhtml = newhtml.replace(/<.*?>/g,"");
 $("#me").val(newhtml);
 $("#myemail").remove();
 })
 $(document).bind("keydown",function(e)
 {
  if(can1press){
   switch(e.which) 
   {
    case 38:
    if (nowid > 0){
     $(".newemail").css("background","#FFF");
     $(".newemail").eq(nowid).prev().css("background","#CACACA").focus();
     nowid = nowid-1;
    }
    if(!nowid){
     nowid = 0;
     $(".newemail").css("background","#FFF");
     $(".newemail").eq(nowid).css("background","#CACACA");  
     $(".newemail").eq(nowid).focus();    
    }
    break; 
    case 40:
    if (nowid < totalid){
     $(".newemail").css("background","#FFF");
     $(".newemail").eq(nowid).next().css("background","#CACACA").focus(); 
     nowid = nowid+1;
    }
    if(!nowid){
     nowid = 0;
     $(".newemail").css("background","#FFF");
     $(".newemail").eq(nowid).css("background","#CACACA");  
     $(".newemail").eq(nowid).focus();    
    }
    break; 
    case 13:
    var newhtml = $(".newemail").eq(nowid).html();
    newhtml = newhtml.replace(/<.*?>/g,"");
    $("#me").val(newhtml); 
    $("#myemail").remove();
   }
  } 
 })
}) 
//检查email邮箱
function isEmail(str){
 if(str.indexOf("@") > 0) 
 { 
 return true;
 } else {
 return false;
 }
}

/*
 * 
 */
window.onload=function(){
	$("#me").focus();
}
$("input").blur(function(){
	if($(this).is("#me")){
		var na = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
		if($("#me").val()!=""){
			if(!(na.test($("#me").val()))){
				$(".spa1").text("请仔细检查您的邮箱");
				return false;
			}else if(na){
				$(".spa1").text("");
				return true;
			}
		}else{
			$(".spa1").text("");
		}
	}
	if($(this).is("#user")){
		var us = /^\w{2,10}$/;
		if(!(us.test($("#user").val()))){
			$(".spa2").text("用户名不含特殊字符且2-10位");
			return false;
		}else if (us){
			$(".spa2").text("");
			return true;
		}
	}else{
		$(".spa2").text("");
	}
	if($(this).is("#password")){
		var pw = /^\w{6,16}$/;
		if(!(pw.test($("#password").val()))){
			$(".spa3").text("密码由字母、数字、下划线组成且6-16位");
			return false;
		}else if (pw){
			$(".spa3").text("");
			return true;
		}
	}else{
		$(".spa3").text("");
	}
	if($(this).is("#repassword")){
		var rpw = $("#repassword").val();
		if(!(rpw==($("#password").val()))){
			$(".spa4").text("两次输入的密码不一致");
			return false;
		}else if(rpw){
			$(".spa4").text("");
			return true;
		}
	}else{
		$(".spa4").text("");
	}
})

$("input").focus(function(){
	$('#password').keyup(function (){
		document.getElementById("_password").innerHTML="<tr><th></th><div id='level' class='pw-strength'><div class='pw-bar'></div><div class='pw-bar-on'></div><div class='pw-txt'><span>弱</span><span>中</span><span>强</span></div></div></tr>";
		var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g"); 
		var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g"); 
		var enoughRegex = new RegExp("(?=.{6,}).*", "g");
		if (false == enoughRegex.test($("#password").val())) {
			$('#level').removeClass('pw-weak'); 
			$('#level').removeClass('pw-medium'); 
			$('#level').removeClass('pw-strong'); 
			$('#level').addClass(' pw-defule'); 
			 //密码小于六位的时候，密码强度图片都为灰色 
		} 
		else if (strongRegex.test($("#password").val())) { 
			$('#level').removeClass('pw-weak'); 
			$('#level').removeClass('pw-medium'); 
			$('#level').removeClass('pw-strong'); 
			$('#level').addClass(' pw-strong'); 
			 //密码为八位及以上并且字母数字特殊字符三项都包括,强度最强 
		} 
		else if (mediumRegex.test($("#password").val())) { 
			$('#level').removeClass('pw-weak'); 
			$('#level').removeClass('pw-medium'); 
			$('#level').removeClass('pw-strong'); 
			$('#level').addClass(' pw-medium'); 
			 //密码为七位及以上并且字母、数字、特殊字符三项中有两项，强度是中等 
		} 
		else { 
			$('#level').removeClass('pw-weak'); 
			$('#level').removeClass('pw-medium'); 
			$('#level').removeClass('pw-strong'); 
			$('#level').addClass('pw-weak'); 
			 //如果密码为6为及以下，就算字母、数字、特殊字符三项都包括，强度也是弱的 
		} 
		return true; 
	})
})
$("#register").click(function(){
	var na = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
	var us = /^\w{2,10}$/;
	var pw = /^\w{6,16}$/;
	var rpw = $("#repassword").val();
	if(na.test($("#me").val())&&us.test($("#user").val())&&pw.test($("#password").val())&&(rpw==($("#password").val()))){
		$.ajax({
			type:"POST",
			url:"/org.xjtusicd3.partner/saveRegister.html",
			data:{
				"email":$("#me").val(),
				"username":$("#user").val(),
				"password":$("#password").val()
			},
			dataType:"json",
			success:function(data){
				if(data=="1"){
					$(".spa1").text('该邮箱已被注册');
				}else{
					document.getElementById("modal_bg").style.display="block";
					document.getElementById("myModal").style.visibility = "visible";
				}
			}
		})

		return true;
	}else{
		if($("#me").val()==""){
			$(".spa1").text('请填写注册的邮箱');
		}
		if($("#user").val()==""){
			$(".spa2").text('请填写用户名');
		}
		if($("#password").val()==""){
			$(".spa3").text('请填写密码');
		}
		if($("#repassword").val()==""){
			$(".spa4").text('请再次填写密码');
		}
		return false;
	}
})
//取消弹出框
$("#cancelModal").click(function(){
	document.getElementById("modal_bg").style.display="none";
	document.getElementById("myModal").style.visibility = "hidden";
	window.location.reload(); 
})