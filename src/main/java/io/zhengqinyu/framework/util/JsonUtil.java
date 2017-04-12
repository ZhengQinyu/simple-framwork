package io.zhengqinyu.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {
	private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public static <T> String toJson(T obj) {
		String json;
		try {
			json = OBJECT_MAPPER.writeValueAsString(obj);
		} catch (Exception e) {
			LOGGER.error("convert POJO to JSON failure", e);
			throw new RuntimeException(e);
		}
		return json;
	}

	public static <T> T fromJson(String json, Class<T> type) {
		T pojo;
		try {
			pojo = OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			LOGGER.error("concert JSON to POJO failure", e);
			throw new RuntimeException(e);
		}
		return pojo;
	}
}
