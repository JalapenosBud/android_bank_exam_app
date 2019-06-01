package com.example.examproject.BankAccounts;

import com.example.examproject.Customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    public static BankFactory bankFactory = new BankFactory();

    public static List<Customer> customers = new ArrayList<>();

    public Bank()
    {

    }

    public static Customer logged_in_customer;

    public static void add(Customer customer)
    {
        customers.add(customer);
    }

    public static List<Customer> getCustomers()
    {
        return customers;
    }

}
