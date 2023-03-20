package com.unity.browsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessDriver implements Driver {

	protected Logger log = LogManager.getLogger();

	/**
	 * This method provides an implementation of getDriver() method for Headless
	 * Chrome browser
	 * 
	 * @author e5571804 tusharG
	 */
	public WebDriver getDriver() {
		log.info("[ Initializing Headless chrome driver ]");
		String chromePath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromePath);
		ChromeOptions options = new ChromeOptions();
		options.merge(options);
		options.setHeadless(true);
		options.addArguments("--no-default-browser-check");
		options.addArguments("--no-proxy-server");
		options.addArguments("--proxy-server='direct://'");
		options.addArguments("--proxy-bypass-list=*");
		options.addArguments("--window-size=1300,1000");

		return new ChromeDriver(options);
	}
}
