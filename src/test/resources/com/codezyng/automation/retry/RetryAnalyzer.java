package com.codezyng.automation.retry;

import org.openqa.selenium.TimeoutException;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result) {

        Throwable throwable = result.getThrowable();

        // Retry ONLY on Selenium TimeoutException
        if (throwable instanceof TimeoutException ||
                (throwable != null && throwable.getCause() instanceof TimeoutException)) {

            if (retryCount < MAX_RETRY_COUNT) {
                retryCount++;
                System.out.println(
                        "🔁 Retrying test due to TimeoutException: "
                                + result.getName()
                                + " | Retry count: "
                                + retryCount
                );
                return true;
            }
        }

        return false;
    }
}
