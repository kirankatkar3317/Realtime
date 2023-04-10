package com.wa.ModifiedOnline.BaseClasses;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Util;

public class CustomDriver {

	public static final Logger log = LogManager.getLogger(CustomDriver.class.getName());

	WebDriver driver;
	JavascriptExecutor js;

	public CustomDriver() {

	}

	public CustomDriver(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		this.driver = driver;

	}

	public void refresh() {
		driver.navigate().refresh();
		log.info("the current browser location is refreshed");
	}

	public String getTitle() {
		String title = driver.getTitle();
		log.info("the current Browser title is :: " + title);
		return title;
	}

	public String geturl() {
		String url = driver.getCurrentUrl();
		log.info("the current url of browser :: " + url);
		return url;
	}

	public void navigateForward() {
		driver.navigate().forward();
		log.info("navigate forward");
	}

	public void navigateback() {
		driver.navigate().back();
		log.info("navigate back");
	}

	public By getByType(String locator) {
		By by = null;
		String locatorType = locator.split("=>")[0];
		locator = locator.split("=>")[1];
		try {
			if (locatorType.contains("xpath")) {
				by = By.xpath(locator);
			}
			if (locatorType.contains("css")) {
				by = By.cssSelector(locator);
			}
			if (locatorType.contains("id")) {
				by = By.id(locator);
			}
			if (locatorType.contains("class")) {
				by = By.className(locatorType);
			}
			if (locatorType.contains("link")) {
				by = By.linkText(locator);
			}
			if (locatorType.contains("name")) {
				by = By.name(locator);
			}
			if (locatorType.contains("tag")) {
				by = By.tagName(locator);
			}
			if (locatorType.contains("partial")) {
				by = By.partialLinkText(locator);
			}

		} catch (Exception e) {
			log.error("by element" + locator + "not found");
			e.printStackTrace();
		}
		return by;
	}

	public WebElement getElement(String locator, String info) {
		WebElement element = null;
		By byType = getByType(locator);
		try {
			element = driver.findElement(byType);

		} catch (Exception e) {
			log.error("element not found with ::" + locator);
			e.printStackTrace();
		}
		return element;
	}

	public List<WebElement> getElements(String locator, String info) {
		List<WebElement> elementList = new ArrayList<WebElement>();
		By byType = getByType(locator);

		try {
			elementList = driver.findElements(byType);
			log.info("element list found with" + locator);
		} catch (Exception e) {
			log.error("element list not found with" + locator);
			e.printStackTrace();
		}
		return elementList;
	}

	public boolean isElementPresent(String locator, String info) {
		List<WebElement> webelementList = getElements(locator, info);
		int size = webelementList.size();
		if (size > 0) {
			return true;
		} else {
			return false;
		}

	}

//	public void elementClick(WebElement element, String info, int timeToWait) {
//		try {
//			element.click();
//			if (timeToWait == 0) {
//				log.info("clicked on " + info);
//			} else {
//				Util.sleep(5, "clicked on ::" + info);
//			}
//		} catch (Exception e) {
//			log.error("cannot click on:: " + info);
//			e.printStackTrace();
//		}
//	}
//	
//	

	public void elementClick(WebElement element, String info, int timeToWait) {
		try {

			element.click();
			if (timeToWait == 0) {

				System.out.println("clicked on element" + info);
			}

			else {
				Util.sleep(timeToWait, "clicked on " + info);
				System.out.println("clicked after wait of" + timeToWait + "on element" + info);
			}
		}

		catch (Exception e) {
			System.out.println("cannot clicked on " + info);
			e.printStackTrace();
			// takeScreenShot("click error", "");
		}

	}

	public void elementClick(WebElement element, String info) {
		elementClick(element, info, 0);
	}

	public void elementClick(String locator, String info) {
		WebElement element = getElement(locator, info);
		elementClick(element, info);

	}

	public void elementClick(String locator, String info, int timeToWait) {
		WebElement element = getElement(locator, info);
		elementClick(element, info, timeToWait);

	}

