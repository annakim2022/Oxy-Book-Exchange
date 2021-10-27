package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ListingsActivity extends AppCompatActivity {

    private ArrayList<Listings> listings;
    private RecyclerView recyclerView;
    //private Object Listings;\
  //  private Button button_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);


        // look up the recycler view in the main activity xml
        recyclerView = findViewById(R.id.recyclerView_listings);
        // create a new ArrayList
        listings = new ArrayList<>();

        // use the method to load the data
        // for each JSONObject, create a Villager object and add to the list
        JSONObject listingsJSON = null;
        try {
            listingsJSON = new JSONObject(loadJSONFromAsset("listings.json")); //TODO add database
            JSONArray listingsArray = listingsJSON.getJSONArray("listings");
            for (int i = 0; i < listingsArray.length(); i++) {
                JSONObject listingsObject = listingsArray.getJSONObject(i);
                Listings listing = new Listings(
                        listingsObject.getString("title"),
                        listingsObject.getString("course"),
                        listingsObject.getString("professor"),
                        listingsObject.getString("semester"),
                        listingsObject.getString("quality"),
                        listingsObject.getString("price"));

                // add it to the array list
                listings.add(listing);
            }

            // create villager adapter to pass in the data
            ListingsAdapter adapter = new ListingsAdapter(listings);
            // attach the adapter to the recycler view to populate
            recyclerView.setAdapter(adapter);
            // define where to add the layout manager
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            // you need a layout manager, otherwise your recycler view is not going to show


        } catch (
                JSONException e) {
            e.printStackTrace();
        }



    }

    private String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = this.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
