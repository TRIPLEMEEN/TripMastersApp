package com.example.tripify.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripify.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        mAuth = FirebaseAuth.getInstance();

        handler.postDelayed(() -> {
            Intent intent;
            if (mAuth.getCurrentUser() != null){
                intent = new Intent(SplashScreen.this, MainActivity.class);
            }else{
                intent = new Intent(SplashScreen.this, RegisterActivity.class);
            }
            startActivity(intent);
            finish();
        }, 3000);

    }
}