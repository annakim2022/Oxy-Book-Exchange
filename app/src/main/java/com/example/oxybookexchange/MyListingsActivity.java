package com.example.oxybookexchange;

import android.os.Bundle;
import android.widget.ArrayAdapter;

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
    private ArrayList<Listings> listings;
    private RecyclerView recyclerView;
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylistings);


        // look up the recycler view in the main activity xml
        recyclerView = findViewById(R.id.recyclerView_sell);
        // create a new ArrayList
        listings = new ArrayList<>();
        String api_url = "http://134.69.224.74:3308/listings";

        client.get(api_url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JSONArray listingsJSON = null;
                try {
                    listingsJSON = new JSONArray(new String(responseBody));
                    for (int i = 0; i < listingsJSON.length(); i++) {
                        JSONObject listingsObject = listingsJSON.getJSONObject(i);

                        Listings listing = new Listings(
                                listingsObject.getString("listingID"),
                                listingsObject.getString("userID"),
                                listingsObject.getString("ISBN"),
                                listingsObject.getString("title"),
                                listingsObject.getString("quality"),
                                listingsObject.getString("price"),
                                listingsObject.getString("course"),
                                listingsObject.getString("semester"),
                                listingsObject.getString("yearPublished"),
                                listingsObject.getString("authors"),
                                listingsObject.getString("profLast"),
                                listingsObject.getString("profLast2"),
                                listingsObject.getString("profLast3"));

                        // add it to the array list
                        listings.add(listing);
                    }

                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        // create adapter to pass in the data
        MyListingsAdapter adapter = new MyListingsAdapter(listings);
        // attach the adapter to the recycler view to populate
        recyclerView.setAdapter(adapter);
        // define where to add the layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // you need a layout manager, otherwise your recycler view is not going to show


}}
