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
	@Select("SELECT * FROM TBL_Pay WHERE PAYUSERID=#{0} ORDER BY TIME DESC LIMIT #{1},#{2} ")
	List<PayPersistence> payList_Limit(String userid,int startNumber,int number);
	@Select("SELECT * FROM TBL_Pay WHERE PAYUSERID=#{0} AND STR_TO_DATE(TIME,'%Y-%m-%d %H:%i')>STR_TO_DATE(#{3},'%Y-%m-%d %H:%i') ORDER BY TIME DESC LIMIT #{1},#{2} ")
	List<PayPersistence> payList_Limit_Time(String userid,int startNumber,int number,String time);
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
	/*
	 * zyq_personal2_个人主页关注别人展示
	 */
	@Select("SELECT * FROM TBL_Pay WHERE PAYUSERID=#{0} AND STR_TO_DATE(TIME,'%Y-%m-%d %H:%i:%s')>STR_TO_DATE(#{1},'%Y-%m-%d %H:%i:%s')")
	List<PayPersistence> payList_time(String userid, String time);
	@Select("SELECT * FROM TBL_Pay WHERE PAYUSERID=#{0} AND STR_TO_DATE(TIME,'%Y-%m-%d %H:%i:%s')>STR_TO_DATE(#{1},'%Y-%m-%d %H:%i:%s') ORDER BY TIME DESC LIMIT #{2},#{3}")
	List<PayPersistence> payList_time_Limit(String userid, String time,int startNumber,int number);
	@Select("SELECT * FROM TBL_Pay WHERE PAYUSERID=#{0} AND STR_TO_DATE(TIME,'%Y-%m-%d %H:%i:%s')>STR_TO_DATE(#{1},'%Y-%m-%d %H:%i:%s') AND STR_TO_DATE(TIME,'%Y-%m-%d %H:%i')>STR_TO_DATE(#{4},'%Y-%m-%d %H:%i') ORDER BY TIME DESC LIMIT #{2},#{3}")
	List<PayPersistence> payList_time_Limit_Time(String userid, String time,int startNumber,int number,String time2);
	
}
