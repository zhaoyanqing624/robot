package org.xjtusicd3.parnter.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class FAQSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
    	//page.addTargetRequests(page.getHtml().css("div.pager-content").links().all());
    	page.putField("author", page.getHtml().xpath("//*[@id='knowledgeTopTplWrapper']/div[4]/ul/li[6]/a/text()").toString());
        //page.putField("content", page.getHtml().xpath("//div[@class='article article-text']/@data-text").toString());
        //page.putField("time", page.getHtml().xpath("//*[@id='article-40146776']/div[1]/div[2]/div/div[2]/span").toString());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new FAQSpider())
                .addUrl("http://iknow.lenovo.com/topic/c_1.html")
                //.addPipeline(new JsonFilePipeline("D:\\webmagic\\"))
                .thread(5)
                .run();
    }
}

