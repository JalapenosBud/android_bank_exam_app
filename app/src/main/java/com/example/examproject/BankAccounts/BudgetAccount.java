package com.example.examproject.BankAccounts;

public class BudgetAccount extends Account{
    public float amount_of_days_to_insert_money;

    public BudgetAccount() {
        money = 50;
    }

    @Override
    public String toString() {
        return "BUDGET";
    }


}
