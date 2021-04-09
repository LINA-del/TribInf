package com.example.teamchfirebase.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.teamchfirebase.MainActivity;
import com.example.teamchfirebase.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConnectionActivity extends AppCompatActivity {
    private static final String TAG = "Info";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton fb1, fb2, fb3;
    private Button signup, login;
    private GoogleSignInClient mGoogleSignInClient;
    private DatabaseReference mDatabase;
    private static final int RC_SIGN_IN = 10;
    private FirebaseAuth mAuth;
    private EditText name;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        mAuth = FirebaseAuth.getInstance();
        createRequest();
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        fb1 = findViewById(R.id.fb1);
        fb2 = findViewById(R.id.fb2);
        fb3 = findViewById(R.id.fb3);
        signup = findViewById(R.id.buttonSignUp);
        login = findViewById(R.id.buttonLogIn);
        name = findViewById(R.id.Nom);
        email = findViewById(R.id.Email2);
        password = findViewById(R.id.Pass2);
        fb3.setOnClickListener(this::fireBaseAuthGoogle);

        //mise en place des tabs de log in et de sign in
        tabLayout.addTab(tabLayout.newTab().setText("LOGIN"));
        tabLayout.addTab(tabLayout.newTab().setText("SIGNUP"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        Adapter adapter = new Adapter(getSupportFragmentManager(), this, tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //quelques animations
        fb1.setTranslationY(300);
        fb1.setTranslationY(300);
        fb1.setTranslationY(300);
        float v = 0;
        fb1.setAlpha(v);
        fb2.setAlpha(v);
        fb3.setAlpha(v);
        tabLayout.setAlpha(v);
        fb1.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        fb2.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        fb3.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();

    }

    public void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // mAuth.signOut();
        // Toast.makeText(this, "Name : " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser account) {

        // le client est deja connecté
        if (account != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
        // Toast.makeText(this, "No login", Toast.LENGTH_SHORT).show();
    }

    public void fireBaseAuthGoogle(View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void customSignUp(View view) {
        //  Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        //  startActivityForResult(signInIntent, RC_SIGN_IN);
        // Toast.makeText(this, "No login", Toast.LENGTH_SHORT).show();
        //writeNewUser("", name.getText().toString(), email.getText().toString(), password.getText().toString());
        String test = name.getText().toString();
        Toast.makeText(this, "" + test, Toast.LENGTH_SHORT).show();
    }

    public void customLogIn(View view) {
        //  Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        //  startActivityForResult(signInIntent, RC_SIGN_IN);
        // Toast.makeText(this, "No login", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);

            }

        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        // boolean newUser = task.getResult().getAdditionalUserInfo().isNewUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        updateUI(null);
                    }
                });
    }

    public void writeNewUser(String userId, String name, String email, String password) {
        User user = new User(name, email, password);

        //  mDatabase.child("users").child(userId).setValue(user);
    }


}
