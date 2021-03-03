package com.cognizant.osp.smartallocation.oabcs.service;

import java.net.URI;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cognizant.osp.smartallocation.common.interceptor.LoggingInterceptor;
import com.cognizant.osp.smartallocation.oabcs.interceptor.AllocationServiceInterceptor;
import com.cognizant.osp.smartallocation.oabcs.request.entities.QueryTransactionRequestEntity;
import com.cognizant.osp.smartallocation.oabcs.response.entities.QueryTransactionResponseEntity;
import com.cognizant.osp.smartallocation.oabcs.utils.SmartAllocationProperties;

public abstract class AllocationService {

	@Autowired
	private AllocationServiceInterceptor allocationServiceInterceptor;

	@Autowired
	private LoggingInterceptor loggingInterceptor;

	protected ResponseEntity<QueryTransactionResponseEntity> getSmartAllocationResponse(String creds, String client,
			String org, String clientId) {
		RestTemplate viewAllocationTemplate = new RestTemplate(
				new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		viewAllocationTemplate.setInterceptors(Arrays.asList(allocationServiceInterceptor, loggingInterceptor));
		ResponseEntity<QueryTransactionResponseEntity> entity = viewAllocationTemplate
				.exchange(getTransactionQueryUrl(), HttpMethod.POST,
						org == null ? getQueryTransactionRequestEntity(creds, client, clientId)
								: getLatestAllocationChangesQueryTransactionRequestEntity(creds, client, org),
						QueryTransactionResponseEntity.class);
		return entity;
	}

	protected abstract HttpEntity<QueryTransactionRequestEntity> getQueryTransactionRequestEntity(String creds,
			String client, String clientId);
	
	protected abstract HttpEntity<QueryTransactionRequestEntity> getLatestAllocationChangesQueryTransactionRequestEntity(String creds,
			String client, String org);

	private URI getTransactionQueryUrl() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SmartAllocationProperties.REST_PROXY_ROOT_URL);
		addTransactionPath(builder);
		return builder.build().toUri();
	}

	protected abstract void addTransactionPath(UriComponentsBuilder uriComponectBuilder);

}
