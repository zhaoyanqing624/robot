package org.xjtusicd3.partner.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.FaqHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.FaqPersistence;
import org.xjtusicd3.partner.view.ClassifyNameView;
import org.xjtusicd3.partner.view.ClassifyView;
import org.xjtusicd3.partner.view.ClassifyView2;
import org.xjtusicd3.partner.view.FaqTitleView;
public class ClassifyService {
//	/*
//	 * robot-分类
//	 */
//	public static List<ClassifyView> classify_robot(){
//		List<ClassifyView> classifyViews = new ArrayList<ClassifyView>();
//		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.FirstClassify_robot();
//		for(ClassifyPersistence classifyPersistence:classifyPersistences){
//			List<ClassifyView2> classifyViews2 = new ArrayList<ClassifyView2>();
//			List<ClassifyPersistence> classifyPersistences2 = ClassifyHelper.FirstClassify_robot2(classifyPersistence.getClassifyId());
//			for(ClassifyPersistence cPersistence:classifyPersistences2){
//				List<FaqTitleView> faqTitleViews = new ArrayList<FaqTitleView>();
//				List<FaqPersistence> faqPersistences = FaqHelper.SecondClassify_robot(cPersistence.getClassifyId());
//				for(FaqPersistence faqPersistence:faqPersistences){
//					FaqTitleView fView = new FaqTitleView(faqPersistence);
//					faqTitleViews.add(fView);
//				}
//				ClassifyView2 view2 = new ClassifyView2(cPersistence);
//				view2.setContent(faqTitleViews);
//				classifyViews2.add(view2);
//			}
//			ClassifyView view = new ClassifyView(classifyPersistence);
//			view.setContent(classifyViews2);
//			classifyViews.add(view);
//		}
//		return classifyViews;
//	}
	/*
	 * robot-分类
	 */
	public static String classify(){
		List<ClassifyNameView> classifyNameViews = new ArrayList<ClassifyNameView>();
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName1();
		for(ClassifyPersistence classifyPersistence:classifyPersistences){
			ClassifyNameView classifyNameView = new ClassifyNameView(classifyPersistence);
			classifyNameViews.add(classifyNameView);
		}
		int length = classifyNameViews.size();
		String firstTitle = "";
		String content = "";
		String content2 = "";
		String secondTitle = "";
		String faqTitle = "";
		String content_string = "{title:'";
		String firstTitle_string = "{title:['";
		String string = "";
		for(ClassifyNameView classifyNameView:classifyNameViews){
			
			
			
			
			List<ClassifyNameView> classifyNameViews2 = new ArrayList<ClassifyNameView>();
			List<ClassifyPersistence> classifyPersistences2 = ClassifyHelper.classifyName2(classifyNameView.getClassifyId());
			for(ClassifyPersistence classifyPersistence2:classifyPersistences2){
				ClassifyNameView classifyNameView2 = new ClassifyNameView(classifyPersistence2);
				classifyNameViews2.add(classifyNameView2);
			}
			int length2 = classifyNameViews2.size();
			for(ClassifyNameView classifyNameView2:classifyNameViews2){
				
				
				
				
				
				List<FaqTitleView> faqTitleViews = new ArrayList<FaqTitleView>();
				List<FaqPersistence> faqPersistences = FaqHelper.SecondClassify_robot(classifyNameView2.getClassifyId());
				for(FaqPersistence faqPersistence:faqPersistences){
					FaqTitleView faqTitleView = new FaqTitleView(faqPersistence);
					faqTitleViews.add(faqTitleView);
				}
				int length3 = faqTitleViews.size();
				for(FaqTitleView faqTitleView:faqTitleViews){
					length3--;
					faqTitle = faqTitleView.getFaqTitle();
					content2 += "'"+faqTitle+"'";
					if (length3>=1) {
						content2 += ",";
					}else {
						content2 += "";
					}
				}
				
				
				
				
				
				length2--;
				secondTitle = classifyNameView2.getClassifyName();
				content +=content_string+secondTitle+"',"+"content:["+content2+ "]}";
				if (length2>=1) {
					content += ",";
				}else {
					content += "";
				}
			}
			
			
			
			
			
			
			length--;
			firstTitle = classifyNameView.getClassifyName();
			string += firstTitle_string+firstTitle+"'],id:'speedMenu'+parseInt(Math.random()*1000),"+"content:["+content+"]"+"}";
			if (length>=1) {
				string += ",";
			}else {
				string += "";
			}
		} 
		return string;
		
	} 
}
