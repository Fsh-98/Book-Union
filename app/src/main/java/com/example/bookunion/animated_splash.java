package com.example.bookunion;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class animated_splash extends AppCompatActivity {//import android.os.Bundle;
//import android.os.Handler;
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.drawable.AnimationDrawable;
//import android.graphics.drawable.BitmapDrawable;
//import android.view.Menu;
//import android.widget.ImageView;


        Timer timer;

        ImageView iv1;
        AnimationDrawable Anim;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);




            iv1 = findViewById(R.id.splash_image);
            try {
                BitmapDrawable frame1 = (BitmapDrawable) getResources().getDrawable(
                        R.drawable.phase_1);
                BitmapDrawable frame2 = (BitmapDrawable) getResources().getDrawable(
                        R.drawable.phase_2);
                BitmapDrawable frame3 = (BitmapDrawable) getResources().getDrawable(
                        R.drawable.phase_3);
                BitmapDrawable frame4 = (BitmapDrawable) getResources().getDrawable(
                        R.drawable.phase_4);
                BitmapDrawable frame5 = (BitmapDrawable) getResources().getDrawable(
                        R.drawable.phase_5);

                Anim = new AnimationDrawable();
                Anim.addFrame(frame1, 800);
                Anim.addFrame(frame2, 800);
                Anim.addFrame(frame3, 800);
                Anim.addFrame(frame4, 800);
                Anim.addFrame(frame5, 800);
                Anim.setOneShot(true);
                iv1.setBackgroundDrawable(Anim);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    public void run() {

                        Anim.start();

                    }
                }, 400);

            } catch (Exception e) {
                // TODO: handle exception
            }
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent;
                    intent = new Intent(animated_splash.this, log_in_activity.class);
                    startActivity(intent);
                    finish();
                }
            }, 4000);



        }
    }




