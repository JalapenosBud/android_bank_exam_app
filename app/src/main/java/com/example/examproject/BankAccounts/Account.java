package com.example.examproject.BankAccounts;

public abstract class Account {
    public float money;

    public abstract void deposit();
    public abstract void withdraw();

    public abstract void updateAccount(float money);
}
