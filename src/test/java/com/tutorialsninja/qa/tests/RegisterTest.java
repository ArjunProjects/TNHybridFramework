package com.tutorialsninja.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	
	RegisterPage registerPage;
	AccountSuccessPage successPage;
	public WebDriver driver;
	
	public RegisterTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver=launchBrowserAndApp(p.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		registerPage = homePage.clickRegisterOption();
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	
	@Test(priority=1)
	public void verifyRegisteringAccountWithMandatoryFields() throws InterruptedException {
		registerPage = new RegisterPage(driver);
		successPage=registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone"), p.getProperty("validPassword"));
		Assert.assertEquals(successPage.retrieveAccountSuccessMsgHeading(), dataProp.getProperty("accountCreatMsg"),"success msg did not appear");
		
	}
	
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() throws InterruptedException {
		registerPage = new RegisterPage(driver);
		successPage=registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone"), p.getProperty("validPassword"));
		Assert.assertEquals(successPage.retrieveAccountSuccessMsgHeading(), dataProp.getProperty("accountCreatMsg"),"success msg did not appear");
	}
	
	@Test(priority=3)
	public void registeringAccountWithExistingEmail() throws InterruptedException {
		registerPage = new RegisterPage(driver);
		registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), p.getProperty("validEmail"), dataProp.getProperty("telephone"), p.getProperty("validPassword"));
		Thread.sleep(10);
		Assert.assertTrue(registerPage.retrieveDuplicateEmailWarningMsg().contains(dataProp.getProperty("duplicateEmailWarning")),"warning msg did not appear for existing email");
		
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
		registerPage = new RegisterPage(driver);
		registerPage.clickOnContinueBtn();
		String actualPrivacyWarningMsg = registerPage.retrievePrivacyPolicyWarningMsg();
		Assert.assertTrue(actualPrivacyWarningMsg.contains(dataProp.getProperty("privacyWarning")),"privacy warning message did not appear");
		
		String actualFirstNameErrorMsg = registerPage.retrieveFirstnameWarningMsg();
		Assert.assertTrue(actualFirstNameErrorMsg.contains(dataProp.getProperty("firstNameWarning")),"firstname error message did not appear");
		
		String actualLastNameErrorMsg = registerPage.retrieveLastnameWarningMsg();
		Assert.assertTrue(actualLastNameErrorMsg.contains(dataProp.getProperty("lastNameWarning")),"lastname error message did not appear");
		
		String actualEmailErrorMsg = registerPage.retrieveEmailWarningMsg();
		Assert.assertTrue(actualEmailErrorMsg.contains(dataProp.getProperty("emailWarning")),"email error message did not appear");
		
		String actualTelephoneErrorMsg = registerPage.retrieveTelephoneWarningMsg();
		Assert.assertTrue(actualTelephoneErrorMsg.contains(dataProp.getProperty("telephoneWarning")),"telephone error message did not appear");
		
		String actualPasswordErrorMsg = registerPage.retrievePasswordWarningMsg();
		Assert.assertTrue(actualPasswordErrorMsg.contains(dataProp.getProperty("passwordWarning")),"password error message did not appear");
		
		
	}

}
