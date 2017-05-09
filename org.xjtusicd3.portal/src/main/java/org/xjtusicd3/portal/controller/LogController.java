package org.xjtusicd3.portal.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.model.LogPersistence;
import org.xjtusicd3.portal.service.LogService;

@Controller
public class LogController
{
	/*
	 * zpz_Log_查看Log
	 */
	@RequestMapping(value="logindex",method=RequestMethod.GET)
	public  ModelAndView advise()
	{
		ModelAndView mv = new ModelAndView("logindex");
		List<LogPersistence> log = LogService.getLog();
		mv.addObject("log_list",log);
		return mv;
	 
		
	}
	 
}