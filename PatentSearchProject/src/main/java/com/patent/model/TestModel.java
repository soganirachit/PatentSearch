package com.patent.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BAS_TEST")
public class TestModel {

	@Id
	@Column(name = "INVENTOR_APPLICATION_NUMBER")
	private String applicationNumber = "";
	@Column(columnDefinition="CLOB")
	private String testName = "";
	@ElementCollection
	private List<Inventor> inventor = new ArrayList<>();

	public TestModel() {
		super();
	}

	public TestModel(String applicationNumber, String testName, List<Inventor> inventor) {
		super();
		this.applicationNumber = applicationNumber;
		this.testName = testName;
		this.inventor = inventor;
	}

	public List<Inventor> getInventor() {
		return inventor;
	}

	public void setInventor(List<Inventor> inventor) {
		this.inventor = inventor;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

}
