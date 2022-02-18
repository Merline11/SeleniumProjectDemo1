package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import resources.BaseAutomationCode;

public class LoginTest extends BaseAutomationCode{
	
	public WebDriver driver; 
	LandingPage landingPage;
	LoginPage loginPage;
	MyAccountPage myAccountPage;
	Logger logger = LogManager.getLogger(LoginTest.class.getName());
	
	@BeforeMethod
	public void openApplication() throws FileNotFoundException, IOException {
		logger.debug("Initializing the browser.........");
		driver = initializeBrowser();
		logger.debug("Navigating the Application url");
		driver.get(prop.getProperty("url"));
	}
	
	@Test(dataProvider = "getLoginData")
	public void login(String email,String password,String expectedResult) throws FileNotFoundException, IOException, InterruptedException {
		logger.debug("Landing page invoked");
		landingPage = new LandingPage(driver);
		landingPage.myAccountOption().click();
		landingPage.loginOption().click();
		
		logger.debug("Login page invoked");
		loginPage = new LoginPage(driver);
		//using properties file retrieving data
		/*
		 * loginPage.emailAddressField.sendKeys(prop.getProperty("email"));
		 * loginPage.passwordField.sendKeys(prop.getProperty("password"));
		 */
		
		//using dataprovider annotated method data - change parameters in method where we 
		//using dataprovider annotated method as per data
		loginPage.emailAddressField.sendKeys(email);
		loginPage.passwordField.sendKeys(password);
		loginPage.loginButton().click();
		
		logger.debug("Account page invoked");
		myAccountPage = new MyAccountPage(driver);
		
		//Assert.assertTrue(myAccountPage.editAccountInformationLink.isDisplayed());
		String actualResult = null;
		try {
			if(myAccountPage.editAccountInformationLink.isDisplayed()) {
				actualResult = "Success";
				logger.info("Successfully Logged in");
			}
			
		} catch (Exception e) {
			actualResult = "Failure";
			logger.error("Failed to loggin");
		}
		
		Assert.assertEquals(actualResult, expectedResult);
		
	}
	
	//Multiple data test(data driven) in testng achieved by DataProvider Annotated Methods
	
	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = {{"hello100@gmail.com","12345","Success"},{"dummy@gmail.com","dummy","Failure"}};
		return data;
	}
	
	@AfterMethod
	public void closure() {
		driver.close();
	}

}
