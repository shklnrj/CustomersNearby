package com.takehome;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CustomerDatasourceReaderTest {
    @org.junit.Test
    public void testMultiReadFromSameSource() throws IOException {
        ArrayList<Customer> customers = CustomerDatasourceReader.createCustomersFromDataSource("https://s3.amazonaws.com/intercom-take-home-test/customers.txt");
        ArrayList<Customer> recreated = CustomerDatasourceReader.createCustomersFromDataSource("https://s3.amazonaws.com/intercom-take-home-test/customers.txt");
        assertEquals("Both arraylists should be of same size", customers.size(), recreated.size());
    }

    @Test(expected = IOException.class)
    public void readFromBadURL() throws IOException{
        ArrayList<Customer> customers = CustomerDatasourceReader.createCustomersFromDataSource("https://amazonaws.com/intercom-take-home-test/customers.txt");
    }
}