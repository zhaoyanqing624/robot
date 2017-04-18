package org.xjtusicd3.partner.controller;




import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.partner.service.ClassifyService;

@Controller
public class ClassifyController {
//	/*
//	 * robot_分类
//	 */
//	@RequestMapping(value="robot",method=RequestMethod.GET)
//	public ModelAndView classifyName(){
//		ModelAndView modelAndView = new ModelAndView("robot");
//		String string = ClassifyService.classify();
//		modelAndView.addObject("string",string);
//		return modelAndView;
//	}
//	/*
//	 * faq、faq1_右侧的第一级分类
//	 */
//	@ResponseBody
//	@RequestMapping(value={"/getFirstLevel"},method={org.springframework.web.bind.annotation.RequestMethod.GET},produces="text/plain;charset=UTF-8")
//	public  String search(HttpServletResponse response){
//		response.setContentType("application/json");
//		response.setCharacterEncoding("utf-8");
//		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName1();
//		if (classifyPersistences == null || classifyPersistences.size()==0) {
//			return null;
//		}			
//		String result = JsonUtil.toJsonString(classifyPersistences);
//		return result;
//	 }
//	/*
//	 * ajax获取第二级分类
//	 */
//	@ResponseBody
//	@RequestMapping(value={"/getSecondLevel"},method={org.springframework.web.bind.annotation.RequestMethod.GET},produces="text/plain;charset=UTF-8")
//	public  String search2(int classifyId,HttpServletResponse response){
//		response.setContentType("application/json");
//		response.setCharacterEncoding("utf-8");
//		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName2_2(classifyId);
//		if (classifyPersistences == null || classifyPersistences.size()==0) {
//			return null;
//		}			
//		String result = JsonUtil.toJsonString(classifyPersistences);
//		return result;
//	 }
	
}
