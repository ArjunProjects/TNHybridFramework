package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	protected WebDriver driver;
	public Properties p;
	public Properties dataProp;
	public Base(){
		
		//common data for example url, browser, usernmae, password
		 p= new Properties();
		 File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		 
		 try {
			 FileInputStream fis = new FileInputStream(file);
			 p.load(fis);
		 }catch(Throwable e) {
			 e.printStackTrace();
		 }
		 
		 //test data
		 dataProp = new Properties();
		 File dataFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		 try {
			 FileInputStream dataFis = new FileInputStream(dataFile);
			 dataProp.load(dataFis);
		 }
		 catch(Throwable e) {
			 e.printStackTrace();
		 }
		 
		 
		
		 
	}
	
	public WebDriver launchBrowserAndApp(String browser) {
		
		if(browser.equals("chrome")) {
			
			driver = new ChromeDriver();
			
		} else if(browser.equals("firefox")) {
			
			driver = new FirefoxDriver();
			
		} else if(browser.equals("edge")) {
			
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(p.getProperty("url"));
		return driver;
	}

}
