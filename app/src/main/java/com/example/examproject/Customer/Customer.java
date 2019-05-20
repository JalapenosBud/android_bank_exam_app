package com.example.examproject.Customer;

import com.example.examproject.BankAccounts.Account;
import com.example.examproject.BankAccounts.AccountType;
import com.example.examproject.BankAccounts.BankFactory;

public class Customer {
    public int age;
    public boolean assigned_to_bank;
    public int money = 500;

    public String first_name;
    public String last_name;
    public String password;
    public String filial;

    public Account defaultAccount;
    public Account budgetAccount;
    public Account pensionAccount;
    public Account savingsAccount;
    public Account businessAccount;

    //use for spinner access
    boolean hasBusinessAccount;
    boolean hasPensionAccount;
    boolean hasSavingsAccount;

    private BankFactory factory = new BankFactory();

    public Customer() {
    }

    public Customer(String first_name, String last_name, String password, String filial){
        this.first_name = first_name;
        this.last_name = last_name;
        this.filial = filial;
        this.password = password;

        defaultAccount = factory.getAccount(AccountType.DEFAULT);
        budgetAccount = factory.getAccount(AccountType.BUDGET);
    }

    public void unlockSavingsAccount(){
        savingsAccount = factory.getAccount(AccountType.SAVINGS);
    }

    public void unlockBusinessAccount(){
        businessAccount = factory.getAccount(AccountType.BUSINESS);
    }

    public void unlockPensionAccount(){
        pensionAccount = factory.getAccount(AccountType.PENSION);
    }

    @Override
    public String toString() {
        return "" + last_name + ", " + first_name;
    }
}
