package com.cognizant.osp.smartallocation.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sector {

	private String id;
	private String name;
	private ArrayList<Fund> funds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Fund> getFunds() {
		return funds;
	}

	public void setFunds(ArrayList<Fund> funds) {
		this.funds = funds;
	}
}
