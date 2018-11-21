package com.cafedelear.aksha.cafedelear.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cafedelear.aksha.cafedelear.MainActivity;
import com.cafedelear.aksha.cafedelear.R;
import com.cafedelear.aksha.cafedelear.Utlities.Session;

public class SplashScreenActivity extends AppCompatActivity {

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        final Intent intent = new Intent(this,MainActivity.class);

        Thread thread = new Thread() {

            public void run(){

                try {
                    sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }

}

