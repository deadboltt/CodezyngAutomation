package com.codezyng.automation.tests;

import com.codezyng.automation.base.BaseTest;
import com.codezyng.automation.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

	@Test(description = "Verify user can complete checkout successfully")
	public void completeCheckoutTest() {

		ProductsPage productsPage = new LoginPage().enterUsername("standard_user").enterPassword("secret_sauce")
				.clickLoginExpectSuccess();

		productsPage.addProductToCartByName("Sauce Labs Backpack");

		CartPage cartPage = productsPage.goToCart();

		CheckoutStepOnePage stepOne = cartPage.proceedToCheckout();

		CheckoutOverviewPage overviewPage = stepOne.enterFirstName("Rajath").enterLastName("Pai")
				.enterPostalCode("560001").clickContinue();

		OrderConfirmationPage confirmationPage = overviewPage.clickFinish();

		Assert.assertTrue(confirmationPage.isOrderSuccessful(), "Order confirmation message should be displayed");
	}
}
