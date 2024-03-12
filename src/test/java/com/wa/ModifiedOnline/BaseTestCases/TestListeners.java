package com.wa.ModifiedOnline.BaseTestCases;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.wa.ModifiedOnline.BaseClasses.CustomDriver;

import Utilities.ExtentManager;

public class TestListeners extends BaseTest implements ITestListener {

	private ExtentReports reporter = ExtentManager.getInstance();
	private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	private static Logger log = LogManager.getLogger(TestListeners.class.getName());

	@Override
	public void onStart(ITestContext context) {
		log.info("onStart -> Test Tag Name::" + context.getName());
		ITestNGMethod methods[] = context.getAllTestMethods();
		log.info("these methods will be executed under thid <test> tag");
		for (ITestNGMethod method : methods) {
			log.info(method.getMethodName());
		}
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

		log.info("OnFinish -> Test Tag Name ::" + context.getName());
		reporter.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTest test = reporter.createTest(result.getInstance() + "::" + result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		log.info("OnTestSuccess-> Test Method Name::" + result.getName());
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "method" + methodName + "is succesfull" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		log.info("onTestFailure-> Test Method Name::" + result.getName());
		String methodName = result.getMethod().getMethodName();
		String exceptionMessege = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details>"+"<summary>"+"<b>"+"<font color=red>"+"Exception Occured: Click to see details:"+"</font>"+"</b>"+"</summary>"+exceptionMessege.replaceAll(",", "<br>")+"</details>"+"\n");
		
		String browser = WebDriverFactory.getInstance().getBrowser();
		WebDriver driver = WebDriverFactory.getInstance().getDriver(browser);
		CustomDriver cd = new CustomDriver(driver);
		String path = cd.takeScreenshot(result.getName(), browser);
		
		try {
			extentTest.get().fail("<b>"+"<font color=red>"+"Screeshot of failure"+"</font>"+"</b>",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		catch(Exception e) {
			extentTest.get().fail("Test method failed,cannot attach screenshot");
		}
		
		
		String logText = "<b>"+"Test Method"+ methodName +"Failed"+"</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);

		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		log.info("onTestSkipped-> Test Method Name::" + result.getName());
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "method" + methodName + "is skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().log(Status.PASS, m);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

}
