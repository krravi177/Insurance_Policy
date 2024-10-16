package com.javarnd.project.dto;

import lombok.Data;

@Data
public class ClaimsDTO {

	private Integer claimId;
	private String claimNo;
	private String claimStatus;
	private byte[] claimsReciept;
	private Integer customerId;
	private String customerName;
	public Integer getClaimId() {
		return claimId;
	}
	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public byte[] getClaimsReciept() {
		return claimsReciept;
	}
	public void setClaimsReciept(byte[] claimsReciept) {
		this.claimsReciept = claimsReciept;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	
}
