package com.takehome;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DistanceHelper {
    public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    static class CustomerComparator implements Comparator<Customer>{
        @Override
        public int compare(Customer first, Customer second) {
            return (second.userId-first.userId);
        }
    }
    public static PriorityQueue<Customer> getCustomersInLocationRange(ArrayList<Customer> customers, Location location, double cutoffDistance){
        PriorityQueue<Customer> prq = new PriorityQueue<Customer>(customers.size(), new CustomerComparator());
        for (Customer customer:customers
             ) {
            double customerDistanceFromReference = distance(location.latitude, location.longitude, customer.latitude, customer.longitude, "K");
            System.out.println("The distance from reference was "+customerDistanceFromReference);
            if (customerDistanceFromReference < cutoffDistance){
                prq.add(customer);
            }
        }
        return prq;
    }
}
