package org.xjtusicd3.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.helper.UserQuestionHelper;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.portal.service.UserQuestionService;
 

public class UserQuestionController
{
	/*
	 * zpz_得到userQuestion
	 */
	@RequestMapping(value="incidentindex1",method=RequestMethod.GET)
	public ModelAndView user()
	{
		ModelAndView mv = new ModelAndView("incidentindex");
		String userQuestion = UserQuestionService.getUserQuestion();
		mv.addObject("userQuestion",userQuestion);
		return mv;
		
	}
	
	/*
	 * ZPZ_deleteUserQuestion
	 */
	@ResponseBody
	@RequestMapping(value={"/deleteUserQuestion"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	public void deleteUser(HttpServletRequest request){
		String userQuestionId = request.getParameter("userquestionid");
		UserQuestionHelper.deleteUserQuestion(userQuestionId);
		System.out.println(userQuestionId);
	}
	
	/*
	 * zpz_showUserInfo
	 */
	@RequestMapping(value="showUserInfo",method=RequestMethod.GET)
	public ModelAndView showUserInfo(String u){
		List<UserPersistence> userPersistences = UserHelper.getEmail_id(u);
		ModelAndView modelAndView = new ModelAndView("showUserInfo");
		modelAndView.addObject("userInfoList", userPersistences);
		return modelAndView;
	}
	
}
