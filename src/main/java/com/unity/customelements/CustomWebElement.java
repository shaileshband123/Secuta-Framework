package com.unity.customelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Parent class for all complex web elements.
 **/
public abstract class CustomWebElement {

	/**
	 * The webDriver which can be used in subclasses.
	 **/
	private WebDriver webDriver;

	/**
	 * The locator through which the element(s) used for an action will be
	 * identified.
	 **/
	private By locator;

	/**
	 * Used to access the locators of a webelement.
	 **/
	private WebElementTransformer transformer;

	/**
	 * Constructor.
	 *
	 * @param webDriver
	 *            The webDriver used to interact with the webbrowser.
	 * @param by
	 *            The locator used to identify the element(s) on the website.
	 **/
	public CustomWebElement(WebDriver webDriver, By by) {
		this.webDriver = webDriver;
		locator = by;
		transformer = new WebElementTransformer();

		PageFactory.initElements(new CustomElementFieldDecorator(webDriver, webDriver), this);
	}

	/**
	 * Returns the locator to identify the element(s) on the website.
	 *
	 * @return Returns the locator to identify the element(s) on the website.
	 **/
	public By getBy() {
		return locator;
	}

	/**
	 * Returns value of the given attribute
	 * 
	 * @param attributeName
	 *            The name of the attribute.
	 * @return The attribute/property's current value or null if the value is not
	 *         set.
	 */
	public String getAttribute(String attributeName) {
		return getWebDriver().findElement(getBy()).getAttribute(attributeName);
	}

	/**
	 * Returns the module to transform stuff.
	 *
	 * @return Returns the module to transform stuff.
	 **/
	protected WebElementTransformer transformer() {
		return transformer;
	}

	/**
	 * Returns the webDriver.
	 *
	 * @return Returns the webDriver.
	 **/
	protected WebDriver getWebDriver() {
		return webDriver;
	}

	/**
	 * Returns the used type of a given by locator.
	 *
	 * @return Returns the used type of a given by locator.
	 **/
	protected WebElementTransformer.LocatorType getLocatorType() {
		return transformer.getLocatorType(getBy());
	}

	/**
	 * Returns the locator value of a locator.
	 *
	 * @param type
	 *            The type of the locator.
	 * @return The value of the locator.
	 **/
	protected String getLocatorValue(WebElementTransformer.LocatorType type) {
		return transformer.getLocatorValue(getBy(), type);
	}
}
