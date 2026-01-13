package com.codezyng.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {}

    public static ExtentReports getInstance() {

        if (extent == null) {
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("D:\\Selenium Assignment\\ecommerce-automation-framework\\reports\\ExtentReport.html");

            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("Codezyng Automation");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Browser", "Chrome");
        }

        return extent;
    }
}
