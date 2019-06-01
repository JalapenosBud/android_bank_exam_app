package com.example.examproject.BankAccounts;

public class DefaultAccount extends Account {

    public DefaultAccount() {
        money = 200;
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
        return "DEFAULT";
    }
}
