package com.cognizant.osp.smartallocation.oabcs.request.builders;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import com.cognizant.osp.smartallocation.model.AllocationChangeRequest;
import com.cognizant.osp.smartallocation.oabcs.exception.AllocationServiceException;
import com.cognizant.osp.smartallocation.oabcs.request.entities.QueryTransactionRequestEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ModifyAllocationRequestBuilder extends QueryTransactionRequestBuilder {
	final static Logger log = LoggerFactory.getLogger(ModifyAllocationRequestBuilder.class);

	public HttpEntity<QueryTransactionRequestEntity> buildForModifyAllocation(AllocationChangeRequest allocationChangeRequest, String creds,
			String role) {
		return new HttpEntity<QueryTransactionRequestEntity>(
				getQueryTransactionEntity("modifyAllocation", getModifyAllocationArguments(allocationChangeRequest, role)),
				getHeaders(creds));
	}

	private List<? extends Object> getModifyAllocationArguments(AllocationChangeRequest allocationChangeRequest, String role) {
		try {
			return Arrays.asList(role, new ObjectMapper().writeValueAsString(allocationChangeRequest));
		} catch (JsonProcessingException e) {
			log.error("Unable to parse allocation object:", e);
			throw new AllocationServiceException("Unable to parse allocation object:", e);
		}
	}

}
