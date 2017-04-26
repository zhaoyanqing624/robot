package org.xjtusicd3.portal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.model.ConfigurePersistence;
import org.xjtusicd3.portal.service.ConfigureService;

@Controller
public class ConfigureController {

	/*
	 * zpz_²é¿´configure
	 */
	@RequestMapping(value="cfgindex",method=RequestMethod.GET)
	public ModelAndView configure() {
		ModelAndView mv = new ModelAndView("cfgindex");
		String result = ConfigureService.getPartConfig();
		mv.addObject("result",result);
		return mv;
	}
}
