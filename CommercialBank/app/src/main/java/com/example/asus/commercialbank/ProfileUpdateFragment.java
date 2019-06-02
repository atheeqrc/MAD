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

public class ProfileUpdateFragment extends Fragment {
    View view;
    User user_session;
    TextView fullname, balance, accountNumber;
    EditText firstName, lastName, email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile_update, container, false);
        fullname = view.findViewById(R.id.fullname_profile_update);
        firstName = view.findViewById(R.id.firstName_profile_update);
        lastName = view.findViewById(R.id.lastName_profile_update);
        email = view.findViewById(R.id.email_profile_update);
        balance = view.findViewById(R.id.accountBalance_profile_update);
        accountNumber = view.findViewById(R.id.accountNumber_profile_update);
        user_session = (User) getArguments().getSerializable("user_session");
        return view;
    }

    public void onStart() {
        super.onStart();
        fullname.setText(user_session.getFirstName() + " " + user_session.getLastName());
        firstName.setText(user_session.getFirstName());
        lastName.setText(user_session.getLastName());
        accountNumber.setText(user_session.getAccountNumber() + "");
        balance.setText(user_session.getBalance() + "");
        email.setText(user_session.getEmail());


    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
