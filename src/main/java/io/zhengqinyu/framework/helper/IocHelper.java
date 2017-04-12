package io.zhengqinyu.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import io.zhengqinyu.framework.annotation.Inject;
import io.zhengqinyu.framework.util.ArraryUtil;
import io.zhengqinyu.framework.util.CollectionUtil;
import io.zhengqinyu.framework.util.RefletionUtil;

public final class IocHelper {
	static {
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if (CollectionUtil.isNotEmpty(beanMap)) {
			for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				Field[] beanFields = beanClass.getDeclaredFields();
				if (ArraryUtil.isNotEmpty(beanFields)) {
					for (Field beanField : beanFields) {
						if (beanField.isAnnotationPresent(Inject.class)) {
							Class<?> beanFieldClass = beanField.getType();
							Object beanFieldInstace = beanMap.get(beanFieldClass);
							if (beanFieldInstace != null) {
								//通过反射注入值
								RefletionUtil.setField(beanInstance, beanField, beanFieldInstace);
							}
						}
					}
				}
			}
		}
	}
}
