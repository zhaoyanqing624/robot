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
		AnswerPersistence answerPersistence = new AnswerPersistence();
		answerPersistence.setFAQANSWERID(communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
		answerPersistence.setFAQCONTENT(communityAnswerPersistences.get(0).getCONTENT());
		answerPersistence.setUSERID(communityAnswerPersistences.get(0).getUSERID());
		questionPersistence.setFAQQUESTIONID(communityQuestionPersistences.get(0).getCOMMUNITYQUESTIONID());
		answerPersistence.setFAQQUESTIONID(communityQuestionPersistences.get(0).getCOMMUNITYQUESTIONID());
		questionPersistence.setFAQTITLE(communityQuestionPersistences.get(0).getTITLE());
		questionPersistence.setFAQKEYWORDS(null);
		questionPersistence.setFAQCLASSIFYID(communityQuestionPersistences.get(0).getCLASSIFYID());
    	questionPersistence.setCOLLECTION("0");
    	questionPersistence.setSCAN("0");
    	Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);
    	questionPersistence.setMODIFYTIME(time);
    	questionPersistence.setFAQDESCRIPTION(communityQuestionPersistences.get(0).getCONTENT());
    	questionPersistence.setMODIFYNUMBER("2");
    	questionPersistence.setFAQSTATE(2);
    	QuestionHelper.save(questionPersistence);
		AnswerHelper.save(answerPersistence);
//		CommunityQuestionHelper.deleteCommunityAnswerById(problemID);
//		CommunityQuestionHelper.deleteCommunityQuestionById(problemID);
	}
	
	/*
	 * zpz_konwStatisticsView
	 */
	public static String konwStatisticsView(){
		int Total = 0 ;
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName1();
		JSONArray jsonArray = new JSONArray();
		for(ClassifyPersistence classifyPersistence:classifyPersistences){
			List<ClassifyPersistence> classifyPersistences2 = ClassifyHelper.SecondClassify_total(classifyPersistence.getFAQCLASSIFYID());
			int a = 0;
			for(ClassifyPersistence classifyPersistence2:classifyPersistences2){
				int number = QuestionHelper.pageTotal(classifyPersistence2.getFAQCLASSIFYID());
				a = a + number;
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", a);
			jsonObject.put("name",classifyPersistence.getFAQCLASSIFYNAME());
			jsonArray.add(jsonObject);
			Total =Total + a;
		}
		String result = JsonUtil.toJsonString(jsonArray);
		System.out.println(result);
		System.out.println(Total);
		return result;
	}
	
	public static int FaqTotal()
	{
		int Total;
		Total = QuestionHelper.getFaqTotal1();
		return Total;
	}
	public static void main(String[] args)
	{
		konwStatisticsView();
	}
	
}
