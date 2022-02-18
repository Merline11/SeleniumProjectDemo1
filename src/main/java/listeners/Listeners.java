package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.BaseAutomationCode;
import utilties.ExtentReporter;

public class Listeners extends BaseAutomationCode implements ITestListener {
	
	public WebDriver driver;
	
	//Extent Report thread for execution by order
	ExtentReports extentReports = ExtentReporter.getExtendReports();
	ExtentTest extentTest;
	
	//Extent Report thread safe for parallel execution
	ThreadLocal<ExtentTest> extentThread = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		//Extent Report thread for execution by order
		//extentTest = extentReports.createTest(result.getName()+" has started");
		
		//Extent Report thread safe for parallel execution
		extentTest = extentReports.createTest(result.getName()+" has started");
		extentThread.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//Extent Report thread for execution by order
		//extentTest.log(Status.PASS, result.getName()+ "got passed");
		
		//Extent Report thread safe for parallel execution
		extentThread.get().log(Status.PASS, result.getName()+ "got passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//Extent Report thread for execution by order
		//extentTest.fail(result.getThrowable());
		
		//Extent Report thread safe for parallel execution
		extentThread.get().fail(result.getThrowable());
			
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		try {
			String destionationPath = takeScreenshot(result.getName(),driver);
			extentThread.get().addScreenCaptureFromPath(result.getName(), result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}
	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

	@Override
	protected void finalize() throws Throwable {
		
	}

}
