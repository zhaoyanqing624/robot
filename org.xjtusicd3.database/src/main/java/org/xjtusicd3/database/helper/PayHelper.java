package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.PayPersistenceMapper;
import org.xjtusicd3.database.model.PayPersistence;

public class PayHelper {
	/*
	 * zyq_personal3_关注、被关注
	 */
	public static List<PayPersistence> payList(String userid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		List<PayPersistence> list  = mapper.payList(userid);
		session.close();
		return list;
	}
	public static List<PayPersistence> bepayList(String beuserid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		List<PayPersistence> list  = mapper.bepayList(beuserid);
		session.close();
		return list;
	}
	/*
	 * zyq_personal2_关注
	 */
	public static void savePay(PayPersistence payPersistence) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		mapper.save(payPersistence);
		session.close();
	}
	/*
	 * zyq_personal2_查看关注列表
	 */
	public static List<PayPersistence> getpayList(String userId,String touserId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		List<PayPersistence> list  = mapper.getpayList(userId,touserId);
		session.close();
		return list;
	}
	/*
	 * zyq_personal2_取消关注
	 */
	public static void deletePay(String userId, String touserId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PayPersistenceMapper mapper = session.getMapper(PayPersistenceMapper.class);
		mapper.deletePay(userId,touserId);
		session.close();
	}
}
