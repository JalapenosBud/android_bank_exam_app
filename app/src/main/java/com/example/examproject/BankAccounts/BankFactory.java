package com.example.examproject.BankAccounts;

import com.example.examproject.TransferMoneyBetweenAccounts.Account;
import com.example.examproject.TransferMoneyBetweenAccounts.AccountType;
import com.example.examproject.TransferMoneyBetweenAccounts.BudgetAccount;
import com.example.examproject.TransferMoneyBetweenAccounts.BusinessAccount;
import com.example.examproject.TransferMoneyBetweenAccounts.DefaultAccount;
import com.example.examproject.TransferMoneyBetweenAccounts.PensionAccount;
import com.example.examproject.TransferMoneyBetweenAccounts.SavingsAccount;

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
