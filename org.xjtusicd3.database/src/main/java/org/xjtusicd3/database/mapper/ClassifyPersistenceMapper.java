package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;

public interface ClassifyPersistenceMapper  extends IBaseDao<ClassifyPersistence, String>{
	/*
	 * spider_按照分类名称查找
	 */
	@Select("SELECT * FROM Classify WHERE ClassifyName=#{0}")
	public List<ClassifyPersistence> spider_ClassifyListByName(String ClassifyName);
	/*
	 * robot_分类
	 */
	@Select("SELECT ClassifyId,ClassifyName FROM Classify WHERE ParentId='0'")
	public List<ClassifyPersistence> FirstClassify_robot();
	@Select("SELECT Classify.ClassifyId,Classify.ClassifyName,sum(Question.FaqCollection) as a FROM Classify,Question WHERE Classify.ClassifyId=Question.ClassifyId AND Classify.ParentId=#{0} GROUP BY Question.ClassifyId ORDER BY a DESC LIMIT 4")
	public List<ClassifyPersistence> SecondClassify_robot(String ParentId);

	/*
	 * faq、faq1_上侧的第二级分类
	 */
	@Select("SELECT Classify.ClassifyId,Classify.ClassifyName,sum(Question.FaqScan+Question.FaqCollection*10) as a FROM Classify,Question WHERE Classify.ClassifyId=Question.ClassifyId AND Classify.ParentId=#{0} GROUP BY Question.ClassifyId ORDER BY a DESC")
	public List<ClassifyPersistence> SecondClassify_robot2(String ParentId);
	/*
	 * faq1_下面4栏推荐_按照浏览量
	 */
	@Select("SELECT Classify.ClassifyId,Classify.ClassifyName,sum(FaqScan) as a FROM Question,Classify WHERE Question.ClassifyId = Classify.ClassifyId AND Classify.ParentId=#{0} GROUP BY Question.ClassifyId ORDER BY a DESC LIMIT 4")
	public List<ClassifyPersistence> faq1_SecondClassify(String ParentId);
	@Select("SELECT * FROM Question WHERE ClassifyId=#{0} ORDER BY FaqScan DESC LIMIT 1")
	public List<QuestionPersistence> faq1_faqPersistences(String ClassifyId);
	@Select("SELECT * FROM Question WHERE ClassifyId=#{0} ORDER BY FaqScan DESC LIMIT 1,5")
	public List<QuestionPersistence> faq1_faqPersistences2(String paramString);
	/*
	 * faq2_获取第二类分类的名称、第一类分类的名称
	 */
	@Select("SELECT * FROM Classify WHERE ClassifyId=#{0}")
	public List<ClassifyPersistence> faq2_classify(String ClassifyId);
	@Select("SELECT ParentId FROM Classify WHERE ClassifyId=#{0}")
	public String faq2_classifyParentId(String ClassifyId);
	
}
