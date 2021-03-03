package com.cognizant.osp.smartallocation.oabcs.response.entities;

import com.cognizant.osp.smartallocation.model.AllocationChangeRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payload {

	@JsonProperty("Key")
	private String key;

	@JsonProperty("Record")
	private AllocationChangeRequest allocationChangeRequest;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public AllocationChangeRequest getAllocationChangeRequest() {
		return allocationChangeRequest;
	}

	public void setAllocationChangeRequest(AllocationChangeRequest allocationChangeRequest) {
		this.allocationChangeRequest = allocationChangeRequest;
	}
}
