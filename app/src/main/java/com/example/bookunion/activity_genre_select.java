package com.example.bookunion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_genre_select extends AppCompatActivity {

    Button fiction, novel, poetry, biography, thriller, mystery, others;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_search);

    }
    public void fictionClicked(View view){
        Intent intent = new Intent(activity_genre_select.this, activity_fiction.class);
        startActivity(intent);
    }
    public void novelClicked(View view){
        Intent intent = new Intent(activity_genre_select.this, activity_novel.class);
        startActivity(intent);
    }
    public void poetryClicked(View view){
        Intent intent = new Intent(activity_genre_select.this, activity_poetry.class);
        startActivity(intent);
    }
    public void biographyClicked(View view){
        Intent intent = new Intent(activity_genre_select.this, activity_biography.class);
        startActivity(intent);
    }
    public void thrillerClicked(View view){
        Intent intent = new Intent(activity_genre_select.this, activity_thriller.class);
        startActivity(intent);
    }
    public void mysteryClicked(View view){
        Intent intent = new Intent(activity_genre_select.this, activity_academic.class);
        startActivity(intent);
    }
    public void othersClicked(View view){
        Intent intent = new Intent(activity_genre_select.this, activity_others.class);
        startActivity(intent);
    }
}
