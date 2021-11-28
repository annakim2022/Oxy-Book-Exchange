package com.example.oxybookexchange;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListingsAdapter extends RecyclerView.Adapter<ListingsAdapter.ViewHolder> {

    private List<Listings> listings;
    private String ISBN, title, quality, price, course, semester, authors, professors, year;

    // pass this list into the constructor of the adapter
    public ListingsAdapter(List<Listings> listings) {
        this.listings = listings;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // used to inflate a layout from xml and return the ViewHolder
        // very standard code/template looking code
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // inflate the custom layout
        View listingsView = inflater.inflate(R.layout.item_listings, parent, false);
        // return a new ViewHolder
        ViewHolder viewHolder = new ViewHolder(listingsView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // populate data into the item through holder

        // grab the actual data model based on the position
        Listings listing = listings.get(position);
        // set the view based on the data and the view names
        holder.textView_title.setText(listing.getTitle());
        holder.textView_course.setText(listing.getCourse());
        holder.textView_profLast.setText(listing.getProfessors());
        holder.textView_semester.setText(listing.getSemester());
        holder.textView_quality.setText(listing.getQuality());
        holder.textView_price.setText("$" + listing.getPrice());
        ISBN = listing.getISBN();
        title = listing.getTitle();
        quality = listing.getQuality();
        price = listing.getPrice();
        course = listing.getCourse();
        semester = listing.getSemester();
        authors = listing.getAuthors();
        professors = listing.getProfessors();
        year = listing.getYearPublished();

        holder.button_moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.button_moreInfo.getContext(), InfoActivity.class);
                intent.putExtra("ISBN", ISBN);
                intent.putExtra("title", title);
                intent.putExtra("quality", quality);
                intent.putExtra("price", price);
                intent.putExtra("course", course);
                intent.putExtra("semester", semester);
                intent.putExtra("authors", authors);
                intent.putExtra("professors", professors);
                intent.putExtra("yearPublished", year);

                holder.button_moreInfo.getContext().startActivity(intent);
            }

        });

    }

    @Override
    public int getItemCount() {
        // returns the total number of items in the list
        return listings.size();
    }

    // provides a direct reference to each of the views within the data item
    // used to cache the views within the item layout for fast access


    class ViewHolder extends RecyclerView.ViewHolder {
        // all the views you want to set as you render the row
        // name, birthday, phrase, villager

        Button button_moreInfo;
        TextView textView_title;
        TextView textView_quality;
        TextView textView_price;
        TextView textView_course;
        TextView textView_profLast;
        TextView textView_semester;

        // we will add the image view later
        // create constructor to set these

        public ViewHolder(View itemView) {
            // itemView -> represents the entire view of each row
            super(itemView);
            // look up each view from the custom layout
            textView_title = itemView.findViewById(R.id.textView_title);
            textView_course = itemView.findViewById(R.id.textView_course);
            textView_profLast = itemView.findViewById(R.id.textView_profLast);
            textView_semester = itemView.findViewById(R.id.textView_semester);
            textView_quality = itemView.findViewById(R.id.textView_quality);
            textView_price = itemView.findViewById(R.id.textView_price);
            button_moreInfo = itemView.findViewById(R.id.button_moreInfo);

        }



    }


}
