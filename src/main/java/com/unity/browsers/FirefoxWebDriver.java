package com.unity.browsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxWebDriver implements Driver {

	protected Logger log = LogManager.getLogger();
	
	/**
	 * This method provides an implementation of getDriver() method for Firefox
	 * browser
	 * 
	 * @author e5562312 neerajM
	 */
	public WebDriver getDriver() {
		String firefoxPath = System.getProperty("user.dir") + "/drivers/geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", firefoxPath);
		log.info("[ Initializing firefox driver ]");
		return new FirefoxDriver();
	}
}