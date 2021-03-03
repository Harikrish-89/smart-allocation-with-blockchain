package com.cognizant.osp.smartallocation.authentication.request.entities;

import com.cognizant.osp.smartallocation.oabcs.utils.SmartAllocationProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HttpAuthenticationRequest {

	private String credType = "authorization";

	private String creds;

	private String[] schemas = { "urn:ietf:params:scim:schemas:oracle:idcs:HTTPAuthenticator" };

	private boolean includeMemberships = true;

	private String appId = SmartAllocationProperties.IDCS_AUTHENTICATION_APP_ID;

	public String getCredType() {
		return credType;
	}

	public void setCredType(String credType) {
		this.credType = credType;
	}

	public String getCreds() {
		return creds;
	}

	public void setCreds(String creds) {
		this.creds = creds;
	}

	public String[] getSchemas() {
		return schemas;
	}

	public void setSchemas(String[] schemas) {
		this.schemas = schemas;
	}

	public boolean isIncludeMemberships() {
		return includeMemberships;
	}

	public void setIncludeMemberships(boolean includeMemberships) {
		this.includeMemberships = includeMemberships;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
