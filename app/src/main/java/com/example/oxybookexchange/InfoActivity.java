package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

import cz.msebera.android.httpclient.Header;

public class InfoActivity extends AppCompatActivity {

    private TextView textView_title;
    private TextView textView_isbn;
    private TextView textView_authors;
    private TextView textView_year;
    private TextView textView_quality;
    private TextView textView_price;
    private TextView textView_course;
    private TextView textView_semester;
    private TextView textView_professors, textView_email;
    private String ISBN, title, year, quality, price, course, semester, authors, professors, email;
    private Button button_backToListings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();

        ISBN = intent.getStringExtra("ISBN");
        title = intent.getStringExtra("title");
        quality = intent.getStringExtra("quality");
        price = intent.getStringExtra("price");
        course = intent.getStringExtra("course");
        semester = intent.getStringExtra("semester");
        authors = intent.getStringExtra("authors");
        professors = intent.getStringExtra("professors");
        year = intent.getStringExtra("yearPublished");
        email = intent.getStringExtra("email");

        textView_title = findViewById(R.id.title);
        textView_isbn = findViewById(R.id.isbn);
        textView_authors = findViewById(R.id.authors);
        textView_year = findViewById(R.id.year);
        textView_quality = findViewById(R.id.quality);
        textView_price = findViewById(R.id.price);
        textView_course = findViewById(R.id.course);
        textView_semester = findViewById(R.id.semester);
        textView_professors = findViewById(R.id.professors);
        textView_email = findViewById(R.id.email);

        textView_title.setText(title);
        textView_isbn.setText("ISBN: " + ISBN);
        textView_authors.setText("Author(s): " + authors);
        textView_year.setText("Year Published: " + year);
        textView_quality.setText("Quality: " + quality);
        textView_price.setText("Price: $" + price);
        textView_course.setText("Course: " + course);
        textView_semester.setText("Semester: " + semester);
        textView_professors.setText("Professor(s): " + professors);
        textView_email.setText("Seller Email: " + email);

//        button_backToListings = findViewById(R.id.button_backToListings);
//        button_backToListings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goBack(v);
//            }
//        });
    }

    private void goBack(View v) {
        finish();
    }
}
