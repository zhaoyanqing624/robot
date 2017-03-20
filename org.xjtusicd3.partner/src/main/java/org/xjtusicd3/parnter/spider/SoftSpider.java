package org.xjtusicd3.parnter.spider;

import java.awt.JobAttributes;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.Scheduler;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.selector.Selectable;

public class SoftSpider implements PageProcessor {

    public static final String URL_LIST = "http://rj\\.baidu\\.com/soft/lists/1/\\a+";

    public static final String URL_POST = "http://rj\\.baidu\\.com/soft/detail/\\w+\\.html";

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
    	if (page.getHtml().xpath("//div[@id='list']").match()){
	    	Pattern p = Pattern.compile("var\\s+configs\\s*=\\s*(.*);");
	    	Matcher m = p.matcher(page.getRawText());
	    	while(m.find()){
	    		JSONObject object = JSONObject.fromObject(m.group(1));
	    		JSONArray softlist = object.getJSONObject("data").getJSONObject("softList").getJSONArray("list");
	    		int softpage = object.getJSONObject("data").getJSONObject("page").getInt("totalP");
	    		for(int i = 0; i < softlist.size(); i++){
	    			JSONObject jo = softlist.getJSONObject(i);
	    			int soft_id = jo.getInt("soft_id");
	    			for(int j = 2;j <= softpage;j++){
	    				page.addTargetRequest("http://rj.baidu.com/soft/lists/1/"+j);
	    				page.addTargetRequest("http://rj.baidu.com/soft/detail/"+soft_id+".html");
	    			}
	    		}
	    	}
    	}
    	if(page.getHtml().xpath("//div[@id='detail']").match()){
	    	Pattern p = Pattern.compile("var\\s+configs\\s*=\\s*(.*);");
	    	Matcher m = p.matcher(page.getRawText());
	    	while(m.find()){
	    		JSONObject object = JSONObject.fromObject(m.group(1));
	    		JSONObject detail = object.getJSONObject("data").getJSONObject("softInfo");
	    		String soft_name=detail.getString("soft_name");
	    		int point = detail.getInt("point");
	    		String logo = detail.getString("logo");
	    		String logo48 = detail.getString("logo48");
	    		String logo96 = detail.getString("logo96");
	    		String official_web = detail.getString("official_web");
	    		String soft_desc = detail.getString("sofo_desc");
	    		String soft_desc_short = detail.getString("soft_desc_short");
	    		String nick_version = detail.getString("nick_version");
	    		String whats_new_desc = detail.getString("whats_new_desc");
	    		String whats_new_short = detail.getString("whats_new_short");
	    		String version = detail.getString("version");
	    		String file_size = detail.getString("file_size");
	    		String file_name = detail.getString("file_name");
	    		String update_time = detail.getString("update_time");
	    		String url = detail.getString("url");
	    		String pic_path = detail.getString("pic_path");
	    		String class_name = detail.getString("class_name");
	    		String reurl = detail.getString("reurl");
	    		String developer_name = detail.getJSONObject("pic_path_arr").getString("developer_name");
	    		String download_num = detail.getJSONObject("pic_path_arr").getString("download_num");
	    		System.out.println(soft_name);
	    		System.err.println(point);
	    	}
		}
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
	        Spider.create(new SoftSpider())
	        .addUrl("http://rj.baidu.com/soft/lists/1/1")
	        .run();
    }
}