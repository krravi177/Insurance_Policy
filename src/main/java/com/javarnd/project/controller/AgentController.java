package com.javarnd.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javarnd.project.agentservice.IAgentServiceImpl;
import com.javarnd.project.dao.IAgentDAO;
import com.javarnd.project.entity.Agent;
import com.javarnd.project.entity.Customer;
import com.zaxxer.hikari.util.ConcurrentBag.IConcurrentBagEntry;

@RestController
@RequestMapping("/api")
public class AgentController {

	@Autowired
	private IAgentServiceImpl service;
	
	@Autowired
	private IAgentDAO repo;

	@PostMapping("/agent")
	public ResponseEntity<?> create(@RequestBody Agent agent) {
		try {
			service.create(agent);
			return ResponseEntity.status(HttpStatus.CREATED).body("Data Sved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

	@GetMapping("/agent")
	public ResponseEntity<?> getAll() {
		try {
			List<Agent> list = service.getAllAgent();
			if (list.isEmpty() || list == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Agent Available");
			}
			return ResponseEntity.status(HttpStatus.OK).body(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

	@GetMapping("/agent/{Id}")
	public ResponseEntity<?> getById(@PathVariable int Id) {
		try {
			Agent agentById = service.getAgentById(Id);
			if (agentById == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Agent Available for this Id: " + Id);
			}
			return ResponseEntity.status(HttpStatus.OK).body(agentById);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}

	@PutMapping("/agent")
	public ResponseEntity<?> update(@RequestBody Agent agent) {
		try {
			service.update(agent);
			return ResponseEntity.status(HttpStatus.CREATED).body("Data updates");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}

	}
	
	
	@GetMapping("/agent/{Id}/customers")
	public ResponseEntity<?> getAllCustomersByAgentId(@PathVariable int Id) {
		try {
			if(repo.findByAgentId(Id)== null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Agent Available");
			}
			List<Customer> list = service.getAllCustomerByAgentId(Id);
			if (list.isEmpty() || list == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Mo Agent Available");
			}
			return ResponseEntity.status(HttpStatus.OK).body(list);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

		}
	}
	
	
}
