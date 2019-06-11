package com.example.examproject.Bank;

import com.example.examproject.Accounts.Account;
import com.example.examproject.Accounts.AccountType;
import com.example.examproject.Customer.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bank {

    private static Customer current_logged_in_customer;

    public static BankFactory bankFactory = new BankFactory();

    public static List<Customer> customers = new ArrayList<>();

    public Bank()
    {

    }

    private static List<Account> applicable_accounts = new ArrayList<>(
            Arrays.asList(bankFactory.getAccount(AccountType.SAVINGS),
                        bankFactory.getAccount(AccountType.PENSION),
                        bankFactory.getAccount(AccountType.BUSINESS)));

    public static Customer get_logged_in_customer;

    public static void set_logged_in_custumer(Customer customer)
    {
        current_logged_in_customer = customer;
    }

    public static void add(Customer customer)
    {
        customers.add(customer);
    }

    public static List<Customer> getCustomers()
    {
        return customers;
    }

    public static List<String> getCurrentCustomerAccounts(){
        ArrayList<String> current_customer_account_list = new ArrayList<>();
        for (Account acc : get_logged_in_customer.accounts)
        {
            current_customer_account_list.add(acc.toString());
        }

        return current_customer_account_list;
    }

    public static List<String> getCurrentAccountNamesAndMoney()
    {
        ArrayList<String> money_and_account_list = new ArrayList<>();

        for (Account s : get_logged_in_customer.accounts)
        {
            money_and_account_list.add(s.getMoneyAndAccountString());
        }
        return money_and_account_list;
    }


    public static ArrayList<String> getApplicableAccounts()
    {
        ArrayList<String> applilist = new ArrayList<>();

        for (Account s : applicable_accounts)
        {
            applilist.add(s.getMoneyAndAccountString());
        }
        return applilist;
    }

}
