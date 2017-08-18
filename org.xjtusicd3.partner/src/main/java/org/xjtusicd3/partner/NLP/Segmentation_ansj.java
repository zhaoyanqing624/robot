package org.xjtusicd3.partner.NLP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

import org.ansj.domain.Result;
import org.ansj.library.StopLibrary;
import org.ansj.recognition.impl.NatureRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.lucene.analysis.Analyzer;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.NLP_Word;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.partner.view.robot_Chat;
import org.xjtusicd3.partner.view.robot_ChatView;
public class Segmentation_ansj {
	public static List<NLP_Word> similarScoreFirst(String text) throws Exception{
		Forest forest = Library.makeForest("library/computer.dic");
		Result terms = ToAnalysis.parse(text,forest);
//		Result terms = ToAnalysis.parse(text);
//		System.out.println(terms);
//		System.out.println(StopLibrary.get());
//		System.out.println(StopLibrary.get("stop"));
		Result result = terms.recognition(StopLibrary.get());
		String aString = result.toStringWithOutNature();
//		System.out.println("result..................."+result);
//		System.out.println("aString................"+aString);
		String[] resultArray = aString.split(",");
		List<NLP_Word> list = new ArrayList<NLP_Word>();
		for(int i=0;i<resultArray.length;i++){
			NLP_Word nlp_Word = new NLP_Word();
			nlp_Word.setText(resultArray[i]);
			nlp_Word.setFrequency(0);
			list.add(nlp_Word);
		}
		System.out.println(JsonUtil.toJsonString(list));
		return list;
	}
	public static List<robot_ChatView> similarScoreSecond1() throws Exception{
		Forest forest = Library.makeForest("library/computer.dic");
		List<QuestionPersistence> questionPersistences = QuestionHelper.getFaqTotal();
		List<robot_ChatView> robot_ChatViews = new ArrayList<robot_ChatView>();
		for(QuestionPersistence questionPersistence:questionPersistences){
			Result terms = ToAnalysis.parse(questionPersistence.getFAQTITLE(),forest);
			String result = terms.recognition(StopLibrary.get()).toStringWithOutNature(",");
			String[] resultArray = result.split(",");
			//把得到的语句分词
			List<NLP_Word> list1 = new ArrayList<NLP_Word>();
			for(int i=0;i<resultArray.length;i++){
				NLP_Word nlp_Word = new NLP_Word();
				nlp_Word.setText(resultArray[i]);
				nlp_Word.setFrequency(0);
				list1.add(nlp_Word);
			}
			robot_ChatView robot_ChatView = new robot_ChatView();
			robot_ChatView.setNlp_Words(list1);
			
			robot_ChatView.setQuestion(questionPersistence.getFAQTITLE());
			robot_ChatView.setQuestionId(questionPersistence.getFAQQUESTIONID());
			List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(questionPersistence.getFAQQUESTIONID());
			robot_ChatView.setAnswer(answerPersistences.get(0).getFAQCONTENT());
			robot_ChatViews.add(robot_ChatView);
		}
		return robot_ChatViews;
	}
	public static double similarScoreSecond(List<NLP_Word> list,String text) throws Exception{
//		Forest forest = Library.makeForest("library/computer.dic");
//		GetWord string = forest.getWord("安全");
//		String[] string2 = forest.getBranch("安全").getParam();
//		System.out.println(string2[0]);
//		System.out.println(string2[1]);
//		System.out.println("1111112"+forest.getBranches());
		// 增加新词,中间按照'\t'隔开
//		Result terms = ToAnalysis.parse(text,forest);
//		new NatureRecognition().recognition(terms);
//		String result = terms.recognition(StopLibrary.get()).toStringWithOutNature(",");
		String[] resultArray = text.split(",");
//		Result terms2 = ToAnalysis.parse(text2,forest);
//		new NatureRecognition().recognition(terms2);
//		String result2 = terms2.recognition(StopLibrary.get()).toStringWithOutNature(",");
//		String[] resultArray2 = result2.split(",");
		//把得到的语句分词
		List<NLP_Word> list1 = new ArrayList<NLP_Word>();
		for(int i=0;i<resultArray.length;i++){
			NLP_Word nlp_Word = new NLP_Word();
			nlp_Word.setText(resultArray[i]);
			nlp_Word.setFrequency(0);
			list1.add(nlp_Word);
		}
//		List<NLP_Word> list2 = new ArrayList<NLP_Word>();
//		for(int i=0;i<resultArray2.length;i++){
//			NLP_Word nlp_Word = new NLP_Word();
//			nlp_Word.setText(resultArray2[i]);
//			nlp_Word.setFrequency(0);
//			list2.add(nlp_Word);
//		}
		TextSimilarity textSimilarity = new TextSimilarity();
		double score =  textSimilarity.similarScore(list, list1);
		return score;
	}
	/*
	 * 提取faq的问题开始分词加入文件
	 */
	public static void segmentation_txt() throws Exception{
		Forest forest = Library.makeForest("library/computer.dic");
		List<QuestionPersistence> questionPersistences = QuestionHelper.getFaqTotal();
		for(QuestionPersistence questionPersistence:questionPersistences){
			Result terms = ToAnalysis.parse(questionPersistence.getFAQTITLE(),forest);
			String result = terms.recognition(StopLibrary.get()).toStringWithOutNature();
			BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:/faqKeywords.txt",true),"UTF-8"));
			String string = questionPersistence.getFAQQUESTIONID() + "\t"+result+"\r\n";
			fileWriter.write(string);
			fileWriter.flush();
			fileWriter.close(); 
		}
	}
	/*
	 * 与txt提取的分好的词进行余弦
	 */
	public static List<robot_Chat> robot_Chats(String comment) throws Exception{
		Segmentation_ansj segmentation_ansj = new Segmentation_ansj();
		List<NLP_Word> nList = segmentation_ansj.similarScoreFirst(comment);
		File file = new File("E:/faqKeywords.txt");//Text文件
		BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
		String s = null;
		List<robot_Chat> robot_Chats = new ArrayList<robot_Chat>();
		while((s = br.readLine())!=null){//使用readLine方法，一次读一行
			robot_Chat robot_Chat = new robot_Chat();
			String[] resultArray = s.split("\t");
			double value = segmentation_ansj.similarScoreSecond(nList, resultArray[1]);
			if (value>=0.4) {
				robot_Chat.setQuestionId(resultArray[0]);
				robot_Chat.setValue(value);
				robot_Chats.add(robot_Chat);
			}
		}
		br.close();
		System.out.println(JsonUtil.toJsonString(robot_Chats));
		return robot_Chats;
	}
	
	public static void main(String[] args) throws Exception {
//		segmentation_txt();
//		similarScoreFirst("我的联想电脑无辜蓝屏了");
		robot_Chats("电脑蓝屏了怎么办");
	}
	
}
