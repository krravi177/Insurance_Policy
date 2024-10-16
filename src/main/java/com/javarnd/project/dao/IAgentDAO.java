package com.javarnd.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javarnd.project.entity.Agent;

public interface IAgentDAO extends JpaRepository<Agent, Integer> {
	
	Agent findByAgentId(int Id);

}
