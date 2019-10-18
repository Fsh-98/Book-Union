package com.example.bookunion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_user_profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }

    public void add_book_clicked(View view){
        Intent intent = new Intent(activity_user_profile.this, activity_add_book.class);
        startActivity(intent);
    }
    public void book_list_clicked(View view){
        Intent intent = new Intent(activity_user_profile.this, activity_booklist_user.class);
        startActivity(intent);
    }

    public void search_button_clicked(View view){
        Intent intent = new Intent(activity_user_profile.this, activity_user_search.class);
        startActivity(intent);
    }

    public void settings_clicked(View view){
        Intent intent = new Intent(activity_user_profile.this, activity_user_settings.class);
        startActivity(intent);
    }

    public void senti_clicked(View view){
        Intent intent = new Intent(activity_user_profile.this, activity_user_developers.class);
        startActivity(intent);
    }

}
