package com.tutorialsninja.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	
	SearchPage searchPage;
	HomePage homePage;
	public WebDriver driver;
	
	public SearchTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		
		driver=launchBrowserAndApp(p.getProperty("browser"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		homePage = new HomePage(driver);
		searchPage=homePage.search(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.isValidProductDisplayed());
		
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		homePage = new HomePage(driver);
		searchPage = homePage.search(dataProp.getProperty("invalidProduct"));
//		Assert.assertEquals(searchPage.retrieveNoProductSearchWarningMsg(), dataProp.getProperty("noProductTextInSearchResult"),"Invalid product search msg did not appear");
		Assert.assertEquals(searchPage.retrieveNoProductSearchWarningMsg(), "abcd","Invalid product search msg did not appear");
		
		
	}
	
	@Test(priority=3, dependsOnMethods = {"verifySearchWithValidProduct","verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		homePage = new HomePage(driver);
		searchPage = homePage.clickOnSearchBtn();
		Assert.assertEquals(searchPage.retrieveNoProductSearchWarningMsg(), dataProp.getProperty("noProductTextInSearchResult"),"Invalid product search msg did not appear");
	}

}
