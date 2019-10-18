package com.example.bookunion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class welcome_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome_page);
        Button button = (Button) findViewById(R.id.done_button);

        button.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View view){
                        Intent intent = new Intent(welcome_activity.this, activity_user_profile.class);
                        Toast.makeText(welcome_activity.this, "Automatically logged in", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                });
    }
}
