package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;

public interface ClassifyPersistenceMapper  extends IBaseDao<ClassifyPersistence, String>{
	/*
	 * zyq_spider_按照分类名称查找
	 */
	@Select("SELECT * FROM TBL_FAQclassify WHERE FAQCLASSIFYNAME=#{0} AND FAQPARENTID=#{1}")
	public List<ClassifyPersistence> spider_ClassifyListByName(String ClassifyName,String parentId);
	/*
	 * zyq_robot_分类
	 */
	@Select("SELECT FAQCLASSIFYID,FAQCLASSIFYNAME FROM TBL_FAQclassify WHERE FAQPARENTID='0'")
	public List<ClassifyPersistence> FirstClassify_robot();
	@Select("SELECT TBL_FAQclassify.FAQCLASSIFYID,TBL_FAQclassify.FAQCLASSIFYNAME,sum(TBL_FAQquestion.COLLECTION) as a FROM TBL_FAQclassify,TBL_FAQquestion WHERE TBL_FAQclassify.FAQCLASSIFYID=TBL_FAQquestion.FAQCLASSIFYID AND TBL_FAQclassify.FAQPARENTID=#{0} GROUP BY TBL_FAQquestion.FAQCLASSIFYID ORDER BY a DESC LIMIT 4")
	public List<ClassifyPersistence> SecondClassify_robot(String ParentId);

	@Select("SELECT * FROM TBL_FAQclassify WHERE FAQPARENTID=#{0}")
	public List<ClassifyPersistence> SecondClassify_total(String ParentId);

	/*
	 * zyq_faq、faq1_上侧的第二级分类
	 */
	@Select("SELECT TBL_FAQclassify.FAQCLASSIFYID,TBL_FAQclassify.FAQCLASSIFYNAME,sum(TBL_FAQquestion.SCAN+TBL_FAQquestion.COLLECTION*10) as a FROM TBL_FAQclassify,TBL_FAQquestion WHERE TBL_FAQclassify.FAQCLASSIFYID=TBL_FAQquestion.FAQCLASSIFYID AND TBL_FAQclassify.FAQPARENTID=#{0} GROUP BY TBL_FAQquestion.FAQCLASSIFYID ORDER BY a DESC")
	public List<ClassifyPersistence> SecondClassify_robot2(String ParentId);
	/*
	 * zyq_faq1_下面4栏推荐_按照浏览量
	 */
	@Select("SELECT TBL_FAQclassify.FAQCLASSIFYID,TBL_FAQclassify.FAQCLASSIFYNAME,sum(SCAN) as a FROM TBL_FAQquestion,TBL_FAQclassify WHERE TBL_FAQquestion.FAQCLASSIFYID = TBL_FAQclassify.FAQCLASSIFYID AND TBL_FAQclassify.FAQPARENTID=#{0} GROUP BY TBL_FAQquestion.FAQCLASSIFYID ORDER BY a DESC LIMIT 4")
	public List<ClassifyPersistence> faq1_SecondClassify(String ParentId);
	@Select("SELECT * FROM TBL_FAQquestion WHERE FAQCLASSIFYID=#{0} ORDER BY SCAN DESC LIMIT 1")
	public List<QuestionPersistence> faq1_faqPersistences(String ClassifyId);
	@Select("SELECT * FROM TBL_FAQquestion WHERE FAQCLASSIFYID=#{0} ORDER BY SCAN DESC LIMIT 1,5")
	public List<QuestionPersistence> faq1_faqPersistences2(String paramString);
	/*
	 * zyq_faq2_获取第二类分类的名称、第一类分类的名称
	 */
	@Select("SELECT * FROM TBL_FAQclassify WHERE FAQCLASSIFYID=#{0}")
	public List<ClassifyPersistence> faq2_classify(String ClassifyId);
	@Select("SELECT FAQPARENTID FROM TBL_FAQclassify WHERE FAQCLASSIFYID=#{0}")
	public String faq2_classifyParentId(String ClassifyId);
	/*
	 * zyq_question_查看问答模块的分类
	 * 
	 * 根据 分类名  查找该分类的全部数据
	 * 
	 *    ！！！          返回值肯定是一条记录，不应该是LIST 
	 */
	@Select("SELECT * FROM TBL_FAQclassify WHERE FAQCLASSIFYNAME=#{0} AND FAQPARENTID=#{1}")
	public List<ClassifyPersistence> question_ClassifyListByName(String ClassifyName,String type);
	
	/**
	 * author:zzl
	 * abstract:获取当前问题分类的上一级分类
	 * data:2017年9月15日10:00:03
	 */
	@Select("SELECT FAQPARENTID FROM TBL_FAQclassify WHERE FAQCLASSIFYID=#{0}")
	public String faq_parentId(String faq_classifyId);
	
	/**
	 * author:zzl
	 * abstract:获取该父分类下的所有子分类
	 * data:2017年9月15日10:07:11
	 */
	@Select("SELECT * FROM TBL_FAQclassify WHERE FAQPARENTID=#{0} ")
	public List<ClassifyPersistence> faq_classifyIds(String ParentId);
	
	/**
	 * author:zzl
	 * abstract:获取一级分类信息
	 * data:2017年9月17日19:33:32
	 */
	@Select("SELECT * FROM TBL_FAQclassify WHERE FAQCLASSIFYID=#{0} ")
	public List<ClassifyPersistence> getInfoById(String classifyId);
	

}
