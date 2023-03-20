package com.unity.stepdefs;

import org.testng.SkipException;

import com.unity.browsers.LocalDriverContext;
import com.unity.constants.AppConstants;
import com.unity.pages.AddInstructionsPage;
import com.unity.pages.AddQuestionsPage;
import com.unity.pages.UnitySSOLoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddQuestionsTest extends BaseSteps
{

	AddQuestionsPage addQuestionsPage = null;
	
	
	@Then("^User should add questions$")
	public void Instruction_should_get_created() throws InterruptedException 
	{
		if (scenarioData.get().size() == 0) 
		{
			throw new SkipException("***** TestCase : " + scenario.get().getName()
					+ " not found in Test Data Sheet. Please add an entry of this test case. *****\n ");
		}
		addQuestionsPage = new AddQuestionsPage(LocalDriverContext.getDriver());
		addQuestionsPage.waitForPageToLoad();
		
		addQuestionsPage.verifyUserIsOnAddQuestionPage();
		addQuestionsPage.AddQuestions();
		addQuestionsPage.verifyAddedQuestions();
		
		addQuestionsPage.waitForPageToLoad();
	}


}
