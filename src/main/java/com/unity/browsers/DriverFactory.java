package com.unity.browsers;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	private DriverFactory() {
		throw new IllegalStateException("Driver Factory Class");
	}

	/**
	 * This method returns the Webdriver instance of a given browser type Supported
	 * browser are : chrome, firefox, IE, Remote
	 * 
	 * @author e5562312 neerajM
	 * @return
	 */
	public static WebDriver getDriver(String browserType) {
		if (browserType != null) {
			if (browserType.equalsIgnoreCase("firefox"))
				return new FirefoxWebDriver().getDriver();
			else if (browserType.equalsIgnoreCase("ie"))
				return new IEWebDriver().getDriver();
			else if (browserType.equalsIgnoreCase("remote"))
				return new RemoteDriver().getDriver();
			else if (browserType.equalsIgnoreCase("edge"))
				return new EdgeWebDriver().getDriver();
			else if (browserType.equalsIgnoreCase("headless"))
				return new HeadlessDriver().getDriver();
			else if (browserType.equalsIgnoreCase("android"))
				return new AndroidWebDriver().getDriver();
		}
		return new ChromeWebDriver().getDriver();
	}
}