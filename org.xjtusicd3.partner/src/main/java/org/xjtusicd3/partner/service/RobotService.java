package org.xjtusicd3.partner.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.RobotHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.RobotPersistence;
import org.xjtusicd3.partner.NLP.Segmentation_ansj;
import org.xjtusicd3.partner.view.robot_Chat;

public class RobotService {
	/*
	 * robot_ajax获取机器人信息
	 */
	public static List<RobotPersistence> robotinfo(){
		List<RobotPersistence> list = RobotHelper.robotinfo();
		return list;
	}
	public static void main(String[] args) throws Exception {
		getRobotAnswer("西安交通大学");
	}
	/*
	 * robot_ajax_和机器人聊天
	 */
	public static List<robot_Chat> getRobotAnswer(String comment) throws Exception {
		Segmentation_ansj segmentation_ansj = new Segmentation_ansj();
		List<robot_Chat> robot_Chats = segmentation_ansj.robot_Chats(comment);
		List<robot_Chat> list = new ArrayList<robot_Chat>();
		if (robot_Chats.size()==0) {
			
		}else if (robot_Chats.size()==1) {
			robot_Chat robot_Chat = new robot_Chat();
			robot_Chat.setQuestionId(robot_Chats.get(0).getQuestionId());
			List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(robot_Chats.get(0).getQuestionId());
			robot_Chat.setAnswer(answerPersistences.get(0).getFAQCONTENT());
			list.add(robot_Chat);
		}else if (robot_Chats.size()>6) {
			robot_Chats = ListSort(robot_Chats);
			for(int i=0;i<=6;i++){
				if (i==0) {
					robot_Chat robot_Chat = new robot_Chat();
					robot_Chat.setQuestionId(robot_Chats.get(0).getQuestionId());
					List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(robot_Chats.get(0).getQuestionId());
					robot_Chat.setAnswer(answerPersistences.get(0).getFAQCONTENT());
					list.add(robot_Chat);
				}else {
					robot_Chat robot_Chat = new robot_Chat();
					robot_Chat.setQuestionId(robot_Chats.get(i).getQuestionId());
					List<QuestionPersistence> questionPersistences = QuestionHelper.faq3_faqcontent(robot_Chats.get(i).getQuestionId());
					robot_Chat.setQuestion(questionPersistences.get(0).getFAQTITLE());
					list.add(robot_Chat);
				}
			}
		}else {
			robot_Chats = ListSort(robot_Chats);
			for (int i = 0; i <robot_Chats.size() ; i++) {
				if (i==0) {
					robot_Chat robot_Chat = new robot_Chat();
					robot_Chat.setQuestionId(robot_Chats.get(0).getQuestionId());
					List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(robot_Chats.get(0).getQuestionId());
					robot_Chat.setAnswer(answerPersistences.get(0).getFAQCONTENT());
					list.add(robot_Chat);
				}else {
					robot_Chat robot_Chat = new robot_Chat();
					robot_Chat.setQuestionId(robot_Chats.get(i).getQuestionId());
					List<QuestionPersistence> questionPersistences = QuestionHelper.faq3_faqcontent(robot_Chats.get(i).getQuestionId());
					robot_Chat.setQuestion(questionPersistences.get(0).getFAQTITLE());
					list.add(robot_Chat);
				}
			}
		}
		return list;
	}
	//对按余弦相似度进行排序
	private static List<robot_Chat> ListSort(List<robot_Chat> list){
		Collections.sort(list,new Comparator<robot_Chat>() {
			@Override
			public int compare(robot_Chat o1, robot_Chat o2) {
				double number1 = o1.getValue();
				double number2 = o2.getValue();
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
}
