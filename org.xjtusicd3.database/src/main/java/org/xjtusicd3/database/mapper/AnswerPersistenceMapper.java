package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.AnswerPersistence;

public interface AnswerPersistenceMapper extends IBaseDao<AnswerPersistence, String>{
	/*
	 * faq3_知识内容
	 */
	@Select("SELECT * FROM TBL_FAQanswer WHERE FAQQUESTIONID=#{0}")
	public List<AnswerPersistence> faq3_faqContent(String QuestionId);
}