	public void javascriptClick(String locator, String info) {
		WebElement element = getElement(locator, info);
		try {
			js.executeScript("arguments[0].click()", element);
			log.info("clicked on " + info);
		} catch (Exception e) {
			log.error("cannot click on " + info);
			e.printStackTrace();
		}
	}

	public WebElement waitforElementToClickable(String locator, int timeout, String info) {
		WebElement element = getElement(locator, "click on:: " + info);
		try {

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			log.info("waiting for max:: " + timeout + " seconds for element to clickable");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			log.info("wait for " + timeout + " seconds to clicked on element is successful" + info);

		} catch (Exception e) {
			log.error("element not appeared on webpage");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			e.printStackTrace();

		}
		return element;
	}

	public WebElement waitForElementToVisible(String locator, int timeout, String info) {

		WebElement element = getElement(locator, "click on:: " + info);
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			log.info("waiting for max:: " + timeout + " seconds for element to visible");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(element));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			log.info("element is visible on webpage");
		} catch (Exception e) {
			log.error("element not appeared on webpage");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			e.printStackTrace();
		}
		return element;
	}

	public void sendData(WebElement element, String data, boolean clear, String info) {
		try {
			if (clear) {
				element.clear();
			}

			element.sendKeys(data);
			log.info("send keys on element :: " + info + "with data " + data);

		} catch (Exception e) {
			log.error("not able to send keys on element ::" + info + " with data " + data);
			e.printStackTrace();
		}

	}

	public void sendData(String locator, String data, boolean clear, String info) {
		WebElement element = getElement(locator, info);
		sendData(element, info, clear, info);

	}

	public void sendData(WebElement element, String data, String info) {
		sendData(element, data, true, info);

	}

	public void sendData(String locator, String data, String info) {
		WebElement element = getElement(locator, info);
		sendData(element, data, info);
	}

	public String getText(WebElement element, String info) {
		String text = null;
		text = element.getText();
		if (text.length() == 0) {
			element.getAttribute("innertext");
		}
		if (!text.isEmpty()) {
			log.info("the text is ::" + text);
		} else {
			text = "not_found";
		}
		return text.trim();

	}

	public String getText(String locator, String info) {
		WebElement element = getElement(locator, info);
		return getText(element, info);
	}

	public boolean isEnable(WebElement element, String info) {
		boolean enable = false;
		enable = element.isEnabled();
		if (enable) {
			log.info("element ::" + info + "is enabled");
		} else {
			log.info("element ::" + info + "is disable");
		}
		return enable;
	}

	public boolean isEnable(String locator, String info) {
		WebElement element = getElement(locator, info);
		return isEnable(element, info);
	}

	public boolean isDisplayed(WebElement element, String info) {
		boolean displayed = false;
		displayed = element.isDisplayed();
		if (displayed) {
			log.info("element :: " + info + " is displayed");
		} else {
			log.info("element ::" + info + " is not displayed");
		}
		return displayed;
	}

	public boolean isDisplayed(String locator, String info) {
		WebElement element = getElement(locator, info);
		return isDisplayed(element, info);
	}

	public boolean isSelected(WebElement element, String info) {

		boolean selected = false;
		selected = element.isSelected();
		if (selected) {
			log.info("element ::" + info + "is diplayed on dom");
		} else {
			log.info("element ::" + info + "is not diplayed on dom");
		}
		return selected;
	}

	public boolean isSelected(String locator, String info) {
		WebElement element = getElement(locator, info);
		return isSelected(element, info);
	}

	public boolean check(WebElement element, String info) {
		if (!isSelected(element, info)) {
			elementClick(element, info);
			log.info("Element ::" + info + "is selected");
			return true;

		} else {

			log.info("Element is already checked" + info);
			return false;
		}
	}

	public void check(String locator, String info) {
		WebElement element = getElement(locator, info);
		check(element, info);
	}

	public boolean uncheck(WebElement element, String info) {

		if (isSelected(element, info)) {
			elementClick(element, info);
			log.info("element ::" + info + " is unchecked");
			return true;
		} else {

			log.info("element ::" + info + "is already unchecked");
			return false;
		}
	}

	public void uncheck(String locator, String info) {
		WebElement element = getElement(locator, info);
		uncheck(element, info);
	}

	public boolean submit(WebElement element, String info) {
		if (element != null) {
			element.submit();
			log.info("Element " + info + "is submitted");
			return true;
		} else {
			return false;
		}

	}

	public String getAttributeValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	public String getAttributeValue(String locator, String attribute, String info) {
		WebElement element = getElement(locator, info);
		return getAttributeValue(element, attribute);
	}

	public void mousehover(String locator, String info) {
		WebElement element = getElement(locator, info);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();

	}

