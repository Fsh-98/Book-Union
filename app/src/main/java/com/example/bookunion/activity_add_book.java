package com.example.bookunion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class activity_add_book extends AppCompatActivity {

    EditText name_book;
    EditText author_book;
    EditText language_book;
    EditText genre_book;
    EditText notes_book;
    CheckBox sharing_type;
    Button button;
    RadioButton free_button;
    RadioButton sale_button;
    int book_type;

    DatabaseReference databaseReference;

    String medium;
    String currentUser;
    int e = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        databaseReference = FirebaseDatabase.getInstance().getReference("Books");
        //Toast.makeText(getActivity(), "Fill up all the fields", Toast.LENGTH_SHORT).show();

        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Fiction");
        arrayList.add("Novel");
        arrayList.add("Poetry");
        arrayList.add("Biography");
        arrayList.add("Thriller");
        arrayList.add("Academic");
        arrayList.add("Others");

        //String book_genre;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String book_genre = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected: " + book_genre,          Toast.LENGTH_LONG).show();
                medium = book_genre;
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();



    }

    public void done_button_clicked(View view){
        name_book = findViewById(R.id.book_name);
        author_book = findViewById(R.id.book_author);
        language_book = findViewById(R.id.book_language);
        //genre_book = findViewById(R.id.book_genre);
        notes_book = findViewById(R.id.book_notes);
        free_button = findViewById(R.id.rb_free);
        sale_button = findViewById(R.id.rb_sale);
        //sharing_type = findViewById(R.id.radio_buttton);

        String book_user = name_book.getText().toString();
        String book_author = author_book.getText().toString();
        String book_language = language_book.getText().toString();
        String book_genre = medium;
        String book_notes = notes_book.getText().toString();
        String book_owner = FirebaseAuth.getInstance().getCurrentUser().getUid();


        if(free_button.isChecked()){
            book_type = 0;
        }else{
            book_type = 1;
        }
        if(book_user.isEmpty()){
            name_book.setError("Enter book name");
            e++;
        }
        if(book_author.isEmpty()){
            author_book.setError("Enter author name");
            e++;
        }
        if(book_language.isEmpty()){
            language_book.setError("Enter book language");
            e++;
        }
        if(book_notes.isEmpty()){
            notes_book.setError("Enter brief note of this book");
            e++;
        }

        try{
            if(e == 0){
                String key = databaseReference.push().getKey();

                if(book_genre.equals("Fiction") && book_type == 0){
                    books books = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Fiction_Books");
                    books books_1 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Free_Books");
                    books books_2 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_2);

                    databaseReference = FirebaseDatabase.getInstance().getReference(currentUser);
                    books books_3 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_3);

                }
                if(book_genre.equals("Fiction") && book_type == 1){
                    books books = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Fiction_Books");
                    books books_1 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Buy_Books");
                    books books_2 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_2);

                    databaseReference = FirebaseDatabase.getInstance().getReference(currentUser);
                    books books_3 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_3);


                }

                if(book_genre.equals("Novel") && book_type == 0){
                    books books = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Novel_Books");
                    books books_1 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Free_Books");
                    books books_2 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_2);

                    databaseReference = FirebaseDatabase.getInstance().getReference(currentUser);
                    books books_3 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_3);

                }
                if(book_genre.equals("Novel") && book_type == 1){
                    books books = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Novel_Books");
                    books books_1 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Buy_Books");
                    books books_2 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_2);

                    databaseReference = FirebaseDatabase.getInstance().getReference(currentUser);
                    books books_3 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_3);

                }

                if(book_genre.equals("Poetry") && book_type == 0){
                    books books = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Poetry_Books");
                    books books_1 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Free_Books");
                    books books_2 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_2);

                    databaseReference = FirebaseDatabase.getInstance().getReference(currentUser);
                    books books_3 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_3);

                }
                if(book_genre.equals("Poetry") && book_type == 1){
                    books books = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Poetry_Books");
                    books books_1 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Buy_Books");
                    books books_2 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_2);

                    databaseReference = FirebaseDatabase.getInstance().getReference(currentUser);
                    books books_3 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_3);

                }

                if(book_genre.equals("Biography") && book_type == 0){
                    books books = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Biography_Books");
                    books books_1 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Free_Books");
                    books books_2 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_2);

                    databaseReference = FirebaseDatabase.getInstance().getReference(currentUser);
                    books books_3 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_3);

                }
                if(book_genre.equals("Biography") && book_type == 1){
                    books books = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Biography_Books");
                    books books_1 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Buy_Books");
                    books books_2 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_2);

                    databaseReference = FirebaseDatabase.getInstance().getReference(currentUser);
                    books books_3 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_3);

                }

                if(book_genre.equals("Thriller") && book_type == 0){
                    books books = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Thriller_Books");
                    books books_1 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Free_Books");
                    books books_2 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_2);

                    databaseReference = FirebaseDatabase.getInstance().getReference(currentUser);
                    books books_3 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_3);

                }
                if(book_genre.equals("Thriller") && book_type == 1){
                    books books = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Thriller_Books");
                    books books_1 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Buy_Books");
                    books books_2 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_2);

                    databaseReference = FirebaseDatabase.getInstance().getReference(currentUser);
                    books books_3 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_3);



                }

                if(book_genre.equals("Academic") && book_type == 0){
                    books books = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Academic_Books");
                    books books_1 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Free_Books");
                    books books_2 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_2);

                    databaseReference = FirebaseDatabase.getInstance().getReference(currentUser);
                    books books_3 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_3);

                }
                if(book_genre.equals("Academic") && book_type == 1){
                    books books = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Academic_Books");
                    books books_1 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Buy_Books");
                    books books_2 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_2);

                    databaseReference = FirebaseDatabase.getInstance().getReference(currentUser);
                    books books_3 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_3);

                }

                if(book_genre.equals("Others") && book_type == 0){
                    books books = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Others_Books");
                    books books_1 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Free_Books");
                    books books_2 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_2);

                    databaseReference = FirebaseDatabase.getInstance().getReference(currentUser);
                    books books_3 = new books(book_user, book_author, book_language, book_genre, book_notes, "Free", book_owner);
                    databaseReference.child(key).setValue(books_3);

                }
                if(book_genre.equals("Others") && book_type == 1){
                    books books = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books);
                    databaseReference = FirebaseDatabase.getInstance().getReference("Others_Books");
                    books books_1 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_1);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Buy_Books");
                    books books_2 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_2);

                    databaseReference = FirebaseDatabase.getInstance().getReference(currentUser);
                    books books_3 = new books(book_user, book_author, book_language, book_genre, book_notes, "Sale", book_owner);
                    databaseReference.child(key).setValue(books_3);

                }


                Intent intent = new Intent(activity_add_book.this, activity_user_profile.class);
                startActivity(intent);
                Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
                finish();
                //Toast.makeText(getActivity(), "Book Added", Toast.LENGTH_SHORT).show();
            }else{
                //Toast.makeText(getActivity(), "Field(s) might be empty", Toast.LENGTH_SHORT).show();
                e = 0;
            }

        }catch (Exception ex){
            //Toast.makeText(getActivity(), "Field(s) might be empty", Toast.LENGTH_SHORT).show();
            e = 0;
        }

    }
}
