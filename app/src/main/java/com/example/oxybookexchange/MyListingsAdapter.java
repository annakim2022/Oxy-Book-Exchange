package com.example.oxybookexchange;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyListingsAdapter extends RecyclerView.Adapter<MyListingsAdapter.ViewHolder>{

    class ViewHolder extends RecyclerView.ViewHolder {
        // all the views you want to set as you render the row
        // name, birthday, phrase, villager
        private Button button_editInfo;

        TextView textView_title;
        TextView textView_quality;
        TextView textView_price;
        TextView textView_course;
        TextView textView_professor;
        TextView textView_semester;


        // we will add the image view later
        // create constructor to set these

        public ViewHolder(View itemView) {
            // itemView -> represents the entire view of each row
            super(itemView);
            // look up each view from the custom layout
            textView_title = itemView.findViewById(R.id.textView_title);
            textView_course = itemView.findViewById(R.id.textView_course);
            textView_professor = itemView.findViewById(R.id.textView_profLast);
            textView_semester = itemView.findViewById(R.id.textView_semester);
            textView_quality = itemView.findViewById(R.id.textView_quality);
            textView_price = itemView.findViewById(R.id.textView_price);
            button_editInfo = itemView.findViewById(R.id.button_editInfo);
        }

    }

    private List<Listings> listings;

    // pass this list into the constructor of the adapter
    public MyListingsAdapter(List<Listings> listings) {
        this.listings = listings;

    }


    @NonNull
    @Override
    public MyListingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // inflate the custom layout
        View listingsView = inflater.inflate(R.layout.item_selling, parent, false);
        // return a new ViewHolder
        MyListingsAdapter.ViewHolder viewHolder = new MyListingsAdapter.ViewHolder(listingsView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListingsAdapter.ViewHolder holder, int position) {
        Listings listing = listings.get(position);

        // set the view based on the data and the view names
        holder.textView_title.setText(listing.getTitle());
        holder.textView_course.setText(listing.getCourse());
        holder.textView_professor.setText(listing.getProfLast());
        holder.textView_semester.setText(listing.getSemester());
        holder.textView_quality.setText(listing.getQuality());
        holder.textView_price.setText(listing.getPrice());

        holder.button_editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "hi im working";
                launchInfoActivity(v, s);
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

    public void launchInfoActivity(View v, String s) {
        Intent intent = new Intent(v.getContext(), InfoActivity.class);
        intent.putExtra("info", s);
        v.getContext().startActivity(intent);
    }
}
