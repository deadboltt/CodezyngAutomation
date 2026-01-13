package com.codezyng.automation.pages;

import com.codezyng.automation.utils.WaitUtils;
import org.openqa.selenium.By;

public class OrderConfirmationPage {

    private final By successHeader = By.className("complete-header");
    private final By successText   = By.className("complete-text");

    public OrderConfirmationPage() {
        WaitUtils.waitForElementVisible(successHeader);
    }

    public boolean isOrderSuccessful() {
        return WaitUtils.isElementPresent(successHeader);
    }

    public String getConfirmationMessage() {
        return WaitUtils.getText(successText);
    }
}
