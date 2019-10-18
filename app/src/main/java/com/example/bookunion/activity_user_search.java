package com.example.bookunion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_user_search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);
    }

    public void general_search_button_clicked(View view){
        Intent intent = new Intent(activity_user_search.this, activity_book_list.class);
        startActivity(intent);
    }
    public void genre_button_clicked(View view){
        Intent intent = new Intent(activity_user_search.this, activity_genre_select.class);
        startActivity(intent);
    }
    public void sharing_type_button_clicked(View view){
        Intent intent = new Intent(activity_user_search.this, activity_type_select.class);
        startActivity(intent);
    }
}
