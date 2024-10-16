package com.javarnd.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javarnd.project.entity.Claims;

@Repository
public interface IClaimsDAO extends JpaRepository<Claims,Integer>{
	Claims findByClaimId(int id);
	
	Claims findByClaimNo(String claimNo);
	
	List<Claims> findByClaimStatus(String status);
	
	

}
