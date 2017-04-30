/**
 * 首页
 */

"use strict";

var pageCount=1;
var isLoading=false;
var isNoMore = false;
var isTagRequest;//用于终止用户选择多个标签时，上一个标签还没返回就发出下一个请求
// 加载handlebars-template
var homeQuestion = Handlebars.compile($('#home-question-template').html());
var quickAskTag = Handlebars.compile($('#quickAsk-tag-template').html());
var topicTag = Handlebars.compile($('#topic-tag-template').html());

$(function(){

	// //加载广告位
	// $.getJSON("",function(msg){
	// 	$('#mainR #ad a').attr('href',msg.adURL);
	// 	$('#mainR #ad img').attr('src',msg.imageURL);
	// })

	//载入时加载首页问题列表
	contentFlow();

	//加载内容流
	$(window).scroll(function(){
		if(($(window).scrollTop()+$(window).height())>parseInt($(document).height()*0.75)){//阅读了75%,加载内容流
			contentFlow();
		}
	});

	//载入时获取标签列表
	$.post('/api/ajax/tags/',function(msg){
		$('#mainR #tagFilter').append(topicTag(msg));
		$('#questionForm #systemTag').append(quickAskTag(msg));
	},'json');

	//载入时设置回答框的用户名和头像
	$('div.answer-editor .answerer img').attr('src',getCookie('avatar_file')?decodeURIComponent(getCookie('avatar_file')):"img/user-img.png");
	$('div.answer-editor .answerer p').text(getCookie('nick_name')?getCookie('nick_name'):"游客");

	//点击话题标签
	$('#mainR').delegate('#tagFilter a','click',function(){
		//标签的选中与取消
		if($(this).hasClass('selected'))
			$(this).removeClass('selected');
		else
			$(this).addClass('selected');
		//停止上一个请求
		if(isTagRequest){
			isTagRequest.abort();
		}
		//状态初始化
		pageCount=1;
		isNoMore=false;
		isLoading=false;
		$('#loadStatus>div').addClass('hidden').eq(0).removeClass('hidden');
		$('ul#searchResult').empty();
		contentFlow();
	});

	//快速提问框
	$('#quickAsk input').on('focus',function(){
		if(!getCookie('uid')){
			$('#top #notLoged').trigger('click');
			return;
		}
	});
	$('#quickAsk button').on('click',function(){
		if(getCookie('uid')){
			$('#ask').trigger('click');
			if($(this).prev().val()!=''){
				$('#questionForm #title').val($(this).prev().val());
				$('#title').trigger('input');
			}
		}
		else
			$('#top #notLoged').trigger('click');
	});

	//前端监控配置
	var monitor = lnv_api.frontMonitor.init({
		path: 'wenba: index'
	});
});

//加载内容流
function contentFlow(){
	//没有更多
	if(isNoMore)
		return;
	//已经向服务器发送过请求，且服务器还未返回
	if(isLoading)
		return;
	isLoading=true;
	getUnReadNotifyCount();
	//标签过滤
	if($('#tagFilter a.selected').length>0){
		var tag_id="";
		$('#tagFilter a.selected').each(function(i){
			if(0==i)
				tag_id+=$(this).attr('data-id');
			else
				tag_id+=("-"+$(this).attr('data-id'));
		});
		isTagRequest=$.ajax({
			url:"/api/ajax/tag_filter/",
			type:'get',
			//timeout:10000,
			data:{'page':pageCount,'tag_ids':tag_id,'answer_count':1},
			dataType:'json',
			success:function(msg){
				if(!isTagRequest)
					return;
				isLoading=false;
				//console.log(msg);
				if(msg.data&&msg.data.length>0){//返回数据
					$('#mainL ul#searchResult').append(homeQuestion(msg));
					pageCount++;
					$('#loadStatus>div').addClass('hidden').eq(0).removeClass('hidden');//加载中
	 				//字符串截取
	 				abbrDetailP();
	 				abbrAnswerer();

	 				if(msg.data.length<10){
	 					isNoMore=true;
	 					$('#loadStatus>div').addClass('hidden').eq(2).removeClass('hidden');//没有更多
	 				}
	 			}
	 			else{
	 				isNoMore=true;
	 				$('#loadStatus>div').addClass('hidden').eq(2).removeClass('hidden');
	 			}
	 		},
	 		error:function(){
	 			isLoading=false;
	 			$('#loadStatus>div').addClass('hidden').eq(1).removeClass('hidden');//加载更多
	 		}
	 	});
	}
	else{
		//直接加载
		isTagRequest=$.ajax({
			url:"/api/ajax/list/",
			type:'get',
			//timeout:10000,
			data:{'page':pageCount},
			dataType:'json',
			success:function(msg){
				if(!isTagRequest)
					return;
				isLoading=false;
				
				if(msg.data&&msg.data.length>0){//返回数据
					$('#mainL ul#searchResult').append(homeQuestion(msg));
					pageCount++;
					$('#loadStatus>div').addClass('hidden').eq(0).removeClass('hidden');//加载中
	 				//字符串截取
	 				abbrDetailP();
	 				abbrAnswerer();

	 				if(msg.data.length<10){
	 					isNoMore=true;
	 					$('#loadStatus>div').addClass('hidden').eq(2).removeClass('hidden');//没有更多
	 				}
	 			}
	 			else{
	 				isNoMore=true;
	 				$('#loadStatus>div').addClass('hidden').eq(2).removeClass('hidden');
	 			}
	 		},
	 		error:function(){
	 			isLoading=false;
	 			$('#loadStatus>div').addClass('hidden').eq(1).removeClass('hidden');//加载更多
	 		}
	 	});
	}
};