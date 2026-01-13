package com.codezyng.automation.pages;

import com.codezyng.automation.base.DriverManager;
import com.codezyng.automation.utils.WaitUtils;
import org.openqa.selenium.By;

public class CheckoutOverviewPage {

    /* ---------- Locators ---------- */
    private final By finishButton = By.id("finish");
    private final By title        = By.className("title");

    /* ---------- Constructor ---------- */
    public CheckoutOverviewPage() {
        WaitUtils.waitForElementVisible(title);
    }

    /* ---------- Actions ---------- */

    public OrderConfirmationPage clickFinish() {
        DriverManager.getDriver().findElement(finishButton).click();
        WaitUtils.waitForUrlContains("checkout-complete");
        return new OrderConfirmationPage();
    }
}
