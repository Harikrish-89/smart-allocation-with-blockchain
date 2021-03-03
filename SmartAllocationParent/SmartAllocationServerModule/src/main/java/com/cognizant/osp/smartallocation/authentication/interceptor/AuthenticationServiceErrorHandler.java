package com.cognizant.osp.smartallocation.authentication.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;


import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import com.cognizant.osp.smartallocation.authentication.exception.AuthenticationException;

public class AuthenticationServiceErrorHandler {

	public void handleError(ClientHttpResponse response) throws IOException {
		if (!response.getStatusCode().equals(HttpStatus.CREATED) && !response.getStatusCode().equals(HttpStatus.OK)) {
			throw new AuthenticationException("Unable to authenticate user or get access token:"
					+ StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
		}
	}
}
