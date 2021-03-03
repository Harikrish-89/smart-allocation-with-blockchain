package com.cognizant.osp.smartallocation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllocationChangeRequest {

	private String requestId;
	private String createdTime;
	private String clientId;
	private String clientOrg;
	private Allocation previousAllocation;
	private Allocation modifiedAllocation;
	private String status;
	private String rejectReason;
	private String approver;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientOrg() {
		return clientOrg;
	}

	public void setClientOrg(String clientOrg) {
		this.clientOrg = clientOrg;
	}

	public Allocation getPreviousAllocation() {
		return previousAllocation;
	}

	public void setPreviousAllocation(Allocation previousAllocation) {
		this.previousAllocation = previousAllocation;
	}

	public Allocation getModifiedAllocation() {
		return modifiedAllocation;
	}

	public void setModifiedAllocation(Allocation modifiedAllocation) {
		this.modifiedAllocation = modifiedAllocation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}
}
