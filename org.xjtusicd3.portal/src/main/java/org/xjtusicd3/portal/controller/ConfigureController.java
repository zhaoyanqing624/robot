package org.xjtusicd3.portal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.model.ComputerPersistence;
import org.xjtusicd3.database.model.ConfigureHistoryPersistence;
import org.xjtusicd3.database.model.ConfigurePersistence;
import org.xjtusicd3.database.model.ServerPersistence;
import org.xjtusicd3.portal.service.ComputerService;
import org.xjtusicd3.portal.service.ConfigureService;
import org.xjtusicd3.portal.service.ServerService;
import org.xjtusicd3.portal.view.ChangeIndexView;

@Controller
public class ConfigureController {

	/*
	 * zpz_�鿴configure
	 */
	@RequestMapping(value="cfgindex",method=RequestMethod.GET)
	public ModelAndView configure() {
		ModelAndView modelAndView = new ModelAndView("cfgindex");
		List<ComputerPersistence> computerList = ComputerService.getComputer();
		List<ServerPersistence> serverList = ServerService.getServer();
		List<ConfigurePersistence> cfgList = ConfigureService.getConfigure();
		modelAndView.addObject("computerList", computerList);
		modelAndView.addObject("serverList", serverList);
		modelAndView.addObject("cfgList", cfgList);
		return modelAndView;
	}
	
	/**
	 * author:
	 * abstract:变更列表
	 * data:2017年10月12日17:46:34
	 */
	@RequestMapping(value="changeindex",method=RequestMethod.GET)
    public ModelAndView  change(){
 	   ModelAndView mv=new ModelAndView("changeindex");
 	   int startNumber = 0;
 	   List<ChangeIndexView> changeViewsList = ConfigureService.getConfigureHistory(startNumber);
 	   mv.addObject("cfg_update_list", changeViewsList);
 	   return mv;
    }
}
