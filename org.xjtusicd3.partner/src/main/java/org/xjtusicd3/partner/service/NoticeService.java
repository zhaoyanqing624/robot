package org.xjtusicd3.partner.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.partner.view.Notice_NoticeCommunityView;
import org.xjtusicd3.partner.view.Notice_NoticeView;


public class NoticeService {
	/*
	 * 推送内容
	 */
	public static List<Notice_NoticeView> notice_NoticeViews(String userid){
		List<Notice_NoticeView> notice_NoticeViews = new ArrayList<Notice_NoticeView>();
		//查看论坛
		List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.notice_CommunityQuestion(userid);
		if (communityQuestionPersistences.size()==0) {
			Notice_NoticeCommunityView notice_NoticeCommunityView = new Notice_NoticeCommunityView();
			notice_NoticeCommunityView.setValue("0");
		}else {
			List<Notice_NoticeCommunityView> notice_NoticeCommunityViews = new ArrayList<Notice_NoticeCommunityView>();
			for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
				Notice_NoticeCommunityView notice_NoticeCommunityView = new Notice_NoticeCommunityView();
				notice_NoticeCommunityView.setCommunityQuestionId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
				notice_NoticeCommunityView.setCommunityQuestion(communityQuestionPersistence.getTITLE());
				notice_NoticeCommunityViews.add(notice_NoticeCommunityView);
			}
			Notice_NoticeView notice_NoticeView = new Notice_NoticeView();
			notice_NoticeView.setCommunityViews(notice_NoticeCommunityViews);
			notice_NoticeViews.add(notice_NoticeView);
		}
		String rString = JsonUtil.toJsonString(notice_NoticeViews);
		System.out.println(rString);
		return notice_NoticeViews;
	}
	public static void main(String[] args) {
		notice_NoticeViews("fa2f2884-985d-44e0-89b8-0454d0feaeac");
	}
}
