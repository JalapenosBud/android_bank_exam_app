package com.example.examproject.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examproject.Accounts.PensionAccount;
import com.example.examproject.Bank.Bank;
import com.example.examproject.Bank.BankFactory;
import com.example.examproject.Customer.Customer;
import com.example.examproject.Fragments.RegisterFragment;
import com.example.examproject.Fragments.ResetPasswordFragment;
import com.example.examproject.Interfaces.OnCustomerRegister;
import com.example.examproject.R;

public class LoginActivity extends AppCompatActivity implements RegisterFragment.OnFragmentInteractionListener, ResetPasswordFragment.OnFragmentInteractionListener, OnCustomerRegister {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    RegisterFragment registerFragment = new RegisterFragment();

    ResetPasswordFragment resetPasswordFragment = new ResetPasswordFragment();

    Bank bank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        //--------- BANK --------------
        bank = new Bank();

        Customer peter = new Customer("peter", "hansen","123","Copenhagen");
        Customer lars = new Customer("lars", "dudeson","123","Copenhagen");
        lars.accounts.add(new PensionAccount());

        Bank.add(peter);
        Bank.add(lars);
        Bank.add(new Customer("tjo", "haiti","123","Odense"));
        Bank.add(new Customer("soren", "hansen","123","Odense"));

        //-----------------------

        //---------- VIEWS -------------
        View main_view = (View)findViewById(R.id.front_page_layout);
        Button sign_in = (Button) findViewById(R.id.button_sign_in);
        Button register_button = (Button)findViewById(R.id.button_register_front_page);

        EditText username_input = (EditText)findViewById(R.id.username_input);
        EditText password_input = (EditText)findViewById(R.id.password_input);

        Intent sign_in_intent = new Intent(this, HomeActivity.class);
        View fragment_layout = (View) findViewById(R.id.fragment_register_layout);

        Button btn_reset_password = (Button)findViewById(R.id.btn_reset_pw);

        //-----------------------

        fragmentManager = getSupportFragmentManager();


        //---------- CALLBACKS -------------

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Customer c :
                        Bank.customers) {
                    if(c.first_name.equals(username_input.getText().toString()) && c.password.equals(password_input.getText().toString()))
                    {
                        Bank.get_logged_in_customer = c;
                        startActivity(sign_in_intent);
                        Toast.makeText(getApplicationContext(),"HI " + username_input.getText(), Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Wrong username, try again." + c.first_name + " u: " + username_input.getText(), Toast.LENGTH_SHORT).show();
                    }
                }


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

        btn_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_view.setVisibility(View.INVISIBLE);
                fragmentTransaction = fragmentManager.beginTransaction();

                if(getSupportFragmentManager().findFragmentByTag(resetPasswordFragment.getClass().getName()) != null)
                {
                    fragmentTransaction.remove(resetPasswordFragment);
                }
                else
                {
                    ResetPasswordFragment resetPasswordFragment = new ResetPasswordFragment();
                    fragmentTransaction.add(R.id.fragment_register_layout, resetPasswordFragment, RegisterFragment.class.getName());
                }

                fragment_layout.setVisibility(View.VISIBLE);
                fragmentTransaction.commit();

            }
        });





        if(getIntent().getExtras() != null)
        {
            Intent intent = getIntent();
            Customer cust = intent.getExtras().getParcelable("customer");

            Bank.add(cust);
            System.out.println("ADDED: " + cust);
        }

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
    }
}