package org.xjtusicd3.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	
	@RequestMapping(value="userdetail",method=RequestMethod.GET)
    public ModelAndView  userdetail(){
 	   ModelAndView mv=new ModelAndView("userdetail");
 	   return mv;
    }
	
	@RequestMapping(value="incidentindex",method=RequestMethod.GET)
    public ModelAndView  incident(){
 	   ModelAndView mv=new ModelAndView("incidentindex");
 	   return mv;
    }
	@RequestMapping(value="problemdetail",method=RequestMethod.GET)
    public ModelAndView  problemdetail(){
 	   ModelAndView mv=new ModelAndView("problemdetail");
 	   return mv;
    }
	@RequestMapping(value="problemindex",method=RequestMethod.GET)
    public ModelAndView  problem(){
 	   ModelAndView mv=new ModelAndView("problemindex");
 	   return mv;
    }
	 
	
	
	@RequestMapping(value="changeindex",method=RequestMethod.GET)
    public ModelAndView  change(){
 	   ModelAndView mv=new ModelAndView("changeindex");
 	   return mv;
    }
	@RequestMapping(value="fileindex",method=RequestMethod.GET)
    public ModelAndView  file(){
 	   ModelAndView mv=new ModelAndView("fileindex");
 	   return mv;
    }
	 
	
	@RequestMapping(value="spiderindex",method=RequestMethod.GET)
    public ModelAndView  spider(){
 	   ModelAndView mv=new ModelAndView("spiderindex");
 	   return mv;
    }
	@RequestMapping(value="incidentdetail",method=RequestMethod.GET)
    public ModelAndView  incidentdetail(){
 	   ModelAndView mv=new ModelAndView("incidentdetail");
 	   return mv;
    }
	@RequestMapping(value="incidentdetail2",method=RequestMethod.GET)
    public ModelAndView  incidentdetail2(){
 	   ModelAndView mv=new ModelAndView("incidentdetail2");
 	   return mv;
    }
	@RequestMapping(value="incidentdetail3",method=RequestMethod.GET)
    public ModelAndView  incidentdetail3(){
 	   ModelAndView mv=new ModelAndView("incidentdetail3");
 	   return mv;
    }
	@RequestMapping(value="rbacindex",method=RequestMethod.GET)
    public ModelAndView  rbac(){
 	   ModelAndView mv=new ModelAndView("rbacindex");
 	   return mv;
    }
	@RequestMapping(value="index",method=RequestMethod.GET)
    public ModelAndView  index(){
 	   ModelAndView mv=new ModelAndView("index");
 	   return mv;
    }
	@RequestMapping(value="elementindex",method=RequestMethod.GET)
    public ModelAndView  element(){
 	   ModelAndView mv=new ModelAndView("elementindex");
 	   return mv;
    }

	 @RequestMapping(value="login",method=RequestMethod.GET)
     public ModelAndView  login(){
  	   ModelAndView mv=new ModelAndView("login");
  	   return mv;
     }
	 @RequestMapping(value="register",method=RequestMethod.GET)
     public ModelAndView  register(){
  	   ModelAndView mv=new ModelAndView("register");
  	   return mv;
     }
	 @RequestMapping(value="law",method=RequestMethod.GET)
     public ModelAndView  law(){
  	   ModelAndView mv=new ModelAndView("law");
  	   return mv;
     }
	 @RequestMapping(value="forget",method=RequestMethod.GET)
     public ModelAndView  forget(){
  	   ModelAndView mv=new ModelAndView("forget");
  	   return mv;
     }
	 
	 @RequestMapping(value="event1",method=RequestMethod.GET)
     public ModelAndView  event1(){
  	   ModelAndView mv=new ModelAndView("event1");
  	   return mv;
     }
	 @RequestMapping(value="event1info",method=RequestMethod.GET)
     public ModelAndView  event1info(){
  	   ModelAndView mv=new ModelAndView("event1info");
  	   return mv;
     }
	 @RequestMapping(value="event1return",method=RequestMethod.GET)
     public ModelAndView  event1return(){
  	   ModelAndView mv=new ModelAndView("event1return");
  	   return mv;
     }
	 @RequestMapping(value="event1infochange",method=RequestMethod.GET)
     public ModelAndView  event1infochange(){
  	   ModelAndView mv=new ModelAndView("event1infochange");
  	   return mv;
     }
	 @RequestMapping(value="event2",method=RequestMethod.GET)
     public ModelAndView  event2(){
  	   ModelAndView mv=new ModelAndView("event2");
  	   return mv;
     }
	 @RequestMapping(value="event3",method=RequestMethod.GET)
     public ModelAndView  event3(){
  	   ModelAndView mv=new ModelAndView("event3");
  	   return mv;
     }
	 @RequestMapping(value="index-first",method=RequestMethod.GET)
     public ModelAndView  index_first(){
  	   ModelAndView mv=new ModelAndView("index-first");
  	   return mv;
     }
	 @RequestMapping(value="welcome",method=RequestMethod.GET)
     public ModelAndView  welcome(){
  	   ModelAndView mv=new ModelAndView("welcome");
  	   return mv;
	 }
	 @RequestMapping(value="computer1",method=RequestMethod.GET)
     public ModelAndView  computer1(){
  	   ModelAndView mv=new ModelAndView("computer1");
  	   return mv;
     }
	 @RequestMapping(value="computer1add",method=RequestMethod.GET)
     public ModelAndView  computer1add(){
  	   ModelAndView mv=new ModelAndView("computer1add");
  	   return mv;
     }
	 @RequestMapping(value="computer1info",method=RequestMethod.GET)
     public ModelAndView  computer1info(){
  	   ModelAndView mv=new ModelAndView("computer1info");
  	   return mv;
     }
	 @RequestMapping(value="computer2",method=RequestMethod.GET)
     public ModelAndView  computer2(){
  	   ModelAndView mv=new ModelAndView("computer2");
  	   return mv;
     }
	 @RequestMapping(value="computer2add",method=RequestMethod.GET)
     public ModelAndView  computer2add(){
  	   ModelAndView mv=new ModelAndView("computer2add");
  	   return mv;
     }
	 @RequestMapping(value="knowledge1",method=RequestMethod.GET)
     public ModelAndView  knowledge1(){
  	   ModelAndView mv=new ModelAndView("knowledge1");
  	   return mv;
     }
	 @RequestMapping(value="knowledge1add",method=RequestMethod.GET)
     public ModelAndView  knowledge1add(){
  	   ModelAndView mv=new ModelAndView("knowledge1add");
  	   return mv;
     }
	 @RequestMapping(value="knowledge2",method=RequestMethod.GET)
     public ModelAndView  knowledge2(){
  	   ModelAndView mv=new ModelAndView("knowledge2");
  	   return mv;
     }
	 @RequestMapping(value="knowledge3",method=RequestMethod.GET)
     public ModelAndView  knowledge3(){
  	   ModelAndView mv=new ModelAndView("knowledge3");
  	   return mv;
     }
	 @RequestMapping(value="user1",method=RequestMethod.GET)
     public ModelAndView  user1(){
  	   ModelAndView mv=new ModelAndView("user1");
  	   return mv;
     }
	 @RequestMapping(value="user1add",method=RequestMethod.GET)
     public ModelAndView  user1add(){
  	   ModelAndView mv=new ModelAndView("user1add");
  	   return mv;
     }
	 @RequestMapping(value="user2",method=RequestMethod.GET)
     public ModelAndView  user2(){
  	   ModelAndView mv=new ModelAndView("user2");
  	   return mv;
     }
	 @RequestMapping(value="user3",method=RequestMethod.GET)
     public ModelAndView  user3(){
  	   ModelAndView mv=new ModelAndView("user3");
  	   return mv;
     }
	 @RequestMapping(value="advise",method=RequestMethod.GET)
     public ModelAndView  advise(){
  	   ModelAndView mv=new ModelAndView("advise");
  	   return mv;
     }
	 @RequestMapping(value="system1",method=RequestMethod.GET)
     public ModelAndView  system1(){
  	   ModelAndView mv=new ModelAndView("system1");
  	   return mv;
     }
	 @RequestMapping(value="other1",method=RequestMethod.GET)
     public ModelAndView  other1(){
  	   ModelAndView mv=new ModelAndView("other1");
  	   return mv;
     }
	 @RequestMapping(value="robot1",method=RequestMethod.GET)
     public ModelAndView  robot1(){
  	   ModelAndView mv=new ModelAndView("robot1");
  	   return mv;
     }
	 @RequestMapping(value="spider1",method=RequestMethod.GET)
     public ModelAndView  spider1(){
  	   ModelAndView mv=new ModelAndView("spider1");
  	   return mv;
     }
	
}
