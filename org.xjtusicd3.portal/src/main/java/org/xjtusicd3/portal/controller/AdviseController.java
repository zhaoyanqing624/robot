package org.xjtusicd3.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.helper.AdviseHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AdvisePersistence;
import org.xjtusicd3.portal.service.AdviseService;

@Controller
public class AdviseController
{
	/*
	 * zpz_advise_查看advise
	 */
	@RequestMapping(value="messageindex",method=RequestMethod.GET)
	public  ModelAndView advise()
	{
		ModelAndView mv = new ModelAndView("messageindex");
		List<AdvisePersistence> advise = AdviseService.getAdvise();
		mv.addObject("advise_list",advise);
		return mv;
	 
		
	}
	/*
	 * ZPZ_deleteUserAdvise
	 */
	@ResponseBody
	@RequestMapping(value={"/deleteAdvise"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	public void deleteUser(HttpServletRequest request){
		String adviseid = request.getParameter("adviseid");
		AdviseHelper.deleteAdvise(adviseid);
		System.out.println(adviseid);
	}
	 
}