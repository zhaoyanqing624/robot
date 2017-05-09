package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.PayPersistence;

public interface PayPersistenceMapper extends IBaseDao<PayPersistence, String>{
	/*
	 * zyq_personal3_关注、被关注
	 */
	@Select("SELECT * FROM TBL_Pay WHERE PAYUSERID=#{0}")
	List<PayPersistence> payList(String userid);
	@Select("SELECT * FROM TBL_Pay WHERE BEPAYUSERID=#{0}")
	List<PayPersistence> bepayList(String beuserid);
	
}
