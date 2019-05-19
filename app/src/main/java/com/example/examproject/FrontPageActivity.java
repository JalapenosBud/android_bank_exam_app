package com.example.examproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examproject.BankAccounts.Bank;
import com.example.examproject.Customer.Customer;

public class FrontPageActivity extends AppCompatActivity implements RegisterFragment.OnFragmentInteractionListener, OnCustomerRegister {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    RegisterFragment registerFragment = new RegisterFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_page);

        View main_view = (View)findViewById(R.id.front_page_layout);
        Button sign_in = (Button) findViewById(R.id.button_sign_in);
        Button register_button = (Button)findViewById(R.id.button_register_front_page);

        EditText username_input = (EditText)findViewById(R.id.username_input);
        EditText password_input = (EditText)findViewById(R.id.password_input);

        Intent sign_in_intent = new Intent(this, HomeActivity.class);

        View fragment_layout = (View) findViewById(R.id.fragment_register_layout);
        fragmentManager = getSupportFragmentManager();

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                for (Customer c :
                        Bank.customers) {
                    if(username_input.getText().toString().equals(c.first_name))
                    {
                        startActivity(sign_in_intent);
                    }
                    else
                    {

                        Toast.makeText(getApplicationContext(),"Wrong username, try again." + c.first_name + " u: " + username_input.getText(), Toast.LENGTH_SHORT).show();
                    }
                }

                //startactivity starts a new activity
*/

            startActivity(sign_in_intent);

            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //first call this when returning away from the fragment
                //fragmentTransaction.addToBackStack(null);
                main_view.setVisibility(View.INVISIBLE);
                fragmentTransaction = fragmentManager.beginTransaction();

                if(getSupportFragmentManager().findFragmentByTag(registerFragment.getClass().getName()) != null)
                {
                    fragmentTransaction.remove(registerFragment);
                }
                else
                {
                    RegisterFragment registerFragment = new RegisterFragment();
                    fragmentTransaction.add(R.id.fragment_register_layout, registerFragment, RegisterFragment.class.getName());
                }

                fragment_layout.setVisibility(View.VISIBLE);
                fragmentTransaction.commit();

            }
        });

    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 0)
        {
            this.finish();
        }
        else
        {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void CustomerRegister(Customer customer) {
        Bank.add(customer);
        Log.d("customer", "Customer created with: " + customer.first_name+ ", " +
                customer.last_name + ", " + customer.password + ", total customers in bank: " + Bank.customers.size());
    }
}