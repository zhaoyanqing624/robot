setTimeout("robot_welcome()",1000);
function showTime(){
    var myDate = new Date();  
    var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)  
    var month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)  
    var date = myDate.getDate();        //获取当前日(1-31)  
    var hours = myDate.getHours();       //获取当前小时数(0-23)  
    var minutes = myDate.getMinutes();     //获取当前分钟数(0-59)  
    var seconds = myDate.getSeconds();     //获取当前秒数(0-59)  
    var time = year+"年"+month+"月"+date+"日"+"   "+hours+":"+minutes+":"+seconds;
	return time;
}
function robot_welcome(){
	 $.ajax({
		 type: "GET",
		 url: "/org.xjtusicd3.partner/getFirstLevel.html",            
		 dataType: "json",
		 success: function(data){
			 
		      }
		 });
	document.getElementById("chat01_content").innerHTML = '<li class="media"><a class="pull-left" href="#"><img class="media-object" alt="Generic placeholder image" src="static/image/xiaoduo.png"></a><div class="media-body chat-pop"><h4 class="media-heading">小朵</h4><span class="pull-right"><i class="fa fa-clock-o"></i> <abbr class="timeago" title="Oct 9, 2013">'+showTime()+'</abbr> </span><p><div style="float:left;"><span style="">您好,我是智能客服小朵,有什么可以帮您？如果方案有帮助,记得评价哦<img src="http://robotterm.ecare365.com/animages/bq.png" class="mCS_img_loaded"><br><span style="color:blue;display:inline"><a href="javascript:void(0);" onclick="cancelHref(this);$client.sendTextEx("服务网点");countQuestionsClickTimes("问候语-服务网点");return false;">服务网点</a></span> | <span style="color:blue;display:inline"><a href="javascript:void(0);" onclick="cancelHref(this);$client.sendTextEx("驱动下载");countQuestionsClickTimes("问候语-驱动下载");return false;">驱动下载</a></span> | <span style="color:blue;display:inline"><a href="javascript:void(0);" onclick="cancelHref(this);$client.sendTextEx("配置查询");countQuestionsClickTimes("问候语-配置查询");return false;">配置查询</a></span> | <span style="color:blue;display:inline"><a href="javascript:void(0);" onclick="cancelHref(this);$client.sendTextEx("保修查询");countQuestionsClickTimes("问候语-保修查询");return false;">保修查询</a></span> | <span style="color:blue;display:inline"><a href="javascript:void(0);" onclick="cancelHref(this);$client.sendTextEx("查看提问技巧");countQuestionsClickTimes("问候语-提问技巧");return false;">提问技巧</a></span><br>➢<span style="color:blue;display:inline"><a href="javascript:void(0);" onclick="cancelHref(this);$client.sendMessage("itnewnaijgnahcjgx");countQuestionsClickTimes("问候语-新购机常见问题");return false;">新手常见问题</a></span></span></div></p></div></li>';
} 