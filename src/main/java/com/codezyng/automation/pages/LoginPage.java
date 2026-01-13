package com.codezyng.automation.pages;

import com.codezyng.automation.base.DriverManager;
import com.codezyng.automation.utils.WaitUtils;
import org.openqa.selenium.By;

public class LoginPage {

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton   = By.id("login-button");
    private final By errorMessage  = By.cssSelector("[data-test='error']");

    public LoginPage() {
        WaitUtils.waitForElementVisible(usernameInput);
    }

    public LoginPage enterUsername(String username) {
        DriverManager.getDriver().findElement(usernameInput).clear();
        DriverManager.getDriver().findElement(usernameInput).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        DriverManager.getDriver().findElement(passwordInput).clear();
        DriverManager.getDriver().findElement(passwordInput).sendKeys(password);
        return this;
    }

    /* Used for invalid login (Excel / DataProvider) */
    public void clickLogin() {
        DriverManager.getDriver().findElement(loginButton).click();
    }

    /* Used for Product / Cart / Checkout tests */
    public ProductsPage clickLoginExpectSuccess() {
        DriverManager.getDriver().findElement(loginButton).click();
        WaitUtils.waitForUrlContains("inventory");
        return new ProductsPage();
    }

    public boolean isErrorMessageDisplayed() {
        return WaitUtils.isElementPresent(errorMessage);
    }

    public String getErrorMessageText() {
        return WaitUtils.getText(errorMessage);
    }
}
