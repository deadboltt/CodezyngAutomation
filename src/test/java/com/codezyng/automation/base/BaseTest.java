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

        // ---------- Read config (CLI overrides allowed) ----------
        String browser = System.getProperty(
                "browser",
                ConfigReader.getProperty("browser")
        );

        boolean headless = Boolean.parseBoolean(
                System.getProperty(
                        "headless",
                        ConfigReader.getProperty("headless")
                )
        );

        String baseUrl = ConfigReader.getProperty("baseUrl");

        // ---------- Fail fast on bad config ----------
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new RuntimeException("baseUrl is NULL or EMPTY in config.properties");
        }

        if (browser == null || browser.isBlank()) {
            browser = "chrome";
        }


        // ---------- Create WebDriver ----------
        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();

                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                } else {
                    chromeOptions.addArguments("--start-maximized");
                }

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

                if (headless) {
                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("--window-size=1920,1080");
                } else {
                    edgeOptions.addArguments("--start-maximized");
                }

                edgeOptions.addArguments("--disable-notifications");

                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new RuntimeException("❌ Unsupported browser: " + browser);
        }

        // ---------- Store driver immediately ----------
        DriverManager.setDriver(driver);

        if (DriverManager.getDriver() == null) {
            throw new RuntimeException("❌ DriverManager returned NULL after setDriver()");
        }

        // ---------- Navigate ----------
        DriverManager.getDriver().get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        // 📸 Capture screenshot ONLY if driver exists
        if (DriverManager.getDriver() != null) {
            ScreenshotUtils.captureScreenshot(testName);
        } else {
            System.out.println("⚠ Skipping screenshot — driver not initialized for: " + testName);
        }

        // ---------- Quit safely ----------
        WebDriver drv = DriverManager.getDriver();
        if (drv != null) {
            drv.quit();
        }

        DriverManager.unload();
    }
}
