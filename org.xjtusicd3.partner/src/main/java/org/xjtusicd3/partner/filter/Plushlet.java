package org.xjtusicd3.partner.filter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.partner.service.NoticeService;
import org.xjtusicd3.partner.view.Notice_NoticeCommunityView;

import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;
import nl.justobjects.pushlet.core.Session;
import nl.justobjects.pushlet.core.SessionManager;

public class Plushlet{
	static public class MessClazz extends EventPullSource{
		
		@Override  
		protected long getSleepTime(){
			return 3000;
		}
		
		@Override
		protected void pullEvent(){
			Session[] sessions = SessionManager.getInstance().getSessions();
			for(int i = 0;i<sessions.length;i++){
				String userId = sessions[i].getEvent().getField("uid");
				Event event = Event.createDataEvent("/mipc/he");
				//判定论坛的的评论
				List<Notice_NoticeCommunityView> Notice_NoticeCommunityView = NoticeService.notice_NoticeViews(userId);
				String result2 = JsonUtil.toJsonString(Notice_NoticeCommunityView);
				try {
					result2=java.net.URLEncoder.encode(result2, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					event.setField("mess", "异常错误！");
				}
				event.setField("mess", result2);
				Dispatcher.getInstance().unicast(event, sessions[i].getId()); 
			}
		}
	}
}
