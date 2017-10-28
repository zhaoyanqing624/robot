package org.xjtusicd3.partner.service;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.CurrentConfigureHelper;
import org.xjtusicd3.database.helper.CurrentEquipmentHelp;
import org.xjtusicd3.database.helper.EquipmentHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.CurrentConfigurePersistence;
import org.xjtusicd3.database.model.CurrentEquipmentPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.filter.LOCALMAC;
import org.xjtusicd3.partner.view.Personal3_EquipmentConfigureView;
import org.xjtusicd3.partner.view.Personal3_EquipmentView;

public class EquipmentService {
	/**
	 * author:zhaoyanqing
	 * date:2017年9月9日 21:08:18
	 * abstract:根据MAC地址查看当前配置是否存在
	 */
	public static List<CurrentEquipmentPersistence> currentEquipment(String macAddress){
		List<CurrentEquipmentPersistence> currentEquipmentPersistences = EquipmentHelper.getCurrentEquipmentList(macAddress);
		return currentEquipmentPersistences;
	}
	/**
	 * author:zhaoyanqing
	 * date:2017年9月9日 21:31:03
	 * abstract:如果当前设备不存在当前设备表，将获取的计算机信息添加（add）到当前设备表;如果存在，则update
	 */
	public static void currentEquipment(String username,String macAddress,String ipAddress,String equipmentModel,String equipmentTime,String CPU,String RAM,String memoryBank,
			String hardDrive,String hardDriveModel,String networkCard,String motherBoard,String osName,String osType,String osVersion,String osId,String graphicCard,String audioCard){
	    //判断当前设备表是否包含此计算机
	  	Date date=new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
	    List<CurrentEquipmentPersistence> currentEquipmentPersistences = CurrentEquipmentHelp.currentEquipment(macAddress);
	    if (currentEquipmentPersistences.isEmpty()) {
		    CurrentEquipmentPersistence currentEquipmentPersistence = new CurrentEquipmentPersistence();
			currentEquipmentPersistence.setEQUIPMENTID(UUID.randomUUID().toString());
			currentEquipmentPersistence.setMACADDRESS(macAddress);
			currentEquipmentPersistence.setEQUIPMENTMODEL(equipmentModel);
			currentEquipmentPersistence.setEQUIPMENTTIME(equipmentTime);
			currentEquipmentPersistence.setIP(ipAddress);
			currentEquipmentPersistence.setCPU(CPU);
			currentEquipmentPersistence.setRAM(RAM);
			currentEquipmentPersistence.setHARDDRIVER(hardDrive);
			currentEquipmentPersistence.setHARDDRIVERMODEL(hardDriveModel);
			currentEquipmentPersistence.setSTORAGE(memoryBank);
			currentEquipmentPersistence.setNETWORKCARD(networkCard);
			currentEquipmentPersistence.setMOTHERBOARD(motherBoard);
			currentEquipmentPersistence.setOSNAME(osName);
			currentEquipmentPersistence.setOSTYPE(osType);
			currentEquipmentPersistence.setOSVERSION(osVersion);
			currentEquipmentPersistence.setOSID(osId);
			if (username==null) {
				currentEquipmentPersistence.setUSERID("00000000-0000-0000-0000-000000000000");
			}else {
				List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
				currentEquipmentPersistence.setUSERID(userPersistences.get(0).getUSERID());
			}
			currentEquipmentPersistence.setISNOTICE(0);
			currentEquipmentPersistence.setGRAPHICCARD(graphicCard);
			currentEquipmentPersistence.setAUDIOCARD(audioCard);
		    currentEquipmentPersistence.setTIMEREMARKS(time);
	    	CurrentEquipmentHelp.save(currentEquipmentPersistence);
		}else {
			CurrentEquipmentHelp.updateCurrentEquipment(username,macAddress,ipAddress,equipmentModel,equipmentTime,CPU,RAM,memoryBank,
					hardDrive,hardDriveModel,networkCard,motherBoard,osName,osType,osVersion,osId,graphicCard,audioCard,time);
		}
	    
	}
	/**
	 * author:zhaoyanqing
	 * date:2017年9月10日 09:09:17
	 * abstract:添加（add）或更改（update）当前设备的软件/补丁资源信息
	 */
	public static void currentEquipment(String macaddress,String software[],String path[]){
		List<CurrentEquipmentPersistence> currentEquipmentPersistences = CurrentEquipmentHelp.currentEquipment(macaddress);
		if (!currentEquipmentPersistences.isEmpty()) {
			List<CurrentConfigurePersistence> configurePersistences = CurrentConfigureHelper.getCurrentConfigure(currentEquipmentPersistences.get(0).getEQUIPMENTID());
			if (configurePersistences.isEmpty()) {

			}else {
				CurrentConfigureHelper.deleteCurrentConfigure(configurePersistences.get(0).getEQUIPMENTID(),"软件");
				CurrentConfigureHelper.deleteCurrentConfigure(configurePersistences.get(0).getEQUIPMENTID(),"补丁");
			}
			for(int i=0;i<software.length;i++){
				String[] aStrings = software[i].split(";");
				CurrentConfigurePersistence configurePersistence = new CurrentConfigurePersistence();
				configurePersistence.setCURRENTCONFIGUREID(UUID.randomUUID().toString());
				configurePersistence.setEQUIPMENTID(currentEquipmentPersistences.get(0).getEQUIPMENTID());
				configurePersistence.setCONFIGURENAME(aStrings[0]);
				configurePersistence.setCONFIGUREVERSION(aStrings[1]);
				configurePersistence.setCONFIGURETYPE("软件");
				CurrentConfigureHelper.saveCurrentConfigure(configurePersistence);
			}
			for(int i=0;i<path.length;i++){
				String[] aStrings = path[i].split(";");
				CurrentConfigurePersistence configurePersistence = new CurrentConfigurePersistence();
				configurePersistence.setCURRENTCONFIGUREID(UUID.randomUUID().toString());
				configurePersistence.setEQUIPMENTID(currentEquipmentPersistences.get(0).getEQUIPMENTID());
				configurePersistence.setCONFIGURENAME(aStrings[0]+";"+aStrings[1]);
				configurePersistence.setCONFIGUREVERSION(aStrings[2]);
				configurePersistence.setCONFIGURETYPE("补丁");
				CurrentConfigureHelper.saveCurrentConfigure(configurePersistence);
			}
		}
	}
	/**
	 * author:zhaoyanqing
	 * date:2017年9月10日 09:09:17
	 * abstract:personal3.html设备信息展示
	 */
	
