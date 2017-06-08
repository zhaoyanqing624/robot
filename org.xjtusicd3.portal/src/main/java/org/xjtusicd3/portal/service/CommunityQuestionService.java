package org.xjtusicd3.portal.service;

import java.util.List;

import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;

public class CommunityQuestionService {

	public static List<CommunityQuestionPersistence> getAllCommunityQuestion() 
	{
		List<CommunityQuestionPersistence> communityquestionlist = CommunityQuestionHelper.getAllCommunityQuestion();
		return communityquestionlist;
		
	}
	
}
