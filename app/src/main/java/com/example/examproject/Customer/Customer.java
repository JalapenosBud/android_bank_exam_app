package com.example.examproject.Customer;

import com.example.examproject.BankAccounts.BankAccount;
import com.example.examproject.BankAccounts.BudgetAccount;
import com.example.examproject.BankAccounts.DefaultAccount;

public class Customer {
    public int age;
    public boolean assigned_to_bank;
    public int total_money;

    BankAccount defaultAccount;
    BankAccount budgetAccount;

    public Customer(){
        defaultAccount = new DefaultAccount();
        budgetAccount = new BudgetAccount();
    }
}
