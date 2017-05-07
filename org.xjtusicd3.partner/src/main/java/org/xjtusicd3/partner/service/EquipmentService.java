package org.xjtusicd3.partner.service;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import org.xjtusicd3.database.helper.EquipmentHelper;
import org.xjtusicd3.database.model.EquipmentPersistence;
import org.xjtusicd3.partner.filter.LOCALMAC;
import org.xjtusicd3.partner.filter.SystemDriver;

public class EquipmentService {
	/*
	 * zyq_ajax_实时获取当前设备资源
	 */
	public static void getCurrentEquipment() throws UnknownHostException, SocketException{
		LOCALMAC localmac = new LOCALMAC();
		String macaddress = localmac.getMacAddress();
		List<EquipmentPersistence> list = EquipmentHelper.getEquipmentList(macaddress);
		//如何为本地配置组
		if (list.size()!=0) {
			String buytime = list.get(0).getBUYTIME();
			String equipmentimage = list.get(0).getEQUIPMENTIMAGE();
			SystemDriver systemDriver = new SystemDriver();
			String computermodel = systemDriver.getEquipmentModel();
			String cpu = systemDriver.getCPU();
			String[] GraphicCard = systemDriver.getGraphicCard().split(",");
			String graphiccard = GraphicCard[0];
			String[] AudioCard = systemDriver.getAudioCard().split(",");
			String audiocard = AudioCard[0];
			String[] Storage = systemDriver.getStorage().split(",");
			String storage = Storage[0];
			String[] Mainboard = systemDriver.getMotherBoard().split(",");
			String mainboard = Mainboard[0];
			String[] NetwordCard1 = systemDriver.getNetworkCard1().split(",");
			String network1 = NetwordCard1[0];
			String osid = systemDriver.getOSID();		
 			System.out.println(network1);
		}
		//如果不是本地配置组
		else {
			
		}

	}
	public static void main(String[] args) throws UnknownHostException, SocketException {
		getCurrentEquipment();
	}
}
