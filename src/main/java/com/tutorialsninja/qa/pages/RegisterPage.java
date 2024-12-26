package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(css="input[name='firstname']")
	private WebElement firstNameField;
	
	@FindBy(css="input[name='lastname']")
	private WebElement lastNameField;
	
	@FindBy(css="input[name='email']")
	private WebElement emailField;
	
	@FindBy(css="input[name='telephone']")
	private WebElement telephoneField;
	
	@FindBy(css="input[name='password']")
	private WebElement passwordField;
	
	@FindBy(css="input[name='confirm']")
	private WebElement confirmField;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterField;
	
	@FindBy(css="input[name='agree']")
	private WebElement agreeField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueField;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailWarningMsg;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarningMsg;
	
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstnameWarningMsg;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastnameWarningMsg;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarningMsg;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarningMsg;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarningMsg;
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstname) {
		firstNameField.sendKeys(firstname);
	}
	
	public void enterLastName(String lastname) {
		lastNameField.sendKeys(lastname);
	}
	
	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}
	
	public void enterTelephone(String phone) {
		telephoneField.sendKeys(phone);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void enterConfirmPassword(String confirmPassword) {
		confirmField.sendKeys(confirmPassword);
	}
	
	public void clickOnAgreeCheckBox() {
		agreeField.click();
	}
	
	public AccountSuccessPage clickOnContinueBtn() {
		continueField.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectYesOnNewsLetterCheckBox() {
		yesNewsLetterField.click();	
	}
	
	public String retrieveDuplicateEmailWarningMsg() {
		return duplicateEmailWarningMsg.getText();
	}
	
	public String retrievePrivacyPolicyWarningMsg() {
		return privacyPolicyWarningMsg.getText();
	}
	
	public String retrieveFirstnameWarningMsg() {
		return firstnameWarningMsg.getText();
	}
	
	public String retrieveLastnameWarningMsg() {
		return lastnameWarningMsg.getText();
	}
	
	public String retrieveEmailWarningMsg() {
		return emailWarningMsg.getText();
	}
	
	public String retrieveTelephoneWarningMsg() {
		return telephoneWarningMsg.getText();
	}
	
	public String retrievePasswordWarningMsg() {
		return passwordWarningMsg.getText();
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstname, String lastname, String email, String phone, String password ) {
		firstNameField.sendKeys(firstname);
		lastNameField.sendKeys(lastname);
		emailField.sendKeys(email);
		telephoneField.sendKeys(phone);
		passwordField.sendKeys(password);
		confirmField.sendKeys(password);
		agreeField.click();
		continueField.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllFields(String firstname, String lastname, String email, String phone, String password ) {
		firstNameField.sendKeys(firstname);
		lastNameField.sendKeys(lastname);
		emailField.sendKeys(email);
		telephoneField.sendKeys(phone);
		passwordField.sendKeys(password);
		confirmField.sendKeys(password);
		yesNewsLetterField.click();	
		agreeField.click();
		continueField.click();
		return new AccountSuccessPage(driver);
	}

}
