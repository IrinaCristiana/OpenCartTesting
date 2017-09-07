package com.endava.openCartTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditAccountPage {

    @FindBy(xpath = ".//input[@id='input-firstname']")
    private WebElement firstNameInput;

    @FindBy(xpath = ".//input[@id='input-lastname']")
    private WebElement lastNameInput;

    @FindBy(xpath = ".//input[@id='input-email']")
    private WebElement emailInput;

    @FindBy(xpath = ".//input[@id='input-telephone']")
    private WebElement telephoneInput;

    @FindBy(xpath = ".//*[@id='top-links']/ul/li[2]/a/span[2]")
    private WebElement myAccountButton;

    @FindBy(xpath = ".//div[@id='top-links']/ul[@class='list-inline']/li[@class='dropdown open']/ul/li[5]/a")
    private WebElement logoutButton;

    private WebDriver webDriver;

    public EditAccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getFirstName() {
        return firstNameInput.getAttribute("value");
    }

    public String getLastName() {
        return lastNameInput.getAttribute("value");
    }

    public String getEmail() {
        return emailInput.getAttribute("value");
    }

    public String getTelephone() {
        return telephoneInput.getAttribute("value");
    }

    public void clickOnMyAccount() {
        myAccountButton.click();
    }
    //Log out
    public void logout() {
        logoutButton.click();
    }
}