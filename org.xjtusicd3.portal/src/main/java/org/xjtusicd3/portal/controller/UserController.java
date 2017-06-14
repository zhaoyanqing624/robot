package org.xjtusicd3.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.MvcNamespaceHandler;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.portal.service.UserService;

import com.jayway.jsonpath.spi.Mode;

@Controller
public class UserController 
{
	/*
	 * zpz_�õ�user
	 */
	@RequestMapping(value="userindex",method=RequestMethod.GET)
	public ModelAndView user()
	{
		ModelAndView mv = new ModelAndView("userindex");
		List<UserPersistence> userlist = UserService.getAllUserList();
		mv.addObject("allUserList",userlist);
		return mv;
		
	}
	
	/*
	 * zpz_showUserInfoDetail
	 */
	@RequestMapping(value="showUserInfo",method=RequestMethod.GET)
	public ModelAndView showUserInfo(String u){
		List<UserPersistence> userPersistences = UserHelper.getEmail_id(u);
		ModelAndView modelAndView = new ModelAndView("showUserInfo");
		modelAndView.addObject("userInfoList", userPersistences);
		return modelAndView;
	}
	//�����û�
	@RequestMapping(value="adminAddUser",method=RequestMethod.GET)
	public String adminAddUser(HttpServletRequest request,HttpServletResponse response){

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		//�ж������Ƿ�ע��
		List<UserPersistence> list = UserHelper.getEmail(email);
		if (list.size()==0) {
			UserService.login_register(email, password);
			return "0";
		}else {
			if (UserHelper.getEmail2(email, password).size()==0) {
				if (UserService.validateUserState(email)==false) {
					if (UserService.validateEmail(email)==true) {
						return "1";
					}else {
						UserHelper.deleteUser(email);
						UserService.login_register(email, password);
						return "0";
					}
				}else {
					return "1";
				}
			}else {
				if (UserService.validateUserState(email)==false) {
					if (UserService.validateEmail(email)==true) {
						return "2";
					}else {
						UserHelper.deleteUser(email);
						UserService.login_register(email, password);
						return "0";
					}
				}else {
					return "1";
				}
			}
		}
	
	}
	/*
	 * ZPZ_deleteUser
	 */
	@ResponseBody
	@RequestMapping(value={"/deleteUser"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	public void deleteUser(HttpServletRequest request){
		String userEmail = request.getParameter("userEmail");
		UserHelper.deleteUser(userEmail);
		System.out.println(userEmail);
	}
	

	/*
	 * zpz_addUserInfo
	 */
	@RequestMapping(value="addUserInformation",method=RequestMethod.GET)
	public ModelAndView addUserInformation(){
//		List<UserPersistence> userPersistences = UserHelper.getEmail_id();
		ModelAndView modelAndView = new ModelAndView("addUserInformation");
//		modelAndView.addObject("userInfoList", userPersistences);
		return modelAndView;
	}

	/*
	 * zpz_editUserInfo
	 */
	@RequestMapping(value="editUserInformation",method=RequestMethod.GET)
	public ModelAndView editUserInformation(String u){
		List<UserPersistence> userPersistences = UserHelper.getEmail_id(u);
		ModelAndView modelAndView = new ModelAndView("editUserInformation");
		modelAndView.addObject("userInfoList", userPersistences);
		return modelAndView;
	}
	
	/*
	 * zpz_editUserInfo2
	 */
	@RequestMapping(value="editUserInformation2",method=RequestMethod.POST)
	public ModelAndView editUserInformation2(HttpServletRequest request){
		System.out.println("asdasdasda");
		String username = request.getParameter("username");
		String userid = request.getParameter("userid");
		String useremail = request.getParameter("useremail");
		UserHelper.updateUser(userid, username);
		ModelAndView modelAndView = new ModelAndView("userindex");
		List<UserPersistence> userlist = UserService.getAllUserList();
		modelAndView.addObject("allUserList",userlist);
		return modelAndView;
	}
}
