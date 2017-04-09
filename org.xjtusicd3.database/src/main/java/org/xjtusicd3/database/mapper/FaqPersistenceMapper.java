package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.FaqPersistence;
import org.xjtusicd3.database.model.UserPersistence;

public interface FaqPersistenceMapper extends IBaseDao<FaqPersistence, String>{
	/*
	 * robot-分类
	 */
	@Select("SELECT faq.faqId,faq.faqTitle FROM faq,classify WHERE faq.faqClassify = classify.classifyId AND faq.faqClassify=#{0} ORDER BY faq.faqCollection DESC LIMIT 4")
	public List<FaqPersistence> SecondClassify_robot(int paramString);
	/*
	 * faq2_知识列表
	 */
	@Select("SELECT * FROM faq WHERE faqClassify=#{0} LIMIT #{1},5")
	public List<FaqPersistence> faqlist_faq2(int param1,int param2);
	@Select("SELECT * FROM user WHERE userId=#{0}")
	public List<UserPersistence> userlist_faq2(int userId);
	@Select("SELECT COUNT(*) FROM faq WHERE faqClassify=#{0}")
	public int pageTotal(int faqClassify);
	/*
	 * faq3_知识内容
	 */
	@Select("SELECT * FROM faq WHERE faqId=#{0}")
	public List<FaqPersistence> faqcontent_faq3(int faqId);
	/*
	 * faq3_根据知识ID找类型classify
	 */
	@Select("SELECT faqClassify FROM faq WHERE faqId=#{0}")
	public int faqclassify(int faqId);
}
