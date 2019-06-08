package com.example.examproject.BankAccounts;

import com.example.examproject.TransferMoneyBetweenAccounts.Account;
import com.example.examproject.TransferMoneyBetweenAccounts.Customer;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private static Customer current_logged_in_customer;

    public static BankFactory bankFactory = new BankFactory();

    public static List<Customer> customers = new ArrayList<>();

    public Bank()
    {

    }

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

}
