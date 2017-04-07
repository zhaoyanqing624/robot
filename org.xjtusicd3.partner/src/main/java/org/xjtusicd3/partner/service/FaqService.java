package org.xjtusicd3.partner.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.database.helper.FaqHelper;
import org.xjtusicd3.database.model.FaqPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.view.Faq_faq2View;
import org.xjtusicd3.partner.view.User_faq2View;

public class FaqService {
	/*
	 * faq2_知识列表
	 */
	public static List<Faq_faq2View> faqlist_faq2(int faqClassify){
		List<Faq_faq2View> faq2Views = new ArrayList<Faq_faq2View>();
		List<FaqPersistence> faqPersistences = FaqHelper.faqlist_faq2(faqClassify);
		for(FaqPersistence faqPersistence:faqPersistences){
			List<User_faq2View> user_faq2Views = new ArrayList<User_faq2View>();
			List<UserPersistence> userPersistences = FaqHelper.userlist_faq2(faqPersistence.getFaqBelong());
			for(UserPersistence userPersistence:userPersistences){
				User_faq2View user_faq2View = new User_faq2View(userPersistence);
				user_faq2Views.add(user_faq2View);
			}
			Faq_faq2View faq2View  = new Faq_faq2View(faqPersistence);
			faq2View.setuList(user_faq2Views);
			faq2Views.add(faq2View);
		}
		return faq2Views;
	}
	/*
	 * faq3_知识内容
	 */
	public static List<Faq_faq2View> faqcontent_faq3(int faqId){
		List<Faq_faq2View> faq3Views = new ArrayList<Faq_faq2View>();
		List<FaqPersistence> faqPersistences = FaqHelper.faqcontent_faq3(faqId);
		for(FaqPersistence faqPersistence:faqPersistences){
			List<User_faq2View> user_faq2Views = new ArrayList<User_faq2View>();
			List<UserPersistence> userPersistences = FaqHelper.userlist_faq2(faqPersistence.getFaqBelong());
			for(UserPersistence userPersistence:userPersistences){
				User_faq2View user_faq2View = new User_faq2View(userPersistence);
				user_faq2Views.add(user_faq2View);
			}
			Faq_faq2View faq3View = new Faq_faq2View(faqPersistence);
			faq3View.setuList(user_faq2Views);
			faq3Views.add(faq3View);
		}
		return faq3Views;
	}

}
