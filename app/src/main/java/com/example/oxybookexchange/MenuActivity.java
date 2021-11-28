package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    GoogleSignInClient googleSignInClient;
    Button button_buy;
    Button button_sell;
    Button button_signout;
    String email;

//    Button button_account;
    private ArrayList<String> listingID, userEmail, ISBN, title, quality, price, course, semester, yearPublished, authors, professors;
//    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent2 = getIntent();
        email = intent2.getStringExtra("email");

        // GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        //set buttons
        button_buy = findViewById(R.id.button_buy);
        button_sell = findViewById(R.id.button_sell);
//        button_account = findViewById(R.id.button_account);

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
                launchMyListingsActivity(v, message2);
            }
        });

        button_signout = findViewById(R.id.button_signout);
        button_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    // ...
                    case R.id.button_signout:
                        signOut();
                        break;
                    // ...
                }
            }

        });

//        button_account.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                launchAccountActivity(v);
//            }
//        });

    }

    public void launchListingsActivity(View view) {

        Intent intent = new Intent(this, ListingsActivity.class);
        listingID = new ArrayList<>();
        userEmail = new ArrayList<>();
        ISBN = new ArrayList<>();
        title = new ArrayList<>();
        quality = new ArrayList<>();
        price = new ArrayList<>();
        course = new ArrayList<>();
        semester = new ArrayList<>();
        yearPublished = new ArrayList<>();
        authors = new ArrayList<>();
        professors = new ArrayList<>();

        String api_url = "http://134.69.236.202:3308/listings";

        client.get(api_url, new AsyncHttpResponseHandler() {

                    JSONArray listingsJSON = null;
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            listingsJSON = new JSONArray(new String(responseBody));
                            for (int i = 0; i < listingsJSON.length(); i++) {
                                JSONObject jsonObject = listingsJSON.getJSONObject(i);
                                listingID.add(jsonObject.getString("listingID"));
                                userEmail.add(jsonObject.getString("userEmail"));
                                ISBN.add(jsonObject.getString("ISBN"));
                                title.add(jsonObject.getString("title"));
                                quality.add(jsonObject.getString("quality"));
                                price.add(jsonObject.getString("price"));
                                course.add(jsonObject.getString("course"));
                                semester.add(jsonObject.getString("semester"));
                                yearPublished.add(jsonObject.getString("yearPublished"));
                                authors.add(jsonObject.getString("authors"));
                                professors.add(jsonObject.getString("professors"));

                            }
                            intent.putExtra("listingID", listingID);
                            intent.putExtra("userEmail", userEmail);
                            intent.putExtra("ISBN", ISBN);
                            intent.putExtra("title", title);
                            intent.putExtra("quality", quality);
                            intent.putExtra("price", price);
                            intent.putExtra("course", course);
                            intent.putExtra("semester", semester);
                            intent.putExtra("yearPublished", yearPublished);
                            intent.putExtra("authors", authors);
                            intent.putExtra("professors", professors);
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

    public void launchMyListingsActivity(View view, String s) {
        Intent intent = new Intent(this, MyListingsActivity.class);
        listingID = new ArrayList<>();
        userEmail = new ArrayList<>();
        ISBN = new ArrayList<>();
        title = new ArrayList<>();
        quality = new ArrayList<>();
        price = new ArrayList<>();
        course = new ArrayList<>();
        semester = new ArrayList<>();
        yearPublished = new ArrayList<>();
        authors = new ArrayList<>();
        professors = new ArrayList<>();

        Log.e("MENU", email);
        String api_url = "http://134.69.236.202:3308/listings/" + email;


        client.get(api_url, new AsyncHttpResponseHandler() {

                    JSONArray myListingsJSON = null;
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            myListingsJSON = new JSONArray(new String(responseBody));
                            for (int i = 0; i < myListingsJSON.length(); i++) {
                                JSONObject jsonObject = myListingsJSON.getJSONObject(i);
                                listingID.add(jsonObject.getString("listingID"));
                                userEmail.add(jsonObject.getString("userEmail"));
                                ISBN.add(jsonObject.getString("ISBN"));
                                title.add(jsonObject.getString("title"));
                                quality.add(jsonObject.getString("quality"));
                                price.add(jsonObject.getString("price"));
                                course.add(jsonObject.getString("course"));
                                semester.add(jsonObject.getString("semester"));
                                yearPublished.add(jsonObject.getString("yearPublished"));
                                authors.add(jsonObject.getString("authors"));
                                professors.add(jsonObject.getString("professors"));

                            }
                            intent.putExtra("listingID", listingID);
                            intent.putExtra("userEmail", userEmail);
                            intent.putExtra("ISBN", ISBN);
                            intent.putExtra("title", title);
                            intent.putExtra("quality", quality);
                            intent.putExtra("price", price);
                            intent.putExtra("course", course);
                            intent.putExtra("semester", semester);
                            intent.putExtra("yearPublished", yearPublished);
                            intent.putExtra("authors", authors);
                            intent.putExtra("professors", professors);
                            intent.putExtra("email", email);
                            startActivity(intent);

                        } catch (JSONException e) {
                            launchNoListingsActivity();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    }
                }
        );
    }

    private void signOut() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        googleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                launchLoginActivity();
            }
        });
    }
    public void launchLoginActivity() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void launchNoListingsActivity() {
        Intent intent = new Intent(this, NoListingsActivity.class);
        startActivity(intent);
    }
}
