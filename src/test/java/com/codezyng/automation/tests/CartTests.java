package com.codezyng.automation.tests;

import com.codezyng.automation.base.BaseTest;
import com.codezyng.automation.pages.CartPage;
import com.codezyng.automation.pages.LoginPage;
import com.codezyng.automation.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

	@Test(description = "Verify product is added to cart")
	public void verifyProductAddedToCartTest() {

		ProductsPage productsPage = new LoginPage().enterUsername("standard_user").enterPassword("secret_sauce")
				.clickLoginExpectSuccess();

		productsPage.addProductToCartByName("Sauce Labs Backpack");

		CartPage cartPage = productsPage.goToCart();

		Assert.assertEquals(cartPage.getCartItemsCount(), 1, "Cart should contain 1 product");
	}

	@Test(description = "Verify product can be removed from cart")
	public void removeProductFromCartTest() {

		ProductsPage productsPage = new LoginPage().enterUsername("standard_user").enterPassword("secret_sauce")
				.clickLoginExpectSuccess();

		productsPage.addProductToCartByName("Sauce Labs Backpack");

		CartPage cartPage = productsPage.goToCart();
		cartPage.removeProductByName("Sauce Labs Backpack");

		Assert.assertEquals(cartPage.getCartItemsCount(), 0, "Cart should be empty after removing product");
	}
}
