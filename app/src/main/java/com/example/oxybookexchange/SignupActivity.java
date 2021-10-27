package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    TextView textView_signup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //get intent
        Intent intent = getIntent();

        textView_signup = findViewById(R.id.textView_signup);
        textView_signup.setText(intent.getStringExtra("signup"));
    }
}
