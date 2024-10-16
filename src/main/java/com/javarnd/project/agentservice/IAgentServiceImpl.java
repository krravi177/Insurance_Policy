package com.javarnd.project.agentservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javarnd.project.dao.IAgentDAO;
import com.javarnd.project.entity.Agent;
import com.javarnd.project.entity.Customer;

@Service
public class IAgentServiceImpl implements IAgentServic {
	
	@Autowired
	private IAgentDAO repo;

	@Override
	public void create(Agent agent) {
	repo.save(agent);	
	}

	@Override
	public Agent getAgentById(int Id) {
		return repo.findByAgentId(Id);
	}

	@Override
	public List<Agent> getAllAgent() {
		return repo.findAll();
	}

	@Override
	public void update(Agent agent) {
		repo.save(agent);
		
	}

	@Override
	public void delete(Agent agent) {
		agent.setStatus("Inactive");
		repo.save(agent);
		
	}

	@Override
	public List<Customer> getAllCustomerByAgentId(int AgentId) {
		Agent agent = repo.findByAgentId(AgentId);
		List<Customer> customer = agent.getCustomer();
		return customer;
	}

}
