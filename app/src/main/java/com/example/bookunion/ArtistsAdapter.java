package com.example.bookunion;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Belal on 4/17/2018.
 */

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder> {

    private Context mCtx;
    private List<DataSetFireBook> artistList;

    public ArtistsAdapter(Context mCtx, List<DataSetFireBook> artistList) {
        this.mCtx = mCtx;
        this.artistList = artistList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.user_listed_books_card_view, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        DataSetFireBook artist = artistList.get(position);
        holder.textViewName.setText(artist.getBook_name());
        holder.textViewGenre.setText(artist.getBook_author());
        //holder.textViewAge.setText("Age: " + artist.age);
        //holder.textViewCountry.setText("Country: " + artist.country);
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewGenre, textViewAge, textViewCountry;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.listed_book_name);
            textViewGenre = itemView.findViewById(R.id.listed_author_name);
            //textViewAge = itemView.findViewById(R.id.text_view_age);
            //textViewCountry = itemView.findViewById(R.id.text_view_country);
        }
    }
}
