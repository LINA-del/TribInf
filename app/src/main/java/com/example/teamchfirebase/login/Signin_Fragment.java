package com.example.teamchfirebase.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.teamchfirebase.R;

import static com.example.teamchfirebase.login.ConnectionActivity.writeNewUser;

public class Signin_Fragment extends Fragment {
    private Button signup;
    private EditText name;
    private EditText email;
    private EditText password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Toast.makeText(this.getActivity(), "hello pos : 1", Toast.LENGTH_LONG).show();
        View view = inflater.inflate(R.layout.signin_tab_fragment, container, false);
        signup = view.findViewById(R.id.buttonSignUp);
        name = view.findViewById(R.id.Nom);
        email = view.findViewById(R.id.Email2);
        password = view.findViewById(R.id.Pass2);
        signup.setOnClickListener(this::customSignUp);


        return view;
    }

    public void customSignUp(View view) {
        //  Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        //  startActivityForResult(signInIntent, RC_SIGN_IN);
        writeNewUser("", name.getText().toString().trim(), email.getText().toString().trim(), password.getText().toString().trim());

    }


}