package io.zhengqinyu.demo.service;

import java.util.List;
import java.util.Map;

import io.zhengqinyu.demo.model.Customer;
import io.zhengqinyu.framework.annotation.Service;
import io.zhengqinyu.framework.annotation.Transaction;
import io.zhengqinyu.framework.bean.FileParam;
import io.zhengqinyu.framework.helper.DatabaseHelper;
import io.zhengqinyu.framework.helper.UploadHelper;

@Service
public class CustomerService {

	public List<Customer> getCustomers() {
		List<Customer> customers;
		String sql = "select * from Customer";
		customers = DatabaseHelper.queryEntityList(Customer.class, sql);
		return customers;
	}

	public Customer getCustomerById(long id) {
		Customer customer;
		String sql = "select * from Customer where id=?";
		customer = DatabaseHelper.queryEntity(Customer.class, sql, id);
		return customer;
	}

	@Transaction
	public boolean createCustomer(Map<String, Object> fieldMap, FileParam fileParam) {
		boolean result = DatabaseHelper.insertEntity(Customer.class, fieldMap);
		if (result) {
			UploadHelper.uploadFile("/tmp/upload/", fileParam);
		}
		return result;
	}
}
