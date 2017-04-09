package org.xjtusicd3.partner.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.FaqHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.partner.service.ClassifyService;
import org.xjtusicd3.partner.service.FaqService;
import org.xjtusicd3.partner.view.Classify_faq1View;
import org.xjtusicd3.partner.view.Faq_faq2View;
import org.xjtusicd3.partner.view.Faq_faq3View;

import com.alibaba.fastjson.JSONObject;
@Controller
public class FaqController {
	/*
	 * faq、faq1_上侧的第二级分类
	 */
	@RequestMapping(value="faq1",method=RequestMethod.GET)
	public ModelAndView classifyName2(int p){
		ModelAndView modelAndView = new ModelAndView("faq1");
		List<ClassifyPersistence> list = ClassifyService.classify_second(p);
		List<Classify_faq1View> list2 = ClassifyService.classify_faq1Views(p);
		if (list == null || list.size()==0) {
			return null;
		}
		modelAndView.addObject("classifyName2", list);
		modelAndView.addObject("Classify_faq1View", list2);
		return modelAndView;
	}
	/*
	 * faq2_知识列表
	 */
	@RequestMapping(value="faq2",method=RequestMethod.GET)
	public ModelAndView faqList(int c){
		ModelAndView modelAndView = new ModelAndView("faq2");
		List<ClassifyPersistence> classify2 = ClassifyService.classify2(c);
		List<ClassifyPersistence> classify = ClassifyService.classify(c);
		List<Faq_faq2View> faq2Views = FaqService.faqlist_faq2(c,1);
		modelAndView.addObject("classify", classify);
		modelAndView.addObject("classify2", classify2);
		modelAndView.addObject("faq2Views", faq2Views);
		return modelAndView;
	}
	/*
	 * faq2_ajax请求更多知识列表
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreFaqList"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String faq2list(HttpServletRequest request,HttpServletResponse response){
		int pagenow = Integer.parseInt(request.getParameter("pagenow"));
		int classifyId = Integer.parseInt(request.getParameter("classifyId"));
		List<Faq_faq2View> faq2Views = FaqService. faqlist_faq2(classifyId, pagenow);
		int pageNow = pagenow+1;
		String result = JsonUtil.toJsonString(faq2Views);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagenow", pageNow);
		jsonObject.put("faqlist", faq2Views);
		String faq2_list = JsonUtil.toJsonString(jsonObject);
		return faq2_list;
	 }
	/*
	 * faq3_知识内容
	 */
	@RequestMapping(value="faq3",method=RequestMethod.GET)
	public ModelAndView faqContent(int f){
		ModelAndView modelAndView = new ModelAndView("faq3");
		int classifyId = FaqHelper.faqclassify(f);
		List<ClassifyPersistence> classify2 = ClassifyService.classify2(classifyId);
		List<ClassifyPersistence> classify = ClassifyService.classify(classifyId);
		List<Faq_faq3View> faq3Views = FaqService.faqcontent_faq3(f);
		modelAndView.addObject("classify", classify);
		modelAndView.addObject("classify2", classify2);
		modelAndView.addObject("faq3Views", faq3Views);
		return modelAndView;
	}
}
