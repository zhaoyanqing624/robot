package org.xjtusicd3.partner.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.model.CommentPersistence;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.partner.view.Notice_NoticeCommunityView;
import org.xjtusicd3.partner.view.Notice_NoticeView;

import net.sf.json.util.JSONUtils;


public class NoticeService {
	/*
	 * 推送内容
	 */
	public static List<Notice_NoticeCommunityView> notice_NoticeViews(String userid){
		List<Notice_NoticeCommunityView> notice_NoticeCommunityViews = new ArrayList<Notice_NoticeCommunityView>();
		List<Notice_NoticeCommunityView> notice_NoticeCommunityViews2 = new ArrayList<Notice_NoticeCommunityView>();
		List<Notice_NoticeCommunityView> notice_NoticeCommunityViews3 = new ArrayList<Notice_NoticeCommunityView>();
		//查看论坛_评论
		List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.notice_CommunityQuestion(userid);
		Notice_NoticeCommunityView notice_NoticeCommunityView = new Notice_NoticeCommunityView();
		if (communityQuestionPersistences.size()!=0) {
			for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
				List<CommunityAnswerPersistence> answerPersistences = CommunityAnswerHelper.notice_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
				if (answerPersistences.size()!=0) {
					notice_NoticeCommunityView.setQuestionId(answerPersistences.get(0).getCOMMUNITYQUESTIONID());
					notice_NoticeCommunityView.setNoticeId(answerPersistences.get(0).getCOMMUNITYANSWERID());
					notice_NoticeCommunityView.setNotice(communityQuestionPersistence.getTITLE());
					notice_NoticeCommunityView.setValue("有了新的评论");
					notice_NoticeCommunityView.setTime(answerPersistences.get(0).getTIME());
					notice_NoticeCommunityViews.add(notice_NoticeCommunityView);
				}
			}
		}
		//查看论坛
		
		List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer_userId(userid);
		if (communityAnswerPersistences.size()!=0) {
			Notice_NoticeCommunityView notice_NoticeCommunityView2 = new Notice_NoticeCommunityView();
			Notice_NoticeCommunityView notice_NoticeCommunityView3 = new Notice_NoticeCommunityView();
			for(CommunityAnswerPersistence communityAnswerPersistence:communityAnswerPersistences){
				//查看论坛_回复
				List<CommentPersistence> commentPersistences = CommentHelper.notice_getComment(communityAnswerPersistence.getCOMMUNITYQUESTIONID(),communityAnswerPersistence.getCOMMUNITYANSWERID(),1);
				if (commentPersistences.size()!=0) {
					notice_NoticeCommunityView2.setQuestionId(commentPersistences.get(0).getCOMMUNITYQUESTIONID());
					notice_NoticeCommunityView2.setNoticeId(commentPersistences.get(0).getCOMMENTPARENTID());
					notice_NoticeCommunityView2.setNotice(commentPersistences.get(0).getCOMMENTCONTENT());
					notice_NoticeCommunityView2.setValue("有了新的回复");
					notice_NoticeCommunityView2.setTime(commentPersistences.get(0).getCOMMENTTIME());
					notice_NoticeCommunityViews2.add(notice_NoticeCommunityView2);
				}
				//查看论坛_回复的回复
				List<CommentPersistence> commentPersistences2 = CommentHelper.notice_getReply(communityAnswerPersistence.getCOMMUNITYQUESTIONID(), communityAnswerPersistence.getCOMMUNITYANSWERID(),1);
				if (commentPersistences2.size()!=0) {
					notice_NoticeCommunityView3.setQuestionId(commentPersistences2.get(0).getCOMMUNITYQUESTIONID());
					notice_NoticeCommunityView3.setNoticeId(commentPersistences2.get(0).getCOMMENTPARENTID());
					notice_NoticeCommunityView3.setNotice(commentPersistences2.get(0).getCOMMENTCONTENT());
					notice_NoticeCommunityView3.setParentId(commentPersistences2.get(0).getTOUSERID());
					notice_NoticeCommunityView3.setValue("有了新的回复@");
					notice_NoticeCommunityView3.setTime(commentPersistences2.get(0).getCOMMENTTIME());
					notice_NoticeCommunityViews3.add(notice_NoticeCommunityView3);
				}
			}
		}
		notice_NoticeCommunityViews.addAll(notice_NoticeCommunityViews2);
		notice_NoticeCommunityViews.addAll(notice_NoticeCommunityViews3);
//		String rString = JsonUtil.toJsonString(notice_NoticeCommunityViews);
//		System.out.println(rString);
		List<Notice_NoticeCommunityView> list = ListSort(notice_NoticeCommunityViews);
		return list;
	}
	//对list里面的元素进行时间排序
	private static List<Notice_NoticeCommunityView> ListSort(List<Notice_NoticeCommunityView> list){
		Collections.sort(list,new Comparator<Notice_NoticeCommunityView>() {
			@Override
			public int compare(Notice_NoticeCommunityView o1, Notice_NoticeCommunityView o2) {
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
	public static void main(String[] args) {
		notice_NoticeViews("2555a73e-429d-4f64-9074-5e69738e7669");
	}
}
