package com.example.oxybookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MyInfoActivity extends AppCompatActivity {

    private TextView textView_title;
    private TextView textView_isbn;
    private TextView textView_authors;
    private TextView textView_year;
    private TextView textView_quality;
    private TextView textView_price;
    private TextView textView_course;
    private TextView textView_semester;
    private TextView textView_professors, textView_email;
    private String ISBN, title, year, quality, price, course, semester, authors, email, professors;
    private Button button_backToListings, button_edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        Intent intent = getIntent();

        ISBN = intent.getStringExtra("ISBN");
        title = intent.getStringExtra("title");
        Log.e("MY INFO", title);
        quality = intent.getStringExtra("quality");
        price = intent.getStringExtra("price");
        course = intent.getStringExtra("course");
        semester = intent.getStringExtra("semester");
        authors = intent.getStringExtra("authors");
        professors = intent.getStringExtra("professors");
        year = intent.getStringExtra("yearPublished");
        email = intent.getStringExtra("email");

        textView_title = findViewById(R.id.title2);
        textView_isbn = findViewById(R.id.isbn2);
        textView_authors = findViewById(R.id.authors2);
        textView_year = findViewById(R.id.year2);
        textView_quality = findViewById(R.id.quality2);
        textView_price = findViewById(R.id.price2);
        textView_course = findViewById(R.id.course2);
        textView_semester = findViewById(R.id.semester2);
        textView_professors = findViewById(R.id.professors2);
        textView_email = findViewById(R.id.email2);

        textView_title.setText("Title: " + title);
        textView_isbn.setText("ISBN: " + ISBN);
        textView_authors.setText("Author(s): " + authors);
        textView_year.setText("Year Published: " + year);
        textView_quality.setText("Quality: " + quality);
        textView_price.setText("Price: $" + price);
        textView_course.setText("Course: " + course);
        textView_semester.setText("Semester: " + semester);
        textView_professors.setText("Professor(s): " + professors);
        textView_email.setText("Seller Email: " + email);

        button_edit = findViewById(R.id.button_edit);
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit(v);
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

    private void goBack(View v) {
//        Intent intent = new Intent(this, ListingsActivity.class);
//        startActivityForResult(intent, 1);
        finish();
    }

    private void edit(View v) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("ISBN", ISBN);
        intent.putExtra("title", title);
        intent.putExtra("quality", quality);
        intent.putExtra("price", price);
        intent.putExtra("course", course);
        intent.putExtra("semester", semester);
        intent.putExtra("authors", authors);
        intent.putExtra("professors", professors);
        intent.putExtra("yearPublished", year);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
