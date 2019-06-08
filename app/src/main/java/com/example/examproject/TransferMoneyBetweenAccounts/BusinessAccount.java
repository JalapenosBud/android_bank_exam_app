package com.example.examproject.TransferMoneyBetweenAccounts;

public class BusinessAccount extends Account {

    public BusinessAccount() {
        money = 100;
    }

    @Override
    public String toString() {
        return "BUSINESS";
    }

    @Override
    public String getMoneyAndAccountString() {
        return super.getMoneyAndAccountString();
    }
}
