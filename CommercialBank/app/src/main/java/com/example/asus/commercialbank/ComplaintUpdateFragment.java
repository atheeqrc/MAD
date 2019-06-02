package com.example.asus.commercialbank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ComplaintUpdateFragment extends Fragment {
    View view;
    User user_session;
    TextView Username;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_complaint_update,container,false);
        Username = view.findViewById(R.id.username_session_complaint);
        user_session = (User) getArguments().getSerializable("user_session");
        return view;
    }

    public void onStart(){
        super.onStart();
        Username.setText(user_session.getUsername()+"");


    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
