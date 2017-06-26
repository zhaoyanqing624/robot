$(document).ready(
function(){
	 $.ajax({
	 type: "GET",
	 url: "/org.xjtusicd3.partner/getFirstLevel.html",            
	 dataType: "json",
	 success: function(data){
	 		for(var i in data){
	 		    var a = data[i].fAQCLASSIFYID;
	 		    var b = data[i].fAQCLASSIFYNAME;
	 		    var list = document.getElementById("konwledge-first");
	 		    var html = list.innerHTML;
	 		    var color = new Array("default","orange","pink","red","blue","green","gray","black","default","orange","pink","red","blue","green","gray","black","default","orange","pink","red","blue","green","gray","black");
	 		    list.innerHTML=html+'<li class="'+color[i]+'"><a href="/org.xjtusicd3.partner/faq1.html?p='+a+'">'+b+'</a></li>';
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


