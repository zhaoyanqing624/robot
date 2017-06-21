package org.xjtusicd3.portal.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.naming.spi.DirStateFactory.Result;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.portal.view.KnowledgeindexView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FaqService {
	public static String getFaq(){
		String result = "";
		List<QuestionPersistence> list = QuestionHelper.getFaq();
		for(int i = 0;i<list.size();i++){
			List<ClassifyPersistence> cList = ClassifyHelper.faq2_classify(list.get(i).getFAQCLASSIFYID());
			 result += "{\"id\":\""+(i+1)+"\",\"faqTitle\":\""+list.get(i).getFAQTITLE()+"\",\"classifyName\":\""+cList.get(0).getFAQCLASSIFYNAME()+"\"}";
			if (i<list.size()-1) {
				result += ",";
			}else {
				result += "";
			}
		}
		return result;
	}
	
	public static String tsettset(){
		String result="";
		List<QuestionPersistence> list = QuestionHelper.getFaq();
		for(int i = 0;i<list.size();i++){
			List<ClassifyPersistence> cList = ClassifyHelper.faq2_classify(list.get(i).getFAQCLASSIFYID());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", i+1);
			jsonObject.put("faqTitle", list.get(i).getFAQTITLE());
			jsonObject.put("classifyName", cList.get(0).getFAQCLASSIFYNAME());
			result += JsonUtil.toJsonString(jsonObject);
		}
		System.out.println(result);
		return result;
	}
	public static void main(String[] args) {
		tsettset();
	}
	/*
	 * zpz_knowledgeindex_FAQ的展示
	 */
	public static List<KnowledgeindexView> knowledgeindexViews(){
		List<KnowledgeindexView> knowledgeindexViews = new ArrayList<KnowledgeindexView>();
		List<QuestionPersistence> questionPersistences = QuestionHelper.getFaq();
		for(QuestionPersistence questionPersistence:questionPersistences){
			KnowledgeindexView knowledgeindexView = new KnowledgeindexView();
			knowledgeindexView.setFaqTitle(questionPersistence.getFAQTITLE());
			knowledgeindexView.setFaqId(questionPersistence.getFAQQUESTIONID());
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(questionPersistence.getFAQCLASSIFYID());
			knowledgeindexView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
			knowledgeindexView.setFaqKeyWord(questionPersistence.getFAQKEYWORDS());
			knowledgeindexViews.add(knowledgeindexView);
		}
		return knowledgeindexViews;
	}
	
	public static List<KnowledgeindexView> getFAQinfo_id(String faqId){
		List<KnowledgeindexView> knowledgeindexViews = new ArrayList<KnowledgeindexView>();
		List<QuestionPersistence> questionPersistences = QuestionHelper.faq3_faqcontent(faqId);
		for(QuestionPersistence questionPersistence:questionPersistences){
			KnowledgeindexView knowledgeindexView = new KnowledgeindexView();
			knowledgeindexView.setFaqTitle(questionPersistence.getFAQTITLE());
			knowledgeindexView.setFaqId(questionPersistence.getFAQQUESTIONID());
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(questionPersistence.getFAQCLASSIFYID());
			knowledgeindexView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
			knowledgeindexView.setFaqDescription(questionPersistence.getFAQDESCRIPTION());
			List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(questionPersistence.getFAQQUESTIONID());
			knowledgeindexView.setFaqContent(answerPersistences.get(0).getFAQCONTENT());
			knowledgeindexView.setFaqKeyWord(questionPersistence.getFAQKEYWORDS());
			knowledgeindexViews.add(knowledgeindexView);
		}
		return knowledgeindexViews;
	}
	/*
	 * zpz_ajax_addProblemToFAQ
	 */
	public static void addProblemToFAQ(String problemID)
	{
		List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question2_getCommunity(problemID);
		List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(problemID);
		//开始存入FAQ问题
		QuestionPersistence questionPersistence = new QuestionPersistence();
		questionPersistence.setFAQQUESTIONID(UUID.randomUUID().toString());
		questionPersistence.setFAQTITLE(communityQuestionPersistences.get(0).getTITLE());
    	Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);
    	questionPersistence.setMODIFYTIME(time);
    	questionPersistence.setSCAN("0");
    	QuestionHelper.save(questionPersistence);
    	
		AnswerPersistence answerPersistence = new AnswerPersistence();
		
		 
	}
	
	
}
