package org.xjtusicd3.portal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.portal.service.UserQuestionService;
 

public class UserQuestionController
{
	/*
	 * zpz_得到userQuestion
	 */
	@RequestMapping(value="incidentindex1",method=RequestMethod.GET)
	public ModelAndView user()
	{
		ModelAndView mv = new ModelAndView("incidentindex");
		String userQuestion = UserQuestionService.getUserQuestion();
		mv.addObject("userQuestion",userQuestion);
		return mv;
		
	}
}
