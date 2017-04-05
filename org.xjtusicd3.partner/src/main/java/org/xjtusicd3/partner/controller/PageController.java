package org.xjtusicd3.partner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	@RequestMapping(value="test",method=RequestMethod.GET)
	public ModelAndView test(){
		ModelAndView mv = new ModelAndView("test");
		return mv;
	}
	@RequestMapping(value="test2",method=RequestMethod.GET)
	public ModelAndView test2(){
		ModelAndView mv = new ModelAndView("test2");
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
	@RequestMapping(value="service",method=RequestMethod.GET)
	public ModelAndView service(){
		ModelAndView mv = new ModelAndView("service");
		return mv;
	}
	@RequestMapping(value="personal",method=RequestMethod.GET)
	public ModelAndView personal(){
		ModelAndView mv = new ModelAndView("personal");
		return mv;
	}

	@RequestMapping(value="personal2",method=RequestMethod.GET)
	public ModelAndView personal2(){
		ModelAndView mv = new ModelAndView("personal2");
		return mv;
	}
	@RequestMapping(value="personal22",method=RequestMethod.GET)
	public ModelAndView personal22(){
		ModelAndView mv = new ModelAndView("personal22");
		return mv;
	}
	@RequestMapping(value="personal23",method=RequestMethod.GET)
	public ModelAndView personal23(){
		ModelAndView mv = new ModelAndView("personal23");
		return mv;
	}
	@RequestMapping(value="personal3",method=RequestMethod.GET)
	public ModelAndView personal3(){
		ModelAndView mv = new ModelAndView("personal3");
		return mv;
	}
	@RequestMapping(value="question",method=RequestMethod.GET)
	public ModelAndView question(){
		ModelAndView mv = new ModelAndView("question");
		return mv;
	}
	@RequestMapping(value="question2",method=RequestMethod.GET)
	public ModelAndView question2(){
		ModelAndView mv = new ModelAndView("question2");
		return mv;
	}
	@RequestMapping(value="faq",method=RequestMethod.GET)
	public ModelAndView faq(){
		ModelAndView mv = new ModelAndView("faq");
		return mv;
	}
	@RequestMapping(value="faq2",method=RequestMethod.GET)
	public ModelAndView faq2(){
		ModelAndView mv = new ModelAndView("faq2");
		return mv;
	}
	@RequestMapping(value="faq3",method=RequestMethod.GET)
	public ModelAndView faq3(){
		ModelAndView mv = new ModelAndView("faq3");
		return mv;
	}
	@RequestMapping(value="faqadd",method=RequestMethod.GET)
	public ModelAndView faqadd(){
		ModelAndView mv = new ModelAndView("faqadd");
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
	@RequestMapping(value="notice",method=RequestMethod.GET)
	public ModelAndView notice(){
		ModelAndView mv = new ModelAndView("notice");
		return mv;
	}
	@RequestMapping(value="message",method=RequestMethod.GET)
	public ModelAndView message(){
		ModelAndView mv = new ModelAndView("message");
		return mv;
	}
}
