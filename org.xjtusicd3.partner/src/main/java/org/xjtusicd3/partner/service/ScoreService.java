package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.xjtusicd3.database.helper.ScoreHelper;
import org.xjtusicd3.database.model.ScorePersistence;

public class ScoreService {
	/*
	 * zyq_faq3_ajax_评分
	 */
	public static void saveFAQscore(String FAQquestionId,String userId,float score){
    	Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);
    	ScorePersistence scorePersistence = new ScorePersistence();
    	scorePersistence.setFAQQUESTIONID(FAQquestionId);
    	scorePersistence.setSCORE(score);
    	scorePersistence.setSCOREID(UUID.randomUUID().toString());
    	scorePersistence.setTIME(time);
    	scorePersistence.setUSERID(userId);
    	ScoreHelper.saveFAQscore(scorePersistence);
	}
}
