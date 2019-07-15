package com.bonuspoint.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MerchantAddress {

	@Column(name="shop_no")
	private String shopNo;
	
	@Column(name="lane_no")
	private String laneNo;
	
	@Column(name="lane_name")
	private String laneName;
	
	@Column(name="area_name")
	private String areaName;
	
	@Column(name="landmark")
	private String landmark;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="pinCode")
	private String pin;
	
	@Column(name="country")
	private String country;
	
	
	
	public MerchantAddress() {
		
	}

	public MerchantAddress(String shopNo, String laneNo, String laneName, String areaName, String landmark, String city,
			String state, String pin, String country) {
		super();
		this.shopNo = shopNo;
		this.laneNo = laneNo;
		this.laneName = laneName;
		this.areaName = areaName;
		this.landmark = landmark;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.country = country;
	}
	
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public String getLaneNo() {
		return laneNo;
	}
	public void setLaneNo(String laneNo) {
		this.laneNo = laneNo;
	}
	public String getLaneName() {
		return laneName;
	}
	public void setLaneName(String laneName) {
		this.laneName = laneName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [shopNo=" + shopNo + ", laneNo=" + laneNo + ", laneName=" + laneName + ", areaName=" + areaName
				+ ", landmark=" + landmark + ", city=" + city + ", state=" + state + ", pin=" + pin + ", country="
				+ country + "]";
	}
	
	
}
