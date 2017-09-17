$(function(){
	if(navigator.userAgent.indexOf('Trident') > -1){//IE内核
		
	}else if(navigator.userAgent.indexOf('Presto') > -1){//opera内核
		window.location.href="404.html";
	}else if(navigator.userAgent.indexOf('AppleWebKit') > -1){//苹果、谷歌内核
		window.location.href="404.html";
	}else if(navigator.userAgent.indexOf('Firefox') > -1){//火狐内核Gecko
		window.location.href="404.html";
	}
});