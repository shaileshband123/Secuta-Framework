package com.unity.stepdefs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.unity.browsers.LocalDriverContext;
import com.unity.constants.AppConstants;
import com.unity.customelements.UnityWebElement;
import com.unity.utilities.Utilities;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonActions extends BaseSteps {

	@And("^user navigates to url \"([^\"]*)\"$")
	public void navigate_to_url(String url) {
		LocalDriverContext.getDriver().get(getVariableValue(url));
	}

	@And("^user navigates back in the browser$")
	public void navigate_back_in_the_browser() {
		LocalDriverContext.getDriver().navigate().back();
	}

	@And("^user clicks on \"([^\"]*)\"$")
	public void user_clicks_on(String locator) {
		getWebElement(locator, LocalDriverContext.getDriver()).click();
		Utilities.wait(1000);
	}

	@And("^user clicks on \"([^\"]*)\" using js$")
	public void user_clicks_on_using_js(String locator) {
		getWebElement(locator, LocalDriverContext.getDriver()).jsClick();
		Utilities.wait(1000);
	}

	@And("^user clears text of textbox \"([^\"]*)\"$")
	public void users_clears_text_of_textbox_something(String locator) {
		UnityWebElement element = getWebElement(locator, LocalDriverContext.getDriver());
		element.click();
		element.clearText();
		Utilities.wait(5000);
	}

	@And("^user enters value \"([^\"]*)\" in textbox \"([^\"]*)\"$")
	public void user_enters_value_in_textbox(String value, String locator) {
		value = getVariableValue(value);
		UnityWebElement element = getWebElement(locator, LocalDriverContext.getDriver());
		element.click();
		element.clearText();
		element.sendKeys(value);
		Utilities.wait(2000);
	}

	@And("^user enters value \"([^\"]*)\" in textbox \"([^\"]*)\" using js$")
	public void user_enters_value_in_textbox_using_js(String value, String locator) {
		value = getVariableValue(value);
		UnityWebElement element = getWebElement(locator, LocalDriverContext.getDriver());
		WebElement e = LocalDriverContext.getDriver().findElement(element.getBy());
		JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getDriver();
		jsExecutor.executeScript("arguments[0].value = arguments[1]", e, value);
		e.sendKeys(Keys.RETURN);
		Utilities.wait(3000);
	}

	@And("^user switches to frame element \"([^\"]*)\"$")
	public void user_switches_to_frame_element(String locator) {
		Utilities.switchToFrame(LocalDriverContext.getDriver(),
				getWebElement(locator, LocalDriverContext.getDriver()));
	}

	@And("^user switches to default content$")
	public void user_switches_to_default_content() {
		LocalDriverContext.getDriver().switchTo().defaultContent();
	}

	@And("^user switches to parent frame$")
	public void user_switches_to_parent_frame() {
		LocalDriverContext.getDriver().switchTo().parentFrame();
	}

	@And("^user switches to new window$")
	public void user_switches_to_new_window() {
		parentWindowHandle.set(LocalDriverContext.getDriver().getWindowHandle());
		Utilities.switchToNewWindow(LocalDriverContext.getDriver());
	}

	@And("^user switches back to parent window$")
	public void user_switches_back_to_parent_window() {
		LocalDriverContext.getDriver().close();
		LocalDriverContext.getDriver().switchTo().window(parentWindowHandle.get());
	}

	@And("^user hovers over element \"([^\"]*)\"$")
	public void user_hovers_over_element(String locator) {
		getWebElement(locator, LocalDriverContext.getDriver()).moveToElement();
	}

	@Then("^verify that user is on \"([^\"]*)\" screen$")
	public void verify_that_user_is_on_screen(String pageClassName) {
		Class<?> clazz = getClassObject(pageClassName);
		invokeMethod(LocalDriverContext.getDriver(), clazz, "waitForPageToLoad");
		Utilities.wait(4000);
	}

	@Then("^verify element \"([^\"]*)\" is visible$")
	public void verify_element_is_visible(String locator) {
		getWebElement(locator, LocalDriverContext.getDriver()).assertVisible();
	}

	@Then("^verify element \"([^\"]*)\" is selected$")
	public void verify_element_is_selected(String locator) {
		getWebElement(locator, LocalDriverContext.getDriver()).assertSelected();
	}

	@Then("^verify element \"([^\"]*)\" is not visible$")
	public void verify_element_is_not_visible(String locator) {
		getWebElement(locator, LocalDriverContext.getDriver()).assertNotVisible();
	}

	@And("^user accepts the alert$")
	public void user_accepts_the_alert() {
		WebDriverWait wait = new WebDriverWait(LocalDriverContext.getDriver(), AppConstants.WAIT_TIMEOUT);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = LocalDriverContext.getDriver().switchTo().alert();
		alert.accept();
	}

	@And("^user dismisses the alert$")
	public void user_dismisses_the_alert() {
		WebDriverWait wait = new WebDriverWait(LocalDriverContext.getDriver(), AppConstants.WAIT_TIMEOUT);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = LocalDriverContext.getDriver().switchTo().alert();
		alert.dismiss();
	}

	@And("^verify input element \"([^\"]*)\" value should be \"([^\"]*)\"$")
	public void verify_input_element_value_should_be(String locator, String value) {
		value = getVariableValue(value);
		getWebElement(locator, LocalDriverContext.getDriver()).assertAttribute("value", value);
	}

	@And("^verify text of element \"([^\"]*)\" should be \"([^\"]*)\"$")
	public void verify_text_of_element_should_be(String locator, String text) {
		text = getVariableValue(text);
		getWebElement(locator, LocalDriverContext.getDriver()).assertText(text.trim());
	}

	@And("^verify attribute \"([^\"]*)\" of element \"([^\"]*)\" should be \"([^\"]*)\"$")
	public void verify_attribute_of_element_should_be(String attributeName, String locator, String attributeValue) {
		attributeValue = getVariableValue(attributeValue);
		getWebElement(locator, LocalDriverContext.getDriver()).assertAttribute(attributeName, attributeValue);
	}

	@And("^verify text of element \"([^\"]*)\" should contain \"([^\"]*)\"$")
	public void verify_text_of_element_should_contain(String locator, String text) {
		text = getVariableValue(text);
		getWebElement(locator, LocalDriverContext.getDriver()).assertContainsText(text);
	}

	@And("^verify text of element \"([^\"]*)\" should not contain \"([^\"]*)\"$")
	public void verify_text_of_element_should_not_contain(String locator, String text) {
		text = getVariableValue(text);
		String value = getWebElement(locator, LocalDriverContext.getDriver()).getText();
		Assert.assertFalse(value.contains(text), "Unexpected text " + text + " is present in the given element");
	}

	@And("^verify element \"([^\"]*)\" is enabled$")
	public void verify_element_is_enabled(String locator) {
		getWebElement(locator, LocalDriverContext.getDriver()).assertEnabled();
	}

	@And("^verify element \"([^\"]*)\" is disabled$")
	public void verify_element_is_disabled(String locator) {
		getWebElement(locator, LocalDriverContext.getDriver()).assertDisabled();
	}

	@And("^user waits for element \"([^\"]*)\" to be visible$")
	public void user_waits_for_element_to_be_visible(String locator) {
		getWebElement(locator, LocalDriverContext.getDriver()).waitforVisible();
		Utilities.wait(2000);
	}

	@And("^user waits for element \"([^\"]*)\" to be invisible$")
	public void user_waits_for_element_to_be_invisible(String locator) {
		getWebElement(locator, LocalDriverContext.getDriver()).waitforInvisible();
		Utilities.wait(2000);
	}

	@And("^user captures current window as \"([^\"]*)\"$")
	public void user_captures_current_window_as_something(String mapKey) {
		scenarioData.get().put(mapKey, LocalDriverContext.getDriver().getWindowHandle());
	}

	@When("^user clicks on \"([^\"]*)\" if visible$")
	public void user_clicks_on_something_if_visible(String locator) {
		UnityWebElement element = getWebElement(locator, LocalDriverContext.getDriver());
		if (element.isPresent())
			element.click();
	}

	@And("^verify text of element \"([^\"]*)\" should contain date \"([^\"]*)\"$")
	public void verify_text_of_element_should_contain_date(String locator, String text) {
		text = getVariableValue(text);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String date = LocalDate.parse(text, formatter).format(formatter2);
		getWebElement(locator, LocalDriverContext.getDriver()).assertContainsText(date);
	}

	@When("^user drags \"([^\"]*)\" and drops it at \"([^\"]*)\"$")
	public void user_drags_something_and_drops_it_at_something(String sourceElementLocator,
			String destinationElementLocator) {
		WebElement sourceElement = getWebElement(sourceElementLocator, LocalDriverContext.getDriver()).getElement();
		WebElement destinationElement = getWebElement(destinationElementLocator, LocalDriverContext.getDriver())
				.getElement();
		Utilities.dragAndDropElement(LocalDriverContext.getDriver(), sourceElement, destinationElement);
	}

	@And("^verify text of alert should be \"([^\"]*)\"$")
	public void verify_text_of_alert_should_be(String expectedText) {
		WebDriverWait wait = new WebDriverWait(LocalDriverContext.getDriver(), AppConstants.WAIT_TIMEOUT);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = LocalDriverContext.getDriver().switchTo().alert();
		String alertText = alert.getText();
		Assert.assertTrue(alertText.equals(expectedText));
	}

	@When("^user opens a new browser window and switches to it$")
	public void user_opens_a_new_browser_window_and_switches_to_it() {
		parentWindowHandle.set(LocalDriverContext.getDriver().getWindowHandle());
		Utilities.openNewWindow(LocalDriverContext.getDriver());
	}

}
