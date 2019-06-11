package com.example.examproject.Accounts;

import com.example.examproject.Customer.Customer;

public class PensionAccount extends Account {

    public Customer customer;

    public PensionAccount() {
        money = 300;
    }


    @Override
    public String toString() {
        return "PENSION";
    }
}
