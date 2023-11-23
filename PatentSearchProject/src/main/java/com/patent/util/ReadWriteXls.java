package com.patent.util;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteXls {

	private static Workbook workbook;
	private static Cell cell;
	private static Row row;
	private List<String> applNums;

	public ReadWriteXls() {
		super();
		workbook = null;
	}

	public List<String> getapplNums(String filePath) throws IOException {
		applNums = new ArrayList<>();
		FileInputStream fileInputStream = new FileInputStream(filePath);
		System.out.println("Reading xls file to get appNums");
		workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheetAt(0);

		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row.getCell(0).getCellType().toString().equalsIgnoreCase("STRING")) {
				applNums.add(row.getCell(0).getStringCellValue());
			} else {
				applNums.add(String.valueOf(row.getCell(0).getNumericCellValue()));
			}
		}
		workbook.close();
		fileInputStream.close();
		return applNums;
	}

	public boolean writeExcel(Map<String, String> status, String filePath) {
		try (BufferedWriter bfw = new BufferedWriter(new FileWriter(filePath))) {
			for (String appNum : applNums) {
				if (status.containsKey(appNum)) {
					bfw.write(appNum + "," + status.get(appNum));
					bfw.newLine();
				}
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
