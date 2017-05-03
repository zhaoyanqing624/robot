package org.xjtusicd3.database.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;

public interface QuestionPersistenceMapper extends IBaseDao<QuestionPersistence, String>{
	/*
	 * zyq_robot-分类
	 */
	@Select("SELECT Question.QuestionId,Question.FaqTitle FROM Question WHERE Question.ClassifyId=#{0} ORDER BY Question.FaqCollection DESC LIMIT 4 ")
	public List<QuestionPersistence> SecondClassify_robot(String ClassifyId);
	/*
	 * zyq_faq2_知识列表
	 */
	@Select("SELECT * FROM Question WHERE ClassifyId=#{0} LIMIT #{1},5")
	public List<QuestionPersistence> faq2_faqlist(String param1,int param2);
	@Select("SELECT * FROM User WHERE UserId=#{0}")
	public List<UserPersistence> faq2_userlist(String UserId);
	@Select("SELECT COUNT(*) FROM Question WHERE ClassifyId=#{0}")
	public int pageTotal(String ClassifyId);
	@Select("SELECT UserId FROM Answer WHERE QuestionId=#{0}")
	public String faq2_UserId(String QuestionId);
	/*
	 * zyq_faq3_知识内容
	 */
	@Select("SELECT * FROM Question WHERE QuestionId=#{0}")
	public List<QuestionPersistence> faq3_faqcontent(String QuestionId);
	/*
	 * zyq_faq3_根据知识ID找类型classify
	 */
	@Select("SELECT ClassifyId FROM Question WHERE QuestionId=#{0}")
	public String faq3_faqclassifyId(String QuestionId);
	/*
	 * zpz_
	 */
	@Select("SELECT * FROM TBL_FAQquestion LIMIT 200")
	public List<QuestionPersistence> getFaq();
}
