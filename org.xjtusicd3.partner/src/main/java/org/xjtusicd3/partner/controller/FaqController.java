package org.xjtusicd3.partner.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CollectionHelper;
import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.ITHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.ScoreHelper;
import org.xjtusicd3.database.helper.ShareHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.CollectionPersistence;
import org.xjtusicd3.database.model.CommentPersistence;
import org.xjtusicd3.database.model.ITPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.ScorePersistence;
import org.xjtusicd3.database.model.SharePersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.annotation.SystemControllerLog;
import org.xjtusicd3.partner.lucene.LuceneIndex;
import org.xjtusicd3.partner.service.ClassifyService;
import org.xjtusicd3.partner.service.CommentService;
import org.xjtusicd3.partner.service.LogService;
import org.xjtusicd3.partner.service.QuestionService;
import org.xjtusicd3.partner.service.RobotService;
import org.xjtusicd3.partner.service.ScoreService;
import org.xjtusicd3.partner.view.Faq1_ClassifyView;
import org.xjtusicd3.partner.view.Faq1_UserActive;
import org.xjtusicd3.partner.view.Faq2_faqContentView;
import org.xjtusicd3.partner.view.Faq3_CommentView;
import org.xjtusicd3.partner.view.Faq3_faqContentView;
import org.xjtusicd3.partner.view.Faq_CommendView;
import org.xjtusicd3.partner.view.Faq_UserDynamics;
import org.xjtusicd3.partner.view.robot_Chat;

import com.alibaba.fastjson.JSONObject;
@Controller
public class FaqController {
	@RequestMapping(value="faq",method=RequestMethod.GET)
	@SystemControllerLog(description = "faq首页面")
	public ModelAndView faq(HttpSession session,HttpServletRequest request,String q){
		//String useremail = (String) session.getAttribute("UserEmail");
		//zzl_获取登录用户
		String username = (String) session.getAttribute("UserName");
		ModelAndView mv = new ModelAndView("faq");
		String urlPath="";
		if (request.getQueryString()==null) {
			urlPath = request.getServletPath();
		}else {
			urlPath = request.getServletPath()+"?"+request.getQueryString().toString();
		}
		//查询所有用户发表知识的状态
		List<Faq_UserDynamics> userDynamics = QuestionService.userDynamics();
		System.out.println("userDynamics:"+userDynamics.size());
		//List<UserPersistence> userInfo = UserHelper.getUserNameById(userDynamics.get(0).getUserId());
		//String userImage = userInfo.get(0).getAVATAR();
		//String userName = userInfo.get(0).getUSERNAME();
		session.setAttribute("urlPath", urlPath);
				
		if(username==null){
			//zzl未登录用户获取推荐faq_2017年9月14日21:43:52
			int startnum = 0;
			List<Faq_CommendView> faqlists = QuestionService.faq_recommend_Limit(startnum);
			mv.addObject("faqlists", faqlists);
			System.out.println("未登录用户");
		}else{
			//zzl_已登录用户获取推荐faq_2017年9月14日21:43:52
			System.out.println("已登录用户");
			//List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);	
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			int startnum = 0;
			List<Faq_CommendView> faqlists = QuestionService.user_recommend_Limit(userPersistences.get(0).getUSERID(),startnum);				
			mv.addObject("faqlists", faqlists);
			
		}
		
		mv.addObject("userDynamics", userDynamics);
		//mv.addObject("userImage", userImage);
		//mv.addObject("userName", userName);
		return mv;
		
	}
	
	
	
