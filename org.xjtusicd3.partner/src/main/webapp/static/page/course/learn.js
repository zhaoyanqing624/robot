define(function(require, exports, module){
    require('common');
	require('/org.sjtusicd3.partner/static/page/course/course.layout.js');
	require('/org.sjtusicd3.partner/static/page/course/common/course_common.js');
	require('/org.sjtusicd3.partner/static/page/course/common/course_collection.js');
    var store = require('store');

    //判断是否是新版本chrome
    var index = parseInt(navigator.userAgent.indexOf('Chrome/'))+7;
    var version = navigator.userAgent.slice(index).split(".");
    if(parseInt(version[0]) >= 50){
    	$(".chapter-introubox").find('.chapter-content').css({'width':'280px','white-space':'pre-line','text-align':'center'})
    }


	$(function(){
		var mid_href = store.get('mid_href');
	    if(mid_href !='' && mid_href != undefined && store.get('uid') == undefined ) {
			$('.learnchapter .video  a').each(function() {
				if($(this).attr('href') == mid_href) {
					$(this).parents('.learnchapter').addClass('learnchapter-active');
					$(this).parents('.video').show();
					$(this).parents('.learnchapter')
						.find('span')
						.removeClass('js-close')
						.addClass('js-open');
				}
			})
			store.remove('mid_href');
		} else if(store.get('uid') == undefined) {
			//$('.learnchapter h3').eq(0).addClass('learnchapter-active').click();
		}

		//页面打开章节打开
		var $medias = $('.mod-chapters').find('li');
		if(hasLearn == 0){
			$('.mod-chapters').find('.chapter').addClass('chapter-active');
			$('.mod-chapters').find('h3 span').addClass('js-open');

		}else {
			if($medias.length <= 20){
				$('.mod-chapters').find('.chapter').addClass('chapter-active');
				$('.mod-chapters').find('h3 span').addClass('js-open');
			}
		}
		

		$(".back-btn").on("click",function(event) {
			if(document.referrer&&document.referrer.indexOf("course/list")!=-1){

				window.location=document.referrer
			}else{
				window.location="/course/list"
			}
			return false;
		});

		/*记录播放记录到本地存储*/
		$('.video li').bind('click',function(){
			var mid_href = $(this).find('a').attr('href');
			store.set('mid_href', mid_href);
		})

		$('.mod-chapters').on('click', 'h3, .J-media-item', function(e){
			var $this = $(this);

			// 如果点击的是章
			if(!$this.hasClass('J-media-item')){
				var flag = $this.find('span'),
					chapter = $this.parents('.chapter');

				chapter.toggleClass('chapter-active');

				if(chapter.hasClass('chapter-active')){
					flag.removeClass('js-close').addClass('js-open');
				}else{
					flag.removeClass('js-open').addClass('js-close');
				}
			}else{
				// 点击的是节
				// 如果未登录弹出登录框，然后自动跳转到学习详情页
				if(!isLogin){
					seajs.use('login_sns', function(obj){
						obj.init();
					});
					return false;
				}
			}
		});
		//章节介绍显示
		$(".chapter").delegate('.chapter-info', 'mouseover', function(event) {
			$(this).find(".triangle").show();
			var $chapterLeft = - ($(this).find(".chapter-introubox").width()/2 -8)+'px';
			$(this).find(".chapter-introubox").css("left",$chapterLeft);
		});
		//章节介绍隐藏
		$(".chapter").delegate('.chapter-info', 'mouseleave', function(event) {
			$(this).find(".triangle").hide();
		});
	});

	if(hasLearn == 1){
		//渲染章节学习状态
		$.ajax({
	        type: "post",
	        url: '/course/ajaxusermediasstatus?cid='+GC.course.id,
	        success: function(data) {
	        	var $mediasId = [],
	        		_html;
	        	if(data.result == 0){
	        		var _data = data.data;
	        		var $medias = $('.mod-chapters').find('li');
	        		$($medias).each(function(k, v) {
	        			$mediasId.push($($medias[k]).attr('data-media-id'));
		            });
	        		$($mediasId).each(function(k, v) {
	        			if(_data == ''){
	     					var $item = $('.mod-chapters').find('li').eq(k);
		            		_html = '<i class="icon-nolearn start"></i>';
		            		$($item).find('a').prepend(_html);
		        			return;
		        		}
	        			//添加最近学习
	     				if($mediasId[k] == learnChapter){
	     					$('.mod-chapters').find('li').eq(k).prepend('<em class="laststudy">最近学习</em>');
	     					$('.mod-chapters').find('li').eq(k).parents('.chapter').addClass('chapter-active');
	     					$('.mod-chapters').find('li').eq(k).parents('.video').siblings('h3').find('span').addClass('.js-open');
	     				}

		            	if(_data[$mediasId[k]]){
		            		var $item = $('.mod-chapters').find('li').eq(k);
		            		if(_data[$mediasId[k]].finished == 1){
		            			_html = '<i class="icon-tick-revert done"></i>';
		            		}else{
		            			_html = '<i class="icon-half ing"></i>';
		            		}
		            		
		            		$($item).find('a').prepend(_html);
		            	}else{
		            		var $item = $('.mod-chapters').find('li').eq(k);
		            		_html = '<i class="icon-nolearn start"></i>';
		            		$($item).find('a').prepend(_html);
		            	}
		            });
	        	}
	            
	        }
	    });
	}
	
    
    //分享点击返回积分事件
    var shareEles = $(".bdsharebuttonbox a");
    var sendId= GC.course.id;
    var ajaxing = 0;
    var timer,interval=5000;

    shareEles.on("click",function(e){
        if(ajaxing) return;
        ajaxing=1;
        //积分框消失
        shareFrame.hide();
        $.ajax({
            type: "post",
            url: '/course/courseshare',
            data: "id="+sendId+"&type="+0,
            success: function(res) {
                ajaxing=0;
            },
            error: function() {
                layer.msg('网络错误，请稍后再试', 1, 1);
            },
            complete: function() {
                isAjax=0;
            }
        });
        e.preventDefault();
    }).mouseenter(function(){
        shareFrame.show();
    }).mouseleave(function(){
        if(timer) clearTimeout(timer);
        timer=setTimeout(function(){
            shareFrame.hide();
        },interval);
        
    });

});

