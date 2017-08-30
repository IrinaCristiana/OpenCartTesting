package com.endava.openCartTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = ".//div[@id='top-links']/ul[@class='list-inline']/li[@class='dropdown']/a/span[1]")
    private WebElement myAccountButton;

    @FindBy(xpath = ".//div[@id='top-links']/ul[@class='list-inline']/li[@class='dropdown open']/ul/li[1]/a")
    private WebElement registerButton;

    @FindBy(xpath = ".//div[@id='top-links']/ul[@class='list-inline']/li[@class='dropdown open']/ul/li[2]/a")
    private WebElement loginButton;

    private WebDriver webDriver;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Navigate My Account-> Register
    public CreateAccountPage goToAccountPage() {

        myAccountButton.click();
        registerButton.click();

        CreateAccountPage accountPage = PageFactory.initElements(webDriver, CreateAccountPage.class);
        return accountPage;
    }
    //Navigate to My Account -> Login
    public LoginPage goToLoginPage() {

        myAccountButton.click();
        loginButton.click();

        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        return loginPage;
    }

}
