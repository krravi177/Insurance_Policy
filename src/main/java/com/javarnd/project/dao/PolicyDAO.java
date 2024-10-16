package com.javarnd.project.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javarnd.project.entity.Policy;

@Repository
public interface PolicyDAO extends JpaRepository<Policy, Integer>{
	
	Policy findByPolicyId(Integer Id);
	
	Policy findByPolicyNo(String PolicyNo);
	
	 List<Policy> findByEndDateAfter(Date date);
	
	 List<Policy> findByEndDateBefore(Date date);

}