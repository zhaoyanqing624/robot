package org.xjtusicd3.partner.controller;

import net.sf.json.JSONObject;

public class testaa {

	public static void main(String[] args) {
		String string = "{title:['操作系统'],id:'speedMenu12',content:[{title:'磁盘分区',content:['磁盘分区', '蓝屏死机', '系统安装与升级', '程序安装与卸载', '浏览器', '应用商店问题', '系统还原']}]}";
		JSONObject object = JSONObject.fromObject(string);
		System.out.println(object);
	}

}
