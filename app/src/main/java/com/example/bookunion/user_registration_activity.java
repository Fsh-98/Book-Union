package com.example.bookunion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_registration_activity extends AppCompatActivity {
    EditText user_address;
    EditText user_email;
    EditText user_password;
    EditText user_password2;
    EditText user_number;
    CheckBox user_agreed;
    int error = 0;


    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration);

        //databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //databaseReference = FirebaseDatabase.getInstance().getReference("Users");

    }


    public void signUpClicked(View view){

        user_password2 =  findViewById(R.id.user_password_2);


        //For authentication purpose
        user_email = findViewById(R.id.user_mail);
        user_password = findViewById(R.id.user_password_1);

        String password_user_2 = user_password2.getText().toString();

        //For authentication purpose
        final String email_user = user_email.getText().toString();
        final String password_user = user_password.getText().toString();

        if(email_user.isEmpty()){
            user_email.setError("Please enter your email");
            user_email.requestFocus();
        }
        if(password_user.isEmpty()){
            user_password.setError("Please enter your password");
        }
        if(password_user_2.isEmpty()){
            user_password2.setError("Please re enter your password");
        }

        checkEmail(email_user);
        checkPasswordMatches(password_user, password_user_2);
        if(error == 0){

          mAuth.createUserWithEmailAndPassword(email_user,password_user ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()) {
                          Toast.makeText(user_registration_activity.this, "Continued", Toast.LENGTH_SHORT).show();
                          FirebaseUser user = mAuth.getCurrentUser();
                          Intent intent = new Intent(user_registration_activity.this, user_registration_2.class);
                          intent.putExtra("email", email_user);
                          intent.putExtra("password", password_user);
                          startActivity(intent);
                          finish();

                      } else {
                          if(task.getException() instanceof FirebaseAuthUserCollisionException)
                          {
                              Toast.makeText(getApplicationContext(), "User already registered", Toast.LENGTH_LONG).show();
                          }
                          else{
                              Toast.makeText(getApplicationContext(), "Error: "+task.getException(), Toast.LENGTH_LONG).show();
                          }
                      }
                  }
              });
      }

        error = 0;

    }

    private void checkEmail(String email_user) {
        if(!Patterns.EMAIL_ADDRESS.matcher(email_user).matches()){
            user_email.setError("Please enter a valid email address");
            //user_email.requestFocus();
            error++;
        }
    }



    private void checkPasswordMatches(String password_user, String password_user_2) {
        if(!password_user.equals(password_user_2)){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            error++;
        }
        if(password_user.length()<6 && password_user_2.length()<6)
        {
            user_password.setError("Minimum 6 letters");
            //
            error++;

        }
    }




}
