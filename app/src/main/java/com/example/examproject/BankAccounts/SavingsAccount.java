package com.example.examproject.BankAccounts;

public class SavingsAccount extends Account{

    public int savings;

    public float amount_of_days_to_insert_money;

    public SavingsAccount() {
        money = 400;
    }


    @Override
    public String toString() {
        return "SAVINGS";
    }
}
