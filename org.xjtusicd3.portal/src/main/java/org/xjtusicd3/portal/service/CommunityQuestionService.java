package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.portal.view.ProblemindexView;

public class CommunityQuestionService {

	public static List<CommunityQuestionPersistence> getAllCommunityQuestion() 
	{
		List<CommunityQuestionPersistence> communityquestionlist = CommunityQuestionHelper.getAllCommunityQuestion();
		return communityquestionlist;
		
	}
	
	/**
	 * author:
	 * abstract:获取前台社区问题
	 * data:2017年10月11日11:08:20
	 */
	public static List<ProblemindexView> problemindexViews(){
		List<ProblemindexView> problemindexViews = new ArrayList<ProblemindexView>();
		List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.getAllCommunityQuestion();
		for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
			ProblemindexView problemindexView = new ProblemindexView();
			problemindexView.setProblemId(communityQuestionPersistence.getCOMMUNITYQUESTIONID()); 
			problemindexView.setProblemTitle(communityQuestionPersistence.getTITLE()); 
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
			problemindexView.setProblemClassify(classifyPersistences.get(0).getFAQCLASSIFYNAME());
			List<UserPersistence> userPersistences = UserHelper.getUserNameById(communityQuestionPersistence.getUSERID());
			problemindexView.setProblemUser(userPersistences.get(0).getUSERNAME());
			problemindexView.setProblemTime(communityQuestionPersistence.getTIME());
			problemindexView.setProblemContent(communityQuestionPersistence.getCONTENT());
			//zzl_2017年10月11日15:29:41
			problemindexView.setIsanswer(communityQuestionPersistence.getISANSWER());
			System.out.println("Isanswer:"+communityQuestionPersistence.getISANSWER());
			problemindexViews.add(problemindexView);
		}
		return problemindexViews;
	}
	
	
	/*
	 * get community problem by id
	 */
	public static List<ProblemindexView> getCommunityQuestionById(String communityProblemId){
		List<ProblemindexView> problemindexViews = new ArrayList<ProblemindexView>();
		List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.getCommunityQuestionById(communityProblemId);
		for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
			ProblemindexView problemindexView = new ProblemindexView();
			problemindexView.setProblemId(communityQuestionPersistence.getCOMMUNITYQUESTIONID()); 
			problemindexView.setProblemTitle(communityQuestionPersistence.getTITLE()); 
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
			problemindexView.setProblemClassify(classifyPersistences.get(0).getFAQCLASSIFYNAME());
			List<UserPersistence> userPersistences = UserHelper.getUserNameById(communityQuestionPersistence.getUSERID());
			problemindexView.setProblemUser(userPersistences.get(0).getUSERNAME());
			 
			problemindexView.setProblemTime(communityQuestionPersistence.getTIME());
			problemindexView.setProblemContent(communityQuestionPersistence.getCONTENT());
			problemindexViews.add(problemindexView);
		}
		return problemindexViews;
	}

	//zzl_2017年10月11日20:03:30
	public static List<ProblemindexView> problemindexViews2() {
		List<ProblemindexView> problemindexViews = new ArrayList<ProblemindexView>();
		List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.getAllCommunityQuestion2();
		for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
			ProblemindexView problemindexView = new ProblemindexView();
			problemindexView.setProblemId(communityQuestionPersistence.getCOMMUNITYQUESTIONID()); 
			problemindexView.setProblemTitle(communityQuestionPersistence.getTITLE()); 
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistence.getCLASSIFYID());
			problemindexView.setProblemClassify(classifyPersistences.get(0).getFAQCLASSIFYNAME());
			List<UserPersistence> userPersistences = UserHelper.getUserNameById(communityQuestionPersistence.getUSERID());
			problemindexView.setProblemUser(userPersistences.get(0).getUSERNAME());
			problemindexView.setProblemTime(communityQuestionPersistence.getTIME());
			problemindexView.setProblemContent(communityQuestionPersistence.getCONTENT());
			//zzl_2017年10月11日15:29:41
			problemindexView.setIsanswer(communityQuestionPersistence.getISANSWER());
			System.out.println("Isanswer2:"+communityQuestionPersistence.getISANSWER());
			problemindexViews.add(problemindexView);
		}
		return problemindexViews;
	}
}
