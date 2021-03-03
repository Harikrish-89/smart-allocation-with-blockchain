package com.cognizant.osp.smartallocation.authentication.helper;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cognizant.osp.smartallocation.authentication.exception.AuthenticationException;
import com.cognizant.osp.smartallocation.authentication.interceptor.AuthenticationInterceptor;
import com.cognizant.osp.smartallocation.common.interceptor.LoggingInterceptor;
import com.cognizant.osp.smartallocation.oabcs.utils.SmartAllocationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class AccessTokenHelper {

	private static final Logger log = LoggerFactory.getLogger(AccessTokenHelper.class);
	
	@Autowired
	private AuthenticationInterceptor authenticationInterceptor;

	@Autowired
	private LoggingInterceptor loggingInterceptor;

	public String getAccessToken() {
		RestTemplate accessTokenTemplate = new RestTemplate(
				new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		accessTokenTemplate.setInterceptors(Arrays.asList(
				new BasicAuthorizationInterceptor(SmartAllocationProperties.IDCS_APP_CLIENT_ID,
						SmartAllocationProperties.IDCS_APP_CLIENT_SECRET),
				authenticationInterceptor, loggingInterceptor));
		ResponseEntity<String> response = accessTokenTemplate.exchange(getAccessTokenUrl(),
				HttpMethod.POST, getAccessTokenRequestParams(), String.class);
		return getAccessTokenFromResponse(response);
	}

	private String getAccessTokenFromResponse(ResponseEntity<String> response) {
		String accessToken = null;
		try {
			final ObjectNode node = new ObjectMapper().readValue(response.getBody(), ObjectNode.class);
			accessToken = getTokenFromNode(node);
		} catch (IOException e) {
			log.error("unable to parse access token json response:",e);
			throw new AuthenticationException("unable to parse access token json response:",e);
		}
		return accessToken;
	}

	private String getTokenFromNode(ObjectNode node) {
		if(node.has("access_token")) {
			return node.get("access_token").asText();
		}else {
			throw new AuthenticationException("Access token not found in response");
		}
	}

	private HttpEntity<MultiValueMap<String, String>> getAccessTokenRequestParams() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("grant_type", SmartAllocationProperties.IDCS_GRANT_TYPE);
		map.add("scope", SmartAllocationProperties.IDCS_ACCESS_SCOPE);
		//Commented for new api
		//map.add("client_id", SmartAllocationProperties.IDCS_APP_CLIENT_ID);
		//map.add("client_secret", SmartAllocationProperties.IDCS_APP_CLIENT_SECRET);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		return request;
	}

	private URI getAccessTokenUrl() {
		return UriComponentsBuilder.fromHttpUrl(SmartAllocationProperties.IDCS_ROOT_URL)
				.path(SmartAllocationProperties.IDCS_ACCESS_TOKEN_PATH).build().toUri();
	}
}
