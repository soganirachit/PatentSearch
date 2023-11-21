package com.patent.prepareDataset;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.patent.model.Applicant;
import com.patent.model.Inventor;
import com.patent.model.PatentDetails;

public class MapRowDataToPojo {

	PatentDetails patentDetails = new PatentDetails();
	SimpleDateFormat formatter;
	String aplNum = "";

	public MapRowDataToPojo() {
		super();
		formatter = new SimpleDateFormat("dd/MM/yyyy");
	}

	public PatentDetails mapData(HtmlTable table) throws IndexOutOfBoundsException, ParseException {
		for (int i = 0; i < table.getRows().size(); i++) {
			if (table.getRow(i).getCells().size() > 0) {

				String cellData = table.getRow(i).getCell(0).asNormalizedText();

				switch (cellData) {
				case "Invention Title":
					patentDetails.setInventionTitle(table.getRow(i).getCell(1).asNormalizedText());
					break;
				case "Publication Number":
					patentDetails.setPublicationNumber(table.getRow(i).getCell(1).asNormalizedText());
					break;
				case "Publication Date":
					patentDetails.setPublicationDate(table.getRow(i).getCell(1).asNormalizedText());
					break;
				case "Publication Type":
					patentDetails.setPublicationType(table.getRow(i).getCell(1).asNormalizedText());
					break;
				case "Application Number":
					aplNum = table.getRow(i).getCell(1).asNormalizedText();
					patentDetails.setApplicationNumber(aplNum);
					break;
				case "Application Filing Date":
					patentDetails.setApplicationFilingDate(table.getRow(i).getCell(1).asNormalizedText());
					break;
				case "Priority Number":
					patentDetails.setPriorityNumber(table.getRow(i).getCell(1).asNormalizedText());
					break;
				case "Priority Country":
					patentDetails.setPriorityCountry(table.getRow(i).getCell(1).asNormalizedText());
					break;
				case "Priority Date":
					patentDetails.setPriorityDate(table.getRow(i).getCell(1).asNormalizedText());
					break;
				case "Field Of Invention":
					patentDetails.setFieldOfInvention(table.getRow(i).getCell(1).asNormalizedText());
					break;
				case "Classification (IPC)":
					patentDetails.setClassification_IPC(table.getRow(i).getCell(1).asNormalizedText());
					break;
				case "Inventor":
					parseInventor(table.getRow(i + 1), "//*[@id=\"home\"]/table/tbody/tr[13]/td/table");
					break;
				case "Applicant":
					parseApplicant(table.getRow(i + 1), "//*[@id=\"home\"]/table/tbody/tr[15]/td/table");
					break;
				default:
				}
				if (cellData.contains("Abstract:")) {
					String x = table.getRow(i).getCell(0).asNormalizedText();
					patentDetails.setAbstractOfInvention(x.substring(x.indexOf("Abstract:") + 9).trim());
				}
				if (cellData.contains("Complete Specification")) {
					String x = table.getRow(i).getCell(0).asNormalizedText();
					patentDetails
							.setCompleteSpecification(x.substring(x.indexOf("Complete Specification") + 22).trim());
				}
			}
		}
		//System.out.println(patentDetails.toString());
		return patentDetails;
	}

	public void parseInventor(HtmlTableRow row, String xPath) {

		Inventor inventor;
		List<Inventor> lst = new ArrayList<>();
		HtmlTable nestedTable = row.getCell(0).getFirstByXPath(xPath);
		int rowSize = nestedTable.getRowCount();
		int colSize = nestedTable.getRow(0).getCells().size();
		for (int i = 0; i < rowSize; i++) {
			inventor = new Inventor();
			for (int j = 0; j < colSize; j++) {
				if (nestedTable.getCellAt(0, j).asNormalizedText().equalsIgnoreCase("Name")) {
					inventor.setName(nestedTable.getCellAt(i, j).asNormalizedText());
				} else if (nestedTable.getCellAt(0, j).asNormalizedText().equalsIgnoreCase("Address")) {
					inventor.setAddress(nestedTable.getCellAt(i, j).asNormalizedText());
				} else if (nestedTable.getCellAt(0, j).asNormalizedText().equalsIgnoreCase("Country")) {
					inventor.setCountry(nestedTable.getCellAt(i, j).asNormalizedText());
				} else if (nestedTable.getCellAt(0, j).asNormalizedText().equalsIgnoreCase("Nationality")) {
					inventor.setNationality(nestedTable.getCellAt(i, j).asNormalizedText());
				}
				inventor.setApplicationNumber(aplNum);
			}
			lst.add(inventor);
		}

		patentDetails.setInventor(lst);
	}

	public void parseApplicant(HtmlTableRow row, String xPath) {

		Applicant applicant;
		List<Applicant> lst = new ArrayList<>();
		HtmlTable nestedTable = row.getCell(0).getFirstByXPath(xPath);
		int rowSize = nestedTable.getRowCount();
		int colSize = nestedTable.getRow(0).getCells().size();
		for (int i = 0; i < rowSize; i++) {
			applicant = new Applicant();
			for (int j = 0; j < colSize; j++) {
				if (nestedTable.getCellAt(0, j).asNormalizedText().equalsIgnoreCase("Name")) {
					applicant.setName(nestedTable.getCellAt(i, j).asNormalizedText());
				} else if (nestedTable.getCellAt(0, j).asNormalizedText().equalsIgnoreCase("Address")) {
					applicant.setAddress(nestedTable.getCellAt(i, j).asNormalizedText());
				} else if (nestedTable.getCellAt(0, j).asNormalizedText().equalsIgnoreCase("Country")) {
					applicant.setCountry(nestedTable.getCellAt(i, j).asNormalizedText());
				} else if (nestedTable.getCellAt(0, j).asNormalizedText().equalsIgnoreCase("Nationality")) {
					applicant.setNationality(nestedTable.getCellAt(i, j).asNormalizedText());
				}
				applicant.setApplicationNumber(aplNum);
			}
			lst.add(applicant);
		}

		patentDetails.setApplicant(lst);
	}

}
