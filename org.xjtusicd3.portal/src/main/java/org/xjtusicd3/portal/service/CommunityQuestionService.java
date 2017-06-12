package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
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
			problemindexViews.add(problemindexView);
		}
		return problemindexViews;
	}
}
