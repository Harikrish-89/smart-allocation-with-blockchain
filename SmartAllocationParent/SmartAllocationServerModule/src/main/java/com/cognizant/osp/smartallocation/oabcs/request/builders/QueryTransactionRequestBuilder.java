package com.cognizant.osp.smartallocation.oabcs.request.builders;

import java.util.List;

import org.springframework.http.HttpHeaders;

import com.cognizant.osp.smartallocation.oabcs.request.entities.QueryTransactionRequestEntity;
import com.cognizant.osp.smartallocation.oabcs.utils.SmartAllocationProperties;

public abstract class QueryTransactionRequestBuilder {
	protected QueryTransactionRequestEntity getQueryTransactionEntity(String method, List<? extends Object> args) {
		QueryTransactionRequestEntity queryTransactionRequestEntity = new QueryTransactionRequestEntity();
		queryTransactionRequestEntity.setChannel(SmartAllocationProperties.CHANNEL_NAME);
		queryTransactionRequestEntity.setChaincode(SmartAllocationProperties.CHAINCODE_NAME);
		queryTransactionRequestEntity.setChaincodeVer(SmartAllocationProperties.CHAINCODE_VERSION);
		queryTransactionRequestEntity.setMethod(method);
		queryTransactionRequestEntity.setArgs(args);
		return queryTransactionRequestEntity;
	}

	protected HttpHeaders getHeaders(String creds) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + creds);
		return headers;
	}
}