	public static List<Personal3_EquipmentView> personal3_EquipmentView(String username,String macaddress) {
		List<Personal3_EquipmentView> personal3_EquipmentViews = new ArrayList<Personal3_EquipmentView>();
		List<UserPersistence> uList = UserHelper.getUserInfo(username);		
		//zzl_获取macaddress
		List<CurrentEquipmentPersistence> configure = CurrentEquipmentHelp.getMacAdress(uList.get(0).getUSERID());
		
		List<CurrentEquipmentPersistence> currentEquipmentPersistences = CurrentEquipmentHelp.currentEquipment(configure.get(0).getMACADDRESS());
		for(CurrentEquipmentPersistence currentEquipmentPersistence:currentEquipmentPersistences){
			List<Personal3_EquipmentConfigureView> personal3_EquipmentConfigureViews = new ArrayList<Personal3_EquipmentConfigureView>();
			List<CurrentConfigurePersistence> configurePersistences = CurrentConfigureHelper.getCurrentConfigure(currentEquipmentPersistence.getEQUIPMENTID(),"补丁");
			List<Personal3_EquipmentConfigureView> personal3_EquipmentConfigureViews2 = new ArrayList<Personal3_EquipmentConfigureView>();
			List<CurrentConfigurePersistence> configurePersistences2 = CurrentConfigureHelper.getCurrentConfigure(currentEquipmentPersistence.getEQUIPMENTID(),"软件");
			for(CurrentConfigurePersistence configurePersistence:configurePersistences){
				Personal3_EquipmentConfigureView personal3_EquipmentConfigureView = new Personal3_EquipmentConfigureView(configurePersistence);
				personal3_EquipmentConfigureViews.add(personal3_EquipmentConfigureView);
			}
			for(CurrentConfigurePersistence configurePersistence:configurePersistences2){
				Personal3_EquipmentConfigureView personal3_EquipmentConfigureView = new Personal3_EquipmentConfigureView(configurePersistence);
				personal3_EquipmentConfigureViews2.add(personal3_EquipmentConfigureView);
			}
			Personal3_EquipmentView view = new Personal3_EquipmentView(currentEquipmentPersistence);
			view.setPatchViews(personal3_EquipmentConfigureViews);
			view.setSoftViews(personal3_EquipmentConfigureViews2);;
			personal3_EquipmentViews.add(view);
		}
		return personal3_EquipmentViews;
	}
}
