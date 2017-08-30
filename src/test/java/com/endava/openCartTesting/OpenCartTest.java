package com.endava.openCartTesting;

import org.junit.Assert;
import org.junit.Test;

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
    public void connectToDataBase() throws SQLException {
        //Connect to database
        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.164.15:3306/bitnami_opencart", "root", "root");
        if (connection != null) {
            System.out.println("Connected to the database");
        }
        //Create Query to the Database using the Statement Object.
        Statement statement = connection.createStatement();

        //Add a new entry for your account in the oc_address
        String query = "INSERT INTO oc_address (customer_id, firstname, lastname, company, address_1,address_2, city, postcode, country_id, custom_field)\n" +
                "VALUES ((select customer_id from oc_customer where email='irina.cristea@endava.com'),'Irina', 'Cristea','Endava', 'test address 1', 'test address 2','Bucharest','122222', (select country_id from oc_country where name='Romania'), '0')";
        statement.executeUpdate(query);
        statement.close();
        connection.close();

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
    public void productSelectionTest() throws SQLException, InterruptedException {
        //Connect to database
        Connection connection = DriverManager.getConnection("jdbc:mariadb://192.168.164.15:3306/bitnami_opencart", "root", "root");
        if (connection != null) {
            System.out.println("Connected to the database");
        }
        //Create Query to the Database using the Statement Object.
        Statement statement = connection.createStatement();

        //Login in the application
        LoginPage loginPage = homePage.goToLoginPage();
        MyAccountPage myAccountPage = loginPage.login("irina.cristea@endava.com", "Password1");
        ProductsPage productsPage = myAccountPage.goToProductsPage();

        //Click on a random product from Home Page
        SelectedProductPage selectedProductPage = productsPage.selectProduct();
        selectedProductPage.addToCart();
        Thread.sleep(4000);

        //From database (oc_cart) for the current session increase quantity by 1
        String query = "UPDATE oc_cart\n" +
                "SET quantity = quantity+1\n" +
                "WHERE customer_id = 3";

        statement.executeUpdate(query);

        String queryResult = "SELECT quantity\n" +
                "FROM oc_cart\n" +
                "WHERE customer_id = 3";

        ResultSet result = statement.executeQuery(queryResult);
        if (result.next()) {
            System.out.println("Updated quantity: "+ result.getString(1));

            //Verify in the UI that the values has been increased
            Assert.assertEquals("6", result.getString(1));
        }
        //Log out
        myAccountPage.clickOnMyAccount();
        myAccountPage.logout();
    }
}
