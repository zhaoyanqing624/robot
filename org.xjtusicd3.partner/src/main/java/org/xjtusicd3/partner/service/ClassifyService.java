package org.xjtusicd3.partner.service;

import java.util.List;

import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.FaqHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.FaqPersistence;
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
			String faqTitle = "";
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
					content2 += "\""+faqTitle+"\"";
					if (length3>1) {
						content2 += ",";
					}else {
						content2 += "";
					}
				}
				length2--;
				secondTitle = classifyPersistence2.getClassifyName();
				content +=content_string+secondTitle+"\","+"\"content\":["+content2+ "]}";
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
		
}
