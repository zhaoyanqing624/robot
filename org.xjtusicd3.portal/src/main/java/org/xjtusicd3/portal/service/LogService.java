package org.xjtusicd3.portal.service;

import java.util.List;

import org.xjtusicd3.database.helper.LogHelper;
import org.xjtusicd3.database.model.LogPersistence;
 

public class LogService {

	public static List<LogPersistence> getLog(){
		 
		List<LogPersistence> log = LogHelper.getLog();
		 
		return log;
	}
}