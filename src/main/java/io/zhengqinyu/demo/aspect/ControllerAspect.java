package io.zhengqinyu.demo.aspect;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.zhengqinyu.framework.annotation.Aspect;
import io.zhengqinyu.framework.annotation.Controller;
import io.zhengqinyu.framework.proxy.AspectProxy;

/**
 * @author ZhengQinyu controller拦截类
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

	private long begin;

	@Override
	public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
		LOGGER.debug("-----------begin---------------");
		LOGGER.debug(String.format("class : %s", cls.getName()));
		LOGGER.debug(String.format("method : %s", method.getName()));
		begin = System.currentTimeMillis();
	}

	@Override
	public void after(Class<?> cls, Method method, Object[] params) throws Throwable {
		LOGGER.debug(String.format("time : %dms", System.currentTimeMillis() - begin));
		LOGGER.debug("-----------end---------------");
	}
}
