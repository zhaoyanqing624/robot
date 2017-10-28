package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.CurrentEquipmentPersistenceMapper;
import org.xjtusicd3.database.model.CurrentEquipmentPersistence;
import org.xjtusicd3.database.model.UserPersistence;

public class CurrentEquipmentHelp {
	/*
	 * zyq_personal3_检验是否存在当前设备表
	 */
	public static List<CurrentEquipmentPersistence> currentEquipment(String macaddress){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentEquipmentPersistenceMapper mapper = session.getMapper(CurrentEquipmentPersistenceMapper.class);
		List<CurrentEquipmentPersistence> list = mapper.getCurrentEquipment(macaddress);
		session.close();
		return list;
	}
	/*
	 * zyq_personal3_存入当前设备表
	 */
	public static void save(CurrentEquipmentPersistence currentEquipmentPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentEquipmentPersistenceMapper mapper = session.getMapper(CurrentEquipmentPersistenceMapper.class);
		mapper.save(currentEquipmentPersistence);
		session.close();
	}
	/*
	 * zyq_peisonal3_更新当前设备表
	 */
	public static void updateCurrentEquipment(String username,String macAddress,String ipAddress,String equipmentModel,String equipmentTime,String CPU,String RAM,String memoryBank,
			String hardDrive,String hardDriveModel,String networkCard,String motherBoard,String osName,String osType,String osVersion,String osId,String graphicCard,String audioCard,String time){
		String userId ="";
		if (username==null) {
			userId = "00000000-0000-0000-0000-000000000000";
		}else {
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			userId = userPersistences.get(0).getUSERID();
		}
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentEquipmentPersistenceMapper mapper = session.getMapper(CurrentEquipmentPersistenceMapper.class);
		mapper.updateCurrentEquipment(userId,macAddress,ipAddress,equipmentModel,equipmentTime,CPU,RAM,memoryBank,
				hardDrive,hardDriveModel,networkCard,motherBoard,osName,osType,osVersion,osId,graphicCard,audioCard,time);
		session.close();
	}
	//zzl_2017年9月30日15:20:41
	public static List<CurrentEquipmentPersistence> getMacAdress(String userid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentEquipmentPersistenceMapper mapper = session.getMapper(CurrentEquipmentPersistenceMapper.class);
		List<CurrentEquipmentPersistence> list = mapper.getMacAdress(userid);
		session.close();
		return list;
	}
}
