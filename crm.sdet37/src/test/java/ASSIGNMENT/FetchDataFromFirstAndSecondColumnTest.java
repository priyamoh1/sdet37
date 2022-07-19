package ASSIGNMENT;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchDataFromFirstAndSecondColumnTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		//fetch data from excel sheet	
		FileInputStream fis=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\data.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		//enter the sheet of the excel file
		Sheet sh= wb.getSheet("Organization");
		int count=sh.getLastRowNum();
		for(int i=0;i<=count;i++)
		{
			Row r =sh.getRow(i);
			//store first column data
			String firstcelldata=r.getCell(0).getStringCellValue();
			//store second column data
			String secondceldata=r.getCell(1).getStringCellValue();
			//showing first and second column in the console
			System.out.println(firstcelldata+"\t"+secondceldata);
		}

	}

}
