package com.codezyng.automation.tests;

import com.codezyng.automation.base.BaseTest;
import com.codezyng.automation.pages.LoginPage;
import com.codezyng.automation.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

	@Test(description = "Verify user can login with valid credentials")
	public void validLoginTest() {

		LoginPage loginPage = new LoginPage();

		ProductsPage productsPage = loginPage.enterUsername("standard_user").enterPassword("secret_sauce")
				.clickLoginExpectSuccess();

		Assert.assertTrue(productsPage.isProductsPageDisplayed(),
				"Products page should be displayed after successful login");
	}

	@Test(description = "Verify login fails with invalid credentials")
	public void invalidLoginTest() {

		LoginPage loginPage = new LoginPage();

		loginPage.enterUsername("invalid_user").enterPassword("wrong_password").clickLogin();

		Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid login");
	}
}
