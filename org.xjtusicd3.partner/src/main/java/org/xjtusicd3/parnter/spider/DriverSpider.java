package org.xjtusicd3.parnter.spider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xjtusicd3.database.helper.DriversSpiderHelper;
import org.xjtusicd3.database.model.DriversSpiderPersistence;

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
    	String type = "";
    	if (page.getHtml().xpath("//div[@class='cjpp']").match()) {
        	page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
		}
    	else if (page.getHtml().xpath("//div[@class='newslis']").match()) {
    		page.addTargetRequests(page.getHtml().links().regex(URL_LIST2).all());
		}
    	else if (page.getHtml().xpath("//div[@class='pxv_box font_blue']").match()) {
    		page.addTargetRequests(page.getHtml().links().regex(URL_POST).all());
		}else{
			String url="";
	    	Pattern p = Pattern.compile("downcurrentfile\"\\s*href=\"(.*?)\">");
	    	Matcher m = p.matcher(page.getRawText());
	    	while(m.find()){
	    		url = m.group(1);
	    	}
			DriversSpiderPersistence ds = new DriversSpiderPersistence();
			ds.setDriverName(page.getHtml().xpath("//div[@class='t']/text()").toString());
			ds.setDriverOs(page.getHtml().xpath("//div[@class='down_lb']/ul/li[1]/text()").toString());
			ds.setDriverProducer(page.getHtml().xpath("//div[@class='down_lb']/ul/li[2]/a/text()").toString());
			ds.setDriverDate(page.getHtml().xpath("//div[@class='down_lb']/ul/li[3]/text()").toString());
			ds.setDriverSize(page.getHtml().xpath("//div[@class='down_lb']/ul/li[4]/text()").toString());
			ds.setDriverClassify(page.getHtml().xpath("//div[@class='down_lb']/ul/li[5]/a/text()").toString());
			ds.setDriverFitness(page.getHtml().xpath("//div[@class='down_lb']/ul/li[6]/a/text()").toString());
			ds.setDriverContent(page.getHtml().xpath("//div[@class='down_info']/text()").toString());
			ds.setDriverUrl(url);
			DriversSpiderHelper.sava(ds);
		}
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
    	for(int i = 1;i <= 40;i++){
        	Spider.create(new DriverSpider()).addUrl("http://drivers.mydrivers.com/search-"+i+"/").thread(10).run();
        	}
    }
}