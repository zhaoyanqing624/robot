package org.xjtusicd3.portal.controller;

import java.util.List;

import org.apache.commons.io.output.ThresholdingOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.portal.service.CommunityQuestionService;


@Controller
public class CommunityQuestionController {

	/*
	 * zpz_get community question
	 */
	@RequestMapping(value="problemindex",method=RequestMethod.GET)
	public ModelAndView getCommunityQuestion()
	{
		ModelAndView mv = new ModelAndView("problemindex");
		List<CommunityQuestionPersistence> communityquestionlist = CommunityQuestionService.getAllCommunityQuestion();
		mv.addObject("cqlist",communityquestionlist);
		return mv;
		
	}
	 
}
