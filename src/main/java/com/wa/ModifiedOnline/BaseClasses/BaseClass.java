package com.wa.ModifiedOnline.BaseClasses;

import org.openqa.selenium.WebDriver;

public class BaseClass extends CustomDriver {
	public WebDriver driver;

	public BaseClass(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean verifyTitle(String expectedTitle) {
		
		return getTitle().equalsIgnoreCase(expectedTitle);
	}

//	private String getTitle() {
//		return driver.getTitle();
//
//	}
}
