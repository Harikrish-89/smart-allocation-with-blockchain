package com.cognizant.osp.smartallocation.authentication.response.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IdcsUser {
	
	private String userId;
	
	private String userDisplayName;
	
	private String userLoginId;
	
	private List<Group> groups;
	
	private List<AppRole> appRoles;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<AppRole> getAppRoles() {
		return appRoles;
	}

	public void setAppRoles(List<AppRole> appRoles) {
		this.appRoles = appRoles;
	}
}
