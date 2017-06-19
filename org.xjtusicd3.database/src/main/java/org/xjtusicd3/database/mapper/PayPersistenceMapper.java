package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
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
	/*
	 * zyq_personal2_查看关注列表
	 */
	@Select("SELECT * FROM TBL_Pay WHERE PAYUSERID=#{0} AND BEPAYUSERID=#{1}")
	List<PayPersistence> getpayList(String userId, String touserId);
	/*
	 * zyq_personal2_取消关注
	 */
	@Delete("DELETE FROM TBL_Pay WHERE PAYUSERID=#{0} AND BEPAYUSERID=#{1}")
	void deletePay(String userId, String touserId);
	
}
