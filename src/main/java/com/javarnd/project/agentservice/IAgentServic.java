package com.javarnd.project.agentservice;

import java.util.List;

import com.javarnd.project.entity.Agent;
import com.javarnd.project.entity.Customer;

public interface IAgentServic {
	
	void create(Agent agent);
	Agent getAgentById(int Id);
	List<Agent> getAllAgent();
	void update(Agent agent);
	void delete(Agent Agent);
	List<Customer> getAllCustomerByAgentId(int agentId);	

}
