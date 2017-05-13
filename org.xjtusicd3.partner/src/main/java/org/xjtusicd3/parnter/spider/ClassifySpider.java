package org.xjtusicd3.parnter.spider;


import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.ForClosure;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;
import java.util.UUID;

public class ClassifySpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Override
    public void process(Page page) {
    	//第一层分类
    	List<String> classifyName = new JsonPathSelector("$.datas[*].firstKnowledgeName").selectList(page.getRawText());  
    	if (CollectionUtils.isNotEmpty(classifyName)) {
    		for (String classifyNameFirst : classifyName) {
    			UUID uuid = UUID.randomUUID();
    			ClassifyPersistence classifyPersistence = new ClassifyPersistence();
    			classifyPersistence.setFAQCLASSIFYID(uuid.toString());
    			classifyPersistence.setFAQCLASSIFYNAME(classifyNameFirst);
    			classifyPersistence.setFAQPARENTID("0");
    			try {
					ClassifyHelper.save(classifyPersistence);
			    	//第二层分类
			    	List<String> classifyName2 = new JsonPathSelector("$.datas[*].category[*].category_name").selectList(page.getRawText());
			    	List<ClassifyPersistence> cList = ClassifyHelper.spider_ClassifyListByName(classifyNameFirst,"0");
			        if (CollectionUtils.isNotEmpty(classifyName2)) {
			            for (String classifyNameSecond : classifyName2) {
			            	UUID uuid2 = UUID.randomUUID();	
			            	ClassifyPersistence classifyPersistence2 = new ClassifyPersistence();
			    			classifyPersistence2.setFAQCLASSIFYID(uuid2.toString());
			    			classifyPersistence2.setFAQCLASSIFYNAME(classifyNameSecond);
			    			classifyPersistence2.setFAQPARENTID(cList.get(0).getFAQCLASSIFYID());
			    			try {
								ClassifyHelper.save(classifyPersistence2);
							} catch (Exception e) {
								e.printStackTrace();
							}
			            }
			        }
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	}
    }
    @Override
    public Site getSite() {
        return site;
    }
    public static void main(String[] args) {
        Spider.create(new ClassifySpider())
//       .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=1&type=S")
//        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=2&type=S")
//        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=3&type=S")
//        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=4&type=S")
//        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=5&type=S")
//        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=6&type=S")
//        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=7&type=S")
//        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=8&type=S")
//        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=9&type=S")
//        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=10&type=S")
//        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=12&type=S")
        .addUrl("http://iknow.lenovo.com/doc/topicData?category_id=13&type=S")
        .run();
    }
}