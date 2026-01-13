package com.codezyng.automation.listeners;

import com.codezyng.automation.utils.ScreenshotUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    
    @Override
    public void onTestStart(ITestResult result) {
        // Optional: Pre-test screenshot
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        takeScreenshot(result, "PASS");
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        takeScreenshot(result, "FAIL");
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        takeScreenshot(result, "SKIP");
    }
    
    private void takeScreenshot(ITestResult result, String status) {
        String testClassName = result.getTestClass().getName().replace("com.codezyng.automation.tests.", "");
        ScreenshotUtils.captureScreenshot(testClassName);
    }
}
