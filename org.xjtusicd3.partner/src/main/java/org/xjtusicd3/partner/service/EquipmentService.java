package org.xjtusicd3.partner.service;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import org.hyperic.sigar.SigarException;
import org.xjtusicd3.database.helper.EquipmentHelper;
import org.xjtusicd3.database.model.EquipmentPersistence;
import org.xjtusicd3.partner.filter.LOCALMAC;
import org.xjtusicd3.partner.filter.Similarity;
import org.xjtusicd3.partner.filter.SystemDriver;
import org.xjtusicd3.partner.filter.SystemPatch;
import org.xjtusicd3.partner.filter.SystemSigar;
import org.xjtusicd3.partner.filter.SystemSoftware;

public class EquipmentService {
	/*
	 * zyq_ajax_实时获取当前设备资源
	 */
	public static String getCurrentEquipment(String email) throws UnknownHostException, SocketException, SigarException{
		LOCALMAC localmac = new LOCALMAC();
		String macaddress = localmac.getMacAddress();
		List<EquipmentPersistence> list = EquipmentHelper.getEquipmentList(macaddress);
		//如果为本地配置组
		if (list.size()!=0) {
			
			String buytime = list.get(0).getBUYTIME();
			String equipmentimage = list.get(0).getEQUIPMENTIMAGE();
			
			SystemDriver systemDriver = new SystemDriver();
			String[] EquipmentModel = systemDriver.getEquipmentModel().split(",");
			String equipmentmodel = EquipmentModel[0];
			String cpu = systemDriver.getCPU();
			String[] GraphicCard = systemDriver.getGraphicCard().split(",");
			String graphiccard = GraphicCard[0];
			String[] AudioCard = systemDriver.getAudioCard().split(",");
			String audiocard = AudioCard[0];
			String[] Storage = systemDriver.getStorage().split(",");
			String storage = Storage[0];
			String[] Mainboard = systemDriver.getMotherBoard().split(",");
			String mainboard = Mainboard[0]+" "+Mainboard[1];
			String[] NetwordCard1 = systemDriver.getNetworkCard1().split(",");
			String network1 = NetwordCard1[0];
			String osid = systemDriver.getOSID();
			
			SystemSigar systemSigar = new SystemSigar();
			String ram = systemSigar.memory();
			String harddriver = systemSigar.file();
			String[] propery = systemSigar.property().split(",");
			String ip = propery[0];
			String osname = propery[1];
			
			SystemPatch systemPatch = new SystemPatch();
			List list1 = systemPatch.PatchList();
			
			SystemSoftware systemSoftware = new SystemSoftware();
			systemSoftware.SoftList(list.get(0).getEQUIPMENTID());
			
			//是否已添加当前列表
			
			//检验当然设备表中是否存储
			
			
		}
		//如果不是本地配置组
		else {
			
		}
		return null;

	}
	public static void main(String[] args) throws UnknownHostException, SocketException, SigarException {
		getCurrentEquipment("1134955889@qq.com");
	}
}
