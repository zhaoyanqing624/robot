package org.xjtusicd3.partner.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.MessageHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.MessageHistoryPersistence;
import org.xjtusicd3.database.model.MessagePersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.view.Message_MessageView;

public class MessageService {
	/*
	 * zyq_message_ajax_添加私信
	 */
	public static Message_MessageView message_MessageView(String username, String touserId, String content) {
		List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
		String userId = userPersistences.get(0).getUSERID();
		//保存私信
		MessagePersistence messagePersistence = new MessagePersistence();
		messagePersistence.setMESSAGEID(UUID.randomUUID().toString());
		messagePersistence.setMESSAGECONTENT(content);
    	Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
		messagePersistence.setMESSAGETIME(time);
		messagePersistence.setMESSAGESTATE(1);
		messagePersistence.setPOSTUSERID(userId);
		messagePersistence.setGETUSERID(touserId);
		MessageHelper.save(messagePersistence);
		Message_MessageView message_MessageView = new Message_MessageView();
		message_MessageView.setContent(content);
		message_MessageView.setTime(time);
		message_MessageView.setUserId(userId);
		message_MessageView.setUserImage(userPersistences.get(0).getAVATAR());
		message_MessageView.setUserName(userPersistences.get(0).getUSERNAME());
		
		return message_MessageView;
	}
	/*
	 * zyq_message_查看聊天信息
	 */
	public static List<Message_MessageView> message_MessageViews(String userId){
		List<Message_MessageView> message_MessageViews = new ArrayList<Message_MessageView>();
		List<MessagePersistence> messagePersistences = MessageHelper.getMessage(userId, 1);
		for(MessagePersistence messagePersistence:messagePersistences){
			Message_MessageView message_MessageView = new Message_MessageView();
			message_MessageView.setContent(messagePersistence.getMESSAGECONTENT());
			message_MessageView.setLastContent(messagePersistences.get(messagePersistences.size()-1).getMESSAGECONTENT());
			message_MessageView.setNumber(Integer.toString(messagePersistences.size()));
			message_MessageView.setTime(messagePersistence.getMESSAGETIME());
			List<UserPersistence> uList = UserHelper.getEmail_id(messagePersistence.getPOSTUSERID());
			message_MessageView.setUserId(messagePersistence.getPOSTUSERID());
			message_MessageView.setUserImage(uList.get(0).getAVATAR());
			message_MessageView.setUserName(uList.get(0).getUSERNAME());
			message_MessageViews.add(message_MessageView);
		}
		return message_MessageViews;
	}
	/*
	 * zyq_message_查看私信用户列表
	 */
	public static List<Message_MessageView> message_userList(String userId){
		List<Message_MessageView> message_MessageViews = new ArrayList<Message_MessageView>();
		//有信息的用户
		List<MessagePersistence> messagePersistences = MessageHelper.getUserList(userId,1,2);
		if (messagePersistences.size()!=0) {
			for(MessagePersistence messagePersistence:messagePersistences){
				Message_MessageView message_MessageView = new Message_MessageView();
				List<UserPersistence> userPersistences = UserHelper.getEmail_id(messagePersistence.getPOSTUSERID());
				//判断是否存在新的私信
				List<MessagePersistence> messagePersistences2 = MessageHelper.getMessageContent(userPersistences.get(0).getUSERID(), userId, 1);
				if (messagePersistences2.size()!=0) {
					message_MessageView.setUserImage(userPersistences.get(0).getAVATAR());
					message_MessageView.setUserName(userPersistences.get(0).getUSERNAME());
					message_MessageView.setUserId(userPersistences.get(0).getUSERID());
					message_MessageView.setLastContent(messagePersistences2.get(0).getMESSAGECONTENT());
					message_MessageView.setNumber(Integer.toString(messagePersistences2.size()));
					message_MessageViews.add(message_MessageView);
				}else{
					List<MessageHistoryPersistence> messageHistoryPersistences = MessageHelper.getMessageHistoryList(userPersistences.get(0).getUSERID(), userId);
					if (messageHistoryPersistences.size()==0) {
						message_MessageView.setUserImage(userPersistences.get(0).getAVATAR());
						message_MessageView.setUserName(userPersistences.get(0).getUSERNAME());
						message_MessageView.setUserId(userPersistences.get(0).getUSERID());
						message_MessageView.setNumber("0");
						message_MessageViews.add(message_MessageView);
					}else {
						String time = messageHistoryPersistences.get(0).getTIMEMARK();
						List<MessagePersistence> messagePersistences3 = MessageHelper.getMessageContent_time(userPersistences.get(0).getUSERID(), userId, 2, time);
						if (messagePersistences3.size()!=0) {
							message_MessageView.setUserImage(userPersistences.get(0).getAVATAR());
							message_MessageView.setUserName(userPersistences.get(0).getUSERNAME());
							message_MessageView.setUserId(userPersistences.get(0).getUSERID());
							message_MessageView.setNumber("0");
							message_MessageViews.add(message_MessageView);
						}
					}
				}
			}
		}

		List<Message_MessageView> list = ListSort(message_MessageViews);
		return list;
	}
	//对list里面的元素进行number的排序
	private static List<Message_MessageView> ListSort(List<Message_MessageView> list){
		Collections.sort(list,new Comparator<Message_MessageView>() {
			
			@Override
			public int compare(Message_MessageView o1, Message_MessageView o2) {
				int number1 = Integer.parseInt(o1.getNumber());
				int number2 = Integer.parseInt(o2.getNumber());
				if (number1<number2) {
					return 1;
				}else if (number1>number2) {
					return -1;
				}else {
					return 0;
				}
			}
		});
		return list;
	}

