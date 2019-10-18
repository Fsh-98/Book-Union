package com.example.bookunion;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_general_search extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArtistsAdapter adapter;
    private List<DataSetFireBook> artistList;
    EditText search;
    Button button;

    DatabaseReference dbArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_search);

        search = findViewById(R.id.search_book);

        //String s = search.getText().toString();

        recyclerView = findViewById(R.id.recyclerView_2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        artistList = new ArrayList<>();
        adapter = new ArtistsAdapter(this, artistList);
        recyclerView.setAdapter(adapter);

        //1. SELECT * FROM Artists
        dbArtists = FirebaseDatabase.getInstance().getReference("Users");

        //2. SELECT * FROM Artists WHERE id = "-LAJ7xKNj4UdBjaYr8Ju"

        Query query = FirebaseDatabase.getInstance().getReference("Users")
                .orderByChild("user_ID")
                .equalTo("rbl8e5rKkJVgKilGB06MTS46JdX2");
        query.addListenerForSingleValueEvent(valueEventListener)

        //3. SELECT * FROM Artists WHERE country = "India"
        //Query query3 = FirebaseDatabase.getInstance().getReference("Artists")
        //        .orderByChild("country")
        //        .equalTo("India");

        //4. SELECT * FROM Artists LIMIT 2
        //Query query4 = FirebaseDatabase.getInstance().getReference("Artists").limitToFirst(2);


        //5. SELECT * FROM Artists WHERE age < 30
        //Query query5 = FirebaseDatabase.getInstance().getReference("Artists")
        //        .orderByChild("age")
         //       .endAt(29);


        //6. SELECT * FROM Artists WHERE name = "A%"
        //Query query6 = FirebaseDatabase.getInstance().getReference("Artists")
         //       .orderByChild("name")
        //        .startAt("A")
        //        .endAt("A\uf8ff");

        ;
        /*
         * You just need to attach the value event listener to read the values
         * for example
         * query6.addListenerForSingleValueEvent(valueEventListener)
         * */
    }



    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Users artist = snapshot.getValue(Users.class);
                    //artistList.add(artist);
                    Intent intent = new Intent(activity_general_search.this, activity_user_details.class);
                    intent.putExtra("User_Name",artist.getUser_name());
                    intent.putExtra("User_Address",artist.getUser_address());
                    intent.putExtra("User_Email", artist.getUser_email());
                    intent.putExtra("User_Mobile", artist.getUser_number());
                    //intent.putExtra("User_Dummy", artist.getUser_dummy());

                    startActivity(intent);


                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public void button_clicked(View view){

    }
}

