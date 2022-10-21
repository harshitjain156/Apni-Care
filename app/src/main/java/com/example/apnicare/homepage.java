package com.example.apnicare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class homepage extends Fragment {
    Button upbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_homepage, container, false);

        upbtn=view.findViewById(R.id.homeupload);
        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(homepage.this)
                        .navigate(R.id.action_homepage_to_upload_prescription);
            }
        });
        return view;
    }
}