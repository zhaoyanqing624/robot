package org.xjtusicd3.parnter.spider;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.xjtusicd3.partner.filter.DownLoadFromUrl;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class LexiconSpider implements PageProcessor {
    public static final String URL_LIST = "http://pinyin.sogou.com/dict/cate/index/97/default/\\w+";
    public static final String URL_POST = "http://download.pinyin.sogou.com/dict/download_cell.php\\?id=\\w+&name=\\w+";

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
	    if(page.getHtml().regex(URL_LIST).match()){
	    	page.addTargetRequests(page.getHtml().css("#dict_page_list ul li:last-child").links().regex(URL_LIST).all());
	    	List<String> fileURL = page.getHtml().css(".dict_dl_btn").links().all();
	    	for(String string:fileURL){
	    		try {
					uploadUrl(string);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    }
    }
    
    
    //下载
    public static void uploadUrl(String fileURL) throws Exception{
    	String fileLocal="E:\\lexicon";
    	String[] refileName = fileURL.split("name=", 2);
    	String fileName = refileName[1];
    	fileName = getURLDecoderString(fileName);
    	DownLoadFromUrl downLoadFromUrl = new DownLoadFromUrl();
    	downLoadFromUrl.downLoadFromUrl(fileURL, fileName, fileLocal);
    }
    //url解码
    private final static String ENCODE = "UTF-8";
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public Site getSite() {
        return site;
    }
//    public static void spider_patch(){
//      	Spider.create(new LexiconSpider()).addUrl("http://pinyin.sogou.com/dict/cate/index/97").thread(15).run();
//    }
    public static void main(String[] args) throws Exception {
    	Spider.create(new LexiconSpider()).addUrl("http://pinyin.sogou.com/dict/cate/index/97").thread(15).run();
	}

}