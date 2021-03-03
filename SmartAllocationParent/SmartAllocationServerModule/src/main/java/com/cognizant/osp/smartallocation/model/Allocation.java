package com.cognizant.osp.smartallocation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Allocation {

	private String id;
	private String clientOrg;
	private Sector sector;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientOrg() {
		return clientOrg;
	}

	public void setClientOrg(String clientOrg) {
		this.clientOrg = clientOrg;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}
}
