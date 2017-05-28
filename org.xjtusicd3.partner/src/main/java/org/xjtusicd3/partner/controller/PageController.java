package org.xjtusicd3.partner.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	/*
	 * aaaa
	 */
	@RequestMapping(value="test",method=RequestMethod.GET)
	public ModelAndView test(){
		ModelAndView mv = new ModelAndView("test");
		return mv;
	}
	@RequestMapping(value="push",method=RequestMethod.GET)
	public ModelAndView push(){
		ModelAndView mv = new ModelAndView("push");
		return mv;
	}
	@RequestMapping(value="index",method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	@RequestMapping(value="login",method=RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
//	@RequestMapping(value="robot",method=RequestMethod.GET)
//	public ModelAndView robot(){
//		ModelAndView mv = new ModelAndView("robot");
//		return mv;
//	}
	@RequestMapping(value="404",method=RequestMethod.GET)
	public ModelAndView notfound(){
		ModelAndView mv = new ModelAndView("404");
		return mv;
	}
	@RequestMapping(value="advise",method=RequestMethod.GET)
	public ModelAndView advise(){
		ModelAndView mv = new ModelAndView("advise");
		return mv;
	}
	@RequestMapping(value="service",method=RequestMethod.GET)
	public ModelAndView service(){
		ModelAndView mv = new ModelAndView("service");
		return mv;
	}

	@RequestMapping(value="personal22",method=RequestMethod.GET)
	public ModelAndView personal22(HttpSession session,HttpServletRequest request){
		String useremail = (String) session.getAttribute("UserEmail");
		if (useremail==null) {
			return new ModelAndView("login");
		}else {
			ModelAndView modelAndView = new ModelAndView("personal22");
			return modelAndView;
		}
	}
	@RequestMapping(value="personal23",method=RequestMethod.GET)
	public ModelAndView personal23(){
		ModelAndView mv = new ModelAndView("personal23");
		return mv;
	}
	@RequestMapping(value="ueditor",method=RequestMethod.GET)
	public ModelAndView ueditor(){
		ModelAndView mv = new ModelAndView("ueditor");
		return mv;
	}
	@RequestMapping(value="/ueditor/dialogs/emotion/emotion",method=RequestMethod.GET)
	public ModelAndView emotion(){
		ModelAndView mv = new ModelAndView("/ueditor/dialogs/emotion/emotion");
		return mv;
	}
	@RequestMapping(value="/ueditor/dialogs/image/image",method=RequestMethod.GET)
	public ModelAndView image(){
		ModelAndView mv = new ModelAndView("/ueditor/dialogs/image/image");
		return mv;
	}
	@RequestMapping(value="/ueditor/dialogs/video/video",method=RequestMethod.GET)
	public ModelAndView video(){
		ModelAndView mv = new ModelAndView("/ueditor/dialogs/video/video");
		return mv;
	}
	@RequestMapping(value="/ueditor/dialogs/attachment/attachment",method=RequestMethod.GET)
	public ModelAndView attachment(){
		ModelAndView mv = new ModelAndView("/ueditor/dialogs/attachment/attachment");
		return mv;
	}
	@RequestMapping(value="/ueditor/dialogs/spechars/spechars",method=RequestMethod.GET)
	public ModelAndView spechars(){
		ModelAndView mv = new ModelAndView("/ueditor/dialogs/spechars/spechars");
		return mv;
	}
	@RequestMapping(value="/ueditor/dialogs/snapscreen/snapscreen",method=RequestMethod.GET)
	public ModelAndView snapscreen(){
		ModelAndView mv = new ModelAndView("/ueditor/dialogs/snapscreen/snapscreen");
		return mv;
	}
	@RequestMapping(value="questionadd",method=RequestMethod.GET)
	public ModelAndView questionadd(){
		ModelAndView mv = new ModelAndView("questionadd");
		return mv;
	}
	@RequestMapping(value="message",method=RequestMethod.GET)
	public ModelAndView message(){
		ModelAndView mv = new ModelAndView("message");
		return mv;
	}
}
