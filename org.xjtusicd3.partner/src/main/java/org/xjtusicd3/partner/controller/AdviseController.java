package org.xjtusicd3.partner.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xjtusicd3.partner.service.AdviseService;
import org.xjtusicd3.partner.view.AdviseView;

@Controller

public class AdviseController {
	/*
	 * zyq_advise_意见和建议增加
	 */
	@RequestMapping(value="/saveAdvise",method=RequestMethod.POST)
	public String saveAdvise(AdviseView adviseView,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String urlPath = (String) session.getAttribute("urlPath");
		if (useremail==null) {
			return "redirect:login.html";
		}else {
			String email = adviseView.getEMAIL();
			String name = adviseView.getNAME();
			String phone = adviseView.getPHONE();
			String text = adviseView.getTEXT();
			System.out.println(name);
			AdviseService.saveAdvise(useremail, email, name, phone, text);
			return "redirect:robot.html";
		}
	}
	
}
