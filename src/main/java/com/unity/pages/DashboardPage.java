package com.unity.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.unity.constants.AppConstants;
import com.unity.customelements.CustomElementFieldDecorator;
import com.unity.customelements.UnityWebElement;

public class DashboardPage extends BaseTestPage {

	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new CustomElementFieldDecorator(driver, driver), this);
	}

	// Loader spinner
	@FindBy(xpath = "//unity-loader")
	public UnityWebElement loginSpinner;

	// header
	@FindBy(xpath = "//unity-header//*[normalize-space(text())='Unity']")
	public UnityWebElement headerUnity;

	@Override
	public void waitForPageToLoad() {
		startTime = System.currentTimeMillis();
		if (super.isPageLoaded(driver))
			try {
				loginSpinner.waitforInvisible(AppConstants.PAGE_LOAD_TIMEOUT);
				headerUnity.waitforVisible(AppConstants.PAGE_LOAD_TIMEOUT);
				endTime = System.currentTimeMillis();
				pageLoadTime = (endTime - startTime) / 1000;
			} catch (Exception e) {
				Assert.fail("Expected page did not load\n" + e.getLocalizedMessage());
			}
		else {
			Assert.fail("Timed out after " + AppConstants.PAGE_LOAD_TIMEOUT + " seconds\n");
		}
	}

}