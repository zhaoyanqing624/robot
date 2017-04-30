package org.xjtusicd3.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.portal.service.UserService;

@Controller
public class UserController 
{
	/*
	 * zpz_µÃµ½user
	 */
	@RequestMapping(value="userindex",method=RequestMethod.GET)
	public ModelAndView user()
	{
		ModelAndView mv = new ModelAndView("userindex");
		String user = UserService.getUser();
		mv.addObject("user",user);
		return mv;
		
	}
}
