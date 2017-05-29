package org.xjtusicd3.partner.filter;

import java.util.List;

import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.service.NoticeService;
import org.xjtusicd3.partner.view.Notice_NoticeView;

import com.alibaba.fastjson.JSONObject;

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
				List<UserPersistence> userPersistences = UserHelper.getEmail_id(userId);
				JSONObject jsonObject = new JSONObject();
				//判定论坛的的回答
//				List<Notice_NoticeView> notice_NoticeViews = NoticeService.
				List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.notice_CommunityQuestion(userId);
				if (communityQuestionPersistences.size()!=0) {
					for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
						List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.notice_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
						
						jsonObject.put("community", communityAnswerPersistences);
					}
				}
				event.setField("mess", result);
				Dispatcher.getInstance().unicast(event, sessions[i].getId()); 
			}
		}
	}
}
