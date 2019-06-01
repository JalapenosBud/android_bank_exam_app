package com.example.examproject.BankAccounts;

public class BusinessAccount extends Account{

    public BusinessAccount() {
        money = 100;
    }

    @Override
    public void deposit() {

    }

    @Override
    public void withdraw() {

    }

    @Override
    public void updateAccount(float money) {
        this.money += money;
    }

    @Override
    public String toString() {
        return "BUSINESS";
    }
}
