package com.example.examproject.TransferMoneyBetweenAccounts;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import com.example.examproject.BankAccounts.Bank;
import com.example.examproject.Interfaces.OnCustomerSelected;
import com.example.examproject.R;

import java.util.ArrayList;


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

    View nem_id_fragment;
    View normal_layout;
    View paybillsfrag;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    NemIdFragment myfrag;

    Account money_from_account;
    Account money_to_account;

    Customer current_customer;
    Customer chosen_customer = new Customer();

    ArrayList<String> chosen_customer_list = new ArrayList<>();

    public static boolean hasValidated = false;
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

        //TEXTVIEWS
        TextView cur_amount_from = (TextView)getView().findViewById(R.id.tv_cur_amount_from);
        TextView cur_amount_to = (TextView)getView().findViewById(R.id.tv_cur_amount_to);
        TextView tv_from_person = (TextView)getView().findViewById(R.id.tv_person_from);

        //EDITTEXT
        EditText input_field = (EditText)getView().findViewById(R.id.input_amount);

        //SPINNERS
        Spinner spinner_person = (Spinner)getView().findViewById(R.id.spinner_person_transfer_to);
        Spinner spinner_from_account = (Spinner)getView().findViewById(R.id.spinner_transfer_from_account);
        Spinner spinner_to_account = (Spinner)getView().findViewById(R.id.spinner_transfer_to_account);

        //ADAPTERS
        ArrayAdapter<Customer> customerAdapter = new ArrayAdapter<Customer>(getView().getContext(), R.layout.support_simple_spinner_dropdown_item,Bank.getCustomers());
        ArrayAdapter<String> logged_in_adapter = new ArrayAdapter<String>(getView().getContext(), R.layout.support_simple_spinner_dropdown_item,Bank.getCurrentCustomerAccounts());

        //BUTTONS
        Button transfer_button = (Button)getView().findViewById(R.id.button_ok);

        //FRAGMENT
        nem_id_fragment = (View)getView().findViewById(R.id.nem_id_fragment);
        paybillsfrag = (View)getView().findViewById(R.id.pay_bills_fragment);

        //LAYOUT
        normal_layout = (View)getView().findViewById(R.id.normal_layout);

        //INIT LOGIC
        tv_from_person.setText("" + Bank.get_logged_in_customer);
        spinner_person.setAdapter(customerAdapter);
        spinner_from_account.setAdapter(logged_in_adapter);
        myfrag = new NemIdFragment();
        fragmentManager = getFragmentManager();

        //CALLBACKS
        spinner_person.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //chosen customer adapter

                chosen_customer = (Customer)parent.getSelectedItem();
                chosen_customer_list = chosen_customer.getActiveAccountsAsString();

                ArrayAdapter<String> chosen_customer_adapter = new ArrayAdapter<String>(getView().getContext(), R.layout.support_simple_spinner_dropdown_item,chosen_customer_list);
                spinner_to_account.setAdapter(chosen_customer_adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_from_account.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TODO: test if changing to singleto breaks anything
                current_customer = Bank.get_logged_in_customer;
                money_from_account = current_customer.accounts.get(spinner_from_account.getSelectedItemPosition());
                cur_amount_from.setText("" + money_from_account.money);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_to_account.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                current_customer = customerAdapter.getItem(position);
                money_to_account = chosen_customer.accounts.get(spinner_to_account.getSelectedItemPosition());
                cur_amount_to.setText("" + money_to_account.money);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        transfer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = input_field.getText().toString();
                if(input == null || input.trim().equals(""))
                {
                    Toast.makeText(getContext(),"enter an amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(spinner_to_account.getSelectedItem().equals("PENSION"))
                {
                    fragmentTransaction = fragmentManager.beginTransaction();
                    normal_layout.setVisibility(View.INVISIBLE);

                    if( getFragmentManager().findFragmentByTag(myfrag.getClass().getName()) != null)
                    {
                        fragmentTransaction.remove(myfrag);
                    }
                    else
                    {
                        fragmentTransaction = getChildFragmentManager().beginTransaction();
                    }

                    nem_id_fragment.setVisibility(View.VISIBLE);
                    fragmentTransaction.replace(R.id.nem_id_fragment, myfrag).commit();
                }

                if(!money_from_account.withdraw(Float.parseFloat(input_field.getText().toString())))
                {
                    Toast.makeText(getContext(), "Not enough money left on the account",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(),
                            spinner_person.getSelectedItem() +  " transfered " + input_field.getText().toString() + " from " + spinner_from_account.getSelectedItem() + " to " + spinner_to_account.getSelectedItem()
                            , Toast.LENGTH_SHORT).show();
                    money_to_account.deposit(Float.parseFloat(input_field.getText().toString()));

                }
                cur_amount_from.setText("" + money_from_account.money);
                cur_amount_to.setText("" + money_to_account.money);

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
        System.out.println("attached");
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
        System.out.println("detached");
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
