package org.xjtusicd3.partner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("message")
public class MessageController {
	@RequestMapping(value="main",method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String messageIndexPage() {
		return "liuyan/index";
	}
}
