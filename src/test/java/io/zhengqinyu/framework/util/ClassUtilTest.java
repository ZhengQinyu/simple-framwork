package io.zhengqinyu.framework.util;

import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassUtilTest {

	private final static Logger LOG = LoggerFactory.getLogger(ClassUtilTest.class);
	
	@Test
	public void test() {
		
		Set<Class<?>> classes = ClassUtil.getClassSet("javax.servlet");
		for (Class<?> class1 : classes) {
			LOG.debug(class1.getName());
		}
	}

}
