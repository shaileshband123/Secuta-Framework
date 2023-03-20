package com.unity.customelements;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.unity.constants.AppConstants;
import com.unity.utilities.Utilities;

/**
 * UnityWebElement is a wrapper over default WebElement which provides direct
 * access to helper methods and gives the flexibility to customize selenium
 * methods as needed.
 * 
 * @author e5562312 neerajM
 *
 */
public class UnityWebElement extends CustomWebElement {
	protected Logger log = LogManager.getLogger();

	/**
	 * Constructor
	 *
	 * @param webDriver
	 *            The webDriver used to interact with the web browser.
	 * @param by
	 *            The locator used to identify the element(s) on the website.
	 **/
	public UnityWebElement(WebDriver webDriver, By by) {
		super(webDriver, by);
	}

	/**
	 * This method converts CustomWebElement and returns default WebElement
	 * 
	 * @return WebElement
	 */
	public WebElement getElement() {
		return getWebDriver().findElement(getBy());
	}

	/**
	 * Performs click on element.
	 * 
	 **/
	public void click() {
		waitforVisible();
		if (getElement().isDisplayed() && getElement().isEnabled()) {
			log.info("Clicking on " + getElement().getText());
			highlightElement();
			try {
				getElement().click();
			} catch (WebDriverException e) {
				jsClick();
			}
		}
	}

	/**
	 * Performs click on element using JavaScriptExecutor.
	 * 
	 **/
	public void jsClick() {
		Utilities.wait(2000);
		waitforVisible();
		if (getElement().isDisplayed() && getElement().isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
			log.info("Click using JS on " + getElement().getText());
			highlightElement();
			js.executeScript("arguments[0].click();", getElement());
		}
	}

	/**
	 * Performs click on element using Actions Class by moving to the given element
	 * 
	 **/
	public void moveToAndClick() {
		waitforVisible();
		new Actions(getWebDriver()).moveToElement(getElement()).click().build().perform();
	}

	/**
	 * Performs hover over element using Actions Class by moving to the given
	 * element
	 * 
	 **/
	public void moveToElement() {
		waitforVisible();
		new Actions(getWebDriver()).moveToElement(getElement()).build().perform();
	}

	/**
	 * Performs click on element by scrolling to it using JavaScriptExecutor.
	 * 
	 **/
	public void scrollAndClick() {
		Utilities.wait(2000);
		waitforVisible();
		JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
		js.executeScript("arguments[0].scrollIntoView();", getElement());
		log.info("Click by scrolling on " + getElement().getText());
		highlightElement();
		jsClick();
	}

	/**
	 * Sets the text of the element.
	 **/
	public void sendKeys(String text) {
		waitforVisible();
		if (getElement().isDisplayed() && getElement().isEnabled()) {
			getElement().clear();
			log.info("Entering text : " + text);
			highlightElement();
			getElement().sendKeys(text);
		}
	}

	public void setPassword(String password) {
		waitforVisible();
		if (getElement().isDisplayed() && getElement().isEnabled()) {
			getElement().clear();
			log.info("Entering Password : ************");
			highlightElement();
			getElement().sendKeys(password);
		}
	}

