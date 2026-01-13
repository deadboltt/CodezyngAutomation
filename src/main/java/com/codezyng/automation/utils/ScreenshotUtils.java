package com.codezyng.automation.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.codezyng.automation.base.DriverManager;

public final class ScreenshotUtils {

    private ScreenshotUtils() {}

    /**
     * 📸 Capture screenshot as byte[] (Used by Allure & Extent)
     */
    
    public static byte[] getScreenshotAsBytes() {
        return ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }

    public static String convertToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }


    /**
     * 📸 Capture screenshot and save to screenshots folder (Optional)
     */
    public static String captureScreenshot(String testName) {

        File srcFile = ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.FILE);

        String screenshotsDir = System.getProperty("user.dir")
                + File.separator + "screenshots";

        new File(screenshotsDir).mkdirs();

        String destinationPath =
                screenshotsDir + File.separator + testName + ".png";

        try {
            Files.copy(
                    srcFile.toPath(),
                    Path.of(destinationPath),
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destinationPath;
    }
}
