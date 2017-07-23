var pagenow = 1;
function queryMoreTop(){ 
	var classifyId = $("#secondNavStep").attr("value");
		$.ajax({
			type:"post",
			url:"/org.xjtusicd3.partner/getMoreFaqList.html",
			data:{
				"pagenow":pagenow,
				"classifyId":classifyId
			},
			dataType:"json",
			success:function(data){
				for(var i in data.faqlist){
					var html = document.getElementById("secondListtWrapper").innerHTML;
					var time = data.faqlist[i].faqModifytime.substring(0,10).replace(/-/,'/');
					document.getElementById("secondListtWrapper").innerHTML = html+ '<ul class="knowledgeList"><li><p class="title"><a href="faq3.html?q='+data.faqlist[i].questionId+'" target="_blank">'+data.faqlist[i].faqTitle+'</a><span class="tags undefined"></span></p></li><li class="clearfix"><span class="userPic"><img src="'+data.faqlist[i].uList[0].userImage+'"></span><span class="username">'+data.faqlist[i].uList[0].userName+'</span><span class="dot">-</span><span class="time">'+time+'</span><span class="line">|</span><span class="showCount">'+data.faqlist[i].faqScan+'</span><span class="message">'+data.faqlist[i].commentNumber+'</span><span class="collection">'+data.faqlist[i].faqCollection+'</span></li><li class="content">'+data.faqlist[i].faqDescription+'</li></ul>';
				}
				if(data.pagenow<data.pageTotal){
					pagenow = data.pagenow;
				}else{
					document.getElementById("querymorelink").remove();
				}
			}
		});
}
$(".leifeng-tab&.js-leifeng-tab&.day").click(function(){
	$(".leifeng-tab-box-min&.day").css('display','block');
	$(".leifeng-tab-box-min&.week").css('display','none');
	$(".leifeng-tab&.js-leifeng-tab&.day").addClass("active");
	$(".leifeng-tab&.js-leifeng-tab&.week").removeClass("active");
});
$(".leifeng-tab&.js-leifeng-tab&.week").click(function(){
	$(".leifeng-tab-box-min&.day").css('display','none');
	$(".leifeng-tab-box-min&.week").css('display','block');
	$(".leifeng-tab&.js-leifeng-tab&.day").removeClass("active");
	$(".leifeng-tab&.js-leifeng-tab&.week").addClass("active");
});