package org.xjtusicd3.partner.filter;

import java.util.List;

import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;

import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;
import nl.justobjects.pushlet.core.Session;
import nl.justobjects.pushlet.core.SessionManager;

public class HelloWorldPlushlet{
	static public class MessClazz extends EventPullSource{
		
		@Override  
		protected long getSleepTime(){
			return 3000;
		}
		
		@Override
		protected void pullEvent(){
			String result = "";
			Session[] sessions = SessionManager.getInstance().getSessions();
			for(int i = 0;i<sessions.length;i++){
				String userId = sessions[i].getEvent().getField("uid");
				Event event = Event.createDataEvent("/mipc/he");
				System.out.println("111111111"+sessions[i].getEvent().getField("UserEmail"));
				System.out.println(userId);
				List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.notice_ByUserId(userId, 1);
				if (communityAnswerPersistences==null) {
					result="0";
				}else{
					result="1";
				}
				event.setField("mess", result);
				Dispatcher.getInstance().unicast(event, sessions[i].getId()); 
			}
		}
	}
}
