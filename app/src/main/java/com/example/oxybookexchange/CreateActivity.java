package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        String email = PreferenceManager.getDefaultSharedPreferences(this).getString("email", "bkim4@oxy.edu");

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

                if (!TextUtils.isEmpty(input_isbn.getText().toString()) && !TextUtils.isEmpty(input_title.getText().toString()) &&
                        !TextUtils.isEmpty(input_quality.getText().toString()) && !TextUtils.isEmpty(input_price.getText().toString()) &&
                        !TextUtils.isEmpty(input_course.getText().toString()) && !TextUtils.isEmpty(input_semester.getText().toString()) &&
                        !TextUtils.isEmpty(input_authors.getText().toString()) && !TextUtils.isEmpty(input_yearPublished.getText().toString()) &&
                        !TextUtils.isEmpty(input_professors.getText().toString())) {

                    title = input_title.getText().toString();
                    quality = input_quality.getText().toString();
                    price = input_price.getText().toString();
                    course = input_course.getText().toString();
                    semester = input_semester.getText().toString();
                    authors = input_authors.getText().toString();
                    yearPublished = input_yearPublished.getText().toString();
                    professors = input_professors.getText().toString();

                    String email = PreferenceManager.getDefaultSharedPreferences(CreateActivity.this).getString("email", "bkim4@oxy.edu");
                    String postUrl = "http://134.69.236.202:3308/newlisting";
                    RequestQueue requestQueue = Volley.newRequestQueue(CreateActivity.this);

                    JSONObject MyData = new JSONObject();
                    try {
                        MyData.put("listingID", "0"); //Add the data you'd like to send to the server.
                        MyData.put("userEmail", email);
                        MyData.put("ISBN", ISBN);
                        MyData.put("title", title);
                        MyData.put("quality", quality);
                        MyData.put("price", price);
                        MyData.put("course", course);
                        MyData.put("semester", semester);
                        MyData.put("yearPublished", yearPublished);
                        MyData.put("authors", authors);
                        MyData.put("professors", professors);

                        Toast.makeText(CreateActivity.this, "Success!", Toast.LENGTH_LONG).show();

                        returnToMenu(email);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, MyData, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                } else {
                    Toast.makeText(CreateActivity.this, "Please fill in all sections.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void autofill(String ISBN) {
        AsyncHttpClient client = new AsyncHttpClient();
        api_url = "https://openlibrary.org/isbn/" + ISBN + ".json";

        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("CREATE", api_url);
                try {
                    JSONObject json = new JSONObject(new String(responseBody));
                    input_title.setText(json.getString("title"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject json = new JSONObject(new String(responseBody));
                    input_authors.setText(json.getString("by_statement"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject json = new JSONObject(new String(responseBody));
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

    private void returnToMenu(String email) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
