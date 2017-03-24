package org.xjtusicd3.parnter.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class DriverSpider implements PageProcessor {
	
    public static final String URL_LIST = "/search-\\w+-\\w+/";
    public static final String URL_LIST2 = "/search-\\w+-\\w+/\\w+-0-0-1-0-1.htm";
    public static final String URL_POST = "/drivers/\\w+_\\w+.htm";

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
    	if (page.getHtml().xpath("//div[@class='cjpp']").match()) {
        	page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
		}
    	else if (page.getHtml().xpath("//div[@class='newslis']").match()) {
    		page.addTargetRequests(page.getHtml().links().regex(URL_LIST2).all());
		}
    	else if (page.getHtml().xpath("//div[@class='pxv_box font_blue']").match()) {
    		page.addTargetRequests(page.getHtml().links().regex(URL_POST).all());
		}else{
			page.putField("dirvers_name", page.getHtml().xpath("//div[@class='t']/text()").toString());
		}
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new DriverSpider()).addUrl("http://drivers.mydrivers.com/search-1/").thread(5).run();
    }
}