$(document).ready(
function(){
	 $.ajax({
	 type: "GET",
	 url: "/org.xjtusicd3.partner/getFirstLevel.html",            
	 dataType: "json",
	 success: function(data){
	 		for(var i in data){
	 		    var a = data[i].classifyId;
	 		    var b = data[i].classifyName;
	 		    var list = document.getElementById("konwledge-first");
	 		    var html = list.innerHTML;
	 		    if(a<=8){
	     			if(a%8==1){
	     				list.innerHTML=html+'<li class="default"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}else if(a%8==2){
	     				list.innerHTML=html+'<li class="orange"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}else if(a%8==3){
	     				list.innerHTML=html+'<li class="pink"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}else if(a%8==4){
	     				list.innerHTML=html+'<li class="red"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}else if(a%8==5){
	     				list.innerHTML=html+'<li class="blue"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}else if(a%8==6){
	     				list.innerHTML=html+'<li class="green"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}else if(a%8==7){
	     				list.innerHTML=html+'<li class="gray"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}else if(a%8==0){
	     				list.innerHTML=html+'<li class="black"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}
	 		    }else{
	     			if(a%8==1){
	     				list.innerHTML=html+'<li class="default"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}else if(a%8==2){
	     				list.innerHTML=html+'<li class="orange"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}else if(a%8==3){
	     				list.innerHTML=html+'<li class="pink"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}else if(a%8==4){
	     				list.innerHTML=html+'<li class="red"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}else if(a%8==5){
	     				list.innerHTML=html+'<li class="blue"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}else if(a%8==6){
	     				list.innerHTML=html+'<li class="green"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}else if(a%8==7){
	     				list.innerHTML=html+'<li class="gray"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	     			}else if(a%8==0){
	     				list.innerHTML=html+'<li class="black"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
	         			}
	     		    }
	          }
	      }
	 });
})
function getData(a){
	var str =a;
	str = str.replace(/-/g,"/");
	var date = new Date(str);
	return date;
}

function showAddGuanzhu(){
	document.getElementById("weiguanzhu").style.display="block";
};
function hideAddGuanzhu(){
	document.getElementById("weiguanzhu").style.display="none";
}
function showGuanzhu(){
	document.getElementById("yiguanzhu").style.display="block";
}
function hideGuanzhu(){
	document.getElementById("yiguanzhu").style.display="none";
}
function guanzhu(){
	document.getElementById("yiguanzhu0").style.display="block";
	document.getElementById("weiguanzhu0").style.display="none";
}
function quguanzhu(){
	document.getElementById("yiguanzhu0").style.display="none";
	document.getElementById("weiguanzhu0").style.display="block";
}

function zhankai() {
	zhankaiall.outerHTML = '<a onclick="shouqi()" style="color:blue;" id="shouqiall">收起</a>';
	buding.style.height = "auto";
}
function shouqi() {
	shouqiall.outerHTML = '<a onclick="zhankai()" style="color:blue;" id="zhankaiall">展开</a>';
	buding.style.height = "78px";
}
function zhankai2(){
	zhankaiall2.outerHTML='<a onclick="shouqi2()" style="color:blue;" id="shouqiall2">收起</a>';
	ruanjian.style.height="auto";
}
function shouqi2(){
	shouqiall2.outerHTML='<a onclick="zhankai2()" style="color:blue;" id="zhankaiall2">展开</a>';
	ruanjian.style.height="78px";
}
function c_index(){
	c_index.outerHTML='<a onclick="c_index()" class="active" id="c_index"><i class="fa fa-home"></i><span>主&nbsp;&nbsp;&nbsp;&nbsp;页</span><b class="fa fa-angle-right"></b></a>';
	c_know.outerHTML='<a onclick="c_know()"  id="c_know"><i class="fa fa-lightbulb-o"></i><span>知识库</span><b class="fa fa-angle-right"></b></a>';
	c_question.outerHTML='<a onclick="c_question()"  id="c_question"><i class="fa fa-question"></i><span>问&nbsp;&nbsp;&nbsp;&nbsp;吧</span><b class="fa fa-angle-right"></b></a>';
	zhao1.style.display="block";
	zhao2.style.display="none";
	zhao3.style.display="none";
}
function c_know(){
	c_index.outerHTML='<a onclick="c_index()"  id="c_index"><i class="fa fa-home"></i><span>主&nbsp;&nbsp;&nbsp;&nbsp;页</span><b class="fa fa-angle-right"></b></a>';
	c_know.outerHTML='<a onclick="c_know()"  class="active" id="c_know"><i class="fa fa-lightbulb-o"></i><span>知识库</span><b class="fa fa-angle-right"></b></a>';
	c_question.outerHTML='<a onclick="c_question()"  id="c_question"><i class="fa fa-question"></i><span>问&nbsp;&nbsp;&nbsp;&nbsp;吧</span><b class="fa fa-angle-right"></b></a>';
	zhao2.style.display="block";
	zhao1.style.display="none";
	zhao3.style.display="none";
}
function c_question(){
	c_index.outerHTML='<a onclick="c_index()"  id="c_index"><i class="fa fa-home"></i><span>主&nbsp;&nbsp;&nbsp;&nbsp;页</span><b class="fa fa-angle-right"></b></a>';
	c_know.outerHTML='<a onclick="c_know()"  id="c_know"><i class="fa fa-lightbulb-o"></i><span>知识库</span><b class="fa fa-angle-right"></b></a>';
	c_question.outerHTML='<a onclick="c_question()"  class="active" id="c_question"><i class="fa fa-question"></i><span>问&nbsp;&nbsp;&nbsp;&nbsp;吧</span><b class="fa fa-angle-right"></b></a>';
	zhao3.style.display="block";
	zhao1.style.display="none";
	zhao2.style.display="none";
}


