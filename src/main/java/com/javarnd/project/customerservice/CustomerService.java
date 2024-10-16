package com.javarnd.project.customerservice;

import java.util.List;

import com.javarnd.project.entity.Customer;

public interface CustomerService {
	
	void create (Customer customer);
	List<Customer> getAllCustomers();
	Customer findById(Integer Id);
	void updateCustomer(Customer customer);
}
