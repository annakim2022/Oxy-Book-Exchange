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

    private ArrayList<Listings> listings;
    private RecyclerView recyclerView;
    private static AsyncHttpClient client = new AsyncHttpClient();
    Spinner spinnerSearch;
    ArrayList<String> searchList = new ArrayList<>();
    ArrayAdapter<String> searchAdapter;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        // spinner
        requestQueue = Volley.newRequestQueue(this);
        spinnerSearch = findViewById(R.id.spinner_search);

        // look up the recycler view in the main activity xml
        recyclerView = findViewById(R.id.recyclerView_listings);
        // create a new ArrayList
        listings = new ArrayList<>();
        String api_url = "http://134.69.196.240:3308/listings";

        searchList.add("ISBN");
        searchList.add("Title");
        searchList.add("Author");
        searchList.add("Course");
        searchList.add("Professor");

        searchAdapter = new ArrayAdapter<>(ListingsActivity.this, android.R.layout.simple_spinner_item, searchList);
        searchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSearch.setAdapter(searchAdapter);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, api_url, null, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        //String ISBN = jsonObject.getString("ISBN"); // gets all ISBN numbers
                        Listings listing = new Listings(
                                jsonObject.getString("listingID"),
                                jsonObject.getString("userID"),
                                jsonObject.getString("ISBN"),
                                jsonObject.getString("title"),
                                jsonObject.getString("quality"),
                                jsonObject.getString("price"),
                                jsonObject.getString("course"),
                                jsonObject.getString("semester"),
                                jsonObject.getString("yearPublished"),
                                jsonObject.getString("authors"),
                                jsonObject.getString("profLast"),
                                jsonObject.getString("profLast2"),
                                jsonObject.getString("profLast3"));

                        // add it to the array list
                        listings.add(listing);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
        // create adapter to pass in the data
        ListingsAdapter adapter = new ListingsAdapter(listings);
        // attach the adapter to the recycler view to populate
        recyclerView.setAdapter(adapter);
        // define where to add the layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // you need a layout manager, otherwise your recycler view is not going to show
        Log.e("HERE", "here");

    }


}