package org.xjtusicd3.partner.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.partner.service.ClassifyService;
import org.xjtusicd3.partner.service.QuestionService;
import org.xjtusicd3.partner.view.Faq1_ClassifyView;
import org.xjtusicd3.partner.view.Faq2_faqContentView;
import org.xjtusicd3.partner.view.Faq3_faqContentView;

import com.alibaba.fastjson.JSONObject;
@Controller
public class FaqController {
	/*
	 * faq、faq1_上侧的第二级分类
	 */
	@RequestMapping(value="faq1",method=RequestMethod.GET)
	public ModelAndView classifyName2(String p){
		ModelAndView modelAndView = new ModelAndView("faq1");
		List<ClassifyPersistence> list = ClassifyHelper.faq1_ClassifyName(p);
		List<Faq1_ClassifyView> list2 = ClassifyService.faq1_ClassifyView(p);
		if (list == null || list.size()==0) {
			return null;
		}
		modelAndView.addObject("faq1_list", list);
		modelAndView.addObject("faq1_list2", list2);
		return modelAndView;
	}
	/*
	 * faq2_知识列表
	 */
	@RequestMapping(value="faq2",method=RequestMethod.GET)
	public ModelAndView faqList(String c){
		ModelAndView modelAndView = new ModelAndView("faq2");
		List<ClassifyPersistence> classify2 = ClassifyService.faq2_classify2(c);
		List<ClassifyPersistence> classify = ClassifyService.faq2_classify(c);
		List<Faq2_faqContentView> faq2Views = QuestionService.faqlist_faq2(c,1);
		modelAndView.addObject("faq2_list", classify);
		modelAndView.addObject("faq2_list2", classify2);
		modelAndView.addObject("faq2_list3", faq2Views);
		return modelAndView;
	}
	/*
	 * faq2_ajax请求更多知识列表
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreFaqList"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String faq2list(HttpServletRequest request,HttpServletResponse response){
		int pagenow = Integer.parseInt(request.getParameter("pagenow"));
		int pageNow = pagenow+1;
		String ClassifyId = request.getParameter("classifyId");
		List<Faq2_faqContentView> faq2Views = QuestionService.faqlist_faq2(ClassifyId, pageNow);
		int faqTotal = QuestionHelper.pageTotal(ClassifyId);
		int pageTotal = (int) Math.ceil((double)faqTotal/(double)5);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagenow", pageNow);
		jsonObject.put("faqlist", faq2Views);
		jsonObject.put("pageTotal",pageTotal);
		String faq2_list = JsonUtil.toJsonString(jsonObject);
		System.out.println(faq2_list);
		return faq2_list;
	 }
	/*
	 * faq3_知识内容
	 */
	@RequestMapping(value="faq3",method=RequestMethod.GET)
	public ModelAndView faqContent(String q){
		ModelAndView modelAndView = new ModelAndView("faq3");
		String classifyId = QuestionHelper.faqclassify(q);
		List<ClassifyPersistence> classify2 = ClassifyService.faq2_classify2(classifyId);
		List<ClassifyPersistence> classify = ClassifyService.faq2_classify(classifyId);
		List<Faq3_faqContentView> faq3Views = QuestionService.faq3_faqcontent(q);
		modelAndView.addObject("classify", classify);
		modelAndView.addObject("classify2", classify2);
		modelAndView.addObject("faq3Views", faq3Views);
		return modelAndView;
	}
}
