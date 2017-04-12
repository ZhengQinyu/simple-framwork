package io.zhengqinyu.framework.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CodecUtil {
	private final static Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

	public static String encodeURL(String source) {
		String target;
		try {
			target = URLEncoder.encode(source, "UTF-8");
		} catch (Exception e) {
			LOGGER.error("encode url failure", e);
			throw new RuntimeException(e);
		}
		return target;
	}

	public static String decodeURL(String source) {
		String targer;
		try {
			targer = URLDecoder.decode(source, "UTF-8");
		} catch (Exception e) {
			LOGGER.error("decode url failure", e);
			throw new RuntimeException(e);
		}
		return targer;
	}
}
