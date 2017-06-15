package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.LogHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.LogPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.portal.view.KnowledgeindexView;
import org.xjtusicd3.portal.view.logindexView;
 

public class LogService {

	public static List<LogPersistence> getLog(){
		 
		List<LogPersistence> log = LogHelper.getLog();
		 
		return log;
	}
	
	/*
	 * zpz_knowledgeindex_FAQ的展示
	 */
	public static List<logindexView> logindexViews(){
		List<logindexView> logindexViews = new ArrayList<logindexView>();
		List<LogPersistence> logPersistences = LogHelper.getLog();
		for(LogPersistence logPersistence:logPersistences){
			logindexView logindexView = new logindexView();
			logindexView.setLogMethod(logPersistence.getLogMethod());
			logindexView.setLogTime(logPersistence.getLogTime());
			List<UserPersistence> userPersistences = UserHelper.getUserNameById(logPersistence.getUserId());
			logindexView.setLogUser(userPersistences.get(0).getUSERNAME()); 
			logindexViews.add(logindexView);
		}
		return logindexViews;
	}
	
}