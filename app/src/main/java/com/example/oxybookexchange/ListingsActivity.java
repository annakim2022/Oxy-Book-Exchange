package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ListingsActivity extends AppCompatActivity {

    private ArrayList<Listings> listings;
    private RecyclerView recyclerView;

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
            listingsJSON = new JSONObject(loadJSONFromAsset("listings.json"));
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
//
//        TextView textView = findViewById(R.id.textView);
//        textView.setText(getTextFromSQL());

    }

//    public String getTextFromSQL() {
//        String string = "";
//        try {
//            ConnectionHelper connectionHelper = new ConnectionHelper();
//            connect = connectionHelper.connectionClass();
//            if(connect!=null) {
//                String query = "SELECT * FROM books";
//                Statement statement = connect.createStatement();
//                ResultSet resultSet = statement.executeQuery(query);
//
//                while (resultSet.next()) {
//                    string = resultSet.getString(2);
//                }
//            }
//        }
//        catch (Exception e) {
//
//        }
//        return string;
//    }

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
