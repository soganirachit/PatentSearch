package com.patent.userInteraction.dto;

import org.springframework.stereotype.Component;

@Component
public class InputData {

	private String keyWords = "";

	private String fieldOfInvention = "";

	private String applicationFilingDate = "";

	public InputData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InputData(String keyWords, String fieldOfInvention, String applicationFilingDate) {
		super();
		this.keyWords = keyWords;
		this.fieldOfInvention = fieldOfInvention;
		this.applicationFilingDate = applicationFilingDate;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getFieldOfInvention() {
		return fieldOfInvention;
	}

	public void setFieldOfInvention(String fieldOfInvention) {
		this.fieldOfInvention = fieldOfInvention;
	}

	public String getApplicationFilingDate() {
		return applicationFilingDate;
	}

	public void setApplicationFilingDate(String applicationFilingDate) {
		this.applicationFilingDate = applicationFilingDate;
	}

	@Override
	public String toString() {
		return "InputData [keyWords=" + keyWords + ", fieldOfInvention=" + fieldOfInvention
				+ ", applicationFilingDate=" + applicationFilingDate + "]";
	}

}
