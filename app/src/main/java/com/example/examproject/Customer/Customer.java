package com.example.examproject.Customer;

import com.example.examproject.BankAccounts.Account;
import com.example.examproject.BankAccounts.AccountType;
import com.example.examproject.BankAccounts.BankFactory;
import com.example.examproject.BankAccounts.BudgetAccount;
import com.example.examproject.BankAccounts.BusinessAccount;
import com.example.examproject.BankAccounts.DefaultAccount;
import com.example.examproject.BankAccounts.PensionAccount;
import com.example.examproject.BankAccounts.SavingsAccount;

public class Customer {
    public int age;
    public boolean assigned_to_bank;

    public String first_name;
    public String last_name;
    public String password;
    public String filial;

    public Account[] accounts = {new DefaultAccount(), new BudgetAccount(), new SavingsAccount(), new BusinessAccount(),new PensionAccount()};

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

        accounts[0] = factory.getAccount(AccountType.DEFAULT);
        accounts[1] = factory.getAccount(AccountType.BUDGET);
    }

    public void unlockSavingsAccount(){
        accounts[2] = factory.getAccount(AccountType.SAVINGS);
    }

    public void unlockBusinessAccount(){
        accounts[3] = factory.getAccount(AccountType.BUSINESS);
    }

    public void unlockPensionAccount(){
        accounts[4] = factory.getAccount(AccountType.PENSION);
    }

    @Override
    public String toString() {
        return "" + last_name + ", " + first_name;
    }
}
