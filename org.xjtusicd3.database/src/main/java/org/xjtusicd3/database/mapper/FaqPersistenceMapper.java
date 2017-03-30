package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.FaqPersistence;

public interface FaqPersistenceMapper extends IBaseDao<FaqPersistence, String>{
	/*
	 * robot-分类
	 */
	@Select("SELECT faq.faqId,faq.faqTitle FROM faq,classify WHERE faq.faqClassify = classify.classifyId AND faq.faqClassify=#{0} ORDER BY faq.faqCollection DESC LIMIT 4")
	public List<FaqPersistence> SecondClassify_robot(int paramString);
}
