package org.xjtusicd3.partner.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.CommentPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.service.ClassifyService;
import org.xjtusicd3.partner.service.CommentService;
import org.xjtusicd3.partner.service.QuestionService;
import org.xjtusicd3.partner.service.UserService;
import org.xjtusicd3.partner.view.Faq1_ClassifyView;
import org.xjtusicd3.partner.view.Faq2_faqContentView;
import org.xjtusicd3.partner.view.Faq3_CommentView;
import org.xjtusicd3.partner.view.Faq3_faqContentView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
@Controller
public class FaqController {
	@RequestMapping(value="faq",method=RequestMethod.GET)
	public ModelAndView faq(HttpSession session,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("faq");
		String urlPath = request.getServletPath();
		session.setAttribute("urlPath", urlPath);
		return mv;
	}
	/*
	 * faq、faq1_上侧的第二级分类
	 */
	@RequestMapping(value="faq1",method=RequestMethod.GET)
	public ModelAndView classifyName2(HttpSession session,HttpServletRequest request,String p){
		ModelAndView modelAndView = new ModelAndView("faq1");
		List<ClassifyPersistence> list = ClassifyHelper.faq1_ClassifyName(p);
		List<Faq1_ClassifyView> list2 = ClassifyService.faq1_ClassifyView(p);
		if (list == null || list.size()==0) {
			return null;
		}
		modelAndView.addObject("faq1_list", list);
		modelAndView.addObject("faq1_list2", list2);
		String urlPath = request.getServletPath()+"?"+request.getQueryString().toString();
		session.setAttribute("urlPath", urlPath);
		return modelAndView;
	}
	/*
	 * faq2_知识列表
	 */
	@RequestMapping(value="faq2",method=RequestMethod.GET)
	public ModelAndView faqList(HttpSession session,HttpServletRequest request,String c){
		ModelAndView modelAndView = new ModelAndView("faq2");
		List<ClassifyPersistence> classify2 = ClassifyService.faq2_classify2(c);
		List<ClassifyPersistence> classify = ClassifyService.faq2_classify(c);
		List<Faq2_faqContentView> faq2Views = QuestionService.faqlist_faq2(c,1);
		modelAndView.addObject("faq2_list", classify);
		modelAndView.addObject("faq2_list2", classify2);
		modelAndView.addObject("faq2_list3", faq2Views);
		String urlPath = request.getServletPath()+"?"+request.getQueryString().toString();
		session.setAttribute("urlPath", urlPath);
		return modelAndView;
	}
	/*
	 * faq2_ajax请求更多知识列表
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreFaqList"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String faq2list(HttpServletRequest request,HttpServletResponse response,HttpSession session){
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
		return faq2_list;
	 }
	/*
	 * faq3_知识内容
	 */
	@RequestMapping(value="faq3",method=RequestMethod.GET)
	public ModelAndView faqContent(HttpSession session,HttpServletRequest request,String q){
		ModelAndView modelAndView = new ModelAndView("faq3");
		String classifyId = QuestionHelper.faqclassify(q);
		List<ClassifyPersistence> classify2 = ClassifyService.faq2_classify2(classifyId);
		List<ClassifyPersistence> classify = ClassifyService.faq2_classify(classifyId);
		List<Faq3_faqContentView> faq3Views = QuestionService.faq3_faqcontent(q);
		List<Faq3_CommentView> faq3_CommentViews = CommentService.faq3_comment(faq3Views.get(0).getQuestionId(),0);
		modelAndView.addObject("classify", classify);
		modelAndView.addObject("classify2", classify2);
		modelAndView.addObject("faq3Views", faq3Views);
		modelAndView.addObject("comment", faq3_CommentViews);
		String urlPath = request.getServletPath()+"?"+request.getQueryString().toString();
		session.setAttribute("urlPath", urlPath);
		return modelAndView;
	}
	/*
	 * zyq_ajax_FAQ的增加
	 */
	@ResponseBody
	@RequestMapping(value={"/saveFAQ"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String saveFAQ(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String url = (String) session.getAttribute("urlPath");
		if (useremail==null) {
			return "0";
		}else {
			String title = request.getParameter("title");
			String keywords = request.getParameter("keywords");
			String subspecialCategoryId = request.getParameter("subspecialCategoryId");
			String description = request.getParameter("description");
			String risk_prompt = request.getParameter("risk_prompt");
			String faqcontent = request.getParameter("faqcontent");
			List<QuestionPersistence> questionPersistences = QuestionHelper.faqadd_iscurrent(title,useremail);
			JSONObject jsonObject = new JSONObject();
			if (questionPersistences.size()==0) {
				QuestionService.saveFAQ(useremail,title,keywords,subspecialCategoryId,description,risk_prompt,faqcontent);
				jsonObject.put("value", "1");
				jsonObject.put("url", url);
				String result = JsonUtil.toJsonString(jsonObject);
				return result;
			}else {
				jsonObject.put("value", "2");
				jsonObject.put("url", url);
				String result = JsonUtil.toJsonString(jsonObject);
				return result;
			}
		}
	}
	/*
	 * zyq_faqadd_FAQ的增加页面
	 */
	@RequestMapping(value="faqadd",method=RequestMethod.GET)
	public ModelAndView faqadd(HttpSession session,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("faqadd");
		String urlPath = request.getHeader("REFERER");
		session.setAttribute("urlPath", urlPath);
		return mv;
	}
}
