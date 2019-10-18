package com.example.bookunion;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class final_book_list_user extends ArrayAdapter<books> {
    private Activity context;
    private List<books> book_list;

    public final_book_list_user (Activity context, List<books> book_list){
        super(context, R.layout.book_display_layout, book_list);
        this.context = context;
        this.book_list = book_list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.book_display_layout, null, true);

        TextView bookname = listViewItem.findViewById(R.id.list_book_name);
        TextView authorname = listViewItem.findViewById(R.id.list_author_name);

        books book = book_list.get(position);
        bookname.setText(book.getBook_name());
        authorname.setText(book.getBook_author());

        return listViewItem;
    }
}
