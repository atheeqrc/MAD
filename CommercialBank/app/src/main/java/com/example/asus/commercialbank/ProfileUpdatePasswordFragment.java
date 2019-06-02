package com.example.asus.commercialbank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileUpdatePasswordFragment extends Fragment {
    View view;
    User user_session;
    TextView fullName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view =inflater.inflate(R.layout.fragment_profile_update_password,container,false);
         fullName = view.findViewById(R.id.fullname_profile_update_password);
         user_session = (User) getArguments().getSerializable("user_session");
        return view;
    }

    public void onStart(){
        super.onStart();
        fullName.setText(user_session.getFirstName() + " " +user_session.getLastName());

    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
