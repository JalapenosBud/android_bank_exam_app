package com.example.examproject.BankAccounts;

import com.example.examproject.Customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    BankFactory bankFactory = new BankFactory();

    public List<Customer> customers;

    public Bank()
    {
        customers = new ArrayList<>();
    }

    public void add(Customer customer)
    {
        customers.add(customer);
    }

}
