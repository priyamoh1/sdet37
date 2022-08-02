package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericUtilities.WebDriverUtility;

public class NewOrganizationPage extends WebDriverUtility 
{
	//initialization
	public NewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//declaration 

	@FindBy(xpath ="//img[@alt=\"Create Organization...\"]")
	private WebElement neworganization;

	@FindBy(name ="industry")
	private WebElement industry;

	@FindBy(name ="accountname")
	private WebElement accountname;
	
	@FindBy(name ="accounttype")
	private WebElement accounttype;

	@FindBy(xpath ="//input[@title=\"Save [Alt+S]\"]")
	private WebElement savebutton;

	@FindBy(xpath ="//img[@src=\"themes/softed/images/menuDnArrow.gif\"]")
	private WebElement moredropdown;

	@FindBy(xpath ="//span[@class=\"dvHeaderText\"]")
	private WebElement verify;

	//utilization 

	public WebElement getNeworganization() 
	{
		return neworganization;
	}

	public WebElement getAccounttype() {
		return accounttype;
	}

	public WebElement getVerify() {
		return verify;
	}

	public WebElement getSavebutton() {
		return savebutton;
	}

	public WebElement getAccountname()
	{
		return accountname;
	}

	public void createNewOrganization(WebDriver driver,String acname,String industrytype,String accounttypedp)
	{
		//		neworganization.click();
		accountname.sendKeys(acname);
		select(industrytype, industry);
		select(accounttypedp, accounttype);
		savebutton.click();
	}

}
