package org.xjtusicd3.partner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("test")
public class LTestController {
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String name() {
		return "index";
	}
	
	@RequestMapping(value="analyse",method=RequestMethod.GET)
	public String getanpage() {
		return "patent/analyse";
	}
	
	@RequestMapping(value="search_result",method=RequestMethod.GET)
	public String getSearchResult() {
		return "patent/searchResult";
	}
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register() {
		return "user/register";
	}
	
	@RequestMapping(value="usercenter",method=RequestMethod.GET)
	public String usercenter() {
		return "user/user_center";
	}
}
