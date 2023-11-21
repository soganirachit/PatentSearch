package com.test.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "")
public class PatentDetails {

	@Column(name = "INVENTION_TITLE")
	private String inventionTitle = "";

	@Column(name = "PUBLICATION_NUMBER")
	private int publicationNumber;

	@Column(name = "PUBLICATION_DATE")
	private Date PublicationDate;

	@Column(name = "PUBLICATION_TYPE")
	private String publicationType = "";

	@Id
	@Column(name = "APPLICATION_NUMBER")
	private int applicationNumber;

	@Column(name = "APPLICATION_FILING_DATE")
	private Date applicationFilingDate;

	@Column(name = "PRIORITY_NUMBER")
	private int priorityNumber;

	@Column(name = "PRIORITY_COUNTRY")
	private String priorityCountry = "";

	@Column(name = "PRIORITY_DATE")
	private Date priorityDate;

	@Column(name = "FIELD_OF_INVENTION")
	private String fieldOfInvention = "";

	@Column(name = "CLASSIFICATION_IPC")
	private String classification_IPC = "";

	@ElementCollection
	@Column(name = "INVENTOR")
	private List<Inventor> inventor;

	@ElementCollection
	@Column(name = "APPLICANT")
	private List<Applicant> applicant;

	@Column(name = "ABSTRACT_OF_INVENTION")
	private String abstractOfInvention = "";

	@Column(name = "COMPLETE_SPECIFICATION")
	private String completeSpecification = "";

	public PatentDetails() {
		super();
	}

	public PatentDetails(String inventionTitle, int publicationNumber, Date publicationDate, String publicationType,
			int applicationNumber, Date applicationFilingDate, int priorityNumber, String priorityCountry,
			Date priorityDate, String fieldOfInvention, String classification_IPC, List<Inventor> inventor,
			List<Applicant> applicant, String abstractOfInvention, String completeSpecification) {
		super();
		this.inventionTitle = inventionTitle;
		this.publicationNumber = publicationNumber;
		PublicationDate = publicationDate;
		this.publicationType = publicationType;
		this.applicationNumber = applicationNumber;
		this.applicationFilingDate = applicationFilingDate;
		this.priorityNumber = priorityNumber;
		this.priorityCountry = priorityCountry;
		this.priorityDate = priorityDate;
		this.fieldOfInvention = fieldOfInvention;
		this.classification_IPC = classification_IPC;
		this.inventor = inventor;
		this.applicant = applicant;
		this.abstractOfInvention = abstractOfInvention;
		this.completeSpecification = completeSpecification;
	}

	public String getInventionTitle() {
		return inventionTitle;
	}

	public void setInventionTitle(String inventionTitle) {
		this.inventionTitle = inventionTitle;
	}

	public int getPublicationNumber() {
		return publicationNumber;
	}

	public void setPublicationNumber(int publicationNumber) {
		this.publicationNumber = publicationNumber;
	}

	public Date getPublicationDate() {
		return PublicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		PublicationDate = publicationDate;
	}

	public String getPublicationType() {
		return publicationType;
	}

	public void setPublicationType(String publicationType) {
		this.publicationType = publicationType;
	}

	public int getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(int applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public Date getApplicationFilingDate() {
		return applicationFilingDate;
	}

	public void setApplicationFilingDate(Date applicationFilingDate) {
		this.applicationFilingDate = applicationFilingDate;
	}

	public int getPriorityNumber() {
		return priorityNumber;
	}

	public void setPriorityNumber(int priorityNumber) {
		this.priorityNumber = priorityNumber;
	}

	public String getPriorityCountry() {
		return priorityCountry;
	}

	public void setPriorityCountry(String priorityCountry) {
		this.priorityCountry = priorityCountry;
	}

	public Date getPriorityDate() {
		return priorityDate;
	}

	public void setPriorityDate(Date priorityDate) {
		this.priorityDate = priorityDate;
	}

	public String getFieldOfInvention() {
		return fieldOfInvention;
	}

	public void setFieldOfInvention(String fieldOfInvention) {
		this.fieldOfInvention = fieldOfInvention;
	}

	public String getClassification_IPC() {
		return classification_IPC;
	}

	public void setClassification_IPC(String classification_IPC) {
		this.classification_IPC = classification_IPC;
	}

	public List<Inventor> getInventor() {
		return inventor;
	}

	public void setInventor(List<Inventor> inventor) {
		this.inventor = inventor;
	}

	public List<Applicant> getApplicant() {
		return applicant;
	}

	public void setApplicant(List<Applicant> applicant) {
		this.applicant = applicant;
	}

	public String getAbstractOfInvention() {
		return abstractOfInvention;
	}

	public void setAbstractOfInvention(String abstractOfInvention) {
		this.abstractOfInvention = abstractOfInvention;
	}

	public String getCompleteSpecification() {
		return completeSpecification;
	}

	public void setCompleteSpecification(String completeSpecification) {
		this.completeSpecification = completeSpecification;
	}

	@Override
	public String toString() {
		return "PatentDetails [inventionTitle=" + inventionTitle + ", publicationNumber=" + publicationNumber
				+ ", PublicationDate=" + PublicationDate + ", publicationType=" + publicationType
				+ ", applicationNumber=" + applicationNumber + ", applicationFilingDate=" + applicationFilingDate
				+ ", priorityNumber=" + priorityNumber + ", priorityCountry=" + priorityCountry + ", priorityDate="
				+ priorityDate + ", fieldOfInvention=" + fieldOfInvention + ", classification_IPC=" + classification_IPC
				+ ", inventor=" + inventor + ", applicant=" + applicant + ", abstractOfInvention=" + abstractOfInvention
				+ ", completeSpecification=" + completeSpecification + "]";
	}


}
