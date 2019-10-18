package com.example.bookunion;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {

    public TextView bookName;
    public TextView authorName;


    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);

        bookName = itemView.findViewById(R.id.bookName);
        authorName = itemView.findViewById(R.id.authorName);

    }
}
