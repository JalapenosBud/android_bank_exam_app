package com.example.examproject.BankAccounts;

public class BudgetAccount extends Account{
    public float amount_of_days_to_insert_money;

    public BudgetAccount() {
        money = 50;
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
        return "BUDGET";
    }


}
