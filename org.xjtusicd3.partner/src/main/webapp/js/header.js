/*
	所有页面的通用模块，包括判断用户是否登录，导航的操作：搜索、提问、登录注册、个人中心等
	还有一些个页面的通用函数，包括cookie操作、弹窗函数、json对象长度计算等
	创建于：2015.10.27.
*/

//用于判断UA，移动端和PC端之间的跳转
var userAgentInfo = navigator.userAgent;
var Agents = ["Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod"];
var flag = true;
for (var v = 0; v < Agents.length; v++) {
	if (userAgentInfo.indexOf(Agents[v]) > 0) {
		flag = false;
		break;
	}
}
if(!flag){
	window.location.href=window.location.href.replace('/html/','/mhtml/');
}

var isEditingQuestion=false;//在提交问题时判断是在提新问题还是在编辑问题
var isSubmitQuestion = false;//是否正在提交问题，防止多次提交
var searchNowRequest;//即时搜索的xmlhttprequest对象，用于终止上一次的查询请求
var notification_interval;	//消息面板上下移动计时器
var post_hash;	// 提新问题、编辑问题token



/**
 * 用于记录跳至其他标签页时的计时器的全局变量
 * 其拥有两个属性
 * autoFresh表示每5分钟刷新一次未读消息的计时器
 * title，存储原标题
 */
var pageVisibility={};

