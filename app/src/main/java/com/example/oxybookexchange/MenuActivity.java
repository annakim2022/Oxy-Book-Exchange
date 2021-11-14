package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    Button button_buy;
    Button button_sell;
    Button button_chat;
    Button button_account;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

       // GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        //set buttons
        button_buy = findViewById(R.id.button_buy);
        button_sell = findViewById(R.id.button_sell);
        button_account = findViewById(R.id.button_account);

        button_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchBuyActivity(v);
            }
        });

        button_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message2 = "let me sell";
                launchSellActivity(v, message2);
            }
        });

        button_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAccountActivity(v);
            }
        });

    }

    public void launchBuyActivity(View view) {
        Intent intent = new Intent(this, ListingsActivity.class);
        startActivity(intent);
    }
    public void launchSellActivity(View view, String s) {
        Intent intent = new Intent(this, MyListingsActivity.class);
        intent.putExtra("sell", s);
        startActivity(intent);
    }
    public void launchAccountActivity(View view) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }
}
