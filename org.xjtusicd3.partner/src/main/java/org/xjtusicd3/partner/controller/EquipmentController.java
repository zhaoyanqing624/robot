package org.xjtusicd3.partner.controller;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hyperic.sigar.SigarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.partner.service.EquipmentService;
import org.xjtusicd3.partner.view.Personal3_EquipmentView;


@Controller
public class EquipmentController {
	/**
	 * author:zhaoyanqing
	 * date:2017年9月12日 10:35:22
	 * abstract:展示personal3当前设备信息
	 */
	@RequestMapping(value="personal3",method=RequestMethod.GET)
	public ModelAndView personal3(HttpSession session,HttpServletRequest request,String e) throws UnknownHostException, SocketException, SigarException {
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		ModelAndView mv = new ModelAndView("personal3");
		List<Personal3_EquipmentView> list = EquipmentService.personal3_EquipmentView(username,e);
		mv.addObject("personal3_list", list);
		return mv;
	}
	/**
	 * author:zhaoyanqing
	 * date:2017年9月6日 17:22:51
	 * abstract:获取客户端的信息
	 */
	@ResponseBody
	@RequestMapping(value={"/getInformation"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public void getInformation(HttpServletRequest request,HttpSession session,final String[] softWare,final String[] path){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String macAddress = request.getParameter("macAddress");
		String ipAddress = request.getParameter("ipAddress");
		String equipmentModel = request.getParameter("equipmentModel");
		String equipmentTime = request.getParameter("equipmentTime");
		String CPU = request.getParameter("CPU");
		String RAM = request.getParameter("RAM");
		String memoryBank = request.getParameter("memoryBank");
		String hardDrive = request.getParameter("hardDrive");
		String hardDriveModel = request.getParameter("hardDriveModel");
		String networkCard = request.getParameter("networkCard");
		String motherBoard = request.getParameter("motherBoard");
		String osName = request.getParameter("osName");
		String osType = request.getParameter("osType");
		String osVersion = request.getParameter("osVersion");
		String osId = request.getParameter("osId");
		String graphicCard = request.getParameter("graphicCard");
		String audioCard = request.getParameter("audioCard");
		//硬件信息
		EquipmentService.currentEquipment(username,macAddress,ipAddress,equipmentModel,equipmentTime,CPU,RAM,memoryBank,
				hardDrive,hardDriveModel,networkCard,motherBoard,osName,osType,osVersion,osId,graphicCard,audioCard);
		//软件、补丁信息
		EquipmentService.currentEquipment(macAddress,softWare,path);
	}

}
