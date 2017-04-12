package io.zhengqinyu.framework;

import io.zhengqinyu.framework.helper.AopHelper;
import io.zhengqinyu.framework.helper.BeanHelper;
import io.zhengqinyu.framework.helper.ClassHelper;
import io.zhengqinyu.framework.helper.ControllerHelper;
import io.zhengqinyu.framework.helper.IocHelper;
import io.zhengqinyu.framework.util.ClassUtil;

public final class HelperLoader {

	public static void init() {
		Class<?>[] classList = { ClassHelper.class, BeanHelper.class, AopHelper.class,IocHelper.class, ControllerHelper.class };

		for (Class<?> cls : classList) {
			ClassUtil.loadClass(cls.getName());
		}
	}
}
