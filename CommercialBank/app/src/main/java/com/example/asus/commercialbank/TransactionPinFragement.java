package com.example.asus.commercialbank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TransactionPinFragement extends Fragment {
    View view;
    User user_session;
    TextView username;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_transaction_pin,container,false);
        user_session = (User) getArguments().getSerializable("user_session");
        username = view.findViewById(R.id.username_session_trans);
        return view;
    }

    public void onStart(){
        super.onStart();
        username.setText(user_session.getUsername());

    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
