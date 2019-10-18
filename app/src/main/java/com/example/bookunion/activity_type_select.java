package com.example.bookunion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_type_select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_select);
    }

    public void freeSelected(View view){
        Intent intent =new Intent(activity_type_select.this, activity_free_book.class);
        startActivity(intent);
    }
    public void buySelected(View view){
        Intent intent =new Intent(activity_type_select.this, activity_buy_books.class);
        startActivity(intent);
    }
}
