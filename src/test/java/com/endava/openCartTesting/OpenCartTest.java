package com.endava.openCartTesting;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

public class OpenCartTest extends TestBaseClass {

    @Test
    public void createAccountTest() throws InterruptedException {
        //Navigate My Account -> Register
        CreateAccountPage accountPage = homePage.goToAccountPage();

        //Fill the form with your information (email address, name etc); Submit the form
        ConfirmationPage confirmationPage = accountPage.createAccount("Irina", "Cristea", "irina.cristea@gmail.com", "0766847139", "Password1", "Password1", 1);

        EditAccountPage editAccountPage = confirmationPage.goToEditAccountPage();

        //Assert user from Account -> Edit Information
        Assert.assertEquals("Irina", editAccountPage.getFirstName());
        Assert.assertEquals("Cristea", editAccountPage.getLastName());
        Assert.assertEquals("irina.cristea@gmail.com", editAccountPage.getEmail());
        Assert.assertEquals("0766847139", editAccountPage.getTelephone());
        Thread.sleep(4000);

        //Log out
        editAccountPage.clickOnMyAccount();
        editAccountPage.logout();
    }

    @Test
    public void loginTest() {
        //Navigate to My Account -> Login
        LoginPage loginPage = homePage.goToLoginPage();

        //Fill your email and password
        MyAccountPage myAccountPage = loginPage.login("irina.cristea@endava.com", "Password1");

        //Log out
        myAccountPage.clickOnMyAccount();
        myAccountPage.logout();
    }

    @Test
    public void connectToDataBaseTest() throws SQLException, IOException {
        //Connect to database
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.connectToDataBase();

        //Insert a new address
        dataBaseConnection.insertAddress();

        //Login in application
        LoginPage loginPage = homePage.goToLoginPage();
        MyAccountPage myAccountPage = loginPage.login("irina.cristea@endava.com", "Password1");

        //Navigate Account -> Address book
        AddressBookPage addressBookPage = myAccountPage.goToAddressBookPage();

        //Assert that the address is present in the UI
        String address = "Irina Cristea\n" +
                "Endava\n" +
                "test address 1\n" +
                "test address 2\n" +
                "Bucharest 122222\n" +
                "Romania";
        Assert.assertTrue(addressBookPage.containsAddress(address));
    }

    @Test
    public void productSelectionTest() throws SQLException, InterruptedException, IOException {
        //Connect to database
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        dataBaseConnection.connectToDataBase();

        //Login in the application
        LoginPage loginPage = homePage.goToLoginPage();
        MyAccountPage myAccountPage = loginPage.login("irina.cristea@endava.com", "Password1");

        //Go to Products Page
        ProductsPage productsPage = myAccountPage.goToProductsPage();

        //Click on a random product from Home Page
        SelectedProductPage selectedProductPage = productsPage.selectProduct();
        selectedProductPage.addToCart();
        Thread.sleep(4000);

        //Update value
        dataBaseConnection.updateValue();

        //Verify in the UI that the values has been increased
        Integer qty = dataBaseConnection.getValue();

        System.out.println(qty);

        Assert.assertEquals("2",qty.toString());

        //Log out
        myAccountPage.clickOnMyAccount();
        myAccountPage.logout();
    }
}
