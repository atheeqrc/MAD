package com.example.asus.commercialbank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    View view;
    User user_session;
    TextView fullName, firstName, lastName, accountNumber, accountBalance, email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        fullName = view.findViewById(R.id.fullname_profile);
        firstName = view.findViewById(R.id.firstName_profile);
        lastName = view.findViewById(R.id.lastName_profile);
        accountNumber = view.findViewById(R.id.accountNumber_profile);
        accountBalance = view.findViewById(R.id.accountBalance_profile);
        email = view.findViewById(R.id.email_profile);
        user_session = (User) getArguments().getSerializable("user_session");
        return view;
    }

    public void onStart() {
        super.onStart();
        fullName.setText(user_session.getFirstName() + " " + user_session.getLastName());
        firstName.setText(user_session.getFirstName());
        lastName.setText(user_session.getLastName());
        accountNumber.setText(user_session.getAccountNumber() + "");
        accountBalance.setText(user_session.getBalance() + "");
        email.setText(user_session.getEmail());


    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
