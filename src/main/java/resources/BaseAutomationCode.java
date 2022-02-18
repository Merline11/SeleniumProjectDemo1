package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseAutomationCode {
		
	WebDriver driver;
	public Properties prop = new Properties();
	
	@SuppressWarnings("deprecation")
	public WebDriver initializeBrowser() throws FileNotFoundException, IOException {		
				
		prop.load(new FileInputStream(new File(System.getProperty("user.dir")+"//src//main//java//resources//data.properties")));
		
		String browserName = prop.getProperty("browser");
		System.out.println(browserName);
		
		if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if(browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}

	public String takeScreenshot(String testMethodName,WebDriver driver) throws IOException {
		
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir")+"\\screenshots\\"+ testMethodName+ ".png";
		FileUtils.copyFile(sourceFile, new File(destinationPath));
		
		return destinationPath;
		
	}
}
