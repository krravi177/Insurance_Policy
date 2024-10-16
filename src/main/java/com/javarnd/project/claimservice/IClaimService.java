package com.javarnd.project.claimservice;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.javarnd.project.dto.ClaimsDTO;
import com.javarnd.project.entity.Claims;

public interface IClaimService {
	
	void create(String claims ,MultipartFile file) throws JsonMappingException, JsonProcessingException, IOException;
	List<Claims>getAll();
	Claims getById(int Id);
	List<Claims>getAllClaimsByCustomerId(int Id);
	ClaimsDTO searchClaimsByClaimNo(String ClaimNo);
	List<Claims>  findClaimsByStatus(String status);
	void upadateClaims(String claims , MultipartFile file) throws JsonMappingException, JsonProcessingException, IOException;

}
