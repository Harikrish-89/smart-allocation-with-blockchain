package com.cognizant.osp.smartallocation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fund {

	private String id;
	private String name;
	private double targetWeightPercent;
	private double tolerancePercent;
	private int minimumTradeSize;
	private String invstFrozenConfig;
	private double overdraftLimitPercent;
	private double targetCashPercent;

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

	public double getTargetWeightPercent() {
		return targetWeightPercent;
	}

	public void setTargetWeightPercent(double targetWeightPercent) {
		this.targetWeightPercent = targetWeightPercent;
	}

	public double getTolerancePercent() {
		return tolerancePercent;
	}

	public void setTolerancePercent(double tolerancePercent) {
		this.tolerancePercent = tolerancePercent;
	}

	public int getMinimumTradeSize() {
		return minimumTradeSize;
	}

	public void setMinimumTradeSize(int minimumTradeSize) {
		this.minimumTradeSize = minimumTradeSize;
	}

	public String getInvstFrozenConfig() {
		return invstFrozenConfig;
	}

	public void setInvstFrozenConfig(String invstFrozenConfig) {
		this.invstFrozenConfig = invstFrozenConfig;
	}

	public double getOverdraftLimitPercent() {
		return overdraftLimitPercent;
	}

	public void setOverdraftLimitPercent(double overdraftLimitPercent) {
		this.overdraftLimitPercent = overdraftLimitPercent;
	}

	public double getTargetCashPercent() {
		return targetCashPercent;
	}

	public void setTargetCashPercent(double targetCashPercent) {
		this.targetCashPercent = targetCashPercent;
	}
}
