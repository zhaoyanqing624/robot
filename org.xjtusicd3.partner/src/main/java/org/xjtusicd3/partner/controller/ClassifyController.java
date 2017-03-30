package org.xjtusicd3.partner.controller;

import java.util.List;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.partner.service.ClassifyService;
import org.xjtusicd3.partner.view.ClassifyNameView;
import org.xjtusicd3.partner.view.ClassifyView;

import net.sf.json.JSONArray;
public class ClassifyController {
	/*
	 * root_分类
	 */
	@RequestMapping(value="robot",method=RequestMethod.GET)
	public ModelAndView classify_robot(){
		ModelAndView modelAndView = new ModelAndView("robot");
		List<ClassifyView> classifyViews = ClassifyService.classify_robot();
		modelAndView.addObject("classifyViews", classifyViews);
		return modelAndView;
	}
	
	//@RequestMapping(value="robot",method=RequestMethod.GET)
	public static   void classifyName1(){
		ModelAndView modelAndView = new ModelAndView("robot");
		List<ClassifyNameView> classifyNameViews = ClassifyService.classifyName1();
		JSONArray jsonArray = JSONArray.fromObject(classifyNameViews);
		System.out.println(jsonArray);
	}
	
	public static void main(String[] args) {
		System.out.println();
	}
}
