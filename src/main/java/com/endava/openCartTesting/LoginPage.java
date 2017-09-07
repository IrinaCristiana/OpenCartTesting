package com.endava.openCartTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "input-email")
    private WebElement emailBox;

    @FindBy(id = "input-password")
    private WebElement passwordBox;

    @FindBy(xpath = ".//div[@id='content']/div/div[2]/div/form/input[@class='btn btn-primary']")
    private WebElement loginButton;

    private WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Fill your email and password
    public MyAccountPage login(String email, String password) {
        emailBox.sendKeys(email);
        passwordBox.sendKeys(password);
        loginButton.click();

        return PageFactory.initElements(webDriver, MyAccountPage.class);
    }
}
