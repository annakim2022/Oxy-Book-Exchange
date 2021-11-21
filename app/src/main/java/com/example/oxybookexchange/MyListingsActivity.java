package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyListingsActivity extends AppCompatActivity {

    private Button button_add;
    // spinner
    ArrayAdapter<String> searchAdapter;
    Spinner spinnerSearchMy;
    ArrayList<String> searchList = new ArrayList<>();

    // recycler view
    private RecyclerView recyclerView;
    private ArrayList<Listings> listings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylistings);

        // spinner
        spinnerSearchMy = findViewById(R.id.spinner_searchMy);
        searchList.add("ISBN");
        searchList.add("Title");
        searchList.add("Author");
        searchList.add("Course");
        searchList.add("Professor");
        searchAdapter = new ArrayAdapter<>(MyListingsActivity.this, android.R.layout.simple_spinner_item, searchList);
        searchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSearchMy.setAdapter(searchAdapter);

        // recycler view
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.recyclerView_myListings);
        listings = new ArrayList<>();
        ArrayList<String> listingID = intent.getStringArrayListExtra("listingID");
        ArrayList<String> userID = intent.getStringArrayListExtra("userID");
        ArrayList<String> ISBN = intent.getStringArrayListExtra("ISBN");
        ArrayList<String> title = intent.getStringArrayListExtra("title");
        ArrayList<String> quality = intent.getStringArrayListExtra("quality");
        ArrayList<String> price = intent.getStringArrayListExtra("price");
        ArrayList<String> course = intent.getStringArrayListExtra("course");
        ArrayList<String> semester = intent.getStringArrayListExtra("semester");
        ArrayList<String> yearPublished = intent.getStringArrayListExtra("yearPublished");
        ArrayList<String> authors = intent.getStringArrayListExtra("authors");
        ArrayList<String> professors = intent.getStringArrayListExtra("professors");

        for (int i = 0; i < listingID.size(); i++) {
            Listings listing = new Listings(
                    listingID.get(i),
                    userID.get(i),
                    ISBN.get(i),
                    title.get(i),
                    quality.get(i),
                    price.get(i),
                    course.get(i),
                    semester.get(i),
                    yearPublished.get(i),
                    authors.get(i),
                    professors.get(i));
            listings.add(listing);
        }

        // call adapter
        MyListingsAdapter adapter = new MyListingsAdapter(listings);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button_add = findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
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