//	public void selectOption(String locator, String optionToSelect,String info) {
//		WebElement element = getElement(locator, info);
//		Select select = new Select(element);
//		select.selectByVisibleText(optionToSelect);
//		System.out.println("selected option ::" + optionToSelect);
//		
//	}

	public void selectOption(WebElement element, String optionToSelect) {
		Select select = new Select(element);
		select.selectByVisibleText(optionToSelect);
		log.info("selected option ::" + optionToSelect);

	}

	public void selectOption(String locator, String optionToSelect, String info) {
		WebElement element = getElement(locator, info);
		selectOption(element, optionToSelect);

	}

	public String getTextOfDropDownValue(WebElement element) {
		Select select = new Select(element);
		String dropDownText = select.getFirstSelectedOption().getText();
		return dropDownText;
	}

	public boolean isOptionExists(WebElement element, String optionToVerify) {

		Select select = new Select(element);
		boolean exist = false;
		List<WebElement> options = select.getOptions();
		for (int i = 0; i < options.size(); i++) {

			String text = getText(options.get(i), "option text");
			if (text.matches(optionToVerify)) {
				exist = true;
				break;
			}
		}

		if (exist) {
			log.info("select option" + optionToVerify + "is exists");
		} else {
			log.info("select option" + optionToVerify + "is not exists");
		}
		return exist;
	}

	public void rightClick(String locator, String info) {
		WebElement element = getElement(locator, info);
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
		log.info("right click on ::" + info);

	}

	public void elementClear(String locator, String info) {
		WebElement element = getElement(locator, info);
		element.clear();

	}

	public void doubleClick(String locator, String info) {

		WebElement element = getElement(locator, info);
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
		log.info("double click on ::" + info);

	}

	public void selectItemAndRightClick(String firstLocator, String secondLocator) {
		WebElement element = getElement(firstLocator, "info");
		Actions action = new Actions(driver);
		action.contextClick(element);
		WebElement itemElement = getElement(secondLocator, "info");
		elementClick(itemElement, secondLocator, 3);

	}

	public void keyPress(String info, Keys key) {
		Actions action = new Actions(driver);
		action.keyDown(key);
		log.info("key pressed on" + info);
	}

//	public String takesScreenshot(String methodName, String browserName) {
//		String fileName = Util.getScreenshotName(methodName, browserName);
//		String screenshotDir = System.getProperty("user.dir")+"//" +"test-output/screenshots";
//		new File(screenshotDir).mkdirs();
//		String path = screenshotDir + "//" + fileName;
//		
//		
//		try {
//			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//			FileUtils.copyFile(screenshot, new File(path));
//			System.out.println("screenshot i saved at ::"+ path);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		return path;
//	}
//
//	

	public String takeScreenshot(String methodName, String browserName) {
		String fileName = Util.getScreenshotName(methodName, browserName);
		String screenshotDir = System.getProperty("User.dir") + "//" + "test-output/screenshots";
		new File(screenshotDir).mkdir();
		String path = screenshotDir + "//" + fileName;

		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
			System.out.println("screenshot is saved at ::"+path);
		} catch (Exception e) {
			e.printStackTrace();
		}
return path;
	}

}