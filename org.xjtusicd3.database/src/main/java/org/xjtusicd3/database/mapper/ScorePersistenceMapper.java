package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ScorePersistence;

public interface ScorePersistenceMapper extends IBaseDao<ScorePersistence, String>{
	/*
	 * zyq_faq3_ajax_查看评论列表
	 */
	@Select("SELECT * FROM TBL_Score WHERE FAQQUESTIONID=#{0}")
	List<ScorePersistence> getScoreList(String questionId);
	/*
	 * zyq_faq3_获取FAQ得分
	 */
	@Select("SELECT SUM(SCORE) FROM TBL_Score WHERE FAQQUESTIONID=#{0}")
	String getScore(String questionId);
	
	@Insert("INSERT INTO TBL_Score(SCOREID,USERID,FAQQUESTIONID,SCORE,TIME) VALUES (#{0},#{1},#{2},#{3},#{4})")
	void saveFAQscore(String scoreId, String userId, String fAQquestionId, float score, String time);
	
}
