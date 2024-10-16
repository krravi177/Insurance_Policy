package com.javarnd.project.customerservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javarnd.project.dao.CustomerDAO;
import com.javarnd.project.entity.Agent;
import com.javarnd.project.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDAO repo;

	@Override
	public void create(Customer customer) {
		repo.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return repo.findAll();
	}

	@Override
	public Customer findById(Integer Id) {
		return repo.findByCustomerId(Id);
	}

	@Override
	public void updateCustomer(Customer customer) {
		repo.save(customer);
	}

}
