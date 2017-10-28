package org.xjtusicd3.portal.controller;

import java.util.List;

import org.apache.commons.io.output.ThresholdingOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.portal.service.CommunityQuestionService;
import org.xjtusicd3.portal.service.FaqService;
import org.xjtusicd3.portal.view.KnowledgeindexView;
import org.xjtusicd3.portal.view.ProblemindexView;


@Controller
public class CommunityQuestionController {

	/*
	 * zpz_get community question
	 */
	@RequestMapping(value="problemindex",method=RequestMethod.GET)
	public ModelAndView getCommunityQuestion()
	{
		ModelAndView mv = new ModelAndView("problemindex");
		List<ProblemindexView> communityquestionlist = CommunityQuestionService.problemindexViews();
		mv.addObject("cqlist",communityquestionlist);
		//zzl_2017年10月11日19:59:49_未解决
		List<ProblemindexView> communityquestionlist2 = CommunityQuestionService.problemindexViews2();
		mv.addObject("cqlist1",communityquestionlist2);
		return mv;
		
	}

	/*
	 * zpz_showFAQInfoDetail
	 */
	@RequestMapping(value="showProblem",method=RequestMethod.GET)
	public ModelAndView showProblem(String u){
		List<ProblemindexView> problemindexViews = CommunityQuestionService.getCommunityQuestionById(u);
		ModelAndView modelAndView = new ModelAndView("showProblem");
		modelAndView.addObject("problemList", problemindexViews);
		return modelAndView;
	}
	 
	/**
	 * author:
	 * abstract:社区问题列表显示_有最佳答案
	 * data:2017年10月12日17:46:34
	 */
	@RequestMapping(value="showProblemInfo",method=RequestMethod.GET)
    public ModelAndView  showProblemInfo(){
 	   ModelAndView mv=new ModelAndView("showProblemInfo");
 	   return mv;
    }
	
}
