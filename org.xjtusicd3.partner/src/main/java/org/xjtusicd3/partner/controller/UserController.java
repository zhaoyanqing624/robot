package org.xjtusicd3.partner.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.ITHelper;
import org.xjtusicd3.database.helper.PayHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.ITPersistence;
import org.xjtusicd3.database.model.PayPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.filter.CopyFile;
import org.xjtusicd3.partner.filter.RegexAddress;
import org.xjtusicd3.partner.service.UserService;
import org.xjtusicd3.partner.view.Personal2_CommunityView;
import org.xjtusicd3.partner.view.Personal2_FaqView;
import org.xjtusicd3.partner.view.Personal2_PayView;
import org.xjtusicd3.partner.view.Personal2_indexList;
import org.xjtusicd3.partner.view.UserView;

import com.alibaba.fastjson.JSONObject;

@Controller
public class UserController {
	/*
	 * login_ajax_注册
	 */
	@ResponseBody
	@RequestMapping(value={"/saveRegister"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
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
		if (urlPath==null) {
			urlPath = "robot.html";
		}
		if (useremail==null) {
			String email = userView.getUserEmail();
			String password = userView.getUserPassword();
			List<UserPersistence> list = UserHelper.getEmail2(email, password);
			if (list.size()==0) {
				return "redirect:login.html";
			}else {
				List<UserPersistence> list2 = UserHelper.getEmail(userView.getUserEmail());
				session.setAttribute("UserId", list2.get(0).getUSERID());
				session.setAttribute("UserEmail", email);
				return "redirect:"+urlPath;
			}
		}else {
				String email = userView.getUserEmail();
				String password = userView.getUserPassword();
				List<UserPersistence> list = UserHelper.getEmail2(email, password);
				if (list.size()==0) {
					return "redirect:login.html";
				}else {
					List<UserPersistence> list2 = UserHelper.getEmail(userView.getUserEmail());
					session.setAttribute("UserId", list2.get(0).getUSERID());
					session.setAttribute("UserEmail", email);
					return "redirect:"+urlPath;
				}
		}
		
		
	}
	/*
	 * 用户退出
	 */
	@RequestMapping(value="/loginout",method=RequestMethod.GET)
	public String loginout(HttpSession session,HttpServletRequest request){
		String urlPath = (String) session.getAttribute("urlPath");
		session.invalidate();
		return "redirect:"+urlPath;
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
				mv.addObject("address", RegexAddress.replaceAddress(address));
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
			if(userView.getUserSex()==null&&userView.getUserSex2()==null){
				usersex = list.get(0).getGENDER();
			}else if (userView.getUserSex()!=null&&userView.getUserSex2()==null) {
				usersex = userView.getUserSex();
			}else if (userView.getUserSex()==null&&userView.getUserSex2()!=null) {
				usersex = userView.getUserSex2();
			}
			String username = userView.getUserName();
			String userbirthday = userView.getUserBirthday();
			String province = userView.getProvince();
			String city = userView.getCity();
			String district = userView.getDistrict();
			System.out.println(province=="");
			if (province==""&&city==""&&district=="") {
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
	public ModelAndView personal2(String u,HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String userId = (String) session.getAttribute("UserId");
		List<UserPersistence> list = new ArrayList<UserPersistence>();
		if (useremail==null) {
			return new ModelAndView("login");
		}else {
			//主页页面
			ModelAndView mv = new ModelAndView("personal2");
			if (u==null||u==userId) {
				list = UserHelper.getEmail(useremail);
				List<Personal2_indexList> lists = UserService.personal2_indexList(useremail);
				mv.addObject("IsMy", "1");
				mv.addObject("indexList", lists);
				mv.addObject("indexListSize", lists.size());
			}else {
				list = UserHelper.getEmail_id(u);
				mv.addObject("IsMy", "0");
				List<PayPersistence> payPersistences = PayHelper.getpayList(userId,u);
				List<Personal2_indexList> lists = UserService.personal2_indexList(useremail);
				mv.addObject("indexList", lists);
				mv.addObject("indexListSize", lists.size());
				if (payPersistences.size()==0) {
					mv.addObject("payList","0");
				}else {
					mv.addObject("payList","1");
				}
			}
			
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
			mv.addObject("uid", userId);
			return mv;
		}
		
		
	}
	/*
	 * zyq_question_ajax获取用户的信息
	 */
	@ResponseBody
	@RequestMapping(value={"/getUserInfo"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	public String getUserInfo(HttpServletRequest request,HttpServletResponse response){
		String email = request.getParameter("useremail");
		List<UserPersistence> userPersistences = UserHelper.getEmail(email);
		String result = JsonUtil.toJsonString(userPersistences);
		System.out.println(result);
		return result;
	}
	
	/*
	 * zyq_personal2_关注
	 */
	@ResponseBody
	@RequestMapping(value={"/savePay"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String savePay(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String touserId = request.getParameter("touserId");
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
			String userId = userPersistences.get(0).getUSERID();
			List<PayPersistence> payPersistences = PayHelper.getpayList(userId, touserId);
			if (payPersistences.size()==0) {
				UserService.savePay(userId,touserId);
			}
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_personal2_取消关注
	 */
	@ResponseBody
	@RequestMapping(value={"/deletePay"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String deletePay(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String touserId = request.getParameter("touserId");
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
			String userId = userPersistences.get(0).getUSERID();
			List<PayPersistence> payPersistences = PayHelper.getpayList(userId, touserId);
			if (payPersistences.size()!=0) {
				PayHelper.deletePay(userId,touserId);
			}
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_personal2_ajax_获取关注列表
	 */
	@ResponseBody
	@RequestMapping(value={"/getPay"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getPay(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String userId = request.getParameter("userId");
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_PayView> personal2_PayViews = UserService.getPay(userId);
			jsonObject.put("payView", personal2_PayViews);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_personal2_ajax_获取被关注列表
	 */
	@ResponseBody
	@RequestMapping(value={"/getbePay"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getbePay(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String userId = request.getParameter("userId");
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_PayView> personal2_PayViews = UserService.getbePay(userId);
			jsonObject.put("bepayView", personal2_PayViews);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	
	/*
	 * zyq_personal2_ajax_获取知识库列表
	 */
	@ResponseBody
	@RequestMapping(value={"/getpersonalFaq"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getpersonalFaq(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String userId = request.getParameter("userId");
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_FaqView> personal2_FaqViews = UserService.getpersonalFaq(userId);
			jsonObject.put("faqView", personal2_FaqViews);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_personal2_ajax_获取更多的个人主页信息
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreIndex"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getMoreIndex(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");
		String time3 = request.getParameter("time3");
		String time11 = request.getParameter("time11");
		String time22 = request.getParameter("time22");
		String time33 = request.getParameter("time33");
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_indexList> personal2_indexLists = UserService.personal2_indexList_Limit(useremail,time1,time2,time3,time11,time22,time33);
			jsonObject.put("personalIndex", personal2_indexLists);
			jsonObject.put("personalIndexSize", personal2_indexLists.size());
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_personal2_ajax_获取更多的个人FAQ
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreFaq1"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getMoreFaq1(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_FaqView> personal2_FaqViews = UserService.getpersonalFaq_More(userPersistences.get(0).getUSERID(), startnumber);
			jsonObject.put("faqView", personal2_FaqViews);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_personal2_ajax_获取收藏FAQ
	 */
	@ResponseBody
	@RequestMapping(value={"/getCollectFaq"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getCollectFaq(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_FaqView> personal2_FaqViews = UserService.getCollectionFaq(userPersistences.get(0).getUSERID());
			jsonObject.put("faqView", personal2_FaqViews);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	/*
	 * zyq_personal2_ajax_获取更多的收藏FAQ
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreCollectFaq"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getMoreCollectFaq(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_FaqView> personal2_FaqViews = UserService.getCollectionFaq_More(userPersistences.get(0).getUSERID(),startnumber);
			jsonObject.put("faqView", personal2_FaqViews);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	/*
	 * zyq_personal2_ajax_获取FAQ的评论
	 */
	@ResponseBody
	@RequestMapping(value={"/getCommentFaq"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getCommentFaq(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_FaqView> personal2_FaqViews = UserService.getCommentFaq(userPersistences.get(0).getUSERID());
			jsonObject.put("faqView", personal2_FaqViews);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	/*
	 * zyq_personal2_ajax_获取更多FAQ的评论
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreCommentFaq"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getMoreCommentFaq(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_FaqView> personal2_FaqViews = UserService.getCommentFaq_More(userPersistences.get(0).getUSERID(),startnumber);
			jsonObject.put("faqView", personal2_FaqViews);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	/*
	 * zyq_personal2_ajax_获取问吧的问题
	 */
	@ResponseBody
	@RequestMapping(value={"/getpersonalCommunity"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getpersonalCommunity(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String userId = request.getParameter("userId");
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_CommunityView> personal2_CommunityViews = UserService.getpersonalCommunity(userId);
			jsonObject.put("communityView", personal2_CommunityViews);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_personal2_ajax_获取更多问吧的问题
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreCommunity1"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getMoreCommunity1(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_CommunityView> personal2_CommunityViews = UserService.getMoreCommunity(userPersistences.get(0).getUSERID(),startnumber);
			jsonObject.put("communityView", personal2_CommunityViews);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_personal2_ajax_获取问吧的关注答案
	 */
	@ResponseBody
	@RequestMapping(value={"/getPayCommunity"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getPayCommunity(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String userId = request.getParameter("userId");
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_CommunityView> personal2_CommunityViews = UserService.getPayCommunity(userId);
			jsonObject.put("communityView", personal2_CommunityViews);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_personal2_ajax_获取更多问吧的关注答案
	 */
	@ResponseBody
	@RequestMapping(value={"/getMorePayCommunity"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getMorePayCommunity(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_CommunityView> personal2_CommunityViews = UserService.getMorePayCommunity(userPersistences.get(0).getUSERID(),startnumber);
			jsonObject.put("communityView", personal2_CommunityViews);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_personal2_ajax_获取问吧的回答
	 */
	@ResponseBody
	@RequestMapping(value={"/getReplyCommunity"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getReplyCommunity(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String userId = request.getParameter("userId");
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_CommunityView> personal2_CommunityViews = UserService.getReplyCommunity(userId);
			jsonObject.put("communityView", personal2_CommunityViews);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_personal2_ajax_获取更多问吧的回答
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreReplyCommunity"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getMoreReplyCommunity(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		JSONObject jsonObject = new JSONObject();
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Personal2_CommunityView> personal2_CommunityViews = UserService.getMoreReplyCommunity(userPersistences.get(0).getUSERID(),startnumber);
			jsonObject.put("communityView", personal2_CommunityViews);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	
	public static void main(String[] args) {
		
	}

}
