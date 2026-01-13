package com.codezyng.automation.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.codezyng.automation.base.DriverManager;

public class ScreenshotUtils {

    private ScreenshotUtils() {}

    public static void captureScreenshot(String testName) {

        WebDriver driver = DriverManager.getDriver();

        // ✅ ABSOLUTE SAFETY CHECK
        if (driver == null) {
            System.out.println("⚠ Driver is null. Screenshot skipped for test: " + testName);
            return;
        }

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String path = System.getProperty("user.dir")
                    + "/screenshots/"
                    + testName + "_" + timestamp + ".png";

            FileUtils.copyFile(src, new File(path));

        } catch (Exception e) {
            System.out.println("⚠ Screenshot capture failed: " + e.getMessage());
        }
    }
}