	/*
	 * faq、faq1_上侧的第二级分类
	 */
	@RequestMapping(value="faq1",method=RequestMethod.GET)
	@SystemControllerLog(description = "faq、faq1_上侧的第二级分类")
	public ModelAndView classifyName2(HttpSession session,HttpServletRequest request,String p){
		ModelAndView modelAndView = new ModelAndView("faq1");		
		//zzl_获取一级分类信息
		List<ClassifyPersistence> classify1Info = ClassifyHelper.getInfoById(p);
		//zzl_获取二级分类
		List<ClassifyPersistence> list = ClassifyHelper.faq1_ClassifyName(p);
		List<Faq1_ClassifyView> list2 = ClassifyService.faq1_ClassifyView(p);
		List<Faq1_UserActive> faq1_UserActives = CommentService.faq1_userActive();
		List<Faq1_UserActive> faq1_UserActives2 = CommentService.faq1_userActive_week();
		if (list == null || list.size()==0) {
			return null;
		}
		
		//zzl_推荐知识_根据收藏量推荐前4个_2017年9月17日19:45:11
		List<Faq_CommendView> faq_list = QuestionService.faqInfo(p);
		
		modelAndView.addObject("faq_list", faq_list);
		modelAndView.addObject("classifyInfo", classify1Info);
		modelAndView.addObject("faq1_list", list);
		modelAndView.addObject("faq1_list2", list2);
		modelAndView.addObject("userActive", faq1_UserActives);
		modelAndView.addObject("userActiveWeek", faq1_UserActives2);
		String urlPath="";
		if (request.getQueryString()==null) {
			urlPath = request.getServletPath();
		}else {
			urlPath = request.getServletPath()+"?"+request.getQueryString().toString();
		}
		session.setAttribute("urlPath", urlPath);;
		return modelAndView;
	}
	/*
	 * faq2_知识列表
	 */
	@RequestMapping(value="faq2",method=RequestMethod.GET)
	@SystemControllerLog(description = "faq2_知识列表")
	public ModelAndView faqList(HttpSession session,HttpServletRequest request,String c){
		ModelAndView modelAndView = new ModelAndView("faq2");
		List<ClassifyPersistence> classify2 = ClassifyService.faq2_classify2(c);
		List<ClassifyPersistence> classify = ClassifyService.faq2_classify(c);
		List<Faq2_faqContentView> faq2Views = QuestionService.faqlist_faq2(c,1);
		List<Faq1_UserActive> faq1_UserActives = CommentService.faq1_userActive();
		List<Faq1_UserActive> faq1_UserActives2 = CommentService.faq1_userActive_week();
		modelAndView.addObject("faq2_list", classify);
		modelAndView.addObject("faq2_list2", classify2);
		modelAndView.addObject("faq2_list3", faq2Views);
		modelAndView.addObject("userActive", faq1_UserActives);
		modelAndView.addObject("userActiveWeek", faq1_UserActives2);
		String urlPath="";
		if (request.getQueryString()==null) {
			urlPath = request.getServletPath();
		}else {
			urlPath = request.getServletPath()+"?"+request.getQueryString().toString();
		}
		session.setAttribute("urlPath", urlPath);
		return modelAndView;
	}
	/*
	 * faq2_ajax请求更多知识列表
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreFaqList"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "faq2_ajax请求更多知识列表")
	public String faq2list(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		int pagenow = Integer.parseInt(request.getParameter("pagenow"));
		int pageNow = pagenow+1;
		String ClassifyId = request.getParameter("classifyId");
		List<Faq2_faqContentView> faq2Views = QuestionService.faqlist_faq2(ClassifyId, pageNow);
		int faqTotal = QuestionHelper.pageTotal(ClassifyId);
		int pageTotal = (int) Math.ceil((double)faqTotal/(double)5);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("pagenow", pageNow);
		jsonObject.put("faqlist", faq2Views);
		jsonObject.put("pageTotal",pageTotal);
		String faq2_list = JsonUtil.toJsonString(jsonObject);
		return faq2_list;
	 }
	/*
	 * faq3_知识内容
	 */
	@RequestMapping(value="faq3",method=RequestMethod.GET)
	@SystemControllerLog(description = "faq3_知识内容")
	public ModelAndView faqContent(HttpSession session,HttpServletRequest request,String q) throws Exception{
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		//List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
		System.out.println("username"+username);
		ModelAndView modelAndView = new ModelAndView("faq3");
		String classifyId = QuestionHelper.faqclassify(q);
		List<ClassifyPersistence> classify2 = ClassifyService.faq2_classify2(classifyId);
		List<ClassifyPersistence> classify = ClassifyService.faq2_classify(classifyId);
		List<Faq3_faqContentView> faq3Views = QuestionService.faq3_faqcontent(q);
		List<CommentPersistence> commentPersistences = CommentHelper.getComment(q);
		List<Faq3_CommentView> faq3_CommentViews = CommentService.faq3_comment(q,0);		
		
		//登录FAQ 增加浏览量
		QuestionHelper.updateFAQScan(q);
		//FAQ的总评分展示
		List<ScorePersistence> FAQlist = ScoreHelper.getScoreList(q);
		float totalscore = ScoreHelper.getScore(q);
		int number;
		if (totalscore==0) {
			number = 1;
		}else {
			number = FAQlist.size();
		}
		float score = totalscore/number;
		modelAndView.addObject("score", score);
		if (username!=null) {	
			modelAndView.addObject("userName", userPersistences.get(0).getUSERNAME());
			//判断用户是否收藏
			//List<CollectionPersistence> collectionPersistences = CollectionHelper.getCollection2(useremail, q);
			List<CollectionPersistence> collectionPersistences = CollectionHelper.getCollection3(username, q);		
			modelAndView.addObject("collection", collectionPersistences.size());
			//判断用户是否评分
			List<ScorePersistence> scorePersistences = ScoreHelper.getScoreList(q);
			modelAndView.addObject("scoreList", scorePersistences);
			modelAndView.addObject("scoreSize", scorePersistences.size());
			//判断是否有分享内容的权利
			List<ITPersistence> list = ITHelper.IT(userPersistences.get(0).getUSERID());
			if (list.size()==0) {
				modelAndView.addObject("IsIT", "0");
			}else{
				modelAndView.addObject("IsIT", "1");
				List<SharePersistence> sharePersistences = ShareHelper.getShareList_ID(userPersistences.get(0).getUSERID(),q);
				if (sharePersistences.size()==0) {
					modelAndView.addObject("IsShare", "0");
				}else {
					modelAndView.addObject("IsShare", "1");
				}
			}
		}
		//查看相似的问题
		List<robot_Chat> robot_Chats = RobotService.getRobotAnswer(faq3Views.get(0).getFaqTitle());
				
		modelAndView.addObject("commentNumber", commentPersistences.size());
		modelAndView.addObject("classify", classify);
		modelAndView.addObject("classify2", classify2);
		modelAndView.addObject("faq3Views", faq3Views);
		modelAndView.addObject("comment", faq3_CommentViews);
		modelAndView.addObject("faqSimilarity", robot_Chats);
		String urlPath="";
		if (request.getQueryString()==null) {
			urlPath = request.getServletPath();
		}else {
			urlPath = request.getServletPath()+"?"+request.getQueryString().toString();
		}
		session.setAttribute("urlPath", urlPath);
		return modelAndView;
	}
	/*
	 * zyq_ajax_FAQ的增加
	 */
	@ResponseBody
	@RequestMapping(value={"/saveFAQ"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	@SystemControllerLog(description = "FAQ的增加")
	public String saveFAQ(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String url = (String) session.getAttribute("urlPath");
		if (username==null) {
			return "0";
		}else {
			String title = request.getParameter("title");
			String keywords = request.getParameter("keywords");
			String subspecialCategoryId = request.getParameter("subspecialCategoryId");
			String description = request.getParameter("description");
			String risk_prompt = request.getParameter("risk_prompt");
			String faqcontent = request.getParameter("faqcontent");
			//List<QuestionPersistence> questionPersistences = QuestionHelper.faqadd_iscurrent(title,useremail);
			//zzl_faqadd_校验知识是否重复增添
			List<QuestionPersistence> questionPersistences = QuestionHelper.faqadd_iscurrent2(title,username);
			JSONObject jsonObject = new JSONObject();
			if (questionPersistences.size()==0) {				
				//QuestionService.saveFAQ(useremail,title,keywords,subspecialCategoryId,description,risk_prompt,faqcontent);
				//zzl_保存知识
				QuestionService.saveFAQ2(username,title,keywords,subspecialCategoryId,description,risk_prompt,faqcontent);
				jsonObject.put("value", "1");
				jsonObject.put("url", url);
				String result = JsonUtil.toJsonString(jsonObject);
				return result;
			}else {
				jsonObject.put("value", "2");
				jsonObject.put("url", url);
				String result = JsonUtil.toJsonString(jsonObject);
				return result;
			}
		}
	}
	/*
	 * zyq_faqadd_FAQ的增加页面
	 */
	@RequestMapping(value="faqadd",method=RequestMethod.GET)
	@SystemControllerLog(description = "FAQ的增加页面")
	public ModelAndView faqadd(HttpSession session,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("faqadd");
		String urlPath = request.getHeader("REFERER");
		session.setAttribute("urlPath", urlPath);
		return mv;
	}
	/*
	 * zyq_faq3_ajax_FAQ评分
	 */
	@ResponseBody
	@RequestMapping(value={"/saveFAQscore"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "FAQ评分")
	public String saveFAQscore(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");
		float score = Float.parseFloat(request.getParameter("score"));
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			ScoreService.saveFAQscore(questionId, userPersistences.get(0).getUSERID(), score);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	/*
	 * zyq_faq3_ajax_FAQ分享
	 */
	@ResponseBody
	@RequestMapping(value={"/saveShare"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "FAQ分享")
	public String saveShare(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");
		String state = request.getParameter("state");
		String from = request.getParameter("from");
		System.out.println(from);
		JSONObject jsonObject = new JSONObject();
		List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			if (from.equals("communityQuestion")) {
				List<SharePersistence> lPersistences = ShareHelper.getShareList_ID2(userPersistences.get(0).getUSERID(), questionId);
				if (lPersistences.size()==0) {
					QuestionService.saveShare2(userPersistences.get(0).getUSERID(), questionId);
					jsonObject.put("value", "1");
					String result = JsonUtil.toJsonString(jsonObject);
					return result;
				}else {
					ShareHelper.deleteShare(lPersistences.get(0).getSHAREID());
					jsonObject.put("value", "2");
					String result = JsonUtil.toJsonString(jsonObject);
					return result;
				}
			}else {
				if (state.equals("1")) {
					QuestionService.saveShare(userPersistences.get(0).getUSERID(), questionId);
					jsonObject.put("value", "1");
					String result = JsonUtil.toJsonString(jsonObject);
					return result;
				}else {
					List<SharePersistence> lPersistences = ShareHelper.getShareList_ID(userPersistences.get(0).getUSERID(), questionId);
					if (lPersistences.size()==0) {
						jsonObject.put("value", "1");
						String result = JsonUtil.toJsonString(jsonObject);
						return result;
					}else {
						ShareHelper.deleteShare(lPersistences.get(0).getSHAREID());
						jsonObject.put("value", "2");
						String result = JsonUtil.toJsonString(jsonObject);
						return result;
					}
				}
			}
		}
	}
	/**
	 * author:zhaoyanqing
	 * abstract:用来建立luence的知识库搜索
	 * data:2017年8月20日 20:52:06
	 * @throws Exception 
	 */
	@RequestMapping(value="/faqSearch",method=RequestMethod.POST)
	@SystemControllerLog(description = "FAQ查找")
	public ModelAndView faqSearch(HttpSession session,HttpServletRequest request) throws Exception{
		String queryStr = request.getParameter("queryString");
		ModelAndView modelAndView = new ModelAndView("faqSearch");
		LuceneIndex luceneIndex = new LuceneIndex();
		List<QuestionPersistence> qList = luceneIndex.searchFAQ(queryStr);
		List<Faq2_faqContentView> faq2List = luceneIndex.faq2_faqContentViews(qList, 0 ,qList.size());
		String urlPath="";
		if (request.getQueryString()==null) {
			urlPath = request.getServletPath();
		}else {
			urlPath = request.getServletPath()+"?"+request.getQueryString().toString();
		}
		session.setAttribute("urlPath", urlPath);
		modelAndView.addObject("faq2List", faq2List);
		modelAndView.addObject("queryStr", queryStr);
		modelAndView.addObject("titleNumber", qList.size());
		return modelAndView;
	}
	/**
	 * author:zhaoyanqing
	 * abstract:查看luence搜索更多的结果
	 * data:2017年9月13日 13:50:17
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value={"/queryMoreResult"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "获取更多FAQ结果")
	public String queryMoreResult(HttpSession session,HttpServletRequest request) throws Exception{
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		if(username==null){
			jsonObject.put("value", "0");
			return JsonUtil.toJsonString(jsonObject);
		}else{
			String queryStr = request.getParameter("queryStr");
			int starNum = Integer.parseInt(request.getParameter("starNumb"));
			LuceneIndex luceneIndex = new LuceneIndex();
			List<QuestionPersistence> qList = luceneIndex.searchFAQ(queryStr);
			List<Faq2_faqContentView> faq2List = luceneIndex.faq2_faqContentViews(qList, starNum ,qList.size());
			jsonObject.put("value", "1");
			jsonObject.put("queryList", faq2List);
			return JsonUtil.toJsonString(jsonObject);
		}
	}
	
	
}
