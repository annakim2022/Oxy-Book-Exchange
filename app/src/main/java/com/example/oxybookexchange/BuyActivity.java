package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BuyActivity extends AppCompatActivity {
    TextView textView_buy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        //get intent
        Intent intent = getIntent();

        textView_buy = findViewById(R.id.textView_buy);
        textView_buy.setText(intent.getStringExtra("buy"));
    }
}
