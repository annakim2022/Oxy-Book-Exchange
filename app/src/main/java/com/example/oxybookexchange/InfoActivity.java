package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    TextView textView_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //get intent
        Intent intent = getIntent();

        textView_info = findViewById(R.id.textView_info);
        textView_info.setText(intent.getStringExtra("info"));

    }
}
