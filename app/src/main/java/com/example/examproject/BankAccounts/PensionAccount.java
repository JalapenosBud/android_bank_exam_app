package com.example.examproject.BankAccounts;

import com.example.examproject.Customer.Customer;

public class PensionAccount extends Account {

    public int pension_savings;

    public Customer customer;

    public PensionAccount() {
        money = 300;
    }


    @Override
    public String toString() {
        return "PENSION\t" + money;
    }
}
