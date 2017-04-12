package io.zhengqinyu.framework.util;

public final class CastUtil {

	public static String castString(Object obj) {
		return castString(obj, "");
	}

	public static String castString(Object obj, String defaultValue) {
		return obj == null ? defaultValue : String.valueOf(obj);
	}

	public static double castDouble(Object obj) {
		return castDouble(obj, 0);
	}

	public static double castDouble(Object obj, double defaultValue) {
		double value = defaultValue;
		if (obj != null) {
			String str = castString(obj);
			if (StringUtil.isNotEmpty(str)) {
				try {
					value = Double.parseDouble(str);
				} catch (NumberFormatException e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}

	public static long castLong(Object obj) {
		return castLong(obj, 0);
	}

	public static long castLong(Object obj, long defaultValue) {
		long value = defaultValue;
		if (obj != null) {
			String str = castString(obj);
			if (StringUtil.isNotEmpty(str)) {
				try {
					value = Long.parseLong(str);
				} catch (NumberFormatException e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}

	public static int castInt(Object obj) {
		return castInt(obj, 0);
	}

	public static int castInt(Object obj, int defaultValue) {
		int value = defaultValue;
		if (obj != null) {
			String str = castString(obj);
			if (StringUtil.isNotEmpty(str)) {
				try {
					value = Integer.parseInt(str);
				} catch (NumberFormatException e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}

	public static boolean castBoolean(Object obj) {
		return castBoolean(obj, false);
	}

	public static boolean castBoolean(Object obj, boolean defaultValue) {
		boolean value = defaultValue;
		if (obj != null) {
			value = Boolean.parseBoolean(castString(obj));
		}
		return value;
	}
}
