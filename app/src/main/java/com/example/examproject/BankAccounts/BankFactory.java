package com.example.examproject.BankAccounts;

public class BankFactory {

    public Account getAccount(AccountType accountType)
    {
        if(accountType == AccountType.DEFAULT)
        {
            return new DefaultAccount();
        }
        if(accountType == AccountType.BUDGET)
        {
            return new BudgetAccount();
        }
        if(accountType == AccountType.SAVINGS)
        {
            return new SavingsAccount();
        }
        if(accountType == AccountType.BUSINESS)
        {
            return new BusinessAccount();
        }
        if(accountType == AccountType.PENSION)
        {
            return new PensionAccount();
        }
        return null;
    }
}
