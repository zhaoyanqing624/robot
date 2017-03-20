package org.xjtusicd3.parnter.spider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xjtusicd3.database.helper.SoftSpiderHelper;
import org.xjtusicd3.database.model.SoftSpiderPersistence;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class SoftSpider implements PageProcessor {

    public static final String URL_LIST = "http://rj\\.baidu\\.com/soft/lists/1/\\a+";

    public static final String URL_POST = "http://rj\\.baidu\\.com/soft/detail/\\w+\\.html";

    private Site site = Site.me().setRetryTimes(5).setSleepTime(1500);

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
	    			for(int x = 1;x <= 19;x++){
		    			for(int j = 1;j <= softpage;j++){
		    				page.addTargetRequest("http://rj.baidu.com/soft/lists/"+x+"/"+j);
		    				page.addTargetRequest("http://rj.baidu.com/soft/detail/"+soft_id+".html");
		    			}
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
	    		String soft_desc = detail.getString("soft_desc");
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
	    		String developer_name = detail.getString("developer_name");
	    		String download_num = detail.getString("download_num");
	    		JSONObject os_type1 = detail.getJSONObject("os_type");
	    		String os_10 = "";
	    		String os_100 = "";
	    		String os_1000 = "";
	    		String os_10000 = "";
	    		String os_100000 = "";
	    		String os_1000000 = "";
	    		String os_10000000 = "";
	    		if(os_type1.containsKey("10")){
	    			 os_10 = os_type1.getString("10"); 
	    		}
	    		if(os_type1.containsKey("100")){
	    			 os_100 = os_type1.getString("100"); 
	    		}
	    		if(os_type1.containsKey("1000")){
	    			 os_1000 = os_type1.getString("1000"); 
	    		}
	    		if(os_type1.containsKey("10000")){
	    			 os_10000 = os_type1.getString("10000"); 
	    		}
	    		if(os_type1.containsKey("100000")){
	    			 os_100000 = os_type1.getString("100000"); 
	    		}
	    		if(os_type1.containsKey("1000000")){
	    			 os_1000000 = os_type1.getString("1000000"); 
	    		}
	    		if(os_type1.containsKey("10000000")){
	    			 os_10000000 = os_type1.getString("10000000"); 
	    		}
	    		String os_type = os_10+os_100+os_1000+os_10000+os_100000+os_1000000+os_10000000;
	    		
	    		SoftSpiderPersistence sp = new SoftSpiderPersistence();
	    		sp.setSoft_name(soft_name);
	    		sp.setPoint(point);
	    		sp.setLogo(logo);
	    		sp.setLogo48(logo48);
	    		sp.setLogo96(logo96);
	    		sp.setOfficial_web(official_web);
	    		sp.setSoft_desc(soft_desc);
	    		sp.setSoft_desc_short(soft_desc_short);
	    		sp.setNick_version(nick_version);
	    		sp.setWhats_new_desc(whats_new_desc);
	    		sp.setWhats_new_short(whats_new_short);
	    		sp.setVersion(version);
	    		sp.setFile_size(file_size);
	    		sp.setFile_name(file_name);
	    		sp.setUpdate_time(update_time);
	    		sp.setUrl(url);
	    		sp.setPic_path(pic_path);
	    		sp.setClass_name(class_name);
	    		sp.setReurl(reurl);
	    		sp.setDeveloper_name(developer_name);
	    		sp.setDownload_num(download_num);
	    		sp.setOs_type(os_type);
	    		SoftSpiderHelper.sava(sp);
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