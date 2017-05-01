package org.xjtusicd3.parnter.spider;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xjtusicd3.database.helper.ConfigureHelper;
import org.xjtusicd3.database.helper.ConfigureHistoryHelper;
import org.xjtusicd3.database.helper.SoftHelper;
import org.xjtusicd3.database.model.ConfigureHistoryPersistence;
import org.xjtusicd3.database.model.ConfigurePersistence;
import org.xjtusicd3.database.model.SoftPersistence;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class SoftSpider implements PageProcessor {

    public static final String URL_LIST = "http://rj\\.baidu\\.com/soft/lists/1/\\a+";

    public static final String URL_POST = "http://rj\\.baidu\\.com/soft/detail/\\w+\\.html";

    private Site site = Site.me().setRetryTimes(5).setSleepTime(1000);

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
	    		String ConfigureName = detail.getString("soft_name");
	    		String ConfigureProducer = detail.getString("developer_name");
	    		String ConfigureDate = detail.getString("update_time");
	    		String ConfigureURL = detail.getString("url");
	    		String ConfigureSize = detail.getString("file_size");
	    		
	    		
	    		String SoftId = detail.getString("soft_id");
	    		String SoftScore = detail.getString("point");
	    		String Logo = detail.getString("logo");
	    		String Offical_website = detail.getString("official_web");
	    		String Soft_desc = detail.getString("soft_desc");
	    		String Soft_desc_short = detail.getString("soft_desc_short");
	    		String Nick_version = detail.getString("nick_version");
	    		String Whats_new_desc = detail.getString("whats_new_desc");
	    		String Whats_new_desc_short = detail.getString("whats_new_short");
	    		String Version = detail.getString("version");
	    		String ClassifyName = detail.getString("class_name");
	    		String ReURL = detail.getString("reurl");
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
	    		String OS_type = os_10+os_100+os_1000+os_10000+os_100000+os_1000000+os_10000000;
	    		UUID uuid = UUID.randomUUID();
	    		UUID uuid2 = UUID.randomUUID();
	    		ConfigurePersistence configurePersistence = new ConfigurePersistence();
	    		ConfigureHistoryPersistence configureHistoryPersistence = new ConfigureHistoryPersistence();
	    		SoftPersistence softPersistence = new SoftPersistence();
	    		
	    		configurePersistence.setCONFIGUREID(uuid.toString());
	    		configurePersistence.setCONFIGURENAME(ConfigureName);
	    		configurePersistence.setCONFIGURETYPE("软件");
	    		configurePersistence.setPRODUCER(ConfigureProducer);
	    		configurePersistence.setURL(ConfigureURL);
	    		configurePersistence.setFILESIZE(ConfigureSize);
	    		configurePersistence.setDOWNLOADTIMES("0");
	    		
	    		configureHistoryPersistence.setCONFIGUREHISTORYID(uuid2.toString());
	    		configureHistoryPersistence.setCONFIGUREID(uuid.toString());
	    		configureHistoryPersistence.setUPDATETIME(ConfigureDate);
	    		configureHistoryPersistence.setVERSION(Version);
	    		configureHistoryPersistence.setREMARKS(zhuanyi(Whats_new_desc));
	    		configureHistoryPersistence.setURL(ConfigureURL);
	    		
	    		
	    		softPersistence.setCONFIGUREID(uuid.toString());
	    		softPersistence.setSOFTID(SoftId);
	    		softPersistence.setSCORE(SoftScore);
	    		softPersistence.setLOGO(Logo);
	    		softPersistence.setINTRODUCTION(zhuanyi(Soft_desc));
	    		softPersistence.setDESCRIPTION(zhuanyi(Soft_desc_short));
	    		softPersistence.setVERSIONTYPE(Nick_version);
	    		softPersistence.setVERSION(Version);
	    		softPersistence.setNEWVERSIONINTRODUCTION(zhuanyi(Whats_new_desc));
	    		softPersistence.setNEWVERSIONDESCRIPTION(zhuanyi(Whats_new_desc_short));
	    		softPersistence.setSOFTTYPE(ClassifyName);
	    		softPersistence.setSPAREURL(ReURL);
	    		softPersistence.setOS(OS_type);
	    		softPersistence.setWEBSITE(Offical_website);
	    		
	    		try {
					ConfigureHelper.save_Soft(configurePersistence);
					ConfigureHistoryHelper.save_ConfigureHistory(configureHistoryPersistence);
					SoftHelper.sava(softPersistence);
	    			System.out.println("-------------------------------------");
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
    public static String zhuanyi(String string){
    	string = string.replace("\'", "\\'");
    	return string;
    }
    public static void main(String[] args) {
	        Spider.create(new SoftSpider())
	        .addUrl("http://rj.baidu.com/soft/lists/1/1")
	        .thread(15).run();
    }
}