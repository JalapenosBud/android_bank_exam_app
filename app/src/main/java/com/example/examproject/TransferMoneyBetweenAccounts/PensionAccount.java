package com.example.examproject.TransferMoneyBetweenAccounts;

public class PensionAccount extends Account {

    public int pension_savings;

    public Customer customer;

    public PensionAccount() {
        money = 300;
    }


    @Override
    public String toString() {
        return "PENSION";
    }
}
