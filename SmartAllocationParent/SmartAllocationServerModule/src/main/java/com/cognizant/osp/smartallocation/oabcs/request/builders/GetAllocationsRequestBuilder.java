package com.cognizant.osp.smartallocation.oabcs.request.builders;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import com.cognizant.osp.smartallocation.oabcs.request.entities.QueryTransactionRequestEntity;

@Component
public class GetAllocationsRequestBuilder extends QueryTransactionRequestBuilder {

	public HttpEntity<QueryTransactionRequestEntity> buildForGetAllocation(String creds, List<? extends Object> args) {
		return new HttpEntity<QueryTransactionRequestEntity>(
				getQueryTransactionEntity("getAllocationChanges", args),getHeaders(creds));
	}

	public HttpEntity<QueryTransactionRequestEntity> buildForGetPendingAllocation(String creds, List<? extends Object> args) {
		return new HttpEntity<QueryTransactionRequestEntity>(
				getQueryTransactionEntity("getPendingAllocationChangess", args), getHeaders(creds));
	}
}
