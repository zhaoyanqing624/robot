package org.xjtusicd3.partner.filter;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.partner.service.MessageService;
import org.xjtusicd3.partner.service.NoticeService;
import org.xjtusicd3.partner.view.Message_MessageView;
import org.xjtusicd3.partner.view.Notice_NoticeCommunityView;

import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;
import nl.justobjects.pushlet.core.Session;
import nl.justobjects.pushlet.core.SessionManager;

public class Plushlet{
	//消息通知
	static public class NoticeClazz extends EventPullSource{
		@Override  
		protected long getSleepTime(){
			return 3000;
		}
		@Override
		protected void pullEvent(){
			Session[] sessions = SessionManager.getInstance().getSessions();
			for(int i = 0;i<sessions.length;i++){
				String userId = sessions[i].getEvent().getField("uid");
				List<Notice_NoticeCommunityView> Notice_NoticeCommunityView = NoticeService.notice_NoticeViews(userId,1);
				if (Notice_NoticeCommunityView.size()!=0) {
					Event event = Event.createDataEvent("/mipc/he");
					//判定论坛的的评论
					String result2 = JsonUtil.toJsonString(Notice_NoticeCommunityView);
					try {
						result2=java.net.URLEncoder.encode(result2, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						event.setField("notice", "异常错误！");
					}
					event.setField("notice", result2);
					Dispatcher.getInstance().unicast(event, sessions[i].getId()); 
				}
			}
		}
	}
	//私信
	static public class MessageClazz extends EventPullSource{

		@Override
		protected long getSleepTime() {
			return 2000;
		}
		@Override
		protected void pullEvent() {
			Session[] sessions = SessionManager.getInstance().getSessions();
			for(int i = 0;i<sessions.length;i++){
				String userId = sessions[i].getEvent().getField("uid");
				List<Message_MessageView> message_MessageViews = MessageService.message_getMessage_pushlet(userId);
				if (message_MessageViews.size()!=0) {
					Event event = Event.createDataEvent("/mipc/she");
					String result = JsonUtil.toJsonString(message_MessageViews);
					String messageNumber=Integer.toString(message_MessageViews.size());
					try {
						result=java.net.URLEncoder.encode(result, "UTF-8");
						messageNumber=java.net.URLEncoder.encode(messageNumber, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						event.setField("message", "异常错误！");
					}
					event.setField("message", result);
					event.setField("messageNumber", messageNumber);
					Dispatcher.getInstance().unicast(event, sessions[i].getId()); 
				}

			}
		}
		
		
	}
}
