package com.example.examproject.Customer;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.examproject.Accounts.Account;
import com.example.examproject.Accounts.AccountType;
import com.example.examproject.Accounts.BudgetAccount;
import com.example.examproject.Accounts.DefaultAccount;
import com.example.examproject.Bank.Bank;
import com.example.examproject.Bank.BankFactory;

import java.util.ArrayList;
import java.util.List;

public class Customer  implements Parcelable {
    public int age;
    public boolean assigned_to_bank;

    public String first_name;
    public String last_name;
    public String password;
    public String filial;

    public ArrayList<Account> accounts ;

    //use for spinner access
    boolean hasBusinessAccount;
    boolean hasPensionAccount;
    boolean hasSavingsAccount;

    private BankFactory factory = new BankFactory();

    public Customer()
    {

    }

    public Customer(String first_name, String last_name, String password, String filial){
        this.first_name = first_name;
        this.last_name = last_name;
        this.filial = filial;
        this.password = password;

        accounts = new ArrayList<>();
        accounts.add(new DefaultAccount());
        accounts.add(new BudgetAccount());

        accounts.set(0,factory.getAccount(AccountType.DEFAULT));
        accounts.set(1,factory.getAccount(AccountType.BUDGET));
    }

    protected Customer(Parcel in) {
        age = in.readInt();
        assigned_to_bank = in.readByte() != 0;
        first_name = in.readString();
        last_name = in.readString();
        password = in.readString();
        filial = in.readString();
        hasBusinessAccount = in.readByte() != 0;
        hasPensionAccount = in.readByte() != 0;
        hasSavingsAccount = in.readByte() != 0;

        accounts = new ArrayList<>();
        accounts.add(new DefaultAccount());
        accounts.add(new BudgetAccount());

        accounts.set(0,factory.getAccount(AccountType.DEFAULT));
        accounts.set(1,factory.getAccount(AccountType.BUDGET));
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public ArrayList<String> getActiveAccountsAsString() {
        ArrayList<String> tmplist = new ArrayList<>();

        for(Account s : accounts)
        {
            tmplist.add(s.toString());
        }
        return tmplist;
    }
    public static List<String> getCurrentAccountNamesAndMoney()
    {
        ArrayList<String> money_and_account_list = new ArrayList<>();

        for (Account s : Bank.get_logged_in_customer.accounts)
        {
            money_and_account_list.add(s.getMoneyAndAccountString());
        }
        return money_and_account_list;
    }
    //public ArrayList<String> getAccountsToApplyFor

    @Override
    public String toString() {
        return "" + first_name + ", " + last_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeByte((byte) (assigned_to_bank ? 1 : 0));
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(password);
        dest.writeString(filial);
        dest.writeByte((byte) (hasBusinessAccount ? 1 : 0));
        dest.writeByte((byte) (hasPensionAccount ? 1 : 0));
        dest.writeByte((byte) (hasSavingsAccount ? 1 : 0));
        //dest.writeList(accounts);
    }
}
