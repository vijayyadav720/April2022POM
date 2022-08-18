package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	public static Workbook book;
	public static Sheet sheet;
	public static Object[][] getTestData(String sheetName) {
		Object data[][] = null;
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/testData/testExcelFile.xlsx");
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
		} catch (IOException | InvalidFormatException e) {
			e.printStackTrace();
		}
		
		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(byte i=0; i<sheet.getLastRowNum();i++) {
			 for(byte j=0; j<sheet.getRow(0).getLastCellNum();j++) {
				 data[i][j]= sheet.getRow(i+1).getCell(j).toString();
			 }
		} //for i loop ends
		
		return data;
		
	}
	
}













