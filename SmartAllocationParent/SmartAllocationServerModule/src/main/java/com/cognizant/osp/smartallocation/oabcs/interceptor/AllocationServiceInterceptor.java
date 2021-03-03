package com.cognizant.osp.smartallocation.oabcs.interceptor;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class AllocationServiceInterceptor implements ClientHttpRequestInterceptor {

	final static Logger log = LoggerFactory.getLogger(AllocationServiceInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		ClientHttpResponse response = execution.execute(request, body);
		checkForErrors(response);
		return response;
	}

	private void checkForErrors(ClientHttpResponse response) throws IOException {
		new AllocationServiceErrorHandler().handleError(response);
	}
}
