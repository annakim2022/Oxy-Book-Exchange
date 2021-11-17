package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Api;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.auth.AUTH;

public class MenuActivity extends AppCompatActivity {
    private static AsyncHttpClient client = new AsyncHttpClient();
    Button button_buy;
    Button button_sell;
    Button button_account;
    RequestQueue requestQueue;
    private ArrayList<String> listingID, userID, ISBN, title, quality, price, course, semester, yearPublished, authors, profLast, profLast2, profLast3;
//    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        //set buttons
        button_buy = findViewById(R.id.button_buy);
        button_sell = findViewById(R.id.button_sell);
        button_account = findViewById(R.id.button_account);

        button_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchListingsActivity(v);
            }
        });

        button_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message2 = "let me sell";
                launchSellActivity(v, message2);
            }
        });

        button_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAccountActivity(v);
            }
        });

    }

    public void launchListingsActivity(View view) {

        Intent intent = new Intent(this, ListingsActivity.class);
        requestQueue = Volley.newRequestQueue(this);
        listingID = new ArrayList<>();
        userID = new ArrayList<>();
        ISBN = new ArrayList<>();
        title = new ArrayList<>();
        quality = new ArrayList<>();
        price = new ArrayList<>();
        course = new ArrayList<>();
        semester = new ArrayList<>();
        yearPublished = new ArrayList<>();
        authors = new ArrayList<>();
        profLast = new ArrayList<>();
        profLast2 = new ArrayList<>();
        profLast3 = new ArrayList<>();

        String api_url = "http://134.69.196.240:3308/listings";

        client.get(api_url, new AsyncHttpResponseHandler() {
                    JSONArray listingsJSON = null;
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            listingsJSON = new JSONArray(new String(responseBody));
                            for (int i = 0; i < listingsJSON.length(); i++) {
                                JSONObject jsonObject = listingsJSON.getJSONObject(i);
                                listingID.add(jsonObject.getString("listingID"));
                                userID.add(jsonObject.getString("userID"));
                                ISBN.add(jsonObject.getString("ISBN"));
                                title.add(jsonObject.getString("title"));
                                quality.add(jsonObject.getString("quality"));
                                price.add(jsonObject.getString("price"));
                                course.add(jsonObject.getString("course"));
                                semester.add(jsonObject.getString("semester"));
                                yearPublished.add(jsonObject.getString("yearPublished"));
                                authors.add(jsonObject.getString("authors"));
                                profLast.add(jsonObject.getString("profLast"));
                                profLast2.add(jsonObject.getString("profLast2"));
                                profLast3.add(jsonObject.getString("profLast3"));

                            }
                            intent.putExtra("listingID", listingID);
                            intent.putExtra("userID", userID);
                            intent.putExtra("ISBN", ISBN);
                            intent.putExtra("title", title);
                            intent.putExtra("quality", quality);
                            intent.putExtra("price", price);
                            intent.putExtra("course", course);
                            intent.putExtra("semester", semester);
                            intent.putExtra("yearPublished", yearPublished);
                            intent.putExtra("authors", authors);
                            intent.putExtra("profLast", profLast);
                            intent.putExtra("profLast2", profLast2);
                            intent.putExtra("profLast3", profLast3);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }

                }
        );

    }

    public void launchSellActivity(View view, String s) {
        Intent intent = new Intent(this, MyListingsActivity.class);
        intent.putExtra("sell", s);
        startActivity(intent);
    }

    public void launchAccountActivity(View view) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }
}
