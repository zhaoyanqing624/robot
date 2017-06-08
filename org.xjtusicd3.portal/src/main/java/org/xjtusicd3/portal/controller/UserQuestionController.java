package org.xjtusicd3.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.helper.UserQuestionHelper;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.database.model.UserQuestionPersistence;
import org.xjtusicd3.portal.service.UserQuestionService;
import org.xjtusicd3.portal.view.IncidentindexView;
 
@Controller
public class UserQuestionController
{
	/*
	 * zpz_得到userQuestion
	 */
	@RequestMapping(value="incidentindex",method=RequestMethod.GET)
	public ModelAndView userQuestion()
	{
		ModelAndView mv = new ModelAndView("incidentindex");
		List<IncidentindexView> incidentindexViews = UserQuestionService.incidentindexViews();
		mv.addObject("incidentview",incidentindexViews);
		return mv;
		
	}
	
//	/*
//	 * ZPZ_deleteUserQuestion
//	 */
//	@ResponseBody
//	@RequestMapping(value={"/deleteUserQuestion"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
//	public void deleteUser(HttpServletRequest request){
//		String userQuestionId = request.getParameter("userquestionid");
//		UserQuestionHelper.deleteUserQuestion(userQuestionId);
//		System.out.println(userQuestionId);
//	}
//	
//	
	
}
