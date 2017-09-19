    		<div class="loginRegistHead" role="banner">
			<div class="content clearfix">
                <div class="header_top_wrap_left">
		            <ul>
		                <li><a class="new_a" href="robot.html" data-pos="categorys_1_1">智能小朵</a></li>
		                <li><a class="new_a" href="faq.html" data-pos="categorys_1_1">知识库</a></li>
		                <li><a class="new_a" href="question.html?c=all&type=all" data-pos="categorys_1_1">问题中心</a></li>
		                <li><a class="new_a" href="advise.html" data-pos="categorys_1_1">意见建议</a></li>
		                <li>
		                    <a class="new_a" href="service.html">关于我们</a>
		                </li>
		            </ul> 
                </div>
				<div class="header_top_wrap_right">
					<ul>
						<#if UserEmail??>
						<div class="unlogin">
							<li class="loginLinkLi"><span class="person_icon"></span></li>
							<li class="loginLinkLi" id="userNameText">您好：${UserEmail}</li>
							<li class="left_margin my_center loginLinkLi" id="my_center" onmouseover="Util.showPersonCenter()" onmouseout="Util.hidePersonCenter()">个人中心<span class="v_center_arrow"></span>
								<div class="my_service_list" style="display: none; height: 126px; padding-top: 0px; margin-top: 0px; padding-bottom: 0px; margin-bottom: 0px;">
									<div class="top_icon"></div>
									<ul class="ul_list">
										<li><a href="personal.html">个人信息</a></li>
										<li><a id="equipment" href="personal3.html">我的设备</a></li>
										<li><a href="personal2.html">我的主页</a></li>
										<li><a href="notice.html">消息通知</a></li>
									</ul>
								</div>
							</li>
							<li class="left_margin loginLinkLi"><a href="loginout.html" id="headExit">退出</a></li>
						</div>

						<#else>
						<div class="unlogin">
							<li class="unloginLinkLi">
								<a href="login.html" id="headLogin" class="listen_btn" data-pos="categorys_1_2">登录/注册</a>
							</li>
						</div>
						</#if>
					</ul> 
				</div>
			</div>
		</div>
<script type="text/javascript">


</script>