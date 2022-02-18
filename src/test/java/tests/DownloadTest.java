package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resources.BaseAutomationCode;

public class DownloadTest extends BaseAutomationCode{
	
	public WebDriver driver;
	Logger logger = LogManager.getLogger(DownloadTest.class.getName());
	
	@Test
	public void download() throws FileNotFoundException, IOException {
		driver = initializeBrowser();
		driver.get(prop.getProperty("url"));
		logger.debug("Invoking Download Test");
		Assert.assertEquals(false, true);
	}
	
	@AfterMethod
	public void closure() {
		driver.close();
	}

}
