package com.unity.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.unity.constants.AppConstants;
import com.unity.customelements.CustomElementFieldDecorator;
import com.unity.customelements.UnityWebElement;
import com.unity.utilities.Utilities;

public class AddCategoryPage extends BaseTestPage {

	WebDriver driver;

	public AddCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new CustomElementFieldDecorator(driver, driver), this);
	}

	// login form
	@FindBy(xpath = "(//span[@class='rtsTxt'])[2]")
	public UnityWebElement setUpBtn;

	@FindBy(xpath = "(//span[@class='rmText rmExpandDown'])[2]")
	public WebElement questionnaireBtn;

	@FindBy(xpath = "//*[text() ='Categories']//parent::a")
	public WebElement categoryBtn;

	@FindBy(xpath = "(//button[@class='k-button'])[1]")
	public UnityWebElement addBtn;

	@FindBy(xpath = "(//input[@id='txtYEQCategoryName'])[1]")
	public UnityWebElement categoryName;
	
	@FindBy(xpath = "(//button[@class='k-button'])[1]")
	public UnityWebElement saveBtn;
	
	@FindBy(xpath = "(//button[@class='k-button'])[6]")
	public UnityWebElement categoryPopUp;
	
	
	public void waitForPageToLoad() {
		startTime = System.currentTimeMillis();
		if (super.isPageLoaded(driver))
			try {
				endTime = System.currentTimeMillis();
				pageLoadTime = (endTime - startTime) / 1000;
			} catch (Exception e) {
				Assert.fail("Expected page did not load\n");
			}
		else {
			Assert.fail("Timed out after " + AppConstants.PAGE_LOAD_TIMEOUT + " seconds\n");
		}
	}

	public void addCategory() throws InterruptedException 
	{
		Utilities.wait(3000);

		setUpBtn.click();
		
		Thread.sleep(9000);
		
		Actions action = new Actions(driver);
		action.moveToElement(questionnaireBtn).moveToElement(categoryBtn).click().perform();
				
		Thread.sleep(9000);
				
		addBtn.click();
		
		categoryName.sendKeys("Test");
		
		saveBtn.click();
		
		categoryPopUp.click();
		
	}
	
	

}
