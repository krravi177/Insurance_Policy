package com.javarnd.project.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class Agent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer agentId;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "agent")
	@JsonManagedReference
	private List<Customer> customer;
}
