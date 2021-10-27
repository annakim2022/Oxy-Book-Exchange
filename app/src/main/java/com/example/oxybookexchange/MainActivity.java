package com.example.oxybookexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //variables
    Button button_login;
    Button button_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set buttons
        button_login =  findViewById(R.id.button_login);
        button_signup = findViewById(R.id.button_signup);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "log me in";
                launchLoginActivity(v, message);
            }
        });

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message2 = "sign me up";
                launchSignupActivity(v, message2);
            }
        });

    }

    public void launchLoginActivity(View view, String s) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("login", s);
        startActivity(intent);
    }
    public void launchSignupActivity(View view, String s) {
        Intent intent = new Intent(this, SignupActivity.class);
        intent.putExtra("signup", s);
        startActivity(intent);
    }
}