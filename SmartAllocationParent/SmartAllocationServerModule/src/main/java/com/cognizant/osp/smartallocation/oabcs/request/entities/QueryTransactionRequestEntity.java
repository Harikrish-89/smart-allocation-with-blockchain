package com.cognizant.osp.smartallocation.oabcs.request.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryTransactionRequestEntity {

	private String channel;

	private String chaincode;

	private String chaincodeVer;

	private String method;

	private List<? extends Object> args;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<? extends Object> getArgs() {
		return args;
	}

	public void setArgs(List<? extends Object> args) {
		this.args = args;
	}

	public String getChaincode() {
		return chaincode;
	}

	public void setChaincode(String chaincode) {
		this.chaincode = chaincode;
	}

	public String getChaincodeVer() {
		return chaincodeVer;
	}

	public void setChaincodeVer(String chaincodeVer) {
		this.chaincodeVer = chaincodeVer;
	}
}
