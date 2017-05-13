package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;

public class CommunityService {
	/*
	 * zyq_ajax_question的增加
	 */
	public static void savaCommunityQuestion(String email,String title,String content,String classifynumber){
		List<UserPersistence> userPersistences = UserHelper.getEmail(email);
		String classifyname = "";
		if (classifynumber=="1") {
			classifyname="个人电脑";
		}else if (classifynumber=="2") {
			classifyname="智能手机";
		}else if (classifynumber=="3") {
			classifyname="平板电脑";
		}else if (classifynumber=="4") {
			classifyname="网络安全";
		}else if (classifynumber=="5") {
			classifyname="影音数码";
		}else if (classifynumber=="6") {
			classifyname="硬件外设";
		}else if (classifynumber=="7") {
			classifyname="操作系统";
		}else if (classifynumber=="8") {
			classifyname="智能设备";
		}else if (classifynumber=="9") {
			classifyname="评测选购";
		}else{
			classifyname="应用软件";
		}
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.question_ClassifyListByName(classifyname,"1");
    	Date date=new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time = format.format(date);
    	CommunityQuestionHelper.saveCommunityQuestion(UUID.randomUUID().toString(),time,title,content,classifyPersistences.get(0).getFAQCLASSIFYID(),userPersistences.get(0).getUSERID(),"0","0",null);
	}
}
