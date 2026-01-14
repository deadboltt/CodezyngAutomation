package com.codezyng.automation.pages;

import com.codezyng.automation.base.DriverManager;
import com.codezyng.automation.utils.WaitUtils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage {

	// Locators
	private final By cartTitle = By.className("title");
	private final By cartItems = By.className("cart_item");
	private final By checkoutButton = By.id("checkout");
	private final By inventoryItem = By.className("inventory_item");

	// Constructor
	public CartPage() {
		WaitUtils.waitForElementVisible(cartTitle);
	}

	// Page Checks
	public boolean isCartPageDisplayed() {
		return WaitUtils.isElementPresent(cartTitle);
	}

	// Cart Actions

	public int getCartItemsCount() {
		return DriverManager.getDriver().findElements(By.className("cart_item")).size();
	}

	// Remove product from cart by visible product name

	public void removeProductByName(String productName) {
		List<WebElement> cartItems = DriverManager.getDriver().findElements(By.className("cart_item"));

		for (WebElement item : cartItems) {
			String name = item.findElement(By.className("inventory_item_name")).getText();

			if (name.equalsIgnoreCase(productName)) {
				item.findElement(By.tagName("button")).click();
				return;
			}
		}

		throw new RuntimeException("Product not found to remove: " + productName);
	}

	public CheckoutStepOnePage proceedToCheckout() {
		DriverManager.getDriver().findElement(checkoutButton).click();
		WaitUtils.waitForUrlContains("checkout-step-one");
		return new CheckoutStepOnePage();
	}
}
