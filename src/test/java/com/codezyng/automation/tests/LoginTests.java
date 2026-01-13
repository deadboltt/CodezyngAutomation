package com.codezyng.automation.tests;

import com.codezyng.automation.base.BaseTest;
import com.codezyng.automation.pages.LoginPage;
import com.codezyng.automation.pages.ProductsPage;
import com.codezyng.automation.dataproviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
	
    @Test(dataProvider = "loginExcelData", dataProviderClass = LoginDataProvider.class)
    public void loginTest(String username, String password, String expected) {
        LoginPage loginPage = new LoginPage();
        loginPage.enterUsername(username)
                 .enterPassword(password)
                 .clickLogin();

        if (expected.equalsIgnoreCase("success")) {
            ProductsPage productsPage = new ProductsPage();
            Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page should be displayed after successful login");
        } else {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid login");
        }
    }
}
