package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NoListingsActivity extends AppCompatActivity {
    String email;
    Button button_add2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nolistings);

        button_add2 = findViewById(R.id.button_add2);
        button_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCreateActivity(v);
            }
        });
    }

    public void launchCreateActivity(View view) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }
}
