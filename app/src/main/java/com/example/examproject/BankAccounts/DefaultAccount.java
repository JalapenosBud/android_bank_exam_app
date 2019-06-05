package com.example.examproject.BankAccounts;

public class DefaultAccount extends Account {

    public DefaultAccount() {
        money = 200;
    }


    @Override
    public String toString() {
        return "DEFAULT\t" + money;
    }
}
