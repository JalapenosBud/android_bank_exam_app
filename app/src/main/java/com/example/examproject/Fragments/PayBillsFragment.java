package com.example.examproject.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.examproject.BankAccounts.Bank;
import com.example.examproject.R;
import com.example.examproject.TransferMoneyBetweenAccounts.Account;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PayBillsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PayBillsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PayBillsFragment extends Fragment {

    Account account_to_pay_from;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PayBillsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PayBillsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PayBillsFragment newInstance(String param1, String param2) {
        PayBillsFragment fragment = new PayBillsFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pay_bills, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        EditText bill_name = (EditText)getView().findViewById(R.id.et_bill_name);
        EditText pay_bill_amount = (EditText)getView().findViewById(R.id.paybills_et_enter_amount);
        Button pay_button = (Button)getView().findViewById(R.id.btn_pay_bill);

        ArrayList<String> money_and_account_list = new ArrayList<>();

        for (Account s : Bank.get_logged_in_customer.accounts)
        {
            money_and_account_list.add(s.getMoneyAndAccountString());
        }


        Spinner choose_acc_spinner = (Spinner)getView().findViewById(R.id.spinner_paybill_from_account);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getView().getContext(), R.layout.support_simple_spinner_dropdown_item,money_and_account_list);

        choose_acc_spinner.setAdapter(adapter);

        choose_acc_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                account_to_pay_from = Bank.get_logged_in_customer.accounts.get(choose_acc_spinner.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bill_name_input = bill_name.getText().toString();
                if(bill_name_input == null || bill_name_input.trim().equals(""))
                {
                    Toast.makeText(getContext(),"enter a bill", Toast.LENGTH_SHORT).show();
                    return;
                }
                String bill_amount = pay_bill_amount.getText().toString();
                if(bill_amount == null || bill_amount.trim().equals(""))
                {
                    Toast.makeText(getContext(),"enter an amount", Toast.LENGTH_SHORT).show();
                    return;
                }


                account_to_pay_from = Bank.get_logged_in_customer.accounts.get(choose_acc_spinner.getSelectedItemPosition());
                //Bank.get_logged_in_customer.
                if(account_to_pay_from.withdraw(Float.parseFloat(pay_bill_amount.getText().toString())))
                {
                    Toast.makeText(getContext(), "PAID " + bill_name.getText().toString() + " remaing: " + account_to_pay_from.money, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "Not enough money", Toast.LENGTH_SHORT).show();
                }


            }
        });
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
