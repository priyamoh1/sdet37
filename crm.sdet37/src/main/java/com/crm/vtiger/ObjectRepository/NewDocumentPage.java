package com.crm.vtiger.ObjectRepository;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericUtilities.WebDriverUtility;

public class NewDocumentPage extends WebDriverUtility
{
	//initialization

		public NewDocumentPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}


		//declaration 

		@FindBy(name ="notes_title")
		private WebElement title;

		@FindBy(xpath ="//td[@id=\"cke_contents_notecontent\"]")
		private WebElement notesbox;
		
		@FindBy(xpath ="//input[@id=\"filename_I__\"]")
		private WebElement fileupload;
		
		@FindBy(xpath ="//input[@title=\"Save [Alt+S]\"]")
		private WebElement savebutton;
		
		@FindBy(xpath ="//span[@class=\"dvHeaderText\"]")
		private WebElement verify;
		
		//utilization

		public WebElement getSavebutton() {
			return savebutton;
		}

		public WebElement getVerify() {
			return verify;
		}

		public WebElement getTitle() {
			return title;
		}

		public WebElement getNotesbox() {
			return notesbox;
		}

		public WebElement getFileupload() {
			return fileupload;
		}
		public void createnewdoc(String notetitle,String notebox) throws AWTException
		{
			title.sendKeys(notetitle);
			notesbox.sendKeys(notebox);
			fileupload.click();
			StringSelection file=new StringSelection("\"C:\\Users\\-hp-\\Desktop\\SDET-37 MySQL cmds.txt\"");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file, null);
			RobotControlV();
			EnterAndRelease();
			
		}

		
}
