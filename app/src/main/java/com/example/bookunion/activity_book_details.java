package com.example.bookunion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class activity_book_details extends AppCompatActivity {

    TextView details_book_name;
    TextView details_book_author;
    TextView details_book_language;
    TextView details_book_genre;
    TextView details_book_notes;
    TextView details_book_type;

    String book;
    String author;
    String language;
    String genre;
    String notes;
    String type;
    String owner;

    DatabaseReference dbArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        dbArtists = FirebaseDatabase.getInstance().getReference("Users");

        book = getIntent().getStringExtra("Book_Name");
        author = getIntent().getStringExtra("Author_Name");
        language = getIntent().getStringExtra("Book_Language");
        genre = getIntent().getStringExtra("Book_Genre");
        notes = getIntent().getStringExtra("Book_Notes");
        type = getIntent().getStringExtra("Book_type");
        owner = getIntent().getStringExtra("Book_Owner");

        details_book_name = findViewById(R.id.details_book_name);
        details_book_author = findViewById(R.id.details_book_author);
        details_book_language = findViewById(R.id.details_book_language);
        details_book_genre = findViewById(R.id.details_book_genre);
        details_book_notes = findViewById(R.id.details_book_notes);
        //details_book_type = findViewById(R.id.details_book_type);


        details_book_name.setText(book);
        details_book_author.setText(author);
        details_book_language.setText(language);
        details_book_genre.setText(genre);
        details_book_notes.setText(notes);
        //details_book_type.setText(type);

        //Toast.makeText(this, book, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, author, Toast.LENGTH_SHORT).show();
    }
    public void owner_details_clicked(View view){
        Query query = FirebaseDatabase.getInstance().getReference("Users")
                .orderByChild("user_ID")
                .equalTo(owner);
        query.addListenerForSingleValueEvent(valueEventListener);

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //artistList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Users artist = snapshot.getValue(Users.class);
                    //artistList.add(artist);
                    Intent intent = new Intent(activity_book_details.this, activity_user_details.class);
                    intent.putExtra("User_Name",artist.getUser_name());
                    intent.putExtra("User_Address",artist.getUser_address());
                    intent.putExtra("User_Email", artist.getUser_email());
                    intent.putExtra("User_Mobile", artist.getUser_number());
                    //intent.putExtra("User_Dummy", artist.getUser_dummy());

                    startActivity(intent);
                    finish();

                }
                // adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
