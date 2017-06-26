<!DOCTYPE html>
<html>
    <head>
        <title>jQuery Scrollbar Demo</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/websocket.js"></script>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="new/front/style/reset2.css" />
    <link rel="stylesheet" type="text/css" href="new/front/style/util2.css" />
    <link href="css/main.css" rel="stylesheet">
    <link href="css/bootstrap.min2.css" rel="stylesheet">
    <link href="css/css.css" rel="stylesheet">
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="includes/style.css">
    <link rel="stylesheet" href="includes/prettify/prettify.css">
    <link rel="stylesheet" href="css/scrollbar.css">
    <script src="includes/prettify/prettify.js"></script>
    <script src="includes/jquery.js"></script>
    <script src="js/jquery.scrollbar.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function(){
            jQuery('.scrollbar-macosx').scrollbar();
        });
    </script>

        <style type="text/css" id="css-common">
            /*************** SCROLLBAR BASE CSS ***************/

            .scroll-wrapper {
                overflow: hidden !important;
                padding: 0 !important;
                position: relative;
            }

            .scroll-wrapper > .scroll-content {
                border: none !important;
                box-sizing: content-box !important;
                height: auto;
                left: 0;
                margin: 0;
                max-height: none;
                max-width: none !important;
                overflow: scroll !important;
                padding: 0;
                position: relative !important;
                top: 0;
                width: auto !important;
            }

            .scroll-wrapper > .scroll-content::-webkit-scrollbar {
                height: 0;
                width: 0;
            }

            .scroll-element {
                display: none;
            }
            .scroll-element, .scroll-element div {
                box-sizing: content-box;
            }

            .scroll-element.scroll-x.scroll-scrollx_visible,
            .scroll-element.scroll-y.scroll-scrolly_visible {
                display: block;
            }

            .scroll-element .scroll-bar,
            .scroll-element .scroll-arrow {
                cursor: default;
            }

            .scroll-textarea {
                border: 1px solid #cccccc;
                border-top-color: #999999;
            }
            .scroll-textarea > .scroll-content {
                overflow: hidden !important;
            }
            .scroll-textarea > .scroll-content > textarea {
                border: none !important;
                box-sizing: border-box;
                height: 100% !important;
                margin: 0;
                max-height: none !important;
                max-width: none !important;
                overflow: scroll !important;
                outline: none;
                padding: 2px;
                position: relative !important;
                top: 0;
                width: 100% !important;
            }
            .scroll-textarea > .scroll-content > textarea::-webkit-scrollbar {
                height: 0;
                width: 0;
            }




        </style>
    </head>
    <body>



                    <div class="content">
                        <style type="text/css">

                            /*************** SCROLLBAR MAC OS X ***************/

                            .scrollbar-macosx > .scroll-element,
                            .scrollbar-macosx > .scroll-element div
                            {
                                background: none;
                                border: none;
                                margin: 0;
                                padding: 0;
                                position: absolute;
                                z-index: 10;
                            }

                            .scrollbar-macosx > .scroll-element div {
                                display: block;
                                height: 100%;
                                left: 0;
                                top: 0;
                                width: 100%;
                            }

                            .scrollbar-macosx > .scroll-element .scroll-element_track { display: none; }
                            .scrollbar-macosx > .scroll-element .scroll-bar {
                                background-color: #6C6E71;
                                display: block;

                                -ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
                                filter: alpha(opacity=0);
                                opacity: 0;

                                -webkit-border-radius: 7px;
                                -moz-border-radius: 7px;
                                border-radius: 7px;

                                -webkit-transition: opacity 0.2s linear;
                                -moz-transition: opacity 0.2s linear;
                                -o-transition: opacity 0.2s linear;
                                -ms-transition: opacity 0.2s linear;
                                transition: opacity 0.2s linear;
                            }
                            .scrollbar-macosx:hover > .scroll-element .scroll-bar,
                            .scrollbar-macosx > .scroll-element.scroll-draggable .scroll-bar {
                                -ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=70)";
                                filter: alpha(opacity=70);
                                opacity: 0.7;
                            }


                            .scrollbar-macosx > .scroll-element.scroll-x {
                                bottom: 0px;
                                height: 0px;
                                left: 0;
                                min-width: 100%;
                                overflow: visible;
                                width: 100%;
                            }

                            .scrollbar-macosx > .scroll-element.scroll-y {
                                height: 100%;
                                min-height: 100%;
                                right: 0px;
                                top: 0;
                                width: 0px;
                            }

                            /* scrollbar height/width & offset from container borders */
                            .scrollbar-macosx > .scroll-element.scroll-x .scroll-bar { height: 7px; min-width: 10px; top: -9px; }
                            .scrollbar-macosx > .scroll-element.scroll-y .scroll-bar { left: -9px; min-height: 10px; width: 7px; }

                            .scrollbar-macosx > .scroll-element.scroll-x .scroll-element_outer { left: 2px; }
                            .scrollbar-macosx > .scroll-element.scroll-x .scroll-element_size { left: -4px; }

                            .scrollbar-macosx > .scroll-element.scroll-y .scroll-element_outer { top: 2px; }
                            .scrollbar-macosx > .scroll-element.scroll-y .scroll-element_size { top: -4px; }

                            /* update scrollbar offset if both scrolls are visible */
                            .scrollbar-macosx > .scroll-element.scroll-x.scroll-scrolly_visible .scroll-element_size { left: -11px; }
                            .scrollbar-macosx > .scroll-element.scroll-y.scroll-scrollx_visible .scroll-element_size { top: -11px; }

                        </style>
                        <div class="demo">
                            <div class="scrollbar-macosx">
                                <p class="permanent">
                                    Traditionally scrollbars are permanently displayed whenever an area of a webpage is
                                    scrollable. By contrast, scrollbars in OSX Lion are hidden from sight, and revealed only
                                    when scrolling content. In this example, scrollbars are revealed when mouse is over content.
                                </p>
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Proin nibh augue, suscipit a,
                                    scelerisque sed, lacinia in, mi. Cras vel lorem. Etiam pellentesque aliquet tellus.
                                    Phasellus pharetra nulla ac diam. Quisque semper justo at risus. Donec venenatis, turpis vel
                                    hendrerit interdum, dui ligula ultricies purus, sed posuere libero dui id orci. Nam congue,
                                    pede vitae dapibus aliquet, elit magna vulputate arcu, vel tempus metus leo non est. Etiam
                                    sit amet lectus quis est congue mollis. Phasellus congue lacus eget neque. Phasellus ornare,
                                    ante vitae consectetuer consequat, purus sapien ultricies dolor, et mollis pede metus eget
                                    nisi. Praesent sodales velit quis augue. Cras suscipit, urna at aliquam rhoncus, urna quam
                                    viverra nisi, in interdum massa nibh nec erat.
                                </p>
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Proin nibh augue, suscipit a,
                                    scelerisque sed, lacinia in, mi. Cras vel lorem. Etiam pellentesque aliquet tellus.
                                    Phasellus pharetra nulla ac diam. Quisque semper justo at risus. Donec venenatis, turpis vel
                                    hendrerit interdum, dui ligula ultricies purus, sed posuere libero dui id orci. Nam congue,
                                    pede vitae dapibus aliquet, elit magna vulputate arcu, vel tempus metus leo non est. Etiam
                                    sit amet lectus quis est congue mollis. Phasellus congue lacus eget neque. Phasellus ornare,
                                    ante vitae consectetuer consequat, purus sapien ultricies dolor, et mollis pede metus eget
                                    nisi. Praesent sodales velit quis augue. Cras suscipit, urna at aliquam rhoncus, urna quam
                                    viverra nisi, in interdum massa nibh nec erat.
                                </p>
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Proin nibh augue, suscipit a,
                                    scelerisque sed, lacinia in, mi. Cras vel lorem. Etiam pellentesque aliquet tellus.
                                    Phasellus pharetra nulla ac diam. Quisque semper justo at risus. Donec venenatis, turpis vel
                                    hendrerit interdum, dui ligula ultricies purus, sed posuere libero dui id orci. Nam congue,
                                    pede vitae dapibus aliquet, elit magna vulputate arcu, vel tempus metus leo non est. Etiam
                                    sit amet lectus quis est congue mollis. Phasellus congue lacus eget neque. Phasellus ornare,
                                    ante vitae consectetuer consequat, purus sapien ultricies dolor, et mollis pede metus eget
                                    nisi. Praesent sodales velit quis augue. Cras suscipit, urna at aliquam rhoncus, urna quam
                                    viverra nisi, in interdum massa nibh nec erat.
                                </p>
                            </div>
                        </div>
                        
    <script type="text/javascript" src="new/front/js/util.js"></script>
    <script src="js/autosize.js"></script>
    <script>
    	$(document).ready(function(){
    		autosize(document.querySelectorAll('textarea'));
    	})
    	//好友私信列表显示
		$('#lastChat').find('li').click(function(){
			$(this).addClass("active").siblings("li").removeClass("active");
			var userid = $(this)[0].id.split("lastChat")[1];
			var usercontentid = "userchatUl"+userid;
			$('#'+usercontentid).css('display','block').siblings("ul").css('display','none');
			if(document.getElementById("messagenumber")!=null){
				document.getElementById("messagenumber").innerHTML="";
			}
			var touserId = document.getElementById("lastChat").getElementsByClassName("active")[0].id.split("lastChat")[1];
			//点击列表后开始查询
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/getMessage.html",
				data:{
					"touserId":touserId
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}else{
						if(data.messageContentList==""){
							if(data.messageHistory!=""){
								if(data.isMore=="1"){
									var html = document.getElementById("userchatUl"+touserId).innerHTML;
									document.getElementById("userchatUl"+touserId).innerHTML = html+'<li><div class="getmore"><strong style="width:130px;">查看更多记录</strong></div></li>';
									for(var j in data.messageHistory){
										if(data.messageHistory[j].userId==document.getElementById("zhao_hidden").innerHTML){
											if(document.getElementById(data.messageHistory[j].messageId)==null){
											 	if(document.getElementById(data.messageHistory[j].time.substring(0,10))==null){
											 		var htmls = document.getElementById("userchatUl"+touserId).innerHTML;
											 		document.getElementById("userchatUl"+touserId).innerHTML =htmls + '<li id="'+data.messageHistory[j].time.substring(0,10)+'"><div class="timeLine"><strong style="width:130px;">'+data.messageHistory[j].time.substring(0,10)+'</strong></div></li>';
											 	}
												var html = document.getElementById("userchatUl"+touserId).innerHTML;
												document.getElementById("userchatUl"+touserId).innerHTML = html+'<li class="me" id="'+data.messageHistory[j].messageId+'"><div class="chat_avata"><a href="personal2.html?u='+data.messageHistory[j].userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageHistory[j].userImage+'"></a></div><div class="a_msg_info"><pre>'+data.messageHistory[j].content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageHistory[j].time+'</small></li>';
											}
											
										}else{
											if(document.getElementById(data.messageHistory[j].messageId)==null){
												if(document.getElementById(data.messageHistory[j].time.substring(0,10))==null){
											 		var htmls = document.getElementById("userchatUl"+touserId).innerHTML;
											 		document.getElementById("userchatUl"+touserId).innerHTML =htmls + '<li id="'+data.messageHistory[j].time.substring(0,10)+'"><div class="timeLine"><strong style="width:130px;">'+data.messageHistory[j].time.substring(0,10)+'</strong></div></li>';
											 	}
												var html = document.getElementById("userchatUl"+touserId).innerHTML;
												document.getElementById("userchatUl"+touserId).innerHTML = html+'<li class="you" id="'+data.messageHistory[j].messageId+'"><div class="chat_avata"><a href="personal2.html?u='+data.messageHistory[j].userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageHistory[j].userImage+'"></a></div><div class="a_msg_info"><pre>'+data.messageHistory[j].content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageHistory[j].time+'</small></li>';
											}
										}
									}
								}else{
									for(var j in data.messageHistory){
										if(data.messageHistory[j].userId==document.getElementById("zhao_hidden").innerHTML){
											if(document.getElementById(data.messageHistory[j].messageId)==null){
												if(document.getElementById(data.messageHistory[j].time.substring(0,10))==null){
											 		var htmls = document.getElementById("userchatUl"+touserId).innerHTML;
											 		document.getElementById("userchatUl"+touserId).innerHTML =htmls + '<li id="'+data.messageHistory[j].time.substring(0,10)+'"><div class="timeLine"><strong style="width:130px;">'+data.messageHistory[j].time.substring(0,10)+'</strong></div></li>';
											 	}
												var html = document.getElementById("userchatUl"+touserId).innerHTML;
												document.getElementById("userchatUl"+touserId).innerHTML = html+'<li class="me" id="'+data.messageHistory[j].messageId+'"><div class="chat_avata"><a href="personal2.html?u='+data.messageHistory[j].userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageHistory[j].userImage+'"></a></div><div class="a_msg_info"><pre>'+data.messageHistory[j].content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageHistory[j].time+'</small></li>';
											}
										}else{
											if(document.getElementById(data.messageHistory[j].messageId)==null){
												if(document.getElementById(data.messageHistory[j].time.substring(0,10))==null){
											 		var htmls = document.getElementById("userchatUl"+touserId).innerHTML;
											 		document.getElementById("userchatUl"+touserId).innerHTML =htmls + '<li id="'+data.messageHistory[j].time.substring(0,10)+'"><div class="timeLine"><strong style="width:130px;">'+data.messageHistory[j].time.substring(0,10)+'</strong></div></li>';
											 	}
												var html = document.getElementById("userchatUl"+touserId).innerHTML;
												document.getElementById("userchatUl"+touserId).innerHTML = html+'<li class="you" id="'+data.messageHistory[j].messageId+'"><div class="chat_avata"><a href="personal2.html?u='+data.messageHistory[j].userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageHistory[j].userImage+'"></a></div><div class="a_msg_info"><pre>'+data.messageHistory[j].content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageHistory[j].time+'</small></li>';
											}
										}
									}
								}
							}
						}else{
							if(data.isMore=="1"){
								var html = document.getElementById("userchatUl"+touserId).innerHTML;
								document.getElementById("userchatUl"+touserId).innerHTML = html+'<li><div class="getmore"><strong style="width:130px;">查看更多记录</strong></div></li>';
								for(var i in data.messageContentList){
									var html = document.getElementById("userchatUl"+touserId).innerHTML;
									if(document.getElementById(data.messageContentList[i].messageId)==null){
										if(document.getElementById(data.messageContentList[i].time.substring(0,10))==null){
									 		var htmls = document.getElementById("userchatUl"+touserId).innerHTML;
									 		document.getElementById("userchatUl"+touserId).innerHTML =htmls + '<li id="'+data.messageContentList[i].time.substring(0,10)+'"><div class="timeLine"><strong style="width:130px;">'+data.messageContentList[i].time.substring(0,10)+'</strong></div></li>';
									 	}
										document.getElementById("userchatUl"+touserId).innerHTML = html + '<li class="you" id="'+data.messageContentList[i].messageId+'"><div class="chat_avata"><a href="personal2.html?u='+data.messageContentList[i].userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageContentList[i].userImage+'"></a></div><div class="a_msg_info" id="4426066"><pre>'+data.messageContentList[i].content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageContentList[i].time+'</small></li>';
									}
								}
							}else{
								for(var i in data.messageContentList){
									var html = document.getElementById("userchatUl"+touserId).innerHTML;
									if(document.getElementById(data.messageContentList[i].messageId)==null){
										if(document.getElementById(data.messageContentList[i].time.substring(0,10))==null){
									 		var htmls = document.getElementById("userchatUl"+touserId).innerHTML;
									 		document.getElementById("userchatUl"+touserId).innerHTML =htmls + '<li id="'+data.messageContentList[i].time.substring(0,10)+'"><div class="timeLine"><strong style="width:130px;">'+data.messageContentList[i].time.substring(0,10)+'</strong></div></li>';
									 	}
										document.getElementById("userchatUl"+touserId).innerHTML = html + '<li class="you" id="'+data.messageContentList[i].messageId+'"><div class="chat_avata"><a href="personal2.html?u='+data.messageContentList[i].userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageContentList[i].userImage+'"></a></div><div class="a_msg_info" id="4426066"><pre>'+data.messageContentList[i].content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageContentList[i].time+'</small></li>';
									}
								}
							}
						}
					}
				}
			});			
		})
    </script>
    <script>
    	function sendMessage(){
    		var content = document.getElementById("textInput").value;
    		var touserId = document.getElementById("lastChat").getElementsByClassName("active")[0].id.split("lastChat")[1];
			$.ajax({
				type:"POST",
				url:"/org.xjtusicd3.partner/saveMessage.html",
				data:{
					"content":content,
					"touserId":touserId
				},
				dataType:"json",
				success:function(data){
					if(data.value=="0"){
						self.location='login.html';
					}else{
						var html = document.getElementById("userchatUl"+touserId).innerHTML;
						document.getElementById("userchatUl"+touserId).innerHTML= html + '<li class="me"><div class="chat_avata"><a href="personal2.html?u='+data.messageList.userId+'" target="_blank"><img width="40" height="40" class="img_border_one" src="'+data.messageList.userImage+'"></a></div><div class="a_msg_info"><pre>'+data.messageList.content+'</pre><i class="arrow_left_b"></i></div><small class="time">'+data.messageList.time+'</small></li>';
						document.getElementById("textInput").value="";
					}
				}
			})
    	}
    </script>
                

    </body>
</html>
