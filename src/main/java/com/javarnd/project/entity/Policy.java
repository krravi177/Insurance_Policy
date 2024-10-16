package com.javarnd.project.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer policyId;
	private String policyNo;
	private String policyType;
	private String policyName;
	private Date startDate;
	private Date endDate;
	
	@Lob
	private byte[] policyDocs;
	
	@ManyToOne
	@JsonBackReference
	private Customer customer;
	
}
