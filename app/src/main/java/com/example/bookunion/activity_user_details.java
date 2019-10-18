package com.example.bookunion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;

public class activity_user_details extends AppCompatActivity {
    TextView details_book_name;
    TextView details_book_author;
    TextView details_book_language;
    TextView details_book_genre;
    TextView dummy;
    //TextView details_book_notes;
    //TextView details_book_type;

    String book;
    String author;
    String language;
    String genre;
    String dummy_string;


    EditText e1, e2, codeNumber;
    TextView phoneNumber;
    GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth, auth;
    private static final int REQUEST_CALL = 1;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    SignInButton signInButton;

    ImageButton image_button;
    //String notes;
    //String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        phoneNumber = findViewById(R.id.phoneField);

        book = getIntent().getStringExtra("User_Name");
        author = getIntent().getStringExtra("User_Address");
        language = getIntent().getStringExtra("User_Email");
        genre = getIntent().getStringExtra("User_Mobile");
        dummy_string = getIntent().getStringExtra("User_Dummy");
        //notes = getIntent().getStringExtra("Book_Notes");
        //type = getIntent().getStringExtra("Book_type");

        details_book_name = findViewById(R.id.details_book_name);
        details_book_author = findViewById(R.id.details_book_author);
        details_book_language = findViewById(R.id.details_book_language);
        phoneNumber = findViewById(R.id.phoneField);
        image_button= findViewById(R.id.imageButton);

        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });
        //dummy = findViewById(R.id.dummy);
        //details_book_notes = findViewById(R.id.details_book_notes);
        //details_book_type = findViewById(R.id.details_book_type);


        details_book_name.setText(book);
        details_book_author.setText(author);
        details_book_language.setText(language);
        phoneNumber.setText(genre);
        //dummy.setText(dummy_string);
        //details_book_notes.setText(notes);
        //details_book_type.setText(type);
    }

    //method for phone call
    private void makePhoneCall() {
        String number = phoneNumber.getText().toString();
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(activity_user_details.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity_user_details.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(activity_user_details.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    //request for phone call
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
