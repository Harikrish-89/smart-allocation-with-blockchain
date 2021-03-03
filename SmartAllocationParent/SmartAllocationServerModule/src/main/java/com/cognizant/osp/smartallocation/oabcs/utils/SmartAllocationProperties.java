package com.cognizant.osp.smartallocation.oabcs.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmartAllocationProperties {

	public static String IDCS_AUTHENTICATION_APP_ID;

	public static String REST_PROXY_ROOT_URL;

	public static String TRANSACTION_QUERY_PATH;

	public static String CHAINCODE_NAME;

	public static String CHAINCODE_VERSION;

	public static String CHANNEL_NAME;

	public static String TRANSACTION_INVOCATION_PATH;

	public static String IDCS_ROOT_URL;

	public static String IDCS_AUTHENTICATOR_PATH;

	public static String IDCS_ACCESS_TOKEN_PATH;

	public static String IDCS_APP_CLIENT_ID;

	public static String IDCS_APP_CLIENT_SECRET;

	public static String IDCS_GRANT_TYPE;

	public static String IDCS_ACCESS_SCOPE;

	@Value("${oabcs.query.transactions.path}")
	public void setTransactionQueryPath(String transactionQueryPath) {
		TRANSACTION_QUERY_PATH = transactionQueryPath;
	}

	@Value("${oabcs.invocation.transactions.path}")
	public void setTransactionInvocationPath(String transactionInvocationPath) {
		TRANSACTION_INVOCATION_PATH = transactionInvocationPath;
	}

	@Value("${oabcs.chaincode.name}")
	public void setChainCodeName(String chainCodeName) {
		CHAINCODE_NAME = chainCodeName;
	}

	@Value("${oabcs.chaincode.version}")
	public void setChainCodeVersion(String chainCodeVersion) {
		CHAINCODE_VERSION = chainCodeVersion;
	}

	@Value("${oabcs.channel.name}")
	public void setChannelName(String channelName) {
		CHANNEL_NAME = channelName;
	}

	@Value("${oabcs.base.rest.proxy.root.url}")
	public void setRestProxyRootUrl(String rootUrl) {
		REST_PROXY_ROOT_URL = rootUrl;
	}

	@Value("${idcs.root.url}")
	public void setIDCSRootUrl(String idcsRootUrl) {
		IDCS_ROOT_URL = idcsRootUrl;
	}

	@Value("${idcs.http.authenticator.path}")
	public void setAuthenticatorPath(String authenticatorPath) {
		IDCS_AUTHENTICATOR_PATH = authenticatorPath;
	}

	@Value("${idcs.access.token.path}")
	public void setAccessTokenPath(String accessTokenPath) {
		IDCS_ACCESS_TOKEN_PATH = accessTokenPath;
	}

	@Value("${idcs.application.client.id}")
	public void setClientId(String clientId) {
		IDCS_APP_CLIENT_ID = clientId;
	}

	@Value("${idcs.application.client.secret}")
	public void setClientSecret(String clientSecret) {
		IDCS_APP_CLIENT_SECRET = clientSecret;
	}

	@Value("${idcs.application.grant.type}")
	public void setGrantType(String grantType) {
		IDCS_GRANT_TYPE = grantType;
	}

	@Value("${idcs.access.scope}")
	public void setAccessScope(String accessScope) {
		IDCS_ACCESS_SCOPE = accessScope;
	}

	@Value("${idcs.http.authentication.app.id}")
	public void setAuthenticationAppId(String appId) {
		IDCS_AUTHENTICATION_APP_ID = appId;
	}
}
