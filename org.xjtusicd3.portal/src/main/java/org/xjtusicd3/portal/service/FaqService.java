package org.xjtusicd3.portal.service;

import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FaqService {
	public static String getFaq(){
		String result = "";
		List<QuestionPersistence> list = QuestionHelper.getFaq();
		for(int i = 0;i<list.size();i++){
			List<ClassifyPersistence> cList = ClassifyHelper.faq2_classify(list.get(i).getFAQCLASSIFYID());
			 result += "{\"id\":\""+(i+1)+"\",\"faqTitle\":\""+list.get(i).getFAQTITLE()+"\",\"classifyName\":\""+cList.get(0).getFAQCLASSIFYNAME()+"\"}";
			if (i<list.size()-1) {
				result += ",";
			}else {
				result += "";
			}
		}
		return result;
	}
	
	public static String tsettset(){
		String result="";
		List<QuestionPersistence> list = QuestionHelper.getFaq();
		for(int i = 0;i<list.size();i++){
			List<ClassifyPersistence> cList = ClassifyHelper.faq2_classify(list.get(i).getFAQCLASSIFYID());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", i+1);
			jsonObject.put("faqTitle", list.get(i).getFAQTITLE());
			jsonObject.put("classifyName", cList.get(0).getFAQCLASSIFYNAME());
			result += JsonUtil.toJsonString(jsonObject);
		}
		System.out.println(result);
		return result;
	}
	public static void main(String[] args) {
		tsettset();
	}

}
