package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VerifyOrganizationPage
{
	//initialization
		public VerifyOrganizationPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//declaration 

		@FindBy(xpath ="//span[@class=\"dvHeaderText\"]")
		private WebElement verify;
		
		//utilization

		public WebElement getVerify() 
		{
			return verify;
		}
		public void Verify(String orgname)
		{
			String txt=verify.getText();
			if(txt.contains(orgname))
			{
				System.out.println("org is created");
			}
			else
			{
				System.out.println("org is not created");
			}

		}

}
