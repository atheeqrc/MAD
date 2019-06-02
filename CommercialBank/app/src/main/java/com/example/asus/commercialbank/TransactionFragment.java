package com.example.asus.commercialbank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TransactionFragment extends Fragment {
    TextView Accno,Date;
    EditText Amount;
    View view ;
    User user_session;
    java.util.Date date;
    String date_text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_transaction,container,false);
        Accno =  view.findViewById(R.id.accno_session_trans);
        Date = view.findViewById(R.id.date_trans);
        date =  Calendar.getInstance().getTime();
        Amount = view.findViewById(R.id.amount_trans);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        date_text =df.format(date);


         user_session = (User) getArguments().getSerializable("user_session");
        return view;
    }
    public void onStart(){
        super.onStart();

        Accno.setText(user_session.getAccountNumber()+"");
        Amount.setText(0+"");
        Date.setText(""+date_text);
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
