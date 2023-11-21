package com.patent.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "Patent_Details")
public class PatentDetails {

	@Column(name = "INVENTION_TITLE")
	private String inventionTitle = "";

	@Column(name = "PUBLICATION_NUMBER")
	private String publicationNumber;

	@Column(name = "PUBLICATION_DATE")
	private String PublicationDate;

	@Column(name = "PUBLICATION_TYPE")
	private String publicationType = "";

	@Id
	@Column(name = "APPLICATION_NUMBER")
	private String applicationNumber;

	@Column(name = "APPLICATION_FILING_DATE")
	private String applicationFilingDate;

	@Column(name = "PRIORITY_NUMBER")
	private String priorityNumber;

	@Column(name = "PRIORITY_COUNTRY")
	private String priorityCountry = "";

	@Column(name = "PRIORITY_DATE")
	private String priorityDate;

	@Column(name = "FIELD_OF_INVENTION")
	private String fieldOfInvention = "";

	@Column(name = "CLASSIFICATION_IPC")
	private String classification_IPC = "";

	@ElementCollection
	@JoinTable(name = "Inventor_Details", joinColumns = @JoinColumn(name = "APPLICATION_NUMBER"))
	@GenericGenerator(name = "hilo-gen", strategy = "hilo")
	@CollectionId(columns = { @Column(name = "Inventor_Id") }, generator = "", type = @Type(type = "int"))
	private List<Inventor> inventor;

	@ElementCollection
	@JoinTable(name = "Applicant_Details", joinColumns = @JoinColumn(name = "APPLICATION_NUMBER"))
	@GenericGenerator(name = "hilo-gen", strategy = "hilo")
	@CollectionId(columns = { @Column(name = "Applicant_Id") }, generator = "", type = @Type(type = "int"))
	private List<Applicant> applicant;

	@Column(name = "ABSTRACT_OF_INVENTION")
	private String abstractOfInvention = "";

	@Column(name = "COMPLETE_SPECIFICATION")
	private String completeSpecification = "";

	public PatentDetails() {
		super();
	}

	public String getInventionTitle() {
		return inventionTitle;
	}

	public void setInventionTitle(String inventionTitle) {
		this.inventionTitle = inventionTitle;
	}

	public String getPublicationNumber() {
		return publicationNumber;
	}

	public void setPublicationNumber(String publicationNumber) {
		this.publicationNumber = publicationNumber;
	}

	public String getPublicationDate() {
		return PublicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		PublicationDate = publicationDate;
	}

	public String getPublicationType() {
		return publicationType;
	}

	public void setPublicationType(String publicationType) {
		this.publicationType = publicationType;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getApplicationFilingDate() {
		return applicationFilingDate;
	}

	public void setApplicationFilingDate(String applicationFilingDate) {
		this.applicationFilingDate = applicationFilingDate;
	}

	public String getPriorityNumber() {
		return priorityNumber;
	}

	public void setPriorityNumber(String priorityNumber) {
		this.priorityNumber = priorityNumber;
	}

	public String getPriorityCountry() {
		return priorityCountry;
	}

	public void setPriorityCountry(String priorityCountry) {
		this.priorityCountry = priorityCountry;
	}

	public String getPriorityDate() {
		return priorityDate;
	}

	public void setPriorityDate(String priorityDate) {
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

	public PatentDetails(String inventionTitle, String publicationNumber, String publicationDate,
			String publicationType, String applicationNumber, String applicationFilingDate, String priorityNumber,
			String priorityCountry, String priorityDate, String fieldOfInvention, String classification_IPC,
			List<Inventor> inventor, List<Applicant> applicant, String abstractOfInvention,
			String completeSpecification) {
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
