package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CreateActivity extends AppCompatActivity {
    private TextInputEditText input_isbn, input_title, input_quality, input_price, input_course, input_semester, input_professors, input_authors, input_yearPublished;
    private String ISBN, title, authors, yearPublished, quality, price, course, semester, professors;
    private Button button_autofill, button_create;
    private String api_url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        input_isbn = findViewById(R.id.input_isbn);
        input_title = findViewById(R.id.input_title);
        input_quality = findViewById(R.id.input_quality);
        input_price = findViewById(R.id.input_price);
        input_course = findViewById(R.id.input_course);
        input_semester = findViewById(R.id.input_semester);
        input_authors = findViewById(R.id.input_authors);
        input_yearPublished = findViewById(R.id.input_yearPublished);
        input_professors = findViewById(R.id.input_professors);

        button_autofill = findViewById(R.id.button_autofill);
        button_autofill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ISBN = input_isbn.getText().toString();
                autofill(ISBN);
            }
        });

        button_create = findViewById(R.id.button_create);
        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = input_isbn.getText().toString();
                quality = input_quality.getText().toString();
                price = input_price.getText().toString();
                course = input_course.getText().toString();
                semester = input_semester.getText().toString();
                authors = input_authors.getText().toString();
                yearPublished = input_yearPublished.getText().toString();
                professors = input_professors.getText().toString();
                Log.e("SUCCESS", title+quality+price+course+semester+authors+yearPublished+professors);
            }
        });
    }

    private void autofill(String ISBN) {
        AsyncHttpClient client = new AsyncHttpClient();
        api_url = "https://openlibrary.org/isbn/" + ISBN + ".json";
        Log.e("HERE3", api_url);

        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("HERE", api_url);
                try {
                    JSONObject json = new JSONObject(new String(responseBody));
                    input_title.setText(json.getString("title"));
                    input_authors.setText(json.getString("by_statement"));
                    input_yearPublished.setText(json.getString("publish_date"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }

}
