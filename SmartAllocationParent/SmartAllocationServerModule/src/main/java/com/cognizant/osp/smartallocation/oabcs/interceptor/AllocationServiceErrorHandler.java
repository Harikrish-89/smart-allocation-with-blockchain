package com.cognizant.osp.smartallocation.oabcs.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import com.cognizant.osp.smartallocation.oabcs.exception.AllocationServiceException;
import com.cognizant.osp.smartallocation.oabcs.response.entities.QueryTransactionResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AllocationServiceErrorHandler {

	public void handleError(ClientHttpResponse response) throws IOException {
		if (!response.getStatusCode().equals(HttpStatus.OK)) {
			throw new AllocationServiceException("Oracle cloud server error:"
					+ StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
		} else {
			verifyForSmartContractError(response);
		}
	}

	private void verifyForSmartContractError(ClientHttpResponse response) throws IOException {
		String responseString = StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
		QueryTransactionResponseEntity responseEntity = new ObjectMapper().readValue(responseString,
				QueryTransactionResponseEntity.class);
		if (responseEntity.getReturnCode().equalsIgnoreCase("failure")) {
			throw new AllocationServiceException(
					"Smart contract error" + new ObjectMapper().writeValueAsString(responseEntity.getInfo()));
		}
	}

}
