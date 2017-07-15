package org.xjtusicd3.portal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.portal.service.LogService;
import org.xjtusicd3.portal.service.rbacService;
import org.xjtusicd3.portal.view.logindexView;
import org.xjtusicd3.portal.view.rolePerView;

@Controller
public class rbacController
{
	/*
	 * zpz_Log_查看Log
	 */
	@RequestMapping(value="rbacindex",method=RequestMethod.GET)
	public  ModelAndView advise()
	{
		ModelAndView mv = new ModelAndView("rbacindex");
		List<rolePerView> rolePerViews = rbacService.getRolePer();
		mv.addObject("rolePer_list",rolePerViews);
		return mv;
	 
	}
}
