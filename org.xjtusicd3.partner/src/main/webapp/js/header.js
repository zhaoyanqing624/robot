
/**
 * 用于记录跳至其他标签页时的计时器的全局变量
 * 其拥有两个属性
 * autoFresh表示每5分钟刷新一次未读消息的计时器
 * title，存储原标题
 */
var pageVisibility={};

$(function(){
	/*
	 * 即时搜索提示部分
	 */
	//用户输入内容响应事件
	$('header form input').on('propertychange input',function(e){
		//设置即时提示框的样式
		var top = 44;
		var left=$(this).offset().left;
		var width=$(this).width()+18;
		$('.liketips').remove();
		var liketips=$('<div></div>');
		liketips.addClass('liketips');
		liketips.css({'top':top+'px','left':left+'px','width':width+'px','position':'fixed','z-index':'1001'});

		//终止上次的ajax请求
		if(searchNowRequest){
			searchNowRequest.abort();
		}

		//请求提示内容
		searchNowRequest = $.ajax({
			url: '/search/ajax/search/',
			data: {
				'q': $(this).val(),
				'limit': 5,
				'action': 'related'
			},
			dataType: 'json',
			success: function(msg){
				liketips.empty();
				if(searchNowRequest){
					if(msg.data){
						for(var i=0;i<msg.data.length;i++){
							$('<div></div>').attr('search_id',msg.data[i].search_id).html(msg.data[i].name).appendTo(liketips);
						}
					}
				}
			}
		});

		liketips.appendTo('body');
	});

	//失去焦点，隐藏提示框
	$('#top header form input').on('blur',function(){
		setTimeout(function(){
			$('.liketips').fadeOut('fast');
		},50)
	});

	//用户通过键盘选择提示内容
	$('#top header form input').on('keydown',function(e){
		if(e.keyCode==40){//向下按键
			var nowtr=$('.liketips div.selected');
			if(nowtr.length>0){	//存在已经选中的
				var nexttr = nowtr.next('div');
				if(nexttr.length>0){	//不是最后的选项
					$('.liketips div.selected').removeClass('selected');
					nexttr.addClass('selected');
				}
				else{//已经到最后一个，则返回第一个
					$('.liketips div.selected').removeClass('selected');
					$('.liketips').find('div:first-child').addClass('selected');
				}
			}
			else{
				$('.liketips').find('div:first-child').addClass('selected');
			}
		}
		else if(e.keyCode==38){	//向上按键
			var nowtr=$('.liketips div.selected');
			if(nowtr.length>0){	//存在已经选中的
				var pretr = nowtr.prev('div');
				if(pretr.length>0){	//不是第一个选项
					$('.liketips div.selected').removeClass('selected');
					pretr.addClass('selected');
				}
				else{//已经到第一个，则返回最后一个
					$('.liketips div.selected').removeClass('selected');
					$('.liketips').find('div:last-child').addClass('selected');
				}
			}
			else{
				$('.liketips').find('div:last-child').addClass('selected');
			}
		}
		else if(e.keyCode==13){//回车键
			var nowtr=$('.liketips div.selected');
			if(nowtr.length==1){
				window.open('detail.html?qid='+nowtr.eq(0).attr('search_id'));
			}
			$('.liketips').fadeOut('fast');
		}
	});

	//用户mouseOver事件，注意selected类用于识别是否选中某条提示
	$('body').delegate('.liketips div','mouseover',function(){
		$('.liketips div').removeClass('selected');
		$(this).addClass('selected');
	})

	//用户点击提示，直接跳转到该问题
	$('body').delegate('.liketips div','click',function(){
		window.open('detail.html?qid='+$(this).attr('search_id'));
		$('.liketips').remove();
	})
	/*
	 * 即时搜索提示部分
	 */


	/**
	 * 提新问题部分
	 */
	//用户点击"提新问题"
	$('#ask').click(function(){
		if(getCookie('uid')){
			//提问弹窗的显示与初始化
			$('#questionForm').fadeIn(500);
			$('#questionForm #title').val('');
			$('#question-s').removeClass('hidden');
			$('#tag-s').addClass('hidden');
			ue00.setContent('');
			$('#similarAsk').empty().hide();
			$('#questionForm input[type="checkbox"]').removeAttr("checked");
			$('#tilte').focus();
			$('body').css({'max-height':$(window).height()+'px','overflow':'hidden'});

			// 调用接口：获取post_hash
			$.ajax({
				url: '/api/ajax/getPostHash/',
				type: 'get',
				data: {},
				dataType: 'json',
				success: function(msg) {
					if (msg.return_code == 200 && msg.data) {
						post_hash = msg.data.post_hash;
					}
				},
				error: function(msg) {
					console.log(msg);
				}
			});
		}
		else{
			$('#notLoged').trigger('click');
		}
	});



	//下一步
	$('body').delegate('#toStep2','click',function(e){
		if($('#title').val().length<5){
			popUpTip(false,"标题不得少于5个字")
			return;
		}
		if($('#title').val().length>100){
			popUpTip(false,"标题不得多于100个字")
			return;
		}
		$('#question-s').addClass('hidden');
		$('#tag-s').removeClass('hidden');
		$('#titP').text('“'+$('#title').val()+'”');
	});

	//返回上一步
	$('body').delegate('#toStep1','click',function(e){
		$('#question-s').removeClass('hidden');
		$('#tag-s').addClass('hidden');
	});

	//点击标签的label触发checkbox
	$('body').delegate('#questionForm label','click',function(e){
		$(this).prev().trigger('click');
	});

	//判断最多选择五个标签
	$('body').delegate('#questionForm input[name="category_id"]','change',function(){
		if($('#questionForm input[name="category_id"]:checked').length>=1){
			$('#questionForm input[name="category_id"]').not(':checked').attr('disabled',true);
		}
		else{
			$('#questionForm input[name="category_id"]').attr('disabled',false);
		}
	})
	/*
	//添加自定义标签
	$('body').delegate('#tip','click',function(){
		$(this).addClass('hidden');
		$('#custom').removeClass('hidden');
		$('#customTip').focus();
	});

	//停止添加自定义标签
	$('#cancelCustom').on('click',function(){
		$(this).parent().addClass('hidden');
		$('#tip').removeClass('hidden');
	});

	//提交自定义标签
	$('body').delegate('#addCustom','click',function(){
		var a=$(this).prev();
		var customtag=$(this).prev().val();
		if(customtag!=''){
			var flag=false;
			$('#questionForm input[name="category_id"]').each(function(){
				if($(this).next().text()==customtag){
					popUpTip(false,"标签已存在");
					flag=true;
					return false;
				}
			})
			if(flag)
				return;
			$.ajax({
				url:'/api/ajax/add_tags/',
				type:'post',
				data:{'title':customtag},
				dataType:'json',
				success:function(msg){
					var box = $('<input type="checkbox" name="category_id"/>').val(msg.data.id);
					var label=$('<label></label>').text(customtag);
					$('<li></li>').append(box).append(label).appendTo('#questionForm ul#customTag');
					if($('#questionForm input[name="category_id"]:checked').length<5){
						box.trigger('click');
					}
					else{
						box.attr('disabled',true);
					}
					a.val('');
				},
				error:function(){
					popUpTip(false,"新建标签失败");
				}
			})
		}
		$(this).parent().addClass('hidden');
		$('#questionForm p#tip').removeClass('hidden');
	})
	*/

	/**
	 * 提新问题结束
	 */

	//具体问题点击收起或者展开
	$('body').delegate('.detail','click',function(){
		$(this).addClass('hidden');
		$(this).closest('article').find('.fold').show();
		$(this).parent().find('.fullDetail').removeClass('hidden');

		//悬浮栏显示
		var bottomHeight = $(window).scrollTop()+$(window).height();
		var article = $(this).closest('article');
		var option = article.find('.options');
		if((bottomHeight>article.offset().top+100)&&(bottomHeight<option.offset().top+option.innerHeight())){
			article.find('.options').clone().addClass('float').appendTo(article);
		}
		$(window).scroll();
	});

	$('body').delegate('.fold','click',function(){
		var atop = $(this).closest('article').offset().top;
		var btop = $(window).scrollTop();
		$(this).closest('article').find('.fold').hide();
		$(this).closest('article').find('.fullDetail').addClass('hidden');
		$(this).closest('article').find('.detail').removeClass('hidden');

		//悬浮栏移除，之后如果当前文章不再显示范围，页面滚动至该文章
		$(this).closest('article').find('.float').remove();
		if(atop < btop){
			$(window).scrollTop(atop-60);
		}
	});

	/**
	 * 消息面板的向下滚动                                                                [description]
	 */

	//点击查看全部消息
	$('#personal_allNotification').on('click',function(){
		var _href = "personal.html?userid="+encodeURIComponent(getCookie('uid'))+"&&notify";
		if(window.location.href.indexOf('personal')!=-1){
			window.location.href=_href;
		}
		else{
			window.open(_href);
		}
	});


	//关闭所有弹窗
	$('body').delegate('div.popup a#close','click',function(){
		$(this).closest('div.popup').fadeOut(200);
		$('body').css({'max-height':'none','overflow':'scroll'});
	});


	//用户点击加载更多
	$('#loadMore button').click(function(){
		$('#loadStatus>div').addClass('hidden').eq(0).removeClass('hidden');
		contentFlow();
	});

	//window的scroll事件，判断悬浮栏是否需删除
	$(window).scroll(function(){
		//遍历所有的文章
		$('.options').not('.float').each(function(){
			//只检查展开了的文章
			if(!$(this).closest('article').find('.fullDetail').is(':hidden')){
				var bottomHeight = $(window).scrollTop()+$(window).height();
				var article = $(this).closest('article');
				//不需要悬浮栏
				if(bottomHeight>=$(this).offset().top+$(this).innerHeight()||bottomHeight<article.offset().top+100){
					$(this).closest('article').find('.float').remove();
				}
				//需要悬浮栏
				else{
					//如果当前没有悬浮栏，增加一个
					if($(this).closest('article').find('.float').length<1){
						$(this).clone().addClass('float').appendTo(article);
					}
				}
			}
		});
	});

	// 修复提问框点击插入视频，弹窗不显示的问题
	// 此函数通过修改z-index修复该问题
	$('#questionForm').delegate('.edui-for-insertvideo','click',function(){
		setTimeout(function(){
			$('#edui_fixedlayer').css('z-index',10);
		},100);
	});

	/**
	 * 监听visibilitychange事件，当用户离开页面5分钟后自动刷新未读消息数，每1秒刷新一次页面标题
	 */
	$(document).on('visibilitychange',function(){
		if(document.visibilityState=="visible"){
			document.title = pageVisibility.title;
			clearInterval(pageVisibility.autoFresh);
			getUnReadNotifyCount();
		}
		else{
			pageVisibility.title = document.title;
			//如果当前有未读消息，则更改页面标题
			var preCount = parseInt(localStorage.unReadNumber);

			if (preCount > 0){
				document.title = "("+preCount+") 有新消息-问吧";
			}
			//每5分钟更新一次未读消息数
			pageVisibility.autoFresh = setInterval(
				function(){
					getUnReadNotifyCount();
				},
				5*60*1000
			);
		}
	});
});

