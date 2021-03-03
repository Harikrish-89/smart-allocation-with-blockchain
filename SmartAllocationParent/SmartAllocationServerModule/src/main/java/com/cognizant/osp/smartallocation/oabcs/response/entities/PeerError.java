package com.cognizant.osp.smartallocation.oabcs.response.entities;

public class PeerError {
	
	private String peerId;
	
	private String errMsg;
	
	private boolean verified;
	
	public String getPeerId() {
		return peerId;
	}

	public void setPeerId(String peerId) {
		this.peerId = peerId;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
}
