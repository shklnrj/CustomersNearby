package com.takehome;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDatasourceReader {
    public static ArrayList<Customer> createCustomersFromDataSource(String datasource) throws  IOException{
        ArrayList<Customer> customers = new ArrayList<>();
         {
            // write your code here
            URL url = new URL(datasource);
            Scanner s = new Scanner(url.openStream());
            String inputLine = null;
            Customer freshCustomerRead = null;
            while (s.hasNextLine()) {
                inputLine = s.nextLine();
                JSONObject customerJSON = new JSONObject(inputLine);
                System.out.println(customerJSON);
                freshCustomerRead = new Customer(
                        customerJSON.getString("name"),
                        customerJSON.getInt("user_id"),
                        customerJSON.getDouble("latitude"),
                        customerJSON.getDouble("longitude")
                );
                customers.add(freshCustomerRead);
            }
        }
        return customers;
    }
}
