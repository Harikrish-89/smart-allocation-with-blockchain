package com.cognizant.osp.smartallocation.authentication.service;

import java.net.URI;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cognizant.osp.smartallocation.authentication.helper.AccessTokenHelper;
import com.cognizant.osp.smartallocation.authentication.interceptor.AuthenticationInterceptor;
import com.cognizant.osp.smartallocation.authentication.request.entities.HttpAuthenticationRequest;
import com.cognizant.osp.smartallocation.authentication.response.entities.IdcsUser;
import com.cognizant.osp.smartallocation.common.interceptor.LoggingInterceptor;
import com.cognizant.osp.smartallocation.oabcs.utils.SmartAllocationProperties;

@Service
public class AuthenticationService {

	@Autowired
	private AccessTokenHelper accessTokenHelper;

	@Autowired
	private AuthenticationInterceptor authenticationInterceptor;

	@Autowired
	private LoggingInterceptor loggingInterceptor;

	public IdcsUser getAuthenticatedUser(String userNamePassword) {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setOutputStreaming(false);
		RestTemplate accessTokenTemplate = new RestTemplate(
				new BufferingClientHttpRequestFactory(requestFactory));
		accessTokenTemplate.setInterceptors(Arrays.asList(authenticationInterceptor, loggingInterceptor));
		ResponseEntity<IdcsUser> response = accessTokenTemplate.exchange(getAuthticatorUrl(), HttpMethod.POST,
				getAuthenticationRequest(userNamePassword), IdcsUser.class);
		return response.getBody();
	}

	private HttpEntity<HttpAuthenticationRequest> getAuthenticationRequest(String userNamePassword) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + accessTokenHelper.getAccessToken());
		HttpAuthenticationRequest authRequest = new HttpAuthenticationRequest();
		authRequest.setCreds("Basic "+userNamePassword);
		return new HttpEntity<HttpAuthenticationRequest>(authRequest, headers);
	}

	private URI getAuthticatorUrl() {
		return UriComponentsBuilder.fromHttpUrl(SmartAllocationProperties.IDCS_ROOT_URL)
				.path(SmartAllocationProperties.IDCS_AUTHENTICATOR_PATH).build().toUri();
	}
	
	
}
