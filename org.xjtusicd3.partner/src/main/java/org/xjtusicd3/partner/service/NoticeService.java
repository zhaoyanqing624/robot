package org.xjtusicd3.partner.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.CommentPersistence;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.partner.view.Notice_NoticeCommunityView;



public class NoticeService {
	/*
	 * 推送内容
	 */
	public static List<Notice_NoticeCommunityView> notice_NoticeViews(String userid){
		List<Notice_NoticeCommunityView> notice_NoticeCommunityViews = new ArrayList<Notice_NoticeCommunityView>();
		//产看FAQ_评论
		List<AnswerPersistence> answerPersistences2 = AnswerHelper.notice_faqanswerList(userid);
		if (answerPersistences2.size()!=0) {
			for(AnswerPersistence answerPersistence:answerPersistences2){
				List<CommentPersistence> commentPersistences = CommentHelper.notice_getFaqComment(answerPersistence.getFAQQUESTIONID(), "0", 1);
				if (commentPersistences.size()!=0) {
					Notice_NoticeCommunityView notice_NoticeCommunityView = new Notice_NoticeCommunityView();
					List<QuestionPersistence> questionPersistences = QuestionHelper.faq3_faqcontent(answerPersistence.getFAQQUESTIONID());
					notice_NoticeCommunityView.setName(questionPersistences.get(0).getFAQTITLE());
					notice_NoticeCommunityView.setNotice(commentPersistences.get(0).getCOMMENTCONTENT());
					notice_NoticeCommunityView.setNoticeId(commentPersistences.get(0).getCOMMENTID());
					notice_NoticeCommunityView.setQuestionId(answerPersistence.getFAQQUESTIONID());
					notice_NoticeCommunityView.setTime(commentPersistences.get(0).getCOMMENTTIME());
					notice_NoticeCommunityView.setValue("知识库_有了新的评论");
					notice_NoticeCommunityViews.add(notice_NoticeCommunityView);
				}
				
				
			}
		}
		//查看FAQ_回复以及回复的回复
		List<CommentPersistence> faqCommentList = CommentHelper.notice_getFaqComment2(userid, "0");
		if (faqCommentList.size()!=0) {
			for(CommentPersistence commentPersistence:faqCommentList){
				List<CommentPersistence> reply = CommentHelper.notice_getFaqReply(commentPersistence.getCOMMENTID(),1);
				if (reply.size()!=0) {
					Notice_NoticeCommunityView notice_NoticeCommunityView = new Notice_NoticeCommunityView();
					notice_NoticeCommunityView.setName(commentPersistence.getCOMMENTCONTENT());
					notice_NoticeCommunityView.setNotice(reply.get(0).getCOMMENTCONTENT());
					notice_NoticeCommunityView.setNoticeId(reply.get(0).getCOMMENTID());
					notice_NoticeCommunityView.setQuestionId(commentPersistence.getFAQQUESTIONID());
					notice_NoticeCommunityView.setTime(reply.get(0).getCOMMENTTIME());
					notice_NoticeCommunityView.setValue("知识库_有了新的回复");
					notice_NoticeCommunityViews.add(notice_NoticeCommunityView);
				}
				
				List<CommentPersistence> reply2 = CommentHelper.notice_getFaqReply2(commentPersistence.getCOMMENTID(), 1);
				if (reply2.size()!=0) {
					Notice_NoticeCommunityView notice_NoticeCommunityView = new Notice_NoticeCommunityView();
					notice_NoticeCommunityView.setName(commentPersistence.getCOMMENTCONTENT());
					notice_NoticeCommunityView.setNotice(reply2.get(0).getCOMMENTCONTENT());
					notice_NoticeCommunityView.setNoticeId(reply2.get(0).getCOMMENTID());
					notice_NoticeCommunityView.setParentId(reply2.get(0).getCOMMENTPARENTID());
					notice_NoticeCommunityView.setQuestionId(commentPersistence.getFAQQUESTIONID());
					notice_NoticeCommunityView.setTime(reply2.get(0).getCOMMENTTIME());
					notice_NoticeCommunityView.setValue("知识库_有了新的回复@");
					notice_NoticeCommunityViews.add(notice_NoticeCommunityView);
				}
			}
		}
		//查看问吧_评论
		List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.notice_CommunityQuestion(userid);
		if (communityQuestionPersistences.size()!=0) {
			for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
				List<CommunityAnswerPersistence> answerPersistences = CommunityAnswerHelper.notice_CommunityAnswer(communityQuestionPersistence.getCOMMUNITYQUESTIONID(), 1);
				if (answerPersistences.size()!=0) {
					Notice_NoticeCommunityView notice_NoticeCommunityView = new Notice_NoticeCommunityView();
					notice_NoticeCommunityView.setName(communityQuestionPersistence.getTITLE());
					notice_NoticeCommunityView.setQuestionId(answerPersistences.get(0).getCOMMUNITYQUESTIONID());
					notice_NoticeCommunityView.setNoticeId(answerPersistences.get(0).getCOMMUNITYANSWERID());
					notice_NoticeCommunityView.setNotice(answerPersistences.get(0).getCONTENT());
					notice_NoticeCommunityView.setValue("问吧_有了新的评论");
					notice_NoticeCommunityView.setTime(answerPersistences.get(0).getTIME());
					notice_NoticeCommunityViews.add(notice_NoticeCommunityView);
				}
			}
		}
		//查看问吧
		List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer_userId(userid);
		if (communityAnswerPersistences.size()!=0) {
			for(CommunityAnswerPersistence communityAnswerPersistence:communityAnswerPersistences){
				//查看问吧_回复
				List<CommentPersistence> commentPersistences = CommentHelper.notice_getComment(communityAnswerPersistence.getCOMMUNITYQUESTIONID(),communityAnswerPersistence.getCOMMUNITYANSWERID(),1);
				if (commentPersistences.size()!=0) {
					Notice_NoticeCommunityView notice_NoticeCommunityView = new Notice_NoticeCommunityView();
					notice_NoticeCommunityView.setName(communityAnswerPersistence.getCONTENT());
					notice_NoticeCommunityView.setQuestionId(commentPersistences.get(0).getCOMMUNITYQUESTIONID());
					notice_NoticeCommunityView.setNoticeId(commentPersistences.get(0).getCOMMENTID());
					notice_NoticeCommunityView.setParentId(commentPersistences.get(0).getCOMMENTPARENTID());
					notice_NoticeCommunityView.setNotice(commentPersistences.get(0).getCOMMENTCONTENT());
					notice_NoticeCommunityView.setValue("问吧_有了新的回复");
					notice_NoticeCommunityView.setTime(commentPersistences.get(0).getCOMMENTTIME());
					notice_NoticeCommunityViews.add(notice_NoticeCommunityView);
				}
				
				//查看问吧_回复的回复
				List<CommentPersistence> commentPersistences2 = CommentHelper.notice_getReply(communityAnswerPersistence.getCOMMUNITYQUESTIONID(), communityAnswerPersistence.getCOMMUNITYANSWERID(),1);
				if (commentPersistences2.size()!=0) {
					Notice_NoticeCommunityView notice_NoticeCommunityView = new Notice_NoticeCommunityView();
					notice_NoticeCommunityView.setName(communityAnswerPersistence.getCONTENT());
					notice_NoticeCommunityView.setQuestionId(commentPersistences2.get(0).getCOMMUNITYQUESTIONID());
					notice_NoticeCommunityView.setNoticeId(commentPersistences2.get(0).getCOMMENTID());
					notice_NoticeCommunityView.setNotice(commentPersistences2.get(0).getCOMMENTCONTENT());
					notice_NoticeCommunityView.setParentId(commentPersistences2.get(0).getCOMMENTPARENTID());
					notice_NoticeCommunityView.setValue("问吧_有了新的回复@");
					notice_NoticeCommunityView.setTime(commentPersistences2.get(0).getCOMMENTTIME());
					notice_NoticeCommunityViews.add(notice_NoticeCommunityView);
				}
			}

		}
		String rString = JsonUtil.toJsonString(notice_NoticeCommunityViews);
		System.out.println(rString);
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
