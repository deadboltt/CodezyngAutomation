package com.codezyng.automation.utils;

import com.codezyng.automation.base.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

	private static WebDriverWait getWait() {
		WebDriver driver = DriverManager.getDriver();
		if (driver == null) {
			throw new RuntimeException("Driver is null. Did you forget to call DriverManager.initDriver()?");
		}
		return new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	/* ---------- ELEMENT WAITS ---------- */

	public static WebElement waitForElementVisible(By locator) {
		return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static WebElement waitForElementClickable(By locator) {
		return getWait().until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static boolean isElementPresent(By locator) {
		try {
			DriverManager.getDriver().findElement(locator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getText(By locator) {
		return waitForElementVisible(locator).getText();
	}

	/* ---------- URL WAITS ---------- */

	public static void waitForUrlContains(String value) {
		getWait().until(ExpectedConditions.urlContains(value));
	}
}
