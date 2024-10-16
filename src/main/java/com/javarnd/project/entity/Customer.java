package com.javarnd.project.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	private String firstName;
	private String lastName;
	private String mobileNo;
	
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer" , fetch = FetchType.EAGER)
	@JsonManagedReference
	@BatchSize(size = 10)	
	private List<Policy>policy;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "customer")
	@JsonManagedReference
	private List<Claims>claims;
	
	@ManyToOne
	@JsonBackReference
	private Agent agent;
	
}
