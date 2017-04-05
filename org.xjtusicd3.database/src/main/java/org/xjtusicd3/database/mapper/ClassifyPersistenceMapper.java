package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.FaqPersistence;

public interface ClassifyPersistenceMapper  extends IBaseDao<ClassifyPersistence, String>{
	/*
	 * robot-分类
	 */
	@Select("SELECT classifyId,classifyName FROM classify WHERE parentId='0'")
	public List<ClassifyPersistence> FirstClassify_robot();
	@Select("SELECT classifyId,classifyName,sum(faqCollection) as a FROM faq,classify  WHERE classifyId=faqClassify AND parentId=#{0} GROUP BY faqClassify ORDER BY a DESC LIMIT 4")
	public List<ClassifyPersistence> SecondClassify_robot(int paramString);
	/*
	 * faq、faq1_上侧的第二级分类
	 */
	@Select("SELECT classifyId,classifyName FROM classify  WHERE parentId=#{0}")
	public List<ClassifyPersistence> SecondClassify_robot2(int paramString);
	/*
	 * faq1_下面4栏推荐_按照浏览量
	 */
	@Select("SELECT classifyId,classifyName,sum(faqScan) as a  FROM faq,classify WHERE faq.faqClassify = classify.classifyId AND classify.parentId=#{0} GROUP BY faqClassify ORDER BY a DESC LIMIT 4")
	public List<ClassifyPersistence> SecondClassify_faq1(int paramString);
	@Select("SELECT * FROM faq WHERE faqClassify=#{0} ORDER BY faqScan DESC LIMIT 1")
	public List<FaqPersistence> faqPersistences_faq1(int paramString);
	@Select("SELECT * FROM faq WHERE faqClassify=#{0} ORDER BY faqScan DESC LIMIT 1,5")
	public List<FaqPersistence> faqPersistences2_faq1(int paramString);
}
