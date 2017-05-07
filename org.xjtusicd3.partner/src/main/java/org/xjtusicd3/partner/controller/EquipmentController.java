package org.xjtusicd3.partner.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xjtusicd3.database.model.EquipmentPersistence;
import org.xjtusicd3.partner.filter.SystemDriver;
import org.xjtusicd3.partner.filter.SystemSigar;
import org.xjtusicd3.partner.service.EquipmentService;

@Controller
public class EquipmentController {
	/*
	 * zyq_ajax_实时获取当前设备资源
	 */
	@ResponseBody
	@RequestMapping(value={"/getCurrentEquipment"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	public String getCurrentEquipment(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		if (useremail==null) {
			return "redirect:login.html";
		}else {
			EquipmentService 
		}
		return null;
	}
}
