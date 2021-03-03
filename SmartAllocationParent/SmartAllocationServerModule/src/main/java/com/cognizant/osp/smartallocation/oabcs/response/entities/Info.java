package com.cognizant.osp.smartallocation.oabcs.response.entities;

import java.util.List;

public class Info {

	String proxyError;

	List<PeerError> peerErrors;

	public String getProxyError() {
		return proxyError;
	}

	public void setProxyError(String proxyError) {
		this.proxyError = proxyError;
	}

	public List<PeerError> getPeerErrors() {
		return peerErrors;
	}

	public void setPeerErrors(List<PeerError> peerErrors) {
		this.peerErrors = peerErrors;
	}

}
