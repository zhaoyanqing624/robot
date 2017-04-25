package org.xjtusicd3.portal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.model.ConfigurePersistence;
import org.xjtusicd3.portal.service.ConfigureService;

public class ComputerController {

	/*
	 * computer_·ÖÀà
	 */
	@RequestMapping(value="computer",method=RequestMethod.GET)
	public ModelAndView classifyName() throws Exception{
		ModelAndView modelAndView = new ModelAndView("cfgindex");
		List<ConfigurePersistence> cp = ConfigureService.getAllConfig();
		modelAndView.addObject("cp",cp);
		return modelAndView;
	}
}
