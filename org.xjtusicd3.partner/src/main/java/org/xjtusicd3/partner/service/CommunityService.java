package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.view.Question_CommunityView;

public class CommunityService {
	/*
	 * zyq_ajax_question的增加
	 */
	public static void savaCommunityQuestion(String email,String title,String content,String classifynumber){
		List<UserPersistence> userPersistences = UserHelper.getEmail(email);
		String classifyname = "";
		if (classifynumber=="1") {
			classifyname="个人电脑";
		}else if (classifynumber=="2") {
			classifyname="智能手机";
		}else if (classifynumber=="3") {
			classifyname="平板电脑";
		}else if (classifynumber=="4") {
			classifyname="网络安全";
		}else if (classifynumber=="5") {
			classifyname="影音数码";
		}else if (classifynumber=="6") {
			classifyname="硬件外设";
		}else if (classifynumber=="7") {
			classifyname="操作系统";
		}else if (classifynumber=="8") {
			classifyname="智能设备";
		}else if (classifynumber=="9") {
			classifyname="评测选购";
		}else{
			classifyname="应用软件";
		}
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.question_ClassifyListByName(classifyname,"1");
    	Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);
    	CommunityQuestionHelper.saveCommunityQuestion(UUID.randomUUID().toString(),time,title,content,classifyPersistences.get(0).getFAQCLASSIFYID(),userPersistences.get(0).getUSERID(),"0","0",null,0);
	}
	/*
	 * zyq_question_问题展示
	 */
	public static List<Question_CommunityView> Question_CommunityView(String type,String classifyname){
		List<Question_CommunityView> question_CommunityViews = new ArrayList<Question_CommunityView>();
		if (classifyname=="all") {
			if (type=="all") {//展示全部问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity_isanswer();
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					if (list.size()==0) {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setCommunityNumber(communityNumber);
						question_CommunityViews.add(question_CommunityView);
					}else {
					
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						question_CommunityView.setLikesNumber(list.get(0).getLIKES());
						question_CommunityView.setCommunityNumber(communityNumber);
						//获取用户信息
						List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						//获取用户评论总数和点赞总数
						String likesNumber =Integer.toString(CommunityAnswerHelper.likesNumber());
						List<CommunityAnswerPersistence> communityAnswerPersistences2 = CommunityAnswerHelper.question_CommunityAnswer_userId(list.get(0).getUSERID());
						question_CommunityView.setTotalLikesNumber(likesNumber);
						question_CommunityView.setTotalCommunityNumber(Integer.toString(communityAnswerPersistences2.size()));
						question_CommunityViews.add(question_CommunityView);
					}
				}
			}else if (type=="0") {//展示未有答案的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2_isanswer(0);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					if (list.size()==0) {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setCommunityNumber(communityNumber);
						question_CommunityViews.add(question_CommunityView);
					}else {
					
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						question_CommunityView.setLikesNumber(list.get(0).getLIKES());
						question_CommunityView.setCommunityNumber(communityNumber);
						//获取用户信息
						List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						//获取用户评论总数和点赞总数
						String likesNumber =Integer.toString(CommunityAnswerHelper.likesNumber());
						List<CommunityAnswerPersistence> communityAnswerPersistences2 = CommunityAnswerHelper.question_CommunityAnswer_userId(list.get(0).getUSERID());
						question_CommunityView.setTotalLikesNumber(likesNumber);
						question_CommunityView.setTotalCommunityNumber(Integer.toString(communityAnswerPersistences2.size()));
						question_CommunityViews.add(question_CommunityView);
					}
				}
			}else if (type=="1") {//展示已回答的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2_isanswer(1);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					if (list.size()==0) {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setCommunityNumber(communityNumber);
						question_CommunityViews.add(question_CommunityView);
					}else {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						question_CommunityView.setLikesNumber(list.get(0).getLIKES());
						question_CommunityView.setCommunityNumber(communityNumber);
						//获取用户信息
						List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						//获取用户评论总数和点赞总数
						String likesNumber =Integer.toString(CommunityAnswerHelper.likesNumber());
						List<CommunityAnswerPersistence> communityAnswerPersistences2 = CommunityAnswerHelper.question_CommunityAnswer_userId(list.get(0).getUSERID());
						question_CommunityView.setTotalLikesNumber(likesNumber);
						question_CommunityView.setTotalCommunityNumber(Integer.toString(communityAnswerPersistences2.size()));
						question_CommunityViews.add(question_CommunityView);
					}
				}
			}
		}else {
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.spider_ClassifyListByName(classifyname, "1");
			if (type=="all") {//展示全部问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity(classifyPersistences.get(0).getFAQCLASSIFYID());
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					if (list.size()==0) {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setCommunityNumber(communityNumber);
						question_CommunityViews.add(question_CommunityView);
					}else {
					
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						question_CommunityView.setLikesNumber(list.get(0).getLIKES());
						question_CommunityView.setCommunityNumber(communityNumber);
						//获取用户信息
						List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						//获取用户评论总数和点赞总数
						String likesNumber =Integer.toString(CommunityAnswerHelper.likesNumber());
						List<CommunityAnswerPersistence> communityAnswerPersistences2 = CommunityAnswerHelper.question_CommunityAnswer_userId(list.get(0).getUSERID());
						question_CommunityView.setTotalLikesNumber(likesNumber);
						question_CommunityView.setTotalCommunityNumber(Integer.toString(communityAnswerPersistences2.size()));
						question_CommunityViews.add(question_CommunityView);
					}
				}
			}else if (type=="0") {//展示未有答案的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2(classifyPersistences.get(0).getFAQCLASSIFYID(),0);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					if (list.size()==0) {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setCommunityNumber(communityNumber);
						question_CommunityViews.add(question_CommunityView);
					}else {
					
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						question_CommunityView.setLikesNumber(list.get(0).getLIKES());
						question_CommunityView.setCommunityNumber(communityNumber);
						//获取用户信息
						List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						//获取用户评论总数和点赞总数
						String likesNumber =Integer.toString(CommunityAnswerHelper.likesNumber());
						List<CommunityAnswerPersistence> communityAnswerPersistences2 = CommunityAnswerHelper.question_CommunityAnswer_userId(list.get(0).getUSERID());
						question_CommunityView.setTotalLikesNumber(likesNumber);
						question_CommunityView.setTotalCommunityNumber(Integer.toString(communityAnswerPersistences2.size()));
						question_CommunityViews.add(question_CommunityView);
					}
				}
			}else if (type=="1") {//展示已回答的问题
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2(classifyPersistences.get(0).getFAQCLASSIFYID(),1);
				for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
					//获取评论数
					List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
					String communityNumber = Integer.toString(communityAnswerPersistences.size());
					//判断是否有最佳答案
					List<CommunityAnswerPersistence> list = CommunityAnswerHelper.question_iscurrentAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
					if (list.size()==0) {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setCommunityNumber(communityNumber);
						question_CommunityViews.add(question_CommunityView);
					}else {
						Question_CommunityView question_CommunityView = new Question_CommunityView();
						question_CommunityView.setCommunityTitle(communityQuestionPersistence.getTITLE());
						question_CommunityView.setCommunityQuestion(communityQuestionPersistence.getCONTENT());
						question_CommunityView.setTime(communityQuestionPersistence.getTIME());
						question_CommunityView.setAnswer(list.get(0).getCONTENT());
						question_CommunityView.setLikesNumber(list.get(0).getLIKES());
						question_CommunityView.setCommunityNumber(communityNumber);
						//获取用户信息
						List<UserPersistence> userPersistences = UserHelper.getEmail_id(list.get(0).getUSERID());
						question_CommunityView.setUserImage(userPersistences.get(0).getAVATAR());
						question_CommunityView.setUserName(userPersistences.get(0).getUSERNAME());
						question_CommunityView.setSignature(userPersistences.get(0).getUSERSIGNATURE());
						//获取用户评论总数和点赞总数
						String likesNumber =Integer.toString(CommunityAnswerHelper.likesNumber());
						List<CommunityAnswerPersistence> communityAnswerPersistences2 = CommunityAnswerHelper.question_CommunityAnswer_userId(list.get(0).getUSERID());
						question_CommunityView.setTotalLikesNumber(likesNumber);
						question_CommunityView.setTotalCommunityNumber(Integer.toString(communityAnswerPersistences2.size()));
						question_CommunityViews.add(question_CommunityView);
					}
				}
			}
		}

		return question_CommunityViews;
	}
}
