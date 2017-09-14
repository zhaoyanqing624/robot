package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.LogHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.LogPersistence;
import org.xjtusicd3.database.model.UserPersistence;

public class LogService {
	public static List<LogPersistence> getLog(){
		 
		List<LogPersistence> log = LogHelper.getLog();
		 
		return log;
	}

	//前台添加日志_2017年9月14日22:23:30
	public static void addLog(String userId, String faqPath) {
		LogPersistence logPersistence = new LogPersistence();
		String logId = UUID.randomUUID().toString();
		Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String logTime = format.format(date);
        logPersistence.setLogId(logId);
        logPersistence.setUserId(userId);
        logPersistence.setLogMethod(faqPath);
        logPersistence.setLogTime(logTime);
		LogHelper.addLog(logId,userId,faqPath,logTime);
        
	}
	
	/*
	 * zpz_knowledgeindex_FAQ的展示
	 */
//	public static List<logindexView> logindexViews(){
//		List<logindexView> logindexViews = new ArrayList<logindexView>();
//		List<LogPersistence> logPersistences = LogHelper.getLog();
//		for(LogPersistence logPersistence:logPersistences){
//			logindexView logindexView = new logindexView();
//			logindexView.setLogMethod(logPersistence.getLogMethod());
//			logindexView.setLogTime(logPersistence.getLogTime());
//			List<UserPersistence> userPersistences = UserHelper.getUserNameById(logPersistence.getUserId());
//			logindexView.setLogUser(userPersistences.get(0).getUSERNAME()); 
//			logindexViews.add(logindexView);
//		}
//		return logindexViews;
//	}
	/*
	 * zpz_add_log
	 */
//	public static void saveLog(String userId,String logPermission)
//	{
//		logindexView logindexView = new logindexView();
//		String logId = UUID.randomUUID().toString();
//		logindexView.setLogUersId(userId);
//		Date date=new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String logTime = format.format(date);
//		logindexView.setLogTime(logTime);
//		LogHelper.getLog(logId, userId, logPermission, logTime);
//	}
}
