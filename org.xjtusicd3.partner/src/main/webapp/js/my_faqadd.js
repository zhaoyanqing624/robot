$(document).ready(
   function(){
         $.ajax({
             type: "GET",
             url: "/org.xjtusicd3.partner/getFirstLevel.html",            
             dataType: "json",
             success: function(data){            
     			 for(var i in data){ 
     			 	 document.getElementById("specialCategoryId").options.add(new Option(data[i].fAQCLASSIFYNAME, data[i].fAQCLASSIFYID));					        
			      }                                                                      
             }
         });
    })
    
function selectSecondChild(){
var element = document.getElementById("specialCategoryId");
var classifyId = element.options[element.selectedIndex].value;
$.ajax({
     type: "GET",
     url: "/org.xjtusicd3.partner/getSecondLevel.html"+"?"+"classifyId="+classifyId,            
     dataType: "json",
     success: function(data){
     			 document.getElementById("subspecialCategoryId").options.length=0;              	
     			 for(var i in data){ 
     			 	 document.getElementById("subspecialCategoryId").options.add(new Option(data[i].fAQCLASSIFYNAME, data[i].fAQCLASSIFYID));					        
				      }                                                                      
                  }
     });         
}


window.onload=function(){
	$("#title").focus()
}
$("input").blur(function(){
	$(".validate_faqadd").css("color","#BD362F")
	if($(this).is("#title")){				//faq标题判断
		var na = /^\S{2,44}$/
		if($("#title").val()!=""){
			if(!(na.test($("#title").val()))){
				$(".spa1").text("请输入2-44个字符");
				$(this).css("border","1px solid #BD362F")
				return false;
			}else if(na){
				$(".spa1").text("");
				return true;
			}
		}else{
			$(".spa1").text("");
		}
	}
	if($(this).is("#keywords")){            //关键词判断
		var kw= /^\S{2,30}$/
		if($("#userkwone").val()!=""){
		if(!(kw.test($("#keywords").val()))){
			$(".spa3").text("请输入2-30个字符");
			$(this).css("border","1px solid #BD362F")
			return false;
		}else if(kw){
			$(".spa3").text("");
			return true;
		}
		}else{
			$(".spa3").text("");
		}
	}
})

$("textarea").blur(function(){
	$(".validate_faqadd").css("color","#BD362F")
	if($(this).is("#description")){				//简介的判断
		var dp = /^\S{2,100}$/
		if($("#description").val()!=""){
			if(!(dp.test($("#description").val()))){
				$(".spa5").text("请输入2-100个字符");
				$(this).css("border","1px solid #BD362F")
				return false;
			}else if(dp){
				$(".spa5").text("");
				return true;
			}
		}else{
			$(".spa5").text("");
		}
	}
	if($(this).is("#risk_prompt")){				//风险的判断
		var rp = /^\S{0,100}$/
		if($("#risk_prompt").val()!=""){
			if(!(rp.test($("#risk_prompt").val()))){
				$(".spa6").text("请输入至多100个字符");
				$(this).css("border","1px solid #BD362F")
				return false;
			}else if(rp){
				$(".spa6").text("");
				return true;
			}
		}else{
			$(".spa6").text("");
		}
	}
})

$("input").focus(function(){
	if($(this).is("#title")){
		$(".spa1").text("不超过44个字符").css("color","#aaa")
		$(this).css("border","1px solid #aaa")
	}
	if($(this).is("#keywords")){
		$(".spa3").text("输入关键词用逗号隔开").css("color","#aaa")
		$(this).css("border","1px solid #aaa")
	}
})

$("textarea").focus(function(){
	if($(this).is("#description")){
		$(".spa5").text("不超过100个字符").css("color","#aaa")
		$(this).css("border","1px solid #aaa")
	}
	if($(this).is("#risk_prompt")){
		$(".spa6").text("不超过100个字符").css("color","#aaa")
		$(this).css("border","1px solid #aaa")
	}
})

$("#sub").click(function(){
	$(".spa2").text("");
	$(".spa4").text("");
	var na = /^\S{2,44}$/   
	var kw = /^\S{2,30}$/  
	var dp = /^\S{2,100}$/
	if(na.test($("#title").val())&&kw.test($("#keywords").val())&&$("#subspecialCategoryId").val&&dp.test($("#description").val())){
			var title = document.getElementById("title").value;
			var keywords = document.getElementById("keywords").value;
			var subspecialCategoryId = document.getElementById("subspecialCategoryId").value;
			var description = document.getElementById("description").value;
			var risk_prompt = document.getElementById("risk_prompt").value;
			var faqcontent = UE.getEditor('editor').getContent();
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/saveFAQ.html",
				data:{
					"title":title,
					"keywords":keywords,
					"subspecialCategoryId":subspecialCategoryId,
					"description":description,
					"risk_prompt":risk_prompt,
					"faqcontent":faqcontent
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}else if(data.value=="1"){
						(function() {
							var dlgtrigger = document.querySelector( '[data-dialog]' ),
								somedialog = document.getElementById( dlgtrigger.getAttribute( 'data-dialog' ) ),
								dlg = new DialogFx( somedialog );
							dlgtrigger.addEventListener( 'click', dlg.toggle.bind(dlg) );
						})();
						document.getElementById('lasturl').innerHTML=data.url;
					}else{
						(function() {
							var dlgtrigger = document.querySelector( '[data-dialog]' ),
								somedialog = document.getElementById( dlgtrigger.getAttribute( 'data-dialog' ) ),
								dlg = new DialogFx( somedialog2 );
							dlgtrigger.addEventListener( 'click', dlg.toggle.bind(dlg) );
						})();
						document.getElementById('lasturl').innerHTML=data.url;
					}
				}
			})
		return true;
	}else{
		if($("#title").val()==""){
			$(".spa1").text('请您填写标题')
		}
		if($('input:radio[name="resource"]:checked').val()==null){
			$(".spa2").text('请您选择类型')
		}
		if($("#keywords").val()==""){
			$(".spa3").text('请您填写关键词')
		}
		if($("#subspecialCategoryId").val()==null){
			$(".spa4").text('请您选择知识分类')
		} 
		if($("#description").val()==""){
			$(".spa5").text('请您填写简单描述')
		}
		return false;
	}
})