function setCookie(sName,sValue,oExpires,sPath,sDomain,bSecure){
	var sCookie = sName+"="+encodeURIComponent(sValue);
	if(oExpires) {
		var expireDate = new Date();
		expireDate.setTime(expireDate.getTime()+oExpires*3600*1000);
		sCookie+=";expires="+expireDate.toGMTString();
	}
	if(sPath) sCookie+=";path="+sPath;
	if(sDomain) sCookie+=";domain="+sDomain;
	if(bSecure) sCookie+=";secure";
	document.cookie = sCookie;
}

function getCookie(NameOfCookie)
{
	if (document.cookie.length > 0)
	{
		//写的这么复杂，是为了防止匹配uid时匹配到uuid的情况。。。
		var reg = new RegExp("^"+NameOfCookie+"=([^;]*)$");
		var cookies=document.cookie.split(';');
		for(var i in cookies){
			if($.trim(cookies[i]).match(reg)){
				return(decodeURIComponent(RegExp.$1));
			}
		}
   	}
   	return null;
}

function deleteCookie(sName,sPath,sDomain){
	setCookie(sName,'',-1,sPath);
}

//要求用户输入用户名
function inputUserName(){
	$('<div id="userNameForm" class="popup" style="display:none"><div class="fade"></div><div id="userName-f"><div id="userName-s"><h1>问吧</h1><h2>有问题，来问吧~</h2><h3>输入用户昵称</h3><span class="nickNameTip"></span><input type="text" id="userNameInput" maxlength="16"/><img class="hidden"/><button id="confirmUserName">确定</button></div></div></div>').appendTo('body').fadeIn(500);
	$('body').css({'max-height':$(window).height()+'px','overflow':'hidden'});
}

