package com.example.bookunion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_registration_2 extends AppCompatActivity {
    public EditText user_name;
    public EditText user_address;
    public EditText user_number;
    CheckBox user_agreed;
    int error = 0;

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration_2);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");


    }

    public void signUpClicked(View view){
        user_name = findViewById(R.id.user_name);
        user_address = findViewById(R.id.user_address);
        user_number = findViewById(R.id.user_mobile);
        user_agreed = findViewById(R.id.check_box);

        String name_user = user_name.getText().toString();
        String address_user = user_address.getText().toString();
        String number_user = user_number.getText().toString();
        String user_acc = FirebaseAuth.getInstance().getCurrentUser().getUid();

        if(name_user.isEmpty()){
            user_name.setError("Please enter your name");
        }
        if(address_user.isEmpty()){
            user_address.setError("Please enter your address");
        }
        if(number_user.isEmpty()) {
            user_number.setError("Please enter your mobile number");
        }

        if(!user_agreed.isChecked()){
            user_agreed.setError("Please check to proceed");
            error++;
        }
        String email_user = getIntent().getStringExtra("email");
        String password_user = getIntent().getStringExtra("password");

        checkName(name_user);
        checkNumber(number_user);

        Users users = new Users(name_user, email_user, password_user, address_user, number_user, user_acc);
        String key = databaseReference.push().getKey();

        if(error == 0){
            databaseReference.child(key).setValue(users);
            Intent intent = new Intent(user_registration_2.this, welcome_activity.class);
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
        else{
            error = 0;
        }
    }

    private void checkName(String name_user) {
        for(int i=0; i<name_user.length(); i++){
            char c = name_user.charAt(i);
            if(!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c == ' '))) {
                //continue;
                Toast.makeText(this, "Please enter valid name", Toast.LENGTH_SHORT).show();
                error++;
                break;
            }
        }
    }

    private void checkNumber(String number_user) {
        for(int i=0; i<number_user.length(); i++){
            char c = number_user.charAt(i);
            if(i==0 && c!='0'){
                error++;
            }
            if(i==1 && c!='1'){
                error++;
            }
            if(!(c >= '0' && c <= '9')){
                //continue;
                Toast.makeText(this, "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
                error++;
                break;
            }
        }
        if(number_user.length() != 11){
            if(number_user.length() != 0){
                Toast.makeText(this, "Problem in mobile number length", Toast.LENGTH_SHORT).show();
                error++;
            }

        }
    }
}
