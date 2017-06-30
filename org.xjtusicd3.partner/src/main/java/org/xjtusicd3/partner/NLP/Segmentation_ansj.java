package org.xjtusicd3.partner.NLP;

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Result;
import org.ansj.library.StopLibrary;
import org.ansj.recognition.impl.NatureRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;
import org.xjtusicd3.database.model.NLP_Word;
public class Segmentation_ansj {
	public static double similarScore(String text,String text2) throws Exception{
		Forest forest = Library.makeForest("library/computer.dic");
//		GetWord string = forest.getWord("安全");
//		String[] string2 = forest.getBranch("安全").getParam();
//		System.out.println(string2[0]);
//		System.out.println(string2[1]);
//		System.out.println("1111112"+forest.getBranches());
		// 增加新词,中间按照'\t'隔开
		Result terms = ToAnalysis.parse(text,forest);
		new NatureRecognition().recognition(terms);
		String result = terms.recognition(StopLibrary.get()).toStringWithOutNature(",");
		String[] resultArray = result.split(",");
		Result terms2 = ToAnalysis.parse(text2,forest);
		new NatureRecognition().recognition(terms2);
		String result2 = terms2.recognition(StopLibrary.get()).toStringWithOutNature(",");
		String[] resultArray2 = result2.split(",");
		//把得到的语句分词
		List<NLP_Word> list = new ArrayList<NLP_Word>();
		for(int i=0;i<resultArray.length;i++){
			NLP_Word nlp_Word = new NLP_Word();
			nlp_Word.setText(resultArray[i]);
			nlp_Word.setFrequency(0);
			list.add(nlp_Word);
		}
		//把数据库的语句分词
		List<NLP_Word> list2 = new ArrayList<NLP_Word>();
		for(int i=0;i<resultArray2.length;i++){
			NLP_Word nlp_Word = new NLP_Word();
			nlp_Word.setText(resultArray2[i]);
			nlp_Word.setFrequency(0);
			list2.add(nlp_Word);
		}
		TextSimilarity textSimilarity = new TextSimilarity();
		double score =  textSimilarity.similarScore(list, list2);
		return score;
	}
	public static void main(String[] args) throws Exception {
		System.out.println(similarScore("联想电脑的硬盘如何处理成数据","联想电脑的硬盘如何处理成数据"));
	}
}
