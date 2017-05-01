package org.xjtusicd3.database.helper;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.SoftPersistenceMapper;
import org.xjtusicd3.database.model.SoftPersistence;

public class SoftHelper {
	/*
	 * zyq_spider_软件的信息添加
	 */
	public static void sava(SoftPersistence softPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SoftPersistenceMapper mapper = session.getMapper(SoftPersistenceMapper.class);
		mapper.save(softPersistence);
		session.close();
	}
	/*
	 * zyq_spider_配置信息更新
	 */
	public static List<SoftPersistence> update_Soft(String softid,String logo,String introduce,String descrip,String versiontype,String version,String new_introduce,String new_descrip,String softtype,String spareurl,String os,String website,String score){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SoftPersistenceMapper mapper = session.getMapper(SoftPersistenceMapper.class);
		List<SoftPersistence> list = mapper.update_Soft(softid,logo,introduce,descrip,versiontype,version,new_introduce,new_descrip,softtype,spareurl,os,website,score);
		session.close();
		return list;
	}
}
