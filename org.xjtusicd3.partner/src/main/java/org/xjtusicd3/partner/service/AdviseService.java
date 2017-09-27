package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.AdviseHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AdvisePersistence;
import org.xjtusicd3.database.model.UserPersistence;

public class AdviseService {
	/*
	 * zyq_advise_添加意见建议
	 */
	public static void saveAdvise(String username,String email,String name,String phone,String text){
		List<UserPersistence> list = UserHelper.getUserInfo(username);
		AdvisePersistence advisePersistence = new AdvisePersistence();
		advisePersistence.setADVISEID(UUID.randomUUID().toString());
		advisePersistence.setEMAIL(email);
		advisePersistence.setNAME(name);
		advisePersistence.setPHONE(phone);
		advisePersistence.setSTATES(0);
		advisePersistence.setTEXT(text);
		advisePersistence.setUSERID(list.get(0).getUSERID());
		advisePersistence.setANNEX("");
		Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        advisePersistence.setADVISETIME(time);
		AdviseHelper.saveAdvise(advisePersistence);
	}
}
