package org.xjtusicd3.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.portal.service.FaqService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
public class FaqController {
	/*
	 * zpz_faq_²é¿´faq
	 */
	@RequestMapping(value="knowledgeindex",method=RequestMethod.GET)
    public ModelAndView  knowledge(){
 	   ModelAndView mv=new ModelAndView("knowledgeindex");
 	   String result = FaqService.getFaq();
 	   mv.addObject("result", result);
 	   return mv;
    }

}
