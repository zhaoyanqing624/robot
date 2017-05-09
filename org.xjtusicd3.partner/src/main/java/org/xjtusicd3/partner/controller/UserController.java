package org.xjtusicd3.partner.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.eclipse.jetty.server.Authentication.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.helper.ITHelper;
import org.xjtusicd3.database.helper.PayHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.ITPersistence;
import org.xjtusicd3.database.model.PayPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.filter.CopyFile;
import org.xjtusicd3.partner.filter.DeleteFile;
import org.xjtusicd3.partner.filter.RegexAddress;
import org.xjtusicd3.partner.service.UserService;
import org.xjtusicd3.partner.view.UserView;

@Controller
public class UserController {
	/*
	 * login_ajax_注册
	 */
	@ResponseBody
	@RequestMapping(value={"/saveRegister"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	public String registerlist(HttpServletRequest request,HttpServletResponse response){
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		//判断邮箱是否被注册
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
	 * 邮箱跳转验证码是否超时
	 */
	@RequestMapping(value="/valiadateEmail",method=RequestMethod.GET)
	public String validateEmail(HttpSession session,String e,String p){
		List<UserPersistence> list = UserHelper.getEmail3(e, p);
		if (list.size()==0) {
			return "redirect:404.html";
		}else {
			if (UserService.validateUserState(e)==true) {
				session.setAttribute("UserEmail", e);
				return "redirect:robot.html";
			}else {
				if (UserService.validateEmail(e)==true) {
					UserHelper.updateUserState(e);
					session.setAttribute("UserEmail", e);
					return "redirect:robot.html";
				}else {
					return "redirect:404.html";
				}
			}
		}
	}
	/*
	 * login_登录
	 */
	@RequestMapping(value="/saveLogin",method=RequestMethod.POST)
	public String loginlist(UserView userView,HttpSession session,HttpServletRequest request){
		String useremail = (String) session.getAttribute("UserEmail");
		String urlPath = (String) session.getAttribute("urlPath");
		if (useremail==null) {
			String email = userView.getUserEmail();
			String password = userView.getUserPassword();
			List<UserPersistence> list = UserHelper.getEmail2(email, password);
			if (list.size()==0) {
				return "redirect:login.html";
			}else {
				session.setAttribute("UserEmail", email);
				return "redirect:robot.html";
			}
		}else {
			if(urlPath==null){
				return "redirect:login.html";
			}else {
				String email = userView.getUserEmail();
				String password = userView.getUserPassword();
				List<UserPersistence> list = UserHelper.getEmail2(email, password);
				if (list.size()==0) {
					return "redirect:login.html";
				}else {
					session.setAttribute("UserEmail", email);
					return "redirect:"+urlPath+"";
				}
			}
		}
		
		
	}
	/*
	 * 用户退出
	 */
	@RequestMapping(value="/loginout",method=RequestMethod.GET)
	public String loginout(HttpSession session,HttpServletRequest request){
		ModelAndView modelAndView = null;
		String urlPath = (String) session.getAttribute("urlPath");
		session.invalidate();
		return "redirect:"+urlPath+"";
	}
	/*
	 * personal_个人信息
	 */
	@RequestMapping(value="personal",method=RequestMethod.GET)
	public ModelAndView personal(UserView userView ,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		if (useremail==null) {
			return new ModelAndView("login");
		}else {
			ModelAndView mv = new ModelAndView("personal");
			List<UserPersistence> list = UserHelper.getEmail(useremail);
			String address = list.get(0).getUSERADDRESS();
			if(address==null){
				
			}else {
				RegexAddress regexAddress = new RegexAddress();
				mv.addObject("address", regexAddress.replaceAddress(address));
			}
			mv.addObject("personal_list", list);
			return mv;
		}
		
	}
	/*
	 * personal_个人信息添加
	 */
	@RequestMapping(value="/addUserInfo",method=RequestMethod.POST)
	public String addUserInfo(UserView userView,HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String usersex = "";
		String address = "";
		if (useremail==null) {
			return "redirect:login.html";
		}else {
			List<UserPersistence> list = UserHelper.getEmail(useremail);
			if (list.get(0).getGENDER()!=null) {
				 usersex = list.get(0).getGENDER();
			}else {
				 usersex = userView.getUserSex();
			}
			String username = userView.getUserName();
			String userbirthday = userView.getUserBirthday();
			String province = userView.getProvince();
			String city = userView.getCity();
			String district = userView.getDistrict();
			RegexAddress regexAddress = new RegexAddress();
			if (province==null&&city==null&&district==null) {
				address = list.get(0).getUSERADDRESS();
			}else {
				address = "0"+province+"1"+city+"2"+district+"3";
			}
			String userbrief = userView.getUserBrief();
			UserHelper.updateUserInfo(useremail, username, usersex, userbirthday, address, userbrief);
			return "redirect:personal.html";
		}
	}
	/*
	 * personal_个人密码修改
	 */
	@ResponseBody
	@RequestMapping(value={"/updateUserPassword"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	public String updateUserPassword(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String repassword2 = request.getParameter("repassword2");
		String useremail = (String) session.getAttribute("UserEmail");
		if (useremail==null) {
			return "redirect:login.html";
		}else {
			if (password==password2) {
				return "0";
			}else {
				List<UserPersistence> list = UserHelper.getEmail2(useremail, password);
				if (list.size()==0) {
					return "1";
				}else {
					UserHelper.updateUserPassword(useremail, password2);
					return "2";
				}
			}
		}
	}
	
	
	//头像上传
	@ResponseBody
	@RequestMapping(value = "/uploadUserImage",method=RequestMethod.POST)
    public String uploadUserImage(HttpServletRequest request,HttpSession session) throws IOException {
		String useremail = (String) session.getAttribute("UserEmail");
		if (useremail==null) {
			return "redirect:login.html";
		}else {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;//request强制转换注意
			Iterator<String> iterator = mRequest.getFileNames();
	        String path  ="";
	        String fileName = "";
	        String suffix = "";
			String filename = "";
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        String dir = "static/image/"+useremail +"/"+ sdf.format(new Date()) + "/";
	        String realPath = request.getSession().getServletContext().getRealPath("/");
	        while(iterator.hasNext()){
	            MultipartFile multipartFile = mRequest.getFile(iterator.next());
	            if(multipartFile != null){
	                String fn = multipartFile.getOriginalFilename();
	                 suffix = fn.substring(fn.lastIndexOf("."));
	                 filename = RandomStringUtils.randomAlphanumeric(6);
	                fileName = dir + filename + suffix;
	                path = realPath + fileName;
	                path = path.replace("\\", "/");
	                File f = new File(path);
	                if(!f.mkdirs()){
	                    f.mkdir();
	                }
	                multipartFile.transferTo(f);
	            }
	        }
	        CopyFile copyFile = new CopyFile();
	        String newPath = copyFile.copyFile(path, useremail, sdf.format(new Date()));
	        newPath = newPath.replace("\\", "/");
	        newPath = newPath.replace("E:/eclipse/workspace/robot-master/org.xjtusicd3.partner/src/main/webapp", "/org.xjtusicd3.partner");
	        UserHelper.updateUserImage(useremail, newPath);
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        String aString = "{\"result\":\""+newPath+"\"}";
			return aString;
		}
    }
	/*
	 * zyq_personal2_个人信息
	 */
	@RequestMapping(value="personal2",method=RequestMethod.GET)
	public ModelAndView personal2(HttpServletRequest request,HttpSession session){
		
		String useremail = (String) session.getAttribute("UserEmail");
		if (useremail==null) {
			return new ModelAndView("login");
		}else {
			//主页页面
			ModelAndView mv = new ModelAndView("personal2");
			List<UserPersistence> list = UserHelper.getEmail(useremail);
			List<ITPersistence> list2 = ITHelper.IT(list.get(0).getUSERID());
			if (list2.size()!=0) {
				mv.addObject("GOODWORK", list2.get(0).getGOODWORK());
				mv.addObject("WORKAGE", list2.get(0).getWORKAGE());
			}
			List<PayPersistence> payPersistences = PayHelper.payList(list.get(0).getUSERID());
			List<PayPersistence> payPersistences2 = PayHelper.bepayList(list.get(0).getUSERID());
			
			mv.addObject("personal2_list", list);
			mv.addObject("paynumber", payPersistences.size());//关注人数
			mv.addObject("bepaynumber", payPersistences2.size());//粉丝数
			return mv;
		}
		
		
	}
	
}
