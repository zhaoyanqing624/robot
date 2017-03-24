package org.xjtusicd3.parnter.spider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class DriverSpider2 implements PageProcessor {
	
    public static final String URL_LIST = "/search-\\w+-\\w+/";
    public static final String URL_LIST2 = "/search-\\w+-\\w+/\\w+-0-0-1-0-1.htm";
    public static final String URL_POST = "/drivers/\\w+_\\w+.htm";

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {

			page.putField("dirvers_name", page.getHtml().xpath("//div[@class='t']/text()").toString());
			page.putField("os", page.getHtml().xpath("//div[@class='down_lb']/ul/li[1]/text()").toString());
			page.putField("producer", page.getHtml().xpath("//div[@class='down_lb']/ul/li[2]/a/text()").toString());
			page.putField("date", page.getHtml().xpath("//div[@class='down_lb']/ul/li[3]/text()").toString());
			page.putField("size", page.getHtml().xpath("//div[@class='down_lb']/ul/li[4]/text()").toString());
			page.putField("classify", page.getHtml().xpath("//div[@class='down_lb']/ul/li[5]/a/text()").toString());
			page.putField("fitness", page.getHtml().xpath("//div[@class='down_lb']/ul/li[6]/a/text()").toString());
			page.putField("content", page.getHtml().xpath("//div[@class='down_info']/text()").toString());
	    	Pattern p = Pattern.compile("downcurrentfile\"\\s*href=\"(.*?)\">");
	    	Matcher m = p.matcher(page.getRawText());
	    	while(m.find()){
	    		page.putField("url", m.group(1));
	    	}
	    	
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new DriverSpider2()).addUrl("http://drivers.mydrivers.com/drivers/144_57997.htm").thread(5).run();
    }
}