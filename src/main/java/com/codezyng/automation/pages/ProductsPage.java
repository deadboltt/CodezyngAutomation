package com.codezyng.automation.pages;

import com.codezyng.automation.base.DriverManager;
import com.codezyng.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {

	// Locators
	private final By pageTitle = By.className("title");
	private final By inventoryItem = By.className("inventory_item");
	private final By cartIcon = By.className("shopping_cart_link");
	private final By cartBadge = By.className("shopping_cart_badge");

	// Constructor
	public ProductsPage() {
		WaitUtils.waitForElementVisible(pageTitle);
	}

	// Validations
	public boolean isProductsPageDisplayed() {
		return DriverManager.getDriver().findElement(pageTitle).getText().equalsIgnoreCase("Products");
	}

	// Actions
	// Add product to cart by visible product name
	public void addProductToCartByName(String productName) {

		List<WebElement> products = DriverManager.getDriver().findElements(inventoryItem);

		for (WebElement product : products) {
			String name = product.findElement(By.className("inventory_item_name")).getText();

			if (name.equalsIgnoreCase(productName)) {
				product.findElement(By.tagName("button")).click();
				return;
			}
		}

		throw new RuntimeException("Product not found to add: " + productName);
	}

	// Navigate to cart page
	public CartPage goToCart() {
		DriverManager.getDriver().findElement(cartIcon).click();
		WaitUtils.waitForUrlContains("cart");
		return new CartPage();
	}

	// Get cart badge count (0 if not visible)
	public int getCartBadgeCount() {
		if (DriverManager.getDriver().findElements(cartBadge).isEmpty()) {
			return 0;
		}
		return Integer.parseInt(DriverManager.getDriver().findElement(cartBadge).getText());
	}
}
