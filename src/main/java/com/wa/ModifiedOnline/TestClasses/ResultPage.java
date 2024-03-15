package com.wa.ModifiedOnline.TestClasses;

import org.openqa.selenium.WebDriver;

import com.wa.ModifiedOnline.BaseClasses.BaseClass;

public class ResultPage extends BaseClass {

	public WebDriver driver;

	public ResultPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	private final String noResultMessege = "By.cssSelector=>div[class='d-flex justify-content-center p-3']";


	public String gettingNoResult() {
		//waitForElementToVisible(noResultMessege, 2, "waiting for result to display");
		String resultMessage = getText(noResultMessege, "No result on Text Page");
		resultMessage.trim();
		return resultMessage;
	}

}