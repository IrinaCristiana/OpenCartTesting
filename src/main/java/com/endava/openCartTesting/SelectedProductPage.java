package com.endava.openCartTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectedProductPage {
    @FindBy(id = "button-cart")
    private WebElement addToCartButton;

    private WebDriver webDriver;

    public SelectedProductPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void addToCart() {
        addToCartButton.click();
    }
}
