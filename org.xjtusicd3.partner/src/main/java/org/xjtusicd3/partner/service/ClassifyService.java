package org.xjtusicd3.partner.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.FaqHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.FaqPersistence;
import org.xjtusicd3.partner.view.Classify_faq1View;
import org.xjtusicd3.partner.view.Faq_faq1View;
public class ClassifyService {
	/*
	 * robot-分类
	 */
	public static String classify(){
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName1();
		int length = classifyPersistences.size()+1;
		String string = "";
		for(ClassifyPersistence classifyPersistence:classifyPersistences){
			String firstTitle = "";
			String content = "";
			String secondTitle = "";
			int secondTitleId;
			String faqTitle = "";
			int faqId;
			String content_string = "{\"title\":\"";
			String firstTitle_string = "{\"title\":\"";
			List<ClassifyPersistence> classifyPersistences2 = ClassifyHelper.classifyName2(classifyPersistence.getClassifyId());
			int length2 = classifyPersistences2.size()+1;
			for(ClassifyPersistence classifyPersistence2:classifyPersistences2){
				String content2 = "";
				List<FaqPersistence> faqPersistences = FaqHelper.SecondClassify_robot(classifyPersistence2.getClassifyId());
				int length3 = faqPersistences.size()+1;
				for(FaqPersistence faqPersistence:faqPersistences){
					length3--;
					faqTitle = faqPersistence.getFaqTitle();
					faqId = faqPersistence.getFaqId();
					content2 += "{\"faqTitle\":\""+faqTitle+"\","+"\"faqId\":\""+faqId+"\"}";
					if (length3>1) {
						content2 += ",";
					}else {
						content2 += "";
					}
				}
				length2--;
				secondTitle = classifyPersistence2.getClassifyName();
				secondTitleId = classifyPersistence2.getClassifyId();
				content +=content_string+secondTitle+"\","+"\"id\":\""+secondTitleId+"\",\"content\":["+content2+ "]}";
				if (length2>1) {
					content += ",";
				}else {
					content += "";
				}
			}
				int num  = classifyPersistence.getClassifyId();
				length--;
				firstTitle = classifyPersistence.getClassifyName();
				string += firstTitle_string+firstTitle+"\","+"\"id\":\"speedMenu"+num+"\","+"\"content\":["+content+"]"+"}";
				if (length>1) {
					string += ",";
				}else {
					string += "";
				}
			}
		return string;
		}
	/*
	 * faq、faq1_右侧的第一类分类
	 */
	public static List<ClassifyPersistence> classify_first(){
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName1();
		return classifyPersistences;
	}
	/*
	 * faq、faq1_右侧的第一类分类
	 */
	public static List<ClassifyPersistence> classify_second(int parentId){
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName2_2(parentId);
		return classifyPersistences;
	}
	/*
	 * faq1_下面4栏推荐_按照浏览量
	 */
	public static List<Classify_faq1View> classify_faq1Views(int parentId){
		List<Classify_faq1View> classify_faq1Views = new ArrayList<Classify_faq1View>();
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.SecondClassify_faq1(parentId);
		for(ClassifyPersistence classifyPersistence:classifyPersistences){
			List<Faq_faq1View> faq1Views = new ArrayList<Faq_faq1View>();
			List<FaqPersistence> faqPersistences = ClassifyHelper.faqPersistences_faq1(classifyPersistence.getClassifyId());
			List<Faq_faq1View> faq1Views2 = new ArrayList<Faq_faq1View>();
			List<FaqPersistence> faqPersistences2 = ClassifyHelper.faqPersistences2_faq1(classifyPersistence.getClassifyId());
			for(FaqPersistence faqPersistence:faqPersistences){
				Faq_faq1View faq1View = new Faq_faq1View(faqPersistence);
				faq1Views.add(faq1View);
			}
			for(FaqPersistence faqPersistence:faqPersistences2){
				Faq_faq1View faq1View = new Faq_faq1View(faqPersistence);
				faq1Views2.add(faq1View);
			}
			Classify_faq1View view = new Classify_faq1View(classifyPersistence);
			view.setContent(faq1Views);
			view.setContent2(faq1Views2);
			classify_faq1Views.add(view);
		}
		return classify_faq1Views;
	}
	/*
	 * faq2_获取第二类分类的名称、第一类分类的名称
	 */
	public static List<ClassifyPersistence> classify2(int classifyId){
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classify(classifyId);
		return classifyPersistences;
	}
	public static List<ClassifyPersistence> classify(int classifyId){
		int classifyParentId = ClassifyHelper.classifyParentId(classifyId);
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classify(classifyParentId);
		return classifyPersistences;
	}

		
}
