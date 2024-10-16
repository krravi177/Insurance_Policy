package com.javarnd.project.claimservice;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarnd.project.dao.CustomerDAO;
import com.javarnd.project.dao.IClaimsDAO;
import com.javarnd.project.dto.ClaimsDTO;
import com.javarnd.project.entity.Claims;
import com.javarnd.project.entity.Customer;

;

@Service
public class IClaimServiceImpl implements IClaimService {

	@Autowired
	private IClaimsDAO repo;

	@Autowired
	private CustomerDAO repoCx;

	@Override
	public void create(String claims, MultipartFile file) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Claims claim = mapper.readValue(claims, Claims.class);
		claim.setClaimsReciept(file.getBytes());
		repo.save(claim);

	}

	@Override
	public List<Claims> getAll() {
		return repo.findAll();
	}

	@Override
	public Claims getById(int Id) {
		return repo.findByClaimId(Id);
	}

	@Override
	public List<Claims> getAllClaimsByCustomerId(int Id) {
		Customer customer = repoCx.findByCustomerId(Id);
		List<Claims> claims = customer.getClaims();
		return claims;
	}

	@Override
	public ClaimsDTO searchClaimsByClaimNo(String ClaimNo) {

		Claims claims = repo.findByClaimNo(ClaimNo);
		if(claims == null) {
			return null;
		}

		ClaimsDTO claimsDTO = new ClaimsDTO();

		claimsDTO.setClaimId(claims.getClaimId());
		claimsDTO.setClaimNo(claims.getClaimNo());
		claimsDTO.setClaimStatus(claims.getClaimStatus());
		claimsDTO.setClaimsReciept(claims.getClaimsReciept());
		claimsDTO.setCustomerId(claims.getCustomer().getCustomerId());
		claimsDTO.setCustomerName(claims.getCustomer().getFirstName() + " " + claims.getCustomer().getLastName());

		return claimsDTO;
	}

	@Override
	public List<Claims> findClaimsByStatus(String status) {
		return repo.findByClaimStatus(status);
		
	}

	@Override
	public void upadateClaims(String claims , MultipartFile file) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Claims claim = mapper.readValue(claims, Claims.class);
		claim.setClaimsReciept(file.getBytes());
		repo.save(claim);
	}

	

}
