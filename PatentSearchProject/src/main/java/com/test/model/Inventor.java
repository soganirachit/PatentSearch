package com.test.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Inventor {
	@Column(name = "INVENTOR_NAME")
	private String name = "";

	@Column(name = "INVENTOR_ADDRESS")
	private String address = "";

	@Column(name = "INVENTOR_COUNTRY")
	private String country = "";

	@Column(name = "INVENTOR_NATIONALITY")
	private String nationality = "";

	@Column(name = "INVENTOR_APPLICATION_NUMBER")
	private String applicationNumber = "";

	public Inventor() {
		super();
	}

	public Inventor(String name, String address, String country, String nationality, String applicationNumber) {
		super();
		this.name = name;
		this.address = address;
		this.country = country;
		this.nationality = nationality;
		this.applicationNumber = applicationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	@Override
	public String toString() {
		return "Inventor [name=" + name + ", address=" + address + ", country=" + country + ", nationality="
				+ nationality + ", applicationNumber=" + applicationNumber + "]";
	}

}
