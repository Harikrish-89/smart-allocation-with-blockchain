package com.cognizant.osp.smartallocation.common.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.cognizant.osp.smartallocation.oabcs.interceptor.AllocationServiceInterceptor;

@Component
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

	final static Logger log = LoggerFactory.getLogger(AllocationServiceInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		traceRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		traceResponse(response);
		return response;
	}

	private void traceRequest(HttpRequest request, byte[] body) throws IOException {
		log.info("===========================request begin================================================");
		log.info("URI         : {}", request.getURI());
		log.info("Method      : {}", request.getMethod());
		log.info("Headers     : {}", request.getHeaders());
		log.info("Request body: {}", new String(body, "UTF-8"));
		log.info("==========================request end================================================");
	}

	private void traceResponse(ClientHttpResponse response) throws IOException {
		log.info("============================response begin==========================================");
		log.info("Status code  : {}", response.getStatusCode());
		log.info("Status text  : {}", response.getStatusText());
		log.info("Headers      : {}", response.getHeaders());
		log.info("Response body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
		log.info("=======================response end=================================================");
	}
}

