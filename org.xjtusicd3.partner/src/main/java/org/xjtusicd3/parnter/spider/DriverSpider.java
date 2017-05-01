package org.xjtusicd3.parnter.spider;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xjtusicd3.database.helper.ConfigureHelper;
import org.xjtusicd3.database.helper.DriversHelper;
import org.xjtusicd3.database.model.ConfigurePersistence;
import org.xjtusicd3.database.model.DriverPersistence;

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
	    	ConfigurePersistence configurePersistence = new ConfigurePersistence();
//	    	ConfigureHistoryPersistence configureHistoryPersistence = new ConfigureHistoryPersistence();
	    	DriverPersistence driversPersistence = new DriverPersistence();
	    	List<ConfigurePersistence> list = ConfigureHelper.getConfigure(zhuanyi(page.getHtml().xpath("//div[@class='t']/text()").toString()));
	    	if (list.size()==0) {
	    	UUID uuid = UUID.randomUUID();
//	    	UUID uuid2 = uuid.randomUUID();
	    	configurePersistence.setCONFIGUREID(uuid.toString());
	    	configurePersistence.setCONFIGURENAME(zhuanyi(page.getHtml().xpath("//div[@class='t']/text()").toString()));
	    	configurePersistence.setCONFIGURETYPE("驱动");
	    	configurePersistence.setPRODUCER(page.getHtml().xpath("//div[@class='down_lb']/ul/li[2]/a/text()").toString());
	    	configurePersistence.setURL(url);
	    	configurePersistence.setFILESIZE(page.getHtml().xpath("//div[@class='down_lb']/ul/li[4]/text()").toString());
	    	configurePersistence.setDOWNLOADTIMES("0");
	    	configurePersistence.setCONFIGURETIME(page.getHtml().xpath("//div[@class='down_lb']/ul/li[3]/text()").toString());
	    	
//	    	configureHistoryPersistence.setCONFIGUREHISTORYID(uuid2.toString());
//	    	configureHistoryPersistence.setCONFIGUREID(uuid.toString());
//	    	configureHistoryPersistence.setUPDATETIME(page.getHtml().xpath("//div[@class='down_lb']/ul/li[3]/text()").toString());
	    	
	    	driversPersistence.setCONFIGUREID(uuid.toString());
	    	driversPersistence.setOS(page.getHtml().xpath("//div[@class='down_lb']/ul/li[1]/text()").toString());
	    	driversPersistence.setDRIVERTYPE(page.getHtml().xpath("//div[@class='down_lb']/ul/li[5]/a/text()").toString());
	    	driversPersistence.setFITNESS(page.getHtml().xpath("//div[@class='down_lb']/ul/li[6]/a/text()").toString());
	    	driversPersistence.setDRIVERINTRODUCTION(zhuanyi(page.getHtml().xpath("//div[@class='down_info']/text()").toString()));
			
	    	try {
				ConfigureHelper.save_Driver(configurePersistence);
//				ConfigureHistoryHelper.save_ConfigureHistory(configureHistoryPersistence);
				DriversHelper.sava(driversPersistence);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	System.out.println("++++++++++++++++++++++++++++++++++++++");
	    	}else {
				System.out.println(".........................................");
			}
		}
    }
    public static String zhuanyi(String string){
    	string = string.replace("\'", "\\'");
    	return string;
    }
    @Override
    public Site getSite() {
        return site;
    }

    public static void spider_driver(){
    	for(int i = 1;i <= 40;i++){
        	Spider.create(new DriverSpider()).addUrl("http://drivers.mydrivers.com/search-"+i+"/").thread(10).run();
        	}
    }
}