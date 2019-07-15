package com.bonuspoint.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class ProjectAddress {

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "pinCode")
	@Size(max=6, min=6 , message="Invalid Pin Code")
	private String pin;

	@Column(name = "country")
	private String country;

	public ProjectAddress() {

	}

	public ProjectAddress(String city, String state, String pin, String country) {
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.country = country;
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
		return "ProjectAddress [city=" + city + ", state=" + state + ", pin=" + pin + ", country=" + country + "]";
	}

	
}
