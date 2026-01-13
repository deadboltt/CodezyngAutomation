package com.codezyng.automation.listeners;

import com.aventstack.extentreports.Status;
import com.codezyng.automation.utils.ExtentManager;
import com.codezyng.automation.utils.ExtentTestManager;
import com.codezyng.automation.utils.ScreenshotUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.setTest(
                ExtentManager.getExtent()
                        .createTest(result.getMethod().getMethodName())
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest()
                .log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest()
                .log(Status.FAIL, result.getThrowable());

        byte[] screenshot = ScreenshotUtils.getScreenshotAsBytes();
        ExtentTestManager.getTest()
                .addScreenCaptureFromBase64String(
                        ScreenshotUtils.convertToBase64(screenshot),
                        "Failure Screenshot"
                );
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        ExtentManager.getExtent().flush();
        ExtentTestManager.unload();
    }
}
