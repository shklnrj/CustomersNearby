package com.takehome;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    public static void printCustomersInLocationRange(PriorityQueue<Customer> customersInLocationRange, Location location){
        if (customersInLocationRange.size() > 0) {
            System.out.println("There are "+customersInLocationRange.size()+" locations in the given range.");
            while (customersInLocationRange.size() >0){
                Customer customer = customersInLocationRange.remove();
                System.out.println("The distance of customer " + customer.getName()+ " with user Id "+customer.getUserId()+ " at " + customer.getLatitude() + " , " + customer.getLongitude() + " location from " +
                        "office is " +
                        DistanceHelper.distance(location.getLatitude(), location.getLongitude(), customer.getLatitude(), customer.getLongitude(), "K"));
            }
        }else{
            System.out.println("There was no customer in the range of given location.");
        }
    }
    public static void main(String[] args) throws IOException {
        // get all the customers in an arraylist
        ArrayList<Customer> customers = CustomerDatasourceReader.createCustomersFromDataSource("https://s3.amazonaws.com/intercom-take-home-test/customers.txt");
        Location officeLocation = new Location("Intercom Office", 53.339428, -6.257664);
        // get all the customers who are in the distance range from the office
        PriorityQueue<Customer> customersInRange = DistanceHelper.getCustomersInLocationRange(customers, officeLocation, 100.0);
        // use the max heap to print the customers in decreasing order of their IDs
        printCustomersInLocationRange(customersInRange, officeLocation);
    }
}
