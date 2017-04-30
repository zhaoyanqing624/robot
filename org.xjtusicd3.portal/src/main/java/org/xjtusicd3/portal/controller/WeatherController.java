package org.xjtusicd3.portal.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

@Controller
public class WeatherController implements PageProcessor{
	private Site site = Site.me().setDomain("www.pm25.com");
	@Override
	public Site getSite() {
		return site;
	}
	@Override
	public void process(Page page) {
		page.putField("number",page.getHtml().xpath("//div[@class='bi_aqiarea_top']/a/text()").get());
        page.putField("evaluate",page.getHtml().xpath("//div[@class='bi_aqiarea_top']/p/span[1]/text()").get());
	}
	public static String ReadFile(String path){
		File file = new File(path);
		BufferedReader reader = null;
		String laststr="";
		try {
			reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            while ((tempString = reader.readLine()) != null) {  
                laststr = laststr + tempString;  
            }
            reader.close();
        }catch(IOException e){
        	e.printStackTrace();
		} finally {
			if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            } 
		}
		return laststr;
		
	}
	//动态获取雾霾信息
	@RequestMapping(value={"getWumai"},method={org.springframework.web.bind.annotation.RequestMethod.GET},produces="text/html;charset=UTF-8")
	public void getWeather(HttpServletResponse response) throws IOException{
		Spider.create(new WeatherController()).addUrl("http://www.pm25.com/").addPipeline(new JsonFilePipeline("D:\\webmagic\\")).run();
		String result = ReadFile("D:/webmagic/www.pm25.com/c70da8eaca83f9d002b616b5074d870d.json");
		System.out.println(result);
		PrintWriter out = response.getWriter();
		out.write(result);
		return;
	}
	
}

