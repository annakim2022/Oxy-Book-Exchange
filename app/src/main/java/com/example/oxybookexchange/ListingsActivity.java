package com.example.oxybookexchange;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListingsActivity extends AppCompatActivity {

    // spinner
    ArrayAdapter<String> searchAdapter;
    Spinner spinnerSearch;
    ArrayList<String> searchList = new ArrayList<>();

    // recycler view
    private RecyclerView recyclerView;
    private ArrayList<Listings> listings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        // spinner
        spinnerSearch = findViewById(R.id.spinner_search);
        searchList.add("ISBN");
        searchList.add("Title");
        searchList.add("Author");
        searchList.add("Course");
        searchList.add("Professor");
        searchAdapter = new ArrayAdapter<>(ListingsActivity.this, android.R.layout.simple_spinner_item, searchList);
        searchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSearch.setAdapter(searchAdapter);

        // recycler view
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.recyclerView_listings);
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
        ListingsAdapter adapter = new ListingsAdapter(listings);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


}