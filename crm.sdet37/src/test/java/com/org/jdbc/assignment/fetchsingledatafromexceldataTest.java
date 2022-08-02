package com.org.jdbc.assignment;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class fetchsingledatafromexceldataTest {

	public static void main(String[] args) throws IOException 
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\data.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		Sheet sh= wb.getSheet("Organization");
		Row rw= sh.getRow(1);
		Cell c= rw.getCell(4);
		Cell c1=rw.getCell(3);
		c.toString();
		System.out.println( c.toString());
		System.out.println( c1.toString());
		DataFormatter dof= new DataFormatter();
		String value=dof.formatCellValue(c);
		System.out.println(value);
	}

}
