package com.org.jdbc.assignment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class InsertDataIntoExcelsheetTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\data.xlsx");
		//fetch data from the excel file
		Workbook wb= WorkbookFactory.create(fis);
		//go the sheet of the excel file
		Sheet sh= wb.getSheet("Organization");
		//create row
		org.apache.poi.ss.usermodel.Row r= sh.createRow(6);
		Cell c= r.createCell(3);
		//write the value in the excel sheet
		c.setCellValue("hello");
		FileOutputStream fos=new FileOutputStream("C:\\\\Users\\\\-hp-\\\\Desktop\\\\ECLIPSE 2106\\\\crm.sdet37\\\\src\\\\test\\\\resources\\\\data.xlsx");
		wb.write(fos);
	}

}
