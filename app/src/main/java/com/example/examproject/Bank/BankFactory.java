package com.example.examproject.Bank;

import com.example.examproject.Accounts.Account;
import com.example.examproject.Accounts.AccountType;
import com.example.examproject.Accounts.BudgetAccount;
import com.example.examproject.Accounts.BusinessAccount;
import com.example.examproject.Accounts.DefaultAccount;
import com.example.examproject.Accounts.PensionAccount;
import com.example.examproject.Accounts.SavingsAccount;

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
