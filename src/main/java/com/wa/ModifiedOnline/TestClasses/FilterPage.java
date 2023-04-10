package com.wa.ModifiedOnline.TestClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wa.ModifiedOnline.BaseClasses.BaseClass;

public class FilterPage extends BaseClass {

	WebDriver driver;

	public FilterPage(WebDriver driver) {
		super(driver);
		driver = this.driver;
	}

	private String Filter_Button = "By.css=>div[class = 'filter-button']";
	private String Filter_All_Button = "By.css=>ul[class='nav justify-content-center'] li[class='nav-item active']";
	private String Filter_Latest_Button = "By.css=>.nav-item.active";
	private String Filter_Featured_Button = "By.css=>div[class='col-12 p-4 filters'] li:nth-child(1)";
	private String Filter_Notparticipated_Button = "By.css=>div[class='col-12 p-4 filters'] li:nth-child(1)";
	private String Filter_trending_Button = "By.css=>div[class='col-12 p-4 filters'] li:nth-child(1)";
	private String Filter_Pollnottaken_Button = "By.css=>div[class='col-12 p-4 filters'] li:nth-child(1)";
	private String Filter_Promoted_Button = "By.css=>div[class='col-12 p-4 filters'] li:nth-child(1)";
	private String Filter_Notapplied_Button = "By.css=>div[class='col-12 p-4 filters'] li:nth-child(1)";
	private String Filter_Company_Dropdown = "By.xpath=>//ng-select[@placeholder='Company']//input[@role='combobox']";
	private String Filter_Category_Dropdown = "By.xpath=>/html[1]/body[1]/app-root[1]/app-wrapper[1]/div[1]/main[1]/app-job-listing[1]/div[1]/app-filters[1]/div[1]/div[1]/div[3]/div[1]/div[2]/form[1]/ng-select[1]/div[1]/div[1]/div[2]/input[1]";
	private String Filter_Apply_Button = "By.css=>button[fdprocessedid='tuxvn']";
	private String Filter_Clear_Button = "By.css=>button[fdprocessedid= 'aqit1e']";
	private String Company_Dropdown_options = "By.css=>span[class= 'ng-option-label']";
	private String Category_Dropdown_options = "By.css=>.ng-option-label";
	private String Filter_Company_Dropdown_Arrow = "By.css=>ng-select[placeholder='Company'] span[class='ng-arrow-wrapper']";

	public void clickOnFilterButton() {
		elementClick(Filter_Button, "click on filter button");
	}

	public ResultPage clickOnFilterApplyButton() {
		elementClick(Filter_Apply_Button, "click on apply-filter button");
		return new ResultPage(driver);

	}

	public void clickOnFilterClearButton() {
		elementClick(Filter_Clear_Button, "click on clear-filter button");

	}

	public void sendDataOnCompanyDropdown(String shortName) {
		// waitforElementToClickable(Category_Dropdown_options, 2, "filter company
		// dropdown");
		sendData(Filter_Company_Dropdown, shortName, "filter company dropdown");
	}

	public void selectfromCompanyDropdown(String fullname) {
		List<WebElement> dropdownList = getElements(Company_Dropdown_options, "all elements in dropdown");
		for (WebElement company : dropdownList) {
			String org = getText(company, "each company");
			if (org.contains(fullname)) {
				elementClick(company, "click on organisation name");
				elementClear(Filter_Company_Dropdown, "text cleared");
				elementClick(Filter_Company_Dropdown_Arrow, "value selected");
			}
		}
	}

	public void sendDataOnCategoryDropdown(String data) {
		sendData(Filter_Category_Dropdown, data, "filter category dropdown");
	}

	public void selectfromCategoryDropdown(String fullname) {
		// waitForElementToVisible(Company_Dropdown_options, 2, "options for company
		// dropdown");
		List<WebElement> dropdownList = getElements(Category_Dropdown_options, "all elements in dropdown");
		for (WebElement company : dropdownList) {
			String org = getText(company, "each company");
			if (org.contains(fullname)) {
				elementClick(company, "click on organisation name");
				elementClear(Filter_Company_Dropdown, "text cleared");
			}
		}
	}

	public void goToAll() {
		elementClick(Filter_All_Button, "click on all-filter category");
	}

	public void goToLatest() {
		elementClick(Filter_Latest_Button, "click on latest-filter category");
	}

	public void goTofeatured() {
		elementClick(Filter_Featured_Button, "click on featured-filter category");
	}

	public void goToNotParticipated() {
		elementClick(Filter_Notparticipated_Button, "click on notparticipated-filter category");
	}

	public void goTotrending() {
		elementClick(Filter_trending_Button, "click on trending-filter category");
	}

	public void goToPollNotTaken() {
		elementClick(Filter_Pollnottaken_Button, "click on pollnottaken-filter category");
	}

	public void goToPromoted() {
		elementClick(Filter_Promoted_Button, "click on promoted-filter category");
	}

	public void goToNotApplied() {
		elementClick(Filter_Notapplied_Button, "click on notapplied-filter category");
	}

}
