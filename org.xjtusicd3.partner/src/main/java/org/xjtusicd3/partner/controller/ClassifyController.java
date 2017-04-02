package org.xjtusicd3.partner.controller;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.partner.service.ClassifyService;





@Controller
public class ClassifyController {
	/*
	 * root_分类
	 */
	@RequestMapping(value="robot",method=RequestMethod.GET)
	public ModelAndView classifyName(){
		ModelAndView modelAndView = new ModelAndView("robot");
		String string = ClassifyService.classify();
		//JSONObject object = JSONObject.fromObject(string);
		System.out.println(string);
		modelAndView.addObject("string",string);
		return modelAndView;
	}
	
//	@ResponseBody
//	@RequestMapping(value={"/getFirstLevel"},method={org.springframework.web.bind.annotation.RequestMethod.GET},produces="text/plain;charset=UTF-8")
//	public  JSONObject search(HttpServletResponse response){
//		String string = "{\"title\":\"操作系统\",\"id\":\"speedMenu12\",\"content\":[{\"title\":\"磁盘分区\",\"content\":[\"磁盘分区\", \"蓝屏死机\", \"系统安装与升级\", \"程序安装与卸载\", \"浏览器\", \"应用商店问题\", \"系统还原\"]}]}";
//		JSONObject jsonObject = JSONObject.parseObject(string);
//		System.out.println(jsonObject);
//		return jsonObject;
//	 }
}
