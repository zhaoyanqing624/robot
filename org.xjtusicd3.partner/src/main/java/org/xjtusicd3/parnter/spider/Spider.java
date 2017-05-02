package org.xjtusicd3.parnter.spider;

public class Spider {
	/*
	 * zyq_spider_启动爬虫
	 */
	public static void main(String[] args) {
		SoftSpider softSpider = new SoftSpider();
		softSpider.spider_soft();
		
		DriverSpider driverSpider = new DriverSpider();
		driverSpider.spider_driver();
		
		PatchSpider patchSpider = new PatchSpider();
		patchSpider.spider_patch();
	}
}
