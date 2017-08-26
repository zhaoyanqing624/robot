package org.xjtusicd3.partner.word2vec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.ansj.domain.Result;
import org.ansj.library.StopLibrary;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.partner.view.robot_Chat;
/**
 * author:zhaoyanqing
 * abstract:word2Vec模型
 * date:2017年8月19日 23:18:50
 */
public class Word2Vec {
	/**
	 * author:zhaoyanqing
	 * abstract:聊天机器人接口，用户根据自然语言提问，利用ansj将分好的词提供给word2vec，随后利用余弦进行相似度计算
	 * date:2017年8月19日 23:18:50
	 * @throws Exception 
	 */
	public static List<robot_Chat> robot_Chats(String comment) throws Exception{
		org.xjtusicd3.partner.vec.util.Word2Vec word2Vec = new org.xjtusicd3.partner.vec.util.Word2Vec();
		try {
			word2Vec.loadGoogleModel("data/wiki_chinese_word2vec(Google).model");
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> nList = Word2Vec.similarScoreFirst_word2vec(comment);
		float[] weightArray = Word2Vec.getPOSWeightArray(nList);
		File file = new File("library/faqKeywords2.txt");//Text文件
		BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
		String s = null;
		List<robot_Chat> robot_Chats = new ArrayList<robot_Chat>();
		while((s = br.readLine())!=null){//使用readLine方法，一次读一行
			robot_Chat robot_Chat = new robot_Chat();
			String[] resultArray = s.split("\t");
			String[] strArray = resultArray[1].split(",");
			List<String> sList = new ArrayList<String>();
			for(int i=0;i<strArray.length;i++){
				sList.add(strArray[i]);
			}
			float[] weightArray2 = Word2Vec.getPOSWeightArray(sList);
			float value = word2Vec.sentenceSimilarity(nList, sList, weightArray, weightArray2);
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
	/**
	 * author:zhaoyanqing
	 * abstract:利用ansj分词
	 * date:2017年8月19日 23:14:41
	 * @throws Exception 
	 */
	public static List<String> similarScoreFirst_word2vec(String text) throws Exception{
		Forest forest = Library.makeForest("library/computer.dic");
		Result terms = ToAnalysis.parse(text,forest);
		Result result = terms.recognition(StopLibrary.get());
		String aString = result.toString();
		String[] resultArray = aString.split(",");
		List<String> list = new ArrayList<String>();
		for(int i=0;i<resultArray.length;i++){
			list.add(resultArray[i]);
		}
		return list;
	}
	/**
	 * author:zhaoyanqing
	 * abstract:将ansj分好的词获取权重数值
	 * date:2017年8月19日 23:16:18
	 * @throws Exception 
	 */
	public static float[] getPOSWeightArray(List<String> posList) {
		float[] weightVector = new float[posList.size()];
		for (int i = 0; i < weightVector.length; i++) {
			String POS = posList.get(i);
			switch(POS.charAt(0)) {
			case 'n':weightVector[i] = (float) 1.0;break;
			case 'v':weightVector[i] = (float) 0.7;break;
			case 'a':weightVector[i] = (float) 0.5;break;
			default:weightVector[i] = (float) 0.8;break;
			}
		}
		return weightVector;
	}

}
