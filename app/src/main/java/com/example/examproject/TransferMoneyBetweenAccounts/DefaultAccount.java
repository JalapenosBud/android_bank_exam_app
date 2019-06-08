package com.example.examproject.TransferMoneyBetweenAccounts;

public class DefaultAccount extends Account {

    public DefaultAccount() {
        money = 200;
    }


    @Override
    public String toString() {
        return "DEFAULT";
    }

    @Override
    public String getMoneyAndAccountString() {
        return super.getMoneyAndAccountString();
    }
}
