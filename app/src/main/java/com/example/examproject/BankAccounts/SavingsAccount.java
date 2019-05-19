package com.example.examproject.BankAccounts;

public class SavingsAccount implements Account{

    public int savings;

    public float amount_of_days_to_insert_money;

    @Override
    public void deposit() {

    }

    @Override
    public void withdraw() {

    }

    @Override
    public String toString() {
        return "Savings";
    }
}
