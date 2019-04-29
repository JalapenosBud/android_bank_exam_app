package com.example.examproject.BankAccounts;

import com.example.examproject.Customer.Customer;

public class BankAccount {

    Customer customer;
    int total_balance;

    void deposit(int amount){
        total_balance += amount;
    }

    boolean withdraw(){
        if(customer.age > 77)
        {
            return true;
        }
        else{
            return false;
        }
    }

}
