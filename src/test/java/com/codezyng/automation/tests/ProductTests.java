package com.codezyng.automation.tests;

import com.codezyng.automation.base.BaseTest;
import com.codezyng.automation.pages.LoginPage;
import com.codezyng.automation.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTests extends BaseTest {

	@Test(description = "Verify products page loads successfully")
	public void verifyProductsPageLoadedTest() {

		ProductsPage productsPage = new LoginPage().enterUsername("standard_user").enterPassword("secret_sauce")
				.clickLoginExpectSuccess();

		Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page should be visible");
	}

	@Test(description = "Verify product can be added to cart")
	public void verifyAddProductToCartTest() {

		ProductsPage productsPage = new LoginPage().enterUsername("standard_user").enterPassword("secret_sauce")
				.clickLoginExpectSuccess();

		productsPage.addProductToCartByName("Sauce Labs Backpack");

		Assert.assertEquals(productsPage.getCartBadgeCount(), 1, "Cart badge count should be 1 after adding product");
	}
}
