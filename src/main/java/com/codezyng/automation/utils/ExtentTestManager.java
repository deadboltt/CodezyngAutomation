package com.codezyng.automation.utils;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentTestManager {

    private ExtentTestManager() {}

    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void unload() {
        extentTest.remove();
    }
}
