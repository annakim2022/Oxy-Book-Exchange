package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.loopj.android.http.AsyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    Button button_sign_out;
    GoogleSignInClient googleSignInClient;

    private static AsyncHttpClient client = new AsyncHttpClient();
    RequestQueue requestQueue;

    private TextView textView_username;
    private TextView textView_userID;
    private TextView textView_name;
    private TextView textView_email;
    private TextView textView_phoneNo;

    private String username;
    private String userID;
    private String name;
    private String email;
    private String phoneNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        requestQueue = Volley.newRequestQueue(this);

        textView_username = findViewById(R.id.textView_username);
        textView_userID = findViewById(R.id.textView_userID);
        textView_name = findViewById(R.id.textView_name);
        textView_email = findViewById(R.id.textView_email);
        textView_phoneNo = findViewById(R.id.textView_phoneNo);

        String api_url = "http://134.69.224.74:3308/users";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, api_url, null, new com.android.volley.Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    userID = jsonObject.getString("userID");
                    username = jsonObject.getString("username");
                    name = jsonObject.getString("firstName") + " " + jsonObject.getString("lastName");
                    email = jsonObject.getString("email");

                    try {
                        phoneNo = jsonObject.getString("phoneNo");
                        textView_phoneNo.setText("phone: " + phoneNo);
                    } catch (Exception e) {
                    }

                    textView_userID.setText("ID: " + userID);
                    textView_username.setText("username: " + username);
                    textView_name.setText(name);
                    textView_email.setText("email: " + email);

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

        button_sign_out = findViewById(R.id.button_sign_out);
        button_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    // ...
                    case R.id.button_sign_out:
                        signOut();
                        break;
                    // ...
                }
            }

        });
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

}
