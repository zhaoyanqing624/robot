package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ClassifyPersistence;

public interface ClassifyPersistenceMapper  extends IBaseDao<ClassifyPersistence, String>{
	/*
	 * spider_按照分类名称查找
	 */
	@Select("SELECT * FROM Classify WHERE ClassifyName=#{0}")
	public List<ClassifyPersistence> spider_ClassifyListByName(String ClassifyName);
//	/*
//	 * robot_分类
//	 */
//	@Select("SELECT ClassifyId,ClassifyName FROM Classify WHERE ParentId='0'")
//	public List<ClassifyPersistence> FirstClassify_robot();
//	@Select("SELECT ClassifyId,classifyName,sum(faqCollection) as a FROM faq,classify  WHERE classifyId=faqClassify AND parentId=#{0} GROUP BY faqClassify ORDER BY a DESC LIMIT 4")
//	public List<ClassifyPersistence> SecondClassify_robot(int paramString);
//	/*
//	 * faq、faq1_上侧的第二级分类
//	 */
//	@Select("SELECT ClassifyId,classifyName FROM classify  WHERE parentId=#{0}")
//	public List<ClassifyPersistence> SecondClassify_robot2(int paramString);
//	/*
//	 * faq1_下面4栏推荐_按照浏览量
//	 */
//	@Select("SELECT ClassifyId,classifyName,sum(faqScan) as a  FROM faq,classify WHERE faq.faqClassify = classify.classifyId AND classify.parentId=#{0} GROUP BY faqClassify ORDER BY a DESC LIMIT 4")
//	public List<ClassifyPersistence> SecondClassify_faq1(int paramString);
//	@Select("SELECT * FROM faq WHERE faqClassify=#{0} ORDER BY faqScan DESC LIMIT 1")
//	public List<FaqPersistence> faqPersistences_faq1(int paramString);
//	@Select("SELECT * FROM faq WHERE faqClassify=#{0} ORDER BY faqScan DESC LIMIT 1,5")
//	public List<FaqPersistence> faqPersistences2_faq1(int paramString);
//	/*
//	 * faq2_获取第二类分类的名称、第一类分类的名称
//	 */
//	@Select("SELECT * FROM classify WHERE classifyId=#{0}")
//	public List<ClassifyPersistence> classify(int classifyId);
//	@Select("SELECT parentId FROM classify WHERE classifyId=#{0}")
//	public int classifyParentId(int classifyId);
	
}
