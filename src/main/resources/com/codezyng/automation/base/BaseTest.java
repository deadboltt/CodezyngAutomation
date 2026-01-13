package com.codezyng.automation.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.codezyng.automation.config.ConfigReader;
import com.codezyng.automation.driver.DriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        // Create WebDriver using DriverFactory
        driver = DriverFactory.createDriver();

        // 🔥 Store driver in ThreadLocal for listeners & screenshots
        DriverManager.setDriver(driver);

        // Navigate to application URL
        driver.get(ConfigReader.get("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        // Quit browser safely
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }

        // Remove ThreadLocal reference
        DriverManager.unload();
    }
}
