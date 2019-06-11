package com.example.examproject.Fragments;

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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.examproject.Bank.Bank;
import com.example.examproject.Bank.BankFactory;
import com.example.examproject.R;
import com.example.examproject.Accounts.AccountType;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ApplyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ApplyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ApplyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<String> tmp_accounts = new ArrayList<>();
    private OnFragmentInteractionListener mListener;

    public ApplyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ApplyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ApplyFragment newInstance(String param1, String param2) {
        ApplyFragment fragment = new ApplyFragment();
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
        return inflater.inflate(R.layout.fragment_apply, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BankFactory factory = new BankFactory();

        Spinner spinner_apply = (Spinner)getView().findViewById(R.id.spinner_account_apply);
        SpinnerAdapter listAdapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, Bank.getApplicableAccounts());

        View apply_view = (View)getView().findViewById(R.id.apply_for_acc_layout) ;
        spinner_apply.setAdapter(listAdapter);

        Button btn_submit = (Button)getView().findViewById(R.id.btn_apply_submit);

        spinner_apply.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //tmp_accounts = Bank.getApplicableAccounts();
                //tmp_account = Bank.accounts.get(spinner_to_account.getSelectedItemPosition());
                //tmp_account = (Account)spinner_apply.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!Bank.get_logged_in_customer.accounts.contains(factory.getAccount(AccountType.SAVINGS)))
                {
                    Bank.get_logged_in_customer.accounts.add(factory.getAccount(AccountType.SAVINGS));
                }

                if(!Bank.get_logged_in_customer.accounts.contains(factory.getAccount(AccountType.BUSINESS)))
                {
                    Bank.get_logged_in_customer.accounts.add(factory.getAccount(AccountType.BUSINESS));
                }
                if(!Bank.get_logged_in_customer.accounts.contains(factory.getAccount(AccountType.PENSION)))
                {
                    Bank.get_logged_in_customer.accounts.add(factory.getAccount(AccountType.PENSION));
                }

                System.out.println(Bank.get_logged_in_customer.getCurrentAccountNamesAndMoney());
                getActivity().findViewById(R.id.apply_for_acc_layout).setVisibility(View.INVISIBLE);
                getActivity().findViewById(R.id.sv_main).setVisibility(View.VISIBLE);
            }
        });
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
