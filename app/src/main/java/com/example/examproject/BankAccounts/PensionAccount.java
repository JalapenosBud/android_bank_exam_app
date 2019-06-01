package com.example.examproject.BankAccounts;

import com.example.examproject.Customer.Customer;

public class PensionAccount extends Account {

    public int pension_savings;

    public Customer customer;

    public PensionAccount() {
        money = 300;
    }

    @Override
    public void deposit()
    {

    }

    @Override
    public void withdraw()
    {

    }

    @Override
    public void updateAccount(float money) {
        this.money += money;
    }

    @Override
    public String toString() {
        return "PENSION";
    }
}
