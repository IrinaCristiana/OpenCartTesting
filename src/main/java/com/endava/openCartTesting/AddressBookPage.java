package com.endava.openCartTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddressBookPage {

    @FindBy(xpath = ".//div[@id='content']/div[@class='table-responsive']/table/tbody/tr/td[1]")
    private List<WebElement> addressBookList;

    private WebDriver webDriver;

    public AddressBookPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public Boolean containsAddress(String address) {
        Boolean value = false;
        for (Integer i = 0; i < addressBookList.size(); i++) {
            if (addressBookList.get(i).getText().equals(address)) {
                value = true;
            }
        }
        return value;
    }

}
