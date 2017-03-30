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

import net.sf.json.JSONArray;

public class ClassifyService {
	/*
	 * robot-分类
	 */
	public static List<ClassifyView> classify_robot(){
		List<ClassifyView> classifyViews = new ArrayList<ClassifyView>();
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.FirstClassify_robot();
		for(ClassifyPersistence classifyPersistence:classifyPersistences){
			List<ClassifyView2> classifyViews2 = new ArrayList<ClassifyView2>();
			List<ClassifyPersistence> classifyPersistences2 = ClassifyHelper.FirstClassify_robot2(classifyPersistence.getClassifyId());
			for(ClassifyPersistence cPersistence:classifyPersistences2){
				List<FaqTitleView> faqTitleViews = new ArrayList<FaqTitleView>();
				List<FaqPersistence> faqPersistences = FaqHelper.SecondClassify_robot(cPersistence.getClassifyId());
				for(FaqPersistence faqPersistence:faqPersistences){
					FaqTitleView fView = new FaqTitleView(faqPersistence);
					faqTitleViews.add(fView);
				}
				ClassifyView2 view2 = new ClassifyView2(cPersistence);
				view2.setContent(faqTitleViews);
				classifyViews2.add(view2);
			}
			ClassifyView view = new ClassifyView(classifyPersistence);
			view.setContent(classifyViews2);
			classifyViews.add(view);
		}
		return classifyViews;
	}
	/*
	 * robot-第一类
	 */
	public static List<ClassifyNameView> classifyName1(){
		List<ClassifyNameView> classifyNameViews = new ArrayList<ClassifyNameView>();
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName1();
		for(ClassifyPersistence classifyPersistence:classifyPersistences){
			ClassifyNameView classifyNameView = new ClassifyNameView(classifyPersistence);
			classifyNameViews.add(classifyNameView);
		}
		JSONArray jsonArray = new JSONArray().fromObject(classifyNameViews);
		
		return jsonArray;
		
	} 
	
	public static void main(String[] args) {
		System.out.println(classifyName1());
	}
}
