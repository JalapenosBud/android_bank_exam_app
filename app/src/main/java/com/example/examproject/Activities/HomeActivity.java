package com.example.examproject.Activities;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.examproject.TransferMoneyBetweenAccounts.Account;
import com.example.examproject.BankAccounts.Bank;
import com.example.examproject.TransferMoneyBetweenAccounts.NemIdFragment;
import com.example.examproject.Fragments.PayBillsFragment;
import com.example.examproject.TransferMoneyBetweenAccounts.TransferFragment;
import com.example.examproject.R;

public class HomeActivity extends AppCompatActivity implements TransferFragment.OnFragmentInteractionListener, NemIdFragment.OnFragmentInteractionListener, PayBillsFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TransferFragment transferFragment = new TransferFragment();
    NemIdFragment nemIdFragment = new NemIdFragment();
    PayBillsFragment payBillsFragment = new PayBillsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //VIEWS
        Button btn_home = (Button)findViewById(R.id.button_home);
        Button btn_transactions = (Button)findViewById(R.id.button_transactions);
        Button btn_pay_bills = (Button)findViewById(R.id.button_bills);

        View transfer_money_layout = (View) findViewById(R.id.transfer_money_fragment);
        View paybills_fragment = (View) findViewById(R.id.pay_bills_fragment);
        View sv_main = (View)findViewById(R.id.sv_main);

        fragmentManager = getSupportFragmentManager();

        //classes

        TextView tv_name_as = (TextView) findViewById(R.id.tv_logged_in_name);

        GridView home_grid = (GridView) findViewById(R.id.home_grid_layout);

        ListAdapter listAdapter = new ArrayAdapter<Account>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, Bank.get_logged_in_customer.accounts);


        home_grid.setAdapter(listAdapter);

        tv_name_as.setText(/*"LOGGED IN AS: " + Bank.get_logged_in_customer.toString()*/ Bank.get_logged_in_customer.accounts.get(0).getMoneyAndAccountString());
        //CALLBACKS

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
                fragmentTransaction.addToBackStack(null);
                transfer_money_layout.setVisibility(View.INVISIBLE);
                paybills_fragment.setVisibility(View.INVISIBLE);
                sv_main.setVisibility(View.VISIBLE);
                //only use commit when leaving the activity
                //fragmentTransaction.commit();
            }
        });

        btn_transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                Log.d("asd","hi");
                sv_main.setVisibility(View.INVISIBLE);
                paybills_fragment.setVisibility(View.INVISIBLE);
                if(getSupportFragmentManager().findFragmentByTag(transferFragment.getClass().getName()) != null)
                {
                    fragmentTransaction.remove(transferFragment);
                }
                else
                {
                    TransferFragment myfrag = new TransferFragment();
                    fragmentTransaction.add(R.id.transfer_money_fragment, myfrag, TransferFragment.class.getName());
                }

                transfer_money_layout.setVisibility(View.VISIBLE);
                fragmentTransaction.commit();
            }
        });

        btn_pay_bills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();

                sv_main.setVisibility(View.INVISIBLE);
                transfer_money_layout.setVisibility(View.INVISIBLE);
                if(getSupportFragmentManager().findFragmentByTag(payBillsFragment.getClass().getName()) != null)
                {
                    fragmentTransaction.remove(payBillsFragment);
                }
                else
                {
                    PayBillsFragment payBillsFragment = new PayBillsFragment();
                    fragmentTransaction.add(R.id.pay_bills_fragment, payBillsFragment, PayBillsFragment.class.getName());
                }

                paybills_fragment.setVisibility(View.VISIBLE);
                fragmentTransaction.commit();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d("hi","asd");
    }
}
