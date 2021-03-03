package com.cognizant.osp.smartallocation;

public class Allocation {
	
	private String allocationId;
	private String clientOrg;
	private String clientId;
	private String parentPortfolio;
	private String childPortfolio;
	private String tolerances;
	private String targetWeight;
	private String targetWeightTolerance;
	private boolean shareClassConfigFlag;
	private String invstFrozenConfig;
	private String targetCash;
	private String overdraftLimit;
	
	private int minimumTradeSize;
	

	public Allocation() {
		
	}


	public String getAllocationId() {
		return allocationId;
	}


	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}


	public String getClientOrg() {
		return clientOrg;
	}


	public void setClientOrg(String clientOrg) {
		this.clientOrg = clientOrg;
	}


	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public String getTolerances() {
		return tolerances;
	}


	public void setTolerances(String tolerances) {
		this.tolerances = tolerances;
	}


	public String getTargetWeight() {
		return targetWeight;
	}


	public void setTargetWeight(String targetWeight) {
		this.targetWeight = targetWeight;
	}


	public boolean isShareClassConfigFlag() {
		return shareClassConfigFlag;
	}


	public void setShareClassConfigFlag(boolean shareClassConfigFlag) {
		this.shareClassConfigFlag = shareClassConfigFlag;
	}


	public String getInvstFrozenConfig() {
		return invstFrozenConfig;
	}


	public void setInvstFrozenConfig(String invstFrozenConfig) {
		this.invstFrozenConfig = invstFrozenConfig;
	}


	public String getTargetCash() {
		return targetCash;
	}


	public void setTargetCash(String targetCash) {
		this.targetCash = targetCash;
	}


	public int getMinimumTradeSize() {
		return minimumTradeSize;
	}


	public void setMinimumTradeSize(int minimumTradeSize) {
		this.minimumTradeSize = minimumTradeSize;
	}


	public String getChildPortfolio() {
		return childPortfolio;
	}


	public void setChildPortfolio(String childPortfolio) {
		this.childPortfolio = childPortfolio;
	}


	public String getParentPortfolio() {
		return parentPortfolio;
	}


	public void setParentPortfolio(String parentPortfolio) {
		this.parentPortfolio = parentPortfolio;
	}


	public String getTargetWeightTolerance() {
		return targetWeightTolerance;
	}


	public void setTargetWeightTolerance(String targetWeightTolerance) {
		this.targetWeightTolerance = targetWeightTolerance;
	}


	public String getOverdraftLimit() {
		return overdraftLimit;
	}


	public void setOverdraftLimit(String overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}
	
	
	

}
