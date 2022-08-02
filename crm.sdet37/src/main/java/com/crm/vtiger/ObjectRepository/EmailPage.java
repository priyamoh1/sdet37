package com.crm.vtiger.ObjectRepository;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericUtilities.WebDriverUtility;

public class EmailPage extends WebDriverUtility
{
	//initialization

		public EmailPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}


		//declaration 

		@FindBy(xpath ="//a[.=\"Compose\"]")
		private WebElement composemail;
		
		@FindBy(xpath ="//img[@src=\"themes/softed/images/select.gif\"]")
		private WebElement conatactlookup;
		
		@FindBy(name ="search_text")
		private WebElement searchtext;
		
		@FindBy(name ="search")
		private WebElement searchbutton;
		
		@FindBy(name ="subject")
		private WebElement subject;
		
		@FindBy(id ="my_file_element")
		private WebElement uploadfile;
		
		@FindBy(xpath ="//a[.=\"HANZO FERRARI\"]")
		private WebElement selectlastname;
		
		@FindBy(xpath ="//td[@id=\\\"cke_contents_description\\\"]")
		private WebElement description;
		
		@FindBy(xpath ="//td[.=\\\"email \\\"]")
		private WebElement verifyemail;
		
		@FindBy(name ="button")
		private WebElement button;


		public WebElement getDescription() {
			return description;
		}
		public WebElement getSubject() {
			return subject;
		}
		public WebElement getVerifyemail() {
			return verifyemail;
		}
		public WebElement getUploadfile() {
			return uploadfile;
		}
		public WebElement getContactlookup() {
			return conatactlookup;
		}
		public WebElement getSearchtext() {
			return searchtext;
		}
		public WebElement getSearchbutton() {
			return searchbutton;
		}
		public WebElement getComposemail()
		{
			return composemail;
		}
		public WebElement getButton() {
			return button;
		}
		public WebElement getSelectlastname() {
			return selectlastname;
		}
		public void composenewmail(WebDriver driver,String contactname,String emailsubject,String emaildescription) throws AWTException
		{
			composemail.click();
			switchToWindow(driver, "Emails&action");
			conatactlookup.click();
			switchToWindow(driver, "Products&action");
			searchtext.sendKeys(contactname);
			searchbutton.click();
			selectlastname.click();
			switchToWindow(driver, "Emails&action");
			subject.sendKeys(emailsubject);
			uploadfile.click();
			StringSelection file=new StringSelection("\"C:\\Users\\-hp-\\Desktop\\SDET-37 MySQL cmds.txt\"");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file, null);
			RobotControlV();
			EnterAndRelease();
			description.sendKeys(emaildescription);
			button.click();
			switchToWindow(driver, "Emails&action");
			
			
			
		}

		
}
