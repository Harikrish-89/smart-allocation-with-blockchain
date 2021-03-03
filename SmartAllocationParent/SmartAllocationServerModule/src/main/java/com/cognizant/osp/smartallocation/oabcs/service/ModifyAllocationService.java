package com.cognizant.osp.smartallocation.oabcs.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.cognizant.osp.smartallocation.model.AllocationChangeRequest;
import com.cognizant.osp.smartallocation.oabcs.request.builders.ModifyAllocationRequestBuilder;
import com.cognizant.osp.smartallocation.oabcs.request.entities.QueryTransactionRequestEntity;
import com.cognizant.osp.smartallocation.oabcs.utils.SmartAllocationProperties;

@Service
public class ModifyAllocationService extends AllocationService {

	@Autowired
	private ModifyAllocationRequestBuilder modifyAllocationRequestBuilder;

	private AllocationChangeRequest allocationChangeRequest;

	public boolean modifyAllocation(AllocationChangeRequest allocationChangeRequest, String creds, String role) {
		this.allocationChangeRequest = allocationChangeRequest;
		this.allocationChangeRequest.setCreatedTime(Instant.now().toString());
		return getSmartAllocationResponse(creds, role, null, null).getBody().getReturnCode()
				.equalsIgnoreCase("Success");
	}

	@Override
	protected HttpEntity<QueryTransactionRequestEntity> getQueryTransactionRequestEntity(String creds,
			String role, String clientId) {
		return modifyAllocationRequestBuilder.buildForModifyAllocation(allocationChangeRequest, creds, role);
	}

	@Override
	protected void addTransactionPath(UriComponentsBuilder uriComponectBuilder) {
		uriComponectBuilder.path(SmartAllocationProperties.TRANSACTION_INVOCATION_PATH);
	}

	@Override
	protected HttpEntity<QueryTransactionRequestEntity> getLatestAllocationChangesQueryTransactionRequestEntity(
			String creds, String client, String org) {
		return null;
	}
}
