package org.xjtusicd3.partner.controller;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.partner.service.ClassifyService;

public class ClassifyController {
	/*
	 * root_分类
	 */
	@RequestMapping(value="robot",method=RequestMethod.GET)
	public static  void classifyName1(){
		ModelAndView modelAndView = new ModelAndView("robot");
		String string = ClassifyService.classify();
		modelAndView.addObject("string", string);
	}
//	public static void main(String[] args) {
//		classifyName1();
//	}
}
