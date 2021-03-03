package com.cognizant.osp.smartallocation.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SmartAllocationServiceRestEntities {
	private String creds;

	private String role;

	private String org;
	
	private String clientId;

	public String getCreds() {
		return creds;
	}

	public void setCreds(String creds) {
		this.creds = creds;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
