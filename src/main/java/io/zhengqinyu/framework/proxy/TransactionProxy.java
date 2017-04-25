package io.zhengqinyu.framework.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.zhengqinyu.framework.annotation.Transaction;
import io.zhengqinyu.framework.helper.DatabaseHelper;

public class TransactionProxy implements Proxy {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);

	private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>() {
		@Override
		protected Boolean initialValue() {
			return false;
		}
	};

	@Override
	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		Object result;
		boolean flag = FLAG_HOLDER.get();
		Method method = proxyChain.getTargetMethod();
		if (!flag && method.isAnnotationPresent(Transaction.class)) {
			FLAG_HOLDER.set(true);
			try {
				DatabaseHelper.beginTransation();
				LOGGER.debug("begin transation");
				result = proxyChain.doProxyChain();
				DatabaseHelper.commitTransation();
				LOGGER.debug("commit transation");
			} catch (Exception e) {
				DatabaseHelper.rollbackTransation();
				LOGGER.debug("rollback transation");
				throw e;
			} finally {
				FLAG_HOLDER.remove();
			}
		} else {
			result = proxyChain.doProxyChain();
		}
		return result;
	}

}
