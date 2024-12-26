package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropdownMenu;
	
	@FindBy(xpath="//a[text()='Login']")
	private WebElement selectLoginOption;
	
	@FindBy(xpath="//a[text()='Register']")
	private WebElement selectRegisterOption;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchBoxField;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchBtn;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickMyAccount() {
		myAccountDropdownMenu.click();;
	}
	
	public LoginPage clickLoginOption() {
		selectLoginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage clickRegisterOption() {
		selectRegisterOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterProductIntoSearchBox(String product) {
		searchBoxField.sendKeys(product);
	}
	
	public SearchPage clickOnSearchBtn() {
		searchBtn.click();
		return new SearchPage(driver);
	}
	
	public SearchPage search(String product) {
		searchBoxField.sendKeys(product);
		searchBtn.click();
		return new SearchPage(driver); 
	}

}
