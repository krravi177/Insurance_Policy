package com.javarnd.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarnd.project.customerservice.CustomerServiceImpl;
import com.javarnd.project.entity.Customer;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl service;

	@PostMapping("/customers")
	public ResponseEntity<?> create(@RequestParam("Customer") String customer) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Customer value = objectMapper.readValue(customer, Customer.class);
			service.create(value);
			return ResponseEntity.status(HttpStatus.CREATED).body("Data Sved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

	@GetMapping("/customers")
	public ResponseEntity<?> getAll() {
		try {
			List<Customer> allCustomers = service.getAllCustomers();
			if (allCustomers.isEmpty() || allCustomers == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Customers Available");
			}
			return ResponseEntity.status(HttpStatus.OK).body(allCustomers);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}
	
	
	@GetMapping("/customers/{Id}")
	public ResponseEntity<?> getById(@PathVariable int Id) {
		try {
			Customer customer = service.findById(Id);
			if (customer == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Customers Available for this Id: "+Id);
			}
			return ResponseEntity.status(HttpStatus.OK).body(customer);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}
	
	@PutMapping("/customers")
	public ResponseEntity<?> update(@RequestBody Customer customer) {
		try {
			service.updateCustomer(customer);
			return ResponseEntity.status(HttpStatus.CREATED).body("Data Updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}


}
