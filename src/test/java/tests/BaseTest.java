package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.common;
public class BaseTest {

	common common;

	static WebDriver driver;
	ExtentReports extent;
	
	@BeforeSuite
	public void config()
	{
		String path= System.getProperty("useer.dir")+"\\report\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results of Sathya Shankaran");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("QEEngineer","SathyaShankaran");
		
				
		
		
	}
	@Parameters({"browser", "url"})
	@BeforeTest
	public void beforeTest(String browser, String url) {
		ExtentTest test=extent.createTest("reports");
		common = new common();
		common.setupBrowser(browser, url);
		driver = common.getDriver();
		driver.manage().window().maximize();
	}
	
	
	
	@AfterTest
	public void afterTest() {
		common.quitBrowser();
	} 
	
	
}

