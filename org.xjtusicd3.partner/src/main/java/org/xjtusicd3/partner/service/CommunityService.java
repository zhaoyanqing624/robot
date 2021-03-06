package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.AgreeHelper;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CollectionHelper;
import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AgreePersistence;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.CollectionPersistence;
import org.xjtusicd3.database.model.CommentPersistence;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.view.Question2_CommunityReplayView;
import org.xjtusicd3.partner.view.Question2_CommunityView;
import org.xjtusicd3.partner.view.Question_CommunityView;


public class CommunityService {
	/*
	 * zyq_ajax_question的增加
	 */
	public static void savaCommunityQuestion(String email,String title,String content,String classifyId){
		List<UserPersistence> userPersistences = UserHelper.getEmail(email);
    	Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);
    	CommunityQuestionHelper.saveCommunityQuestion(UUID.randomUUID().toString(),time,title,content,classifyId,userPersistences.get(0).getUSERID(),"0",null,0);
	}
	
	/**
	 * author:zzl
	 * abstract:保存问题
	 * data:2017年9月22日14:39:58
	 */
	public static void savaCommunityQuestion2(String username,String title,String content,String classifyId){
		List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
    	Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);
    	CommunityQuestionHelper.saveCommunityQuestion(UUID.randomUUID().toString(),time,title,content,classifyId,userPersistences.get(0).getUSERID(),"0",null,0);
	}
	
	
	
	
	/*
	 * zyq_question_问题展示
	 *    从 startnumber 开始加载 type类型【待解决、已解决、全部】的  classifyname【具体知识库分类】的 5条记录
	 */
	public static List<Question_CommunityView> Question_CommunityView(String username,int startnumber,String type,String classifyname){
		List<Question_CommunityView> question_CommunityViews = new ArrayList<Question_CommunityView>();
		if (classifyname.equals("all")) {
			if (type.equals("all")) {//展示全部问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity_isanswer(startnumber);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					if (list.size()==0) {
						//zzl_无最佳答案_基本信息
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
						question_CommunityView.setCommunityNumber(communityNumber);			
						question_CommunityViews.add(question_CommunityView);
					}else {
						//zzl_有最佳答案_基本信息
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());						
						question_CommunityView.setCommunityNumber(communityNumber);
						//zzl_最佳答案专有显示
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						List<CommunityAnswerPersistence> list2 = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
						List<AgreePersistence> agreePersistences = AgreeHelper.getAgree_id(list2.get(0).getCOMMUNITYANSWERID());
						question_CommunityView.setLikesNumber(Integer.toString(agreePersistences.size()));
						
						//最佳答案用户信息
						List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
						question_CommunityView.setUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						
						//zzl_用户点评
						List<CommunityAnswerPersistence> list3 = CommunityAnswerHelper.question_CommunityAnswer_userId(userPersistences.get(0).getUSERID());
						question_CommunityView.setTotalCommunityNumber(Integer.toString(list3.size()));
						
						//zzl_用户点赞
						List<AgreePersistence> list4 = AgreeHelper.getAgreebyUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setTotalLikesNumber(Integer.toString(list4.size()));
						if (username!=null) {
							List<AgreePersistence> agreePersistences2 = AgreeHelper.getAgree(username, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
							//判断是否点赞
							if (agreePersistences2.size()==0) {
								question_CommunityView.setIsLike("0");
							}else {
								question_CommunityView.setIsLike("1");
							}
						}
						question_CommunityViews.add(question_CommunityView);
					}
				}
			}else if (type.equals("2")) {//展示未有答案的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2_isanswer(0);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					
					Question_CommunityView question_CommunityView = new Question_CommunityView();
					question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
					question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
					question_CommunityView.setTime(communityQuestionPersistence.getTIME());
					question_CommunityView.setCommunityNumber(communityNumber);
					List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
					question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
					question_CommunityViews.add(question_CommunityView);
					
				}
			}else if (type.equals("1")) {//展示已解决的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2_isanswer(1);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					//获取最佳答案信息
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					Question_CommunityView question_CommunityView = new Question_CommunityView();
					question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
					question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
					question_CommunityView.setTime(communityQuestionPersistence.getTIME());
					List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
					question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
					
					question_CommunityView.setAnswer(list.get(0).getCONTENT());
					question_CommunityView.setCommunityNumber(communityNumber);
					
					//zzl_点赞
					List<CommunityAnswerPersistence> list2 = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					List<AgreePersistence> agreePersistences = AgreeHelper.getAgree_id(list2.get(0).getCOMMUNITYANSWERID());
					question_CommunityView.setLikesNumber(Integer.toString(agreePersistences.size()));
	
					//获取用户信息
					List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
					question_CommunityView.setUserId(userPersistences.get(0).getUSERID());
					question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
					question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
					question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
					
					//zzl_用户点评
					List<CommunityAnswerPersistence> list3 = CommunityAnswerHelper.question_CommunityAnswer_userId(userPersistences.get(0).getUSERID());
					question_CommunityView.setTotalCommunityNumber(Integer.toString(list3.size()));
					
					//zzl_用户点赞
					List<AgreePersistence> list4 = AgreeHelper.getAgreebyUserId(userPersistences.get(0).getUSERID());
					question_CommunityView.setTotalLikesNumber(Integer.toString(list4.size()));
					if (username!=null) {
						List<AgreePersistence> agreePersistences2 = AgreeHelper.getAgree(username, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
						//判断是否点赞
						if (agreePersistences2.size()==0) {
							question_CommunityView.setIsLike("0");
						}else {
							question_CommunityView.setIsLike("1");
						}
					}
					question_CommunityViews.add(question_CommunityView);
				}
			}
		}else {
			//zzl_分类显示
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.spider_ClassifyListByName(classifyname, "0");			
			if (type.equals("all")) {//展示全部问题
				System.out.println("子分类下所有问题显示");
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity(classifyPersistences.get(0).getFAQCLASSIFYID());				
				System.out.println("子分类下问题个数："+communityQuestionPersistences.size());
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					System.out.println("问题分类id："+communityQuestionPersistence.getCLASSIFYID());
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					Question_CommunityView question_CommunityView = new Question_CommunityView();
					
					//zzl_有/无最佳答案都显示
					question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
					question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
					question_CommunityView.setTime(communityQuestionPersistence.getTIME());					
					question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
					
					
					question_CommunityView.setCommunityNumber(communityNumber);
					if (list.size()!=0) {
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						
						//获取用户信息
						List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
						question_CommunityView.setUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						
						//zzl_用户点评
						List<CommunityAnswerPersistence> list3 = CommunityAnswerHelper.question_CommunityAnswer_userId(userPersistences.get(0).getUSERID());
						question_CommunityView.setTotalCommunityNumber(Integer.toString(list3.size()));
						
						//zzl_用户点赞
						List<AgreePersistence> list4 = AgreeHelper.getAgreebyUserId(userPersistences.get(0).getUSERID());						
						question_CommunityView.setTotalLikesNumber(Integer.toString(list4.size()));
						
						//zzl_点赞
						List<CommunityAnswerPersistence> list2 = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
						List<AgreePersistence> agreePersistences = AgreeHelper.getAgree_id(list2.get(0).getCOMMUNITYANSWERID());
						question_CommunityView.setLikesNumber(Integer.toString(agreePersistences.size()));
						
						if (username!=null) {
							List<AgreePersistence> agreePersistences2 = AgreeHelper.getAgree(username, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
							//判断是否点赞
							if (agreePersistences2.size()==0) {
								question_CommunityView.setIsLike("0");
							}else {
								question_CommunityView.setIsLike("1");
							}
						}					
					}
					question_CommunityViews.add(question_CommunityView);
				}
			}else if (type.equals("2")) {//展示未有答案的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2(classifyPersistences.get(0).getFAQCLASSIFYID(),0);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					Question_CommunityView question_CommunityView = new Question_CommunityView();
					question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
					question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
					question_CommunityView.setTime(communityQuestionPersistence.getTIME());
					
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					question_CommunityView.setCommunityNumber(communityNumber);
					question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
					
					question_CommunityViews.add(question_CommunityView);
					
				}
			}else if (type.equals("1")) {//展示已回答的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2(classifyPersistences.get(0).getFAQCLASSIFYID(),1);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取最佳答案信息
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					
					//zzl_有最佳答案
					Question_CommunityView question_CommunityView = new Question_CommunityView();
					question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
					question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
					question_CommunityView.setTime(communityQuestionPersistence.getTIME());
					question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
					
					question_CommunityView.setAnswer(list.get(0).getCONTENT());
					
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					question_CommunityView.setCommunityNumber(communityNumber);
					
					//zzl_点赞
					List<CommunityAnswerPersistence> list2 = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					List<AgreePersistence> agreePersistences = AgreeHelper.getAgree_id(list2.get(0).getCOMMUNITYANSWERID());
					question_CommunityView.setLikesNumber(Integer.toString(agreePersistences.size()));
					
					//获取用户信息
					List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
					question_CommunityView.setUserId(userPersistences.get(0).getUSERID());
					question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
					question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
					question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
					
					//zzl_用户点评
					List<CommunityAnswerPersistence> list3 = CommunityAnswerHelper.question_CommunityAnswer_userId(userPersistences.get(0).getUSERID());
					question_CommunityView.setTotalCommunityNumber(Integer.toString(list3.size()));
					
					//zzl_用户点赞
					List<AgreePersistence> list4 = AgreeHelper.getAgreebyUserId(userPersistences.get(0).getUSERID());					
					question_CommunityView.setTotalLikesNumber(Integer.toString(list4.size()));
					
					if (username!=null) {
						List<AgreePersistence> agreePersistences2 = AgreeHelper.getAgree(username, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
						//判断是否点赞
						if (agreePersistences2.size()==0) {
							question_CommunityView.setIsLike("0");
						}else {
							question_CommunityView.setIsLike("1");
						}
					}
					question_CommunityViews.add(question_CommunityView);					
				}
			}
		}

		return question_CommunityViews;
	}

		
	/*
	 * zyq_question_ajax_添加评论
	 */
	public static void addComment(String userid,String content,String questionId){
		CommunityAnswerPersistence communityAnswerPersistence = new CommunityAnswerPersistence();
		communityAnswerPersistence.setCOMMUNITYANSWERID(UUID.randomUUID().toString());
	    Date date=new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
		communityAnswerPersistence.setTIME(time);
		communityAnswerPersistence.setCONTENT(content);
		communityAnswerPersistence.setISBESTANSWER(0);
		communityAnswerPersistence.setCOMMUNITYQUESTIONID(questionId);
		communityAnswerPersistence.setUSERID(userid);
		//查询是否回答的是自己的问题
		List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question2_getCommunity(questionId);
		if (userid==communityQuestionPersistences.get(0).getUSERID()) {
			communityAnswerPersistence.setISNOTICE(0);
		}else {
			communityAnswerPersistence.setISNOTICE(1);
		}
		CommunityAnswerHelper.addComment(communityAnswerPersistence);
	}
	/*
	 * zyq_question2_问题展示_最佳答案
	 */
	public static List<Question2_CommunityView> question2_CommunityViews_best(String username,String questionId){
		List<Question2_CommunityView> question2_CommunityViews = new ArrayList<Question2_CommunityView>();
		List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer_best(questionId);
		for(CommunityAnswerPersistence communityAnswerPersistence:communityAnswerPersistences){
			Question2_CommunityView question2_CommunityView = new Question2_CommunityView();
			List<Question2_CommunityReplayView> question2_CommunityReplayViews = new ArrayList<Question2_CommunityReplayView>();
			question2_CommunityView.setAnswerId(communityAnswerPersistence.getCOMMUNITYANSWERID());
			question2_CommunityView.setAnswer(communityAnswerPersistence.getCONTENT());
			//获取用户信息
			List<UserPersistence> userPersistences = UserHelper.getEmail_id(communityAnswerPersistence.getUSERID());
			question2_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
			question2_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
			question2_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
			
			question2_CommunityView.setTime(communityAnswerPersistence.getTIME());
			
			//查看评论数
			List<CommentPersistence> commentPersistences = CommentHelper.question2_getComment_Limit(communityAnswerPersistence.getCOMMUNITYQUESTIONID(), communityAnswerPersistence.getCOMMUNITYANSWERID());
			List<CommentPersistence> commentPersistences2 = CommentHelper.question2_getComment(communityAnswerPersistence.getCOMMUNITYQUESTIONID(), communityAnswerPersistence.getCOMMUNITYANSWERID());
			for(CommentPersistence commentPersistence:commentPersistences){
				Question2_CommunityReplayView question2_CommunityReplayView = new Question2_CommunityReplayView();
				List<UserPersistence> userPersistences2 = UserHelper.getEmail_id(commentPersistence.getUSERID());
				question2_CommunityReplayView.setUserName(userPersistences2.get(0).getUSERNAME());
				question2_CommunityReplayView.setUserImage(userPersistences2.get(0).getAVATAR());
				question2_CommunityReplayView.setTime(commentPersistence.getCOMMENTTIME());
				question2_CommunityReplayView.setCommunity(commentPersistence.getCOMMENTCONTENT());
				question2_CommunityReplayView.setCommentId(commentPersistence.getCOMMENTID());
				if (commentPersistence.getTOUSERID()!=null) {
					List<UserPersistence> userPersistences3 = UserHelper.getEmail_id(commentPersistence.getTOUSERID());
					question2_CommunityReplayView.setTouserName("@"+userPersistences3.get(0).getUSERNAME());
				}
				question2_CommunityReplayViews.add(question2_CommunityReplayView);
			}
			String communityNumber = Integer.toString(commentPersistences2.size());
			question2_CommunityView.setCommunityNumber(communityNumber);
			List<AgreePersistence> agreePersistences = AgreeHelper.getAgree_id(communityAnswerPersistence.getCOMMUNITYANSWERID());
			String likeNumber = Integer.toString(agreePersistences.size());
			List<AgreePersistence> agreePersistences2 = AgreeHelper.getAgree(username, communityAnswerPersistence.getCOMMUNITYANSWERID());
			//判断是否点赞
			if (agreePersistences2.size()==0) {
				question2_CommunityView.setIsLike("0");
			}else {
				question2_CommunityView.setIsLike("1");
			}
			//判断是否被收藏
			List<CollectionPersistence> collectionPersistences = CollectionHelper.getCollection(username, communityAnswerPersistence.getCOMMUNITYANSWERID());
			if (collectionPersistences.size()==0) {
				question2_CommunityView.setIsCollection("0");
			}else {
				question2_CommunityView.setIsCollection("1");
			}
			question2_CommunityView.setLikesNumber(likeNumber);
			question2_CommunityView.setReplay(question2_CommunityReplayViews);
			//查看用户回答数量
			List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_CommunityAnswer_userId(communityAnswerPersistence.getUSERID());
			//查看用户被点赞数量
			List<CommunityAnswerPersistence> communityAnswerPersistences2 = CommunityAnswerHelper.getCommunityAnswerLike(communityAnswerPersistence.getUSERID());
			question2_CommunityView.setTotalLikes(Integer.toString(communityAnswerPersistences2.size()));
			question2_CommunityView.setTotalAnswer(Integer.toString(list.size()));
			question2_CommunityView.setIsBestAnswer(Integer.toString(communityAnswerPersistence.getISBESTANSWER()));
			question2_CommunityViews.add(question2_CommunityView);
			
		}
		return question2_CommunityViews;
	}
	/*
	 * zyq_question2_问题展示_除了最佳答案其他
	 */
	public static List<Question2_CommunityView> question2_CommunityViews_other(String username,String questionId,int startNumber){
		List<Question2_CommunityView> question2_CommunityViews = new ArrayList<Question2_CommunityView>();
		List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer_other(questionId,startNumber);
		for(CommunityAnswerPersistence communityAnswerPersistence:communityAnswerPersistences){
			Question2_CommunityView question2_CommunityView = new Question2_CommunityView();
			List<Question2_CommunityReplayView> question2_CommunityReplayViews = new ArrayList<Question2_CommunityReplayView>();
			question2_CommunityView.setAnswerId(communityAnswerPersistence.getCOMMUNITYANSWERID());
			question2_CommunityView.setAnswer(communityAnswerPersistence.getCONTENT());
			//获取用户信息
			List<UserPersistence> userPersistences = UserHelper.getEmail_id(communityAnswerPersistence.getUSERID());
			question2_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
			question2_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
			question2_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
			question2_CommunityView.setUserId(userPersistences.get(0).getUSERID());
			question2_CommunityView.setTime(communityAnswerPersistence.getTIME());
			
			//查看评论数
			List<CommentPersistence> commentPersistences = CommentHelper.question2_getComment_Limit(communityAnswerPersistence.getCOMMUNITYQUESTIONID(), communityAnswerPersistence.getCOMMUNITYANSWERID());
			List<CommentPersistence> commentPersistences2 = CommentHelper.question2_getComment(communityAnswerPersistence.getCOMMUNITYQUESTIONID(), communityAnswerPersistence.getCOMMUNITYANSWERID());
			for(CommentPersistence commentPersistence:commentPersistences){
				Question2_CommunityReplayView question2_CommunityReplayView = new Question2_CommunityReplayView();
				List<UserPersistence> userPersistences2 = UserHelper.getEmail_id(commentPersistence.getUSERID());
				question2_CommunityReplayView.setUserName(userPersistences2.get(0).getUSERNAME());
				question2_CommunityReplayView.setUserImage(userPersistences2.get(0).getAVATAR());
				question2_CommunityReplayView.setTime(commentPersistence.getCOMMENTTIME());
				question2_CommunityReplayView.setCommunity(commentPersistence.getCOMMENTCONTENT());
				question2_CommunityReplayView.setCommentId(commentPersistence.getCOMMENTID());
				question2_CommunityReplayView.setUserId(userPersistences2.get(0).getUSERID());
				if (commentPersistence.getTOUSERID()!=null) {
					List<UserPersistence> userPersistences3 = UserHelper.getEmail_id(commentPersistence.getTOUSERID());
					question2_CommunityReplayView.setTouserName("@"+userPersistences3.get(0).getUSERNAME());
				}
				question2_CommunityReplayViews.add(question2_CommunityReplayView);
			}
			String communityNumber = Integer.toString(commentPersistences2.size());
			question2_CommunityView.setCommunityNumber(communityNumber);
			List<AgreePersistence> agreePersistences = AgreeHelper.getAgree_id(communityAnswerPersistence.getCOMMUNITYANSWERID());
			String likeNumber = Integer.toString(agreePersistences.size());
			List<AgreePersistence> agreePersistences2 = AgreeHelper.getAgree(username, communityAnswerPersistence.getCOMMUNITYANSWERID());
			//判断是否点赞
			if (agreePersistences2.size()==0) {
				question2_CommunityView.setIsLike("0");
			}else {
				question2_CommunityView.setIsLike("1");
			}
			//判断是否被收藏
			List<CollectionPersistence> collectionPersistences = CollectionHelper.getCollection(username, communityAnswerPersistence.getCOMMUNITYANSWERID());
			if (collectionPersistences.size()==0) {
				question2_CommunityView.setIsCollection("0");
			}else {
				question2_CommunityView.setIsCollection("1");
			}
			question2_CommunityView.setLikesNumber(likeNumber);
			question2_CommunityView.setReplay(question2_CommunityReplayViews);
			//查看用户回答数量
			List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_CommunityAnswer_userId(communityAnswerPersistence.getUSERID());
			//查看用户被点赞数量
			List<CommunityAnswerPersistence> communityAnswerPersistences2 = CommunityAnswerHelper.getCommunityAnswerLike(communityAnswerPersistence.getUSERID());
			question2_CommunityView.setTotalLikes(Integer.toString(communityAnswerPersistences2.size()));
			question2_CommunityView.setTotalAnswer(Integer.toString(list.size()));
			question2_CommunityView.setIsBestAnswer(Integer.toString(communityAnswerPersistence.getISBESTANSWER()));
			question2_CommunityViews.add(question2_CommunityView);
			
		}
		return question2_CommunityViews;
	}
	/*
	 * zyq_ajax_question2回复的增加
	 */
	public static void saveReplyQuestion(String username,String content,String questionId){
		CommunityAnswerPersistence communityAnswerPersistence = new CommunityAnswerPersistence();
		communityAnswerPersistence.setCOMMUNITYANSWERID(UUID.randomUUID().toString());
		communityAnswerPersistence.setCOMMUNITYQUESTIONID(questionId);
		communityAnswerPersistence.setCONTENT(content);
		communityAnswerPersistence.setISBESTANSWER(0);
	    Date date=new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
		communityAnswerPersistence.setTIME(time);
		List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
		communityAnswerPersistence.setUSERID(userPersistences.get(0).getUSERID());
		//判断是否为自己回复
	//	List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(questionId);
		List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.CommunityQuestion(questionId);
		int isnotice = 0;
		if ((userPersistences.get(0).getUSERID()).equals(communityQuestionPersistences.get(0).getUSERID())) {
			isnotice = 0;
		}else {
			isnotice = 1;
		}
		communityAnswerPersistence.setISNOTICE(isnotice);
		System.out.println("执行结束");
		CommunityAnswerHelper.addComment(communityAnswerPersistence);
	}
}
