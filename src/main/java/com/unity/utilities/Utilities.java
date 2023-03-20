package com.unity.utilities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.unity.constants.AppConstants;
import com.unity.customelements.UnityWebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Utilities {

	private static Logger log = LogManager.getLogger();

	private Utilities() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * This method takes screenshot and returns the path to it in file system
	 * 
	 * @param methodName
	 *            - Name of the calling method
	 * @param driver
	 *            - Current instance of webdriver
	 * @return - String path
	 */
	public static byte[] takeFullScreenShot(WebDriver driver) {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(2000))
				.takeScreenshot(driver);
		BufferedImage bImage = screenshot.getImage();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, "png", bos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}

	/**
	 * Utility to fetch data from excel and pass it to dataprovider
	 * 
	 * @throws Exception
	 * 
	 */
	public static Map<String, String> getData(String testName, ExcelUtilities excelUtil) {
		HashMap<String, String> table = new HashMap<>();
		List<String> sheetNames = excelUtil.getSheetNames();
		String currentSheet = "";
		int testCaseStartRowNum = 0;
		for (String sheetName : sheetNames) {
			testCaseStartRowNum = excelUtil.findRow(sheetName, testName);
			if (testCaseStartRowNum >= 0)
				break;
		}
		if (testCaseStartRowNum < 0) {
			log.error("*****TestCase : " + testName + " Not found in Test Data Sheet for environment : "
					+ AppConstants.APPLICATION_ENV + ". Please add an entry of this test case.*****");
			return table;
		}

		int testDataKeyStartRow = testCaseStartRowNum + 1;
		int testDataValueStartRow = testCaseStartRowNum + 2;

		int testDataSetRows = 0;
		while (!excelUtil.getCellData(currentSheet, testDataValueStartRow + testDataSetRows, 0).equals("")) {
			testDataSetRows++;
		}

		int totaldataColumns = 0;
		while (!excelUtil.getCellData(currentSheet, testDataValueStartRow, totaldataColumns).equals("")) {
			totaldataColumns++;
		}

		int dataSetStartRow = testDataValueStartRow;

		for (int rowNum = dataSetStartRow; rowNum < (testDataValueStartRow
				+ testDataSetRows); rowNum++, dataSetStartRow++) {
			for (int colNum = 0; colNum < totaldataColumns; colNum++) {
				table.put(excelUtil.getCellData(currentSheet, testDataKeyStartRow, colNum),
						excelUtil.getCellData(currentSheet, dataSetStartRow, colNum));
			}
		}
		return table;
	}

	/**
	 * Static wait for the given time in seconds
	 * 
	 * @param milliseconds
	 */
	public static void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			Assert.fail(e.getLocalizedMessage());
			Thread.currentThread().interrupt();
		}
	}

	public static void switchToFrame(WebDriver driver, UnityWebElement frame) {
		frame.waitforVisible(AppConstants.FRAME_LOAD_TIMEOUT);
		driver.switchTo().frame(driver.findElement(frame.getBy()));
	}

	public static void dragAndDropElement(WebDriver driver, WebElement fromElement, WebElement toElement) {
		Actions builder = new Actions(driver);
		builder.clickAndHold(fromElement).moveToElement(toElement).release(toElement).build().perform();
	}

	public static String openNewWindow(WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.open();");
		switchToNewWindow(driver);
		return driver.getWindowHandle();
	}

	public static String switchToNewWindow(WebDriver driver) {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String window : windowHandles) {
			wait(2000);
			driver.switchTo().window(window);
		}
		return driver.getWindowHandle();
	}
}
