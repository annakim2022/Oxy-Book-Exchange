package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class MyListingsActivity extends AppCompatActivity {

    // create button
    private Button button_add;

    // search view
    private SearchView filter;

    // spinner
    ArrayAdapter<String> searchAdapter;
    Spinner spinnerSearchMy;
    ArrayList<String> searchList = new ArrayList<>();

    // recycler view
    private RecyclerView recyclerView;
    private ArrayList<Listings> listings;
    private ArrayList<Listings> displayedListings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylistings);

        // recycler view
        Intent intent = getIntent();
        recyclerView = findViewById(R.id.recyclerView_myListings);
        listings = new ArrayList<>();
        displayedListings = new ArrayList<>();
        ArrayList<String> listingID = intent.getStringArrayListExtra("listingID");
        ArrayList<String> userEmail = intent.getStringArrayListExtra("userEmail");
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
                    userEmail.get(i),
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
            displayedListings.add(listing);
        }
        // send info to edit activity


        // spinner
        spinnerSearchMy = findViewById(R.id.spinner_searchMy);
        searchList.add("ISBN");
        searchList.add("Title");
        searchList.add("Author");
        searchList.add("Professor");
        searchAdapter = new ArrayAdapter<>(MyListingsActivity.this, android.R.layout.simple_spinner_item, searchList);
        searchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSearchMy.setAdapter(searchAdapter);

        // search view
        filter = findViewById(R.id.searchView_myListings);
        spinnerSearchMy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String item = parent.getItemAtPosition(position).toString();
                if (item == "ISBN") {
                    filter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return true;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            if (!newText.isEmpty()) {
                                // clear recycler view
                                newText = newText.toLowerCase();
                                displayedListings.clear();
                                for (int i = 0; i < listings.size(); i++) {
                                    if (listings.get(i).getISBN().toLowerCase().contains(newText)) {
                                        // display in recycler view
                                        displayedListings.add(listings.get(i));

                                    }
                                    Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
                                }

                            } else {
                                // clear recycler view
                                // add all to recycler view
                                displayedListings.clear();
                                displayedListings.addAll(listings);

                                Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
                            }
                            return true;
                        }
                    });

                } else if (item == "Title") {

                    filter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return true;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            if (!newText.isEmpty()) {
                                // clear recycler view
                                newText = newText.toLowerCase();
                                displayedListings.clear();
                                for (int i = 0; i < listings.size(); i++) {
                                    if (listings.get(i).getTitle().toLowerCase().contains(newText)) {
                                        // display in recycler view
                                        displayedListings.add(listings.get(i));

                                    }
                                    Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
                                }

                            } else {
                                // clear recycler view
                                // add all to recycler view
                                displayedListings.clear();
                                displayedListings.addAll(listings);

                                Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
                            }
                            return true;
                        }
                    });
                } else if (item == "Author") {
                    filter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return true;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            if (!newText.isEmpty()) {
                                // clear recycler view
                                newText = newText.toLowerCase();
                                displayedListings.clear();
                                for (int i = 0; i < listings.size(); i++) {
                                    if (listings.get(i).getAuthors().toLowerCase().contains(newText)) {
                                        // display in recycler view
                                        displayedListings.add(listings.get(i));

                                    }
                                    Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
                                }

                            } else {
                                // clear recycler view
                                // add all to recycler view
                                displayedListings.clear();
                                displayedListings.addAll(listings);

                                Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
                            }
                            return true;
                        }
                    });

                } else if (item == "Professor") {
                    filter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return true;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            if (!newText.isEmpty()) {
                                // clear recycler view
                                newText = newText.toLowerCase();
                                displayedListings.clear();
                                for (int i = 0; i < listings.size(); i++) {
                                    if (listings.get(i).getProfessors().toLowerCase().contains(newText)) {
                                        // display in recycler view
                                        displayedListings.add(listings.get(i));

                                    }
                                    Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
                                }

                            } else {
                                // clear recycler view
                                // add all to recycler view
                                displayedListings.clear();
                                displayedListings.addAll(listings);

                                Objects.requireNonNull(recyclerView.getAdapter()).notifyDataSetChanged();
                            }
                            return true;
                        }
                    });
                }

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        // call adapter
        MyListingsAdapter adapter = new MyListingsAdapter(displayedListings);
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