//通用弹窗函数
function popUpTip(isRight,msg){
	var time= arguments[2]?arguments[2]:1000;
	var popup=$('<div class="success" style="z-index:1001;position:fixed;top:40%;left:45%;width:220px;background: #f3f3f3;text-align: center;border:1px solid black;border-radius:3px;"><div style="margin-top:30px; margin-bottom:30px;"><img src="img/right.png" style="width:20px;height:20px;margin-right:10px;"><h2 style="font-size:16px;display:inline-block;line-height:22px;vertical-align:top"></h2></div></div>');
	if(isRight==false)
		popup.find('img').attr('src',"images/wrong.png");
	popup.find('h2').text(msg);
	popup.appendTo('body');
	if(time!=-1)
		setTimeout(function(){popup.fadeOut(500).remove();},time);
	return popup;
}

//获得json的对象个数
function jsonLength(jsonObj){
	var length=0;
	for(var i in jsonObj){
		length++;
	}
	return length;
}

//所有问题和回答的缩略版截断函数
function abbrDetailP(){
	$('ul#searchResult .detailP').each(function(){
		$(this).text($(this).text());
		var length=$(this).text().length;
		if(length>116){
			$(this).text($(this).text().substring(0,116)+"...");
			$(this).html($(this).html()+"<span class='readMore'>查看更多</span>")
		}
	});
}

