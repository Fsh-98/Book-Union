package com.example.bookunion;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen_activity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        //progressBar = (ProgressBar) findViewById(R.id.progress);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                Intent intent = new Intent(splash_screen_activity.this, log_in_activity.class);
                startActivity(intent);
                finish();
            }
        });

        thread.start();
    }

    public void doWork(){
        for(i=1; i<=100; i++)
        {
            try{
                Thread.sleep(20);
                progressBar.setProgress(i);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
