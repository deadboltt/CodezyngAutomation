package com.codezyng.automation.base;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
        // prevent instantiation
    }

    // Store WebDriver
    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    // Retrieve WebDriver
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Clear ThreadLocal
    public static void unload() {
        driver.remove();
    }
}
