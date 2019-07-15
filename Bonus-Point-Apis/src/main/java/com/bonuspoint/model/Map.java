package com.bonuspoint.model;

import java.math.BigDecimal;

public class Map {

	private int points;
	private BigDecimal amount;

	public Map() {

	}

	public Map(int points, BigDecimal amount) {
		this.points = points;
		this.amount = amount;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


}
