package com.example.examproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examproject.BankAccounts.Account;
import com.example.examproject.BankAccounts.AccountType;
import com.example.examproject.BankAccounts.Bank;
import com.example.examproject.Customer.Customer;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TransferFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TransferFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransferFragment extends Fragment implements OnCustomerSelected {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private OnEntrySelectedListener entrySelectedListener;

    Account[] accounts = {
            Bank.bankFactory.getAccount(AccountType.DEFAULT),
            Bank.bankFactory.getAccount(AccountType.BUDGET),
            Bank.bankFactory.getAccount(AccountType.SAVINGS),
            Bank.bankFactory.getAccount(AccountType.BUSINESS),
            Bank.bankFactory.getAccount(AccountType.PENSION)
    };

    Account to_account;
    Customer current_customer;


    @Override
    public void onCustomerSelected(Customer customer) {

    }

    public interface OnEntrySelectedListener{
        public void onEntrySelected(String string);
    }

    public TransferFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransferFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransferFragment newInstance(String param1, String param2) {
        TransferFragment fragment = new TransferFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //TODO: Associate bank accounts with each new Customer that gets registered to the bank

        String[] objs = {
                accounts[0].toString(),
                accounts[1].toString(),
                accounts[2].toString(),
                accounts[3].toString(),
                accounts[4].toString()
        };

        EditText input_field = (EditText)getView().findViewById(R.id.input_amount);
        TextView cur_amount_from = (TextView)getView().findViewById(R.id.tv_cur_amount_from);
        TextView cur_amount_to = (TextView)getView().findViewById(R.id.tv_cur_amount_to);

        //-----------------
        List<Customer> customers = Bank.getCustomers();
        //-----------------
        //Spinner logic
        Spinner spinner_person = (Spinner)getView().findViewById(R.id.person_spinner);
        Spinner spinner_from_account = (Spinner)getView().findViewById(R.id.spinner_from_account);
        Spinner spinner_to_account = (Spinner)getView().findViewById(R.id.spinner_to_account);

        ArrayAdapter<Customer> customerAdapter = new ArrayAdapter<Customer>(getView().getContext(), R.layout.support_simple_spinner_dropdown_item,customers);
        spinner_person.setAdapter(customerAdapter);

        ArrayAdapter<String> stringAdapter = new ArrayAdapter<String>(getView().getContext(), R.layout.support_simple_spinner_dropdown_item,objs);
        spinner_from_account.setAdapter(stringAdapter);
        spinner_to_account.setAdapter(stringAdapter);



        spinner_from_account.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                //vis de penge i text view som der er på den tilsvarende account man trykker på

                current_customer = customerAdapter.getItem(position);

                //System.out.println(to_account.money);
                cur_amount_from.setText("" + current_customer.accounts[spinner_from_account.getSelectedItemPosition()].money);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_to_account.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                current_customer = customerAdapter.getItem(position);
                to_account = current_customer.accounts[spinner_to_account.getSelectedItemPosition()];
                cur_amount_to.setText("" + current_customer.accounts[spinner_to_account.getSelectedItemPosition()].money);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //------------------

        //--------------
        //button logic
        Button transfer_button = (Button)getView().findViewById(R.id.button_ok);
        transfer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                to_account.updateAccount(Float.parseFloat(input_field.getText().toString()));
                cur_amount_from.setText("" + current_customer.accounts[spinner_from_account.getSelectedItemPosition()].money);
                cur_amount_to.setText("" + current_customer.accounts[spinner_to_account.getSelectedItemPosition()].money);
                Toast.makeText(getContext(),
                        spinner_person.getSelectedItem() +  " transfered " + input_field.getText().toString() + " from " + spinner_from_account.getSelectedItem() + " to " + spinner_to_account.getSelectedItem()
                        , Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transfer, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
