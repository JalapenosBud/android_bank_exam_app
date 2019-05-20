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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.examproject.BankAccounts.Account;
import com.example.examproject.BankAccounts.AccountType;
import com.example.examproject.BankAccounts.Bank;
import com.example.examproject.BankAccounts.BankFactory;
import com.example.examproject.BankAccounts.BudgetAccount;
import com.example.examproject.BankAccounts.BusinessAccount;
import com.example.examproject.BankAccounts.DefaultAccount;
import com.example.examproject.BankAccounts.PensionAccount;
import com.example.examproject.BankAccounts.SavingsAccount;
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

        //-----------------
       List<Customer> customers = Bank.getCustomers();
        //-----------------
        //Spinner logic
        Spinner spinner_account_1 = (Spinner)getView().findViewById(R.id.spinner_first_account);
        Spinner spinner_account_2 = (Spinner)getView().findViewById(R.id.spinner_second_account);
        Spinner spinner_account_3 = (Spinner)getView().findViewById(R.id.spinner_choose_account);

        ArrayAdapter<String> stringAdapter = new ArrayAdapter<String>(getView().getContext(), R.layout.support_simple_spinner_dropdown_item,objs);
        spinner_account_1.setAdapter(stringAdapter);
        spinner_account_2.setAdapter(stringAdapter);

        ArrayAdapter<Customer> customerAdapter = new ArrayAdapter<Customer>(getView().getContext(), R.layout.support_simple_spinner_dropdown_item,customers);
        spinner_account_3.setAdapter(customerAdapter);

        //------------------

        //--------------
        //button logic
        Button transfer_button = (Button)getView().findViewById(R.id.button_transfer);
        transfer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),
                        "Transfered " + Bank.customers.get(0).money + " from " + spinner_account_1.getSelectedItem() + " to " + spinner_account_2.getSelectedItem()
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
