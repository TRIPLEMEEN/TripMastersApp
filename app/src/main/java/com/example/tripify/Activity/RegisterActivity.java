package com.example.tripify.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripify.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    // --Commented out by Inspection (20/04/2023, 16:49):TextView btn;
    EditText input_email, input_username, input_password, input_confirm_password;
    Button register_btn;
    final String email_pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]+";
    ProgressDialog mLoadingBar;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    TextView textView;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        textView = findViewById(R.id.got_account_sign_in);

        input_email = findViewById(R.id.input_email);
        input_username = findViewById(R.id.input_username);
        input_confirm_password = findViewById(R.id.input_confirm_password);
        input_password = findViewById(R.id.input_password);
        register_btn = findViewById(R.id.register_btn);

        textView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        mLoadingBar = new ProgressDialog(RegisterActivity.this);
        mAuth = FirebaseAuth.getInstance();



        //register_btn.setOnClickListener(view -> checkCredentials());
        //btn.setOnClickListener((v) -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }

    private void checkCredentials() {
        String username = input_username.getText().toString();
        String email = input_email.getText().toString();
        String password = input_password.getText().toString();
        String confirm_password = input_confirm_password.getText().toString();

        if (username.isEmpty() || username.length() < 7)
        {
            input_username.setError("Invalid Username");
        }
        else if (email.isEmpty() || !email.matches(email_pattern)) {
            input_email.setError("Enter Correct Email Address");
        }
        else if (password.isEmpty() || password.length() < 6)
        {
            input_password.setError("Enter proper password");
        }
        else if (confirm_password.isEmpty() || !password.equals(confirm_password))
        {
            input_confirm_password.setError("Passwords do not match!");
        }
        else
        {
            mLoadingBar.setMessage("Please be patient while we register you...");
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            register_btn.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            });

            buttonReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressBar.setVisibility(View.VISIBLE);
                    String email, password;
                    email = String.valueOf(editTextEmail.getText());
                    password = String.valueOf(editTextPassword.getText());

                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(Register.this, "Enter email", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                        return;
                    }


            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {

                        Toast.makeText(RegisterActivity.this, "Account created.",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();

                    }
                });
        }
    }
}


