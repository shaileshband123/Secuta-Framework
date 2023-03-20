package com.unity.browsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeWebDriver implements Driver {

	protected Logger log = LogManager.getLogger();

	/**
	 * This method provides an implementation of getDriver() method for Edge browser
	 * 
	 * @author e5562312 neerajM
	 */
	public WebDriver getDriver() {
		log.info("[ Initializing Edge driver ]");
		String edgePath = System.getProperty("user.dir") + "/drivers/msedgedriver.exe";
		System.setProperty("webdriver.edge.driver", edgePath);

		return new EdgeDriver();
	}
}