	/**
	 * Enters text using JS.
	 **/
	public void sendKeysJS(String text) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();
		jsExecutor.executeScript("arguments[0].value = arguments[1]", getElement(), text);
		getElement().sendKeys(Keys.RETURN);
	}

	/**
	 * Clears the text of the element.
	 **/
	public void clearText() {
		waitforVisible();
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();
		jsExecutor.executeScript("arguments[0].value=''", getElement());
	}

	/**
	 * Returns tagname of the element.
	 * 
	 * @return - tagname as String.
	 */
	public String getTagName() {
		return getElement().getTagName();
	}

	/**
	 * Returns value of a given attribute.
	 * 
	 * @return - Attribute value as String.
	 **/
	@Override
	public String getAttribute(String attributeName) {
		return getElement().getAttribute(attributeName);
	}

	/**
	 * Determine whether or not this element is selected. This operation only
	 * applies to input elements such as checkboxes, options in a select and radio
	 * buttons.
	 *
	 * @return True if the element is currently selected or checked, false
	 *         otherwise.
	 */
	public boolean isSelected() {
		return getElement().isSelected();
	}

	/**
	 * Is the element currently enabled or not? This will generally return true for
	 * everything but disabled input elements.
	 *
	 * @return True if the element is enabled, false otherwise.
	 */
	public boolean isEnabled() {
		return getElement().isEnabled();
	}

	/**
	 * Is this element displayed or not? This method avoids the problem of having to
	 * parse an element's "style" attribute.
	 *
	 * @return Whether or not the element is displayed
	 */
	public boolean isDisplayed() {
		return getElement().isDisplayed();
	}

	/**
	 * This method returns true or false based on the element is present in DOM or
	 * not. It avoids NoSuchElementException by not calling the isDisplayed() method
	 * on element.
	 *
	 * @return Whether or not the element is present in DOM
	 */
	public boolean isPresent() {
		return getWebDriver().findElements(getBy()).isEmpty() ? false : true;
	}

	/**
	 * Finds an element which uses the locator of this element as base.
	 *
	 * @return The found sub web element of this complex web element.
	 **/
	public WebElement findElement(By locator) {
		return getElement().findElement(locator);
	}

	/**
	 * Finds elements which uses the locator of this element as base.
	 *
	 * @return The found sub web elements of this complex web element.
	 **/
	public List<WebElement> findElements(By locator) {
		return getElement().findElements(locator);
	}

	/**
	 * Returns the node text of the element.
	 *
	 * @return Returns the node text of the element.
	 **/
	public String getText() {
		return getElement().getText();
	}

	/**
	 * This method verifies if a given element is visible on screen. If not, it will
	 * throw an AssertionError.
	 */
	public void assertVisible() {
		waitforVisible();
		Assert.assertTrue(getElement().isDisplayed(), "Element is not visible on screen\n");
	}

	/**
	 * This method verifies if a given element is selected on screen. If not, it
	 * will throw an AssertionError.
	 */
	public void assertSelected() {
		waitforVisible();
		Assert.assertTrue(getElement().isSelected(), "Element is not selected on screen\n");
	}

	/**
	 * This method verifies if a given element is enabled on screen. If not, it will
	 * throw an AssertionError.
	 */
	public void assertEnabled() {
		waitforVisible();
		Assert.assertTrue(getElement().isEnabled(), "Element is disabled on screen\n");
	}

	/**
	 * This method verifies if a given element is disabled on screen. If not, it
	 * will throw an AssertionError.
	 */
	public void assertDisabled() {
		waitforVisible();
		Assert.assertFalse(getElement().isEnabled(), "Element is enabled on screen\n");
	}

	/**
	 * This method verifies if a given element is not visible on screen. If visible,
	 * it will throw an AssertionError.
	 */
	public void assertNotVisible() {
		boolean flag = false;
		try {
			getElement().isDisplayed();
		} catch (Exception e) {
			flag = true;
		}
		Assert.assertTrue(flag, "Unwanted element is visible on screen\n");
	}

	/**
	 * This method matches text value of a given element with the given text. In
	 * case of mismatch, it will throw an AssertionError.
	 */
	public void assertText(String expectedText) {
		Utilities.wait(3000);
		Assert.assertEquals(getElement().getText().trim(), expectedText);
	}

	/**
	 * This method matches text value of a given element with the given text. In
	 * case of mismatch, it will throw an AssertionError.
	 */
	public void assertText(String expectedText, String failureMessage) {
		Utilities.wait(3000);
		Assert.assertEquals(getElement().getText().trim(), expectedText, failureMessage);
	}

	/**
	 * This method checks if text value of a given element contains the given text.
	 * In case of mismatch, it will throw an AssertionError.
	 */
	public void assertContainsText(String expectedText) {
		Utilities.wait(3000);
		String actualText = getElement().getText().trim();
		Assert.assertTrue(actualText.contains(expectedText),
				"Expected-> " + expectedText + "\nActual->" + actualText + "\n");
	}

	/**
	 * This method checks if text value of a given element contains the given text.
	 * In case of mismatch, it will throw an AssertionError.
	 */
	public void assertContainsText(String expectedText, String failureMessage) {
		Utilities.wait(3000);
		String actualText = getElement().getText().trim();
		Assert.assertTrue(actualText.contains(expectedText), failureMessage);
	}

	/**
	 * This method matches attribute value of a given element with the given input.
	 * In case of mismatch, it will throw an AssertionError.
	 */
	public void assertAttribute(String attributeName, String attributeValue) {
		Assert.assertEquals(getElement().getAttribute(attributeName), attributeValue);
	}

	/**
	 * This method waits for given element to be invisible.
	 * 
	 * @return True if the element is invisible within given timeout, false
	 *         otherwise.
	 */
	public boolean waitforInvisible() {
		WebDriverWait wait = new WebDriverWait(getWebDriver(), AppConstants.WAIT_TIMEOUT);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(getBy()));
	}

	/**
	 * This method waits for given element to be visible.
	 * 
	 * @return WebElement if the element is visible within given timeout, Exception
	 *         otherwise.
	 */
	public WebElement waitforVisible() {
		WebDriverWait wait = new WebDriverWait(getWebDriver(), AppConstants.WAIT_TIMEOUT);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(getBy()));
	}

	/**
	 * This method waits for given element to be visible for given timeout
	 * 
	 * @param timeInSeconds
	 * @return
	 */
	public WebElement waitforVisible(int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(getWebDriver(), timeInSeconds);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(getBy()));
	}

	/**
	 * This method waits for given element to be invisible for given timeout
	 * 
	 * @return True if the element is invisible within given timeout, false
	 *         otherwise.
	 */
	public boolean waitforInvisible(int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(getWebDriver(), timeInSeconds);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(getBy()));
	}

	private void highlightElement() {
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].style.border='2px dashed green'",
				getElement());
	}
}
