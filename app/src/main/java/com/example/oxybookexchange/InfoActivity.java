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

    private TextView TVtitle;
    private TextView TVisbn;
    private TextView TVauthors;
    private TextView TVyear;
    private TextView TVquality;
    private TextView TVprice;
    private TextView TVcourse;
    private TextView TVsemester;
    private TextView TVprofessors;
    private String ISBN, title, year, quality, price, course, semester, authors, professors;
    private Button button_backToListings;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        AsyncHttpClient client = new AsyncHttpClient();

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

        TVtitle = findViewById(R.id.title);
        TVisbn = findViewById(R.id.isbn);
        TVauthors = findViewById(R.id.authors);
        TVyear = findViewById(R.id.year);
        TVquality = findViewById(R.id.quality);
        TVprice = findViewById(R.id.price);
        TVcourse = findViewById(R.id.course);
        TVsemester = findViewById(R.id.semester);
        TVprofessors = findViewById(R.id.professors);

        TVtitle.setText(title);
        TVisbn.setText(ISBN);
        TVauthors.setText(authors);
        TVyear.setText(year);
        TVquality.setText(quality);
        TVprice.setText("$" + price);
        TVcourse.setText(course);
        TVsemester.setText(semester);
        TVprofessors.setText(professors);


        button_backToListings = findViewById(R.id.button_backToListings);
        button_backToListings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack(v);
            }
        });
    }

    private void goBack(View v) {
//        Intent intent = new Intent(this, ListingsActivity.class);
//        startActivityForResult(intent, 1);
        finish();
    }
}
