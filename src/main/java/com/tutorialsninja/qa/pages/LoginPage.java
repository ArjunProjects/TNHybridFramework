package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;

	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginBtn;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement invalidEmailOrPasswordErrorMsg;
	
	public LoginPage(WebDriver driver ) {
		
		PageFactory.initElements(driver, this);
	}
	
	
	public void sendEmail(String email) {
		emailField.sendKeys(email);
	}
	
	public void sendPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void clickOnLoginBtn() {
		loginBtn.click();
	}
	
	public String retrieveInvalidEmailOrPasswordErrorMsg() {
		return invalidEmailOrPasswordErrorMsg.getText();
	}
	
	public void login(String email,String password) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginBtn.click();
		
	}
}
