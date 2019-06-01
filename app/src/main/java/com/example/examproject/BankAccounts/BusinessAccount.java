package com.example.examproject.BankAccounts;

public class BusinessAccount extends Account{

    public BusinessAccount() {
        money = 100;
    }

    @Override
    public String toString() {
        return "BUSINESS";
    }
}
