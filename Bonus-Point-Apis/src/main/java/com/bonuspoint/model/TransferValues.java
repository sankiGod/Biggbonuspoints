package com.bonuspoint.model;

import java.math.BigDecimal;

public class TransferValues {

	private int toBonusPoint;
	private BigDecimal toBonusAmount;
	private int fromBonusPoint;
	private BigDecimal fromBonusAmount;

	public TransferValues() {

	}

	public TransferValues(int toBonusPoint, BigDecimal toBonusAmount, int fromBonusPoint, BigDecimal fromBonusAmount) {
		super();
		this.toBonusPoint = toBonusPoint;
		this.toBonusAmount = toBonusAmount;
		this.fromBonusPoint = fromBonusPoint;
		this.fromBonusAmount = fromBonusAmount;
	}

	public int getToBonusPoint() {
		return toBonusPoint;
	}

	public void setToBonusPoint(int toBonusPoint) {
		this.toBonusPoint = toBonusPoint;
	}

	public int getFromBonusPoint() {
		return fromBonusPoint;
	}

	public void setFromBonusPoint(int fromBonusPoint) {
		this.fromBonusPoint = fromBonusPoint;
	}

	public BigDecimal getToBonusAmount() {
		return toBonusAmount;
	}

	public void setToBonusAmount(BigDecimal toBonusAmount) {
		this.toBonusAmount = toBonusAmount;
	}

	public BigDecimal getFromBonusAmount() {
		return fromBonusAmount;
	}

	public void setFromBonusAmount(BigDecimal fromBonusAmount) {
		this.fromBonusAmount = fromBonusAmount;
	}

}
