package com.cognizant.osp.smartallocation.oabcs.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.cognizant.osp.smartallocation.model.AllocationChangeRequest;
import com.cognizant.osp.smartallocation.oabcs.request.builders.GetAllocationsRequestBuilder;
import com.cognizant.osp.smartallocation.oabcs.request.builders.GetLatestAllocationsRequestBuilder;
import com.cognizant.osp.smartallocation.oabcs.request.entities.QueryTransactionRequestEntity;
import com.cognizant.osp.smartallocation.oabcs.response.entities.Payload;
import com.cognizant.osp.smartallocation.oabcs.response.entities.QueryTransactionResponseEntity;
import com.cognizant.osp.smartallocation.oabcs.utils.SmartAllocationProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ViewAllocationService extends AllocationService {

	@Autowired
	private GetAllocationsRequestBuilder getAllocationsRequestBuilder;

	@Autowired
	private GetLatestAllocationsRequestBuilder getLatestAllocationsRequestBuilder;

	private static final Logger log = LoggerFactory.getLogger(ViewAllocationService.class);

	public List<AllocationChangeRequest> getAllocationChanges(String creds, String role, String clientId) {
		List<Payload> payloadResponse = null;
		try {
			ResponseEntity<QueryTransactionResponseEntity> entity = getSmartAllocationResponse(creds, role, null,
					clientId);
			payloadResponse = new ObjectMapper().readValue(entity.getBody().getResult().getPayload(),
					new TypeReference<List<Payload>>() {
					});
		} catch (IOException e) {
			log.error("Unable to parse payload:", e);
		}
		return payloadResponse.stream().map(payload -> payload.getAllocationChangeRequest()).collect(Collectors.toList());
	}

	@Override
	protected HttpEntity<QueryTransactionRequestEntity> getQueryTransactionRequestEntity(String creds,
			String role, String orgOrClientId) {
		return getAllocationsRequestBuilder.buildForGetAllocation(creds, Arrays.asList(role, orgOrClientId));
	}

	@Override
	protected void addTransactionPath(UriComponentsBuilder uriComponectBuilder) {
		uriComponectBuilder.path(SmartAllocationProperties.TRANSACTION_QUERY_PATH);
	}

	public List<AllocationChangeRequest> getLatestAllocations(String creds, String role, String org) {
		List<Payload> payloadResponse = null;
		try {
			ResponseEntity<QueryTransactionResponseEntity> entity = getSmartAllocationResponse(creds, role, org, null);
			payloadResponse = new ObjectMapper().readValue(entity.getBody().getResult().getPayload(),
					new TypeReference<List<Payload>>() {
					});
		} catch (IOException e) {
			log.error("Unable to parse payload:", e);
		}
		return payloadResponse.stream().map(payload -> payload.getAllocationChangeRequest()).collect(Collectors.toList());

	}

	@Override
	protected HttpEntity<QueryTransactionRequestEntity> getLatestAllocationChangesQueryTransactionRequestEntity(
			String creds, String role, String org) {
		return getLatestAllocationsRequestBuilder.buildForGetLatestAllocations(creds, role, org);
	}
}
