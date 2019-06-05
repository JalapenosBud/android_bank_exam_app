package com.example.examproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examproject.Customer.Customer;
import com.example.examproject.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);

        Button register_button = (Button)findViewById(R.id.button3);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText first_name = (EditText)findViewById(R.id.firstnameinput);
                EditText last_name  = (EditText)findViewById(R.id.lastnameinput);
                EditText password =   (EditText)findViewById(R.id.passwordinput);
                Customer temp_customer = new Customer(first_name.getText().toString(),
                        last_name.getText().toString(),
                        password.getText().toString(),
                        "");

                Intent intent = new Intent(getApplicationContext(), FrontPageActivity.class);
                intent.putExtra("customer", temp_customer);

                startActivity(intent);

                //temp_customer.


                //customer_listener.CustomerRegister(temp_customer);

                //FragmentTransaction ft = getFragmentManager().beginTransaction();
                //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                //getActivity().findViewById(R.id.fragment_register_layout).setVisibility(View.INVISIBLE);
                //getActivity().findViewById(R.id.front_page_layout).setVisibility(View.VISIBLE);

                Toast.makeText(getApplicationContext(),"Registered " + first_name + " " + last_name, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
