package Utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			createInstance();
		}
		return extent;
	}

	public static synchronized ExtentReports createInstance() {
		// TODO Auto-generated method stub
		String fileName = Util.getExtentReportName();
		String reportsDirectory = Commons.reportsDirectory;
		new File(reportsDirectory).mkdir();
		String path = reportsDirectory + fileName;

		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("Automation_Run");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);

		extent = new ExtentReports();
		extent.setSystemInfo("Oragnization", "Lets-Code-It");
		extent.setSystemInfo("Automation Freamework", "Selenium-Webdriver");
		extent.attachReporter(htmlReporter);
		
		return extent;
	}

}
