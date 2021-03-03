package com.cognizant.osp.smartallocation.rest;

import com.cognizant.osp.smartallocation.model.AllocationChangeRequest;

public class AllocationRequestEntitiy {

	private AllocationChangeRequest allocationChangeRequest;

	private SmartAllocationServiceRestEntities smartAllocationRequestEntity;

	public SmartAllocationServiceRestEntities getSmartAllocationRequestEntity() {
		return smartAllocationRequestEntity;
	}

	public void setSmartAllocationRequestEntity(SmartAllocationServiceRestEntities smartAllocationRequestEntity) {
		this.smartAllocationRequestEntity = smartAllocationRequestEntity;
	}

	public AllocationChangeRequest getAllocationChangeRequest() {
		return allocationChangeRequest;
	}

	public void setAllocationChangeRequest(AllocationChangeRequest allocationChangeRequest) {
		this.allocationChangeRequest = allocationChangeRequest;
	}
}
