package com.wa.ModifiedOnline.BaseTestCases;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;

import Utilities.Commons;

public class WebDriverFactory {
	
	
	
	
	
	public static final Logger log = LogManager.getLogger(WebDriverFactory.class.getName());

	private static final WebDriverFactory instance = new WebDriverFactory();

	private WebDriverFactory() {

	}

	public static WebDriverFactory getInstance() {
		return instance;
	}

	private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();

	public WebDriver getDriver(String browser) {

		WebDriver driver = null;
		setDriver(browser);
		if (threadedDriver.get() == null) {
			try {

				if (browser.equalsIgnoreCase(Commons.CHROME)) {
					ChromeOptions chromeOptions = setChromeOptions();
					driver = new ChromeDriver(chromeOptions);
					threadedDriver.set(driver);
				}
	
				if (browser.equalsIgnoreCase(Commons.FIREFOX)) {
					FirefoxOptions firefoxOptions = setFirefoxOptions();
					driver = new FirefoxDriver(firefoxOptions);
					threadedDriver.set(driver);
				}
				if (browser.equalsIgnoreCase(Commons.IE)) {
					driver = new InternetExplorerDriver();
					threadedDriver.set(driver);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

			threadedDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
			threadedDriver.get().manage().window().maximize();
		}
		return threadedDriver.get();
	}

	public void exitDriver() {
		threadedDriver.get().quit();
		threadedDriver.set(null);

	}

	private void setDriver(String browser) {

		String driverPath = "";
		String os = Commons.SYSTEM_OS.toLowerCase().substring(0, 3);
		System.out.println("The os of system is::" + os);
		String directory = Commons.BASE_PATH + Commons.DRIVER_DIRECTORY;
		String driverKey = "";
		String driverValue = "";

		if (browser.equals(Commons.CHROME)) {
			driverKey = Commons.CHROME_DRIVER_PROPERTY;
			driverValue = Commons.CHROME_DRIVER_NAME;
		} else if (browser.equals(Commons.FIREFOX)) {
			driverKey = Commons.FIREFOX_DRIVER_PROPERTY;
			driverValue = Commons.FIREFOX_DRIVER_NAME;
		}

		driverPath = directory + driverValue + (os.equals("win") ? ".exe" : "");
		log.info("driver binary ::" + driverPath);
		System.setProperty(driverKey, driverPath);

	}

	private ChromeOptions setChromeOptions() {

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		chromeOptions.addArguments("disable-infobar");
		return chromeOptions;

	}

	private FirefoxOptions setFirefoxOptions() {

		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		return firefoxOptions;

	}
}