//所有回答人信息的个人签名截断函数
function abbrAnswerer(){
	$('.answerer p a').each(function(){
		var length=$(this).text().length;
		if(length>50){
			$(this).text($(this).text().substring(0,50)+"...");
		}
	})
}

/**
 * 获取未读消息
 */
function getUnReadNotifyCount(){
	//如果用户未登录，状态清零
	if(!getCookie('uid')){
		localStorage.unReadNumber = 0;
		return false;
	}
	$.ajax({
		url:'/account/ajax/getnotify/',
		data:{
			uid: getCookie('uid')
		},
		dataType:'json',
		success:function(msg){
			if(msg.data){
				var count = parseInt(msg.data.count);
				var preCount = parseInt(localStorage.unReadNumber);

				//有新的未读消息，则声音提示
				if(count>preCount){
					$('#notifyAudio')[0].play();
				}
				//显示未读消息数
				if(!isNaN(count)){
					//利用localStrage存储未读消息数
					localStorage.unReadNumber = count;

					//显示未读消息数
					$('#hearer_user_notification_num').attr('data-num',count);
					$('#top .memberico').removeAttr('data-load-notification');
					$('.header_notification_popup_num').text(count);
					if(count>0){
						if(count<=9){
							$('#hearer_user_notification_num,.personal_notify_count').text(count).show();
						}
						else{
							$('#hearer_user_notification_num,.personal_notify_count').text('···').show();
						}
					}
					else{
						$('#hearer_user_notification_num,.personal_notify_count').hide();
					}
					//如果当前页面不可见，则更新页面标题
					if(document.visibilityState !== "visible"){
						if(count > 0){
							document.title = "("+count+") 有新消息-问吧";
						}
						else{
							document.title = pageVisibility.title;
						}
					}
				}
			}
		}
	});
}

