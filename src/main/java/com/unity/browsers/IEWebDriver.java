package com.unity.browsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IEWebDriver implements Driver {

	protected Logger log = LogManager.getLogger();
	
	/**
	 * This method provides an implementation of getDriver() method for Internet
	 * Explorer browser
	 * 
	 * @author e5562312 neerajM
	 */
	public WebDriver getDriver() {
		String iePath = System.getProperty("user.dir") + "/drivers/IEDriverServer.exe";
		System.setProperty("webdriver.ie.driver", iePath);
		log.info("[ Initializing Internet Explorer driver ]");
		return new InternetExplorerDriver();
	}
}
