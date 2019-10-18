package com.example.bookunion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter_user_listed_books extends RecyclerView.Adapter<adapter_user_listed_books.ViewHolder> {

    private List<list_item_user_books> listItems;
    private Context context;

    public adapter_user_listed_books(List<list_item_user_books> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_listed_books_card_view, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        list_item_user_books listItem = listItems.get(position);

        holder.book_name.setText(listItem.getBook_name());
        holder.author_name.setText(listItem.getAuthor_name());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView book_name;
        public TextView author_name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            book_name = (TextView) itemView.findViewById(R.id.listed_book_name);
            author_name = (TextView) itemView.findViewById(R.id.listed_author_name);
        }
    }
}
