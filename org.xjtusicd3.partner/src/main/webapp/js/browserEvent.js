function browserEvent(){
	var _event="";
	if(navigator.userAgent.indexOf('Trident') > -1){//IE内核
		_event = window.event.srcElement;
	}else if(navigator.userAgent.indexOf('Presto') > -1){//opera内核
		
	}else if(navigator.userAgent.indexOf('AppleWebKit') > -1){//苹果、谷歌内核
		_event = event.target;
	}else if(navigator.userAgent.indexOf('Firefox') > -1){//火狐内核Gecko
		
	}
	return _event;
}
