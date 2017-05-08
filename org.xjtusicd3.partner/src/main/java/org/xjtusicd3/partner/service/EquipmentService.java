package org.xjtusicd3.partner.service;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hyperic.sigar.SigarException;
import org.xjtusicd3.database.helper.CurrentConfigureHelper;
import org.xjtusicd3.database.helper.CurrentEquipmentHelp;
import org.xjtusicd3.database.helper.EquipmentHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.helper.User_Equipment_HistoryHelper;
import org.xjtusicd3.database.model.CurrentConfigurePersistence;
import org.xjtusicd3.database.model.CurrentEquipmentPersistence;
import org.xjtusicd3.database.model.EquipmentPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.database.model.User_Equipment_HistoryPersistence;
import org.xjtusicd3.partner.filter.LOCALMAC;
import org.xjtusicd3.partner.filter.SystemDriver;
import org.xjtusicd3.partner.filter.SystemPatch;
import org.xjtusicd3.partner.filter.SystemSigar;
import org.xjtusicd3.partner.filter.SystemSoftware;
import org.xjtusicd3.partner.view.Personal3_EquipmentView;

public class EquipmentService {
	/*
	 * zyq_ajax_实时获取当前设备资源
	 */
	public static void saveCurrentEquipment(String email) throws UnknownHostException, SocketException, SigarException{
		LOCALMAC localmac = new LOCALMAC();
		String macaddress = localmac.getMacAddress();
		List<EquipmentPersistence> list = EquipmentHelper.getEquipmentList(macaddress);
		//获取本机基本信息
		SystemDriver systemDriver = new SystemDriver();
		String[] EquipmentModel = systemDriver.getEquipmentModel().split(",");
		String equipmentmodel = EquipmentModel[0];
		String equipmenttime = EquipmentModel[1];
		String cpu = systemDriver.getCPU();
		String[] GraphicCard = systemDriver.getGraphicCard().split(",");
		String graphiccard = GraphicCard[0];
		String[] AudioCard = systemDriver.getAudioCard().split(",");
		String audiocard = AudioCard[0];
		String[] Storage = systemDriver.getStorage().split(",");
		String storage = Storage[0];
		String[] NetwordCard1 = systemDriver.getNetworkCard1().split(",");
		String network1 = NetwordCard1[0];
		String[] NetwordCard2 = systemDriver.getNetworkCard2().split(",");
		String netword2 = NetwordCard2[0];
		String[] MotherBoard = systemDriver.getMotherBoard().split(",");
		String motherboard = MotherBoard[0]+" "+MotherBoard[1];
		String osid = systemDriver.getOSID();
		
		SystemSigar systemSigar = new SystemSigar();
		String ram = systemSigar.memory();
		String harddriver = systemSigar.file();
		String ip = systemSigar.property();
		String[] OS = systemSigar.os().split(",");
		String osname = OS[0];
		String ostype = OS[1];
		String osversion = OS[2];
		
		SystemPatch systemPatch = new SystemPatch();
		List<String> PatchList = systemPatch.PatchList();
		
		SystemSoftware systemSoftware = new SystemSoftware();
		List<String> SoftList = systemSoftware.SoftList();
		
		//如果为本地配置组
		if (list.size()!=0) {
			//是否已添加当前列表
			List<CurrentEquipmentPersistence> list2 = CurrentEquipmentHelp.currentEquipment(macaddress);
			List<User_Equipment_HistoryPersistence> list3 = User_Equipment_HistoryHelper.User_Equipment_HistoryList(list.get(0).getEQUIPMENTID());
			if (list2.size()==0) {
				CurrentEquipmentPersistence cep = new CurrentEquipmentPersistence();
				UUID uuid = UUID.randomUUID();
				cep.setEQUIPMENTID(uuid.toString());
				cep.setMACADDRESS(macaddress);
				cep.setEQUIPMENTMODEL(equipmentmodel);
				cep.setEQUIPMENTTIME(equipmenttime);
				cep.setRAM(ram);
				cep.setHARDDRIVER(harddriver);
				cep.setIP(ip);
				cep.setCPU(cpu);
				cep.setSTORAGE(storage);
				cep.setNETWORKCARD(network1);
				cep.setNETWORKCARD2(netword2);
				cep.setMOTHERBOARD(motherboard);
				cep.setOSNAME(osname);
				cep.setOSTYPE(ostype);
				cep.setOSVERSION(osversion);
				cep.setOSID(osid);
				cep.setUSERID(list3.get(0).getUSERID());
				cep.setISNOTICE(0);
				cep.setGRAPHICCARD(graphiccard);
				cep.setAUDIOCARD(audiocard);
		    	Date date=new Date();
		        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String time = format.format(date);
		        cep.setTIMEREMARKS(time);
				CurrentEquipmentHelp.save(cep);
				//添加补丁信息
				for(int i=0;i<PatchList.size();i++){
					CurrentConfigurePersistence ccp = new CurrentConfigurePersistence();
					UUID uuid2 = UUID.randomUUID();
					ccp.setCURRENTCONFIGUREID(uuid2.toString());
					ccp.setEQUIPMENTID(uuid.toString());
					ccp.setCONFIGUREVERSION("");
					ccp.setCONFIGURENAME(PatchList.get(i).toString());
					ccp.setCONFIGURETYPE("补丁");
					CurrentConfigureHelper.saveCurrentConfigure(ccp);
				}
				//添加软件信息
				for(int i=0;i<SoftList.size();i++){
					String[] soft = SoftList.get(i).toString().split(",");
					String softname = soft[0];
					String softversion = soft[1];
					CurrentConfigurePersistence ccp = new CurrentConfigurePersistence();
					UUID uuid2 = UUID.randomUUID();
					ccp.setCURRENTCONFIGUREID(uuid2.toString());
					ccp.setEQUIPMENTID(uuid.toString());
					ccp.setCONFIGUREVERSION(softversion);
					ccp.setCONFIGURENAME(softname);
					ccp.setCONFIGURETYPE("软件");
					CurrentConfigureHelper.saveCurrentConfigure(ccp);
				}
				//添加驱动
				CurrentConfigureHelper.saveCurrentConfigure_driver(UUID.randomUUID().toString(), uuid.toString(), GraphicCard[1], GraphicCard[0], "驱动");
				CurrentConfigureHelper.saveCurrentConfigure_driver(UUID.randomUUID().toString(), uuid.toString(), AudioCard[1], AudioCard[0], "驱动");
				if (NetwordCard1[0].length()>1) {
					CurrentConfigureHelper.saveCurrentConfigure_driver(UUID.randomUUID().toString(), uuid.toString(), NetwordCard1[1], NetwordCard1[0], "驱动");
				}
				if (NetwordCard2[0].length()>1) {
					CurrentConfigureHelper.saveCurrentConfigure_driver(UUID.randomUUID().toString(), uuid.toString(), NetwordCard2[1], NetwordCard2[0], "驱动");
				}
				CurrentConfigureHelper.saveCurrentConfigure_driver(UUID.randomUUID().toString(), uuid.toString(), Storage[1], Storage[0], "驱动");
			}else {
				CurrentEquipmentPersistence cep = new CurrentEquipmentPersistence();
				cep.setEQUIPMENTID(list2.get(0).getEQUIPMENTID());
				cep.setMACADDRESS(macaddress);
				cep.setEQUIPMENTMODEL(equipmentmodel);
				cep.setEQUIPMENTTIME(equipmenttime);
				cep.setRAM(ram);
				cep.setHARDDRIVER(harddriver);
				cep.setIP(ip);
				cep.setCPU(cpu);
				cep.setSTORAGE(storage);
				cep.setNETWORKCARD(network1);
				cep.setNETWORKCARD2(netword2);
				cep.setMOTHERBOARD(motherboard);
				cep.setOSNAME(osname);
				cep.setOSTYPE(ostype);
				cep.setOSVERSION(osversion);
				cep.setOSID(osid);
				cep.setUSERID(list3.get(0).getUSERID());
				cep.setISNOTICE(0);
				cep.setGRAPHICCARD(graphiccard);
				cep.setAUDIOCARD(audiocard);
				Date date=new Date();
		        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String time = format.format(date);
		        cep.setTIMEREMARKS(time);
				CurrentEquipmentHelp.update(cep);
				//添加补丁信息
				for(int i=0;i<PatchList.size();i++){
					CurrentConfigurePersistence ccp = new CurrentConfigurePersistence();
					UUID uuid2 = UUID.randomUUID();
					ccp.setCURRENTCONFIGUREID(uuid2.toString());
					ccp.setEQUIPMENTID(list2.get(0).getEQUIPMENTID());
					ccp.setCONFIGUREVERSION("");
					ccp.setCONFIGURENAME(PatchList.get(i).toString());
					ccp.setCONFIGURETYPE("补丁");
					CurrentConfigureHelper.updataCurrentConfigure(ccp);
				}
				//添加软件信息
				for(int i=0;i<SoftList.size();i++){
					String[] soft = SoftList.get(i).toString().split(",");
					String softname = soft[0];
					String softversion = soft[1];
					CurrentConfigurePersistence ccp = new CurrentConfigurePersistence();
					UUID uuid2 = UUID.randomUUID();
					ccp.setCURRENTCONFIGUREID(uuid2.toString());
					ccp.setEQUIPMENTID(list2.get(0).getEQUIPMENTID());
					ccp.setCONFIGUREVERSION(softversion);
					ccp.setCONFIGURENAME(softname);
					ccp.setCONFIGURETYPE("软件");
					CurrentConfigureHelper.updataCurrentConfigure(ccp);
				}
				//添加驱动
				CurrentConfigureHelper.updateCurrentConfigure_driver(UUID.randomUUID().toString(), list2.get(0).getEQUIPMENTID(), GraphicCard[1], GraphicCard[0], "驱动");
				CurrentConfigureHelper.updateCurrentConfigure_driver(UUID.randomUUID().toString(), list2.get(0).getEQUIPMENTID(), AudioCard[1], AudioCard[0], "驱动");
				if (NetwordCard1[0].length()>1) {
					CurrentConfigureHelper.updateCurrentConfigure_driver(UUID.randomUUID().toString(), list2.get(0).getEQUIPMENTID(), NetwordCard1[1], NetwordCard1[0], "驱动");
				}
				if (NetwordCard2[0].length()>1) {
					CurrentConfigureHelper.updateCurrentConfigure_driver(UUID.randomUUID().toString(), list2.get(0).getEQUIPMENTID(), NetwordCard2[1], NetwordCard2[0], "驱动");
				}
				CurrentConfigureHelper.updateCurrentConfigure_driver(UUID.randomUUID().toString(), list2.get(0).getEQUIPMENTID(), Storage[1], Storage[0], "驱动");
			}
		}
		//如果不是本地配置组
		else {
			List<CurrentEquipmentPersistence> list2 = CurrentEquipmentHelp.currentEquipment(macaddress);
			List<UserPersistence> userPersistences = UserHelper.getEmail(email);
			if (list2.size()==0) {
				CurrentEquipmentPersistence cep = new CurrentEquipmentPersistence();
				UUID uuid = UUID.randomUUID();
				cep.setEQUIPMENTID(uuid.toString());
				cep.setMACADDRESS(macaddress);
				cep.setEQUIPMENTMODEL(equipmentmodel);
				cep.setEQUIPMENTTIME(equipmenttime);
				cep.setRAM(ram);
				cep.setHARDDRIVER(harddriver);
				cep.setIP(ip);
				cep.setCPU(cpu);
				cep.setSTORAGE(storage);
				cep.setNETWORKCARD(network1);
				cep.setNETWORKCARD2(netword2);
				cep.setMOTHERBOARD(motherboard);
				cep.setOSNAME(osname);
				cep.setOSTYPE(ostype);
				cep.setOSVERSION(osversion);
				cep.setOSID(osid);
				cep.setUSERID(userPersistences.get(0).getUSERID());
				cep.setISNOTICE(0);
				cep.setGRAPHICCARD(graphiccard);
				cep.setAUDIOCARD(audiocard);
		    	Date date=new Date();
		        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String time = format.format(date);
		        cep.setTIMEREMARKS(time);
				CurrentEquipmentHelp.save(cep);
				//添加补丁信息
				for(int i=0;i<PatchList.size();i++){
					CurrentConfigurePersistence ccp = new CurrentConfigurePersistence();
					UUID uuid2 = UUID.randomUUID();
					ccp.setCURRENTCONFIGUREID(uuid2.toString());
					ccp.setEQUIPMENTID(uuid.toString());
					ccp.setCONFIGUREVERSION("");
					ccp.setCONFIGURENAME(PatchList.get(i).toString());
					ccp.setCONFIGURETYPE("补丁");
					CurrentConfigureHelper.saveCurrentConfigure(ccp);
				}
				//添加软件信息
				for(int i=0;i<SoftList.size();i++){
					String[] soft = SoftList.get(i).toString().split(",");
					String softname = soft[0];
					String softversion = soft[1];
					CurrentConfigurePersistence ccp = new CurrentConfigurePersistence();
					UUID uuid2 = UUID.randomUUID();
					ccp.setCURRENTCONFIGUREID(uuid2.toString());
					ccp.setEQUIPMENTID(uuid.toString());
					ccp.setCONFIGUREVERSION(softversion);
					ccp.setCONFIGURENAME(softname);
					ccp.setCONFIGURETYPE("软件");
					CurrentConfigureHelper.saveCurrentConfigure(ccp);
				}
				//添加驱动
				CurrentConfigureHelper.saveCurrentConfigure_driver(UUID.randomUUID().toString(), uuid.toString(), GraphicCard[1], GraphicCard[0], "驱动");
				CurrentConfigureHelper.saveCurrentConfigure_driver(UUID.randomUUID().toString(), uuid.toString(), AudioCard[1], AudioCard[0], "驱动");
				if (NetwordCard1[0].length()>1) {
					CurrentConfigureHelper.saveCurrentConfigure_driver(UUID.randomUUID().toString(), uuid.toString(), NetwordCard1[1], NetwordCard1[0], "驱动");
				}
				if (NetwordCard2[0].length()>1) {
					CurrentConfigureHelper.saveCurrentConfigure_driver(UUID.randomUUID().toString(), uuid.toString(), NetwordCard2[1], NetwordCard2[0], "驱动");
				}
				CurrentConfigureHelper.saveCurrentConfigure_driver(UUID.randomUUID().toString(), uuid.toString(), Storage[1], Storage[0], "驱动");
			}else {
				CurrentEquipmentPersistence cep = new CurrentEquipmentPersistence();
				cep.setEQUIPMENTID(list2.get(0).getEQUIPMENTID());
				cep.setMACADDRESS(macaddress);
				cep.setEQUIPMENTMODEL(equipmentmodel);
				cep.setEQUIPMENTTIME(equipmenttime);
				cep.setRAM(ram);
				cep.setHARDDRIVER(harddriver);
				cep.setIP(ip);
				cep.setCPU(cpu);
				cep.setSTORAGE(storage);
				cep.setNETWORKCARD(network1);
				cep.setNETWORKCARD2(netword2);
				cep.setMOTHERBOARD(motherboard);
				cep.setOSNAME(osname);
				cep.setOSTYPE(ostype);
				cep.setOSVERSION(osversion);
				cep.setOSID(osid);
				cep.setUSERID(userPersistences.get(0).getUSERID());
				cep.setISNOTICE(0);
				cep.setGRAPHICCARD(graphiccard);
				cep.setAUDIOCARD(audiocard);
				Date date=new Date();
		        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String time = format.format(date);
		        cep.setTIMEREMARKS(time);
				CurrentEquipmentHelp.update(cep);
				//添加补丁信息
				for(int i=0;i<PatchList.size();i++){
					CurrentConfigurePersistence ccp = new CurrentConfigurePersistence();
					UUID uuid2 = UUID.randomUUID();
					ccp.setCURRENTCONFIGUREID(uuid2.toString());
					ccp.setEQUIPMENTID(list2.get(0).getEQUIPMENTID());
					ccp.setCONFIGUREVERSION("");
					ccp.setCONFIGURENAME(PatchList.get(i).toString());
					ccp.setCONFIGURETYPE("补丁");
					CurrentConfigureHelper.updataCurrentConfigure(ccp);
				}
				//添加软件信息
				for(int i=0;i<SoftList.size();i++){
					String[] soft = SoftList.get(i).toString().split(",");
					String softname = soft[0];
					String softversion = soft[1];
					CurrentConfigurePersistence ccp = new CurrentConfigurePersistence();
					UUID uuid2 = UUID.randomUUID();
					ccp.setCURRENTCONFIGUREID(uuid2.toString());
					ccp.setEQUIPMENTID(list2.get(0).getEQUIPMENTID());
					ccp.setCONFIGUREVERSION(softversion);
					ccp.setCONFIGURENAME(softname);
					ccp.setCONFIGURETYPE("软件");
					CurrentConfigureHelper.updataCurrentConfigure(ccp);
				}
				//添加驱动
				CurrentConfigureHelper.updateCurrentConfigure_driver(UUID.randomUUID().toString(), list2.get(0).getEQUIPMENTID(), GraphicCard[1], GraphicCard[0], "驱动");
				CurrentConfigureHelper.updateCurrentConfigure_driver(UUID.randomUUID().toString(), list2.get(0).getEQUIPMENTID(), AudioCard[1], AudioCard[0], "驱动");
				if (NetwordCard1[0].length()>1) {
					CurrentConfigureHelper.updateCurrentConfigure_driver(UUID.randomUUID().toString(), list2.get(0).getEQUIPMENTID(), NetwordCard1[1], NetwordCard1[0], "驱动");
				}
				if (NetwordCard2[0].length()>1) {
					CurrentConfigureHelper.updateCurrentConfigure_driver(UUID.randomUUID().toString(), list2.get(0).getEQUIPMENTID(), NetwordCard2[1], NetwordCard2[0], "驱动");
				}
				CurrentConfigureHelper.updateCurrentConfigure_driver(UUID.randomUUID().toString(), list2.get(0).getEQUIPMENTID(), Storage[1], Storage[0], "驱动");
			}
		}

	}
	/*
	 * zyq_personal3_设备信息展示
	 */
	public static List<Personal3_EquipmentView> personal3_EquipmentView(String email){
		List<UserPersistence> uList = UserHelper.getEmail(email);
		List<CurrentEquipmentPersistence> list = CurrentEquipmentHelp.currentEquipmentByID(uList.get(0).getUSERID());
		
		return null;
		
	}
}
