package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;

public interface ClassifyPersistenceMapper  extends IBaseDao<ClassifyPersistence, String>{
	/*
	 * zyq_spider_按照分类名称查找
	 */
	@Select("SELECT * FROM TBL_FAQclassify WHERE FAQCLASSIFYNAME=#{0} AND FAQPARENTID=#{1}")
	public List<ClassifyPersistence> spider_ClassifyListByName(String ClassifyName,String type);
	/*
	 * zyq_robot_分类
	 */
	@Select("SELECT FAQCLASSIFYID,FAQCLASSIFYNAME FROM TBL_FAQclassify WHERE FAQPARENTID='0'")
	public List<ClassifyPersistence> FirstClassify_robot();
	@Select("SELECT TBL_FAQclassify.FAQCLASSIFYID,TBL_FAQclassify.FAQCLASSIFYNAME,sum(TBL_FAQquestion.COLLECTION) as a FROM TBL_FAQclassify,TBL_FAQquestion WHERE TBL_FAQclassify.FAQCLASSIFYID=TBL_FAQquestion.FAQCLASSIFYID AND TBL_FAQclassify.FAQPARENTID=#{0} GROUP BY TBL_FAQquestion.FAQCLASSIFYID ORDER BY a DESC LIMIT 4")
	public List<ClassifyPersistence> SecondClassify_robot(String ParentId);

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
	 */
	@Select("SELECT * FROM TBL_FAQclassify WHERE FAQCLASSIFYNAME=#{0} AND FAQPARENTID=#{1}")
	public List<ClassifyPersistence> question_ClassifyListByName(String ClassifyName,String type);
	
}
