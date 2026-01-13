package com.codezyng.automation.base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.codezyng.automation.config.ConfigReader;
import com.codezyng.automation.utils.ScreenshotUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        System.out.println("🔥 BaseTest @BeforeMethod EXECUTED");

        String browser = System.getProperty(
                "browser",
                ConfigReader.getProperty("browser")
        );

        String baseUrl = ConfigReader.getProperty("baseUrl");

        if (browser == null || browser.isBlank()) {
            browser = "chrome";
            System.out.println("⚠ Browser not set. Defaulting to chrome");
        }

        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-infobars");
                chromeOptions.addArguments("--disable-save-password-bubble");

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);
                prefs.put("profile.password_manager_leak_detection_dialog_shown", true);

                chromeOptions.setExperimentalOption("prefs", prefs);

                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();

                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--disable-notifications");

                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new RuntimeException("❌ Unsupported browser: " + browser);
        }

        // Store driver in ThreadLocal
        DriverManager.setDriver(driver);

        if (DriverManager.getDriver() == null) {
            throw new RuntimeException("❌ WebDriver is NULL after initialization");
        }

        DriverManager.getDriver().get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        switch (result.getStatus()) {

            case ITestResult.SUCCESS:
                ScreenshotUtils.captureScreenshot(testName + "_PASS");
                break;

            case ITestResult.FAILURE:
                ScreenshotUtils.captureScreenshot(testName + "_FAIL");
                break;

            case ITestResult.SKIP:
                ScreenshotUtils.captureScreenshot(testName + "_SKIP");
                break;

            default:
                break;
        }

        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
        }

        DriverManager.unload();
    }

}
