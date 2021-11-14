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

import cz.msebera.android.httpclient.Header;

public class InfoActivity extends AppCompatActivity {

    private TextView TVtitle;
    private TextView TVisbn;
    private TextView TVpublisher;
    private TextView TVauthors;
    private TextView TVyear;
    private TextView TVquality;
    private TextView TVprice;
    private TextView TVcourse;
    private TextView TVsemester;
    private TextView TVprofessors;
    private Button button_backToListings;

    private String api_url;
    private String ISBN;

    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreinfo);
        //get intent
        Intent intent = getIntent();
        ISBN = intent.getStringExtra("ISBN");

        api_url = "https://openlibrary.org/isbn/" + ISBN + ".json";

        client.get(api_url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                try {
                    JSONObject json = new JSONObject(new String(responseBody));
                    String title = json.getString("title");
                    JSONArray publisherArray = json.getJSONArray("publishers");
                    String publishers = publisherArray.getString(0);
                    Log.e("HERE", publishers);
                    for (int i = 1; i < publisherArray.length(); i++){
                        publishers += ", " + publisherArray.getString(1);
                    }
                    String authors = json.getString("by_statement");
                    String year = json.getString("publish_date");
                    String quality = intent.getStringExtra("quality");
                    String price = intent.getStringExtra("price");
                    String course = intent.getStringExtra("course");
                    String semester = intent.getStringExtra("semester");
                    String professors = intent.getStringExtra("professors");

                    TVtitle = findViewById(R.id.title);
                    TVisbn = findViewById(R.id.isbn);
                    TVpublisher = findViewById(R.id.publisher);
                    TVauthors= findViewById(R.id.authors);
                    TVyear = findViewById(R.id.year);
                    TVquality = findViewById(R.id.quality);
                    TVprice = findViewById(R.id.price);
                    TVcourse = findViewById(R.id.course);
                    TVsemester = findViewById(R.id.semester);
                    TVprofessors = findViewById(R.id.professors);

                    Log.e("HERE", title + ISBN + publishers + authors + year);

                    TVtitle.setText(title);
                    TVisbn.setText(ISBN);
                    TVpublisher.setText(publishers);
                    TVauthors.setText(authors);
                    TVyear.setText(year);
                    TVquality.setText(quality);
                    TVprice.setText(price);
                    TVcourse.setText(course);
                    TVsemester.setText(semester);
                    TVprofessors.setText(professors);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        button_backToListings = findViewById(R.id.button_backToListings);
        button_backToListings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack(v);
            }
        });

    }
    private void goBack(View v){
//        Intent intent = new Intent(this, ListingsActivity.class);
//        startActivityForResult(intent, 1);
        finish();
    }
}
