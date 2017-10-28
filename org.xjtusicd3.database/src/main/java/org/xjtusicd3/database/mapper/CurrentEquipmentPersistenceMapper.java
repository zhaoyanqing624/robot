package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.helper.CurrentConfigureHelper;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.CurrentEquipmentPersistence;

public interface CurrentEquipmentPersistenceMapper extends IBaseDao<CurrentEquipmentPersistence, String>{
	/**
	 * author:zhaoyanqing
	 * date:2017年9月9日 21:12:28
	 * abstract:根据MAC地址查看是否在当前配置表中
	 */
	@Select("SELECT * FROM TBL_CurrentEquipment WHERE MACADDRESS=#{0}")
	List<CurrentEquipmentPersistence> getCurrentEquipment(String macaddress);
	/**
	 * author:zhaoyanqing
	 * date:2017年9月9日 23:58:45
	 * abstract:根据MAC地址更新（update）当前配置表中
	 */
	@Update("UPDATE TBL_CurrentEquipment SET USERID=#{0},IP=#{2},EQUIPMENTMODEL=#{3},EQUIPMENTTIME=#{4},CPU=#{5},RAM=#{6},STORAGE=#{7},HARDDRIVER=#{8},HARDDRIVERMODEL=#{9},NETWORKCARD=#{10},MOTHERBOARD=#{11},OSNAME=#{12},OSTYPE=#{13},OSVERSION=#{14},OSID=#{15},GRAPHICCARD=#{16},AUDIOCARD=#{17},TIMEREMARKS=#{18} WHERE MACADDRESS=#{1}")
	void updateCurrentEquipment(String userId, String macAddress, String ipAddress, String equipmentModel,
			String equipmentTime, String cPU, String rAM, String memoryBank, String hardDrive,
			String hardDriveModel, String networkCard, String motherBoard, String osName, String osType,
			String osVersion, String osId, String graphicCard, String audioCard,String time);
	
	//zzl_2017年9月30日15:20:41
	@Select("SELECT * FROM TBL_CurrentEquipment WHERE USERID=#{0}")
	List<CurrentEquipmentPersistence> getMacAdress(String userid);

}
