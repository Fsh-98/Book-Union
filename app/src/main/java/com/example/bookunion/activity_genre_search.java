package com.example.bookunion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class activity_genre_search extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DataSetFireBook> arrayList;
    private FirebaseRecyclerOptions<DataSetFireBook> options;
    private FirebaseRecyclerAdapter<DataSetFireBook, FirebaseViewHolder> adapter;
    private DatabaseReference databaseReference, dbReference;
    //SearchView searchView;
    EditText search;
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_search);

            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            arrayList = new ArrayList<>();
            //searchView = findViewById(R.id.search_view);
            //databaseReference = new FirebaseDatabase().getInstance().getReference().child("Books");

            //search = findViewById(R.id.search_text);

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Books");

            databaseReference.keepSynced(true);

            options = new FirebaseRecyclerOptions.Builder<DataSetFireBook>().setQuery(databaseReference, DataSetFireBook.class).build();

            adapter = new FirebaseRecyclerAdapter<DataSetFireBook, FirebaseViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull final DataSetFireBook model) {
                    holder.bookName.setText(model.getBook_name());
                    holder.authorName.setText(model.getBook_author());
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(activity_genre_search.this, activity_book_details.class);
                            intent.putExtra("Book_Name",model.getBook_name());
                            intent.putExtra("Author_Name",model.getBook_author());
                            intent.putExtra("Book_Genre", model.getBook_genre());
                            intent.putExtra("Book_Language",model.getBook_language());
                            intent.putExtra("Book_Notes", model.getBook_notes());
                            intent.putExtra("Book_Type", model.getBook_type());
                            intent.putExtra("Book_Owner", model.getBook_owner());

                            startActivityForResult(intent, 1);
                            //startActivity(intent);
                        }
                    });
                }

                @NonNull
                @Override
                public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                    return new FirebaseViewHolder(LayoutInflater.from(activity_genre_search.this).inflate(R.layout.row, viewGroup, false));
                }
            };


            recyclerView.setAdapter(adapter);

            //searchView = findViewById(R.id.search_view);
            //String Search = search.getText().toString();

            dbReference = FirebaseDatabase.getInstance().getReference("Books");
            //dbReference.addValueEventListener(valueEventListener);
            //Query query = FirebaseDatabase.getInstance().getReference("Books").orderByChild("book_name").equalTo(Search);
            //query.addListenerForSingleValueEvent(valueEventListener);
        }
/*
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            arrayList.clear();
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    DataSetFireBook b = snapshot.getValue(DataSetFireBook.class);
                    arrayList.add(b);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };*/




    }


