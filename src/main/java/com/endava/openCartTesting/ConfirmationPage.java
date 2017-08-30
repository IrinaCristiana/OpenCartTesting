package com.endava.openCartTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {

    @FindBy(xpath = ".//aside[@id='column-right']/div[@class='list-group']/a[2]")
    private WebElement editAccountMenuItem;

    private WebDriver webDriver;

    public ConfirmationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public EditAccountPage goToEditAccountPage() {
        editAccountMenuItem.click();

        EditAccountPage editAccountPage = PageFactory.initElements(webDriver, EditAccountPage.class);
        return editAccountPage;
    }
}
