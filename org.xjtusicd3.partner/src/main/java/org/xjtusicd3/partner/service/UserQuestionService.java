package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.helper.UserQuestionHelper;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.database.model.UserQuestionPersistence;

public class UserQuestionService {
	/**
	 * author:zzl
	 * abstract:记录用户提问记录
	 * data:2017年10月22日11:29:15
	 */
	public static void addUserQuestion(String username, String comment) {
		// TODO Auto-generated method stub
		//UserQuestionPersistence uQuestion = new UserQuestionPersistence();
//			uQuestion.setUSERQUESTIONID(UUID.randomUUID().toString());
//			uQuestion.setQUESTIONTITLE(comment);
		Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        //	uQuestion.setQUESTIONTIME(time);
        List<QuestionPersistence> qList = QuestionHelper.getFaqQuestion(comment);
		int isFaq;
        if (qList.size()==0) {
			isFaq = 0;
		}else {
			isFaq = 1;
		}
        String userId;
		if(username == null){
			userId = "00000000-0000-0000-0000-000000000000";
		}else {
			List<UserPersistence> list = UserHelper.getUserInfo(username);
			userId = list.get(0).getUSERID();
		}
			UserQuestionHelper.addUserQuestion(UUID.randomUUID().toString(),comment,time,isFaq,userId);

	}

}
