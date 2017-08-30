package com.endava.openCartTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

    @FindBy(xpath = ".//div[@id='content']/div[@class='row']/div[1]/div/div[@class='caption']/h4/a")
    private WebElement product;

    private WebDriver webDriver;

    public ProductsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Click on a random product from Home Page
    public SelectedProductPage selectProduct() {
        product.click();

        SelectedProductPage selectedProductPage = PageFactory.initElements(webDriver, SelectedProductPage.class);
        return selectedProductPage;
    }
}
