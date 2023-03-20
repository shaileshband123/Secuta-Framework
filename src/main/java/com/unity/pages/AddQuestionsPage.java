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

public class AddQuestionsPage extends BaseTestPage {

	WebDriver driver;

	public AddQuestionsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new CustomElementFieldDecorator(driver, driver), this);
	}

	// login form
	@FindBy(xpath = "(//span[@class='rtsTxt'])[2]")
	public UnityWebElement setUpBtn;

	@FindBy(xpath = "(//span[@class='rmText rmExpandDown'])[2]")
	public WebElement questionnairesBtn;

	@FindBy(xpath = "(//*[@class='rmText'])[9]")
	public WebElement questionBtn;

	@FindBy(xpath = "(//*[@id='yedcGlobalQuestionsAddDeleteButton']/button)[1]")
	public UnityWebElement addBtn;

	@FindBy(xpath = "(//span[@class='k-input ng-scope'])[1]")
	public UnityWebElement selectCategory;
	
	@FindBy(xpath = "(//*[@class='k-list k-reset'])[1]/li")
	public UnityWebElement listOfCategory;
	
	@FindBy(xpath = "//*[@id='txtCustomLabel']")
	public UnityWebElement questiontitle;
	
	@FindBy(xpath = "(//html[@data-lt-installed='true'])/lt-highlighter/lt-div/lt-div")
	public UnityWebElement questiontext;
	
	@FindBy(xpath = "(//button[@class='k-button'])[5]")
	public UnityWebElement saveBtn;
	
	
	
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

	public void verifyUserIsOnAddQuestionPage() throws InterruptedException 
	{
		Utilities.wait(3000);

		setUpBtn.click();
		
		Actions action = new Actions(driver);
		action.moveToElement(questionnairesBtn).moveToElement(questionBtn).click().perform();
				
		Thread.sleep(9000);
				
	}
	
	public void AddQuestions()
	{
		Utilities.wait(3000);
		
		addBtn.click();
		
		selectCategory.click();
		
		questiontitle.sendKeys("Test");
		
		questiontext.sendKeys("Test");
		
		//saveBtn.click();
		
	}
	
	public void verifyAddedQuestions()
	{
		Utilities.wait(3000);
	}

}
