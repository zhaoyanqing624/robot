package org.xjtusicd3.parnter.spider;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xjtusicd3.database.helper.PatchSpiderHelper;
import org.xjtusicd3.database.model.PatchSpiderPersistence;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class PatchSpider implements PageProcessor {
    public static final String URL_LIST = "/list/716_3_\\w+.html";
    public static final String URL_POST = "/content/\\w+.html";

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
	    if(page.getHtml().xpath("div[@class='soft_list']").match()){
	    	//page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
	    	page.addTargetRequests(page.getHtml().xpath("//[@class='soft_list']").links().regex(URL_POST).all());
	    }else{
	    	String patchName="";
	    	String patchOs="";
	    	String patchProducer="";
	    	String patchDate="";
	    	String patchSize="";
	    	String patchKeyword="";
	    	String patchLanguage="";
	    	String patchContent="";
	    	String patchURL="";
	    	patchName =  page.getHtml().xpath("//*[@id='cs_m']/div[@class='b_cmon']/h1/text()").toString();
	    	Pattern p = Pattern.compile("发布公司：(.*?)<");
	    	Matcher m = p.matcher(page.getRawText());
	    	while(m.find()){
	    		patchProducer = m.group(1);
	    	}
	    	Pattern p2 = Pattern.compile("操作系统：(.*?)<");
	    	Matcher m2 = p2.matcher(page.getRawText());
	    	while(m2.find()){
	    		patchOs = m2.group(1);
	    	}
	    	Pattern p3 = Pattern.compile("软件语言：(.*?)<");
	    	Matcher m3 = p3.matcher(page.getRawText());
	    	while(m3.find()){
	    		patchLanguage = m3.group(1);
	    	}
	    	Pattern p4 = Pattern.compile("软件大小：(.*?)<");
	    	Matcher m4 = p4.matcher(page.getRawText());
	    	while(m4.find()){
	    		patchSize = m4.group(1);
	    	}
	    	Pattern p5 = Pattern.compile("更新日期：(.*?)<");
	    	Matcher m5 = p5.matcher(page.getRawText());
	    	while(m5.find()){
	    		patchDate = m5.group(1);
	    	}
	    	Pattern p6 = Pattern.compile("f_name=(.*?)\"");
	    	Matcher m6 = p6.matcher(page.getRawText());
	    	while(m6.find()){
	    		patchKeyword = m6.group(1);
	    	}
	    	Pattern p7 = Pattern.compile("广告位结束\\s*-->\\r*\\n*\\s*([\\s\\S]*?)<div\\s*class=\"pd\"");
	    	Matcher m7 = p7.matcher(page.getRawText());
	    	while(m7.find()){
	    		patchContent = m7.group(1);
	    	}
	    	Pattern p8 = Pattern.compile("43,(.*?)]");
	    	Matcher m8 = p8.matcher(page.getRawText());
	    	while(m8.find()){
	    		patchURL = "http://down.tech.sina.com.cn/download/d_load.php?d_id="+m8.group(1)+"&down_id=2";
	    	}
	    	PatchSpiderPersistence ps = new PatchSpiderPersistence();
	    	ps.setPatchName(patchName);
	    	ps.setPatchOs(patchOs);
	    	ps.setPatchProducer(patchProducer);
	    	ps.setPatchDate(patchDate);
	    	ps.setPatchSize(patchSize);
	    	ps.setPatchKeyword(patchKeyword);
	    	ps.setPatchLanguage(patchLanguage);
	    	ps.setPatchContent(patchContent);
	    	ps.setPatchURL(patchURL);
	    	try {
				PatchSpiderHelper.save(ps);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
    	for(int i=1;i<=919;i++)
        Spider.create(new PatchSpider()).addUrl("http://down.tech.sina.com.cn/list/716_3_"+i+".html").thread(15).run();
    }
}