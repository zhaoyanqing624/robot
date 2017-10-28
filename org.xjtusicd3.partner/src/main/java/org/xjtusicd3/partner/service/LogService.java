package org.xjtusicd3.partner.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.xjtusicd3.database.helper.LogHelper;
import org.xjtusicd3.database.model.LogPersistence;
@Service
@Transactional
public class LogService {
	public static List<LogPersistence> getLog(){
		 
		List<LogPersistence> log = LogHelper.getLog();
		 
		return log;
	}

	//前台添加日志_2017年9月14日22:23:30
//	public static void addLog(String userId, String faqPath) {
//		LogPersistence logPersistence = new LogPersistence();
//		String logId = UUID.randomUUID().toString();
//		Date date=new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String logTime = format.format(date);
//        logPersistence.setLogId(logId);
//        logPersistence.setUserId(userId);
//        logPersistence.setLogMethod(faqPath);
//        logPersistence.setLogTime(logTime);
//		LogHelper.addLog(logId,userId,faqPath,logTime);
//        System.out.println("添加了一条日志信息");
//	}

	/**
	 * author:zzl
	 * abstract:获取用户日志
	 * data:2017年9月15日09:14:27
	 * @param userid 
	 */
	public static List<LogPersistence> getLogs(String userid) {
		List<LogPersistence> logs = LogHelper.getLogs(userid);
		return logs;
	}

	public void insertLog(LogPersistence myLog) {
		LogHelper.insertLog(myLog);
		
	}


}
