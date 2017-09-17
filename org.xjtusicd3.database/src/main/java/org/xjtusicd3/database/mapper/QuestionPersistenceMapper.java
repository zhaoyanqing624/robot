package org.xjtusicd3.database.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;

public interface QuestionPersistenceMapper extends IBaseDao<QuestionPersistence, String>{
	@Select("SELECT TBL_FAQquestion.FAQQUESTIONID FROM TBL_FAQquestion WHERE TBL_FAQquestion.FAQCLASSIFYID=#{0}")
	public List<QuestionPersistence> test_(String ClassifyId);
	/*
	 * zyq_robot-分类
	 */
	@Select("SELECT TBL_FAQquestion.FAQQUESTIONID,TBL_FAQquestion.FAQTITLE FROM TBL_FAQquestion WHERE TBL_FAQquestion.FAQCLASSIFYID=#{0} ORDER BY TBL_FAQquestion.COLLECTION DESC LIMIT 4 ")
	public List<QuestionPersistence> SecondClassify_robot(String ClassifyId);
	/*
	 * zyq_faq2_知识列表
	 */
	@Select("SELECT * FROM TBL_FAQquestion WHERE FAQCLASSIFYID=#{0} LIMIT #{1},5")
	public List<QuestionPersistence> faq2_faqlist(String param1,int param2);
	@Select("SELECT * FROM TBL_User WHERE USERID=#{0}")
	public List<UserPersistence> faq2_userlist(String UserId);
	
	@Select("SELECT COUNT(*) FROM TBL_FAQquestion WHERE FAQCLASSIFYID=#{0}")
	public int pageTotal(String ClassifyId);
	@Select("SELECT USERID FROM TBL_FAQanswer WHERE FAQQUESTIONID=#{0}")
	public String faq2_UserId(String QuestionId);
	/*
	 * zyq_faq3_知识内容
	 */
	@Select("SELECT * FROM TBL_FAQquestion WHERE FAQQUESTIONID=#{0}")
	public List<QuestionPersistence> faq3_faqcontent(String QuestionId);
	@Select("SELECT * FROM TBL_FAQquestion WHERE FAQTITLE=#{0}")
	public List<QuestionPersistence> faq3_faqcontent_title(String faqtitle);
	/*
	 * 
	 * zyq_faq3_根据知识ID找类型classify
	 */
	@Select("SELECT FAQCLASSIFYID FROM TBL_FAQquestion WHERE FAQQUESTIONID=#{0}")
	public String faq3_faqclassifyId(String QuestionId);
	/*
	 * zpz_
	 */
	@Select("SELECT * FROM TBL_FAQquestion LIMIT 200")
	public List<QuestionPersistence> getFaq();
	/*
	 * faqadd_校验知识是否重复增添
	 */
	@Select("SELECT * FROM TBL_FAQquestion,TBL_FAQanswer WHERE TBL_FAQquestion.FAQQUESTIONID=TBL_FAQanswer.FAQQUESTIONID AND TBL_FAQquestion.FAQTITLE=#{0} AND TBL_FAQanswer.USERID=#{1}")
	public List<QuestionPersistence> faqadd_iscurrent(String faqtitle, String userid);
	
	/*
	 * zpz_delete faq of TBL_FAQquestion
	 */
	@Delete("DELETE FROM TBL_FAQquestion WHERE TBL_FAQquestion.FAQQUESTIONID=#{0}")
	public void deleteFAQquestion(String faqQuestionId);
	
