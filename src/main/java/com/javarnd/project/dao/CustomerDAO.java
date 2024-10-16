package com.javarnd.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javarnd.project.entity.Customer;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer>{
	
	Customer findByCustomerId(Integer id);

}
