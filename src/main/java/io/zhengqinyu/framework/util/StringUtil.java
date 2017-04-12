package io.zhengqinyu.framework.util;

/**
 * 字符串工具类
 * 
 * @author ZhengQinyu
 *
 */
public final class StringUtil {

	public static final String SEPARATOR = String.valueOf((char) 29);

	public static boolean isEmpty(String str) {
		if (str != null) {
			str = str.trim();
		}
		return (str == null) || (str.length() == 0);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String[] splitString(String str, String separator) {
		return str == null ? null : str.split(separator);
	}
}
