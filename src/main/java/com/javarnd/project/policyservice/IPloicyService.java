package com.javarnd.project.policyservice;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.javarnd.project.dto.PolicyDTO;
import com.javarnd.project.entity.Policy;

public interface IPloicyService {

	void create(String policy , MultipartFile file) throws JsonMappingException, JsonProcessingException, IOException;

	Policy getById(int Id);

	List<Policy> getAll();
	
	void update(String policy ,MultipartFile file) throws JsonMappingException, JsonProcessingException, IOException;
	
	List<Policy> getAllPolicyByCustomerId(int customerId);
	
	PolicyDTO searchPolicyByPolicyNo(String number);
	
	List<Policy> expiredPolicy(Date date);
	
	List<Policy> activePolicy(Date date);

}
