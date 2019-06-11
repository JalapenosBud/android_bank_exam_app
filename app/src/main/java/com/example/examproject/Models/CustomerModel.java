package com.example.examproject.Models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.examproject.Customer.Customer;

public class CustomerModel extends ViewModel {
    public final MutableLiveData<Customer> userLiveData = new MutableLiveData<>();

    public LiveData<Customer> getCustomer() {
        return userLiveData;
    }

    public CustomerModel() {
        // trigger user load.
    }

    public void doAction() {
        // depending on the action, do necessary business logic calls and update the
        // userLiveData.

/*
        EditText first_name = (EditText)findViewById(R.id.firstnameinput);
        EditText last_name  = (EditText)findViewById(R.id.lastnameinput);
        EditText password =   (EditText)findViewById(R.id.passwordinput);

        Customer temp_customer = new Customer(first_name.getText().toString(),
                last_name.getText().toString(),
                password.getText().toString(),
                "");

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("customer", temp_customer);

        startActivity(intent);*/

        //temp_customer.


        //customer_listener.CustomerRegister(temp_customer);

        //FragmentTransaction ft = getFragmentManager().beginTransaction();
        //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        //getActivity().findViewById(R.id.fragment_register_layout).setVisibility(View.INVISIBLE);
        //getActivity().findViewById(R.id.front_page_layout).setVisibility(View.VISIBLE);


    }
}
