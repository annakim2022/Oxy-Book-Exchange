package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SellActivity extends AppCompatActivity {
    TextView textView_sell;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        //get intent
        Intent intent = getIntent();

        textView_sell = findViewById(R.id.textView_sell);
        textView_sell.setText(intent.getStringExtra("sell"));
    }
}
