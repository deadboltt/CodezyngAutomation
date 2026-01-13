package com.codezyng.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.codezyng.automation.base.DriverManager;

public class LoginPage {

    private WebDriver driver;

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage() {
        this.driver = DriverManager.getDriver();

        if (this.driver == null) {
            throw new RuntimeException("WebDriver is null in LoginPage constructor");
        }

        PageFactory.initElements(driver, this);
    }

    public LoginPage enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
        return this;
    }

    public LoginPage enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
        return this;
    }

    public ProductsPage clickLoginExpectSuccess() {
        driver.findElement(loginButton).click();
        return new ProductsPage();
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }
}
