package org.xjtusicd3.partner.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.partner.service.ClassifyService;
import org.xjtusicd3.partner.view.Classify_faq1View;
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
}
