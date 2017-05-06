package org.xjtusicd3.partner.service;

import org.xjtusicd3.partner.filter.SystemDriver;

public class EquipmentService {
	/*
	 * zyq_ajax_实时获取当前设备资源
	 */
	public static void getCurrentEquipment(){
		SystemDriver systemDriver = new SystemDriver();
		String computermodel = systemDriver.getEquipmentModel();
		String cpu = systemDriver.getCPU();
	}
}