	/*
	 * zyq_message_ajax_查询私信内容
	 */
	public static List<Message_MessageView> message_getMessage(String postuserId, String username) {
		List<Message_MessageView> message_MessageViews = new ArrayList<Message_MessageView>();
		List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
		String getuserId = userPersistences.get(0).getUSERID();
		List<MessagePersistence> messagePersistences = MessageHelper.getMessageContent(postuserId,getuserId,1);
		if (messagePersistences.size()!=0) {
			for(MessagePersistence messagePersistence:messagePersistences){
				Message_MessageView message_MessageView = new Message_MessageView();
				message_MessageView.setContent(messagePersistence.getMESSAGECONTENT());
				List<UserPersistence> uList = UserHelper.getEmail_id(messagePersistence.getPOSTUSERID());
				message_MessageView.setUserImage(uList.get(0).getAVATAR());
				message_MessageView.setUserId(messagePersistence.getPOSTUSERID());
				message_MessageView.setTime(messagePersistence.getMESSAGETIME());
				message_MessageView.setMessageId(messagePersistence.getMESSAGEID());
				message_MessageViews.add(message_MessageView);
				MessageHelper.updateMessageState(messagePersistence.getMESSAGEID(),2);
			}
		}
		return message_MessageViews;
	}
	/*
	 * zyq_message_pushlet_推送未读的消息
	 */
	public static List<Message_MessageView> message_getMessage_pushlet(String getuserId){
		List<Message_MessageView> message_MessageViews = new ArrayList<>();
		List<MessagePersistence> messagePersistences = MessageHelper.getUserList_pushlet(getuserId, 1);
		if(messagePersistences.size()!=0){
			for(MessagePersistence messagePersistence:messagePersistences){
				List<MessagePersistence> messagePersistences1 = MessageHelper.getMessageContent1(messagePersistence.getPOSTUSERID(), getuserId, 1);
				if (messagePersistences1.size()!=0) {
					for(MessagePersistence messagePersistence1:messagePersistences1){
						Message_MessageView message_MessageView = new Message_MessageView();
						message_MessageView.setContent(messagePersistence1.getMESSAGECONTENT());
						message_MessageView.setLastContent(messagePersistences1.get(0).getMESSAGECONTENT());
						List<UserPersistence> uList = UserHelper.getEmail_id(messagePersistence1.getPOSTUSERID());
						message_MessageView.setUserImage(uList.get(0).getAVATAR());
						message_MessageView.setUserId(messagePersistence1.getPOSTUSERID());
						message_MessageView.setTime(messagePersistence1.getMESSAGETIME());
						message_MessageView.setMessageId(messagePersistence1.getMESSAGEID());
						message_MessageViews.add(message_MessageView);
					}
				}
			}
		}
		return message_MessageViews;
	}
	/*
	 * zyq_message_ajax_查看历史私信
	 */
	public static List<Message_MessageView> message_getMessageHistory(String postuserId,String getuserId,int state,int startnumber){
		List<Message_MessageView> message_MessageViews = new ArrayList<Message_MessageView>();
		List<MessagePersistence> messagePersistences = MessageHelper.getMessageContent2(postuserId, getuserId, 2, 0);
		if (messagePersistences.size()!=0) {
			for(MessagePersistence messagePersistence:messagePersistences){
				Message_MessageView message_MessageView = new Message_MessageView();
				message_MessageView.setContent(messagePersistence.getMESSAGECONTENT());
				List<UserPersistence> uList = UserHelper.getEmail_id(messagePersistence.getPOSTUSERID());
				message_MessageView.setUserImage(uList.get(0).getAVATAR());
				message_MessageView.setUserId(messagePersistence.getPOSTUSERID());
				message_MessageView.setTime(messagePersistence.getMESSAGETIME());
				message_MessageView.setMessageId(messagePersistence.getMESSAGEID());
				message_MessageViews.add(message_MessageView);
			}
		}
		List<Message_MessageView> list = ListSort2(message_MessageViews);
		return list;
	}
	//比较时间
	public static List<Message_MessageView> message_getMessageHistory_time(String postuserId,String getuserId,int state,int startnumber,String time){
		List<Message_MessageView> message_MessageViews = new ArrayList<Message_MessageView>();
		List<MessagePersistence> messagePersistences = MessageHelper.getMessageContent2_time(postuserId, getuserId, 2, 0,time);
		if (messagePersistences.size()!=0) {
			for(MessagePersistence messagePersistence:messagePersistences){
				Message_MessageView message_MessageView = new Message_MessageView();
				message_MessageView.setContent(messagePersistence.getMESSAGECONTENT());
				List<UserPersistence> uList = UserHelper.getEmail_id(messagePersistence.getPOSTUSERID());
				message_MessageView.setUserImage(uList.get(0).getAVATAR());
				message_MessageView.setUserId(messagePersistence.getPOSTUSERID());
				message_MessageView.setTime(messagePersistence.getMESSAGETIME());
				message_MessageView.setMessageId(messagePersistence.getMESSAGEID());
				message_MessageViews.add(message_MessageView);
			}
		}
		List<Message_MessageView> list = ListSort2(message_MessageViews);
		return list;
	}
	public static List<Message_MessageView> message_getMessageHistory2(String postuserId,String getuserId,int state,String date){
		List<Message_MessageView> message_MessageViews = new ArrayList<Message_MessageView>();
		List<MessagePersistence> messagePersistences = MessageHelper.getMessageContent21(postuserId, getuserId, 2,date);
		if (messagePersistences.size()!=0) {
			for(MessagePersistence messagePersistence:messagePersistences){
				Message_MessageView message_MessageView = new Message_MessageView();
				message_MessageView.setContent(messagePersistence.getMESSAGECONTENT());
				List<UserPersistence> uList = UserHelper.getEmail_id(messagePersistence.getPOSTUSERID());
				message_MessageView.setUserImage(uList.get(0).getAVATAR());
				message_MessageView.setUserId(messagePersistence.getPOSTUSERID());
				message_MessageView.setTime(messagePersistence.getMESSAGETIME());
				message_MessageView.setMessageId(messagePersistence.getMESSAGEID());
				message_MessageViews.add(message_MessageView);
			}
		}
		List<Message_MessageView> list = ListSort3(message_MessageViews);
		return list;
	}
	//对list里面的元素进行time的排序
	private static List<Message_MessageView> ListSort2(List<Message_MessageView> list){
		Collections.sort(list,new Comparator<Message_MessageView>() {
			@Override
			public int compare(Message_MessageView o1, Message_MessageView o2) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date dt1 = format.parse(o1.getTime());
					Date dt2 = format.parse(o2.getTime());
					if (dt1.getTime()>dt2.getTime()) {
						return 1;
					}else if (dt1.getTime()<dt2.getTime()) {
						return -1;
					}else {
						return 0;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}
		});
		return list;
	}
	private static List<Message_MessageView> ListSort3(List<Message_MessageView> list){
		Collections.sort(list,new Comparator<Message_MessageView>() {
			@Override
			public int compare(Message_MessageView o1, Message_MessageView o2) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date dt1 = format.parse(o1.getTime());
					Date dt2 = format.parse(o2.getTime());
					if (dt1.getTime()<dt2.getTime()) {
						return 1;
					}else if (dt1.getTime()>dt2.getTime()) {
						return -1;
					}else {
						return 0;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}
		});
		return list;
	}
	/*
	 * zyq_message_ajax_删除私信列表
	 */
	public static void deleteMessageList(String postuserId, String getuserId) {
		List<MessagePersistence> messagePersistences = MessageHelper.getMessageContent1(postuserId, getuserId, 2);
		String time = messagePersistences.get(0).getMESSAGETIME();
    	List<MessageHistoryPersistence> messageHistoryPersistences = MessageHelper.getMessageHistoryList(postuserId,getuserId);
    	if (messageHistoryPersistences.size()==0) {
			MessageHistoryPersistence messageHistoryPersistence = new MessageHistoryPersistence();
			messageHistoryPersistence.setMESSAGEHISTORYID(UUID.randomUUID().toString());
			messageHistoryPersistence.setPOSTUSERID(postuserId);
			messageHistoryPersistence.setGETUSERID(getuserId);
			messageHistoryPersistence.setTIMEMARK(time);
			MessageHelper.save(messageHistoryPersistence);
		}else {
			MessageHelper.updateMessageHistory(messageHistoryPersistences.get(0).getMESSAGEHISTORYID(),time);
		}
	}
}
