package org.xjtusicd3.portal.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.portal.service.UserService;
import org.xjtusicd3.portal.view.UserView;

@Controller
public class UserController 
{
	/*
	 * zpz_show User information
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
	
	//login admin
	@RequestMapping(value="adminLogin",method=RequestMethod.POST)
	public String adminLogin(HttpSession session,UserView userView,HttpServletRequest request,HttpServletResponse response){
		String email = request.getParameter("userName");
		String psw = request.getParameter("userPassword");
		String password = StringToMd5(psw);
		System.out.println(password);
		List<UserPersistence> list = UserHelper.getEmail2(email, password);
		if (list.size()==0) 
		{
			return "redirect:login.html";
		}else 
		{
			session.setAttribute("useremail", email);
			session.setAttribute("UserName", list.get(0).getUSERNAME());
			request.getSession().setAttribute("user", list.get(0));
			return "redirect:index.html" ;
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
	//psw转md5
	public static String StringToMd5(String psw) {  
        {  
            try {  
                MessageDigest md5 = MessageDigest.getInstance("MD5");  
                md5.update(psw.getBytes("UTF-8"));  
                byte[] encryption = md5.digest();  
  
                StringBuffer strBuf = new StringBuffer();  
                for (int i = 0; i < encryption.length; i++) {  
                    if (Integer.toHexString(0xff & encryption[i]).length() == 1) {  
                        strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));  
                    } else {  
                        strBuf.append(Integer.toHexString(0xff & encryption[i]));  
                    }  
                }  
  
                return strBuf.toString();  
            } catch (NoSuchAlgorithmException e) {  
                return "";  
            } catch (UnsupportedEncodingException e) {  
                return "";  
            }  
        }  
    } 
	
}
