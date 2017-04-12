package io.zhengqinyu.framework.util;

import java.util.Collection;
import java.util.Map;

public final class CollectionUtil {

	public static boolean isEmpty(Collection<?> collection) {
		return (collection == null) || (collection.isEmpty());
	}

	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return (map == null) || (map.isEmpty());
	}

	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}
}
