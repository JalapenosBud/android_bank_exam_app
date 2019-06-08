package com.example.examproject.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examproject.Models.CustomerModel;
import com.example.examproject.TransferMoneyBetweenAccounts.Customer;
import com.example.examproject.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);

        final CustomerModel viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(CustomerModel.class);
        viewModel.userLiveData.observe(this, new Observer<Customer>() {
            @Override
            public void onChanged(@Nullable Customer customer) {

            }
        });

        Button register_button = (Button)findViewById(R.id.button3);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.doAction();
            }
        });

    }
}
