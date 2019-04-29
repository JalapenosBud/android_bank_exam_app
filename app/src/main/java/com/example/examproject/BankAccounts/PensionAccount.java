package com.example.examproject.BankAccounts;

import com.example.examproject.Customer.Customer;

public class PensionAccount extends BankAccount {

    int savings;

    Customer customer;

    @Override
    void deposit(int amount) {
        super.deposit(amount);
    }

    @Override
    boolean withdraw() {
        if(customer.age > 77)
        {
            return true;
        }
        else{
            return false;
        }
    }
}
