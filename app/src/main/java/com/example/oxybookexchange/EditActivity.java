package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class EditActivity extends AppCompatActivity {
    private TextInputEditText input_isbn, input_title, input_quality, input_price, input_course, input_semester, input_professors, input_authors, input_yearPublished;
    private String ISBN, title, authors, yearPublished, quality, price, course, semester, professors;
    private Button button_update;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

        input_isbn = findViewById(R.id.input_isbn2);
        input_title = findViewById(R.id.input_title2);
        input_quality = findViewById(R.id.input_quality2);
        input_price = findViewById(R.id.input_price2);
        input_course = findViewById(R.id.input_course2);
        input_semester = findViewById(R.id.input_semester2);
        input_authors = findViewById(R.id.input_authors2);
        input_yearPublished = findViewById(R.id.input_yearPublished2);
        input_professors = findViewById(R.id.input_professors2);

        // get text from adapter
        Intent intent = getIntent();
        String ISBN2 = intent.getStringExtra("ISBN");
        String title2 = intent.getStringExtra("title");
        String quality2 =  intent.getStringExtra("quality");
        String price2 = intent.getStringExtra("price");
        String course2 = intent.getStringExtra("course");
        String semester2 = intent.getStringExtra("semester");
        String authors2 = intent.getStringExtra("authors");
        String professors2 = intent.getStringExtra("professors");
        String yearPublished2 = intent.getStringExtra("yearPublished");
        String email = intent.getStringExtra("email");
        String listingID = intent.getStringExtra("listingID");

        // set input text to previous info
        input_isbn.setText(ISBN2);
        input_title.setText(title2);
        input_quality.setText(quality2);
        input_price.setText(price2);
        input_course.setText(course2);
        input_semester.setText(semester2);
        input_authors.setText(authors2);
        input_professors.setText(professors2);
        input_yearPublished.setText(yearPublished2);

        button_update = findViewById(R.id.button_update);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(input_isbn.getText().toString()) && !TextUtils.isEmpty(input_title.getText().toString()) &&
                        !TextUtils.isEmpty(input_quality.getText().toString()) && !TextUtils.isEmpty(input_price.getText().toString()) &&
                        !TextUtils.isEmpty(input_course.getText().toString()) && !TextUtils.isEmpty(input_semester.getText().toString()) &&
                        !TextUtils.isEmpty(input_authors.getText().toString()) && !TextUtils.isEmpty(input_yearPublished.getText().toString()) &&
                        !TextUtils.isEmpty(input_professors.getText().toString())) {

                    ISBN = input_isbn.getText().toString();
                    title = input_title.getText().toString();
                    quality = input_quality.getText().toString();
                    price = input_price.getText().toString();
                    course = input_course.getText().toString();
                    semester = input_semester.getText().toString();
                    authors = input_authors.getText().toString();
                    yearPublished = input_yearPublished.getText().toString();
                    professors = input_professors.getText().toString();

                    String url = "http://134.69.236.202:3308/updatelisting";
                    StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //This code is executed if the server responds, whether or not the response contains data.
                            //The String 'response' contains the server's response.
                        }
                    }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //This code is executed if there is an error.
                        }
                    }) {
                        protected Map<String, String> getParams() {
                            Map<String, String> MyData = new HashMap<String, String>();
                            MyData.put("listingID", listingID); //Add the data you'd like to send to the server.
//                            MyData.put("userEmail", email);
                            MyData.put("ISBN", ISBN);
                            MyData.put("title", title);
                            MyData.put("quality", quality);
                            MyData.put("price", price);
                            MyData.put("course", course);
                            MyData.put("semester", semester);
                            MyData.put("yearPublished", yearPublished);
                            MyData.put("authors", authors);
                            MyData.put("professors", professors);
                            return MyData;
                        }
                    };
                    MyRequestQueue.add(MyStringRequest);

                    returnToMenu(email);
                }
                else {
                    Toast.makeText(EditActivity.this, "Please fill in all sections.", Toast.LENGTH_LONG).show();
                }}
        });

    }
    private void returnToMenu(String email){
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

}