/**
 * 消息未读变已读
 * @param  {object} message 点击的未读信息jquery数据集
 */
function readNotify(message){
	if(!getCookie('uid')){
		return false;
	}
	message.removeClass('unRead');
	var num;
	//移除已经加载未读消息列表的属性，下次将重新加载未读消息列表
	$('#top .memberico').removeAttr('data-load-notification');
	//更新显示的未读消息数
	if((num = parseInt(localStorage.unReadNumber)-1)>=0){
		localStorage.unReadNumber = num;
		$('.header_notification_popup_num').text(num);
		if(num==0){
			$('#hearer_user_notification_num,.personal_notify_count').hide();
		}
		else if(num<10){
			$('#hearer_user_notification_num,.personal_notify_count').text(num).show();
		}
		else{
			$('#hearer_user_notification_num,.personal_notify_count').text('···').show();
		}
	}
	$.ajax({
		url:'/account/ajax/readnotify/',
		type: 'post',
		data:{
			uid: getCookie('uid'),
			type: message.attr('data-type'),
			notification_id: message.attr('data-notification-id')
		},
		dataType: 'json',
		success:function(msg){
		},
		//更新失败，回滚
		error:function(msg){
			message.addClass('unRead');

			localStorage.unReadNumber = num + 1;
			num++;
			if(num==0){
				$('#hearer_user_notification_num,.personal_notify_count').hide();
				$('.header_notification_popup_num').text(0);
			}
			else if(num<10){
				$('#hearer_user_notification_num,.header_notification_popup_num,.personal_notify_count').text(num).show();
			}
			else{
				$('#hearer_user_notification_num,.header_notification_popup_num,.personal_notify_count').text('···').show();
			}
		}
	})
}

// 公共头部导航
var commonHeader='<div id="top">\
			<header class="header">\
				<h1><a href="index.html">问吧</a></h1>\
				<form action="search.html">\
					<input name="q" type="text" placeholder="搜索关键词或问题，如“如何升级至Windows 10？”" autocomplete="off" />\
					<input name="limit" type="hidden" value="10"/>\
					<button class="fm_ele" fm-type="button" fm-name="search_submit" fm-operation="submit" fm-zoon="header_area"></button>\
				</form>\
				<nav class="nav">\
					<ul>\
						<li><a class="fm_ele" href="index.html" fm-type="link" fm-name="link_index" fm-operation="click" fm-zoon="header_area">首页</a></li>\
						<li><a class="fm_ele" href="explore.html" fm-type="link" fm-name="link_explore" fm-operation="click" fm-zoon="header_area">发现</a></li>\
						<li><a class="fm_ele" href="answer.html" fm-type="link" fm-name="link_answer" fm-operation="click" fm-zoon="header_area">答题</a></li>\
					</ul>\
				</nav>\
				<button class="fm_ele" id="ask" fm-type="button" fm-name="button_ask" fm-operation="click" fm-zoon="header_area">提新问题</button>\
				<div class="memberico">\
					<a id="notLoged">\
						<span id="logSpan">登录</span>\
					</a>\
					<div id="logedIn" class="hidden" fm-type="button" fm-name="link_personal" fm-operation="click" fm-zoon="header_area">\
						<div id="hearder_user_info">\
							<img id="userImg"src=""/>\
							<span id="hearer_user_notification_num" data-num="0">0</span>\
						</div>\
						<span id="userName"></span>\
					</div>\
					<div id="personal" class="hidden">\
						<img id="header_personal_deco" src="img/dia-deco.png">\
						<div id="hearder_notification_popup">\
							<div id="header_notification_popup_count">\
								<span>未读消息</span><span class="header_notification_popup_num">0</span>\
								<a id="personal_allNotification" class="fm_ele" fm-type="link" fm-name="link_all_notification" fm-operation="click" fm-zoon="notification_area">查看全部</a>\
							</div>\
							<div id="header_notification_popup_content">\
								<div id="no_notification">\
									<p>暂无内容，欢迎冒泡</p>\
								</div>\
								<div class="spinner1 hidden">\
		  							<div class="bounce1"></div>\
		  							<div class="bounce2"></div>\
		  							<div class="bounce3"></div>\
								</div>\
								<div id="notification_content_outer">\
									<a id="notification_scroll_top" class="notification_scroll hidden"><div class="notification_arrow" id="notification_arrow_up"></div></a>\
									<div id="notification_content">\
									</div>\
									<a id="notification_scroll_down" class="notification_scroll hidden"><div class="notification_arrow" id="notification_arrow_down"></div></a>\
								</div>\
							</div>\
							<div id="header_notification_popup_option">\
								<a id="personal_page" class="fm_ele" fm-type="link" fm-name="link_personal" fm-operation="click" fm-zoon="notification_area">个人主页</a>\
								<a id="aboutpage" href="/html/about.html" target="blank" class="fm_ele" fm-type="link" fm-name="link_about" fm-operation="click" fm-zoon="notification_area">关于问吧</a>\
								<a id="logOut" class="fm_ele" fm-type="link" fm-name="link_logout" fm-operation="click" fm-zoon="notification_area">退出</a>\
							</div>\
						</div>\
					</div>\
				</div>\
			</header>\
		</div>\
		<div class="clearfix"></div>';

