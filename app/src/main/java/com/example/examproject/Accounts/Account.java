package com.example.examproject.Accounts;

public abstract class Account {
    public float money;

    public void deposit(float money) {
        this.money += money;
    }
    public boolean withdraw(float money){

        if(money > this.money)
        {
            return false;
        }
        else
        {
            this.money -= money;
            return true;
        }

    }


    public String getMoneyAndAccountString()
    {
        return "" + this.getClass().getSimpleName() + "\t" + money;
    }
}
