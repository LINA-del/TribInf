package com.example.teamchfirebase.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.teamchfirebase.R;

import static com.example.teamchfirebase.login.ConnectionActivity.checkUser;

public class Login_Fragment extends Fragment {

    private Button login;
    private EditText email;
    private EditText password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Toast.makeText(this.getActivity(), "hello pos : 0", Toast.LENGTH_LONG).show();
        View view = inflater.inflate(R.layout.login_tab_fragment, container, false);
        login = view.findViewById(R.id.buttonLogIn);
        email = view.findViewById(R.id.Email);
        password = view.findViewById(R.id.Pass);
        login.setOnClickListener(this::customLogIn);
        return view;
    }

    public void customLogIn(View view) {
        //  Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        //  startActivityForResult(signInIntent, RC_SIGN_IN);
        // Toast.makeText(this, "No login", Toast.LENGTH_SHORT).show();
        checkUser(email.getText().toString().trim(), password.getText().toString().trim());
    }

}