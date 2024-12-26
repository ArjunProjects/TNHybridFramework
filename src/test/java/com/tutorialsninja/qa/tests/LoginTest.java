package com.tutorialsninja.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	
	LoginPage loginPage;
	public WebDriver driver;
	
	//we are loading properties
	public LoginTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setup() {
		
		driver = launchBrowserAndApp(p.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		loginPage = homePage.clickLoginOption();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1, dataProvider="validCreds")
	public void loginWithValidCreds(String email, String password) throws InterruptedException {
		loginPage.login(email, password);
		Thread.sleep(10);
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertEquals(accountPage.displayEditInfoLink(),"Edit your account information","did not display expected text link");
		
		
	}
	
	@DataProvider(name="validCreds")
	public Object[][] supplyData() {
		
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	
	
	@Test(priority=2)
	public void loginWithInvalidEmailAndValidPassword() {
		loginPage.login(Utilities.generateEmailWithTimeStamp(), p.getProperty("validPassword"));
		Assert.assertEquals(loginPage.retrieveInvalidEmailOrPasswordErrorMsg(), "Warning: No match for E-Mail Address and/or Password.","did not get error message");
	}
	
	@Test(priority=3)
	public void loginWithValidEmailAndInvalidPassword() {
		loginPage.login(p.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		Assert.assertEquals(loginPage.retrieveInvalidEmailOrPasswordErrorMsg(), "Warning: No match for E-Mail Address and/or Password.","did not get error message");
	}
	
	@Test(priority=4)
	public void loginWithInValidEmailAndInvalidPassword() {
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		Assert.assertEquals(loginPage.retrieveInvalidEmailOrPasswordErrorMsg(), "Warning: No match for E-Mail Address and/or Password.","did not get error message");
	}
	
	@Test(priority=5)
	public void loginWithEmptyCreds() {
		loginPage.clickOnLoginBtn();
		Assert.assertEquals(loginPage.retrieveInvalidEmailOrPasswordErrorMsg(), "Warning: No match for E-Mail Address and/or Password.","did not get error message");
	}
	
	

}