	/* 
	 * zpz_edit faq of TBL_FAQquestion
	 */
	@Update("UPDATE TBL_FAQquestion SET FAQKEYWORDS=#{1} WHERE FAQTITLE=#{0}")
	public void updateFAQquestion(String faqTitle,String faqKeyWords);
	/*
	 * zyq_personal2_查看自己是否有FAQ
	 */
	@Select("SELECT * FROM TBL_FAQquestion,TBL_FAQanswer WHERE TBL_FAQquestion.FAQQUESTIONID=TBL_FAQanswer.FAQQUESTIONID AND TBL_FAQanswer.USERID=#{0}")
	public List<QuestionPersistence> personal2_faq(String userId);
	@Select("SELECT * FROM TBL_FAQquestion,TBL_FAQanswer WHERE TBL_FAQquestion.FAQQUESTIONID=TBL_FAQanswer.FAQQUESTIONID AND TBL_FAQanswer.USERID=#{0} ORDER BY TBL_FAQquestion.MODIFYTIME DESC LIMIT #{1},#{2}")
	public List<QuestionPersistence> personal2_faq_Limit(String userId,int startNumber,int number);
	@Select("SELECT * FROM TBL_FAQquestion,TBL_FAQanswer WHERE TBL_FAQquestion.FAQQUESTIONID=TBL_FAQanswer.FAQQUESTIONID AND TBL_FAQanswer.USERID=#{0} AND STR_TO_DATE(TBL_FAQquestion.MODIFYTIME,'%Y-%m-%d %H:%i')<STR_TO_DATE(#{3},'%Y-%m-%d %H:%i') ORDER BY TBL_FAQquestion.MODIFYTIME DESC LIMIT #{1},#{2}")
	public List<QuestionPersistence> personal2_faq_Limit_Time(String userId,int startNumber,int number,String time);
	//判断是创建知识还是修改知识
	@Select("SELECT * FROM TBL_FAQquestion WHERE FAQQUESTIONID=#{0} AND MODIFYNUMBER=#{1}")
	public List<QuestionPersistence> personal2_Ismodify(String faqquestionid, String modifynumber);
 
	@Select("select count(*) from TBL_FAQquestion")
	public int FaqTotal();
 
	/*
	 * zyq_robot_查看所以的faq信息
	 */
	@Select("SELECT * FROM TBL_FAQquestion")
	public List<QuestionPersistence> getFaqTotal();

	/*
	 * zyq_faq_查看用户动态
	 */
	@Select("SELECT * FROM TBL_FAQquestion ORDER BY MODIFYTIME DESC LIMIT 5")
	public List<QuestionPersistence> faq_userDynamics();
	
	/*
	 * faq_按时间推荐_2017年9月14日21:16:18
	 */
	//@Select("SELECT FAQTITLE, MODIFYTIME, FAQDESCRIPTION  ,sum(SCAN+COLLECTION*10) as a FROM TBL_FAQquestion  ORDER BY a DESC LIMIT #{0},5")
	@Select("SELECT * FROM TBL_FAQquestion ORDER BY MODIFYTIME DESC LIMIT #{0},5")
	public List<QuestionPersistence> faq_recommend_Limit(int startnum);
	
	@Select("SELECT * FROM TBL_FAQquestion ORDER BY MODIFYTIME DESC LIMIT #{0},5")
	public List<QuestionPersistence> user_recommend_Limit(String userid, int startnum);
	
	/**
	 * author:zzl
	 * abstract:获取分类下faq具体信息
	 * data:2017年9月15日10:29:07
	 */
	@Select("SELECT TBL_FAQquestion.FAQQUESTIONID ,TBL_FAQquestion.FAQTITLE,TBL_FAQquestion.COLLECTION ,TBL_FAQquestion.SCAN, TBL_FAQquestion.MODIFYTIME,TBL_FAQquestion.FAQDESCRIPTION FROM TBL_FAQquestion ,TBL_FAQclassify WHERE TBL_FAQclassify.FAQPARENTID=#{0} AND TBL_FAQclassify.FAQCLASSIFYID = TBL_FAQquestion.FAQCLASSIFYID ORDER BY TBL_FAQquestion.MODIFYTIME DESC LIMIT #{1},5")
	public List<QuestionPersistence> questionView(String parentId,int startnum);
	
	/**
	 * author:zzl
	 * abstract:推荐知识_根据收藏量推荐前4个
	 * data:2017年9月17日19:53:48
	 */
	@Select("SELECT TBL_FAQquestion.FAQQUESTIONID ,TBL_FAQquestion.FAQTITLE,TBL_FAQquestion.FAQDESCRIPTION FROM TBL_FAQquestion ,TBL_FAQclassify WHERE TBL_FAQclassify.FAQPARENTID=#{0} AND TBL_FAQclassify.FAQCLASSIFYID = TBL_FAQquestion.FAQCLASSIFYID ORDER BY TBL_FAQquestion.COLLECTION DESC LIMIT 4")
	public List<QuestionPersistence> faqInfo_limit(String faqParentId);
}
