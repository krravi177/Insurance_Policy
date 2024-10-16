package com.javarnd.project.policyservice;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarnd.project.dao.CustomerDAO;
import com.javarnd.project.dao.PolicyDAO;
import com.javarnd.project.dto.PolicyDTO;
import com.javarnd.project.entity.Customer;
import com.javarnd.project.entity.Policy;

@Service
public class IPolicyServiceImpl implements IPloicyService{
	
	@Autowired
	private PolicyDAO repo;
	
	@Autowired
	private CustomerDAO repoCx;

	@Override
	public void create(String policy ,MultipartFile file) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Policy value = mapper.readValue(policy, Policy.class);
		value.setPolicyDocs(file.getBytes());
		repo.save(value);
	}

	@Override
	public Policy getById(int Id) {
		return repo.findByPolicyId(Id);
	}

	@Override
	public List<Policy> getAll() {
		return repo.findAll();
	}

	@Override
	public List<Policy> getAllPolicyByCustomerId(int customerId) {
		Customer customer = repoCx.findByCustomerId(customerId);
		List<Policy> policy = customer.getPolicy();
		return policy;
	}

	@Override
	public PolicyDTO searchPolicyByPolicyNo(String number) {
		Policy policy = repo.findByPolicyNo(number);
		if(policy == null)return null;
		PolicyDTO dto = new PolicyDTO();
		dto.setPolicyId(policy.getPolicyId());
		dto.setPolicyNo(policy.getPolicyNo());
		dto.setPolicyType(policy.getPolicyType());
		dto.setPolicyName(policy.getPolicyName());
		dto.setStartDate(policy.getStartDate());
		dto.setEndDate(policy.getEndDate());
		dto.setPolicyDocs(policy.getPolicyDocs());
		dto.setCustomerId(policy.getCustomer().getCustomerId());
		dto.setCustomerName(policy.getCustomer().getFirstName()+" "+policy.getCustomer().getLastName());
		return dto;
	}

	@Override
	public List<Policy> expiredPolicy(Date date) {
		return repo.findByEndDateBefore(date);
	}

	@Override
	public List<Policy> activePolicy(Date date) {
		return repo.findByEndDateAfter(date);
	}

	@Override
	public void update(String policy , MultipartFile file) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Policy value = mapper.readValue(policy, Policy.class);
		value.setPolicyDocs(file.getBytes());
		repo.save(value);
	}

}
