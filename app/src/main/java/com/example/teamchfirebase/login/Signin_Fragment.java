package com.example.teamchfirebase.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.teamchfirebase.R;

public class Signin_Fragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Toast.makeText(this.getActivity(), "hello pos : 1", Toast.LENGTH_LONG).show();
        return inflater.inflate(R.layout.signin_tab_fragment, container, false);
    }
}