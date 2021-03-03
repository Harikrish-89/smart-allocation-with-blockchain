package com.cognizant.osp.smartallocation.oabcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.cognizant.osp.smartallocation.oabcs.request.builders.ReviewedAllocationRequestBuilder;
import com.cognizant.osp.smartallocation.oabcs.request.entities.QueryTransactionRequestEntity;
import com.cognizant.osp.smartallocation.oabcs.utils.SmartAllocationProperties;

@Service
public class ReviewedAllocationService extends AllocationService {

	@Autowired
	ReviewedAllocationRequestBuilder reviewedAllocationRequestBuilder;

	private String requestId;
	private String status;

	public boolean approve(String requestId, String status, String creds, String role) {
		this.requestId = requestId;
		this.status = status;
		return getSmartAllocationResponse(creds, role, null, null).getBody().getReturnCode()
				.equalsIgnoreCase("Success");
	}

	@Override
	protected HttpEntity<QueryTransactionRequestEntity> getLatestAllocationChangesQueryTransactionRequestEntity(
			String creds, String client, String org) {
		return null;
	}

	@Override
	protected HttpEntity<QueryTransactionRequestEntity> getQueryTransactionRequestEntity(String creds,
			String role, String clientId) {
		return reviewedAllocationRequestBuilder.buildForApproveAllocation(requestId, status, creds, role);
	}

	@Override
	protected void addTransactionPath(UriComponentsBuilder uriComponectBuilder) {
		uriComponectBuilder.path(SmartAllocationProperties.TRANSACTION_INVOCATION_PATH);
	}
}
