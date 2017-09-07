package com.endava.openCartTesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class DataBaseConnection{
     Connection connection;
     Statement statement;

    public  Connection connectToDataBase() throws SQLException, IOException {


     try {
         ReadPropertyFile data = new ReadPropertyFile();

         connection = DriverManager.getConnection(data.getUrl(), data.getUser(), data.getPassword());
     }
     catch (SQLException e){
         System.out.println("Connection failed");
         e.printStackTrace();
         return null;
     }
     if (connection != null){
         System.out.println("Connection Success");
         return connection;
     }
     else System.out.println("Connection Failed");
     return null;
    }

    public  void insertAddress() throws SQLException{
        try {
            //Create Query to the Database using the Statement Object.
            statement = connection.createStatement();

            //Add a new entry for your account in the oc_address
            String query = "INSERT INTO oc_address (customer_id, firstname, lastname, company, address_1,address_2, city, postcode, country_id, custom_field)\n" +
                            "VALUES ((select customer_id from oc_customer where email='irina.cristea@endava.com'),'Irina', 'Cristea','Endava', 'test address 1', 'test address 2','Bucharest','191919', (select country_id from oc_country where name='Romania'), '0')";
            statement.executeUpdate(query);
            statement.close();
            connection.close();

        }catch (Exception e) {
            System.out.println(e);
        }
        finally {
            System.out.println("Insert completed");
        }
    }

    public  void updateValue() throws SQLException {

        try {
            //Create Query to the Database using the Statement Object.
             statement = connection.createStatement();

            //From database (oc_cart) for the current session increase quantity by 1
            String query = "UPDATE oc_cart\n" +
                    "SET quantity = quantity+1\n" +
                    "WHERE customer_id = 3";

            statement.executeUpdate(query);

            String queryResult = "SELECT quantity\n" +
                    "FROM oc_cart\n" +
                    "WHERE customer_id = 3";

        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {
            System.out.println("Update completed");
        }

    }
    public  Integer getValue() throws SQLException {

        try {
            //Create Query to the Database using the Statement Object.
            statement = connection.createStatement();

            String query = "SELECT quantity\n" +
                                 "FROM oc_cart\n" +
                                 "WHERE customer_id = 3";

            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                Integer qty = resultSet.getInt("quantity");
                System.out.println("ksjdhfksdjf");
                return qty;
            }
        }

        catch (Exception e){
            System.out.println(e);
        }
        finally {
            System.out.println("Function completed");
        }

        return null;
    }
}
