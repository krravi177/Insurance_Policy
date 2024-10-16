package com.javarnd.project.entity;

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
public class Claims {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer claimId;
	private String claimNo;
	private String claimStatus;

	@Lob
	private byte[] claimsReciept;

	@ManyToOne
	@JsonBackReference
	private Customer customer;


	

}
