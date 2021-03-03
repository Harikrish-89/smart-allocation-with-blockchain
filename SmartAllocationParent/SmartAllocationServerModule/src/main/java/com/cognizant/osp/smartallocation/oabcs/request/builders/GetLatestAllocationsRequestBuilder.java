package com.cognizant.osp.smartallocation.oabcs.request.builders;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import com.cognizant.osp.smartallocation.oabcs.request.entities.QueryTransactionRequestEntity;

@Component
public class GetLatestAllocationsRequestBuilder extends QueryTransactionRequestBuilder {
	public HttpEntity<QueryTransactionRequestEntity> buildForGetLatestAllocations(String creds, String role,
			String org) {
		return new HttpEntity<QueryTransactionRequestEntity>(
				getQueryTransactionEntity("getLatestAllocation", Arrays.asList(role,org)), getHeaders(creds));
	}
}
