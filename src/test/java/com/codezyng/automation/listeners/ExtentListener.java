package com.codezyng.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.codezyng.automation.base.DriverManager;
import com.codezyng.automation.utils.ExtentManager;
import com.codezyng.automation.utils.ExtentTestManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentListener implements ITestListener {

    private ExtentReports extent = ExtentManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(
                result.getMethod().getMethodName(),
                result.getMethod().getDescription()
        );
        ExtentTestManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        test.fail(result.getThrowable());

        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            String base64Screenshot =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

            test.addScreenCaptureFromBase64String(
                    base64Screenshot,
                    "Failure Screenshot"
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        ExtentTestManager.unload();
    }
}
