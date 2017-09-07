package com.endava.openCartTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    private WebDriver webDriver;

    @FindBy(xpath = ".//*[@id='top-links']/ul/li[2]/a/span[2]")
    private WebElement myAccountButton;

    @FindBy(xpath = ".//div[@id='top-links']/ul[@class='list-inline']/li[@class='dropdown open']/ul/li[5]/a")
    private WebElement logoutButton;

    @FindBy(xpath = ".//aside[@id='column-right']/div/a[4]")
    private WebElement addressBook;

    @FindBy(xpath = ".//div[@id='logo']/h1/a")
    private WebElement logo;

    public MyAccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickOnMyAccount() {
        myAccountButton.click();
    }

    public void logout() {
        logoutButton.click();
    }

    //Navigate Account -> Address book
    public AddressBookPage goToAddressBookPage() {
        addressBook.click();

        AddressBookPage addressBookPage = PageFactory.initElements(webDriver, AddressBookPage.class);
        return addressBookPage;
    }

    public ProductsPage goToProductsPage() {
        logo.click();

        return PageFactory.initElements(webDriver, ProductsPage.class);
    }
}
