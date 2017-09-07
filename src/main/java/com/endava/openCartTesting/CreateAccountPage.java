package com.endava.openCartTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateAccountPage {

    @FindBy(xpath = ".//input[@id='input-firstname']")
    private WebElement firstNameBox;

    @FindBy(xpath = ".//input[@id='input-lastname']")
    private WebElement lastNameBox;

    @FindBy(xpath = ".//input[@id='input-email']")
    private WebElement emailBox;

    @FindBy(xpath = ".//input[@id='input-telephone']")
    private WebElement telephoneBox;

    @FindBy(xpath = ".//input[@id='input-password']")
    private WebElement passwordBox;

    @FindBy(xpath = ".//input[@id='input-confirm']")
    private WebElement passwordConfirmBox;

    @FindBy(xpath = ".//div[@id='content']/form/fieldset[3]/div[@class='form-group']/div/label")
    private List<WebElement> subscribeButtonsList;

    @FindBy(xpath = ".//div[@id='content']/form/div[@class='buttons']/div/input[@type='checkbox']")
    private WebElement agreePolicyCheckBox;

    @FindBy(xpath = ".//div[@id='content']/form/div[@class='buttons']/div/input[@class='btn btn-primary']")
    private WebElement continueButton;

    private WebDriver webDriver;

    public CreateAccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Fill the form with your information (email address, name etc)
    public ConfirmationPage createAccount(String firstName, String lastName, String email, String telephone, String password, String confirmPassword, Integer index) {
        firstNameBox.sendKeys(firstName);
        lastNameBox.sendKeys(lastName);
        emailBox.sendKeys(email);
        telephoneBox.sendKeys(telephone);
        passwordBox.sendKeys(password);
        passwordConfirmBox.sendKeys(confirmPassword);
        subscribeButtonsList.get(index - 1).click();
        agreePolicyCheckBox.click();

        //Submit the form
        continueButton.click();

        return PageFactory.initElements(webDriver, ConfirmationPage.class);

    }
}
