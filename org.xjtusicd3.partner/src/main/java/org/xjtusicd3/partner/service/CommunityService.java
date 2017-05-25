package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.AgreeHelper;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AgreePersistence;
import org.xjtusicd3.database.model.ClassifyPersistence;
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
	/*
	 * zyq_question_问题展示
	 */
	public static List<Question_CommunityView> Question_CommunityView(String useremail,int startnumber,String type,String classifyname){
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
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setCommunityNumber(communityNumber);
						List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
						question_CommunityViews.add(question_CommunityView);
					}else {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						question_CommunityView.setCommunityNumber(communityNumber);
						List<CommunityAnswerPersistence> list2 = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
						List<AgreePersistence> agreePersistences = AgreeHelper.getAgree_id(list2.get(0).getCOMMUNITYANSWERID());
						question_CommunityView.setLikesNumber(Integer.toString(agreePersistences.size()));
						List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
						//获取用户信息
						List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
						question_CommunityView.setUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						List<CommunityAnswerPersistence> list3 = CommunityAnswerHelper.question_CommunityAnswer_userId(userPersistences.get(0).getUSERID());
						List<AgreePersistence> list4 = AgreeHelper.getAgreebyUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setTotalCommunityNumber(Integer.toString(list3.size()));
						question_CommunityView.setTotalLikesNumber(Integer.toString(list4.size()));
						List<AgreePersistence> agreePersistences2 = AgreeHelper.getAgree(useremail, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
						//判断是否点赞
						if (agreePersistences2.size()==0) {
							question_CommunityView.setIsLike("0");
						}else {
							question_CommunityView.setIsLike("1");
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
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					if (list.size()==0) {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setCommunityNumber(communityNumber);
						List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
						question_CommunityViews.add(question_CommunityView);
					}else {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						List<CommunityAnswerPersistence> list2 = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
						List<AgreePersistence> agreePersistences = AgreeHelper.getAgree_id(list2.get(0).getCOMMUNITYANSWERID());
						question_CommunityView.setLikesNumber(Integer.toString(agreePersistences.size()));
						question_CommunityView.setCommunityNumber(communityNumber);
						List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
						//获取用户信息
						List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
						question_CommunityView.setUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						List<CommunityAnswerPersistence> list3 = CommunityAnswerHelper.question_CommunityAnswer_userId(userPersistences.get(0).getUSERID());
						List<AgreePersistence> list4 = AgreeHelper.getAgreebyUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setTotalCommunityNumber(Integer.toString(list3.size()));
						question_CommunityView.setTotalLikesNumber(Integer.toString(list4.size()));
						List<AgreePersistence> agreePersistences2 = AgreeHelper.getAgree(useremail, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
						//判断是否点赞
						if (agreePersistences2.size()==0) {
							question_CommunityView.setIsLike("0");
						}else {
							question_CommunityView.setIsLike("1");
						}
						question_CommunityViews.add(question_CommunityView);
					}
				}
			}else if (type.equals("1")) {//展示已回答的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2_isanswer(1);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					if (list.size()==0) {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setCommunityNumber(communityNumber);
						List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
						question_CommunityViews.add(question_CommunityView);
					}else {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						question_CommunityView.setCommunityNumber(communityNumber);
						List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
						//获取用户信息
						List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
						question_CommunityView.setUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						List<CommunityAnswerPersistence> list3 = CommunityAnswerHelper.question_CommunityAnswer_userId(userPersistences.get(0).getUSERID());
						List<AgreePersistence> list4 = AgreeHelper.getAgreebyUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setTotalCommunityNumber(Integer.toString(list3.size()));
						question_CommunityView.setTotalLikesNumber(Integer.toString(list4.size()));
						List<AgreePersistence> agreePersistences2 = AgreeHelper.getAgree(useremail, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
						//判断是否点赞
						if (agreePersistences2.size()==0) {
							question_CommunityView.setIsLike("0");
						}else {
							question_CommunityView.setIsLike("1");
						}
						question_CommunityViews.add(question_CommunityView);
					}
				}
			}
		}else {
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.spider_ClassifyListByName(classifyname, "0");
			if (type.equals("all")) {//展示全部问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity(classifyPersistences.get(0).getFAQCLASSIFYID());
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					if (list.size()==0) {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setCommunityNumber(communityNumber);
						List<ClassifyPersistence> classifyPersistences2 = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
						question_CommunityViews.add(question_CommunityView);
					}else {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						question_CommunityView.setCommunityNumber(communityNumber);
						List<ClassifyPersistence> classifyPersistences2 = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
						//获取用户信息
						List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
						question_CommunityView.setUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						List<CommunityAnswerPersistence> list3 = CommunityAnswerHelper.question_CommunityAnswer_userId(userPersistences.get(0).getUSERID());
						List<AgreePersistence> list4 = AgreeHelper.getAgreebyUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setTotalCommunityNumber(Integer.toString(list3.size()));
						question_CommunityView.setTotalLikesNumber(Integer.toString(list4.size()));
						List<AgreePersistence> agreePersistences2 = AgreeHelper.getAgree(useremail, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
						//判断是否点赞
						if (agreePersistences2.size()==0) {
							question_CommunityView.setIsLike("0");
						}else {
							question_CommunityView.setIsLike("1");
						}
						question_CommunityViews.add(question_CommunityView);
					}
				}
			}else if (type.equals("2")) {//展示未有答案的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2(classifyPersistences.get(0).getFAQCLASSIFYID(),0);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					if (list.size()==0) {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setCommunityNumber(communityNumber);
						List<ClassifyPersistence> classifyPersistences2 = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
						question_CommunityViews.add(question_CommunityView);
					}else {
					
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						question_CommunityView.setCommunityNumber(communityNumber);
						List<ClassifyPersistence> classifyPersistences2 = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
						//获取用户信息
						List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
						question_CommunityView.setUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						List<CommunityAnswerPersistence> list3 = CommunityAnswerHelper.question_CommunityAnswer_userId(userPersistences.get(0).getUSERID());
						List<AgreePersistence> list4 = AgreeHelper.getAgreebyUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setTotalCommunityNumber(Integer.toString(list3.size()));
						question_CommunityView.setTotalLikesNumber(Integer.toString(list4.size()));
						List<AgreePersistence> agreePersistences2 = AgreeHelper.getAgree(useremail, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
						//判断是否点赞
						if (agreePersistences2.size()==0) {
							question_CommunityView.setIsLike("0");
						}else {
							question_CommunityView.setIsLike("1");
						}
						question_CommunityViews.add(question_CommunityView);
					}
				}
			}else if (type.equals("1")) {//展示已回答的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2(classifyPersistences.get(0).getFAQCLASSIFYID(),1);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					if (list.size()==0) {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setCommunityNumber(communityNumber);
						List<ClassifyPersistence> classifyPersistences2 = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
						question_CommunityViews.add(question_CommunityView);
					}else {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						question_CommunityView.setCommunityNumber(communityNumber);
						List<ClassifyPersistence> classifyPersistences2 = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
						question_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
						//获取用户信息
						List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
						question_CommunityView.setUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						List<CommunityAnswerPersistence> list3 = CommunityAnswerHelper.question_CommunityAnswer_userId(userPersistences.get(0).getUSERID());
						List<AgreePersistence> list4 = AgreeHelper.getAgreebyUserId(userPersistences.get(0).getUSERID());
						question_CommunityView.setTotalCommunityNumber(Integer.toString(list3.size()));
						question_CommunityView.setTotalLikesNumber(Integer.toString(list4.size()));
						List<AgreePersistence> agreePersistences2 = AgreeHelper.getAgree(useremail, communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
						//判断是否点赞
						if (agreePersistences2.size()==0) {
							question_CommunityView.setIsLike("0");
						}else {
							question_CommunityView.setIsLike("1");
						}
						question_CommunityViews.add(question_CommunityView);
					}
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
		communityAnswerPersistence.setISNOTICE(0);
		CommunityAnswerHelper.addComment(communityAnswerPersistence);
	}
	/*
	 * zyq_question2_问题展示_最佳答案
	 */
	public static List<Question2_CommunityView> question2_CommunityViews_best(String useremail,String questionId){
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
				question2_CommunityReplayViews.add(question2_CommunityReplayView);
			}
			String communityNumber = Integer.toString(commentPersistences2.size());
			question2_CommunityView.setCommunityNumber(communityNumber);
			List<AgreePersistence> agreePersistences = AgreeHelper.getAgree_id(communityAnswerPersistence.getCOMMUNITYANSWERID());
			String likeNumber = Integer.toString(agreePersistences.size());
			List<AgreePersistence> agreePersistences2 = AgreeHelper.getAgree(useremail, communityAnswerPersistence.getCOMMUNITYANSWERID());
			//判断是否点赞
			if (agreePersistences2.size()==0) {
				question2_CommunityView.setIsLike("0");
			}else {
				question2_CommunityView.setIsLike("1");
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
	public static List<Question2_CommunityView> question2_CommunityViews_other(String useremail,String questionId){
		List<Question2_CommunityView> question2_CommunityViews = new ArrayList<Question2_CommunityView>();
		List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer_other(questionId);
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
				question2_CommunityReplayView.setUserId(userPersistences2.get(0).getUSERID());
				question2_CommunityReplayViews.add(question2_CommunityReplayView);
			}
			String communityNumber = Integer.toString(commentPersistences2.size());
			question2_CommunityView.setCommunityNumber(communityNumber);
			List<AgreePersistence> agreePersistences = AgreeHelper.getAgree_id(communityAnswerPersistence.getCOMMUNITYANSWERID());
			String likeNumber = Integer.toString(agreePersistences.size());
			List<AgreePersistence> agreePersistences2 = AgreeHelper.getAgree(useremail, communityAnswerPersistence.getCOMMUNITYANSWERID());
			//判断是否点赞
			if (agreePersistences2.size()==0) {
				question2_CommunityView.setIsLike("0");
			}else {
				question2_CommunityView.setIsLike("1");
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
	public static void saveReplyQuestion(String useremail,String content,String questionId){
		CommunityAnswerPersistence communityAnswerPersistence = new CommunityAnswerPersistence();
		communityAnswerPersistence.setCOMMUNITYANSWERID(UUID.randomUUID().toString());
		communityAnswerPersistence.setCOMMUNITYQUESTIONID(questionId);
		communityAnswerPersistence.setCONTENT(content);
		communityAnswerPersistence.setISBESTANSWER(0);
		communityAnswerPersistence.setISNOTICE(0);
	    Date date=new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
		communityAnswerPersistence.setTIME(time);
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		communityAnswerPersistence.setUSERID(userPersistences.get(0).getUSERID());
		CommunityAnswerHelper.addComment(communityAnswerPersistence);
	}
}
