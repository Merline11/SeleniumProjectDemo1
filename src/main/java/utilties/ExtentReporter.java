package utilties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
	public static ExtentSparkReporter reporter ;
	public static ExtentReports extentReports;

	public static ExtentReports getExtendReports() {
		
		String extentReportPath = System.getProperty("user.dir")+"\\reports\\FrameworkExtentReport.html";
		reporter = new ExtentSparkReporter(extentReportPath);
		reporter.config().setReportName("Framework Test report");
		reporter.config().setDocumentTitle("Framework Test Results");
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Operating System", "Windows 10");
		extentReports.setSystemInfo("Tested By", "Merli");
		
		return extentReports;
	}
}