$(function(){

	//cookie判断是否登录
	if(getCookie('nick_name')&&getCookie('uid')&&getCookie('avatar_file')&&getCookie('group_id')){
		//如果已经登录，将登录信息显示
		$('#logedIn img#userImg').attr('src',getCookie('avatar_file'));
		$('#logedIn span#userName').text(getCookie('nick_name'));
		$('#logedIn').removeClass('hidden');
		$('#notLoged').addClass('hidden');
		$('#logedIn').width($('#userImg').width()+$('#userName').width()+15);
		if($('header .memberico').width()>120){
			if($('header .memberico').width()<180){
				$('header form input').width($('header form input').width()+120-$('header .memberico').width());
			}
			else{
				$('header form input').width(350);
			}
		}
		//获取未读消息
		getUnReadNotifyCount();
	}
	//如果没有cookie，请求is_user_login接口，判断用户是否已经登录
	else{
		$.getJSON("/api/ajax/is_user_login/",function(msg){
			//如果已经登录，将登录信息写入cookie，并显示
			if(msg.data!=null){
				setCookie('uid',msg.data.uid,null,'/');
				setCookie('avatar_file',decodeURIComponent(msg.data.avatar_file),null,'/');
				setCookie('group_id',msg.data.group_id,null,'/');
				$('#logedIn img#userImg').attr('src',decodeURIComponent(getCookie('avatar_file')));
				$('#logedIn').removeClass('hidden');
				$('#notLoged').addClass('hidden');
				//如果nick_name为空，说明是新用户，还没有输入昵称，则弹窗让用户输入昵称
				if(!msg.data.nick_name){
					inputUserName();
				}
				else{
					setCookie('nick_name',decodeURIComponent(msg.data.nick_name),null,'/');
					$('#logedIn span#userName').text(getCookie('nick_name'));
					$('#logedIn').width($('#userImg').width()+$('#userName').width()+15);
					if($('header .memberico').width()>120){
						if($('header .memberico').width()<180){
							$('header form input').width($('header form input').width()+120-$('header .memberico').width());
						}
						else{
							$('header form input').width(350);
						}
					}
					//刚登录，需要声音提示
					localStorage.unReadNumber = 0;
					getUnReadNotifyCount();
				}
			}
		});
	}

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

	//用户输入标题，即时提醒相似问题
	$('#title').on('input',function(){
		//终止上次的ajax请求
		if(searchNowRequest){
			searchNowRequest.abort();
		}

		//标题输入textarea高度自动适应
  		var minHeight = 20;
		var $this = $(this);
        if ($this.scrollTop() == 0){
        	$this.scrollTop(1);
        }
        while ($this.scrollTop() == 0){
            if ($this.height() > minHeight)
                $this.height($this.height()-minHeight);
            else
                break;
            $this.scrollTop(1);
            $this.css('overflow-y','hidden');
            if ($this.scrollTop() > 0){
                $this.height($this.height()+minHeight);
                break;
            }
        }
        while($this.scrollTop() > 0){
            $this.height($this.height()+minHeight);
            if ($this.scrollTop() == 0){
            	$this.scrollTop(1);
            }
        }

		//判断问题是否超出100字
		if($(this).val().length>100){
			$('.askTitleTip').text("长度超出"+($(this).val().length-100)+"字").show();
			$('#similarAsk').empty().hide();
		}
		else{
			$('.askTitleTip').hide();
		}

		//搜索相似问题
		searchNowRequest = $.ajax({
			url: '/search/ajax/search/',
			data: {
				'q':$(this).val(),
				'action': 'related',
				'limit':5
			},
			dataType: 'json',
			success: function(msg){
				if(searchNowRequest){
					if(msg.data){
						$('#similarAsk').empty().append('<h2>您的问题可能已经有答案</h2>').show();
						for(var i=0;i<msg.data.length;i++){
							var sim = $('<a target="_blank"></a>').attr('href','detail.html?qid='+msg.data[i].search_id).html(msg.data[i].name);
							$('<li></li>').append(sim).appendTo('#similarAsk')
						}
					}
					else{
						$('#similarAsk').empty().hide();
					}
				}
			}
		});
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
		if($('#questionForm input[name="category_id"]:checked').length>=5){
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

	//提交问题
	$('body').delegate('#questionForm #submit','click',function(){
		if(isSubmitQuestion)
			return;
		isSubmitQuestion = true;
		var content=$('#questionForm #title').val();
		var detail=ue00.getContent();

		var category="";
		$('#questionForm input[name="category_id"]:checked').each(function(index){
			if(0==index)
				category=$(this).val();
			else
				category+=(","+$(this).val());
		});

		if(!isEditingQuestion){//提新问题
			$.ajax({
				url:'/publish/ajax/publish_question/',
				type:'post',
				data:{
					'question_content': htmlUtil.htmlEncodeByRegExp(content),
					'question_detail': detail,
					'category_id':category,
					'm_styple':100,
					'post_hash':post_hash
				},
				dataType:'json',
				success:function(msg){
					isSubmitQuestion = false;
					$('#questionForm').remove();
					if (msg.data) {
						if(msg.data.qid!=null){
							window.location.href="detail.html?qid="+msg.data.qid;
						}
						else if(msg.data.approval_id){
							window.location.href="approval.html?qid="+msg.data.approval_id;
						}
					}
					else if (msg.errno == '-1') {
						popUpTip(false, msg.err);
					}
				},
				error:function(msg){
					isSubmitQuestion = false;
					popUpTip(false,"提交失败");
				}
			})
		}
		else{//编辑问题
			var qid = $('#questionForm').attr('qid');
			var approval_id=$('#questionForm').attr('approval_id');
			//编辑审核过的问题
			if(qid){
				$.ajax({
					url:'/publish/ajax/modify_question/',
					type:'post',
					data:{
						'question_id': qid,
						'question_content': htmlUtil.htmlEncodeByRegExp(content),
						'question_detail': detail,
						'category_id': category
					},
					dataType:'json',
					success:function(msg){
						isSubmitQuestion = false;
						$('#questionForm').remove();
						if(msg.data.question_id){
							window.location.href="detail.html?qid="+msg.data.question_id;
						}
						else if(msg.data.approval_id){
							window.location.href="approval.html?qid="+msg.data.approval_id;
						}
						else if(msg.return_code){
							popUpTip(true,"修改成功，等待审核");
						}
						//清空所有的填充项
						$('#questionForm #title').val(null);
						ue00.setContent('');
						$('#questionForm input[name="category_id"]:checked').attr('checked',false);
						$('#questionForm input[name="category_id"]').attr('disabled',false);
						$('#questionForm ul#customTag').empty();
						$('#questionForm #question-s').removeClass('hidden');
						$('#questionForm #tag-s').addClass('hidden');
						$('#questionForm').addClass('hidden');
					},
					error:function(msg){
						isSubmitQuestion = false;
						popUpTip(false,"修改失败");
					}
				});
			}
			//编辑未审核问题
			else{
				$.ajax({
					url:'/api/ajax/approval/',
					type:'post',
					data:{
						'approval_id': approval_id,
						action: 'update',
						'question_content': htmlUtil.htmlEncodeByRegExp(content),
						'question_detail': detail,
						'category_id': category
					},
					dataType:'json',
					success:function(msg){
						isSubmitQuestion = false;
						$('#questionForm').remove();
						if(msg.data.question_id){
							window.location.href="detail.html?qid="+msg.data.question_id;
						}
						else if(msg.data.approval_id){
							window.location.href="approval.html?qid="+msg.data.approval_id;
						}
					},
					error:function(msg){
						isSubmitQuestion = false;
						popUpTip(false,"修改失败");
					}
				});
			}
		}
	});
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

	//登陆按钮
	$('#top #notLoged').on('click',function(){
		if(!getCookie('uid')){//如果未登录
			if(!$('body #loginForm').length){
				$('body').css({'max-height':$(window).height()+'px','overflow':'hidden'});
				$('<div id="loginForm" class="popup" style="display:none"><div class="fade"></div><div id="login-f"><div id="login-s"><a href="javascript:" id="close"></a><h1>问吧</h1><h2>有问题，来问吧~</h2><div id="options"><button id="log">登录</button><button id="register">注册</button></div></div></div></div>').appendTo('body').fadeIn(500);
			}
			else
				$('body #loginForm').fadeIn(500);
		}
	});

	//点击个人中心
	$('#top #logedIn').on('click',function(){
		var _href = "personal.html?userid="+encodeURIComponent(getCookie('uid'));
		//如果存在未读消息，则在个人中心打开消息面板
		if(!$('#hearer_user_notification_num').is(':hidden')&&$('#hearer_user_notification_num').text()){//有未读消息
			_href+="&&notify";
		}
		//如果已经在个人中心，只刷新页面
		if(window.location.href.indexOf('personal')!=-1){
			window.location.href=_href;
		}
		//否则，新窗口打开
		else{
			window.open(_href);
		}
	});

	//hover个人中心，消息框出现、消失
	$('#top .memberico').hover(function(){
		if(!getCookie('uid')){
			return;
		}
		//显示弹框
		$('header #personal').removeClass('hidden').css('right',$('.header').offset().left+'px');
		//调整三角位置
		var deco_left = $('#userImg').offset().left+10-$('#personal').offset().left;
		$('#header_personal_deco').css('left',deco_left+'px');

		if(getCookie('uid')){
			var $this = $(this);
			//没有加载过未读消息，则请求数据
			if(!$(this).attr('data-load-notification')){
				//移除最大高度消息面板限制
				$('#notification_content').css('max-height','3000px');
				//设置消息框内容显示部分的最大高度
				var maxHeight = $(window).height()-136;
				//隐藏向上向下按钮
				$('#notification_scroll_down').addClass('hidden');
				//显示等待动画
				$('.spinner1').removeClass('hidden');
				//清空原来的消息
				$('#notification_content').empty();
				$.ajax({
					url:'/account/ajax/getnotifylist/',
					data:{
						uid: getCookie('uid')
					},
					dataType:'json',
					success:function(msg){
						$this.attr('data-load-notification',true);	//标记已经加载过
						if(jsonLength(msg.data)>0){
							var html = '';
							//醉了，有五条消息的时候，key值居然是从1-5，而不是0-4，因此需要特殊处理
							for(var i = 0, len = jsonLength(msg.data); i <= len; i++){
								var data = msg.data[i];
								if(!data){
									continue;
								}
								html+='<div class="notification_content_detail fm_ele';
								//如果消息未读，增加unRead类
								if(data.read_flag==100){
									html+=' unRead"'
										+ ' fm-type="div" fm-name="read_notification_unRead" fm-operation="click" fm-zoon="notification_area"'//前端监控
								}
								else{
									html+='"'
										+ ' fm-type="div" fm-name="read_notification_readed" fm-operation="click" fm-zoon="notification_area"'//前端监控
								}
								html+=' data-notification-id='+data.notification_id
									+ ' data-type='+data.type+'>'
									+ '<p>'+data.message+'</p>'
									+ '</div>';
							}
							$('#notification_content').html(html);

							//显示消息内容
							$('#no_notification').hide();
							$('.spinner1').addClass('hidden');
							$('#notification_content_outer').show();

							//如果消息面板高度超出，那么设置面板高度，并显示向下按钮
							if($('#notification_content').height() > maxHeight){
								$('#notification_content').css('max-height',maxHeight + 'px');
								$('#notification_scroll_down').removeClass('hidden');
							}
						}
						else{
							$('.spinner1').addClass('hidden');
							$('#no_notification').show();
							$('#notification_content_outer').hide();
						}
					},
					error:function(msg){
					}
				})
			}
		}
	},function(){
		if(getCookie('uid')){
			$('header #personal').addClass('hidden');
		}
	});

	/**
	 * 消息面板的向上滚动
	 */
	$('#notification_scroll_top').hover(
		function(){
			//呼吸灯效果取消
			$('.notification_scroll').css('animation','none');
			//mouseover时，计时器开始，滚动
			notification_interval = setInterval(function(){
				$('#notification_content').scrollTop($('#notification_content').scrollTop()-10);
				//如果滚到顶部，按钮消失; 底部按钮开始呼吸灯效果
				if($('#notification_content').scrollTop()==0){
					$('#notification_scroll_top').addClass('hidden');
					$('#notification_scroll_down').css('animation','breathLight 2s ease-in infinite alternate');
				}
			},100);
			//只要向上滚动，那么就显示向下按钮
			$('#notification_scroll_down').removeClass('hidden');
		},function(){
			//清除计时器
			clearInterval(notification_interval);
		}
	);
	/**
	 * 消息面板的向下滚动                                                                [description]
	 */
	$('#notification_scroll_down').hover(
		function(){
			//呼吸灯效果取消
			$('.notification_scroll').css('animation','none');
			//mouseover时，计时器开始，滚动
			notification_interval = setInterval(function(){
				var pre = $('#notification_content').scrollTop();
				$('#notification_content').scrollTop(pre + 10);
				//如果滚到底部，按钮消失；顶部按钮显示呼吸灯效果
				if(pre == $('#notification_content').scrollTop()){
					$('#notification_scroll_down').addClass('hidden');
					$('#notification_scroll_top').css('animation','breathLight 2s ease-in infinite alternate');
				}
				if($('#notification_content').scrollTop()>0){
					//显示向上按钮
					$('#notification_scroll_top').removeClass('hidden');
				}
			},100);

		},function(){
			//清除计时器
			clearInterval(notification_interval);
		}
	);

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

	//消息已读
	$('#notification_content').delegate('.notification_content_detail.unRead','click',function(){
		readNotify($(this));
	});

	//进入个人中心
	$('#personal_page').on('click',function(){
		var _href = "personal.html?userid="+encodeURIComponent(getCookie('uid'));
		if(window.location.href.indexOf('personal')!=-1){
			window.location.href=_href;
		}
		else{
			window.open(_href);
		}
	});

	//注销
	$('body').delegate('#logOut','click',function(e){
		e.stopPropagation();
		deleteCookie('uid','/');
		deleteCookie('nick_name','/');
		deleteCookie('avatar_file','/');
		deleteCookie('group_id','/');
		window.location.href="/api/ajax/logout/";
	});

	//登陆
	$('body').delegate('#loginForm #log','click',function(){
		window.location.href="/api/login/";
	});

	//注册
	$('body').delegate('#loginForm #register','click',function(){
		window.location.href="/api/register/";
	});

	//关闭所有弹窗
	$('body').delegate('div.popup a#close','click',function(){
		$(this).closest('div.popup').fadeOut(200);
		$('body').css({'max-height':'none','overflow':'scroll'});
	});

	//用户输入用户名
	$('body').delegate('#confirmUserName','click',function(){
		var inputName=$('input#userNameInput').val();

		var count = inputName.replace(/[^\x00-\xff]/g,"**").length;
		if(count<=0){
			$('#userNameForm img').removeClass('hidden').attr('src','images/wrong.png');
			$('#userNameForm .nickNameTip').text('用户昵称不能为空');
			return;
		}
		if(count>16){
			$('#userNameForm img').removeClass('hidden').attr('src','images/wrong.png');
			$('#userNameForm .nickNameTip').text('用户昵称必须小于8个字');
			return;
		}

		var a=$(this);
		a.attr('disabled','disabled');
		$.ajax({
			url:"/api/ajax/profile/",
			type:'post',
			data:{'nick_name':htmlUtil.htmlEncodeByRegExp(inputName)},
			dataType:'json',
			success:function(msg){
				if(msg.return_code==200){
					$('#userNameForm img').removeClass('hidden').attr('src','img/right.png');
					setTimeout(function(){
						$('#userNameForm').remove();
					},1000);
					setCookie('nick_name',inputName,null,'/');
					window.location.reload();
				}
				else if(msg.return_code==3003){
					$('#userNameForm img').removeClass('hidden').attr('src','images/wrong.png');
					$('#userNameForm .nickNameTip').text('用户昵称已经存在');
				}
				a.removeAttr('disabled');
			},
			error:function(){
				$('#userNameForm img').removeClass('hidden').attr('src','images/wrong.png');
				$('#userNameForm .nickNameTip').text('提交失败');
				a.removeAttr('disabled');
			}
		});
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