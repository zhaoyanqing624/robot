package org.xjtusicd3.portal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.model.AdvisePersistence;
import org.xjtusicd3.portal.service.AdviseService;

@Controller
public class AdviseController
{
	/*
	 * zpz_advise_查看advise
	 */
	@RequestMapping(value="messageindex",method=RequestMethod.GET)
	public ModelAndView advise()
	{
		ModelAndView mv = new ModelAndView("messageindex");
		List<AdvisePersistence> advise = AdviseService.getAdvise();
		mv.addObject("advise_list",advise);
		return mv;
		
	}
}
