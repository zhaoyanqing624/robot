package org.xjtusicd3.partner.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.view.Faq2_faqContentView;
import org.xjtusicd3.partner.view.Faq3_faqContentView;
import org.xjtusicd3.partner.view.Faq2_faqUserView;
import org.xjtusicd3.partner.view.Faq3_faqAnswer;

public class QuestionService {
	/*
	 * faq2_知识列表
	 */
	public static List<Faq2_faqContentView> faqlist_faq2(String ClassifyId,int pageNow){
		List<Faq2_faqContentView> faq2Views = new ArrayList<Faq2_faqContentView>();
		List<QuestionPersistence> questionPersistences = QuestionHelper.faq2_faqlist(ClassifyId,pageNow);
		for(QuestionPersistence questionPersistence:questionPersistences){
			List<Faq2_faqUserView> user_faq2Views = new ArrayList<Faq2_faqUserView>();
			String userId = QuestionHelper.faq2_userId(questionPersistence.getFAQQUESTIONID());
			List<UserPersistence> userPersistences = QuestionHelper.faq2_userlist(userId);
			for(UserPersistence userPersistence:userPersistences){
				Faq2_faqUserView user_faq2View = new Faq2_faqUserView(userPersistence);
				user_faq2Views.add(user_faq2View);
			}
			Faq2_faqContentView faq2View  = new Faq2_faqContentView(questionPersistence);
			faq2View.setuList(user_faq2Views);
			faq2Views.add(faq2View);
		}
		return faq2Views;
	}
	/*
	 * faq3_知识内容
	 */
	public static List<Faq3_faqContentView> faq3_faqcontent(String QuestionId){
		List<Faq3_faqContentView> faq3Views = new ArrayList<Faq3_faqContentView>();
		List<QuestionPersistence> faqPersistences = QuestionHelper.faq3_faqcontent(QuestionId);
		for(QuestionPersistence faqPersistence:faqPersistences){
			List<Faq2_faqUserView> user_faq2Views = new ArrayList<Faq2_faqUserView>();
			List<Faq3_faqAnswer> faq3_faqAnswers = new ArrayList<Faq3_faqAnswer>();
			String userId = QuestionHelper.faq2_userId(faqPersistence.getFAQQUESTIONID());
			List<UserPersistence> userPersistences = QuestionHelper.faq2_userlist(userId);
			List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(QuestionId);
			for(UserPersistence userPersistence:userPersistences){
				Faq2_faqUserView user_faq2View = new Faq2_faqUserView(userPersistence);
				user_faq2Views.add(user_faq2View);
			}
			for(AnswerPersistence answerPersistence:answerPersistences){
				Faq3_faqAnswer faq3_faqAnswer = new Faq3_faqAnswer(answerPersistence);
				faq3_faqAnswers.add(faq3_faqAnswer);
			}
			Faq3_faqContentView faq3View = new Faq3_faqContentView(faqPersistence);
			faq3View.setuList(user_faq2Views);
			faq3View.setFaqAnswers(faq3_faqAnswers);
			faq3Views.add(faq3View);
		}
		return faq3Views;
	}
}