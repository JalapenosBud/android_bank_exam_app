package com.example.examproject;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.examproject.BankAccounts.Bank;
import com.example.examproject.Customer.Customer;

public class HomeActivity extends AppCompatActivity implements TransferFragment.OnFragmentInteractionListener, NemIdFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    TransferFragment myfrag = new TransferFragment();
    NemIdFragment nemIdFragment = new NemIdFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //VIEWS
        Button btn_home = (Button)findViewById(R.id.button_home);
        ImageButton btn_transactions = (ImageButton)findViewById(R.id.button_transactions);
        View fragment_layout = (View) findViewById(R.id.fragment_layout);
        View sv_main = (View)findViewById(R.id.sv_main);

        fragmentManager = getSupportFragmentManager();

        //classes



        //CALLBACKS

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack();
                fragmentTransaction.addToBackStack(null);
                fragment_layout.setVisibility(View.INVISIBLE);

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

                if(getSupportFragmentManager().findFragmentByTag(myfrag.getClass().getName()) != null)
                {
                    fragmentTransaction.remove(myfrag);
                }
                else
                {
                    TransferFragment myfrag = new TransferFragment();
                    fragmentTransaction.add(R.id.fragment_layout, myfrag, TransferFragment.class.getName());
                }

                fragment_layout.setVisibility(View.VISIBLE);
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
