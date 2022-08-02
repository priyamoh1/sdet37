package com.crm.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericUtilities.WebDriverUtility;

public class NewProductsPage extends WebDriverUtility
{
	//initialization

	public NewProductsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	//declaration 
	@FindBy(xpath ="//img[@src=\"themes/softed/images/btnL3Add.gif\"")
	private WebElement newproduct;
	
	@FindBy(name ="productname")
	private WebElement productname;
	
	@FindBy(name ="search_text")
	private WebElement searchtext;
	
	@FindBy(name ="search")
	private WebElement searchbutton;
	
	
	@FindBy(xpath ="//img[@src=\\\"themes/softed/images/select.gif\\\"]")
	private WebElement vendorlookup;
	
	@FindBy(xpath ="//a[.='mohanty']")
	private WebElement vendorname;
	
	@FindBy(xpath ="//input[@title=\"Save [Alt+S]\"]")
	private WebElement savebutton;
	
	//utilization
	
	public WebElement getSavebutton() {
		return savebutton;
	}

	public WebElement getSearchtext() {
		return searchtext;
	}

	public WebElement getSearchbutton() {
		return searchbutton;
	}

	public WebElement getVendorname() {
		return vendorname;
	}

	public WebElement getProductname() {
		return productname;
	}

	public WebElement getVendorlookup() {
		return vendorlookup;
	}

	public WebElement getProduct() 
	{
		return newproduct;
	}
	
	public void switchtochild(WebDriver driver)
	{
		switchToWindow(driver, "Vendors&action");
	}
	
	public void switchtoparentwindow(WebDriver driver)
	{
		switchToWindow(driver, "Vendors&action");
	}



}
