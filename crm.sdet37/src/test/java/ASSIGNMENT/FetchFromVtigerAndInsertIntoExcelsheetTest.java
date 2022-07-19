package ASSIGNMENT;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FetchFromVtigerAndInsertIntoExcelsheetTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis1=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\commondata.properties");
		Properties property=new Properties();
		property.load(fis1); 
		//get the properties from the propertyfile using getproperty
		String URL=property.getProperty("url");
		String USERNAME= property.getProperty("username");
		String PASSWORD=property.getProperty("password");
		//String BROWSER=property.getProperty("browser");
		//fetch data from the excel sheet
		FileInputStream fis=new FileInputStream("C:\\Users\\-hp-\\Desktop\\ECLIPSE 2106\\crm.sdet37\\src\\test\\resources\\data.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		//enter the sheet of the excel file
		Sheet sh= wb.getSheet("vtiger");
		WebDriverManager.chromedriver().setup();
		//open the browser
		WebDriver driver=new ChromeDriver();
		//wait for 4 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		//maximize windows
		driver.manage().window().maximize();
		//enter the url
		driver.get(URL);
		//enter the username into the textfield
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		//enter the password into the password textfield
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		//click on the login button
		driver.findElement(By.id("submitButton")).click();
		//store all the links in a variable
		List<WebElement> list = driver.findElements(By.xpath("//a"));
		//
		int count=list.size();
		for(int i=0;i<count;i++)
		{
			Row r= sh.createRow(i);
			Cell c=r.createCell(i);
			c.setCellValue(list.get(i).getAttribute("href"));

		}
		FileOutputStream fos=new FileOutputStream("C:\\\\Users\\\\-hp-\\\\Desktop\\\\ECLIPSE 2106\\\\crm.sdet37\\\\src\\\\test\\\\resources\\\\data.xlsx");
		//write data into the excel sheet
		wb.write(fos);

	}

}
