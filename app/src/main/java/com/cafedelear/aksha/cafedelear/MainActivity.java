package com.cafedelear.aksha.cafedelear;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.cafedelear.aksha.cafedelear.Activity.Bottom_navigationActivity;
import com.cafedelear.aksha.cafedelear.Activity.LoginActivity;
import com.cafedelear.aksha.cafedelear.Activity.RegistrationActivity;
import com.cafedelear.aksha.cafedelear.Fragment.HomeFragment;
import com.cafedelear.aksha.cafedelear.Fragment.JobpostFragment;
import com.cafedelear.aksha.cafedelear.Fragment.NotificationFragment;
import com.cafedelear.aksha.cafedelear.Fragment.PaymentDetailFragment;
import com.cafedelear.aksha.cafedelear.Fragment.ProfileFragment;
import com.cafedelear.aksha.cafedelear.Utlities.Session;

public class MainActivity extends AppCompatActivity  {

    Button login,register;
    Session session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

}
