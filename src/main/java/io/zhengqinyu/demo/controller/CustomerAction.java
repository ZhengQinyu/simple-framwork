package io.zhengqinyu.demo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.zhengqinyu.demo.model.Customer;
import io.zhengqinyu.demo.service.CustomerService;
import io.zhengqinyu.framework.annotation.Action;
import io.zhengqinyu.framework.annotation.Controller;
import io.zhengqinyu.framework.annotation.Inject;
import io.zhengqinyu.framework.bean.Data;
import io.zhengqinyu.framework.bean.FileParam;
import io.zhengqinyu.framework.bean.Param;
import io.zhengqinyu.framework.bean.View;

@Controller
public class CustomerAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAction.class);

	@Inject
	private CustomerService customerService;

	@Action("get:/customers")
	public View getCustomers() {
		List<Customer> customers = customerService.getCustomers();
		LOGGER.debug("get customer size:{}", customers == null ? 0 : customers.size());
		return new View("customers.jsp").AddModel("customers", customers);
	}

	@Action("get:/customer_create_page")
	public View getCreatePage() {
		LOGGER.debug("get Page");
		return new View("customer_create.jsp");
	}

	@Action("post:/customer_create")
	public Data createSubmit(Param param) {
		Map<String, Object> fieldMap = param.getFieldMap();
		FileParam fileParam = param.getFile("photo");
		boolean result = customerService.createCustomer(fieldMap, fileParam);
		return new Data(result);
	}
}
