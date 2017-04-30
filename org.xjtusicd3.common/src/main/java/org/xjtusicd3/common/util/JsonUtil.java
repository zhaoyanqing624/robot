package org.xjtusicd3.common.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	public static <T> T toBean(String text, Class<T> clazz) throws Exception {
		T t = JSON.parseObject(text, clazz);
		return t;
	}

	public static <T> List<T> toArrayBean(String text, Class<T> clazz) {
		List<T> t = JSON.parseArray(text, clazz);
		return t;
	}

	public static String toJsonString(Object obj) {
		String jsonString = JSON.toJSONString(obj);
		return jsonString;
	}

	public static JSONObject toJson(String text) {
		return JSON.parseObject(text);
	}
}
