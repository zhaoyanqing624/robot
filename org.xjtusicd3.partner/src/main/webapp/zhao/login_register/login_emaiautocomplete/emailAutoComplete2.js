/*
 * 邮箱自动补全
 */
var nowid;
var totalid;
var can1press = false;
var emailafter;
var emailbefor;
$(document).ready(function(){ 
 $("#me2").focus(function(){ //文本框获得焦点，插入Email提示层
 $("#myemail2").remove();
 $(this).after("<div id='myemail2' style='width:84%; height:auto; background:#fff; color:#6B6B6B; position:absolute; left:9.5%; top:57%; border:1px solid #ccc;z-index:5px; '></div>");
 if($("#myemail2").html()){
  $("#myemail2").css("display","block");
 $(".newemail2").css("width",$("#myemail2").width());
  can1press = true;
 } else {
  $("#myemail2").css("display","none");
  can1press = false;
 }  
 }).keyup(function(){ //文本框输入文字时，显示Email提示层和常用Email
  var press = $("#me2").val();
  if (press!="" || press!=null){
  var emailtxt = "";   
  var emailvar = new Array("@qq.com","@163.com","@126.com","@yahoo.com","@sina.com","@gmail.com","@hotmail.com","@foxmail.com","@sohu.com");
  totalid = emailvar.length;
   var emailmy = "<div class='newemail2' style='width:84%; color:#6B6B6B; overflow:hidden;'><font color='#D33022'>" + press + "</font></div>";
   if(!(isEmail(press))){
    for(var i=0; i<emailvar.length; i++) {
     emailtxt = emailtxt + "<div class='newemail2' style='width:84%; color:#6B6B6B; overflow:hidden;'><font color='#D33022'>" + press + "</font>" + emailvar[i] + "</div>"
    }
   } else {
    emailbefor = press.split("@")[0];
    emailafter = "@" + press.split("@")[1];
    for(var i=0; i<emailvar.length; i++) {
     var theemail = emailvar[i];
     if(theemail.indexOf(emailafter) == 0)
     {
      emailtxt = emailtxt + "<div class='newemail2' style='width:84%; color:#6B6B6B; overflow:hidden;'><font color='#D33022'>" + emailbefor + "</font>" + emailvar[i] + "</div>"
     }
    }
   }
   $("#myemail2").html(emailmy+emailtxt);
   if($("#myemail2").html()){
     $("#myemail2").css("display","block");
     $(".newemail2").css("width",$("#myemail2").width());
     can1press = true;
   } else {
     $("#myemail2").css("display","none");
     can1press = false;
   }
   beforepress = press;
  }
  if (press=="" || press==null){
   $("#myemail2").html("");  
   $("#myemail2").css("display","none"); 
  }
 })
 $(document).click(function(){ //文本框失焦时删除层
 if(can1press){
   $("#myemail2").remove();
   can1press = false;
   if($("#me2").focus()){
    can1press = false;
   }
  }
 })
 $(".newemail2").live("mouseover",function(){ //鼠标经过提示Email时，高亮该条Email
 $(".newemail2").css("background","#FFF");
 $(this).css("background","#CACACA");
  $(this).focus();
  nowid = $(this).index();
 }).live("click",function(){ //鼠标点击Email时，文本框内容替换成该条Email，并删除提示层
 var newhtml = $(this).html();
 newhtml = newhtml.replace(/<.*?>/g,"");
 $("#me2").val(newhtml);
 _email1();
 $("#myemail2").remove();
 })
 $(document).bind("keydown",function(e)
 {
  if(can1press){
   switch(e.which) 
   {
    case 38:
    if (nowid > 0){
     $(".newemail2").css("background","#FFF");
     $(".newemail2").eq(nowid).prev().css("background","#CACACA").focus();
     nowid = nowid-1;
    }
    if(!nowid){
     nowid = 0;
     $(".newemail2").css("background","#FFF");
     $(".newemail2").eq(nowid).css("background","#CACACA");  
     $(".newemail2").eq(nowid).focus();    
    }
    break; 
    case 40:
    if (nowid < totalid){
     $(".newemail2").css("background","#FFF");
     $(".newemail2").eq(nowid).next().css("background","#CACACA").focus(); 
     nowid = nowid+1;
    }
    if(!nowid){
     nowid = 0;
     $(".newemail2").css("background","#FFF");
     $(".newemail2").eq(nowid).css("background","#CACACA");  
     $(".newemail2").eq(nowid).focus();    
    }
    break; 
    case 13:
    var newhtml = $(".newemail2").eq(nowid).html();
    newhtml = newhtml.replace(/<.*?>/g,"");
    $("#me2").val(newhtml); 
    $("#myemail2").remove();
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
function _email1() {
    var x = document.getElementById("me2").value;
    var na = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
	if($("#me2").val()!=""){
		if(!(na.test($("#me2").val()))){
			$(".spa5").text("请仔细检查您的邮箱");
			return false;
		}else if(na){
			$(".spa5").text("");
			return true;
		}
	}else{
		$(".spa5").text("");
	}
}
function _password1(){
	if($("#password2").val()!=""){
		var pw = /^\w{6,16}$/;
		if(!(pw.test($("#password2").val()))){
			$(".spa6").text("密码由字母、数字、下划线组成且6-16位");
			return false;
		}else if (pw){
			$(".spa6").text("");
			return true;
		}
	}else{
		$(".spa6").text("");
	}
}
//$("input").blur(function(){
//	if($(this).is("#me2")){
//		var na = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
//		if($("#me2").val()!=""){
//			if(!(na.test($("#me2").val()))){
//				$(".spa5").text("请仔细检查您的邮箱");
//				return false;
//			}else if(na){
//				$(".spa5").text("");
//				return true;
//			}
//		}else{
//			$(".spa5").text("");
//		}
//	}
//	if($(this).is("#password2")){
//		var pw = /^\w{6,16}$/;
//		if(!(pw.test($("#password2").val()))){
//			$(".spa6").text("密码由字母、数字、下划线组成且6-16位");
//			return false;
//		}else if (pw){
//			$(".spa6").text("");
//			return true;
//		}
//	}else{
//		$(".spa6").text("");
//	}
//})
$("#login").click(function(){
	var na = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
	var pw = /^\w{6,16}$/;
	if(na.test($("#me2").val())&&pw.test($("#password2").val())){
		$.ajax({
			type:"POST",
			url:"/org.xjtusicd3.partner/saveLogin.html",
			data:{
				"email":$("#me2").val(),
				"password":$("#password2").val()
			},
			dataType:"json",
			success:function(data){
				if(data=="1"){
					$(".spa5").text('帐号或密码错误');
				}
			}
		})

		return true;
	}else{
		if($("#me2").val()==""){
			$(".spa5").text('请填写注册的邮箱');
		}

		if($("#password2").val()==""){
			$(".spa6").text('请填写密码');
		}
		return false;
	}
})
