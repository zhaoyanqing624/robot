package org.xjtusicd3.parnter.spider;

import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

public class FAQSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    
    private static final String ARITICALE_URL = "http://iknow.lenovo\\.com/doc/topicData/\\w+";
    private static final String LIST_URL = "http://iknow.lenovo\\.com/doc/topicData.*";
    @Override
    public void process(Page page) {
        if (page.getUrl().regex(LIST_URL).match()) {
            List<String> ids = new JsonPathSelector("$.datas[*].category[*].category_id").selectList(page.getRawText());
            if (CollectionUtils.isNotEmpty(ids)) {
                for (String id : ids) {
                	for(int p=1;p<=100;p++){
                		page.addTargetRequest("http://iknow.lenovo.com/doc/docList?category_id=" + id +"&type=2&p="+p);
                	}
                }
            }
        } else {
        	List<String> title = new JsonPathSelector("$.docList[*].title").selectList(page.getRawText());
        	List<String> keywords = new JsonPathSelector("$.docList[*].keywords").selectList(page.getRawText());
        	List<String> description = new JsonPathSelector("$.docList[*].description").selectList(page.getRawText());
        	List<String> content = new JsonPathSelector("$.docList[*].content").selectList(page.getRawText());
        	if (CollectionUtils.isNotEmpty(title)) {
        		for(int i = 0;i<title.size();i++){
        			QuestionPersistence questionPersistence = new QuestionPersistence();
        			UUID uuid = UUID.randomUUID();
        			questionPersistence.setQuestionId(uuid.toString());
        			questionPersistence.setFaqTitle(title.get(i));
        			questionPersistence.setFaqDescription(description.get(i));
        			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.spider_ClassifyListByName(new JsonPathSelector("$.Category[*].subName").select(page.getRawText()));
        			for(int j = 0;j<classifyPersistences.size();j++){
        				questionPersistence.setClassify(classifyPersistences.get(j).getClassifyId());
        			}
        			questionPersistence.setFaqKeywords(keywords.get(i));
        			AnswerPersistence answerPersistence = new AnswerPersistence();
        			UUID uuid2 = UUID.randomUUID();
        			answerPersistence.setAnswerId(uuid2.toString());
        			answerPersistence.setQuestionId(uuid.toString());
        			answerPersistence.setFaqContent(zhuanyi(content.get(i)));
        			answerPersistence.setAnswerUserId("4fb6be09-8a2b-4a33-8b29-e9e51b071856");
					try {
						QuestionHelper.save(questionPersistence);
						AnswerHelper.save(answerPersistence);
						System.out.println("-----------------------------------");
					} catch (Exception e) {
						e.printStackTrace();
					}
//        			System.out.println("title:"+title.get(i));
//        			System.out.println("description:"+description.get(i));
//        			System.out.println("classify:"+new JsonPathSelector("$.Category[*].subName").select(page.getRawText()));
//        			System.out.println("keywords:"+keywords.get(i));
//        			System.out.println("content:"+content.get(i));
				}
			}
        }

    }
    @Override
    public Site getSite() {
        return site;
    }
    
    
    public static String zhuanyi(String string){
    	string = string.replace("\'", "\\'");
    	return string;
    }
    public static void main(String[] args) {
        Spider.create(new FAQSpider())
        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=1&type=S")
        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=2&type=S")
        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=3&type=S")
        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=4&type=S")
        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=5&type=S")
        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=6&type=S")
        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=7&type=S")
        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=8&type=S")
        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=9&type=S")
        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=10&type=S")
        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=12&type=S")
        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=13&type=S")
        .run();
    }
}