// 公共底部信息
var commonFooter='<footer class="footer">\
			<div>\
				<span>版权所有</span>\
				<span class="copy">&copy</span>\
				<span>2008-2015</span>\
				<ul class="link">\
					<li><a>联想集团 |</a></li>\
					<li><a>法律公告 |</a></li>\
					<li><a>隐私保护 |</a></li>\
					<li><a>关于联想 |</a></li>\
				</ul>\
				<span class="icp">京ICP备11035381</span>\
				<span>京公网安备110108007970号</span>\
			</div>\
		</footer>\
		<audio id="notifyAudio">\
			<source src="img/notification.ogg" type="audio/ogg">\
			<source src="img/notification.mp3" type="audio/mpeg">\
			<source src="img/notification.wav" type="audio/wav">\
		</audio>';

// 快速提问弹窗
var commonAsk='<div id="questionForm" class="popup" style="display:none">\
			<div class="fade"></div>\
			<div id="question-f">\
				<div id="question-s">\
					<a href="javascript:" id="close"></a>\
					<h1 id="titH">添加问题</h1>\
					<textarea rows="1" name="question_content" id="title" placeholder="请输入您的问题，如：IPhone 6指纹识别如何破解？" maxlength="100"></textarea>\
					<p class="askTitleTip"></p>\
					<ul id="similarAsk"></ul>\
					<h1 id="desH">添加问题的详细描述</h1>\
					<script id="ueditor" type="text/plain" style="margin:20px 0 0 35px;width:650px;height:300px;font-size:14px;"></script>\
					<button id="toStep2">下一步</button>\
				</div>\
				<div id="tag-s" class="hidden">\
					<a href="javascript:" id="close"></a>\
					<h1 id="tagH">添加标签</h1>\
					<h2>可选</h2>\
					<p id="titP"></p>\
					<ul id="systemTag"></ul>\
					<ul id="customTag"></ul>\
					<div></div>\
					<!-- <img src="img/addTag.png"/>\
					<p id="tip">添加其他标签</p>\
					<div id="custom" class="hidden" tabindex="13">\
						<input id="customTip" type="text"/>\
						<button id="addCustom">添加</button>\
						<button id="cancelCustom">取消</button>\
					</div> -->\
					<button id="toStep1">返回</button>\
					<button id="submit">提交</button>\
				</div>\
			</div>\
		</div>';

$(window).resize(function(){
	if($('#questionForm').is(':visible')||$('#loginForm').is(':visible')||$('#userNameForm').is(':visible')||$('#feedbackForm').is(':visible')){
		$('body').css({'max-height':$(window).height()+'px','overflow':'hidden'});
	}
	//消息面板调整
	$('.memberico').removeAttr('data-load-notification');
});