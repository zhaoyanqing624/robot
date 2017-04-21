package org.xjtusicd3.partner.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.partner.view.Faq1_ClassifyView;
import org.xjtusicd3.partner.view.Faq1_faqContentView;
public class ClassifyService {
	/*
	 * robot-分类
	 */
	public static String classify(){
		int num  = 1;
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName1();
		int length = classifyPersistences.size()+1;
		String string = "";
		for(ClassifyPersistence classifyPersistence:classifyPersistences){
			String firstTitle = "";
			String content = "";
			String secondTitle = "";
			String faqTitle = "";
			String content_string = "{\"title\":\"";
			String firstTitle_string = "{\"title\":\"";
			List<ClassifyPersistence> classifyPersistences2 = ClassifyHelper.classifyName2(classifyPersistence.getClassifyId());
			int length2 = classifyPersistences2.size()+1;
			for(ClassifyPersistence classifyPersistence2:classifyPersistences2){
				String content2 = "";
				List<QuestionPersistence> faqPersistences = QuestionHelper.SecondClassify_robot(classifyPersistence2.getClassifyId());
				int length3 = faqPersistences.size()+1;
				for(QuestionPersistence faqPersistence:faqPersistences){
					length3--;
					faqTitle = zhuanyi(faqPersistence.getFaqTitle());
					content2 += "{\"faqTitle\":\""+faqTitle+"\"}";
					if (length3>1) {
						content2 += ",";
					}else {
						content2 += "";
					}
				}
				length2--;
				secondTitle = classifyPersistence2.getClassifyName();
				content +=content_string+secondTitle+"\",\"content\":["+content2+ "]}";
				if (length2>1) {
					content += ",";
				}else {
					content += "";
				}
			}
				firstTitle = classifyPersistence.getClassifyName();
				string += firstTitle_string+firstTitle+"\","+"\"id\":\"speedMenu"+num+"\","+"\"content\":["+content+"]"+"}";
				num++;
				length--;
				if (length>1) {
					string += ",";
				}else {
					string += "";
				}
			}
		return string;
		}
    public static String zhuanyi(String string){
    	string = string.replace("\"", "'");
    	return string;
    }

	/*
	 * faq1_下面4栏推荐_按照浏览量
	 */
	public static List<Faq1_ClassifyView> faq1_ClassifyView(String parentId){
		List<Faq1_ClassifyView> faq1_ClassifyViews = new ArrayList<Faq1_ClassifyView>();
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq1_SecondClassify(parentId);
		for(ClassifyPersistence classifyPersistence:classifyPersistences){
			List<Faq1_faqContentView> faq1Views = new ArrayList<Faq1_faqContentView>();
			List<QuestionPersistence> questionPersistences = ClassifyHelper.faq1_faqPersistences(classifyPersistence.getClassifyId());
			List<Faq1_faqContentView> faq1Views2 = new ArrayList<Faq1_faqContentView>();
			List<QuestionPersistence> questionPersistences2 = ClassifyHelper.faq1_faqPersistences2(classifyPersistence.getClassifyId());
			for(QuestionPersistence questionPersistence:questionPersistences){
				Faq1_faqContentView faq1View = new Faq1_faqContentView(questionPersistence);
				faq1Views.add(faq1View);
			}
			for(QuestionPersistence questionPersistence:questionPersistences2){
				Faq1_faqContentView faq1View = new Faq1_faqContentView(questionPersistence);
				faq1Views2.add(faq1View);
			}
			Faq1_ClassifyView view = new Faq1_ClassifyView(classifyPersistence);
			view.setContent(faq1Views);
			view.setContent2(faq1Views2);
			faq1_ClassifyViews.add(view);
		}
		return faq1_ClassifyViews;
	}
	/*
	 * faq2_获取第二类分类的名称、第一类分类的名称
	 */
	public static List<ClassifyPersistence> faq2_classify2(String ClassifyId){
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(ClassifyId);
		return classifyPersistences;
	}
	public static List<ClassifyPersistence> faq2_classify(String ClassifyId){
		String classifyParentId = ClassifyHelper.faq2_classifyParentId(ClassifyId);
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(classifyParentId);
		return classifyPersistences;
	}

}
