package ASSIGNMENT;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchingAllDataFromExcelAndShowInConsoleTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{

		FileInputStream fis=new FileInputStream("C:\\\\Users\\\\-hp-\\\\Desktop\\\\ECLIPSE 2106\\\\crm.sdet37\\\\src\\\\test\\\\resources\\\\data.xlsx");
		//fetch the data from the excel data
		Workbook wb= WorkbookFactory.create(fis);
		//go to the sheet of the excel file
		Sheet sh= wb.getSheet("vtiger");
		for(int i=0;i<=sh.getLastRowNum();i++)
		{
			Row r= sh.getRow(i);
			for(int j=0;j<=r.getLastCellNum();j++)
			{
				Cell c= r.getCell(j);
				DataFormatter dof= new DataFormatter();
				String data=dof.formatCellValue(c);
				//fetching the data to show in the console
				System.out.println(data);
			}
		}

	}

}
