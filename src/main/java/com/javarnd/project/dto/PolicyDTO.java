package com.javarnd.project.dto;

import java.sql.Date;

import javax.persistence.Lob;

import lombok.Data;
@Data
public class PolicyDTO {
	
	private Integer policyId;
	private String policyNo;
	private String policyType;
	private String policyName;
	private Date startDate;
	private Date endDate;
	private byte[] policyDocs;
	private Integer customerId;
	private String CustomerName;
	public Integer getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public byte[] getPolicyDocs() {
		return policyDocs;
	}
	public void setPolicyDocs(byte[] policyDocs) {
		this.policyDocs = policyDocs;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	

}
