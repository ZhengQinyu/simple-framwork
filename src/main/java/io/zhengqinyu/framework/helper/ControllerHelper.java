package io.zhengqinyu.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.zhengqinyu.framework.annotation.Action;
import io.zhengqinyu.framework.bean.Handler;
import io.zhengqinyu.framework.bean.Request;
import io.zhengqinyu.framework.util.ArraryUtil;
import io.zhengqinyu.framework.util.CollectionUtil;

public final class ControllerHelper {
	private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

	static {
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if (CollectionUtil.isNotEmpty(controllerClassSet)) {
			for (Class<?> controllerClass : controllerClassSet) {
				Method[] methods = controllerClass.getDeclaredMethods();
				if (ArraryUtil.isNotEmpty(methods)) {
					for (Method method : methods) {
						if (method.isAnnotationPresent(Action.class)) {
							Action action = method.getAnnotation(Action.class);
							String mapping = action.value();
							if (mapping.matches("\\w+:/\\w*")) {
								String[] array = mapping.split(":");
								if (ArraryUtil.isNotEmpty(array) && array.length == 2) {
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(controllerClass, method);
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
	}

	public static Handler getHandler(String requestMethod, String requestPath) {
		Request request = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}
}
