package com.codezyng.automation.pages;

import com.codezyng.automation.base.DriverManager;
import com.codezyng.automation.utils.WaitUtils;
import org.openqa.selenium.By;

public class CheckoutStepOnePage {

    /* ---------- Locators ---------- */
    private final By firstNameField = By.id("first-name");
    private final By lastNameField  = By.id("last-name");
    private final By postalCode     = By.id("postal-code");
    private final By continueBtn    = By.id("continue");
    private final By title          = By.className("title");

    /* ---------- Constructor ---------- */
    public CheckoutStepOnePage() {
        WaitUtils.waitForElementVisible(title);
    }

    /* ---------- Actions ---------- */

    public CheckoutStepOnePage enterFirstName(String firstName) {
        DriverManager.getDriver().findElement(firstNameField).sendKeys(firstName);
        return this;
    }

    public CheckoutStepOnePage enterLastName(String lastName) {
        DriverManager.getDriver().findElement(lastNameField).sendKeys(lastName);
        return this;
    }

    public CheckoutStepOnePage enterPostalCode(String zip) {
        DriverManager.getDriver().findElement(postalCode).sendKeys(zip);
        return this;
    }

    public CheckoutOverviewPage clickContinue() {
        DriverManager.getDriver().findElement(continueBtn).click();
        WaitUtils.waitForUrlContains("checkout-step-two");
        return new CheckoutOverviewPage();
    }
}
