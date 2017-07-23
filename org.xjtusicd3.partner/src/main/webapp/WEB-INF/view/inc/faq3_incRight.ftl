<div class="rightBarWrapper knowledgeCount">
	<div class="barBox" id="countTplWrapper">
		<h3 class="box-title">知识统计</h3>
		<ul class="countList">
			<li><label>浏览次数：</label><span>${faq3Views.faqScan}次</span></li>
			<li><label>最近更新：</label><span>${faq3Views.faqModifytime}</span></li>
			<#list faq3Views.uList as ulist>
			<li><label>创建者：</label><a href="personal2.html?u=${ulist.userId}"
				class="creatUser">${ulist.userName}</a><a href="javascript:void(0);"
				class="attention" id="attentionLink"
				onclick="attention('10068673852')">+关注</a><a
				href="javascript:void(0);" class="attention overAttention hidden"
				id="overAttentionLink">+已关注</a></li> </#list>
			<li><label>编辑次数：</label><span>${faq3Views.faqWritetime}次</span></li>
			<li><label>评分：</label>
				<div class="atar_Show">
					<p tip="${score}"></p>
				</div></li>
		</ul>
	</div>

	<div style="margin-bottom: 20px;">
		<a href="" onclick="clickadd()"><img src="images/erweima.PNG"
			alt=""></a>
	</div>
	<div class="barBox relateKnowledge">
		<h3 class="box-title">相关知识</h3>
		<ul class="relateKnowledgeList" id="relateTplWrapper">
		<#assign n = faqSimilarity?size />
		<#if n gt 6>
		<#assign n = 5 />
		</#if>
		
		<#if n!=0>
		<#list 1..(n) as i>
		<#assign ls = faqSimilarity[i] />
		<#if ls.question?length gt 22>
			<li><a href="faq3.html?q=${ls.questionId}" target="_blank">${ls.question?substring(0,22)}...</a></li>
		<#else>
			<li><a href="faq3.html?q=${ls.questionId}" target="_blank">${ls.question}</a></li>
		</#if>
		</#list>
		</#if>			
		</ul>
	</div>
</div>