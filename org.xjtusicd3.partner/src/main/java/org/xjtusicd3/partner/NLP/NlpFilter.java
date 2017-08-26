package org.xjtusicd3.partner.NLP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.ansj.domain.Result;
import org.ansj.library.StopLibrary;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.QuestionPersistence;
/**
 * author:zhaoyanqing
 * abstract:存放一些自然语言需要处理的工具类
 * date:2017年8月19日 10:44:46
 */
public class NlpFilter {
	public static void main(String[] args) throws Exception {
		segmentation_txt2();
	}
	/*
	 * 提取faq的问题开始分词(未加入词性)加入文件faqkeywords
	 */
	public static void segmentation_txt() throws Exception{
		Forest forest = Library.makeForest("library/computer.dic");
		List<QuestionPersistence> questionPersistences = QuestionHelper.getFaqTotal();
		File file = new File("/library/faqKeywords.txt");
		if (file.exists()) {
			file.delete();
			for(QuestionPersistence questionPersistence:questionPersistences){
				Result terms = ToAnalysis.parse(questionPersistence.getFAQTITLE(),forest);
				String result = terms.recognition(StopLibrary.get()).toStringWithOutNature();
				BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/library/faqKeywords.txt",true),"UTF-8"));
				String string = questionPersistence.getFAQQUESTIONID() + "\t"+result+"\r\n";
				fileWriter.write(string);
				fileWriter.flush();
				fileWriter.close(); 
			}
		}
	}
	/*
	 * 提取faq的问题开始分词(加入词性)加入文件faqkeywords2
	 */
	public static void segmentation_txt2() throws Exception{
		Forest forest = Library.makeForest("library/computer.dic");
		List<QuestionPersistence> questionPersistences = QuestionHelper.getFaqTotal();
		File file = new File("library/faqKeywords2.txt");
		if (file.exists()) {
			file.delete();
			for(QuestionPersistence questionPersistence:questionPersistences){
				Result terms = ToAnalysis.parse(questionPersistence.getFAQTITLE(),forest);
				String result = terms.recognition(StopLibrary.get()).toString();
				BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("library/faqKeywords2.txt",true),"UTF-8"));
				String string = questionPersistence.getFAQQUESTIONID() + "\t"+result+"\r\n";
				fileWriter.write(string);
				fileWriter.flush();
				fileWriter.close(); 
			}
		}
	}
}
