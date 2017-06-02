package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.ScorePersistenceMapper;
import org.xjtusicd3.database.model.ScorePersistence;

public class ScoreHelper {
	/*
	 * zyq_faq3_ajax_评分
	 */
	public static void saveFAQscore(ScorePersistence scorePersistence) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ScorePersistenceMapper mapper = session.getMapper(ScorePersistenceMapper.class);
		mapper.save(scorePersistence);
		session.close();
	}
	/*
	 * zyq_faq3_ajax_查看评论列表
	 */
	public static List<ScorePersistence> getScoreList(String questionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ScorePersistenceMapper mapper = session.getMapper(ScorePersistenceMapper.class);
		List<ScorePersistence> list = mapper.getScoreList(questionId);
		session.close();
		return list;
	}
	/*
	 * zyq_faq3_获取FAQ得分
	 */
	public static float getScore(String questionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ScorePersistenceMapper mapper = session.getMapper(ScorePersistenceMapper.class);
		String a = mapper.getScore(questionId);
		float score;
		if (a==null) {
			score = 0;
		}else {
			score = Float.parseFloat(a);
		}
		session.close();
		return score;
	}
}
